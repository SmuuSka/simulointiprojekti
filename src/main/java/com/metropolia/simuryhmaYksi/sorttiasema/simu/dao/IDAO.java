package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 * Data Access Objectin rajapinta
 */

public interface IDAO {
     /**
      *
      * @param tyhjaksi = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan tieto, että ajetaanko simulaatio tyhjäksi.
      * @param aika = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan simulointiaika.
      * @param vaihteluvali = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan jätteiden vaihteluvälit.
      * @param jateprosentit = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan jätteiden todennäköisyydet.
      * @param viive = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan simulaattorin viive.
      * @param purkunopeus = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan, kuinka nopeasti simulaattori suorittaa jätteiden purkua.
      * @param aktiivisuus = UI:sta tuleva käyttäjäsyöte, jonka perusteella kirjataan tietokantaan, kuinka ruuhkaiseksi simulaattori päästetään.
      * @throws SQLException
      */
     void luoData(int tyhjaksi, double aika, int[] vaihteluvali, int[] jateprosentit, double viive, double purkunopeus, String aktiivisuus) throws SQLException;

     /**
      *
      * @param suureet tulevat model-pakkauksen Laskenta-luokasta, jossa simulaattorin data käsitellään halutuiksi suureiksi.
      * @throws SQLException
      */
     void paivitaData(Laskenta suureet) throws SQLException;

     /**
      *
      * @return palauttaa UI:lle listan simulaattorin tietokannasta.
      * @throws SQLException
      */
     ArrayList<SimulaatioData> simulaatioColumnData() throws SQLException;

     /**
      * Poistetaan kaikki tietokannassa olevat taulut
      * @throws SQLException
      */
     void poistaKaikkiTietokannanTaulut() throws SQLException;

     /**
      *
      * @param ID simulaattoridata id tulee UI:sta.
      * @return Palauttaa True, jos ollaan poistettu tulos onnistuneesti, muussa tapauksessa palautetaan False;
      * @throws SQLException
      */
     boolean poistaTiettyTulos(int ID) throws SQLException;

     /**
      * Sulkee tietokantayhteyden
      */
     void suljeYhteys();
}
