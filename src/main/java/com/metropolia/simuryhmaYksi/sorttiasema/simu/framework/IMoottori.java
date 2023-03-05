package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;

public interface IMoottori {
    void setSimulointiaika(double aika);
    void setViive(long viive);
     long getViive();
     void viive();
     void setAjetaanTyhjaksi(boolean ajetaanTyhjaksi);
     boolean getAjetaanTyhjaksi();
}
