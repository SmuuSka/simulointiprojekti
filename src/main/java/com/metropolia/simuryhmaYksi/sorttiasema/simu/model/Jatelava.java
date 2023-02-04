package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

public class Jatelava extends Palvelupiste {

    private double maara;
    private final Jatelaji lavanTyyppi;
    public static int saapuneet = 0;

    public Jatelava(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, Jatelaji lavanTyyppi) {
        super(generator, tapahtumalista, tyyppi);
        this.lavanTyyppi = lavanTyyppi;
    }

    @Override
    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
        saapuneet++;
        Asiakas palveltava = jono.peek();
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + palveltava.getId());
		varattu = true;
		double palveluaika = generator.sample();
        // Asiakkaan kantamat jätteet
        LinkedList<Jate> jatteet = palveltava.getJatteet();
        // Jätelavalle jätetty jäte
        Jate poistettuJate = jatteet.removeFirst();
        // Tulostuksia
        System.out.println("Poistettu jäte: " + poistettuJate);
        System.out.println("Asiakkaan jätteet nyt: " + jatteet.toString());
        // Jätemäärän lisäys
        maara += poistettuJate.getPaino();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
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
