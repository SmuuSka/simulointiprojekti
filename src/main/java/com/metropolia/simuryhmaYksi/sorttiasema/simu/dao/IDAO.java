package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import java.util.HashMap;

public interface IDAO {

    public abstract void luoData(double aika, int[] vaihteluvali, int[] jateprosentit);
    public abstract void paivitaData(double jatteidenKokonaismaara);
    public abstract HashMap<Integer, SimulaatioData> haeData();
    public abstract void poistaTaulu();
}
