package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;

import java.util.ArrayList;

/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface ISimulaattoriUI {

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA
    public void showTulokset(ArrayList<SimulaatioData> dataColumn);

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA


    public double getAika();
    public long getViive();
    public void setLoppuaika();
    //Asiakaan Jäte MIN ja MAX määrä getteri.
    public int[] getVaihteluvali();
    public int[] getJateLaijenProsentit();
    //Asiakaan kesto 1 kilon heittämisessä getter.
    public double getAsiakasKgPerSekunti();

    //Simulaattorin Mitä jätettä tuotaan "prosentti int"
    public int[] getJatelajiProsentit();

    //Simulaattorin Aktiivisuus input arvot.
    public int getRuuhkaAika();
    public int getStrategiaTapahtumat();

    //Simulaatorin pois heitetty Jätteen lable elementin arvot.
    public int getElektro_JateCounter();
    public int getPalavaJateCounter();
    public int getPalamatonJateCounter();

    public boolean getAjeetaankoLoppuun();
    public STRATEGIA_FXML_CONTROLLER getStrategiaController();



    public IVisualisointi getVisualisointi();
    public void setEJateJonossa(int pituus);
    public void setPTJateJonossa(int pituus);
    public void setPJateJonossa(int pituus);
    public void setSAAPUMINENJonossa(int pituus);
    public void setAnimaationViive(int viive);
}
