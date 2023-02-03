package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
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
	private static int i = 1;
	private static long sum = 0;
	private String[] status = {"palvelutiskillä", "elektroniikka", "palamaton", "palava", "poistuu"};
	private LinkedList<Jate> jatteet = new LinkedList<>(Arrays.asList(new Jate(Jatelaji.ELEKTRONIIKKA,
			30), new Jate(Jatelaji.PALAMATONAJATE,20), new Jate(Jatelaji.PALAVAJATE,10)));
	
	public Asiakas(){
	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		//jatteet.forEach(jate -> System.out.println(jate));
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
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

	public void status(int i){
		System.out.println("STATUS: " +  status[i] + ", ID: " + this.id);
	}
}
