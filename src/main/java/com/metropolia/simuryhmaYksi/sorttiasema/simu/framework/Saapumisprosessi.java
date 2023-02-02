package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.TapahtumanTyyppi;

public class Saapumisprosessi {
	
	private ContinuousGenerator generaattori;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;

	//TapahtumanTyyppi = Luo pääsaapumisen
	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi){
		this.generaattori = g;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
	}

	public void generoiSeuraava(){
		//Luo uuden pääsaapumistapahtuman
		Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+generaattori.sample());
		tapahtumalista.lisaa(t);
	}

}
