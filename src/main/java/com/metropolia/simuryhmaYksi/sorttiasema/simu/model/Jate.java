package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import com.metropolia.simuryhmaYksi.sorttiasema.eduni.distributions.Normal;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

/**
 * Jäte-luokka, jossa määritellään jätteen ominaisuudet
 */
public class Jate {
    /**
     * Jätteen paino
     */
    private double paino;
    /**
     * Jätteen laji
     */
    private Jatelaji jatelaji;

    /**
     * Konstruktori, joka luo jätteen
     * @param jatelaji Jätegeneraattorista tuleva jätelaji
     * @param maara Jätegeneraattorista tuleva jätemäärä
     */
    public Jate(Jatelaji jatelaji, double maara){
        this.paino = maara;
        this.jatelaji = jatelaji;
    }

    /**
     * Getteri jätteen painolle
     * @return palauttaa jätteen painon
     */
    public double getPaino() {
        return paino;
    }

    /**
     * Getteri jäte-olin jätelajille
     * @return jatelaji
     */
    public Jatelaji getJatelaji() {
        return jatelaji;
    }

    /**
     * Setteri jätteen painolle
     * @param paino tuleva Jätegeneraattorista määrä
     */
    public void setPaino(double paino){
        this.paino = paino;
    }

    /**
     *
     * @return Palauttaa jäte-oliosta merkkijonoesityksen
     */
    @Override
    public String toString() {
        return "Jate{" +
                "paino=" + paino +
                ", jatelaji=" + jatelaji +
                '}';
    }
}
