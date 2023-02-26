package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class TULOKSET_FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;
    public TULOKSET_FXML_CONTROLLER(IKontrolleriVtoM kontrolleri) {
        this.kontrolleri = kontrolleri;

    }
    @FXML
    private TableColumn<?, ?> COLUMN_PAIVAMAARA_TULOKSET;

    @FXML
    private TableColumn<?, ?> ID_COLUMN_TULOKSET;

    @FXML
    private Button TULOKSET_POISTANAPPI;

    public Button getTULOKSET_POISTANAPPI() {
        return TULOKSET_POISTANAPPI;
    }
}
