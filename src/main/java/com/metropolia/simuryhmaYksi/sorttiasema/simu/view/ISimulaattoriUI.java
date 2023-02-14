package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

public interface ISimulaattoriUI {
    public int[] getVaihteluvali();
    public double getAika();
    public long getViive();
    public double getAsiakasJäteMin();
    public double getAsiakasJäteMax();
    public double getAsiakasKgPerSekunti();
    public int getJatelajiProsenttiELEKTRO();
    public int getJatelajiProsenttiPALAVA();
    public int getJatelajiProsenttiPALAMATON();
    public int getRuuhkaAika();
    public int getStrategiaTapahtumat();
    public void setLoppuaika();
    public IVisualisointi getVisualisointi();
}
