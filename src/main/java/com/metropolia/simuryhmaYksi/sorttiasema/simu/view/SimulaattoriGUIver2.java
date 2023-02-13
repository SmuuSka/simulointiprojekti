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

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {

    private Button aloitaButton,nopeutaButton,hidastaButton,strategiaButtonOK;
    private Parent root;
    private Scene scene;
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
            FXMLLoader loaderStrategia = new FXMLLoader(getClass().getResource("/uifxml/Strategia.fxml"));
            FXMLLoader loadersimu = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            FXML_CONTROLLER FXMLcontroller = new FXML_CONTROLLER(kontrolleri);
            loaderStrategia.setController(FXMLcontroller);
            loadersimu.setController(FXMLcontroller);
             root = loaderStrategia.load();

            //Hae Napit FXML CONTROLLERISTA
            aloitaButton = FXMLcontroller.getBUTTON_ALOITA();
            hidastaButton = FXMLcontroller.getBUTTON_HITAAMMIN();
            nopeutaButton = FXMLcontroller.getBUTTON_NOPEAMMIN();
            strategiaButtonOK = FXMLcontroller.getSTRATEGIA_SIIRY_SIMULAATIOON();
            try {
                aloitaButton.setOnAction(event -> {
                    FXMLcontroller.aloitaSimulaatio(event);
                });

                hidastaButton.setOnAction(event -> {
                    FXMLcontroller.hidastaSimulaatio(event);
                });

                nopeutaButton.setOnAction(event -> {
                    FXMLcontroller.nopeutaSimulaatio(event);
                });
            }catch(Exception er){
                System.out.println("Pääsimulaatorin napit ei vielä käytössä.");
            }

            strategiaButtonOK.setOnAction(event ->{
                try {
                    root = loadersimu.load();
                    System.out.println("Vaihettu");
                    scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Sortti-Asema Simu");
                    primaryStage.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });

            scene = new Scene(root);

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
