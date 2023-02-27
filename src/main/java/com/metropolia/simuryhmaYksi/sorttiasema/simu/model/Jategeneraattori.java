package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;


public class Jategeneraattori {

 
    private int[] todT; // {30, 60, 10}
    private LinkedList<Jate> jatteet = new LinkedList<>();
    private final static int ERIJATTEIDENLKM = 3;

    // Jakauman mukainen satunnaisluku generaattori
    private ContinuousGenerator generaattori;

    public Jategeneraattori(int[] todT, ContinuousGenerator generaattori) {
        this.todT = todT;
        this.generaattori = generaattori;
    }

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
            System.err.println("Alaraja: " + valit[j][0] + ", Yläraja: " + valit[j][1]);
        }
        max += arr[0] + arr[1] + arr[2] - 1;
        System.out.println("MAX: " + max);
        int arvottu = (int)(Math.random() * max);
        while (arvottu > valit[i][1]) i++;
        return i; 
    }

    public void generoiJatteet(){

        for (int i=0;i<ERIJATTEIDENLKM;i++){

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

    public LinkedList<Jate> getJatteet(){
        return jatteet;
    }
}

/*
 * 
 * for (int j = 0; j <arr.length; j++){
                if (arvottu <= valit[j][1] && arvottu >= valit[j][0]){
                    i = j;
                }
            } 
 */