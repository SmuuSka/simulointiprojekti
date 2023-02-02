package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

public class Jate {
    private double paino;
    private Jatelaji jatelaji;

    public Jate(){

    }

    public Jate(Jatelaji jatelaji, double maara){
        this.paino = maara;
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
