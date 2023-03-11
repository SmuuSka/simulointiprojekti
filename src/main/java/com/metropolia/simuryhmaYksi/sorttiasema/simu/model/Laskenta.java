package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.Arrays;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

 /**
 * Laskentaluokassa käsitellään havainnolliset suureet järjestelmän suorituskykysuureiksi.
 */

public class Laskenta {

     /**
      * Palvelupisteiden lukumäärä
      */
    private final int LKM = 4;

     /**
      * Saapuneiden asiakkaiden lukumäärä palvelupisteillä
      */
    private int saapuneidenLkm;

     /**
      * Palveltujen asiakkaiden määrä palvelupisteillä
      */
    private int[] palveltujenLkm;
     /**
      * Palveltujen asiakkaiden määrä palvelupisteellä
      */
    private int palveltujenMaara;

     /**
      * Palvelupisteiden aktiiviajat
      */
    private double[] aktiiviajat;

     /**
      * Simulaation kokonaisaika
      */
    private double kokonaisaika = Kello.getInstance().getAika();

     /**
      * Palvelupisteiden kokonaisoleskeluajat
      */
    private long[] kokonaisoleskeluajat;
     /**
      * Jätteiden kokonaismäärä
      */
    private double jatteenKokonaismaara;
     /**
      * Jätemäärät palvelupisteillä
      */
    private double[] jatteenMaarat;
    

    // Johdettuja suureita
     /**
      * Simulaation suoritusteho
      */
    private double suoritusteho;
     /**
      * Keskimääräiset jonopituudet palvelupisteillä
      */
    private double[] keskmJononpituudet = new double[LKM];
     /**
      * Keskimääräiset läpimenoajat palvelupisteillä
      */
    private double[] keskmLapimenoajat = new double[LKM];
     /**
      * Palvelupisteiden käyttöasteet
      */
    private double[] kayttoasteet = new double[LKM];
     /**
      * Keskimääräiset palveluajat palvelupisteillä
      */
    private double[] keskmPalveluajat = new double[LKM];

     /**
      * Keskimääräinen jätemäärä
      */
    private double keskmJatteenmaara;
    
  
    
    /**
     * @param saapuneidenLkm OmaMoottorista tuleva parametri
     * @param palveltujenLkm OmaMoottorista tuleva parametri
     * @param aktiiviajat OmaMoottorista tuleva parametri
     * @param kokonaisoleskeluajat OmaMoottorista tuleva parametri
     * @param jatteenKokonaismaara OmaMoottorista tuleva parametri
     * @param jatteenMaarat OmaMoottorista tuleva parametri
     */
    public Laskenta(int saapuneidenLkm, int[] palveltujenLkm, double[] aktiiviajat, long[] kokonaisoleskeluajat, double jatteenKokonaismaara, double[] jatteenMaarat) {
        this.saapuneidenLkm = saapuneidenLkm;
        this.palveltujenLkm = palveltujenLkm;
        this.aktiiviajat = aktiiviajat;
        this.kokonaisoleskeluajat = kokonaisoleskeluajat;
        this.jatteenKokonaismaara = jatteenKokonaismaara;
        this.jatteenMaarat = jatteenMaarat;
        System.out.println(Arrays.toString(jatteenMaarat));
    }
    /**
     * Metodi joka kutsuu kaikkia settereitä asettaakseen suorituskykysuureet
     */
    public void laske(){
        setPalveltujenMaara();
        setKeskmJatteenmaara();
        setKayttoasteet();
        setSuoritusteho();
        setSuoritusteho();
        setKeskmPalveluajat();
        setKeskimaaraisetLapimenoajat();
        keskmJononpituudet();
    }
    /**
     * Laskutoimitus palveltujen kokonaismäärälle
     */
    public void setPalveltujenMaara(){
        int j = 0;
        for (int i = 0; i < LKM; i++){
            j+=palveltujenLkm[i];
        }
        palveltujenMaara=j;
    }
    /**
     * Laskutoimitukset keskimääräiselle jätteenmäärälle
     */
    public void setKeskmJatteenmaara(){
        keskmJatteenmaara = jatteenKokonaismaara/palveltujenMaara;
        if (jatteenKokonaismaara == 0){
            keskmJatteenmaara = 0;
        }
    }
    /**
     * Laskutoimitukset kayttoasteille
     */
    public void setKayttoasteet(){
        for (int i = 0; i < LKM; i++){
            kayttoasteet[i] = aktiiviajat[i]/kokonaisaika;
        }
    }
    /**
     * Laskutoimitus järjestelmän suoritusteholle
     */
    public void setSuoritusteho(){
        suoritusteho = palveltujenMaara/kokonaisaika;
    }

    /**
     * Laskutoimitukset keskimääräisille palveluajoille
     */
    public void setKeskmPalveluajat(){
        for (int i = 0; i < LKM; i++) {
            if (palveltujenLkm[i] == 0) {
                keskmPalveluajat[i] = 0;
            } else {
                keskmPalveluajat[i] = aktiiviajat[i] / palveltujenLkm[i];
            }
        }
    }
    /**
     * Laskutoimitukset keskimääräisille läpimenoajoille
     */
    public void setKeskimaaraisetLapimenoajat(){
        try {
        for (int i = 0; i < LKM; i++){
            keskmLapimenoajat[i] = kokonaisoleskeluajat[i]/palveltujenLkm[i];
        }
        } catch (Exception e) {

        }
    }
    /**
     * Laskutoimitukset keskimääräiselle jononpituuksille
     */
    public void keskmJononpituudet(){
        try {
            for (int i = 0; i < LKM; i++){
                keskmJononpituudet[i] = kokonaisoleskeluajat[i]/kokonaisaika;
            }
        } catch (Exception e){}
            
        
    }

     /**
      * Getteri saapuneiden lukumäärälle
      * @return palauttaa saapuneiden lukumäärän
      */
    public int getSaapuneidenLkm() {
        return saapuneidenLkm;
    }

     /**
      * Getteri palveltujen lukumäärälle
      * @return palauttaa palveltujen lukumäärän palvelupisteillä
      */
    public int[] getPalveltujenLkm() {
        return palveltujenLkm;
    }

     /**
      * Getteri palveltujen lukumäärälle
      * @return palauttaa palveltujen lukumäärän
      */
    public int getPalveltujenMaara() {
        return palveltujenMaara;
    }
     /**
      * Getteri palvelupisteiden aktiiviajoille
      * @return palauttaa palvelupisteiden aktiiviajat
      */

    public double[] getAktiiviajat() {
        return aktiiviajat;
    }

     /**
      * Getteri simulaation kokonaisajalle
      * @return palauttaa simulaation kokonaisajan
      */
    public double getKokonaisaika() {
        return kokonaisaika;
    }

     /**
      * Getteri palvelupisteiden kokonaisoleskeluajat
      * @return palauttaa palvelupisteiden kokonaisajat
      */
    public long[] getKokonaisoleskeluajat() {
        return kokonaisoleskeluajat;
    }
     /**
      * Getteri jätteiden kokonaismäärälle
      * @return palauttaa jätteiden kokonaismäärän
      */

    public double getJatteenKokonaismaara() {
        return jatteenKokonaismaara;
    }
     /**
      * Getteri jätteiden kokonaismäärille palvelupisteillä
      * @return palauttaa palvelupisteiden kokonaisjätemäärät
      */

    public double[] getJatteenMaarat() {
        return jatteenMaarat;
    }
     /**
      * Getteri simulaation suoritusteholle
      * @return palauttaa suoritustehon
      */

    public double getSuoritusteho() {
        return suoritusteho;
    }

     /**
      * Getteri keskimääräisille jononpituuksille palvelupisteillä
      * @return palauttaa keskimääräiset jononpituudet palvelupisteillä
      */
    public double[] getKeskmJononpituudet() {
        return keskmJononpituudet;
    }

     /**
      * Getteri keskimääräisille läpimenoajoille palvelupisteillä
      * @return palauttaa keskimääräiset läpimenoajat palvelupisteillä
      */
    public double[] getKeskmLapimenoajat() {
        return keskmLapimenoajat;
    }

     /**
      * Getteri keskimääräisille palvelupisteiden käyttöasteet
      * @return palauttaa keskimääräiset palvelupisteiden käyttöasteet
      */
    public double[] getKayttoasteet() {
        return kayttoasteet;
    }

     /**
      * Getteri keskimääräisille palveluajoille palvelupisteillä
      * @return palauttaa keskimääräiset palvelupisteiden palveluajat
      */
    public double[] getKeskmPalveluajat() {
        return keskmPalveluajat;
    }

     /**
      * Getteri jätemäärien keskiarvolle
      * @return palauttaa jätemäärän keskiarvon
      */
    public double getKeskmJatteenmaara() {
        return keskmJatteenmaara;
    }

     /**
      *
      * @return palauttaa merkkijonoesityksen laskentaluokasta
      */
    @Override
    public String toString() {
        return "Laskenta{" +
                "LKM=" + LKM +
                ", saapuneidenLkm=" + saapuneidenLkm +
                ", palveltujenLkm=" + Arrays.toString(palveltujenLkm) +
                ", palveltujenMaara=" + palveltujenMaara +
                ", aktiiviajat=" + Arrays.toString(aktiiviajat) +
                ", kokonaisaika=" + kokonaisaika +
                ", kokonaisoleskeluajat=" + Arrays.toString(kokonaisoleskeluajat) +
                ", jatteenKokonaismaara=" + jatteenKokonaismaara +
                ", jatteenMaarat=" + Arrays.toString(jatteenMaarat) +
                ", suoritusteho=" + suoritusteho +
                ", keskmJononpituudet=" + Arrays.toString(keskmJononpituudet) +
                ", keskmLapimenoajat=" + Arrays.toString(keskmLapimenoajat) +
                ", kayttoasteet=" + Arrays.toString(kayttoasteet) +
                ", keskmPalveluajat=" + Arrays.toString(keskmPalveluajat) +
                ", keskmJatteenmaara=" + keskmJatteenmaara +
                '}';
    }
}
