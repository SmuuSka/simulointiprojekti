package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;

/**
 * @author Samu Aikio, Joel Tikkanen, Kaspar Tullus
 */
public class Jategeneraattori {

    private int[] todT;
    private LinkedList<Jate> jatteet = new LinkedList<>();
    //private final static int ERIJATTEIDENLKM = 3;

    // Jakauman mukainen satunnaisluku generaattori
    private ContinuousGenerator generaattori;

    /**
     * 
     * @param todT
     * @param generaattori
     */
    public Jategeneraattori(int[] todT, ContinuousGenerator generaattori) {
        this.todT = todT;
        this.generaattori = generaattori;
    }

    /**
     * Toteutus perustuu jotenkuten aikasemmin toteutettuun ikäjakauma tehtävään.
     * 
     * Metodille annetaan parametrina taulukko, joka sisältää eri jätteiden todennäköisyydet.
     * Taulukon ensimmäinen indeksi, merkitsee todennäköisyyttä elektroniikkajätteelle, toinen palamattomalle ja kolmas
     * palavalle.
     * Todennakoisyydet jaetaan kaksiuloitteiseen taulukkoon osaväleiksi, 
     * jos todennäköisyys on nolla, niin osaväli on [-1, -1].
     * Sitten arvotaan satunnaisluku väliltä [0, suurin yläraja] (max).
     * Lopuksi metodi palauttaa arvon i, joka kertoo mihin osaväliin satunnaisluku sijoittui (0,1,2).
     * 
     * Kun arvottu luku on väliltä [0, 2], niin voimme hyödyntää sitä helposti Jätelaji enumin käsittelyssä.
     * 
     * @param arr
     * @return arvottu numero väliltä [0,2]
     */
    public int arvoArvo(int[] arr){
        int[][] valit = new int[3][2];
        int i = 0;
        int a = 0;
        int max = 0;
        for (int n: arr) if (n != 0) max++;
       
        for (int j = 0; j <arr.length; j++){
            if (arr[j] == 0){
                valit[j][0] = valit[j][1] = -1;
            } else {
                valit[j][0] = a;
                valit[j][1] = a+arr[j];
                a+=arr[j]+1;
            }
        }
        max += arr[0] + arr[1] + arr[2] - 1;
        int arvottu = (int)(Math.random() * max);
        while (arvottu > valit[i][1]) i++;
        return i; 
    }

    /**
     * Generoi asiakkaalle kolme jätettä arvoArvo metodia käyttäen.
     * Huom. Generoidut jätteet voivat olla samoja.
     */
    public void generoiJatteet(){
        int lkm = 0;
        for (int j : todT) if (j > 0) lkm++;
        for (int i=0;i<lkm;i++){

            int arvottuLaji = arvoArvo(todT);
            boolean loytyi = false;

            for (Jate jate : jatteet) {
                if (jate.getJatelaji() == Jatelaji.values()[arvottuLaji]){
                    jate.setPaino(jate.getPaino() + generaattori.sample());
                    loytyi = true;
                    break;
                }
            }
            if (!loytyi) jatteet.add(new Jate(Jatelaji.values()[arvottuLaji], generaattori.sample()));
        }
    }

    /**
     * 
     * @return jatteet
     */
    public LinkedList<Jate> getJatteet(){
        return jatteet;
    }
}