package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;

public class Palvelutiski extends Palvelupiste {
    
    public Palvelutiski(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
        super(generator, tapahtumalista);
    }

    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		super.aloitaPalvelu();
		Asiakas a = jono.peek();
		double palveluaika = generator.sample();
		double poistumisaika = Kello.getInstance().getAika()+palveluaika;
		aktiiviaika += palveluaika;
		a.setPoistumisaika(poistumisaika);
	
		kokonaisoleskeluaika += (a.getPoistumisaika()-a.getSaapumisaika());
		TapahtumanTyyppi seuraavaTapahtuma = a.getJatteet().size() == 0 ? TapahtumanTyyppi.POISTUMINEN : seuraavaPalvelu(a.getJatteet());
		tapahtumalista.lisaa(new Tapahtuma(seuraavaTapahtuma, poistumisaika, palvelupisteID));
	}
}
