package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

public interface IKontrolleriMtoV {
     void naytaLoppuaika(double aika);
    void tallennaTulokset(Laskenta suureet) throws SQLException;

    void visualisoiAsiakas();
    IVisualisointi getVisualisointi();
    void setEJononPituus(int pituus);
    void setPTJononPituus(int size);
    void setPJononPituus(int size);
     void setSAAPUMISJononPituus(int size);
}
