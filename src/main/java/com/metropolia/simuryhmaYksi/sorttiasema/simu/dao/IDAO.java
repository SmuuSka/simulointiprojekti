package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Palvelupiste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDAO {
    public abstract void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit) throws SQLException;
    public abstract void paivitaData(Laskenta suureet) throws SQLException;
    public abstract ArrayList<SimulaatioData> haeData() throws SQLException;
    public abstract void poistaTaulu() throws SQLException;
    public abstract boolean poistaTiettyTulos(int ID);
}
