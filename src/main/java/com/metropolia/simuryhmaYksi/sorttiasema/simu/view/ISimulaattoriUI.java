package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

public interface ISimulaattoriUI {

    public double getAika();
    public long getViive();
    public void setLoppuaika();
    public int[] getVaihteluvali();
    //Asiakaan Jäte MIN ja MAX määrä getterit.
    public double getAsiakasJäteMin();
    public double getAsiakasJäteMax();

    //Asiakaan kesto 1 kilon heittämisessä getter.
    public double getAsiakasKgPerSekunti();

    //Simulaattorin Mitä jätettä tuotaan "prosentti int"
    public int getJatelajiProsenttiELEKTRO();
    public int getJatelajiProsenttiPALAVA();
    public int getJatelajiProsenttiPALAMATON();

    //Simulaattorin Aktiivisuus input arvot.
    public int getRuuhkaAika();
    public int getStrategiaTapahtumat();

    //Simulaatorin pois heitetty Jätteen lable elementin arvot.
    public int getElektro_JateCounter();
    public int getPalavaJateCounter();
    public int getPalamatonJateCounter();



    public IVisualisointi getVisualisointi();
}
