package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
/**
 * @author Kaspar Tullus
 */
/**
 * Täälä hoitetaan kaikki Tuloksen näkymän FXML getterit UI:ta varten
 */
public class TULOKSET_FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;
    public TULOKSET_FXML_CONTROLLER(IKontrolleriVtoM kontrolleri) {
        this.kontrolleri = kontrolleri;

    }
    @FXML
    private Label TULOKSET_AKTIIVISUUSYHT_ELEKTRO;

    @FXML
    private Label TULOKSET_AKTIIVISUUSYHT_PALAMATON;

    @FXML
    private Label TULOKSET_AKTIIVISUUSYHT_PALAVA;

    @FXML
    private Label TULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE;

    @FXML
    private Label TULOKSET_ASIAKASPER_AIKAYKSIKKO;

    @FXML
    private Label TULOKSET_HEITETTY_ELEKTRO;

    @FXML
    private Label TULOKSET_HEITETTY_PALAMATON;

    @FXML
    private Label TULOKSET_HEITETTY_PALAVA;

    @FXML
    private Label TULOKSET_HEITETTY_YHT;

    @FXML
    private Label TULOKSET_INPUT_AIKA;

    @FXML
    private Label TULOKSET_INPUT_AIKA_PER_KG;

    @FXML
    private Label TULOKSET_INPUT_AKTIIVISUUS;

    @FXML
    private Label TULOKSET_INPUT_MAX_KG;

    @FXML
    private Label TULOKSET_INPUT_MIN_KG;

    @FXML
    private Label TULOKSET_INPUT_PROSENTTI_ELEKTRO;

    @FXML
    private Label TULOKSET_INPUT_PROSENTTI_PALAMATON;

    @FXML
    private Label TULOKSET_INPUT_PROSENTTI_PALAVA;

    @FXML
    private Label TULOKSET_INPUT_VIIVE;

    @FXML
    private Label TULOKSET_KAYTTOASTE_ELEKTROPISTE;

    @FXML
    private Label TULOKSET_KAYTTOASTE_PALAMATONPISTE;

    @FXML
    private Label TULOKSET_KAYTTOASTE_PALAVAPISTE;

    @FXML
    private Label TULOKSET_KAYTTOASTE_SAAPUMISPISTE;

    @FXML
    private Label TULOKSET_KESKJÄTEMAARA_APKJ;

    @FXML
    private Label TULOKSET_KESKLAPIMENO_ELEKTRO;

    @FXML
    private Label TULOKSET_KESKLAPIMENO_PALAMATON;

    @FXML
    private Label TULOKSET_KESKLAPIMENO_PALAVA;

    @FXML
    private Label TULOKSET_KESKLAPIMENO_SAAPUMINEN;
    @FXML
    private Label TULOKSET_KESKMAARA_JONO_ELEKTROPISTE;

    @FXML
    private Label TULOKSET_KESKMAARA_JONO_PALAMATONPISTE;

    @FXML
    private Label TULOKSET_KESKMAARA_JONO_PALAVAPISTE;

    @FXML
    private Label TULOKSET_KESKMAARA_JONO_SAAPUMISPISTE;

    @FXML
    private Label TULOKSET_KESKPALVELUAIKA_ELEKTRO;

    @FXML
    private Label TULOKSET_KESKPALVELUAIKA_PALAMATON;

    @FXML
    private Label TULOKSET_KESKPALVELUAIKA_PALAVA;

    @FXML
    private Label TULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE;

    @FXML
    private Label TULOKSET_OLESKELUAIKA_ELEKTRO;

    @FXML
    private Label TULOKSET_OLESKELUAIKA_PALAMATON;

    @FXML
    private Label TULOKSET_OLESKELUAIKA_PALAVA;

    @FXML
    private Label TULOKSET_OLESKELUAIKA_SAAPUMINEN;

    @FXML
    private Label TULOKSET_PALVELTU_LKM;

    @FXML
    private Label TULOKSET_PALVELTU_LK_ELEKTRO;
    @FXML
    private Label TULOKSET_PALVELTU_LK_PALAVA;

    @FXML
    private Label TULOKSET_PALVELTU_LK_PALAMATON;

    @FXML
    private Label TULOKSET_PALVELTU_LK_SAAPUMINEN;

    @FXML
    private Button TULOKSET_POISTANAPPI;
    @FXML

    private Button TULOKSET_POISTAKAIKKI;
    @FXML
    private Label TULOKSET_SAAPUNUT_LKM;

    @FXML
    private Label TULOKSET_SIMUAIKA;

    public Button getTULOKSET_POISTANAPPI() {
        return TULOKSET_POISTANAPPI;
    }

    public Label getTULOKSET_HEITETTY_YHT() {
        return TULOKSET_HEITETTY_YHT;
    }
    @FXML
    private TableView<SimulaatioData> TABLE_VIEW_DATA;
    /**
     * paikka mihin kaikki tuloksen columit tulee ja tuloksien valinnat.
     */
    public TableView<SimulaatioData> getTABLE_VIEW_DATA() {
        return TABLE_VIEW_DATA;
    }
    /**
     *  Haetaan pois heitetty jäte Elementti ELEKTRONIIKKA JÄTE pisteellä
     */
    public Label getTULOKSET_HEITETTY_ELEKTRO() {
        return TULOKSET_HEITETTY_ELEKTRO;
    }
    /**
     *  Haetaan pois heitetty jäte Elementti PALAMATON JÄTE pisteellä
     */
    public Label getTULOKSET_HEITETTY_PALAMATON() {
        return TULOKSET_HEITETTY_PALAMATON;
    }
    /**
     *  Haetaan pois heitetty jäte Elementti PALAVA JÄTE pisteellä
     */
    public Label getTULOKSET_HEITETTY_PALAVA() {
        return TULOKSET_HEITETTY_PALAVA;
    }
    /**
     *  Haetaan käyttäjän syöttämä SimulaatioAika tulos Label
     */
    public Label getTULOKSET_INPUT_AIKA() {
        return TULOKSET_INPUT_AIKA;
    }
    /**
     *  Haetaan käyttäjän syöttämä Purku aika per kg tulos Label
     */
    public Label getTULOKSET_INPUT_AIKA_PER_KG() {
        return TULOKSET_INPUT_AIKA_PER_KG;
    }
    /**
     * Poista kaikki data nappi.
     */
    public Button getTULOKSET_POISTAKAIKKI() {
        return TULOKSET_POISTAKAIKKI;
    }
    /**
     * Haetaan käyttäjän valitsema aktiivisuus aika per kg tulos Label.
     */
    public Label getTULOKSET_INPUT_AKTIIVISUUS() {
        return TULOKSET_INPUT_AKTIIVISUUS;
    }
    /**
     * Haetaan käyttäjän Syöttämä MAX KG per asiakas tulos Label.
     */
    public Label getTULOKSET_INPUT_MAX_KG() {
        return TULOKSET_INPUT_MAX_KG;
    }
    /**
     * Haetaan käyttäjän Syöttämä MIN KG per asiakas tulos Label.
     */
    public Label getTULOKSET_INPUT_MIN_KG() {
        return TULOKSET_INPUT_MIN_KG;
    }
    /**
     * Haetaan käyttäjän Syöttämä ELEKTRONIIKKA JÄTE prosentti tulos Label.
     */
    public Label getTULOKSET_INPUT_PROSENTTI_ELEKTRO() {
        return TULOKSET_INPUT_PROSENTTI_ELEKTRO;
    }
    /**
     * Haetaan käyttäjän Syöttämä PALAMATON JÄTE prosentti tulos Label.
     */
    public Label getTULOKSET_INPUT_PROSENTTI_PALAMATON() {
        return TULOKSET_INPUT_PROSENTTI_PALAMATON;
    }
    /**
     * Haetaan käyttäjän Syöttämä PALAVA JÄTE prosentti tulos Label.
     */
    public Label getTULOKSET_INPUT_PROSENTTI_PALAVA() {
        return TULOKSET_INPUT_PROSENTTI_PALAVA;
    }
    /**
     * Haetaan käyttäjän Syöttämä Viive tulos Label.
     */
    public Label getTULOKSET_INPUT_VIIVE() {
        return TULOKSET_INPUT_VIIVE;
    }
    /**
     * Simulaatorin simulointi aika tulos Label.
     */
    public Label getTULOKSET_SIMUAIKA() {
        return TULOKSET_SIMUAIKA;
    }
    /**
     * Simulaatorin Palvelupisteen aktiivisuus ELEKTRO JÄTE tulos Label.
     */
    public Label getTULOKSET_AKTIIVISUUSYHT_ELEKTRO() {
        return TULOKSET_AKTIIVISUUSYHT_ELEKTRO;
    }
    /**
     * Simulaatorin Palvelupisteen aktiivisuus PALAMATON JÄTE tulos Label.
     */
    public Label getTULOKSET_AKTIIVISUUSYHT_PALAMATON() {
        return TULOKSET_AKTIIVISUUSYHT_PALAMATON;
    }
    /**
     * Simulaatorin Palvelupisteen aktiivisuus PALAVA JÄTE tulos Label.
     */
    public Label getTULOKSET_AKTIIVISUUSYHT_PALAVA() {
        return TULOKSET_AKTIIVISUUSYHT_PALAVA;
    }
    /**
     * Simulaatorin Palvelupisteen aktiivisuus SAAPUMINEN tulos Label.
     */
    public Label getTULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE() {
        return TULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE;
    }
    /**
     * Simulaatorin asiakas per aikayksikkö tulos Label
     */
    public Label getTULOKSET_ASIAKASPER_AIKAYKSIKKO() {
        return TULOKSET_ASIAKASPER_AIKAYKSIKKO;
    }
    /**
     * Simulaatorin Käyttöaste Yhteensä tulos Label
     */
    public Label getTULOKSET_KAYTTOASTE_ELEKTROPISTE() {
        return TULOKSET_KAYTTOASTE_ELEKTROPISTE;
    }
    /**
     * Simulaatorin Käyttöaste PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_KAYTTOASTE_PALAMATONPISTE() {
        return TULOKSET_KAYTTOASTE_PALAMATONPISTE;
    }
    /**
     * Simulaatorin Käyttöaste PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_KAYTTOASTE_PALAVAPISTE() {
        return TULOKSET_KAYTTOASTE_PALAVAPISTE;
    }
    /**
     * Simulaatorin Käyttöaste SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_KAYTTOASTE_SAAPUMISPISTE() {
        return TULOKSET_KAYTTOASTE_SAAPUMISPISTE;
    }
    /**
     * Simulaatorin keskiläpimeno aika ELEKTRONIIKKA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKLAPIMENO_ELEKTRO() {
        return TULOKSET_KESKLAPIMENO_ELEKTRO;
    }
    /**
     * Simulaatorin keskiläpimeno aika PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_KESKLAPIMENO_PALAMATON() {
        return TULOKSET_KESKLAPIMENO_PALAMATON;
    }
    /**
     * Simulaatorin keskiläpimeno aika PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKLAPIMENO_PALAVA() {
        return TULOKSET_KESKLAPIMENO_PALAVA;
    }
    /**
     * Simulaatorin keskiläpimeno aika SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_KESKLAPIMENO_SAAPUMINEN() {
        return TULOKSET_KESKLAPIMENO_SAAPUMINEN;
    }
    /**
     * Simulaatorin kokonaismäärä keskimääräinen jätemäärä tulos Label
     */
    public Label getTULOKSET_KESKJATEMAARA_APKJ() {
        return TULOKSET_KESKJÄTEMAARA_APKJ;
    }
    /**
     * Simulaatorin keskimääräinen jononpituus ELEKTRONIIKKA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKMAARA_JONO_ELEKTROPISTE() {
        return TULOKSET_KESKMAARA_JONO_ELEKTROPISTE;
    }
    /**
     * Simulaatorin keskimääräinen jononpituus PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_KESKMAARA_JONO_PALAMATONPISTE() {
        return TULOKSET_KESKMAARA_JONO_PALAMATONPISTE;
    }
    /**
     * Simulaatorin keskimääräinen jononpituus PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKMAARA_JONO_PALAVAPISTE() {
        return TULOKSET_KESKMAARA_JONO_PALAVAPISTE;
    }
    /**
     * Simulaatorin keskimääräinen jononpituus SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_KESKMAARA_JONO_SAAPUMISPISTE() {
        return TULOKSET_KESKMAARA_JONO_SAAPUMISPISTE;
    }
    /**
     * Simulaatorin keskimääräinen palveluaika ELEKTRONIIKKA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKPALVELUAIKA_ELEKTRO() {
        return TULOKSET_KESKPALVELUAIKA_ELEKTRO;
    }
    /**
     * Simulaatorin keskimääräinen palveluaika PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_KESKPALVELUAIKA_PALAMATON() {
        return TULOKSET_KESKPALVELUAIKA_PALAMATON;
    }
    /**
     * Simulaatorin keskimääräinen palveluaika PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_KESKPALVELUAIKA_PALAVA() {
        return TULOKSET_KESKPALVELUAIKA_PALAVA;
    }
    /**
     * Simulaatorin keskimääräinen palveluaika SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE() {
        return TULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE;
    }
    /**
     * Simulaatorin oleskeluaika ELEKTRONIIKKA JÄTE tulos Label
     */
    public Label getTULOKSET_OLESKELUAIKA_ELEKTRO() {
        return TULOKSET_OLESKELUAIKA_ELEKTRO;
    }
    /**
     * Simulaatorin oleskeluaika PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_OLESKELUAIKA_PALAMATON() {
        return TULOKSET_OLESKELUAIKA_PALAMATON;
    }
    /**
     * Simulaatorin oleskeluaika PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_OLESKELUAIKA_PALAVA() {
        return TULOKSET_OLESKELUAIKA_PALAVA;
    }
    /**
     * Simulaatorin oleskeluaika SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_OLESKELUAIKA_SAAPUMINEN() {
        return TULOKSET_OLESKELUAIKA_SAAPUMINEN;
    }
    /**
     * Simulaatorin Palveltu asiakaat kokonaismäärä tulos Label
     */
    public Label getTULOKSET_PALVELTU_LKM() {
        return TULOKSET_PALVELTU_LKM;
    }
    /**
     * Simulaatorin Palveltu asiakaat ELEKTRONIIKKA JÄTE tulos Label
     */
    public Label getTULOKSET_PALVELTU_LK_ELEKTRO() {
        return TULOKSET_PALVELTU_LK_ELEKTRO;
    }
    /**
     * Simulaatorin Palveltu asiakaat PALAVA JÄTE tulos Label
     */
    public Label getTULOKSET_PALVELTU_LK_PALAVA() {
        return TULOKSET_PALVELTU_LK_PALAVA;
    }
    /**
     * Simulaatorin Palveltu asiakaat PALAMATON JÄTE tulos Label
     */
    public Label getTULOKSET_PALVELTU_LK_PALAMATON() {
        return TULOKSET_PALVELTU_LK_PALAMATON;
    }
    /**
     * Simulaatorin Palveltu asiakaat SAAPUMISPISTE tulos Label
     */
    public Label getTULOKSET_PALVELTU_LK_SAAPUMINEN() {
        return TULOKSET_PALVELTU_LK_SAAPUMINEN;
    }
    /**
     * Simulaatorin Saapuneita yhteensä tulos Label
     */
    public Label getTULOKSET_SAAPUNUT_LKM() {
        return TULOKSET_SAAPUNUT_LKM;
    }
}
