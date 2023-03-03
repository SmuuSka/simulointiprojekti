package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDAO {
     void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit, int viive, double purkunopeus) throws SQLException;
     void paivitaData(Laskenta suureet) throws SQLException;
     ArrayList<SimulaatioData> simulaatioColumnData() throws SQLException;
     void poistaTaulu() throws SQLException;
     boolean poistaTiettyTulos(int ID) throws SQLException;
}
