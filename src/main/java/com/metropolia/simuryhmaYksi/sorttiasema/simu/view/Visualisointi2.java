package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;


import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Paint;
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
    private ISimulaattoriUI ui;
    private IKontrolleriVtoM kontrolleri;
    private FXML_CONTROLLER FXML_controller;

    private Pane AnimationPane;
    //----------------------------------ASIAKAS ELEMENTIT------------------------------------//

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

    //----------------------------------ONKO PALVELUSSA "VALOT" ----------------------------
    private Ellipse PA_PALVELUSSAINFO;
    private Ellipse EPA_PALVELUSSAINFO;
    private Ellipse ELEKTRO_PALVELUSSAINFO;
    private Ellipse SAAPUMINEN_PALVELUSSAINFO;

    //---------------------------------------------------------------------------------------------
    private Parent root;
    private Stage stage;
    private Scene scene;

    public Visualisointi2(FXML_CONTROLLER FXML_controller, IKontrolleriVtoM kontrolleri,ISimulaattoriUI ui) throws IOException {
        this.ui = ui;
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
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {
        ui.setEJateJonossa(0);
        ui.setPJateJonossa(0);
        ui.setPTJateJonossa(0);
    }

    public void uusiAsiakas() {
        asiakasLkm++;

    }

    @Override
    // SAAPUMISPISTEELTÄ PALAVAAN ASIAKAS ANIMAATIO
    public void moveAsiakasPALAVA() {
        REITTI_SAAPUMINEN_PALAVA = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_PA();
        ImageView ASIAKAS_NODE = new ImageView();
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
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //SAAPUMISPISTEELTÄ EPAAN ASIAKAS ANIMAATIO
    public void moveAsiakasEPA() {
        REITTI_SAAPUMINEN_EPA = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_EPA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_SAAPUMINEN_EPA);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //SAAPUMISPISTEELTÄ ELEKTROON ASIAKAS ANIMAATIO
    public void moveAsiakasELEKTRO() {
        REITTI_SAAPUMINEN_ELEKTRO = FXML_controller.getLINE_SIIRTYY_PALVELU_TO_ELEKTRO();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_SAAPUMINEN_ELEKTRO);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //PALAVA TO POISTUMINEN ASIAKAS ANIMAATIO
    public void moveAsiakasPALAVA_POISTUMINEN() {
        REITTI_PALAVA_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_PA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_PALAVA_POISTUMINEN);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //EPA TO POISTUMINEN ASIAKAS ANIMAATIO
    public void moveAsiakasEPA_POISTUMINEN() {
        REITTI_EPA_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_EPA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();

        pathT.setPath(REITTI_EPA_POISTUMINEN);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //ELEKTRO TO POISTUMINEN ASIAKAS ANIMAATIO
    public void moveAsiakasELEKTRO_POISTUMINEN() {
        REITTI_ELEKTRO_POISTUMINEN = FXML_controller.getLINE_POISTUMINEN_ELEKTRO();
       ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_ELEKTRO_POISTUMINEN);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    // ELEKTRO TO PALAVA ASIAKAS ANIMAATIO
    public void moveAsiakasELEKTRO_PALAVA() {
        REITTI_ELEKTRO_TO_PA_BOTTOM_LINE = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_PA1();
        REITTI_ELEKTRO_TO_PA_TOP_LINE = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_PA2();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_ELEKTRO_TO_PA_BOTTOM_LINE);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
            AnimationPane.getChildren().add(ASIAKAS_NODE);
            PathTransition pathT2 = new PathTransition();
            pathT2.setPath(REITTI_ELEKTRO_TO_PA_TOP_LINE);
            pathT2.setNode(ASIAKAS_NODE);
            pathT2.setDuration(Duration.millis(3000));
            pathT2.setCycleCount(1);
            pathT2.play();
            pathT2.setOnFinished((event2) -> {
                AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
            });
        });
    }

    @Override
    //PALAVA TO ELEKTRO ASIAKAS ANIMAATIO
    public void moveAsiakasPALAVA_ELEKTRO() {
        REITTI_PA_TO_ELEKTRO_BOTTOM_LINE = FXML_controller.getLINE_SIIRTYY_PA_TO_ELEKTRO2();
        REITTI_PA_TO_ELEKTRO_TOP_LINE = FXML_controller.getLINE_SIIRTYY_PA_TO_ELEKTRO1();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_PA_TO_ELEKTRO_TOP_LINE);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
            AnimationPane.getChildren().add(ASIAKAS_NODE);
            PathTransition pathT2 = new PathTransition();
            pathT2.setPath(REITTI_PA_TO_ELEKTRO_BOTTOM_LINE);
            pathT2.setNode(ASIAKAS_NODE);
            pathT2.setDuration(Duration.millis(3000));
            pathT2.setCycleCount(1);
            pathT2.play();
            pathT2.setOnFinished((event2) -> {
                AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
            });
        });
    }

    @Override
    //ELEKTRO TO EPA ASIAKAS ANIMAATIO
    public void moveAsiakasELEKTRO_EPA() {
        REITTI_ELEKTRO_TO_EPA = FXML_controller.getLINE_SIIRTYY_ELEKTRO_TO_EPA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_ELEKTRO_TO_EPA);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //EPA TO ELEKTRO ASIAKAS ANIMAATIO
    public void moveAsiakasEPA_ELEKTRO() {
        REITTI_EPA_TO_ELEKTRO = FXML_controller.getLINE_SIIRTYY_EPA_TO_ELEKTRO();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_EPA_TO_ELEKTRO);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //PA TO EPA ASIAKAS ANIMAATIO
    public void moveAsiakasPA_EPA() {
        REITTI_PA_TO_EPA = FXML_controller.getLINE_SIIRTYY_PA_TO_EPA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_PA_TO_EPA);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //EPA TO PA ASIAKAS ANIMAATIO
    public void moveAsiakasEPA_PA() {
        REITTI_EPA_TO_PA = FXML_controller.getLINE_SIIRTYY_EPA_TO_PA();
        ImageView ASIAKAS_NODE = new ImageView();
        Image imageAsiakas = new Image("uifxml/Asiakas.png");
        ASIAKAS_NODE.setImage(imageAsiakas);
        ASIAKAS_NODE.setScaleX(0.1);
        ASIAKAS_NODE.setScaleY(0.1);
        ASIAKAS_NODE.setScaleZ(0.1);
        AnimationPane = FXML_controller.getAnimationPane();
        AnimationPane.getChildren().add(ASIAKAS_NODE);
        PathTransition pathT = new PathTransition();
        pathT.setPath(REITTI_EPA_TO_PA);
        pathT.setNode(ASIAKAS_NODE);
        pathT.setDuration(Duration.millis(3000));
        pathT.setCycleCount(1);
        pathT.play();
        pathT.setOnFinished((event) -> {
            AnimationPane.getChildren().removeAll(ASIAKAS_NODE);
        });
    }

    @Override
    //PALAVA PISTEEN VARATTU VALO
    public void setPALAVA_VARATTU(boolean onkovarattu) {
        PA_PALVELUSSAINFO = FXML_controller.getPA_PALVELUSSAINFO();
        if (onkovarattu == true) {
            PA_PALVELUSSAINFO.setFill(Color.RED);
            System.out.println("VARATTUPA");
        }else if (!onkovarattu){
            PA_PALVELUSSAINFO.setFill(Color.LIMEGREEN);
            System.out.println("VAPAAPA");
        }
    }

    @Override
    //EPA PISTEEN VARATTU VALO
    public void setEPA_VARATTU(boolean onkovarattu) {
        EPA_PALVELUSSAINFO = FXML_controller.getEPA_PALVELUSSAINFO();
        if (onkovarattu == true) {
            EPA_PALVELUSSAINFO.setFill(Color.RED);
            System.out.println("VARATTUPA");
        }else if (!onkovarattu){
            EPA_PALVELUSSAINFO.setFill(Color.LIMEGREEN);
            System.out.println("VAPAAPA");
        }

    }

    @Override
    //ELEKTRO PISTEEN VARATTU VALO
    public void setELEKTRO_VARATTU(boolean onkovarattu) {
        ELEKTRO_PALVELUSSAINFO = FXML_controller.getELEKTRO_PALVELUSSAINFO();
        if (onkovarattu == true) {
            ELEKTRO_PALVELUSSAINFO.setFill(Color.RED);
            System.out.println("VARATTUPA");
        }else if (!onkovarattu){
            ELEKTRO_PALVELUSSAINFO.setFill(Color.LIMEGREEN);
            System.out.println("VAPAAPA");
        }

    }

    @Override

    //SAAPUMIS PISTEEN VARATTU VALO
    public void setSAAPUMINEN_VARATTU(boolean onkovarattu) {
        SAAPUMINEN_PALVELUSSAINFO = FXML_controller.getSAAPUMINEN_PALVELUSSAINFO();
        if (onkovarattu == true) {
            SAAPUMINEN_PALVELUSSAINFO.setFill(Color.RED);
            System.out.println("VARATTUPA");
        }else if (!onkovarattu){
            SAAPUMINEN_PALVELUSSAINFO.setFill(Color.LIMEGREEN);
            System.out.println("VAPAAPA");
        }
    }


}
