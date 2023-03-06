package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface ISimulaattoriUI {

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA
     void showTulokset(ArrayList<SimulaatioData> dataColumn);
    void poistaData(int ID) throws SQLException;

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA


    double getAika();
     long getViive();
     void setLoppuaika();
    //Asiakaan Jäte MIN ja MAX määrä getteri.
     int[] getVaihteluvali();
     int[] getJateLaijenProsentit();
    //Asiakaan kesto 1 kilon heittämisessä getter.
     double getAsiakasKgPerSekunti();

    //Simulaattorin Mitä jätettä tuotaan "prosentti int"
     int[] getJatelajiProsentit();

    //Simulaattorin Aktiivisuus input arvot.
    int getRuuhkaAika();
    int getStrategiaTapahtumat();

    //Simulaatorin pois heitetty Jätteen lable elementin arvot.
     int getElektro_JateCounter();
     int getPalavaJateCounter();
     int getPalamatonJateCounter();

     boolean getAjeetaankoLoppuun();
     STRATEGIA_FXML_CONTROLLER getStrategiaController();



     IVisualisointi getVisualisointi();
     void setEJateJonossa(int pituus);
     void setPTJateJonossa(int pituus);
     void setPJateJonossa(int pituus);
     void setSAAPUMINENJonossa(int pituus);
     void setAnimaationViive(int viive);

     // Käynnistä button teksti
     public void setAloitaButtonText();
}
