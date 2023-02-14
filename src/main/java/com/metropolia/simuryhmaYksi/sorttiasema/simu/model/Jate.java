package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.Normal;

public class Jate {
    private double paino;
    private Jatelaji jatelaji;

    public Jate(){
        // TODO:
        // Satunnainen lajin ja painon arpominen
    }

    public Jate(Jatelaji jatelaji, double maara){
        this.paino = maara;
        this.jatelaji = jatelaji;
    }
    public Jate(int[] vaihteluvali, Jatelaji jatelaji){
        this.paino = new Normal((vaihteluvali[1] +vaihteluvali[0]) / vaihteluvali.length,10).sample();
        System.out.println("Paino Jäte-luokasta: " + paino);
        this.jatelaji = jatelaji;
    }

    public double getPaino() {
        return paino;
    }

    public Jatelaji getJatelaji() {
        return jatelaji;
    }

    @Override
    public String toString() {
        return "Jate{" +
                "paino=" + paino +
                ", jatelaji=" + jatelaji +
                '}';
    }
}
