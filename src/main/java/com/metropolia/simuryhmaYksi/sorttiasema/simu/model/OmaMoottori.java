package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;
import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Moottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Saapumisprosessi;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;

import java.util.Arrays;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	
	public OmaMoottori(IKontrolleriMtoV kontrolleri){
		super(kontrolleri);
		palvelupisteet = new Palvelupiste[4];
		
		//Palvelutiski
		palvelupisteet[0]=new Palvelutiski(new Normal(10,6), tapahtumalista);

		//Jätelavat
		palvelupisteet[1]=new Jatelava(new Normal(10,6), tapahtumalista, Jatelaji.ELEKTRONIIKKA);
		palvelupisteet[2]=new Jatelava(new Normal(10,10), tapahtumalista ,Jatelaji.PALAMATONAJATE);
		palvelupisteet[3]=new Jatelava(new Normal(5,3), tapahtumalista,  Jatelaji.PALAVAJATE);

		// Järjestelmään Saapuminen
		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5),tapahtumalista,TapahtumanTyyppi.PALVELUTISKI_SAAPUMINEN);
	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		int jono1 = t.getTapahtumanLuoja();
		int jono2 = t.getTyyppi().ordinal();

		System.out.println("ASIAKAS LÄHTEE JONOSTA " + jono1 + " JA SAAPUU JONOON " + jono2 );
		switch (t.getTyyppi()){
			
			case PALVELUTISKI_SAAPUMINEN:
				a = new Asiakas();
				//Palvetiskijono = tapahtumalista
				palvelupisteet[0].lisaaJonoon(a);
				saapumisprosessi.generoiSeuraava();
				break;
			case ELEKTRONIIKKA_SAAPUMINEN:
				kontrolleri.getVisualisointi().moveAsiakasELEKTRO();
				a = palvelupisteet[jono1].otaJonosta();
				palvelupisteet[jono2].lisaaJonoon(a);
				switch(palvelupisteet[jono1].palvelupisteID & palvelupisteet[jono2].palvelupisteID) {
					case 1 & 3:
						kontrolleri.getVisualisointi().moveAsiakasELEKTRO_PALAVA();
						break;
					case 1 & 2:
						kontrolleri.getVisualisointi().moveAsiakasELEKTRO_EPA();
						break;
				}
				break;
			case PALAMATONJATE_SAAPUMINEN:
				kontrolleri.getVisualisointi().moveAsiakasEPA();
				a = palvelupisteet[jono1].otaJonosta();
				palvelupisteet[jono2].lisaaJonoon(a);
				switch(palvelupisteet[jono1].palvelupisteID & palvelupisteet[jono2].palvelupisteID) {
					case 2 & 1:
						kontrolleri.getVisualisointi().moveAsiakasEPA_ELEKTRO();
						break;
					case 2 & 3:
						kontrolleri.getVisualisointi().moveAsiakasEPA_PA();
						break;
				}
				break;
			case PALAVAJATE_SAAPUMINEN:
				kontrolleri.getVisualisointi().moveAsiakasPALAVA();
				a = palvelupisteet[jono1].otaJonosta();
				palvelupisteet[jono2].lisaaJonoon(a);
				switch(palvelupisteet[jono1].palvelupisteID & palvelupisteet[jono2].palvelupisteID) {
					case 3 & 1:
						kontrolleri.getVisualisointi().moveAsiakasPALAVA_ELEKTRO();
						break;
					case 3 & 2:
						kontrolleri.getVisualisointi().moveAsiakasPA_EPA();
						break;
				}
				break;
			case POISTUMINEN:
				a = palvelupisteet[jono1].otaJonosta();
				switch (palvelupisteet[jono1].palvelupisteID) {

					//ELEKTRONIIKKA
					case 1:
						kontrolleri.getVisualisointi().moveAsiakasELEKTRO_POISTUMINEN();
						break;

						//PALAMATONJATE
					case 2:
						kontrolleri.getVisualisointi().moveAsiakasEPA_POISTUMINEN();
						break;
						//PALAVAJATE
					case 3:
						kontrolleri.getVisualisointi().moveAsiakasPALAVA_POISTUMINEN();
						break;
					case 4:
						break;
				}
				a.setPoistumisaika(Kello.getInstance().getAika());
				a.raportti();
				break;
		}
		// Asetetaan elektroniikka jätelavan jonon pituus
		kontrolleri.setEJononPituus(palvelupisteet[0].getJono().size());
		// Asetetaan palamattoman jätteen jätelavan jonon pituss
		kontrolleri.setPTJononPituus(palvelupisteet[1].getJono().size());
		// Asetetaan palavan jätteen jätelavan jonon pituss
		kontrolleri.setPJononPituus(palvelupisteet[2].getJono().size());
	}

	@Override
	protected void tulokset() {	
		double jatteenKokonaismaara = 0;
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		
		System.out.println("Jätelavat: ");
		for (int i=1;i<palvelupisteet.length;i++) System.out.println(palvelupisteet[i]);
		for (int i=1;i<palvelupisteet.length;i++) jatteenKokonaismaara +=  ((Jatelava) (palvelupisteet[i])).getMaara();

		System.out.println("Jatteen kokonaismäärä: " + jatteenKokonaismaara);
		System.out.println("Asiakkaiden kokonaismäärä: " + Asiakas.getID());

		System.out.println("Keskimääräinen jätemäärä per asiakas: " +  jatteenKokonaismaara/Asiakas.getID() + " kg");
		kontrolleri.tallennaTulokset(jatteenKokonaismaara, Asiakas.getID(),palvelupisteet);
		//System.out.println("Tulokset ... puuttuvat vielä");
	}
}
