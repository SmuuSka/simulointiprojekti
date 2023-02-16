package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.Normal;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;

import java.util.Arrays;
import java.util.LinkedList;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int[] JATEMAARAN_VAIHTELUVALI;
	private static int i = 1;
	private static long sum = 0;
	private LinkedList<Jate> jatteet = new LinkedList<>();
	private Jategeneraattori jategeneraattori;

	// Todennäköisyydet eri jätelajeille 
	private static int[] TJATELAJI;

	public static void setJatemaara(int[] jatemaaranVaihteluvali){
		JATEMAARAN_VAIHTELUVALI = jatemaaranVaihteluvali;
		System.out.println("JATEMÄÄRÄ: " + Arrays.toString(JATEMAARAN_VAIHTELUVALI));
	}

	public static void setTJATELAJI(int[] todennakoisyydet){
		TJATELAJI = todennakoisyydet;
	}

	public Asiakas(){

	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		System.out.println(Arrays.toString(TJATELAJI));
		// Lisätään asiakkaalle generoidut jätteet
		int vaihteluvali = JATEMAARAN_VAIHTELUVALI[1]-JATEMAARAN_VAIHTELUVALI[0];
		jategeneraattori = new Jategeneraattori(TJATELAJI, new Normal(vaihteluvali/2, 1.0));
		jategeneraattori.generoiJatteet();
		jatteet = jategeneraattori.getJatteet();

		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}

	public static int getID(){
		return i;
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! ");
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui: " +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui: " +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

	public LinkedList<Jate> getJatteet() {
		return jatteet;
	}

}
