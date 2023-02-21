package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

public class SimulaatioData{
    private final double aika;
    private final int vmin, vmax;

    public SimulaatioData(double aika, int vmin, int vmax){
        this.aika = aika;
        this.vmin = vmin;
        this.vmax = vmax;
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

    @Override
    public String toString() {
        return "SimulaatioData{" +
                "aika=" + aika +
                ", vmin=" + vmin +
                ", vmax=" + vmax +
                '}';
    }
}
