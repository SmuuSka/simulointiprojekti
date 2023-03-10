package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

/**Simulaatori UI:n Interface**/
public interface ISimulaattoriUI {
    /**
     * Kun näytä tulokset nappia on painettu tai kun simulaatio on päätynyt, mennään tähän methodiin joka avaa tulokset ikkunan ja tulostaa tarvittavan datan.
     * @param dataColumn Tänne annetaal ArrayLista jossa on Tietokannan tulokset.
     */
    void showTulokset(ArrayList<SimulaatioData> dataColumn);
    /**
     * Poistaa tietokannasta valitun datan
     * @throws SQLException */
    void poistaData(int ID) throws SQLException;
    /**Hakee käyttäjän antaman syötteen, Syöte: Simulointiaika*/
    double getAika();
    /**Hakee käyttäjän antaman syötteen, Syöte: Viive(ms)*/
    long getViive();

    /**Hakee käyttäjän antaman syötteen, Syöte: MIN ja MAX kg määrä per asiakas*/
    int[] getVaihteluvali();
    /**Hakee käyttäjän antaman syötteen, Syöte: Jätelaijen prosentit*/
    int[] getJateLaijenProsentit();
    /**Hakee käyttäjän antaman syötteen, Syöte: Asiakkaan purku sekuneissa per KG*/
    Double getPurkuNopeus();
    /**Hakee käyttäjän antaman syötteen, Syöte: Ajeetanko simulointi loppuun?*/
    boolean getAjeetaankoLoppuun();
    /**Haetaan Visualisointi interface (Tällä haetaan visualisoinnista tarvittavat methodit jolla animoitaan visualisointia)*/
    IVisualisointi getVisualisointi();
    /**Asetetaan jonon pituus Elektroniikka jäte pisteelle visualisointia varten
     * @param pituus Annetaan tämän hetkinen Elektroniikka jäte palvelupisteen jonon pituus.
     * */
    void setEJateJonossa(int pituus);
    /**Asetetaan jonon pituus Palamattomalle jäte pisteelle visualisointia varten
     * @param pituus Annetaan tämän hetkinen Palamattoman jätteen palvelupisteen jonon pituus.
     * */
    void setPTJateJonossa(int pituus);
    /**Asetetaan jonon pituus Palava jäte pisteelle visualisointia varten
     * @param pituus Annetaan tämän hetkinen Palava jäte palvelupisteen jonon pituus.
     * */
    void setPJateJonossa(int pituus);
    /**Asetetaan jonon pituus Saapumis pisteelle visualisointia varten
     * @param pituus Annetaan tämän hetkinen Saapumis palvelupisteen jonon pituus.
     * */
    void setSAAPUMINENJonossa(int pituus);
    /**Asetetaan animaation nopeus
     * @param viive Annetaan Viiven aika jolla sitten säätellään animointi nopeutta.
     * */
    void setAnimaationViive(int viive);

    /**Asetetaan uusi teksti Aloita napille (Uusi teksti: Käynnistä uudelleen.)
     * */
    void setAloitaButtonText();
    /**Haetaan käyttäjän valitsema Aktiivisuus (Rauhallinen || Normaali || Ruuhka)
     * */
    String getAktiivisuus();
}
