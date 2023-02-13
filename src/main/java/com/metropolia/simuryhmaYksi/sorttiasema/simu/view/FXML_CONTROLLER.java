package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class FXML_CONTROLLER {
    private IKontrolleriVtoM kontrolleri;
    public FXML_CONTROLLER(IKontrolleriVtoM kontrolleri){
        this.kontrolleri = kontrolleri;
    }

    ///PÄÄSIMULAATORIN ELEMENTIT
    @FXML
    private Button BUTTON_ALOITA;

    @FXML
    private Button BUTTON_HITAAMMIN;

    @FXML
    private Button BUTTON_KAIKKITULOKSET;

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

    ///STRATEGIA ELEMENTIT
    @FXML
    private TextField ASIAKAS_MAX_JÄTEMÄÄRÄ;

    @FXML
    private TextField ASIAKAS_MIN_JÄTEMÄÄRÄ;

    @FXML
    private CheckBox RUUHKA_NORMAALIA_CHECK;

    @FXML
    private CheckBox RUUHKA_RAUHALLINEN_CHECK;

    @FXML
    private CheckBox RUUHKA_RUUHKA_CHECK;

    @FXML
    private TextField STRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_KGMAARA_SEKUNTEJA;

    @FXML
    private TextField STRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private TextField STRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ;

    @FXML
    private Button STRATEGIA_SIIRY_SIMULAATIOON;

    @FXML
    private CheckBox STRATEGIA_TAPAHTUMAT_ASIAKASAUTO;

    @FXML
    private CheckBox STRATEGIA_TAPAHTUMAT_SAAPUMISPISTEONGELMA;

    /////////////

    ///PÄÄSIMULAATION ACTION METHODIT!
    @FXML
    void aloitaSimulaatio(ActionEvent event) {
        kontrolleri.kaynnistaSimulointi();
    }

    @FXML
    void hidastaSimulaatio(ActionEvent event) {
        kontrolleri.nopeuta();
    }

    @FXML
    void lopetaSimulaatio(ActionEvent event) {
    }

    @FXML
    void naytaKaikkiTulokset(ActionEvent event) {

    }

    @FXML
    void nopeutaSimulaatio(ActionEvent event) {
        kontrolleri.nopeuta();
    }

    /// PÄÄSIMULAATORIN GETTERIT

    public Button getBUTTON_ALOITA() {
        return BUTTON_ALOITA;
    }

    public Button getBUTTON_HITAAMMIN() {
        return BUTTON_HITAAMMIN;
    }

    public Button getBUTTON_KAIKKITULOKSET() {
        return BUTTON_KAIKKITULOKSET;
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

    ///STRATEGIA GETTERIT!
    public TextField getASIAKAS_MAX_JÄTEMÄÄRÄ() {
        return ASIAKAS_MAX_JÄTEMÄÄRÄ;
    }

    public TextField getASIAKAS_MIN_JÄTEMÄÄRÄ() {
        return ASIAKAS_MIN_JÄTEMÄÄRÄ;
    }

    public CheckBox getRUUHKA_NORMAALIA_CHECK() {
        return RUUHKA_NORMAALIA_CHECK;
    }

    public CheckBox getRUUHKA_RAUHALLINEN_CHECK() {
        return RUUHKA_RAUHALLINEN_CHECK;
    }

    public CheckBox getRUUHKA_RUUHKA_CHECK() {
        return RUUHKA_RUUHKA_CHECK;
    }

    public TextField getSTRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ() {
        return STRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ;
    }

    public TextField getSTRATEGIA_KGMAARA_SEKUNTEJA() {
        return STRATEGIA_KGMAARA_SEKUNTEJA;
    }

    public TextField getSTRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ() {
        return STRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ;
    }

    public TextField getSTRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ() {
        return STRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ;
    }

    public Button getSTRATEGIA_SIIRY_SIMULAATIOON() {
        return STRATEGIA_SIIRY_SIMULAATIOON;
    }

    public CheckBox getSTRATEGIA_TAPAHTUMAT_ASIAKASAUTO() {
        return STRATEGIA_TAPAHTUMAT_ASIAKASAUTO;
    }

    public CheckBox getSTRATEGIA_TAPAHTUMAT_SAAPUMISPISTEONGELMA() {
        return STRATEGIA_TAPAHTUMAT_SAAPUMISPISTEONGELMA;
    }
}