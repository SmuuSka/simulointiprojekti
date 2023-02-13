package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {

    private Button aloitaButton,nopeutaButton,hidastaButton;

    private IKontrolleriVtoM kontrolleri;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
        kontrolleri = new Kontrolleri(this);

    }
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            FXML_CONTROLLER FXMLcontroller = new FXML_CONTROLLER(kontrolleri);
            loader.setController(FXMLcontroller);
            Parent root = loader.load();

            //Hae Napit FXML CONTROLLERISTA
            aloitaButton = FXMLcontroller.getBUTTON_ALOITA();
            hidastaButton = FXMLcontroller.getBUTTON_HITAAMMIN();
            nopeutaButton = FXMLcontroller.getBUTTON_NOPEAMMIN();

            aloitaButton.setOnAction(event ->{
                FXMLcontroller.aloitaSimulaatio(event);
            });

            hidastaButton.setOnAction(event ->{
                FXMLcontroller.hidastaSimulaatio(event);
            });

            nopeutaButton.setOnAction(event ->{
                FXMLcontroller.nopeutaSimulaatio(event);
            });


            Scene scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.setTitle("Sortti-Asema Simu");

            primaryStage.show();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getAika() {
        return 0;
    }

    @Override
    public long getViive() {
        return 0;
    }

    @Override
    public void setLoppuaika() {

    }

    @Override
    public IVisualisointi getVisualisointi() {
        return null;
    }

}
