package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO implements IDAO {
    private static final String SQL_DROPTABLE = "DROP TABLE SIMULAATIO";
    private static final String SQL_INIT = "CREATE TABLE IF NOT EXISTS SIMULAATIO(\n" +
            "    SIMULAATIOID INT(10) NOT NULL AUTO_INCREMENT,\n" +
            "    AIKA DOUBLE DEFAULT NULL,\n" +
            "    VAIHTELUVALI_MIN INT DEFAULT NULL,\n" +
            "    VAIHTELUVALI_MAX INT DEFAULT NULL,\n" +
            "    PRIMARY KEY (SIMULAATIOID))";
    private static final String SQL_INSERT = "INSERT INTO SIMULAATIO (AIKA, VAIHTELUVALI_MIN, VAIHTELUVALI_MAX) VALUES (?,?,?)";
    private static final String SQL_SELECT = "SELECT SIMULAATIOID FROM SIMULAATIO";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static int ID;

    private static int setID(){
        int simulaatioID = 0;
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
    private static Connection avaaYhteysTietokantaan(){
        try{
            connection = (Connection) DriverManager.getConnection("");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void luoData(Double aika, int[] vaihteluvali) {
        try {

            System.out.println("Vaihe 1: Avataan yhteys tietokantaan");
            connection = avaaYhteysTietokantaan();
            //Alustetaan taulu, jos ei ole ennestään
            preparedStatement = connection.prepareStatement(SQL_INIT);
            preparedStatement.executeUpdate();
            System.out.println("Vaihe 2: Avattu yhteys, kirjoitetaan tiedot tietokantaan");
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setDouble(1, aika);
            preparedStatement.setInt(2, vaihteluvali[0]);
            preparedStatement.setInt(3, vaihteluvali[1]);
            int rivi = preparedStatement.executeUpdate();
            ID = setID();
            System.out.println("Vaihe 3: Tiedot kirjattu tietokantaan\n rivi: " + rivi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Vaihe 4: Suljetaan tietokantayhteys");
            try { resultSet.close(); } catch (Exception e) { /* Ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* Ignored */ }
            try { connection.close(); } catch (Exception e) { /* Ignored */ }
            System.out.println("Vaihe 5: Tietokantayhteys suljettu");
        }
    }
        @Override
    public void paivitaData() {

    }

    @Override
    public int haeData() {
        return ID;
    }

    @Override
    public void poistaTaulu() {
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
