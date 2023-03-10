package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.fxml.FXML;
import javafx.scene.control.*;
/**
 * @author Kaspar Tullus
 */
/**
 * Täälä hoitetaan kaikki Strategia FXML getterit UI:ta varten
 */
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
    /**
     * FXML INPUT ELEMENTTI GETTERI (VIIVE)
     */
    public TextField getSTRATEGIA_SIMULOINTIVIIVE() {
        return STRATEGIA_SIMULOINTIVIIVE;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (NÄYTÄTULOKSET NAPPI)
     */
    public Button getSTRATEGIA_NAYTATULOKSET() {
        return STRATEGIA_NAYTATULOKSET;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (AKTIIVISUUS (onko ruuhkaa tai ei)
     */
    public ToggleGroup getAktiivisuusGroup() {
        return AktiivisuusGroup;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (ASIAKAAN MAKSIMI JÄTE)
     */
    public TextField getSTRATEGIA_ASIAKAS_MAX_JATEMAARA() {
        return STRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (ASIAKAAN MINIMI JÄTE)
     */
    public TextField getSTRATEGIA_ASIAKAS_MIN_JATEMAARA() {
        return STRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (NORMAALI RUUHKA CHECKBOX)
     */
    public RadioButton getSTRATEGIA_RUUHKA_NORMAALIA_CHECK() {
        return STRATEGIA_RUUHKA_NORMAALIA_CHECK;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (RAUHALLINEN RUUHKA CHECKBOX)
     */
    public RadioButton getSTRATEGIA_RUUHKA_RAUHALLINEN_CHECK() {
        return STRATEGIA_RUUHKA_RAUHALLINEN_CHECK;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (RUUHKA CHECKBOX)
     */
    public RadioButton getSTRATEGIA_RUUHKA_RUUHKA_CHECK() {
        return STRATEGIA_RUUHKA_RUUHKA_CHECK;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (SIMULOINTIAIKA)
     */
    public TextField getSTRATEGIA_SIMULOINTIAIKA() {
        return STRATEGIA_SIMULOINTIAIKA;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (ELEKTRONIIKKAJÄTE todenäköisyys %)
     */
    public TextField getSTRATEGIA_ELEKTRONIIKKAJATE_PROSENTTIMAARA() {
        return STRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (ASIAKAAN PURKU PER KG/SEKUNTI)
     */
    public TextField getSTRATEGIA_KGMAARA_SEKUNTEJA() {
        return STRATEGIA_KGMAARA_SEKUNTEJA;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (PALAMATON JÄTE todenäköisyys %)
     */
    public TextField getSTRATEGIA_PALAAMATONJATE_PROSENTTIMAARA() {
        return STRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (PALAVA JÄTE todenäköisyys %)
     */
    public TextField getSTRATEGIA_PALAVAJATE_PROSENTTIMAARA() {
        return STRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (OK NAPPI (siirytään simulaattoriin)
     */
    public Button getSTRATEGIA_SIIRY_SIMULAATIOON() {
        return STRATEGIA_SIIRY_SIMULAATIOON;
    }
    /**
     * FXML INPUT ELEMENTTI GETTERI (CHECKBOX AJA SIMULAATTORI LOPPUUN)
     */
    public CheckBox getSTRATEGIA_JONOT_AJALOPPUUN() {
        return STRATEGIA_JONOT_AJALOPPUUN;
    }
}
