package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.TapahtumanTyyppi;

public class Tapahtuma implements Comparable<Tapahtuma> {
	
		
	private TapahtumanTyyppi tyyppi;
	private double aika;
	private int tapahtumanLuoja;
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, double aika, int tapahtumanLuoja){
		this.tyyppi = tyyppi;
		this.aika = aika;
		this.tapahtumanLuoja = tapahtumanLuoja;
	}
	
	public void setTyyppi(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	public TapahtumanTyyppi getTyyppi() {
		return tyyppi;
	}
	public void setAika(double aika) {
		this.aika = aika;
	}
	public double getAika() {
		return aika;
	}

	public int getTapahtumanLuoja(){
		return tapahtumanLuoja;
	}

	@Override
	public int compareTo(Tapahtuma arg) {
		if (this.aika < arg.aika) return -1;
		else if (this.aika > arg.aika) return 1;
		return 0;
	}
	
	
	

}
