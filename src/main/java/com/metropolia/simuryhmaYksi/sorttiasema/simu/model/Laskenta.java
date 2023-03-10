package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.Arrays;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 * 
 * Laskentaluokassa käsitellään havainnolliset suureet järjestelmän suorituskykysuureiksi.
 */

public class Laskenta {

    // Palvelupisteiden lukumäärä
    private final int LKM = 4;

    // Havainnoitavia suureita
    private int saapuneidenLkm;
    private int[] palveltujenLkm;
    private int palveltujenMaara;
    private double[] aktiiviajat;
    private double kokonaisaika = Kello.getInstance().getAika();
    private long[] kokonaisoleskeluajat;
    private double jatteenKokonaismaara;
    private double[] jatteenMaarat;
    

    // Johdettuja suureita
    private double suoritusteho;
    private double[] keskmJononpituudet = new double[LKM];
    private double[] keskmLapimenoajat = new double[LKM];
    private double[] kayttoasteet = new double[LKM];
    private double[] keskmPalveluajat = new double[LKM];
    private double keskmJatteenmaara;
    
  
    
    /**
     * @param saapuneidenLkm
     * @param palveltujenLkm
     * @param aktiiviajat
     * @param kokonaisoleskeluajat
     * @param jatteenKokonaismaara
     * @param jatteenMaarat
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

    public int getLKM() {
        return LKM;
    }

    public int getSaapuneidenLkm() {
        return saapuneidenLkm;
    }

    public int[] getPalveltujenLkm() {
        return palveltujenLkm;
    }

    public int getPalveltujenMaara() {
        return palveltujenMaara;
    }

    public double[] getAktiiviajat() {
        return aktiiviajat;
    }

    public double getKokonaisaika() {
        return kokonaisaika;
    }

    public long[] getKokonaisoleskeluajat() {
        return kokonaisoleskeluajat;
    }

    public double getJatteenKokonaismaara() {
        return jatteenKokonaismaara;
    }

    public double[] getJatteenMaarat() {
        return jatteenMaarat;
    }

    public double getSuoritusteho() {
        return suoritusteho;
    }

    public double[] getKeskmJononpituudet() {
        return keskmJononpituudet;
    }

    public double[] getKeskmLapimenoajat() {
        return keskmLapimenoajat;
    }

    public double[] getKayttoasteet() {
        return kayttoasteet;
    }

    public double[] getKeskmPalveluajat() {
        return keskmPalveluajat;
    }

    public double getKeskmJatteenmaara() {
        return keskmJatteenmaara;
    }

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
