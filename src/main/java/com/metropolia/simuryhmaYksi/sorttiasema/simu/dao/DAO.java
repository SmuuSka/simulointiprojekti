package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.client.result.ResultSetMetaData;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 * Data Access Object
 * Luokan tarkoituksena on tietokannasta tuoda/viedä dataa
 * @see IDAO
 */

public class DAO implements IDAO {
    /**
     * simulaatioTulos on yksi tietokannasta haettu simulaatiotulos-olio
     */
    private  SimulaatioData simulaatioTulos;
    /**
     * Yhteysmuuttuja tietokannan ja simulaattorin välillä
     */
    private final Connection connection;
    /**
     * Haetaan tietoa System.properties tiedostosta
     */
    private static final ResourceBundle rb = ResourceBundle.getBundle("System");
    /**
     * Haetaan tiedostosta merkkijonot
     */
    private static final String url1 = rb.getString("url1") + rb.getString("username1") + rb.getString("password1");
    /**
     * Simulaatin ID
     */
    private static int simuID;
    /**
     * Kaikki tietokanta-oliot listattuna
     */
    private static ArrayList<SimulaatioData> simulaatioDataObjektiLista = new ArrayList<>();

    /**
     * Konstruktori, joka luo uuden yhteyden tietokantaan.
     */
    public DAO() {
        try {
            connection = (Connection) DriverManager.getConnection(url1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hakee ensimmäisen taulun tiedot tietokannasta, ja kutsuu muiden taulujen hakumetodeja.
     * Metodit on palasteltu useampaan osaan koodin luettavuuden kannalta.
     * @throws SQLException
     */
    private void  haeSimulaationTiedot() throws SQLException {
        String id = Integer.toString(simuID);
        String query = "SELECT * FROM simulaatio";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    simulaatioTulos = new SimulaatioData(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getByte(3));
                    haeSimulaationParametrit();
                    haeSimulaattorinTulokset();
                    simulaatioDataObjektiLista.add(simulaatioTulos);
                }
            }
        }
    }

    /**
     * Hakee tietokannasta parametrit-taulun tiedot ja luo siitä hakuoliota.
     * @throws SQLException
     */
    private void haeSimulaationParametrit() throws SQLException {
        String id = Integer.toString(simulaatioTulos.getId());
        String query = "SELECT simulointiaika,viive, purkunopeusPerSek, vaihteluvaliMin,vaihteluvaliMax,jatteenTodennakoisyysElektroniikka,jatteenTodennakoisyysPalamatonJate,jatteenTodennakoisyysPalavaJate, aktiivisuus FROM parametrit WHERE parametrit.parametriID="+id;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.first();
                SimulaatioData.SimulaationParametrit simulaationParametrit = simulaatioTulos.new SimulaationParametrit(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                simulaatioTulos.setParametrit(simulaationParametrit);
            }
        }
    }

    /**
     * Hakee tietokannasta tulokset-taulun tiedot ja luo siitä hakuoliota.
     * @throws SQLException
     */
    private void haeSimulaattorinTulokset() throws SQLException {
        String id = Integer.toString(simulaatioTulos.getId());
        String query = "SELECT * FROM tulokset WHERE tulokset.tuloksetID="+id;
        ArrayList<SimpleIntegerProperty> tuloksetJotkaKokonaislukuja = new ArrayList<>();
        ArrayList<SimpleDoubleProperty> tuloksetJotkaLiukulukuja = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.first();
                ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
                    for (int i = 3; i < 13; i++) {
                        tuloksetJotkaKokonaislukuja.add(new SimpleIntegerProperty(rs.getInt(i)));
                    }
                    for (int j = 13; j < resultSetMetaData.getColumnCount() + 1; j++) {
                        tuloksetJotkaLiukulukuja.add((new SimpleDoubleProperty(rs.getDouble(j))));
                    }
                    SimulaatioData.SimulaattorinTulokset simulaattorinTulokset = simulaatioTulos.new SimulaattorinTulokset(tuloksetJotkaKokonaislukuja, tuloksetJotkaLiukulukuja);
                    simulaatioTulos.setTulokset(simulaattorinTulokset);
            }
        }
    }

    /**
     * Hakee viimeisimmäksi lisätyn tiedon tietokannasta ja palauttaa simulaatiotuloksen ID:n.
     * @return kokonaisluku ID
     * @throws SQLException
     */
    private int setID() throws SQLException {
        String query = "SELECT simulaatioID FROM simulaatio";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                simuID = rs.getInt(1);
            }
        }
        return simuID;
    }

    @Override
    public synchronized void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit, double viive, double purkuaika, String aktiivisuus) throws SQLException {
        lisaaSimulaatioTaulu();
        lisaaTuloksetTaulu();
        lisaaParametritTaulu();
        lisaaSimulaatio(tyhjaksi);
        lisaaParametrit(aika, vaihteluvali, jateprosentit, viive,purkuaika, aktiivisuus);
    }

    /**
     * Jos tietokannasta ei löydy simulaatio-taulua, metodi luo taulun Twr-lausekkeella.
     * @throws SQLException
     */
    private void lisaaSimulaatioTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS simulaatio (" +
                "simulaatioID INT PRIMARY KEY AUTO_INCREMENT," +
                "paivamaara DATE," +
                "ajetaanTyhjaksi BIT(1))";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    /**
     * Jos tietokannasta ei löydy parametrit-taulua, metodi luo taulun Twr-lausekkeella.
     * @throws SQLException
     */
    private void lisaaParametritTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS parametrit (parametriID INT PRIMARY KEY AUTO_INCREMENT," +
                " simulaatioID INT," +
                " simulointiaika DECIMAL, " +
                "viive DECIMAL, " +
                "purkunopeusPerSek DECIMAL(10,1), " +
                "vaihteluvaliMin INT," +
                " vaihteluvaliMax INT, " +
                "jatteenTodennakoisyysElektroniikka INT," +
                " jatteenTodennakoisyysPalamatonJate INT, " +
                "jatteenTodennakoisyysPalavaJate INT," +
                " aktiivisuus VARCHAR(20)," +
                " CONSTRAINT fk_parametrit_simulaatioID FOREIGN KEY (simulaatioID) REFERENCES simulaatio(simulaatioID) ON DELETE CASCADE)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    /**
     * Jos tietokannasta ei löydy tulokset-taulua, metodi luo taulun Twr-lausekkeella.
     * @throws SQLException
     */
    private void lisaaTuloksetTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS tulokset (\n" +
                "\ttuloksetID INT(11) PRIMARY KEY AUTO_INCREMENT,\n" +
                "\tsimulaatioID INT(11),\n" +
                "\tsaapuneidenLKM INT(11),\n" +
                "\tpalveltujenLKM INT(11),\n" +
                "\tkokonaisoleskeluaikaSaapuva INT(11),\n" +
                "\tkokonaisoleskeluaikaElektroniikka INT(11),\n" +
                "\tkokonaisoleskeluaikaPalamatonJate INT(11),\n" +
                "\tkokonaisoleskeluaikaPalavaJate INT(11),\n" +
                "\tpalveltujenLkmSaapuva INT(11) ,\n" +
                "\tpalveltujenLkmElektroniikka INT(11),\n" +
                "\tpalveltujenLkmPalamatonJate INT(11),\n" +
                "\tpalveltujenLkmPalavaJate INT(11),\n" +
                "\taktiiviaikaSaapuva DECIMAL(10,1),\n" +
                "\taktiiviaikaElektroniikka DECIMAL(10,1),\n" +
                "\taktiiviaikaPalamatonJate DECIMAL(10,1),\n" +
                "\taktiiviaikaPalavaJate DECIMAL(10,1),\n" +
                "\tkokonaisaika DECIMAL(10,1),\n" +
                "\tjatteenKokonaismaara DECIMAL(10,1),\n" +
                "\tsuoritusteho DECIMAL(10,1),\n" +
                "\tavgJononPituusSaapuva DECIMAL(10,1),\n" +
                "\tavgJononPituusElektroniikka DECIMAL(10,1),\n" +
                "\tavgJononPituusPalamatonJate DECIMAL(10,1),\n" +
                "\tavgJononPituusPalavaJate DECIMAL(10,1),\n" +
                "\tavgLapimenoSaapuva DECIMAL(10,1),\n" +
                "\tavgLapimenoElektroniikka DECIMAL(10,1),\n" +
                "\tavgLapimenoPalamatonJate DECIMAL(10,1),\n" +
                "\tavgLapimenoPalavaJate DECIMAL(10,1),\n" +
                "\tkayttoasteSaapuva DECIMAL(10,1),\n" +
                "\tkayttoasteElektroniikka DECIMAL(10,1),\n" +
                "\tkayttoastePalamatonJate DECIMAL(10,1),\n" +
                "\tkayttoastePalavaJate DECIMAL(10,1),\n" +
                "\tavgPalveluaikaSaapuva DECIMAL(10,1),\n" +
                "\tavgPalveluaikaElektroniikka DECIMAL(10,1),\n" +
                "\tavgPalveluaikaPalamatonJate DECIMAL(10,1),\n" +
                "\tavgPalveluaikaPalavaJate DECIMAL(10,1),\n" +
                "\tavgJatteenmaara DECIMAL(10,1),\n" +
                "\tjatemaaraElektroniikka DECIMAL(10,1),\n" +
                "\tjatemaaraPalamatonJate DECIMAL(10,1),\n" +
                "\tjatemaaraPalavaJate DECIMAL(10,1),\n" +
                "\tCONSTRAINT fk_simulaatioID\n" +
                "\tFOREIGN KEY (simulaatioID)\n" +
                "\tREFERENCES simulaatio(simulaatioID) ON DELETE CASCADE)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    /**
     * Metodi lisää simulaatio-tauluun uuden simulaatiodatan Twr-lausekkeella.
     * @param ajaTyhjaksiSimulaattori kontrollerin kautta UI:sta tuleva tieto.
     * @throws SQLException
     */
    private void lisaaSimulaatio(int ajaTyhjaksiSimulaattori) throws SQLException {
        String query = "INSERT INTO simulaatio (paivamaara,ajetaanTyhjaksi) VALUES (?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, ajaTyhjaksiSimulaattori);
            ps.executeUpdate();
            simuID = setID();
        }
    }

    /**
     * Metodi lisää parametrit-tauluun uuden simulaation parametridatan Twr-lausekkeella.
     * @param simulointiaika = kontrollerin kautta UI:sta tuleva tieto.
     * @param vaihteluvali = kontrollerin kautta UI:sta tuleva tieto.
     * @param jateprosentit = kontrollerin kautta UI:sta tuleva tieto.
     * @param viive = kontrollerin kautta UI:sta tuleva tieto.
     * @param purkunopeus = kontrollerin kautta UI:sta tuleva tieto.
     * @param aktiivisuus = kontrollerin kautta UI:sta tuleva tieto.
     * @throws SQLException
     */
    private void lisaaParametrit(double simulointiaika, int[] vaihteluvali, int[] jateprosentit, double viive, double purkunopeus, String aktiivisuus) throws SQLException {
        String query = "INSERT INTO parametrit (parametriID,simulaatioID,simulointiaika,viive,purkunopeusPerSek,vaihteluvaliMin," +
                "vaihteluvaliMax,jatteenTodennakoisyysElektroniikka,jatteenTodennakoisyysPalamatonJate," +
                "jatteenTodennakoisyysPalavaJate, aktiivisuus) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //ParametriID
            ps.setInt(1, simuID);
            //SimulointiID
            ps.setInt(2, simuID);
            //Simulaatioaika
            ps.setDouble(3, simulointiaika);
            //Simulaation viive
            ps.setDouble(4,viive);
            //Purkunopeus per sekuntti
            ps.setDouble(5,purkunopeus);
            //Jätemäärä MIN
            ps.setInt(6, vaihteluvali[0]);
            //Jätemäärä MAX
            ps.setInt(7, vaihteluvali[1]);
            //Elektroniikan prosentit
            ps.setInt(8, jateprosentit[0]);
            //Palamattoman jätteen prosentit
            ps.setInt(9, jateprosentit[1]);
            //Palavan jätteen prosentit
            ps.setInt(10, jateprosentit[2]);
            //Ruuhkatiedot
            ps.setString(11,aktiivisuus);

            ps.executeUpdate();
        }
    }

    /**
     * Metodi liittää uuden simulaation kokonaislukudatan tulokset-tauluun.
     * @param suureet tulevat kontrollerin kautta Laskenta-luokasta.
     * @throws SQLException
     */
    private void tuloksetINT(Laskenta suureet) throws SQLException {
        int indexTulokset_1 = 1;
        String query = "INSERT INTO tulokset (" +
                "                tuloksetID," +
                "                simulaatioID," +
                "                saapuneidenLKM,\n" +
                "                palveltujenLKM,\n" +
                "                kokonaisoleskeluaikaSaapuva,\n" +
                "                kokonaisoleskeluaikaElektroniikka,\n" +
                "                kokonaisoleskeluaikaPalamatonJate,\n" +
                "                kokonaisoleskeluaikaPalavaJate,\n" +
                "                palveltujenLkmSaapuva,\n" +
                "                palveltujenLkmElektroniikka,\n" +
                "                palveltujenLkmPalamatonJate,\n" +
                "                palveltujenLkmPalavaJate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //ParametriID
            ps.setInt(indexTulokset_1++, simuID);
            //SimulointiID
            ps.setInt(indexTulokset_1++, simuID);
            //SaapuneidenLKM
            ps.setInt(indexTulokset_1++, suureet.getSaapuneidenLkm());
            //PalveltujenLKM
            ps.setInt(indexTulokset_1++, suureet.getPalveltujenMaara());
            //Kokonaisoleskeluaika Palvelupisteellä, Saapuminen,Elektroniikka,Palamaton Jäte,Palava Jäte
            long[] kOAjat = suureet.getKokonaisoleskeluajat();
            for (long i : kOAjat) {
                ps.setInt(indexTulokset_1++, (int) i);
            }

            int[] pLKM = suureet.getPalveltujenLkm();
            for (int i : pLKM) {
                ps.setInt(indexTulokset_1++, i);
            }

            ps.executeUpdate();
        }
    }

    /**
     * Metodi liittää uuden simulaation liukulukudatan tulokset-tauluun.
     * @param suureet tulevat kontrollerin kautta Laskenta-luokasta.
     * @throws SQLException
     */

    private void tuloksetDouble(Laskenta suureet) throws SQLException {
        int indexTulokset_2 = 1;
        String query = "UPDATE tulokset SET aktiiviaikaSaapuva = ?, aktiiviaikaElektroniikka = ?, aktiiviaikaPalamatonJate = ?, aktiiviaikaPalavaJate = ?, kokonaisaika= ?, jatteenKokonaismaara= ?, suoritusteho= ?, avgJononPituusSaapuva= ?, avgJononPituusElektroniikka= ?, avgJononPituusPalamatonJate= ?,avgJononPituusPalavaJate= ?, avgLapimenoSaapuva= ?, avgLapimenoElektroniikka= ?, avgLapimenoPalamatonJate= ?, avgLapimenoPalavaJate= ?, kayttoasteSaapuva= ?, kayttoasteElektroniikka= ?,kayttoastePalamatonJate= ?,kayttoastePalavaJate= ?, avgPalveluaikaSaapuva= ?, avgPalveluaikaElektroniikka= ?,  avgPalveluaikaPalamatonJate= ?,avgPalveluaikaPalavaJate= ?, avgJatteenmaara = ?, jatemaaraElektroniikka = ?,jatemaaraPalamatonJate = ?,jatemaaraPalavaJate = ? WHERE tuloksetID=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //Palvelupisteiden aktiiviajat,Saapuminen Elektroniikka,Palava Jäte,Palamaton Jäte
            double[] aktiiviaika = suureet.getAktiiviajat();
            for (double d : aktiiviaika) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Simulaation kokonaisaika
            ps.setDouble(indexTulokset_2++, suureet.getKokonaisaika());
            //Jätteiden kokonaismäärä
            ps.setDouble(indexTulokset_2++, suureet.getJatteenKokonaismaara());
            //Suoritusteho
            ps.setDouble(indexTulokset_2++, suureet.getSuoritusteho());
            //Keskimääräinen Jonon Pituus, Saapuminen,Elektroniikka,Palamaton Jäte,Palava Jäte
            double[] avgJPS = suureet.getKeskmJononpituudet();
            for (double d : avgJPS) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Keskimääräinen läpimenoaika, Saapuminen, Elektroniikka,Palamaton Jäte,Palava Jäte
            double[] avgLapimeno = suureet.getKeskmLapimenoajat();
            for (double d : avgLapimeno) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Palvelupisteiden Kayttoasteet, Saapuminen, Elektroniikka,Palamaton Jäte,Palava Jäte
            double[] avgKayttoasteet = suureet.getKayttoasteet();
            for (double d : avgKayttoasteet) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Palvelupisteiden Palveluajat, Saapuminen, Elektroniikka,Palamaton Jäte,Palava Jäte
            double[] palveluajat = suureet.getKeskmPalveluajat();
            for (double d : palveluajat) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Jätemäärän keskiarvo
            if (Double.isNaN(suureet.getKeskmJatteenmaara())) {
                // Jos keskmJatteenmaara on NAN anna NULL sitten ei ole erroreja
                ps.setNull(indexTulokset_2++, Types.DOUBLE);
            } else {
                // Anna normaalitulos
                ps.setDouble(indexTulokset_2++, suureet.getKeskmJatteenmaara());
            }
            //Palvelupisteiden jätemäärät, Elektroniikka, Palamaton Jäte,Palava Jäte
            double[] jatemaaraPalvelupisteella = suureet.getJatteenMaarat();
            for (double d : jatemaaraPalvelupisteella){
                ps.setDouble(indexTulokset_2++, d);
            }
            //Tulokset-taulun avain
            ps.setInt(indexTulokset_2++, simuID);
            ps.executeQuery();
        }
    }

    @Override
    public synchronized void paivitaData(Laskenta suureet) throws SQLException {
        System.out.println("Tallennetaan tulokset");
        tuloksetINT(suureet);
        tuloksetDouble(suureet);
    }

    @Override
    public synchronized ArrayList<SimulaatioData> simulaatioColumnData() throws SQLException {
        haeSimulaationTiedot();
        return simulaatioDataObjektiLista;
    }

    @Override
    public synchronized void poistaKaikkiTietokannanTaulut() throws SQLException {
        String query = "DROP TABLE parametrit,tulokset, simulaatio";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    @Override
    public synchronized boolean poistaTiettyTulos(int ID) throws SQLException {
        String query = "DELETE FROM simulaatio WHERE simulaatio.simulaatioID="+Integer.toString(ID);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int poistettu = ps.executeUpdate();
            if(poistettu == 1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void suljeYhteys() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

