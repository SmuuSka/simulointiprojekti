package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.Normal;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 *
 * Asiakas-luokka määrittelee simulaattorissa käytettävän asiakkaan ominaisuudet
 */

public class Asiakas {
	/**
	 * Asiakkaan saapumisaika palvelutiskille
	 */
	private double saapumisaika;
	/**
	 * Asiakkaan poistumisaika simulaattorista
	 */
	private double poistumisaika;
	/**
	 * Asiakkaan ID
	 */
	private int id;
	/**
	 * UI:sta tuleva tieto jätemäärien vaihteluvälistä
	 */
	private static int[] JATEMAARAN_VAIHTELUVALI;
	/**
	 * Apumuuttuja asiakkaan indeksoinnin ylläpitämiseen
	 */
	public static int i = 0;
	/**
	 *
	 */
	//private static long sum = 0;

	/**
	 * Jate-tyyppinen lista asiakkaan jätteille
 	 */
	private LinkedList<Jate> jatteet = new LinkedList<>();
	/**
	 * jategeneraattori-ilmentymän muuttuja
	 */
	private Jategeneraattori jategeneraattori;

	/**
	 * Taulukko jätelajien todennäköisyyksille
 	 */
	private static int[] TJATELAJI;

	/**
	 * Setteri jätemäärän vaihteluvälille
	 * @param jatemaaranVaihteluvali UI:sta tuleva parametri
	 */
	public static void setJatemaara(int[] jatemaaranVaihteluvali){
		JATEMAARAN_VAIHTELUVALI = jatemaaranVaihteluvali;
		System.out.println("JATEMÄÄRÄ: " + Arrays.toString(JATEMAARAN_VAIHTELUVALI));
	}

	/**
	 * Setteri jätelajille
	 * @param todennakoisyydet UI:sta tuleva parametri
	 */
	public static void setTJATELAJI(int[] todennakoisyydet){
		TJATELAJI = todennakoisyydet;
	}

	/**
	 * Konstruktori, joka luo asiakkaan.
	 */
	public Asiakas(){

	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		System.out.println(Arrays.toString(TJATELAJI));
		// Lisätään asiakkaalle generoidut jätteet
		int vaihteluvali = JATEMAARAN_VAIHTELUVALI[1]+JATEMAARAN_VAIHTELUVALI[0];
		jategeneraattori = new Jategeneraattori(TJATELAJI, new Normal(vaihteluvali/2, 1.0));
		jategeneraattori.generoiJatteet();
		jatteet = jategeneraattori.getJatteet();

		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	/**
	 * Getteri asiakkaan id:lle
	 * @return palauttaa asiakkaan id:n
	 */
	public static int getID(){
		return i;
	}

	/**
	 * Getteri asiakkaan poistumisajalle
	 * @return palauttaa asiakkaan poistumisajan
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * Setteri asiakkaan poistumisajalle
	 * @param poistumisaika Omamoottorista tuleva poistumisaikatieto
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	/**
	 * Getteri saapumisajalle
	 * @return palauttaa asiakkaan saapumisajan
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * Setteri asiakkaan saapumisajalle
	 * @param saapumisaika Omamoottorista tuleva saapumisaikatieto
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	/**
	 * Getteri asiakkaan ID:lle
	 * @return palauttaa asiakkaan id:n
	 */
	public int getId() {
		return id;
	}


	/**
	 * Tulostaa raportin
	 */
	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! ");
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui" +poistumisaika);
	}

	/**
	 * Getteri asiakkaan jäte-olioille
	 * @return palauttaa listan jäte-olioista
	 */
	public LinkedList<Jate> getJatteet() {
		return jatteet;
	}
}
