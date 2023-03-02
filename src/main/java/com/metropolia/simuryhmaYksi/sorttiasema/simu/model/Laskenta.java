package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.Arrays;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;

public class Laskenta {

    private final int LKM = 4;

    // Havainnoitavia suureita
    private int saapuneidenLkm;
    private int[] palveltujenLkm;
    private int palveltujenMaara;
    private double[] aktiiviajat;
    private double kokonaisaika = Kello.getInstance().getAika();
    private long[] kokonaisoleskeluajat;
    private double jatteenKokonaismaara;
    

    // Johdettuja suureita
    private double suoritusteho;
    private double[] keskmJononpituudet = new double[LKM];
    private double[] keskmLapimenoajat = new double[LKM];
    private double[] kayttoasteet = new double[LKM];
    private double[] keskmPalveluajat = new double[LKM];
    private double keskmJatteenmaara;
    
  
    

    public Laskenta(int saapuneidenLkm, int[] palveltujenLkm, double[] aktiiviajat, long[] kokonaisoleskeluajat, double jatteenKokonaismaara) {
        this.saapuneidenLkm = saapuneidenLkm;
        this.palveltujenLkm = palveltujenLkm;
        this.aktiiviajat = aktiiviajat;
        this.kokonaisoleskeluajat = kokonaisoleskeluajat;
        this.jatteenKokonaismaara = jatteenKokonaismaara;
    }

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

    public void setPalveltujenMaara(){
        int j = 0;
        for (int i = 0; i < LKM; i++){
            j+=palveltujenLkm[i];
        }
        palveltujenMaara=j;
    }

    public void setKeskmJatteenmaara(){
        keskmJatteenmaara = jatteenKokonaismaara/palveltujenMaara;
    }


    public void setKayttoasteet(){
        for (int i = 0; i < LKM; i++){
            kayttoasteet[i] = aktiiviajat[i]/kokonaisaika;
        }
    }

    public void setSuoritusteho(){
        suoritusteho = palveltujenMaara/kokonaisaika;
    }

    public void setKeskmPalveluajat(){
        try {
            for (int i = 0; i < LKM; i++){
               keskmPalveluajat[i] = aktiiviajat[i]/palveltujenLkm[i];
            }
        } catch (Exception e) {

        }
    }

    public void setKeskimaaraisetLapimenoajat(){
        try {
        for (int i = 0; i < LKM; i++){
            keskmLapimenoajat[i] = kokonaisoleskeluajat[i]/palveltujenLkm[i];
        }
        } catch (Exception e) {

        }
    }

    public void keskmJononpituudet(){
        try {
            for (int i = 0; i < LKM; i++){
                keskmJononpituudet[i] = kokonaisoleskeluajat[i]/kokonaisaika;
            }
        } catch (Exception e){}
            
        
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

    public double getJatteenKokonaismaara(){
        return jatteenKokonaismaara;
    }

    public double getKeskmJatteenmaara(){
        return keskmJatteenmaara;
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

    @Override
    public String toString() {
        return "Laskenta [saapuneidenLkm=" + saapuneidenLkm + ", palveltujenLkm=" + Arrays.toString(palveltujenLkm)
                + ", palveltujenSumma=" + palveltujenMaara + ", aktiiviajat=" + Arrays.toString(aktiiviajat)
                + ", kokonaisaika=" + kokonaisaika + ", kokonaisoleskeluajat=" + Arrays.toString(kokonaisoleskeluajat)
                + ", jatteenKokonaismaara=" + jatteenKokonaismaara + ", suoritusteho=" + suoritusteho
                + ", keskmJononpituudet=" + Arrays.toString(keskmJononpituudet) + ", keskmLapimenoajat="
                + Arrays.toString(keskmLapimenoajat) + ", kayttoasteet=" + Arrays.toString(kayttoasteet)
                + ", keskmPalveluajat=" + Arrays.toString(keskmPalveluajat) + ", keskmJatteenmaara=" + keskmJatteenmaara
                + "]";
    }

    
}