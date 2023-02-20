package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

public interface IDAO {

    public abstract void luoData(Double aika, int[] vaihteluvali);
    public abstract void paivitaData();
    public abstract int haeData();
    public abstract void poistaTaulu();
}
