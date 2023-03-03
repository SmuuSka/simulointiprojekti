package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Palvelupiste;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

public interface IKontrolleriMtoV {
    public void naytaLoppuaika(double aika);
    public void tallennaTulokset(Laskenta suureet) throws SQLException;
    public void visualisoiAsiakas();
    public IVisualisointi getVisualisointi();
    public void setEJononPituus(int pituus);
    public void setPTJononPituus(int size);
    public void setPJononPituus(int size);
    public void setSAAPUMISJononPituus(int size);
}
