package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import java.util.HashMap;

public interface IDAO {

    public abstract void luoData(Double aika, int[] vaihteluvali);
    public abstract void paivitaData();
    public abstract HashMap<Integer, SimulaatioData> haeData();
    public abstract void poistaTaulu();
}
