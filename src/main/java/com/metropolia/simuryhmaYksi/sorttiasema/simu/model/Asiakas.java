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
	private static int[] JATEMAARA;
	private static int i = 1;
	private static long sum = 0;
	private LinkedList<Jate> jatteet = new LinkedList<>();

	public static void setJatemaara(int[] jatemaaranVaihteluvali){
		JATEMAARA = jatemaaranVaihteluvali;
		System.out.println("JATEMÄÄRÄ: " + Arrays.toString(JATEMAARA));
	}
	
	public Asiakas(){
	    id = i++;

		saapumisaika = Kello.getInstance().getAika();
		// Lisätään asiakkaalle ennaltamäärätyt jätteet
		jatteet.add(new Jate(JATEMAARA, Jatelaji.ELEKTRONIIKKA));
		jatteet.add(new Jate(JATEMAARA, Jatelaji.PALAMATONAJATE));
		jatteet.add(new Jate(JATEMAARA, Jatelaji.PALAVAJATE));
		//jatteet.add(new Jate(Jatelaji.ELEKTRONIIKKA,30));
		//jatteet.add(new Jate(Jatelaji.PALAMATONAJATE,20));
		//jatteet.add(new Jate(Jatelaji.PALAVAJATE,10));
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
