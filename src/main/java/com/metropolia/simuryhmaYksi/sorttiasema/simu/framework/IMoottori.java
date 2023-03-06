package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;

public interface IMoottori {
    public void setSimulointiaika(double aika);
    public void setViive(long viive);
    public long getViive();
    public void viive();
    public void setAjetaanTyhjaksi(boolean ajetaanTyhjaksi);
    public void lopetasimulaatio();
    public boolean getAjetaanTyhjaksi();
}
