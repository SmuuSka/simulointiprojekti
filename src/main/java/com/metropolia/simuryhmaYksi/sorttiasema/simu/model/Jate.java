package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

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

    public double getPaino() {
        return paino;
    }

    public Jatelaji getJatelaji() {
        return jatelaji;
    }

    public void setPaino(double paino){
        this.paino = paino;
    }

    @Override
    public String toString() {
        return "Jate{" +
                "paino=" + paino +
                ", jatelaji=" + jatelaji +
                '}';
    }
}
