package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

/**
 * Rajapinta Mallista näkymään
 */

public interface IKontrolleriMtoV {
    /**
     * Tallentaa simulaatioajon tulokset
     * @param suureet OmaMoottorista tulevat suureet
     * @throws SQLException
     */
    void tallennaTulokset(Laskenta suureet) throws SQLException;
    /**
     * 
     * @return visualisointi
     */
    IVisualisointi getVisualisointi();
    /**
     * Asettaa käyttöliittymään elektroniikka jätelavan jonon pituuden
     * @param pituus OmaMoottorista tuleva jononpituus
     */
    void setEJononPituus(int pituus);
    /**
     * Asettaa käyttöliittymään palamattoman jätteen jätelavan jonon pituuden
     * @param pituus OmaMoottorista tuleva jononpituus
     */
    void setPTJononPituus(int pituus);
    /**
     * Asettaa käyttöliittymään palavan jätteen jätelavan jonon pituuden
     * @param pituus OmaMoottorista tuleva jononpituus
     */
    void setPJononPituus(int pituus);
    /**
     * Asettaa käyttöliittymään saapumisien määrän
     * @param pituus OmaMoottorista tuleva jononpituus
     */
    void setSAAPUMISJononPituus(int pituus);
    /**
     * @return ruuhkaisuus
     */
    double getAktiivisuus();
    /**
     * @return purkunopeus, eli aikayksikköä per kg
     */
    double getPurkuNopeus();
}
