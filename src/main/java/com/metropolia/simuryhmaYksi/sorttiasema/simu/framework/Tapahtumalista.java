package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;

import java.util.PriorityQueue;
/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){
	 
	}
	
	public Tapahtuma poista(){
		Trace.out(Trace.Level.INFO,"Tapahtumalistasta poisto " + lista.peek().getTyyppi() + " " + lista.peek().getAika() );
		return lista.remove();
	}
	
	public void lisaa(Tapahtuma t){
		Trace.out(Trace.Level.INFO,"Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}

	public PriorityQueue<Tapahtuma> getLista() {
		return lista;
	}


	public double getSeuraavanAika() {
		double aika = 0;
		if(!lista.isEmpty()){
			aika = lista.peek().getAika();
		}
		return aika;
	}
}
