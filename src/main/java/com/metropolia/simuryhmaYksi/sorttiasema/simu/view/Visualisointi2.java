package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;


import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.sql.Time;

public class Visualisointi2 extends Canvas implements IVisualisointi {
    private int asiakasLkm = 0;
    private IKontrolleriVtoM kontrolleri;
    private FXML_CONTROLLER FXML_controller;

    private Pane AnimationPane;
    //----------------------------------ASIAKAS ELEMENTIT------------------------------------//

    //ASIAKAS ELEMENTTI.
    private ImageView ASIAKAS_NODE;

    //---------------------------------------------------------------------------------------------

    //------------------------------------JONOT------------------------------------//
    private HBox JONO_SAAPUMISPISTE;
    private HBox JONO_PALAVA;
    private HBox JONO_EPA;
    private HBox JONO_ELEKTRO;

    //JONO PALIKAT("JONOSSA OLEVAT VISUAALISESTI")
    private Rectangle JONOPALIKKA_SAAPUMINEN;
    private Rectangle JONOPALIKKA_PALAVA;
    private Rectangle JONOPALIKKA_EPA;
    private Rectangle JONOPALIKKA_ELEKTRO;
    //---------------------------------------------------------------------------------------------


    //------------------------------------Kulku reitit------------------------------------//

    //SAAPUMISESTA JÄTELAVALLE REITIT
    private Line REITTI_SAAPUMINEN_PALAVA;
    private Line REITTI_SAAPUMINEN_EPA;
    private Line REITTI_SAAPUMINEN_ELEKTRO;

    //JÄTELAVOISTA POISTUMISEEN
    private Line REITTI_PALAVA_POISTUMINEN;
    private Line REITTI_EPA_POISTUMINEN;
    private Line REITTI_ELEKTRO_POISTUMINEN;

    //JÄTELAVOISTA JÄTELAVALLE REITIT

    //PA TO EPA ja EPA TO PA
    private Line REITTI_PA_TO_EPA;
    private Line REITTI_EPA_TO_PA;

    //ELEKTRO TO PA ja PA TO ELEKTRO
    private Line REITTI_PA_TO_ELEKTRO_TOP_LINE;
    private Line REITTI_PA_TO_ELEKTRO_BOTTOM_LINE;
    private Line REITTI_ELEKTRO_TO_PA_BOTTOM_LINE;
    private Line REITTI_ELEKTRO_TO_PA_TOP_LINE;

    //EPA TO ELEKTRO ja ELEKTRO TO EPA
    private Line REITTI_EPA_TO_ELEKTRO;
    private Line REITTI_ELEKTRO_TO_EPA;

    //---------------------------------------------------------------------------------------------
    private Parent root;
    private Stage stage;
    private Scene scene;

    public Visualisointi2(FXML_CONTROLLER FXML_controller, IKontrolleriVtoM kontrolleri) throws IOException {
        this.kontrolleri = kontrolleri;
        this.FXML_controller = FXML_controller;
        FXML_controller = new FXML_CONTROLLER(kontrolleri);
        FXMLLoader loaderSIMU = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
        loaderSIMU.setController(FXML_controller);
        root = loaderSIMU.load();
        //----------------------------------ASIAKAS ELEMENTIT------------------------------------//

        //ASIAKAS ELEMENTTI

        //---------------------------------------------------------------------------------------------

        //------------------------------------JONOT------------------------------------//
        JONO_SAAPUMISPISTE = FXML_controller.getJONO_SAAPUMINEN();
        JONO_PALAVA = FXML_controller.getJONO_PA();
        JONO_EPA = FXML_controller.getJONO_EPA();
        JONO_ELEKTRO = FXML_controller.getJONO_ELEKTRO();

        //JONO PALIKAT("JONOSSA OLEVAT VISUAALISESTI")
        JONOPALIKKA_SAAPUMINEN = FXML_controller.getJONO_PALIKKA_SAAPUMINEN();
        JONOPALIKKA_PALAVA = FXML_controller.getJONO_PALIKKA_PALAVA();
        JONOPALIKKA_EPA = FXML_controller.getJONO_PALIKKA_EPA();
        JONOPALIKKA_ELEKTRO = FXML_controller.getJONO_PALIKKA_ELEKTRO();
        //---------------------------------------------------------------------------------------------


        //------------------------------------Kulku reitit------------------------------------//

        //SAAPUMISESTA JÄTELAVALLE REITIT
        REITTI_SAAPUMINEN_EPA = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_EPA();
        REITTI_SAAPUMINEN_ELEKTRO = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_ELEKTRO();

        //JÄTELAVOISTA POISTUMISEEN
        REITTI_PALAVA_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_PA();
        REITTI_EPA_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_EPA();
        REITTI_ELEKTRO_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_ELEKTRO();

        //JÄTELAVOISTA JÄTELAVALLE REITIT

        //PA TO EPA ja EPA TO PA
        REITTI_PA_TO_EPA = FXML_controller.getLINE_SIIRTYY_PA_TO_EPA();
        REITTI_EPA_TO_PA = FXML_controller.getLINE_SIIRTYY_EPA_TO_PA();

        //ELEKTRO TO PA ja PA TO ELEKTRO
        REITTI_PA_TO_ELEKTRO_TOP_LINE = FXML_controller.getLINE_SIIRTYY_PA_TO_ELEKTRO1();
        REITTI_PA_TO_ELEKTRO_BOTTOM_LINE = FXML_controller.getLINE_SIIRTYY_PA_TO_ELEKTRO2();
        REITTI_ELEKTRO_TO_PA_BOTTOM_LINE = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_PA1();
        REITTI_ELEKTRO_TO_PA_TOP_LINE = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_PA2();

        //EPA TO ELEKTRO ja ELEKTRO TO EPA
        REITTI_EPA_TO_ELEKTRO = FXML_controller.getLINE_SIIRTYY_EPA_TO_ELEKTRO();
        REITTI_ELEKTRO_TO_EPA = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_EPA();

        //---------------------------------------------------------------------------------------------
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {

    }

    public void uusiAsiakas() {
        asiakasLkm++;

    }

    @Override
    public void moveAsiakasPALAVA() {

        REITTI_SAAPUMINEN_PALAVA = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_PA();
        ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_SAAPUMINEN_PALAVA);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();


    }

    @Override
    public void moveAsiakasEPA() {

    }

    @Override
    public void moveAsiakasELEKTRO() {

    }

    @Override
    public void moveAsiakasPALAVA_POISTUMINEN() {

    }

    @Override
    public void moveAsiakasEPA_POISTUMINEN() {

    }

    @Override
    public void moveAsiakasELEKTRO_POISTUMINEN() {

    }

    @Override
    public void setPALAVA_VARATTU() {

    }

    @Override
    public void setEPA_VARATTU() {

    }

    @Override
    public void setELEKTRO_VARATTU() {

    }


}
