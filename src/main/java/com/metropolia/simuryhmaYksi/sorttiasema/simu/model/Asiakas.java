package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import java.util.LinkedList;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private Jategeneraattori jategeneraattori = new Jategeneraattori(new int[] {33,33,33}, new int[] {33,33,33});
	private LinkedList<Jate> jatteet = jategeneraattori.generoiJatteet();
										
	
	public Asiakas(){
	    id = i++;
		saapumisaika = Kello.getInstance().getAika();
		// Lisätään asiakkaalle ennaltamäärätyt jätteet
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
