package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.DAO;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.IDAO;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.IMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Asiakas;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.OmaMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Palvelupiste;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {
    private ISimulaattoriUI ui;
    private IMoottori moottori;
    private IDAO tietokanta;
    private IVisualisointi nayttoVisual;

    public Kontrolleri(ISimulaattoriUI ui){
        this.ui = ui;
    }

    @Override
    public void kaynnistaSimulointi(){
        //Luodaan Gui:n aloitakäskyn perusteella uusi moottori ja tietokantaolio
        moottori = new OmaMoottori(this);
        tietokanta = new DAO();
        //tietokanta.poistaTaulu();
        //Asetetaan simulointiaika ja viive moottorille
        //Tallennetaan simulointiparametrit tietokantaan
        System.out.println("Asetetaan simulointiaika: " + ui.getAika());
        moottori.setSimulointiaika(ui.getAika());
        moottori.setViive(ui.getViive());
        tietokanta.luoData(ui.getAika(), ui.getVaihteluvali(), ui.getJateLaijenProsentit());
        System.out.println("Uista tuleva vaihteluväli: " + Arrays.toString(ui.getVaihteluvali()));

        Asiakas.setJatemaara(ui.getVaihteluvali());
        Asiakas.setTJATELAJI(ui.getJateLaijenProsentit());
        System.out.println("Uista tuleva vaihteluväli: " + Arrays.toString(ui.getVaihteluvali()));

        //Käynnistetään moottori
        ((Thread)moottori).start();

        //Täällä on tietokannasta tuleva data

    }

    @Override
    public void nopeuta() {
        // placeholder
        long viive = 500;
        // Vähentää viivettä 0.5s
        if (moottori.getViive() - viive >= 0){
            moottori.setViive(moottori.getViive() - viive);
            ui.setAnimaationViive((int)(moottori.getViive() - viive));
        }
    }

    @Override
    public void hidasta() {
        // Lisää viivettä 0.5s
        moottori.setViive(moottori.getViive() + 500);
        ui.setAnimaationViive((int)(moottori.getViive() + 500));
    }

    @Override
    public IVisualisointi getVisualisointi() {
        return nayttoVisual;
    }
    @Override
    public void lopetaSimulointi() {

    }

    @Override
    public void naytaLoppuaika(double aika) {

    }

    @Override
    public void tallennaTulokset(double jatteidenKokonaismaara, int asiakkaidenMaara, Palvelupiste[] palvelupisteet) {
        tietokanta.paivitaData(jatteidenKokonaismaara);
        ui.showTulokset(tietokanta.haeData());
        System.out.println("Poistettu ID 1: " + tietokanta.poistaTiettyTulos(2));
    }


    @Override
    public void visualisoiAsiakas() {

    }

    @Override
    public IVisualisointi setVisualisointi(IVisualisointi visualisointi) {
        this.nayttoVisual = visualisointi;
        return visualisointi;
    }


    public void setEJononPituus(int pituus){
        ui.setEJateJonossa(pituus);
    }

    @Override
    public void setPTJononPituus(int pituus) {
        ui.setPTJateJonossa(pituus);
    }

    @Override
    public void setPJononPituus(int pituus) {
        ui.setPJateJonossa(pituus);
    }
    @Override
    public void setSAAPUMISJononPituus(int pituus) {
        ui.setSAAPUMINENJonossa(pituus);
    }

   public void setAnimaationViive(int viive){
        ui.setAnimaationViive(viive);
   }
}
