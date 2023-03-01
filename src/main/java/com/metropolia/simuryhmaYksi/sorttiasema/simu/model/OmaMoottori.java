package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Moottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Saapumisprosessi;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Tapahtuma;
import org.xml.sax.Parser;

import java.util.Arrays;

public class OmaMoottori extends Moottori {

    private Saapumisprosessi saapumisprosessi;
    private int poistunutMaara = 0;
    private int saapumistenMaara = 0;
    private double ELEKTROjatteidenmaara = 0;
    private double PALAVAjatteidenmaara = 0;
    private double PALAMATONjatteidenmaara = 0;

    public OmaMoottori(IKontrolleriMtoV kontrolleri) {
        super(kontrolleri);
        palvelupisteet = new Palvelupiste[4];

        //Palvelutiski
        palvelupisteet[0] = new Palvelutiski(new Normal(10, 6), tapahtumalista);

        //Jätelavat
        palvelupisteet[1] = new Jatelava(new Normal(10, 6), tapahtumalista, Jatelaji.ELEKTRONIIKKA);
        palvelupisteet[2] = new Jatelava(new Normal(10, 10), tapahtumalista, Jatelaji.PALAMATONAJATE);
        palvelupisteet[3] = new Jatelava(new Normal(5, 3), tapahtumalista, Jatelaji.PALAVAJATE);

        // Järjestelmään Saapuminen
        saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.PALVELUTISKI_SAAPUMINEN);
    }


    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

        Asiakas a;
        int jono1 = t.getTapahtumanLuoja();
        int jono2 = t.getTyyppi().ordinal();

        System.out.println("ASIAKAS LÄHTEE JONOSTA " + jono1 + " JA SAAPUU JONOON " + jono2);
        switch (t.getTyyppi()) {

            case PALVELUTISKI_SAAPUMINEN:
                if(Kello.getInstance().getAika() < getSimulointiaika()){
                    saapumistenMaara++;
                    kontrolleri.getVisualisointi().lisaaSaapumistenMaara(saapumistenMaara);
                    a = new Asiakas();
                    palvelupisteet[0].lisaaJonoon(a);
                    saapumisprosessi.generoiSeuraava();
                }
                break;

            case ELEKTRONIIKKA_SAAPUMINEN:
                switch (palvelupisteet[jono1].palvelupisteID){
                    case 0:
                        kontrolleri.getVisualisointi().moveAsiakasELEKTRO();
                }
                a = palvelupisteet[jono1].otaJonosta();
                a.setSaapumisaika(kello.getAika());
                palvelupisteet[jono2].lisaaJonoon(a);
                break;
            case PALAMATONJATE_SAAPUMINEN:
                switch (palvelupisteet[jono1].palvelupisteID){
                    case 0:
                        kontrolleri.getVisualisointi().moveAsiakasEPA();
                }
                a = palvelupisteet[jono1].otaJonosta();
                a.setSaapumisaika(kello.getAika());
                palvelupisteet[jono2].lisaaJonoon(a);
                break;

            case PALAVAJATE_SAAPUMINEN:
                switch (palvelupisteet[jono1].palvelupisteID){
                    case 0:
                        kontrolleri.getVisualisointi().moveAsiakasPALAVA();
                }
                a = palvelupisteet[jono1].otaJonosta();
                a.setSaapumisaika(kello.getAika());
                palvelupisteet[jono2].lisaaJonoon(a);
                break;
            case POISTUMINEN:
                a = palvelupisteet[jono1].otaJonosta();
                poistunutMaara++;
                switch (palvelupisteet[jono1].palvelupisteID){
                    case 1:
                        poistunutMaara++;
                        kontrolleri.getVisualisointi().moveAsiakasELEKTRO_POISTUMINEN();
                        kontrolleri.getVisualisointi().lisaaPoistunutMaara(poistunutMaara);
                        break;
                    case 2:
                        poistunutMaara++;
                        kontrolleri.getVisualisointi().moveAsiakasEPA_POISTUMINEN();
                        kontrolleri.getVisualisointi().lisaaPoistunutMaara(poistunutMaara);
                        break;
                    case 3:
                        poistunutMaara++;
                        kontrolleri.getVisualisointi().moveAsiakasPALAVA_POISTUMINEN();
                        kontrolleri.getVisualisointi().lisaaPoistunutMaara(poistunutMaara);
                        break;
                }
                a.raportti();
                break;
        }
		
        //PALAVAJÄTE REITTI ANIMOINTI
        try {
                //PALAVA REITTI ANIMOINTI
            if (palvelupisteet[jono1].palvelupisteID == 3 && palvelupisteet[jono2].palvelupisteID == 2) {
                kontrolleri.getVisualisointi().moveAsiakasPA_EPA();
            } else if (palvelupisteet[jono1].palvelupisteID == 3 && palvelupisteet[jono2].palvelupisteID == 1) {
                kontrolleri.getVisualisointi().moveAsiakasPALAVA_ELEKTRO();

                //EPA REITTI ANIMOINTI
            }else if(palvelupisteet[jono1].palvelupisteID == 2 && palvelupisteet[jono2].palvelupisteID == 3) {
                kontrolleri.getVisualisointi().moveAsiakasEPA_PA();
            }else if(palvelupisteet[jono1].palvelupisteID == 2 && palvelupisteet[jono2].palvelupisteID == 1){
                kontrolleri.getVisualisointi().moveAsiakasEPA_ELEKTRO();

                //ELEKTRO REITTI ANIMOINTI
            }else if (palvelupisteet[jono1].palvelupisteID == 1 && palvelupisteet[jono2].palvelupisteID == 2){
                kontrolleri.getVisualisointi().moveAsiakasELEKTRO_EPA();
            }else if (palvelupisteet[jono1].palvelupisteID == 1 && palvelupisteet[jono2].palvelupisteID == 3){
                kontrolleri.getVisualisointi().moveAsiakasELEKTRO_PALAVA();
            }
        }catch(ArrayIndexOutOfBoundsException e){
        }
    }

    public void setVarattu(){

        int palvelutiskiPituus = palvelupisteet[0].getJono().size();
        int elektroniikkaPituus = palvelupisteet[1].getJono().size();
        int palamatonPituus = palvelupisteet[2].getJono().size();
        int palavaPituus = palvelupisteet[3].getJono().size();

        kontrolleri.getVisualisointi().setSAAPUMINEN_VARATTU(palvelutiskiPituus != 0);
        kontrolleri.getVisualisointi().setEPA_VARATTU(palamatonPituus != 0);
        kontrolleri.getVisualisointi().setELEKTRO_VARATTU(elektroniikkaPituus != 0);
        kontrolleri.getVisualisointi().setPALAVA_VARATTU(palavaPituus != 0);
    }


	public void setTekstit(){
		
		// Asetetaan saapumisjonon pituus 
        kontrolleri.setSAAPUMISJononPituus(palvelupisteet[0].getJono().size());
        // Asetetaan elektroniikka jätelavan jonon pituus
        kontrolleri.setEJononPituus(palvelupisteet[1].getJono().size());

        // Asetetaan palamattoman jätteen jätelavan jonon pituss
        kontrolleri.setPTJononPituus(palvelupisteet[2].getJono().size());

        // Asetetaan palavan jätteen jätelavan jonon pituss
        kontrolleri.setPJononPituus(palvelupisteet[3].getJono().size());

        // Asetetaan poistuneet teksti
        kontrolleri.getVisualisointi().lisaaPoistunutMaara(poistunutMaara);
	}

    @Override
    protected void tulokset(){
        poistunutMaara = 0;
        double jatteenKokonaismaara = 0;
        long[] koleskelu = new long[4];
        double[] aa = new double[4];
        int[] palveltujenLkm = new int[4];

        System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());

        System.out.println("Jätelavat: ");
        for (int i = 0; i < palvelupisteet.length; i++) {
            long oa = palvelupisteet[i].getKokonaisoleskeluaika();
            koleskelu[i] = oa;
            aa[i] = palvelupisteet[i].getAktiiviaika();
            palveltujenLkm[i] = palvelupisteet[i].getPalveltujenLkm();
            System.out.printf("Kokonaisoleskelu aika palvelupisteellä %d: %d\n", i, oa);

        };


        for (int i = 1; i < palvelupisteet.length; i++){
            jatteenKokonaismaara += ((Jatelava) (palvelupisteet[i])).getMaara();
        }

        System.out.println("Jatteen kokonaismäärä: " + jatteenKokonaismaara);
        System.out.println("Asiakkaiden kokonaismäärä: " + (Asiakas.getID()));
        System.out.println("Keskimääräinen jätemäärä per asiakas: " + jatteenKokonaismaara / (Asiakas.getID()) + " kg");
        Laskenta l = new Laskenta(saapumistenMaara, palveltujenLkm , aa, koleskelu, jatteenKokonaismaara);
        l.laske();
        System.out.println(l);
        kontrolleri.tallennaTulokset(jatteenKokonaismaara, Asiakas.getID(), palvelupisteet);
    }
}
