package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

/**
 * Palvelutiski on Palvelupisteen alaluokka,
 * jossa luodaan uusia saapumistapahtumia, ja aloitetaan asiakkaan palvelu.
 */

public class Palvelutiski extends Palvelupiste {

	/**
	 *
	 * @param generator Satunnaislukugeneraattori
	 * @param tapahtumalista Asikkaiden tapahtumalista
	 */
	public Palvelutiski(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
        super(generator, tapahtumalista);
    }
	/**
	 * Palvelutiskin palvelu
	 * Logiikkaa:
	 * Käsittelee ensimmäisen asiakkaan jonosta.
	 * Generoi normaalijakauman mukaisen palveluajan.
	 * Lisää palvelupistekohtaiset suorituskykysuureet.
	 * Luo seuraavan tapahtuman perustuen asiakkaan jätteisiin.
	 * Lisää tapahtuman tapahtumalistaan.
	 */
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
