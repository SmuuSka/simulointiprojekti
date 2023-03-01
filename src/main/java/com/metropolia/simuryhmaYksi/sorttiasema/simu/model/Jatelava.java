package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;


public class Jatelava extends Palvelupiste {

    private double maara;
    private final Jatelaji lavanTyyppi;
    public static int saapuneet = 0;
    public Jatelava(ContinuousGenerator generator, Tapahtumalista tapahtumalista, Jatelaji lavanTyyppi) {
        super(generator, tapahtumalista);
        this.lavanTyyppi = lavanTyyppi;
    }

    @Override
    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
        super.aloitaPalvelu();
        saapuneet++;

        Asiakas palveltava = jono.peek();

        // Asiakkaan kantamat jätteet
        LinkedList<Jate> jatteet = palveltava.getJatteet();

        // Jätelavalle jätetty jäte
        Jate poistettuJate = jatteet.removeFirst();

        double palveluaika = generator.sample() * poistettuJate.getPaino() * 0.1;

        // Tulostuksia
        System.out.println("Poistettu jäte: " + poistettuJate);
        System.out.println("Asiakkaan jätteet nyt: " + jatteet.toString());

        // Jätemäärän lisäys
        maara += poistettuJate.getPaino();
        TapahtumanTyyppi seuraavaTapahtuma = jatteet.size() == 0 ? TapahtumanTyyppi.POISTUMINEN : seuraavaPalvelu(jatteet);
		tapahtumalista.lisaa(new Tapahtuma(seuraavaTapahtuma,Kello.getInstance().getAika()+palveluaika, palvelupisteID));
	}

    public double getMaara(){
        return maara;
    }

    public Jatelaji getLavanTyyppi() {
        return lavanTyyppi;
    }

    public String toString(){
        return "Lava: "+getLavanTyyppi()+", Jätteen määrä lavalla: "+getMaara()+" kg"; 
    }
}
