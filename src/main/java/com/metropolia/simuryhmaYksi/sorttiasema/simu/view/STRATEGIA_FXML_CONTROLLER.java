package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class STRATEGIA_FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;
    public STRATEGIA_FXML_CONTROLLER(IKontrolleriVtoM kontrolleri) {
        this.kontrolleri = kontrolleri;
    }

    ///STRATEGIA ELEMENTIT
    @FXML
    private TextField STRATEGIA_SIMULOINTIVIIVE;
    @FXML
    private Button STRATEGIA_NAYTATULOKSET;
    @FXML
    private ToggleGroup AktiivisuusGroup;

    @FXML
    private TextField STRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_KGMAARA_SEKUNTEJA;

    @FXML
    private TextField STRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private RadioButton STRATEGIA_RUUHKA_NORMAALIA_CHECK;

    @FXML
    private RadioButton STRATEGIA_RUUHKA_RAUHALLINEN_CHECK;

    @FXML
    private RadioButton STRATEGIA_RUUHKA_RUUHKA_CHECK;

    @FXML
    private Button STRATEGIA_SIIRY_SIMULAATIOON;
    @FXML
    private CheckBox STRATEGIA_JONOT_AJALOPPUUN;
    @FXML
    private TextField STRATEGIA_SIMULOINTIAIKA;

    /////////////
    ///STRATEGIA GETTERIT!

    public TextField getSTRATEGIA_SIMULOINTIVIIVE() {
        return STRATEGIA_SIMULOINTIVIIVE;
    }

    public Button getSTRATEGIA_NAYTATULOKSET() {
        return STRATEGIA_NAYTATULOKSET;
    }

    public ToggleGroup getAktiivisuusGroup() {
        return AktiivisuusGroup;
    }

    public TextField getSTRATEGIA_ASIAKAS_MAX_JATEMAARA() {
        return STRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ;
    }

    public TextField getSTRATEGIA_ASIAKAS_MIN_JATEMAARA() {
        return STRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ;
    }

    public RadioButton getSTRATEGIA_RUUHKA_NORMAALIA_CHECK() {
        return STRATEGIA_RUUHKA_NORMAALIA_CHECK;
    }

    public RadioButton getSTRATEGIA_RUUHKA_RAUHALLINEN_CHECK() {
        return STRATEGIA_RUUHKA_RAUHALLINEN_CHECK;
    }

    public RadioButton getSTRATEGIA_RUUHKA_RUUHKA_CHECK() {
        return STRATEGIA_RUUHKA_RUUHKA_CHECK;
    }

    public TextField getSTRATEGIA_SIMULOINTIAIKA() {
        return STRATEGIA_SIMULOINTIAIKA;
    }

    public TextField getSTRATEGIA_ELEKTRONIIKKAJATE_PROSENTTIMAARA() {
        return STRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ;
    }

    public TextField getSTRATEGIA_KGMAARA_SEKUNTEJA() {
        return STRATEGIA_KGMAARA_SEKUNTEJA;
    }

    public TextField getSTRATEGIA_PALAAMATONJATE_PROSENTTIMAARA() {
        return STRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ;
    }

    public TextField getSTRATEGIA_PALAVAJATE_PROSENTTIMAARA() {
        return STRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ;
    }

    public Button getSTRATEGIA_SIIRY_SIMULAATIOON() {
        return STRATEGIA_SIIRY_SIMULAATIOON;
    }

    public CheckBox getSTRATEGIA_JONOT_AJALOPPUUN() {
        return STRATEGIA_JONOT_AJALOPPUUN;
    }
}
