package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

public class SimulaatioData{
    private final double aika;
    private final int vmin, vmax, jateTE, jateTPJ, jateTPNJ;

    public SimulaatioData(double aika, int vmin, int vmax, int jateTE, int jateTPJ, int jateTPNJ){
        this.aika = aika;
        this.vmin = vmin;
        this.vmax = vmax;
        this.jateTE = jateTE;
        this.jateTPJ = jateTPJ;
        this.jateTPNJ = jateTPNJ;
    }

    public double getAika() {
        return aika;
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
                ", vmin=" + vmin +
                ", vmax=" + vmax +
                ", jateTE=" + jateTE +
                ", jateTPJ=" + jateTPJ +
                ", jateTPNJ=" + jateTPNJ +
                '}';
    }
}
