package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {

    private Button aloitaButton,nopeutaButton,hidastaButton,strategiaButton;

    private TextField simulointiAikaInput,asiakasJateMIN_INPUT,asiakasJateMAX_INPUT,elektroniikkaJatePROSENTTI,palavaJatePROSENTTI,palamatonJatePROSENTTI,asiakasPurku_KG_Sekunti;

    private CheckBox RauhallinenAktiivisuus;
    private CheckBox NormaaliAktiivisuus;
    private CheckBox RuuhkainenAktiivisuus;
    private Scene scene;

    private FXML_CONTROLLER FXMLcontroller;
    private Parent root;
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
            FXMLLoader loaderSIMU = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            FXMLcontroller = new FXML_CONTROLLER(kontrolleri);
            loaderStrategia.setController(FXMLcontroller);

             root = loaderStrategia.load();

            //Hae Napit FXML CONTROLLERISTA
            aloitaButton = FXMLcontroller.getBUTTON_ALOITA();
            hidastaButton = FXMLcontroller.getBUTTON_HITAAMMIN();
            nopeutaButton = FXMLcontroller.getBUTTON_NOPEAMMIN();
            strategiaButton = FXMLcontroller.getSTRATEGIA_SIIRY_SIMULAATIOON();

            //Hae TextFields FXML CONTROLLERISTA
            simulointiAikaInput = FXMLcontroller.getSTRATEGIA_SIMULOINTIAIKA();
            asiakasJateMIN_INPUT = FXMLcontroller.getSTRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ();
            asiakasJateMAX_INPUT = FXMLcontroller.getSTRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ();
            asiakasPurku_KG_Sekunti = FXMLcontroller.getSTRATEGIA_KGMAARA_SEKUNTEJA();
            elektroniikkaJatePROSENTTI = FXMLcontroller.getSTRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ();
            palavaJatePROSENTTI = FXMLcontroller.getSTRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ();
            palamatonJatePROSENTTI= FXMLcontroller.getSTRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ();

            //Hae Checkboxit FXML CONTROLLERISTA
            RauhallinenAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_RAUHALLINEN_CHECK();
            NormaaliAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_NORMAALIA_CHECK();
            RuuhkainenAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_RUUHKA_CHECK();



            scene = new Scene(root);

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
            }catch(Exception e){
                System.out.println("Pääsimulaatorin napit ei vielä käytössä koska strategia ikkuna on auki");
            }


            //Siiry PÄÄSIMULAATIO IKKUNAAN
            strategiaButton.setOnAction(event ->{

                loaderSIMU.setController(FXMLcontroller);
                try{
                     root = loaderSIMU.load();
                    scene = new Scene(root);

                    primaryStage.setScene(scene);

                    primaryStage.setTitle("Sortti-Asema Simu");

                    primaryStage.show();

                }catch(IOException er){
                    System.out.println("PÄÄSIMULAATIO ei ladannut oikein.");
                    er.printStackTrace();

                }

            });

            primaryStage.setScene(scene);

            primaryStage.setTitle("Sortti-Asema Simu");

            primaryStage.show();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getAika() {
        return Double.parseDouble(simulointiAikaInput.getText());
    }

    @Override
    public long getViive() {
        return 0;
    }

    @Override
    public double getAsiakasJäteMin() {
        return Double.parseDouble(asiakasJateMIN_INPUT.getText());
    }

    @Override
    public double getAsiakasJäteMax() {
        return Double.parseDouble(asiakasJateMAX_INPUT.getText());
    }

    @Override
    public double getAsiakasKgPerSekunti() {
        return Double.parseDouble(asiakasPurku_KG_Sekunti.getText());
    }

    @Override
    public int getJatelajiProsenttiELEKTRO() {
        return Integer.parseInt(elektroniikkaJatePROSENTTI.getText());
    }

    @Override
    public int getJatelajiProsenttiPALAVA() {
        return Integer.parseInt(palavaJatePROSENTTI.getText());
    }

    @Override
    public int getJatelajiProsenttiPALAMATON() {
        return Integer.parseInt(palamatonJatePROSENTTI.getText());
    }

    @Override
    public int getRuuhkaAika() {
        return 0;
    }

    @Override
    public int getStrategiaTapahtumat() {
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
