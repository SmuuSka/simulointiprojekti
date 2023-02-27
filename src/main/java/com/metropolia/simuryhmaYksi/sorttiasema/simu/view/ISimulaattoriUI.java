package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface ISimulaattoriUI {

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA
    void showTulokset(ArrayList<SimulaatioData> datatulokset);

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



    public IVisualisointi getVisualisointi();
    public void setEJateJonossa(int pituus);
    public void setPTJateJonossa(int pituus);
    public void setPJateJonossa(int pituus);
    public void setSAAPUMINENJonossa(int pituus);
}
