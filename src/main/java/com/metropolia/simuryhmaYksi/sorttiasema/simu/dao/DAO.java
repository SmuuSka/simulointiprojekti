package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DAO implements IDAO {
    private static final ResourceBundle rb = ResourceBundle.getBundle("System");
    private static final String url = rb.getString("url") +rb.getString("username") +rb.getString("password");
    private static final String SQL_DROPTABLE = "DROP TABLE SIMULAATIO";
    private static final String SQL_INIT = "CREATE TABLE IF NOT EXISTS SIMULAATIO(\n" +
            "    SIMULAATIOID INT(10) NOT NULL AUTO_INCREMENT,\n" +
            "    AIKA DOUBLE DEFAULT NULL,\n" +
            "    VAIHTELUVALI_MIN INT DEFAULT NULL,\n" +
            "    VAIHTELUVALI_MAX INT DEFAULT NULL,\n" +
            "    JATTEEN_TODENNAKOISYYS_ELEKTRONIIKKA INT DEFAULT NULL,\n" +
            "    JATTEEN_TODENNAKOISYYS_PALAVA_JATE INT DEFAULT NULL,\n" +
            "    JATTEEN_TODENNAKOISYYS_PALAMATON_JATE INT DEFAULT NULL,\n" +
            "    JATTEIDENKOKONAISMAARA DOUBLE DEFAULT NULL, \n" +
            "    PRIMARY KEY (SIMULAATIOID))";
    private static final String SQL_INSERT_SIMU_PARAMETRIT = "INSERT INTO SIMULAATIO (AIKA," +
                                                                    " VAIHTELUVALI_MIN, " +
                                                                    " VAIHTELUVALI_MAX," +
                                                                    " JATTEEN_TODENNAKOISYYS_ELEKTRONIIKKA, " +
                                                                    " JATTEEN_TODENNAKOISYYS_PALAVA_JATE," +
                                                                    " JATTEEN_TODENNAKOISYYS_PALAMATON_JATE) VALUES (?,?,?,?,?,?)";

    private static final String SQL_UPDATE_TULOKSET = "UPDATE SIMULAATIO SET JATTEIDENKOKONAISMAARA = ? WHERE SIMULAATIOID = ?";
    private static final String SQL_SELECT = "SELECT SIMULAATIOID FROM SIMULAATIO";
    private static final String SQL_SELECT_ALL = "SELECT * FROM SIMULAATIO";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static int simulaatioID;
    private static final HashMap<Integer,SimulaatioData> simulaatioData = new HashMap<>();


    public static void main(String[] args){

        haeKaikkiTiedot();
        for (Map.Entry<Integer, SimulaatioData> kvp : simulaatioData.entrySet()){
            System.out.println("Key: " + kvp.getKey() +"\nValue: " + kvp.getValue());
        }
    }

    private synchronized static void haeKaikkiTiedot(){
        try{
            connection = avaaYhteysTietokantaan();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                simulaatioData.put(resultSet.getInt(1),new SimulaatioData(
                        resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7),
                        resultSet.getDouble(8)));
//                System.out.println("ID: " + resultSet.getInt(1) + ", AIKA: " + resultSet.getInt(2)+ ","
//                        + " VMIN: " + resultSet.getInt(3)+", VMAX: " + resultSet.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private synchronized static int setID(){
        try{
            connection = avaaYhteysTietokantaan();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                simulaatioID = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return simulaatioID;
    }
    private synchronized static Connection avaaYhteysTietokantaan(){
        try{
            connection = (Connection) DriverManager.getConnection(url);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public synchronized void luoData(double aika, int[] vaihteluvali, int[] jateprosentit) {
        try {
            //Vaihe 1. Yritetään avata yhteys tietokantaan.
            connection = avaaYhteysTietokantaan();
            // Vaihe 2. Alustetaan taulu, jos ei ole ennestään olemassa.
            //Muuten siirrytään vaiheeseen 3.
            preparedStatement = connection.prepareStatement(SQL_INIT);
            preparedStatement.executeUpdate();
            //Vaihe 3. Luodaan taulu tietokantaan.
            preparedStatement = connection.prepareStatement(SQL_INSERT_SIMU_PARAMETRIT);
            //Simulaatioaika
            preparedStatement.setDouble(1, aika);
            //Jätemäärä MIN
            preparedStatement.setInt(2, vaihteluvali[0]);
            //Jätemäärä MAX
            preparedStatement.setInt(3, vaihteluvali[1]);
            //Elektroniikan prosentit
            preparedStatement.setInt(4,jateprosentit[0]);
            //Palavan jätteen prosentit
            preparedStatement.setInt(5,jateprosentit[1]);
            //Palamattoman jätteen prosentit
            preparedStatement.setInt(6,jateprosentit[2]);
            //Vaihe 4. Suoritetaan tietokantakomento
            preparedStatement.executeUpdate();
            //Haetaan simulaation ID
            simulaatioID = setID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //Suljetaan tietokantayhteys
            try { resultSet.close(); } catch (Exception e) { /* Ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* Ignored */ }
            try { connection.close(); } catch (Exception e) { /* Ignored */ }
            System.out.println("Tietokantayhteys suljettu: " + connection.isClosed());
        }
    }
    @Override
    public synchronized void paivitaData(double jatteidenKokonaismaara) {
        try {
            connection = avaaYhteysTietokantaan();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TULOKSET);
            preparedStatement.setDouble(1, jatteidenKokonaismaara);
            preparedStatement.setInt(2, simulaatioID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Suljetaan tietokantayhteys
            try {
                resultSet.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                preparedStatement.close();
            } catch (Exception e) { /* Ignored */ }
            try {
                connection.close();
            } catch (Exception e) { /* Ignored */ }
            System.out.println("Tietokantayhteys suljettu: " + connection.isClosed());
        }
    }

    @Override
    public synchronized HashMap<Integer, SimulaatioData> haeData() {
        return simulaatioData;
    }


    @Override
    public synchronized void poistaTaulu() {
        try {
            connection = avaaYhteysTietokantaan();
            preparedStatement = connection.prepareStatement(SQL_DROPTABLE);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try { resultSet.close(); } catch (Exception e) { /* Ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* Ignored */ }
            try { connection.close(); } catch (Exception e) { /* Ignored */ }
            System.out.println("Vaihe 5: Tietokantayhteys suljettu");
        }
    }
}
