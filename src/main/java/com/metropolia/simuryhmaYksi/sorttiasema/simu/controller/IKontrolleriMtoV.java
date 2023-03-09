package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public interface IKontrolleriMtoV {
    void tallennaTulokset(Laskenta suureet) throws SQLException;
    IVisualisointi getVisualisointi();
    void setEJononPituus(int pituus);
    void setPTJononPituus(int size);
    void setPJononPituus(int size);
     void setSAAPUMISJononPituus(int size);
    double getAktiivisuus();
    double getPurkuNopeus();
}
