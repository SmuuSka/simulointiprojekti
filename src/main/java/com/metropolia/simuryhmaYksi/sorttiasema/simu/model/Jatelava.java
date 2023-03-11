package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public class Jatelava extends Palvelupiste {
    /**
     * Jätelavalle olevan jätteen määrä
     */
    private double maara;
    /**
     * Jätelaji mitä jätelava vastaanottaa
     */
    private final Jatelaji lavanTyyppi;
    /**
     * 
     * @param generator
     * @param tapahtumalista
     * @param lavanTyyppi
     */
    public Jatelava(ContinuousGenerator generator, Tapahtumalista tapahtumalista, Jatelaji lavanTyyppi) {
        super(generator, tapahtumalista);
        this.lavanTyyppi = lavanTyyppi;
    }

    /**
     * Jätelavojen palvelu.
     * 
     * Logiikkaa:
     * Kutsuu yliluokan aloitaPalvelu metodia, missä kaikille palvelupisteille on yhteiset osuudet palvelusta.
     * Ottaa käsittelyyn jonon ensimmäisen asiakkaan, poistaa siltä tuomansa jätteen ja arpoo jätteen määrän sekä normaalijakauman perusteella 
     * palveluajan asiakkaalle.
     * Asetetaan palvelupistekohtaiset suorituskykysuureet, kuten aktiivi- ja kokokonaisoleskeluaika.
     * Lisätään poistetun jätteenmäärä palvelupisteen kokonaisjätemäärään.
     * Lopuksi katsotaan asiakkaalle vielä seuraava tapahtuma hänen "jätelistansa" avulla,
     * ja tapahtumalistalle lisätään poistumistapahtuma ajan hetkelle nykyaika + palveluaika.
     */
    @Override
    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
        super.aloitaPalvelu();
 

        Asiakas palveltava = jono.peek();

        // Asiakkaan kantamat jätteet
        LinkedList<Jate> jatteet = palveltava.getJatteet();

        // Jätelavalle jätetty jäte
        Jate poistettuJate = jatteet.removeFirst();
        
        double palveluaika = Math.abs(generator.sample()) * poistettuJate.getPaino();

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

    /**
     * 
     * @return maara
     */
    public double getMaara(){
        return maara;
    }

    /**
     * 
     * @return Jatelaji jota kyseinen jätelava.
     */
    public Jatelaji getLavanTyyppi() {
        return lavanTyyppi;
    }

    /**
     * @return merkkijonoesitys oliosta.
     */
    public String toString(){
        return "Lava: "+getLavanTyyppi()+", Jätteen määrä lavalla: "+getMaara()+" kg"; 
    }
}
