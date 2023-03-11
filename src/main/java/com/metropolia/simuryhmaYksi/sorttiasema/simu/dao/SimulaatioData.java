package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 *
 * SimulaatioData-luokassa käsitellään tietokannasta haettua tietoa ja tehdään tuloksista tulosolio.
 */

public class SimulaatioData{
    /**
     * Simulaatiotuloksen id
     */
    private final IntegerProperty id;
    /**
     * Simulaatiotuloksen totuusarvomuuttuja tiedolle, ajetaanko simulaattori tyhjäksi
     */
    private final BooleanProperty simulaatioTyhjaksi;
    /**
     * Simulaattorituloksen päivämäärä
     */
    private final ObjectProperty<LocalDate> paivamaara;
    /**
     * Simulaattorin parametritaulun tiedot
     */
    private SimulaationParametrit parametrit;
    /**
     * Simulaattorin tulostaulun tiedot
     */
    private SimulaattorinTulokset tulokset;

    /**
     * Konstruktori simulaation tuloksille
     * @param id Simulaation id
     * @param paivamaara Simulaation päivämäärä
     * @param simulaatioTyhjaksi Ajetaanko simulaattori tyhjäksi
     */
    public SimulaatioData(int id,LocalDate paivamaara, int simulaatioTyhjaksi){
        this.id = new SimpleIntegerProperty(id);
        this.paivamaara = new SimpleObjectProperty<LocalDate>(paivamaara);
        if(simulaatioTyhjaksi == 0){
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(false);
        }else {
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(true);
        }
    }

    /**
     * Getteri simulaation parametreille
     * @return palauttaa simulaation parametrit
     */
    public SimulaationParametrit getParametrit() {
        return parametrit;
    }

    /**
     * Setteri parametri-oliolle
     * @param parametrit parametriolio
     */
    public void setParametrit(SimulaationParametrit parametrit) {
        this.parametrit = parametrit;
    }

    /**
     * Getteri simulaattorin tuloksille
     * @return palauttaa simulaation tulokset
     */
    public SimulaattorinTulokset getTulokset() {
        return tulokset;
    }

    /**
     * Setteri tulokset-oliolle
     * @param tulokset tulosolio
     */
    public void setTulokset(SimulaattorinTulokset tulokset) {
        this.tulokset = tulokset;
    }

    /**
     * Getteri simulaation id
     * @return palauttaa simulaation id:n
     */
    public int getId() {
        return id.get();
    }

    /**
     * Getteri simulaation id
     * @return Palauttaa JavaFx vaatiman IntegerPropertyn simulaation id:stä
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     * Getteri ajetaanko simulaattori tyhjäksi totuusarvolle
     * @return palauttaa ajetaanko tyhjäksi
     */
    public boolean isSimulaatioTyhjaksi() {
        return simulaatioTyhjaksi.get();
    }

    /**
     * Getteri ajetaanko simulaattori tyhjäksi totuusarvolle
     * @return Palauttaa JavaFx vaatiman BooleanProperty-tyyppisen simulaation tyhjennyksestä
     */
    public BooleanProperty simulaatioTyhjaksiProperty() {
        return simulaatioTyhjaksi;
    }

    /**
     * Getteri simulaation päivämäärälle
     * @return palauttaa simulaation päivämäärän
     */
    public LocalDate getPaivamaara() {
        return paivamaara.get();
    }

    /**
     * Getteri simulaation päivämäärälle
     * @return palauttaa JavaFx vaatiman simulaation päivämäärän ObjectProperty-tyyppinä
     */
    public ObjectProperty<LocalDate> paivamaaraProperty() {
        return paivamaara;
    }

    /**
     *
     * @return Palauttaa merkkijonoesityksen simulaatiodatasta
     */
    @Override
    public String toString() {
        return "SimulaatioData{" +
                "id=" + id +
                ", simulaatioTyhjaksi=" + simulaatioTyhjaksi +
                ", paivamaara=" + paivamaara +
                ", parametrit=" + parametrit +
                ", tulokset=" + tulokset +
                '}';
    }

    /**
     * SimulaatioDatan sisäluokka,
     * jossa käsitellään parametrit-taulun tietoa tietokannasta
     */
    public class SimulaationParametrit{

        /**
         * Muuttujat kokonaislukuparametreille
         */
        private final IntegerProperty vmin, vmax, jateTE, jateTPJ, jateTPNJ;
        /**
         * Muuttujat liukulukuparametreille
         */
        private final DoubleProperty aika, purkunopeus, viive;
        /**
         * Simulaation aktiivisuusparametri
         */
        private final StringProperty aktiivisuus;

        /**
         * Konstruktori simulaation parametreille
         *
         * @param aika tietokannasta tuleva aika
         * @param viive tietokannasta tuleva viive
         * @param purkunopeus tietokannasta tuleva purkunopeus
         * @param vmin tietokannasta tuleva vaihteluväli minimimäärä
         * @param vmax tietokannasta tuleva vaihteluväli maksimimäärä
         * @param jateTE tietokannasta tuleva elektroniikka jätteen todennäköisyys
         * @param jateTPJ tietokannasta tuleva palavan jätteen todennäköisyys
         * @param jateTPNJ tietokannasta tuleva aika palamattoman jätteen todennäköisyydet
         * @param aktiivisuus tietokannasta tuleva aktiivisuus
         */
        public SimulaationParametrit(double aika,double viive, double purkunopeus, int vmin, int vmax,int jateTE, int jateTPJ, int jateTPNJ, String aktiivisuus){
            this.aika = new SimpleDoubleProperty(aika);
            this.viive = new SimpleDoubleProperty(viive);
            this.purkunopeus = new SimpleDoubleProperty(purkunopeus);
            this.vmin = new SimpleIntegerProperty(vmin);
            this.vmax = new SimpleIntegerProperty(vmax);
            this.jateTE = new SimpleIntegerProperty(jateTE);
            this.jateTPJ = new SimpleIntegerProperty(jateTPJ);
            this.jateTPNJ = new SimpleIntegerProperty(jateTPNJ);
            this.aktiivisuus = new SimpleStringProperty(aktiivisuus);

        }

        /**
         *
         * @return palauttaa merkkijonoesityksen simulaation parametreistä
         */
        @Override
        public String toString() {
            return "SimulaationParametrit{" +
                    "vmin=" + vmin +
                    ", vmax=" + vmax +
                    ", jateTE=" + jateTE +
                    ", jateTPJ=" + jateTPJ +
                    ", jateTPNJ=" + jateTPNJ +
                    ", aika=" + aika +
                    ", purkunopeus=" + purkunopeus +
                    ", viive=" + viive +
                    ", aktiivisuus=" + aktiivisuus +
                    '}';
        }

        /**
         * Getteri simulaation aktiivisuudelle
         * @return palauttaa aktiivisuuden
         */
        public String getAktiivisuus() {
            return aktiivisuus.get();
        }

        /**
         * Getteri simulaation aktiivisuudelle
         * @return palauttaa JavaFx vaatiman aktiivisuuden StringProperty-tyyppinä
         */
        public StringProperty aktiivisuusProperty() {
            return aktiivisuus;
        }

        /**
         * Getteri simulaation vaihteluväli minimimäärälle
         * @return palauttaa minimi vaihteluvälin
         */
        public int getVmin() {
            return vmin.get();
        }

        /**
         * Getteri simulaation vaihteluväli minimimäärälle
         * @return palauttaa JavaFx vaatiman minimi vaihteluvälin IntegerProperty-tyyppisenä
         */
        public IntegerProperty vminProperty() {
            return vmin;
        }

        /**
         * Getteri simulaation vaihteluväli maksimimäärälle
         * @return palauttaa maksimi vaihteluvälin
         */
        public int getVmax() {
            return vmax.get();
        }
        /**
         * Getteri simulaation vaihteluväli maksimimäärälle
         * @return palauttaa maksimi vaihteluvälin IntegerProperty-tyyppisenä
         */
        public IntegerProperty vmaxProperty() {
            return vmax;
        }

        /**
         * Getteri elektroniikka jätteen todennäköisyydelle
         * @return palauttaa elektroniikka jätteen todennäköisyyden
         */
        public int getJateTE() {
            return jateTE.get();
        }
        /**
         * Getteri elektroniikka jätteen todennäköisyydelle
         * @return palauttaa elektroniikka jätteen todennäköisyyden IntegerProperty-tyyppisenä
         */

        public IntegerProperty jateTEProperty() {
            return jateTE;
        }
        /**
         * Getteri palavan jätteen todennäköisyydelle
         * @return palauttaa palavan jätteen todennäköisyyden
         */

        public int getJateTPJ() {
            return jateTPJ.get();
        }
        /**
         * Getteri palavan jätteen todennäköisyydelle
         * @return palauttaa palavan jätteen todennäköisyyden IntegerProperty-tyyppisenä
         */
        public IntegerProperty jateTPJProperty() {
            return jateTPJ;
        }
        /**
         * Getteri palamattoman jätteen todennäköisyydelle
         * @return palauttaa palamattoman jätteen todennäköisyyden
         */
        public int getJateTPNJ() {
            return jateTPNJ.get();
        }
        /**
         * Getteri palamattoman jätteen todennäköisyydelle
         * @return palauttaa palamattoman jätteen todennäköisyyden IntegerProperty-tyyppisenä
         */
        public IntegerProperty jateTPNJProperty() {
            return jateTPNJ;
        }

        /**
         * Getteri simulaation ajalle
         * @return palauttaa simulaation ajan
         */

        public double getAika() {
            return aika.get();
        }

        /**
         * Getteri simulaation ajalle
         * @return palauttaa simulaation ajan DoubleProperty-tyyppisenä
         */
        public DoubleProperty aikaProperty() {
            return aika;
        }

        /**
         * Getteri purkuajalle
         * @return paluttaa simulaation purkuaikamääreen
         */

        public double getPurkunopeus() {
            return purkunopeus.get();
        }

        /**
         * Getteri purkuajalle
         * @return paluttaa simulaation purkuaikamääreen DoubleProperty-tyyppisenä
         */
        public DoubleProperty purkunopeusProperty() {
            return purkunopeus;
        }

        /**
         * Getteri simulaation viiveelle
         * @return palauttaa simulaation viiveen
         */
        public double getViive() {
            return viive.get();
        }

        /**
         * Getteri simulaation viiveelle
         * @return palauttaa simulaation viiveen DoubleProperty-tyyppisenä
         */
        public DoubleProperty viiveProperty() {
            return viive;
        }
    }


    /**
     * SimulaatioDatan sisäluokka,
     * jossa käsitellään tulostaulun tietoja tietokannasta
     */
    public class SimulaattorinTulokset{
        /**
         * Kokonaislukulista kokonaislukutuloksista
         */
        private ArrayList<SimpleIntegerProperty> tuloksetINT;
        /**
         * Liukulukulista liukulukutuloksista
         */
        private ArrayList<SimpleDoubleProperty> tuloksetDOUBLE;

        /**
         * Konstruktori simulaation tuloksille
         * @param tuloksetINT tietokannasta tulevat kokonaislukutulokset
         * @param tuloksetDOUBLE tietokannasta tulevat liukulukutulokset
         */
        SimulaattorinTulokset(ArrayList<SimpleIntegerProperty> tuloksetINT,ArrayList<SimpleDoubleProperty> tuloksetDOUBLE ){
            this.tuloksetINT = tuloksetINT;
            this.tuloksetDOUBLE = tuloksetDOUBLE;
        }

        /**
         * Getteri kokonaislukutuloksille
         * @return SimpleIntegerProperty-tyyppisen kokonaislukulistan
         */
        public ArrayList<SimpleIntegerProperty> getTuloksetINT() {
            return tuloksetINT;
        }

        /**
         * Getteri liukulukutuloksille
         * @return SimpleDoubleProperty-tyyppisen liukulukulistan
         */
        public ArrayList<SimpleDoubleProperty> getTuloksetDOUBLE() {
            return tuloksetDOUBLE;
        }

        /**
         *
         * @return palauttaa merkkijonoesityksen simulaation tuloksista
         */
        @Override
        public String toString() {
            return "SimulaattorinTulokset{" +
                    "tuloksetINT=" + tuloksetINT +
                    ", tuloksetDOUBLE=" + tuloksetDOUBLE +
                    '}';
        }
    }
}
