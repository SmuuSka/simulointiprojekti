package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;


import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.awt.*;

public class Visualisointi2 extends Canvas implements IVisualisointi{
    private int asiakasLkm = 0;
    private IKontrolleriVtoM kontrolleri;
    private FXML_CONTROLLER FXML_controller = new FXML_CONTROLLER(kontrolleri);

    //ASIAKAS SIIRTYY SAAPUMISPISTEELTÄ TOISEEN PISTEESEEN.
    private ImageView ASIAKAS_MOVE_PA = FXML_controller.getASIAKAS_MOVE_PA();
    private ImageView ASIAKAS_MOVE_EPA = FXML_controller.getASIAKAS_MOVE_EPA();
    private ImageView ASIAKAS_MOVE_ELEKTRO = FXML_controller.getASIAKAS_MOVE_ELEKTRO();

    //POISTUMISEEN SIIRTYVÄT ASIAKAAT
    private ImageView ASIAKAS_MOVE_PA_TO_POISTUMINEN = FXML_controller.getASIAKAS_MOVE_PA_TO_POISTUMINEN();
    private ImageView ASIAKAS_MOVE_EPA_TO_POISTUMINEN = FXML_controller.getASIAKAS_MOVE_EPA_TO_POISTUMINEN();
    private ImageView ASIAKAS_MOVE_ELEKTRO_TO_POISTUMINEN = FXML_controller.getASIAKAS_MOVE_ELEKTRO_TO_POISTUMINEN();

    //ASIAKAAT SIIRTYY PISTEELTÄ PISTEESEEN
    private ImageView ASIAKAS_MOVE_PA_TO_EPA = FXML_controller.getASIAKAS_MOVE_PA_TO_EPA();
    private ImageView ASIAKAS_MOVE_EPA_TO_PA = FXML_controller.getASIAKAS_MOVE_EPA_TO_PA();
    private ImageView ASIAKAS_MOVE_EPA_TO_ELEKTRO = FXML_controller.getASIAKAS_MOVE_EPA_TO_ELEKTRO();
    private ImageView ASIAKAS_MOVE_ELEKTRO_TO_EPA = FXML_controller.getASIAKAS_MOVE_ELEKTRO_TO_EPA();
    private ImageView ASIAKAS_MOVE_PA_TO_ELEKTRO = FXML_controller.getASIAKAS_MOVE_PA_TO_ELEKTRO();
    private ImageView ASIAKAS_MOVE_ELEKTRO_TO_PA = FXML_controller.getASIAKAS_MOVE_ELEKTRO_TO_PA();

    //JONOT
    private HBox JONO_SAAPUMISPISTE = FXML_controller.getJONO_SAAPUMINEN();
    private HBox JONO_PALAVA = FXML_controller.getJONO_PA();
    private HBox JONO_EPA = FXML_controller.getJONO_EPA();
    private HBox JONO_ELEKTRO = FXML_controller.getJONO_ELEKTRO();

    //JONO PALIKAT("JONOSSA OLEVAT VISUAALISESTI")
    private Rectangle JONOPALIKKA_SAAPUMINEN = FXML_controller.getJONO_PALIKKA_SAAPUMINEN();
    private Rectangle JONOPALIKKA_PALAVA = FXML_controller.getJONO_PALIKKA_PALAVA();
    private Rectangle JONOPALIKKA_EPA = FXML_controller.getJONO_PALIKKA_EPA();
    private Rectangle JONOPALIKKA_ELEKTRO = FXML_controller.getJONO_PALIKKA_ELEKTRO();



    public Visualisointi2() {
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {

    }

    public void uusiAsiakas() {
        asiakasLkm++;

    }

    @Override
    public void moveAsiakasPALAVA() {

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
