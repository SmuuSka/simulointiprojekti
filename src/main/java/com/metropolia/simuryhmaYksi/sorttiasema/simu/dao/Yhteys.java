package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Yhteys {

    private static final ResourceBundle rb = ResourceBundle.getBundle("System");
    private static final String url1 = rb.getString("url1") + rb.getString("username1") + rb.getString("password1");
    private static Connection yhteys = null;

    private Yhteys(){}

    public static Connection getYhteys(){
        if (yhteys == null){
            try {
                yhteys = DriverManager.getConnection(url1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return yhteys;
    }
}
