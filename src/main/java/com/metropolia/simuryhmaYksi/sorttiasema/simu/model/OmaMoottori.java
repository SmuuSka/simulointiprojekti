package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Moottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Saapumisprosessi;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	
	public OmaMoottori(){
		palvelupisteet = new Palvelupiste[4];
		//Palvelutiski on Palvelupisteen alaluokka.
		palvelupisteet[0]=new Palvelutiski(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ELEKTRONIIKKA_SAAPUMINEN);

		//Jätelavat
		palvelupisteet[1]=new Jatelava(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.PALAMATONJATE_SAAPUMINEN, Jatelaji.ELEKTRONIIKKA);
		palvelupisteet[2]=new Jatelava(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.PALAVAJATE_SAAPUMINEN,Jatelaji.PALAMATONAJATE);
		palvelupisteet[3]=new Jatelava(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.POISTUMINEN, Jatelaji.PALAVAJATE);

		// Saapuminen
		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5),tapahtumalista,TapahtumanTyyppi.PALVELUTISKI_SAAPUMINEN);
	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()){
			case PALVELUTISKI_SAAPUMINEN:
				a = new Asiakas();
				palvelupisteet[0].lisaaJonoon(a);
				saapumisprosessi.generoiSeuraava();
				break;
			case ELEKTRONIIKKA_SAAPUMINEN: 
				a = palvelupisteet[0].otaJonosta();
				palvelupisteet[1].lisaaJonoon(a);
				break;
			case PALAMATONJATE_SAAPUMINEN: 
				a = palvelupisteet[1].otaJonosta();
				palvelupisteet[2].lisaaJonoon(a);
				break;
			case PALAVAJATE_SAAPUMINEN:
				a = palvelupisteet[2].otaJonosta();
				palvelupisteet[3].lisaaJonoon(a);
				break;
			case POISTUMINEN:
				a = palvelupisteet[3].otaJonosta();
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();
				break;
		}
	}

	@Override
	protected void tulokset() {	
		double jatteenKokonaismaara = 0;
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		for (int i=1;i<palvelupisteet.length;i++) jatteenKokonaismaara +=  ((Jatelava) (palvelupisteet[i])).getMaara();
		System.out.println("Keskimääräinen jätemäärä per asiakas: " +  jatteenKokonaismaara/(Jatelava.saapuneet) + " kg");
		//System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
