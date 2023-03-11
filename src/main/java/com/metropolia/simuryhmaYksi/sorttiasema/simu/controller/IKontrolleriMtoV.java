package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public interface IKontrolleriMtoV {
    /**
     * Tallentaa simulaatioajon tulokset
     * @param suureet
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
     * @param pituus
     */
    void setEJononPituus(int pituus);
    /**
     * Asettaa käyttöliittymään palamattoman jätteen jätelavan jonon pituuden
     * @param size
     */
    void setPTJononPituus(int size);
    /**
     * Asettaa käyttöliittymään palavan jätteen jätelavan jonon pituuden
     * @param size
     */
    void setPJononPituus(int size);
    /**
     * Asettaa käyttöliittymään saapumisien määrän
     * @param size
     */
    void setSAAPUMISJononPituus(int size);
    /**
     * @return ruuhkaisuus
     */
    double getAktiivisuus();
    /**
     * @return purkunopeus, eli aikayksikköä per kg
     */
    double getPurkuNopeus();
}
