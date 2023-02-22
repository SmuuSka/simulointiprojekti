package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

public class SimulaatioData{
    private final double aika, jatteidenKokonaismaara;
    private final int vmin, vmax, jateTE, jateTPJ, jateTPNJ;

    public SimulaatioData(double aika, int vmin, int vmax, int jateTE, int jateTPJ, int jateTPNJ, double jatteidenKokonaismaara){
        this.aika = aika;
        this.vmin = vmin;
        this.vmax = vmax;
        this.jateTE = jateTE;
        this.jateTPJ = jateTPJ;
        this.jateTPNJ = jateTPNJ;
        this.jatteidenKokonaismaara = jatteidenKokonaismaara;
    }

    public double getAika() {
        return aika;
    }

    public double getJatteidenKokonaismaara() {
        return jatteidenKokonaismaara;
    }

    public int getVmin() {
        return vmin;
    }

    public int getVmax() {
        return vmax;
    }

    public int getJateTE() {
        return jateTE;
    }

    public int getJateTPJ() {
        return jateTPJ;
    }

    public int getJateTPNJ() {
        return jateTPNJ;
    }

    @Override
    public String toString() {
        return "SimulaatioData{" +
                "aika=" + aika +
                ", jatteidenKokonaismaara=" + jatteidenKokonaismaara +
                ", vmin=" + vmin +
                ", vmax=" + vmax +
                ", jateTE=" + jateTE +
                ", jateTPJ=" + jateTPJ +
                ", jateTPNJ=" + jateTPNJ +
                '}';
    }
}
