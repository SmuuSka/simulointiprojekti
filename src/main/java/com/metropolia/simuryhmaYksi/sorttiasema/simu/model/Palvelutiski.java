package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

public class Palvelutiski extends Palvelupiste {
    
    public Palvelutiski(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
        super(generator, tapahtumalista);
    }

    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		varattu = true;
		double palveluaika = generator.sample();
		TapahtumanTyyppi seuraavaTapahtuma = jono.peek().getJatteet().size() == 0 ? TapahtumanTyyppi.POISTUMINEN : seuraavaPalvelu( jono.peek().getJatteet());
		tapahtumalista.lisaa(new Tapahtuma(seuraavaTapahtuma,Kello.getInstance().getAika()+palveluaika, palvelupisteID));
	}
}
