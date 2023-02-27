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

}
