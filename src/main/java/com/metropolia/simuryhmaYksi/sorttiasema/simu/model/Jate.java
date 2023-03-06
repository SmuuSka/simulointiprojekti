package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.Normal;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */
public class Jate {
    private double paino;
    private Jatelaji jatelaji;

    public Jate(){
    }

    /**
     * @param jatelaji
     * @param maara
     */
    public Jate(Jatelaji jatelaji, double maara){
        this.paino = maara;
        this.jatelaji = jatelaji;
    }
    /**
     * 
     * @param vaihteluvali
     * @param jatelaji
     */
    public Jate(int[] vaihteluvali, Jatelaji jatelaji){
        this.paino = new Normal((vaihteluvali[1] +vaihteluvali[0]) / vaihteluvali.length,10).sample();
        System.out.println("Paino Jäte-luokasta: " + paino);
        this.jatelaji = jatelaji;
    }

    /**
     * 
     * @return paino
     */
    public double getPaino() {
        return paino;
    }

    /**
     * 
     * @return jatelaji
     */
    public Jatelaji getJatelaji() {
        return jatelaji;
    }

    /**
     * 
     * @param paino
     */
    public void setPaino(double paino){
        this.paino = paino;
    }

    /**
     * @return merkkijonoesitys
     */
    @Override
    public String toString() {
        return "Jate{" +
                "paino=" + paino +
                ", jatelaji=" + jatelaji +
                '}';
    }
}
