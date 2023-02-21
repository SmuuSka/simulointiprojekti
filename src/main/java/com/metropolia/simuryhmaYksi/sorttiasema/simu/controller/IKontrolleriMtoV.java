package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

public interface IKontrolleriMtoV {
    public void naytaLoppuaika(double aika);
    public void visualisoiAsiakas();
    public IVisualisointi getVisualisointi();

    public void setEJononPituus(int pituus);
    public void setPTJononPituus(int size);
    public void setPJononPituus(int size);
}
