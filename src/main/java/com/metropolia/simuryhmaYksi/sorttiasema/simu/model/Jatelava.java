package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;



public class Jatelava extends Palvelupiste {

    private double maara;
    private final Jatelaji lavanTyyppi;

    public Jatelava(ContinuousGenerator generator, Tapahtumalista tapahtumalista, Jatelaji lavanTyyppi) {
        super(generator, tapahtumalista);
        this.lavanTyyppi = lavanTyyppi;
    }

    @Override
    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
        super.aloitaPalvelu();
 

        Asiakas palveltava = jono.peek();

        // Asiakkaan kantamat jätteet
        LinkedList<Jate> jatteet = palveltava.getJatteet();

        // Jätelavalle jätetty jäte
        Jate poistettuJate = jatteet.removeFirst();
        
        double palveluaika = generator.sample() * poistettuJate.getPaino() * 0.1;

        double poistumisaika = Kello.getInstance().getAika()+palveluaika;
        palveltava.setPoistumisaika(poistumisaika);
        aktiiviaika += palveluaika;

		kokonaisoleskeluaika += (poistumisaika-palveltava.getSaapumisaika());

        // Tulostuksia
        System.out.println("Poistettu jäte: " + poistettuJate);
        System.out.println("Asiakkaan jätteet nyt: " + jatteet.toString());

        // Jätemäärän lisäys
        maara += poistettuJate.getPaino();
        TapahtumanTyyppi seuraavaTapahtuma = jatteet.size() == 0 ? TapahtumanTyyppi.POISTUMINEN : seuraavaPalvelu(jatteet);
		tapahtumalista.lisaa(new Tapahtuma(seuraavaTapahtuma, poistumisaika, palvelupisteID));
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
