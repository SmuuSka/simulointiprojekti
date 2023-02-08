package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.IMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.OmaMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV{
    private ISimulaattoriUI ui;
    private IMoottori moottori;
    public Kontrolleri(ISimulaattoriUI ui){
        this.ui = ui;
    }

    public void kaynnistaSimulointi(){
        moottori = new OmaMoottori(this);
    }

    @Override
    public void nopeuta() {

    }

    @Override
    public void hidasta() {

    }

    @Override
    public void naytaLoppuaika(double aika) {

    }

    @Override
    public void visualisoiAsiakas() {

    }
}
