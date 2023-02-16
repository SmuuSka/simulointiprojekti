package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.IMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Asiakas;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.OmaMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;

import java.util.Arrays;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {
    private ISimulaattoriUI ui;
    private IMoottori moottori;


    public Kontrolleri(ISimulaattoriUI ui){
        this.ui = ui;
    }

    @Override
    public void kaynnistaSimulointi(){
        //Luodaan Gui käskyn perusteella uusi moottori ja asetetaan simulointiaika
        moottori = new OmaMoottori(this);
        System.out.println("Asetetaan simulointiaika: " + ui.getAika());
        moottori.setSimulointiaika(ui.getAika());

        //Korjaa tämä funktio
        //Asiakas.setJatemaara(ui.getVaihteluvali());
        System.out.println("Uista tuleva vaihteluväli: " + Arrays.toString(ui.getVaihteluvali()));

        Asiakas.setJatemaara(ui.getVaihteluvali());
        Asiakas.setTJATELAJI(new int[] {80, 10, 10});
        System.out.println("Uista tuleva vaihteluväli: " + Arrays.toString(ui.getVaihteluvali()));

        //Käynnistetään moottori
        ((Thread)moottori).start();
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
