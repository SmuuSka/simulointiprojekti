package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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

    public TableView<SimulaatioData> getTABLE_VIEW_DATA() {
        return TABLE_VIEW_DATA;
    }

    public Label getTULOKSET_HEITETTY_ELEKTRO() {
        return TULOKSET_HEITETTY_ELEKTRO;
    }

    public Label getTULOKSET_HEITETTY_PALAMATON() {
        return TULOKSET_HEITETTY_PALAMATON;
    }

    public Label getTULOKSET_HEITETTY_PALAVA() {
        return TULOKSET_HEITETTY_PALAVA;
    }

    public Label getTULOKSET_INPUT_AIKA() {
        return TULOKSET_INPUT_AIKA;
    }

    public Label getTULOKSET_INPUT_AIKA_PER_KG() {
        return TULOKSET_INPUT_AIKA_PER_KG;
    }

    public Label getTULOKSET_INPUT_AKTIIVISUUS() {
        return TULOKSET_INPUT_AKTIIVISUUS;
    }

    public Label getTULOKSET_INPUT_MAX_KG() {
        return TULOKSET_INPUT_MAX_KG;
    }

    public Label getTULOKSET_INPUT_MIN_KG() {
        return TULOKSET_INPUT_MIN_KG;
    }

    public Label getTULOKSET_INPUT_PROSENTTI_ELEKTRO() {
        return TULOKSET_INPUT_PROSENTTI_ELEKTRO;
    }

    public Label getTULOKSET_INPUT_PROSENTTI_PALAMATON() {
        return TULOKSET_INPUT_PROSENTTI_PALAMATON;
    }

    public Label getTULOKSET_INPUT_PROSENTTI_PALAVA() {
        return TULOKSET_INPUT_PROSENTTI_PALAVA;
    }

    public Label getTULOKSET_INPUT_VIIVE() {
        return TULOKSET_INPUT_VIIVE;
    }

    public Label getTULOKSET_SIMUAIKA() {
        return TULOKSET_SIMUAIKA;
    }

    public Label getTULOKSET_AKTIIVISUUSYHT_ELEKTRO() {
        return TULOKSET_AKTIIVISUUSYHT_ELEKTRO;
    }

    public Label getTULOKSET_AKTIIVISUUSYHT_PALAMATON() {
        return TULOKSET_AKTIIVISUUSYHT_PALAMATON;
    }

    public Label getTULOKSET_AKTIIVISUUSYHT_PALAVA() {
        return TULOKSET_AKTIIVISUUSYHT_PALAVA;
    }

    public Label getTULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE() {
        return TULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE;
    }

    public Label getTULOKSET_ASIAKASPER_AIKAYKSIKKO() {
        return TULOKSET_ASIAKASPER_AIKAYKSIKKO;
    }

    public Label getTULOKSET_KAYTTOASTE_ELEKTROPISTE() {
        return TULOKSET_KAYTTOASTE_ELEKTROPISTE;
    }

    public Label getTULOKSET_KAYTTOASTE_PALAMATONPISTE() {
        return TULOKSET_KAYTTOASTE_PALAMATONPISTE;
    }

    public Label getTULOKSET_KAYTTOASTE_PALAVAPISTE() {
        return TULOKSET_KAYTTOASTE_PALAVAPISTE;
    }

    public Label getTULOKSET_KAYTTOASTE_SAAPUMISPISTE() {
        return TULOKSET_KAYTTOASTE_SAAPUMISPISTE;
    }

    public Label getTULOKSET_KESKLAPIMENO_ELEKTRO() {
        return TULOKSET_KESKLAPIMENO_ELEKTRO;
    }

    public Label getTULOKSET_KESKLAPIMENO_PALAMATON() {
        return TULOKSET_KESKLAPIMENO_PALAMATON;
    }

    public Label getTULOKSET_KESKLAPIMENO_PALAVA() {
        return TULOKSET_KESKLAPIMENO_PALAVA;
    }

    public Label getTULOKSET_KESKLAPIMENO_SAAPUMINEN() {
        return TULOKSET_KESKLAPIMENO_SAAPUMINEN;
    }

    public Label getTULOKSET_KESKJÄTEMAARA_APKJ() {
        return TULOKSET_KESKJÄTEMAARA_APKJ;
    }

    public Label getTULOKSET_KESKMAARA_JONO_ELEKTROPISTE() {
        return TULOKSET_KESKMAARA_JONO_ELEKTROPISTE;
    }

    public Label getTULOKSET_KESKMAARA_JONO_PALAMATONPISTE() {
        return TULOKSET_KESKMAARA_JONO_PALAMATONPISTE;
    }

    public Label getTULOKSET_KESKMAARA_JONO_PALAVAPISTE() {
        return TULOKSET_KESKMAARA_JONO_PALAVAPISTE;
    }

    public Label getTULOKSET_KESKMAARA_JONO_SAAPUMISPISTE() {
        return TULOKSET_KESKMAARA_JONO_SAAPUMISPISTE;
    }

    public Label getTULOKSET_KESKPALVELUAIKA_ELEKTRO() {
        return TULOKSET_KESKPALVELUAIKA_ELEKTRO;
    }

    public Label getTULOKSET_KESKPALVELUAIKA_PALAMATON() {
        return TULOKSET_KESKPALVELUAIKA_PALAMATON;
    }

    public Label getTULOKSET_KESKPALVELUAIKA_PALAVA() {
        return TULOKSET_KESKPALVELUAIKA_PALAVA;
    }

    public Label getTULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE() {
        return TULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE;
    }

    public Label getTULOKSET_OLESKELUAIKA_ELEKTRO() {
        return TULOKSET_OLESKELUAIKA_ELEKTRO;
    }

    public Label getTULOKSET_OLESKELUAIKA_PALAMATON() {
        return TULOKSET_OLESKELUAIKA_PALAMATON;
    }

    public Label getTULOKSET_OLESKELUAIKA_PALAVA() {
        return TULOKSET_OLESKELUAIKA_PALAVA;
    }

    public Label getTULOKSET_OLESKELUAIKA_SAAPUMINEN() {
        return TULOKSET_OLESKELUAIKA_SAAPUMINEN;
    }

    public Label getTULOKSET_PALVELTU_LKM() {
        return TULOKSET_PALVELTU_LKM;
    }

    public Label getTULOKSET_PALVELTU_LK_ELEKTRO() {
        return TULOKSET_PALVELTU_LK_ELEKTRO;
    }

    public Label getTULOKSET_PALVELTU_LK_PALAVA() {
        return TULOKSET_PALVELTU_LK_PALAVA;
    }

    public Label getTULOKSET_PALVELTU_LK_PALAMATON() {
        return TULOKSET_PALVELTU_LK_PALAMATON;
    }

    public Label getTULOKSET_PALVELTU_LK_SAAPUMINEN() {
        return TULOKSET_PALVELTU_LK_SAAPUMINEN;
    }

    public Label getTULOKSET_SAAPUNUT_LKM() {
        return TULOKSET_SAAPUNUT_LKM;
    }
}
