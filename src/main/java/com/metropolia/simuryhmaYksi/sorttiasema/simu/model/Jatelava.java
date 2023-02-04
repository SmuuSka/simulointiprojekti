package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
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
        // Palveltavan asiakkaan jäte
        Jate j = palveltava.getJatteet().removeFirst();
        System.out.println("LAJITELTU JATE: " + j);
        System.out.println(palveltava.getJatteet().toString());
        maara += j.getPaino();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}

    public double getMaara(){
        return maara;
    }

    public Jatelaji getLavanTyyppi() {
        return lavanTyyppi;
    }

    public String toString(){
        return "LAVA: "+getLavanTyyppi()+", JATTEENMAARA LAVALLA: "+getMaara(); 
    }
}
