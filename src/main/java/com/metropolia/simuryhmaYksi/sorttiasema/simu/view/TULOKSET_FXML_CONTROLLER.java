package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TULOKSET_FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;
    public TULOKSET_FXML_CONTROLLER(IKontrolleriVtoM kontrolleri) {
        this.kontrolleri = kontrolleri;

    }
    @FXML
    private Button TULOKSET_POISTANAPPI;
    @FXML
    private Label TULOKSET_HEITETTY_YHT;

    @FXML
    private Label TULOKSET_HEITETTY_ELEKTRO;

    @FXML
    private Label TULOKSET_HEITETTY_PALAMATON;

    @FXML
    private Label TULOKSET_HEITETTY_PALAVA;
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
}
