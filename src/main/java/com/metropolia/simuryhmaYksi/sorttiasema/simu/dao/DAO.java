package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.client.result.ResultSetMetaData;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DAO implements IDAO {
    private int indexTulokset_1 = 1, indexTulokset_2 = 1;
    private static SimulaatioData simulaatioDataOlio;
    private  static SimulaatioData.SimulaattorinTulokset simulaattorinTulokset;
    private static SimulaatioData.SimulaationParametrit simulaationParametrit;
    private static final ResourceBundle rb = ResourceBundle.getBundle("System");
    private static final String url1 = rb.getString("url1") + rb.getString("username1") + rb.getString("password1");
    private static final String SQL_DROPTABLE = "DROP TABLE parametrit,tulokset, simulaatio";
    private static Connection connection = null;
    private static int simuID;
    private static final ArrayList<SimulaatioData> simulaatioDataObjekti = new ArrayList<>();


    private synchronized void haeKaikkiTiedot() throws SQLException {
        haeSimulaationTiedot();
        haeSimulaationParametrit();
        haeSimulaattorinTulokset();
    }

    private static void haeSimulaationTiedot() throws SQLException {
        String id = Integer.toString(simuID);
        String query = "SELECT * FROM simulaatio";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    simulaatioDataOlio = new SimulaatioData(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getByte(3));
                    simulaatioDataObjekti.add(simulaatioDataOlio);
                    //System.out.println("Simulaatio Data: " + simulaatioDataOlio.idProperty() + " " + simulaatioDataOlio.getPaivamaara() + " " + simulaatioDataOlio.simulaatioTyhjaksiProperty());
                }
            }
        }

    }

    private static void haeSimulaationParametrit() throws SQLException {
        String id = Integer.toString(simuID);
        String query = "SELECT simulointiaika, vaihteluvaliMin,vaihteluvaliMax,jatteenTodennakoisyysElektroniikka,jatteenTodennakoisyysPalavaJate,jatteenTodennakoisyysPalamatonJate FROM parametrit";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    simulaationParametrit = simulaatioDataOlio.new SimulaationParametrit(rs.getDouble(1), rs.getInt(2),
                            rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                        simulaatioDataOlio.setParametrit(simulaationParametrit);
                        //simulaatioParametritLista.add(simulaationParametrit);
                    //System.out.println("Simu parametrit: " + simulaationParametrit.aikaProperty());
                }
            }
        }

    }

    private static void haeSimulaattorinTulokset() throws SQLException {
        String id = Integer.toString(simuID);
        String query = "SELECT * FROM tulokset";
        HashMap<SimpleStringProperty, SimpleIntegerProperty> tulosIntAvainArvoParit = new HashMap<>();
        HashMap<SimpleStringProperty, SimpleDoubleProperty> tuloksetDoubleAvainArvoParit = new HashMap<>();
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData resultSetMetaData = (ResultSetMetaData) rs.getMetaData();
                while (rs.next()) {
                    for (int i = 3; i < 13; i++) {
                        String columnName = resultSetMetaData.getColumnName(i);
                        tulosIntAvainArvoParit.put(new SimpleStringProperty(columnName), new SimpleIntegerProperty(rs.getInt(i)));
                    }
                    for (int j = 13; j < resultSetMetaData.getColumnCount(); j++) {
                        String columnName = resultSetMetaData.getColumnName(j);
                        tuloksetDoubleAvainArvoParit.put(new SimpleStringProperty(columnName), new SimpleDoubleProperty(rs.getDouble(j)));
                    }
                }
            }
        }
        simulaattorinTulokset = simulaatioDataOlio.new SimulaattorinTulokset(tulosIntAvainArvoParit, tuloksetDoubleAvainArvoParit);

    }

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

    private static Connection avaaYhteysTietokantaan() {
        try {
            connection = (Connection) DriverManager.getConnection(url1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public synchronized void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit) throws SQLException {
        lisaaSimulaatioTaulu();
        lisaaTuloksetTaulu();
        lisaaParametritTaulu();
        lisaaSimulaatio(tyhjaksi);
        lisaaParametrit(aika, vaihteluvali, jateprosentit);
    }

    private void lisaaSimulaatioTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS simulaatio (" +
                "simulaatioID INT PRIMARY KEY AUTO_INCREMENT," +
                "paivamaara DATE," +
                "ajetaanTyhjaksi BIT(1))";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            System.out.println("Tulos: " + ps.executeUpdate());
        }
    }

    private void lisaaParametritTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS parametrit (parametriID INT PRIMARY KEY AUTO_INCREMENT, simulaatioID INT, simulointiaika DECIMAL," +
                "\tvaihteluvaliMin INT,\n" +
                "\tvaihteluvaliMax INT ,\n" +
                "\tjatteenTodennakoisyysElektroniikka INT,\n" +
                "\tjatteenTodennakoisyysPalavaJate INT,\n" +
                "\tjatteenTodennakoisyysPalamatonJate INT,\n" +
                "    CONSTRAINT fk_parametrit_simulaatioID\n" +
                "    FOREIGN KEY (simulaatioID)\n" +
                "    REFERENCES simulaatio(simulaatioID) ON DELETE CASCADE)";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            System.out.println("Tulos: " + ps.executeUpdate());
        }
    }

    private void lisaaTuloksetTaulu() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS tulokset (\n" +
                "\ttuloksetID INT(11) PRIMARY KEY AUTO_INCREMENT,\n" +
                "\tsimulaatioID INT(11),\n" +
                "\tsaapuneidenLKM INT(11),\n" +
                "\tpalveltujenLKM INT(11),\n" +
                "\tkokonaisoleskeluaikaSaapuva INT(11),\n" +
                "\tkokonaisoleskeluaikaElektroniikka INT(11),\n" +
                "\tkokonaisoleskeluaikaPalavaJate INT(11),\n" +
                "\tkokonaisoleskeluaikaPalamatonJate INT(11),\n" +
                "\tpalveltujenLkmSaapuva INT(11) ,\n" +
                "\tpalveltujenLkmElektroniikka INT(11),\n" +
                "\tpalveltujenLkmPalavaJate INT(11),\n" +
                "\tpalveltujenLkmPalamatonJate INT(11),\n" +
                "\taktiiviaikaSaapuva DECIMAL(10,1),\n" +
                "\taktiiviaikaElektroniikka DECIMAL(10,1),\n" +
                "\taktiiviaikaPalavaJate DECIMAL(10,1),\n" +
                "\taktiiviaikaPalamatonJate DECIMAL(10,1),\n" +
                "\tkokonaisaika DECIMAL(10,1),\n" +
                "\tjatteenKokonaismaara DECIMAL(10,1),\n" +
                "\tsuoritusteho DECIMAL(10,1),\n" +
                "\tavgJononPituusSaapuva DECIMAL(10,1),\n" +
                "\tavgJononPituusElektroniikka DECIMAL(10,1),\n" +
                "\tavgJononPituusPalavaJate DECIMAL(10,1),\n" +
                "\tavgJononPituusPalamatonJate DECIMAL(10,1),\n" +
                "\tavgLapimenoSaapuva DECIMAL(10,1),\n" +
                "\tavgLapimenoElektroniikka DECIMAL(10,1),\n" +
                "\tavgLapimenoPalavaJate DECIMAL(10,1),\n" +
                "\tavgLapimenoPalamatonJate DECIMAL(10,1),\n" +
                "\tkayttoasteSaapuva DECIMAL(10,1),\n" +
                "\tkayttoasteElektroniikka DECIMAL(10,1),\n" +
                "\tkayttoastePalavaJate DECIMAL(10,1),\n" +
                "\tkayttoastePalamatonJate DECIMAL(10,1),\n" +
                "\tavgPalveluaikaSaapuva DECIMAL(10,1),\n" +
                "\tavgPalveluaikaElektroniikka DECIMAL(10,1),\n" +
                "\tavgPalveluaikaPalavaJate DECIMAL(10,1),\n" +
                "\tavgPalveluaikaPalamatonJate DECIMAL(10,1),\n" +
                "\tavgJatteenmaara DECIMAL(10,1),\n" +
                "\tCONSTRAINT fk_simulaatioID\n" +
                "\tFOREIGN KEY (simulaatioID)\n" +
                "\tREFERENCES simulaatio(simulaatioID) ON DELETE CASCADE)";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }

    private void lisaaSimulaatio(int ajaTyhjaksiSimulaattori) throws SQLException {
        String query = "INSERT INTO simulaatio (paivamaara,ajetaanTyhjaksi) VALUES (?,?)";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, ajaTyhjaksiSimulaattori);
            ps.executeUpdate();
            simuID = setID();
        }
    }

    private void lisaaParametrit(double simulointiaika, int[] vaihteluvali, int[] jateprosentit) throws SQLException {
        String query = "INSERT INTO parametrit (parametriID,simulaatioID, simulointiaika,vaihteluvaliMin," +
                "vaihteluvaliMax,jatteenTodennakoisyysElektroniikka,jatteenTodennakoisyysPalavaJate," +
                "jatteenTodennakoisyysPalamatonJate  ) VALUES (?,?,?,?,?,?,?,?)";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //ParametriID
            ps.setInt(1, simuID);
            //SimulointiID
            ps.setInt(2, simuID);
            //Simulaatioaika
            ps.setDouble(3, simulointiaika);
            //Jätemäärä MIN
            ps.setInt(4, vaihteluvali[0]);
            //Jätemäärä MAX
            ps.setInt(5, vaihteluvali[1]);
            //Elektroniikan prosentit
            ps.setInt(6, jateprosentit[0]);
            //Palavan jätteen prosentit
            ps.setInt(7, jateprosentit[1]);
            //Palamattoman jätteen prosentit
            ps.setInt(8, jateprosentit[2]);
            ps.executeUpdate();
        }
    }

    private void tuloksetINT(Laskenta suureet) throws SQLException {
        String query = "INSERT INTO tulokset (" +
                "                tuloksetID," +
                "                simulaatioID," +
                "                saapuneidenLKM,\n" +
                "                palveltujenLKM,\n" +
                "                kokonaisoleskeluaikaSaapuva,\n" +
                "                kokonaisoleskeluaikaElektroniikka,\n" +
                "                kokonaisoleskeluaikaPalavaJate,\n" +
                "                kokonaisoleskeluaikaPalamatonJate,\n" +
                "                palveltujenLkmSaapuva,\n" +
                "                palveltujenLkmElektroniikka,\n" +
                "                palveltujenLkmPalavaJate,\n" +
                "                palveltujenLkmPalamatonJate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //ParametriID
            ps.setInt(indexTulokset_1++, simuID);
            //SimulointiID
            ps.setInt(indexTulokset_1++, simuID);
            //SaapuneidenLKM
            ps.setInt(indexTulokset_1++, suureet.getSaapuneidenLkm());
            //PalveltujenLKM
            ps.setInt(indexTulokset_1++, suureet.getPalveltujenMaara());
            //Kokonaisoleskeluaika Palvelupisteellä, Saapuminen,Elektroniikka,Palava Jäte,Palava Jäte
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

    private void tuloksetDouble(Laskenta suureet) throws SQLException {
        String query = "UPDATE tulokset SET aktiiviaikaSaapuva = ?, aktiiviaikaElektroniikka = ?, aktiiviaikaPalavaJate = ?, aktiiviaikaPalamatonJate = ?, kokonaisaika= ?, jatteenKokonaismaara= ?, suoritusteho= ?, avgJononPituusSaapuva= ?, avgJononPituusElektroniikka= ?, avgJononPituusPalavaJate= ?, avgJononPituusPalamatonJate= ?, avgLapimenoSaapuva= ?, avgLapimenoElektroniikka= ?, avgLapimenoPalavaJate= ?, avgLapimenoPalamatonJate= ?, kayttoasteSaapuva= ?, kayttoasteElektroniikka= ?, kayttoastePalavaJate= ?, kayttoastePalamatonJate= ?, avgPalveluaikaSaapuva= ?, avgPalveluaikaElektroniikka= ?, avgPalveluaikaPalavaJate= ?, avgPalveluaikaPalamatonJate= ?, avgJatteenmaara = ? WHERE tuloksetID=?";
        connection = avaaYhteysTietokantaan();
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
            //Keskimääräinen Jonon Pituus, Saapuminen,Elektroniikka,Palava Jäte,Palamaton Jäte
            double[] avgJPS = suureet.getKeskmJononpituudet();
            for (double d : avgJPS) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Keskimääräinen läpimenoaika, Saapuminen, Elektroniikka,  Palava Jäte, Palamaton Jäte
            double[] avgLapimeno = suureet.getKeskmLapimenoajat();
            for (double d : avgLapimeno) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Palvelupisteiden Kayttoasteet, Saapuminen, Elektroniikka, Palava Jäte, Palamaton Jäte
            double[] avgKayttoasteet = suureet.getKayttoasteet();
            for (double d : avgKayttoasteet) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Palvelupisteiden Palveluajat, Saapuminen, Elektroniikka, Palava Jäte, Palamaton Jäte
            double[] palveluajat = suureet.getKeskmPalveluajat();
            for (double d : palveluajat) {
                ps.setDouble(indexTulokset_2++, d);
            }
            //Jätemäärän keskiarvo
            ps.setDouble(indexTulokset_2++, suureet.getKeskmJatteenmaara());
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
        haeKaikkiTiedot();
        return simulaatioDataObjekti;
    }

    @Override
    public synchronized void poistaTaulu() throws SQLException {
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(SQL_DROPTABLE)) {
            ps.executeUpdate();
        }
    }

    @Override
    public synchronized boolean poistaTiettyTulos(int ID) throws SQLException {
        String query = "DELETE FROM simulaatio WHERE simulaatio.simulaatioID="+Integer.toString(ID);
        connection = avaaYhteysTietokantaan();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            int poistettu = ps.executeUpdate();
            if(poistettu == 1){
                return true;
            }
        }
        return false;
    }
}

