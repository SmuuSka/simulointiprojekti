package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import java.util.LinkedList;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.ContinuousGenerator;


public class Jategeneraattori {

 
    private int[] todT; // {30, 60, 10}
    private LinkedList<Jate> jatteet = new LinkedList<>();
    private final static int ERIJATTEIDENLKM = 3;

    // Jakauma
    private ContinuousGenerator generaattori;

    public Jategeneraattori(int[] todT, ContinuousGenerator generaattori) {
        this.todT = todT;
        this.generaattori = generaattori;
    }

    public int arvoArvo(int[] arr){
        int[][] valit = new int[3][2];
        int i = 0;
        int a = 0;
        for (int j = 0; j <arr.length; j++){
            valit[j][0] = a;
            valit[j][1] = a+arr[j];
            //System.err.println("Alaraja: " + valit[j][0] + ", Yläraja: " + valit[j][1]);
            a+=arr[j]+1;
        }
        int arvottu = (int)(Math.random() * valit[arr.length-1][1]);
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
