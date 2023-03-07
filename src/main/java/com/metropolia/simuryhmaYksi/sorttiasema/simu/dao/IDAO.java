package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public interface IDAO {
     void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit, double viive, double purkunopeus, String aktiivisuus) throws SQLException;
     void paivitaData(Laskenta suureet) throws SQLException;
     ArrayList<SimulaatioData> simulaatioColumnData() throws SQLException;
     void poistaTaulu() throws SQLException;
     boolean poistaTiettyTulos(int ID) throws SQLException;
     void suljeYhteys();
}
