package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtumalista;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

import java.util.LinkedList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 *
 * Palvelupiste on pääluokka Palevutiski ja Jätelava-luokalle
 */

public class Palvelupiste {

	/**
	 * Asiakas-tyyppinen lista asiakkaiden jonotukseen
	 */
	protected LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus

	/**
	 * Satunnaislukugeneraattori
	 */
	
	protected ContinuousGenerator generator;
	/**
	 * Tapahtumalista, jossa on kaikkien asiakkaiden tapahtumat
	 */
	protected Tapahtumalista tapahtumalista;
	/**
	 * Palvelupiste ID palvelupisteiden indeksointiin
	 */
	protected int palvelupisteID = 0;
	/**
	 * Apumuuttuja indeksoinnille
	 */
	protected static int i = 0;
	/**
	 * Totuusarvomuuttuja palvelupisteen aktiivisuudelle
	 */
	protected boolean varattu = false;
	/**
	 * Palvelupisteen aktiiviaikamuuttuja
	 */
	protected double aktiiviaika = 0;
	/**
	 * Palvelupisteellä asiakkaiden viettämän aikamäärän summa
	 */
	protected long kokonaisoleskeluaika = 0;
	/**
	 * Palveltujen asiakkaiden lukumäärä
	 */
	protected int palveltujenLkm = 0;


	/**
	 * Konstruktori palvelupisteelle
	 * @param generator käytettävä satunnaislukugeneraattori
	 * @param tapahtumalista asiakastapahtumat
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		palvelupisteID = i++;
	}


	/**
	 * Lisää uuden asiakkaan jonoon
	 * @param a = uusi asiakas
	 */
	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	/**
	 * Ottaa viimeisimmän asiakkaan pois jonosta
	 * @return palauttaa viimeisen asiakkaan tiedot ja poistaa sen jonosta
	 */

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	/**
	 * Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
	 */
	public void aloitaPalvelu(){
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		palveltujenLkm++;
		varattu = true;
	}

	/**
	 * Asiakkaan jätteistä määräytyvä seuraava tapahtuma
	 * @param jatteet Asiakkaan jätteet
	 * @return palauttaa seuraavan tapahtumatyypin
	 */
	public TapahtumanTyyppi seuraavaPalvelu(LinkedList<Jate> jatteet){

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

	/**
	 * Getteri palvelupisteen aktiivisuudelle
	 * @return onko palvelupiste varattu
	 */
	public boolean onVarattu(){
		return varattu;
	}

	/**
	 * Getteri palveltujen lukumäärälle palvelupisteellä
	 * @return palauttaa palveltujen lukumäärän palvelupisteeltä
	 */
	public int getPalveltujenLkm(){
		return palveltujenLkm;
	}

	/**
	 * Getteri, joka tarkistaa onko palvelupisteen jonossa vielä asiakkaita
	 * @return palauttaa totuusarvomuuttujan
	 */
	public boolean onJonossa(){
		return jono.size() != 0;
	}

	/**
	 * Getteri asiakkaiden kokonaisoleskeluajan palvelupisteessä
	 * @return palauttaa asiakkaiden kokonaisoleskeluajan palvelupisteessä
	 */
	public long getKokonaisoleskeluaika(){
        return kokonaisoleskeluaika;
    }

	/**
	 * Getteri palvelupisteen aktiiviajalle
	 * @return palauttaa aktiiviajan
	 */

	public double getAktiiviaika(){
		return aktiiviaika;
	}

	/**
	 * Getteri palvelupisteen jonolle
	 * @return palauttaa jonon
	 */
	public LinkedList<Asiakas> getJono(){
		return jono;
	}

	/**
	 * Setteri palvelupisteen indeksille
	 * @param j indeksi
	 */
	public void setI(int j){
		i = 0;
	}
}
