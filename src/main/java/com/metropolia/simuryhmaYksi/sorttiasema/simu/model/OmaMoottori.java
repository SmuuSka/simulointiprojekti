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
		palvelupisteet[0]=new Palvelutiski(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.PAASAAPUMINEN);

		//Jätepalvelut
		palvelupisteet[1]=new Jatelava(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ELEKTRONIIKKA);
		palvelupisteet[2]=new Jatelava(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.PALAMATONJATE);
		palvelupisteet[3]=new Jatelava(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.PALAVAJATE);

		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5),tapahtumalista,TapahtumanTyyppi.PAASAAPUMINEN);
	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()){
			
			case PAASAAPUMINEN:
				palvelupisteet[0].lisaaJonoon(new Asiakas());
				saapumisprosessi.generoiSeuraava();
				break;
			case ELEKTRONIIKKA: a = palvelupisteet[0].otaJonosta();
				palvelupisteet[1].lisaaJonoon(a);
				break;
			case PALAMATONJATE: a = palvelupisteet[1].otaJonosta();
				palvelupisteet[2].lisaaJonoon(a);
				break;
			case PALAVAJATE:
				a = palvelupisteet[2].otaJonosta();
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();
				break;
//			case POISTUMINEN:
//				a = palvelupisteet[2].otaJonosta();
//				a.setPoistumisaika(Kello.getInstance().getAika());
//				a.raportti();
//				break;

		}
	}

	@Override
	protected void tulokset() {	
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
