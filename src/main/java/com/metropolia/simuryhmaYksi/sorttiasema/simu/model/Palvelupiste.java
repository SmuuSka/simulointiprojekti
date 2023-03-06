package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

import java.util.LinkedList;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	protected LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	protected ContinuousGenerator generator;
	protected Tapahtumalista tapahtumalista;
	protected int palvelupisteID = 0;
	protected static int i = 0; 	
	protected boolean varattu = false;
	protected double aktiiviaika = 0;
	protected long kokonaisoleskeluaika = 0;
	protected int palveltujenLkm = 0;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		palvelupisteID = i++;
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		palveltujenLkm++;
		varattu = true;
	}

	public TapahtumanTyyppi seuraavaPalvelu(LinkedList<Jate> jatteet){ // Asiakkaan jätteistä määräytyvä seuraava tapahtuma 

        TapahtumanTyyppi seuraavaTapahtuma = TapahtumanTyyppi.POISTUMINEN;

        switch (jatteet.getFirst().getJatelaji()){
            case ELEKTRONIIKKA:
                seuraavaTapahtuma = TapahtumanTyyppi.ELEKTRONIIKKA_SAAPUMINEN;
                break;
            case PALAVAJATE:
                seuraavaTapahtuma = TapahtumanTyyppi.PALAVAJATE_SAAPUMINEN;
                break;
            case PALAMATONAJATE:
                seuraavaTapahtuma = TapahtumanTyyppi.PALAMATONJATE_SAAPUMINEN;
                break;
        }
        return seuraavaTapahtuma;
    }


	public boolean onVarattu(){
		return varattu;
	}

	public int getPalveltujenLkm(){
		return palveltujenLkm;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}

	public long getKokonaisoleskeluaika(){
        return kokonaisoleskeluaika;
    }

	public double getAktiiviaika(){
		return aktiiviaika;
	}

	public LinkedList<Asiakas> getJono(){
		return jono;
	}

	public void setI(int j){
		i = 0;
	}

}
