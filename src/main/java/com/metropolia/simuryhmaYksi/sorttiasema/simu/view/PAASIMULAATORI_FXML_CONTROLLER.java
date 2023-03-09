package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * @Author Kaspar Tullus
 */

public class PAASIMULAATORI_FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;

    public PAASIMULAATORI_FXML_CONTROLLER(IKontrolleriVtoM kontrolleri) {
        this.kontrolleri = kontrolleri;
    }

    ///PÄÄSIMULAATORIN ELEMENTIT
    @FXML
    private Pane AnimationPane;
    @FXML
    private Button BUTTON_ALOITA;

    @FXML
    private Button BUTTON_HITAAMMIN;

    @FXML
    private Button BUTTON_LOPETA;

    @FXML
    private Button BUTTON_NOPEAMMIN;

    @FXML
    private Ellipse ELEKTRO_PALVELUSSAINFO;

    @FXML
    private Label ELEKTRO_POISHEITETTY_NUM;

    @FXML
    private Ellipse EPA_PALVELUSSAINFO;

    @FXML
    private Label EPA_POISHEITETTY_NUM;

    @FXML
    private VBox INFO_SIMJÄTE_VBOX;

    @FXML
    private Label JONOSSAINFO_ELEKTRO;

    @FXML
    private Label JONOSSAINFO_EPA;

    @FXML
    private Label JONOSSAINFO_PA;

    @FXML
    private Label JONOSSAINFO_SAAPUMINEN;

    @FXML
    private Label JONOSSA_SAAPUMINEN;

    @FXML
    private HBox JONO_ELEKTRO;

    @FXML
    private HBox JONO_EPA;

    @FXML
    private HBox JONO_PA;

    @FXML
    private Rectangle JONO_PALIKKA_ELEKTRO;

    @FXML
    private Rectangle JONO_PALIKKA_EPA;

    @FXML
    private Rectangle JONO_PALIKKA_PALAVA;

    @FXML
    private Rectangle JONO_PALIKKA_SAAPUMINEN;

    @FXML
    private HBox JONO_SAAPUMINEN;

    @FXML
    private Line LINE_POISTUMINEN_ELEKTRO;

    @FXML
    private Line LINE_POISTUMINEN_EPA;

    @FXML
    private Line LINE_POISTUMINEN_PA;

    @FXML
    private Line LINE_SIIRTYY_ELEKTRO_TO_EPA;

    @FXML
    private Line LINE_SIIRTYY_ELEKTRO_TO_PA1;

    @FXML
    private Line LINE_SIIRTYY_ELEKTRO_TO_PA2;

    @FXML
    private Line LINE_SIIRTYY_EPA_TO_ELEKTRO;

    @FXML
    private Line LINE_SIIRTYY_EPA_TO_PA;

    @FXML
    private Line LINE_SIIRTYY_PALVELU_TO_ELEKTRO;

    @FXML
    private Line LINE_SIIRTYY_PALVELU_TO_EPA;

    @FXML
    private Line LINE_SIIRTYY_PALVELU_TO_PA;

    @FXML
    private Line LINE_SIIRTYY_PA_TO_ELEKTRO1;

    @FXML
    private Line LINE_SIIRTYY_PA_TO_ELEKTRO2;

    @FXML
    private Line LINE_SIIRTYY_PA_TO_EPA;

    @FXML
    private GridPane NAPPI_LAATIKKO;

    @FXML
    private ImageView PALVELUPISTE_ELEKTRO;

    @FXML
    private ImageView PALVELUPISTE_EPA;

    @FXML
    private ImageView PALVELUPISTE_PA;

    @FXML
    private ImageView PALVELUPISTE_POISTUMINEN;

    @FXML
    private ImageView PALVELUPISTE_SAAPUMINEN;

    @FXML
    private Ellipse PA_PALVELUSSAINFO;

    @FXML
    private Label PA_POISHEITETTY_NUM;

    @FXML
    private Label POISTUNUTINFO;

    @FXML
    private Ellipse SAAPUMINEN_PALVELUSSAINFO;

    @FXML
    private AnchorPane SIMULAATIO_VIEWBOX;

    ///////////

    /// PÄÄSIMULAATORIN GETTERIT
    public Pane getAnimationPane() {
        return AnimationPane;
    }
    public Button getBUTTON_ALOITA() {
        return BUTTON_ALOITA;
    }

    public Button getBUTTON_HITAAMMIN() {
        return BUTTON_HITAAMMIN;
    }

    public Button getBUTTON_LOPETA() {
        return BUTTON_LOPETA;
    }

    public Button getBUTTON_NOPEAMMIN() {
        return BUTTON_NOPEAMMIN;
    }

    public Ellipse getELEKTRO_PALVELUSSAINFO() {
        return ELEKTRO_PALVELUSSAINFO;
    }

    public Label getELEKTRO_POISHEITETTY_NUM() {
        return ELEKTRO_POISHEITETTY_NUM;
    }

    public Ellipse getEPA_PALVELUSSAINFO() {
        return EPA_PALVELUSSAINFO;
    }

    public Label getEPA_POISHEITETTY_NUM() {
        return EPA_POISHEITETTY_NUM;
    }

    public VBox getINFO_SIMJÄTE_VBOX() {
        return INFO_SIMJÄTE_VBOX;
    }

    public Label getJONOSSAINFO_ELEKTRO() {
        return JONOSSAINFO_ELEKTRO;
    }

    public Label getJONOSSAINFO_EPA() {
        return JONOSSAINFO_EPA;
    }

    public Label getJONOSSAINFO_PA() {
        return JONOSSAINFO_PA;
    }

    public Label getJONOSSAINFO_SAAPUMINEN() {
        return JONOSSAINFO_SAAPUMINEN;
    }

    public Label getJONOSSA_SAAPUMINEN() {
        return JONOSSA_SAAPUMINEN;
    }

    public Line getLINE_POISTUMINEN_ELEKTRO() {
        return LINE_POISTUMINEN_ELEKTRO;
    }

    public Line getLINE_POISTUMINEN_EPA() {
        return LINE_POISTUMINEN_EPA;
    }

    public Line getLINE_POISTUMINEN_PA() {
        return LINE_POISTUMINEN_PA;
    }

    public Line getLINE_SIIRTYY_ELEKTRO_TO_EPA() {
        return LINE_SIIRTYY_ELEKTRO_TO_EPA;
    }

    public Line getLINE_SIIRTYY_ELEKTRO_TO_PA1() {
        return LINE_SIIRTYY_ELEKTRO_TO_PA1;
    }

    public Line getLINE_SIIRTYY_ELEKTRO_TO_PA2() {
        return LINE_SIIRTYY_ELEKTRO_TO_PA2;
    }

    public Line getLINE_SIIRTYY_EPA_TO_ELEKTRO() {
        return LINE_SIIRTYY_EPA_TO_ELEKTRO;
    }

    public Line getLINE_SIIRTYY_EPA_TO_PA() {
        return LINE_SIIRTYY_EPA_TO_PA;
    }

    public Line getLINE_SIIRTYY_PALVELU_TO_ELEKTRO() {
        return LINE_SIIRTYY_PALVELU_TO_ELEKTRO;
    }

    public Line getLINE_SIIRTYY_PALVELU_TO_EPA() {
        return LINE_SIIRTYY_PALVELU_TO_EPA;
    }

    public Line getLINE_SIIRTYY_PALVELU_TO_PA() {
        return LINE_SIIRTYY_PALVELU_TO_PA;
    }

    public Line getLINE_SIIRTYY_PA_TO_ELEKTRO1() {
        return LINE_SIIRTYY_PA_TO_ELEKTRO1;
    }

    public Line getLINE_SIIRTYY_PA_TO_ELEKTRO2() {
        return LINE_SIIRTYY_PA_TO_ELEKTRO2;
    }

    public Line getLINE_SIIRTYY_PA_TO_EPA() {
        return LINE_SIIRTYY_PA_TO_EPA;
    }

    public GridPane getNAPPI_LAATIKKO() {
        return NAPPI_LAATIKKO;
    }

    public ImageView getPALVELUPISTE_ELEKTRO() {
        return PALVELUPISTE_ELEKTRO;
    }

    public ImageView getPALVELUPISTE_EPA() {
        return PALVELUPISTE_EPA;
    }

    public ImageView getPALVELUPISTE_PA() {
        return PALVELUPISTE_PA;
    }

    public ImageView getPALVELUPISTE_POISTUMINEN() {
        return PALVELUPISTE_POISTUMINEN;
    }

    public ImageView getPALVELUPISTE_SAAPUMINEN() {
        return PALVELUPISTE_SAAPUMINEN;
    }

    public Ellipse getPA_PALVELUSSAINFO() {
        return PA_PALVELUSSAINFO;
    }

    public Label getPA_POISHEITETTY_NUM() {
        return PA_POISHEITETTY_NUM;
    }

    public Label getPOISTUNUTINFO() {
        return POISTUNUTINFO;
    }

    public Ellipse getSAAPUMINEN_PALVELUSSAINFO() {
        return SAAPUMINEN_PALVELUSSAINFO;
    }

    public AnchorPane getSIMULAATIO_VIEWBOX() {
        return SIMULAATIO_VIEWBOX;
    }

    public HBox getJONO_ELEKTRO() {
        return JONO_ELEKTRO;
    }

    public HBox getJONO_EPA() {
        return JONO_EPA;
    }

    public HBox getJONO_PA() {
        return JONO_PA;
    }

    public Rectangle getJONO_PALIKKA_ELEKTRO() {
        return JONO_PALIKKA_ELEKTRO;
    }

    public Rectangle getJONO_PALIKKA_EPA() {
        return JONO_PALIKKA_EPA;
    }

    public Rectangle getJONO_PALIKKA_PALAVA() {
        return JONO_PALIKKA_PALAVA;
    }

    public Rectangle getJONO_PALIKKA_SAAPUMINEN() {
        return JONO_PALIKKA_SAAPUMINEN;
    }

    public HBox getJONO_SAAPUMINEN() {
        return JONO_SAAPUMINEN;
    }

}
