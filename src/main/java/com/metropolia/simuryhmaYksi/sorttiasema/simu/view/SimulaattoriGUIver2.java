package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {
    private TULOKSET_FXML_CONTROLLER TULOKSET_FXML_CONTROLLER;
    private Button aloitaButton, nopeutaButton, hidastaButton, strategiaButton, lopetaButton, strategiaNaytaTuloksetButton, tuloksetPoistaTulosButton, tuloksetPoistaKaikkiTuloksetButton;

    private TextField simulointiAikaInput, simulointiAikaViiveInput, asiakasJateMIN_INPUT, asiakasJateMAX_INPUT, elektroniikkaJatePROSENTTI,
            palavaJatePROSENTTI, palamatonJatePROSENTTI, asiakasPurku_KG_Sekunti;

    private RadioButton rauhallinenAktiivisuus, normaaliAktiivisuus, ruuhkainenAktiivisuus;

    private CheckBox ajetaanLoppuun;

    private Label paaSim_ELEKTRO_JateCounter, paaSim_PALAVA_JateCounter, paaSim_PALAMATON_JateCounter,
            paaSim_JONOINFO_SAAPUMINEN, paaSim_SAAPUMISIAYHT_COUNTER, paaSim_JONOINFO_PALAVAJATE,
            paaSim_JONOINFO_ELEKTRONIIKKAJATE, paaSim_JONOINFO_PALAMATONJATE, paaSim_POISTUNUT_COUNTER;
    private int elektroJateProsentti = 0;
    private int palavaJateProsentti = 0;
    private int palamatonJateProsentti = 0;
    private int simulaatioAika = 0;
    private double asiakasPerKg = 0;

    private boolean onkoSimuloitu = false;
    private int simulaatioViive = 0;
    private ToggleGroup aktiivisuusRadioGroup;
    private Scene scene;

    private PAASIMULAATORI_FXML_CONTROLLER mainFXML_Controller;
    private STRATEGIA_FXML_CONTROLLER strategiaFXML_Controller;
    private Parent root;
    private Parent rootPaaSimu;
    private Parent rootStrategia;
    private IKontrolleriVtoM kontrolleri;
    private Stage primaryStagePara;
    private IVisualisointi naytto;
    private static String[] mainArgs;
    //-------------------------------------------------------------------------------------------

    /**
     * Tästä aloitetaan SimulaatoriGUI käynnistys
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tässä methodissa tehdään kaikki aloitukseen liityvät asiat
     */
    @Override
    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
        kontrolleri = new Kontrolleri(this);

    }

    /**
     * Täälä aloitetaan ohjelman visualisointi
     */
    @Override
    public void start(Stage primaryStage) {

        try {
            primaryStagePara = primaryStage;
            primaryStage.centerOnScreen();
            primaryStage.setHeight(Screen.getPrimary().getBounds().getMaxY()*0.84);
            primaryStage.getIcons().add(new Image("/uifxml/LOGO.png"));
            FXMLLoader loaderStrategia = new FXMLLoader(getClass().getResource("/uifxml/Strategia.fxml"));
            FXMLLoader loaderSIMU = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            strategiaFXML_Controller = new STRATEGIA_FXML_CONTROLLER(kontrolleri);
            mainFXML_Controller = new PAASIMULAATORI_FXML_CONTROLLER(kontrolleri);
            loaderStrategia.setController(strategiaFXML_Controller);
            loaderSIMU.setController(mainFXML_Controller);
            rootStrategia = loaderStrategia.load();
            rootPaaSimu = loaderSIMU.load();

            //Hae STRATEGIA Napit FXML CONTROLLERISTA

            // (TÄÄLÄ ON KAIKKI STRATEGIA NÄKYMÄN ELEMENTIT)//
            //Strategia näkymän napit.
            strategiaButton = strategiaFXML_Controller.getSTRATEGIA_SIIRY_SIMULAATIOON();
            strategiaNaytaTuloksetButton = strategiaFXML_Controller.getSTRATEGIA_NAYTATULOKSET();
            //Hae TextFields FXML CONTROLLERISTA//

            //SimulointiAika ja viive
            simulointiAikaInput = strategiaFXML_Controller.getSTRATEGIA_SIMULOINTIAIKA();
            simulointiAikaViiveInput = strategiaFXML_Controller.getSTRATEGIA_SIMULOINTIVIIVE();

            //Min ja Max kg määrä per asiakas.
            asiakasJateMIN_INPUT = strategiaFXML_Controller.getSTRATEGIA_ASIAKAS_MIN_JATEMAARA();
            asiakasJateMAX_INPUT = strategiaFXML_Controller.getSTRATEGIA_ASIAKAS_MAX_JATEMAARA();

            /// Purku aika per kg
            asiakasPurku_KG_Sekunti = strategiaFXML_Controller.getSTRATEGIA_KGMAARA_SEKUNTEJA();

            //Jäte Prosentti määrät (kuinka paljon tuodaan jätettä)
            elektroniikkaJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_ELEKTRONIIKKAJATE_PROSENTTIMAARA();
            palavaJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_PALAVAJATE_PROSENTTIMAARA();
            palamatonJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_PALAAMATONJATE_PROSENTTIMAARA();

            //RadioButtonGroup
            aktiivisuusRadioGroup = strategiaFXML_Controller.getAktiivisuusGroup();
            //-------------------------------------------------------------------------------------------

            //Hae Checkboxit/RadioButtonit FXML CONTROLLERISTA//

            //Aktiivisuus
            rauhallinenAktiivisuus = strategiaFXML_Controller.getSTRATEGIA_RUUHKA_RAUHALLINEN_CHECK();
            rauhallinenAktiivisuus.setId("1");

            normaaliAktiivisuus = strategiaFXML_Controller.getSTRATEGIA_RUUHKA_NORMAALIA_CHECK();
            normaaliAktiivisuus.setId("2");

            ruuhkainenAktiivisuus = strategiaFXML_Controller.getSTRATEGIA_RUUHKA_RUUHKA_CHECK();
            ruuhkainenAktiivisuus.setId("3");

            //Ajetaanko Tyhjäksi?
            ajetaanLoppuun = strategiaFXML_Controller.getSTRATEGIA_JONOT_AJALOPPUUN();
            ajetaanLoppuun.setId("6");

            //-------------------------------------------------------------------------------------------

            //-ASETETAAN STRATEGIA SCENE-//
            scene = new Scene(loaderStrategia.getRoot());
            /**Tämä avaa Tulokset ikkunan missä näkyy tietokannasta tuotut tulokset */
            strategiaNaytaTuloksetButton.setOnAction(actionEvent -> {
                try {
                    kontrolleri.showTuloksetAction();
                } catch (SQLException e) {
                    System.out.println("TULOSTUKSISSA EI OLE DATAA");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ilmoitus");
                    alert.setHeaderText("Ilmoitus");
                    alert.setContentText("Tuloksia ei ole,Käynnistä simulaatio jotta voit luoda tuloksia.");
                    alert.show();
                }
            });

            //Siiry PÄÄSIMULAATIO IKKUNAAN KUN PAINETAAN OK NAPPIA STRATEGIASSA
            /**Siirytään Pääsimulaattori ikkunaan,missä visualisoitaan ja lasketaan tulokset käyttäjän syötteiten avulla. */
            strategiaButton.setOnAction(event -> {
                try {
                    simulaatioAika = Integer.parseInt(simulointiAikaInput.getText());
                    asiakasPerKg = Double.parseDouble(asiakasPurku_KG_Sekunti.getText());
                    simulaatioViive = Integer.parseInt(simulointiAikaViiveInput.getText());
                    elektroJateProsentti = Integer.parseInt(elektroniikkaJatePROSENTTI.getText());
                    palavaJateProsentti = Integer.parseInt(palavaJatePROSENTTI.getText());
                    palamatonJateProsentti = Integer.parseInt(palamatonJatePROSENTTI.getText());
                } catch (NumberFormatException numberex) {
                    System.out.println("Kaikkiin kenttiin pitää syöttää numero arvoja.");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Varoitus");
                    alert.setHeaderText("Varoitus:");
                    alert.setContentText("Syöte kenttiin ei voi syötää kirjaimia arvona!");
                    alert.show();
                }
                if (simulaatioViive <= 0 || simulaatioAika <= 0 || asiakasPerKg <= 0) {
                    System.out.println("Kaikkiin kenttiin pitää syöttää numero arvoja.");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Varoitus");
                    alert.setHeaderText("Varoitus:");
                    alert.setContentText("Simulaatioaika,viive ja asiakasSecperkg ei voi olla nollia");
                    alert.show();
                } else if (elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti != 100) {
                    int summa = elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti;
                    System.out.println("Jäteprosentti luvut pitää olla yhteensä 100%, sinulla on " + summa + "%");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Varoitus");
                    alert.setHeaderText("Varoitus:");
                    alert.setContentText("Prosentit pitää olla tasan 100% yhteensä, sinulla on " + summa + "%");
                    alert.show();
                } else {
                    //Asiakaan MIN/MAX kg määrä tarkistus.
                    int asiakasMin = 0;
                    int asiakasMax = 0;
                    try {
                        asiakasMin = Integer.parseInt(asiakasJateMIN_INPUT.getText());
                        asiakasMax = Integer.parseInt(asiakasJateMAX_INPUT.getText());
                    } catch (NumberFormatException numberex) {
                        System.out.println("Et ole Syöttänyt mitää arvoja asiakkaan min ja max kilo määriin!");
                    } finally {

                        if (asiakasMin < 0 || asiakasMax < 0 || asiakasMin == asiakasMax) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Varoitus");
                            alert.setHeaderText("Varoitus:");
                            alert.setContentText("Minimi/Maksimi kilo määrä ei voi olla alle 0 ja molemmat eivät voi olla samoja määriä. Tai et ole antanut mitään arvoja.");
                            alert.show();
                        } else {
                            if (rootPaaSimu.getScene() != null) {
                                primaryStage.setScene(scene);
                                primaryStage.setTitle("Sortti-Asema Simu");
                                primaryStage.centerOnScreen();
                                primaryStage.setHeight(Screen.getPrimary().getBounds().getMaxY()*0.9);
                                primaryStage.setHeight(Screen.getPrimary().getBounds().getMaxX());
                                primaryStage.show();
                            } else {
                                scene = new Scene(loaderSIMU.getRoot());
                                primaryStage.setScene(scene);
                                primaryStage.setTitle("Sortti-Asema Simu");
                                primaryStage.centerOnScreen();
                                primaryStage.setHeight(Screen.getPrimary().getBounds().getMaxY()*0.9);
                                primaryStage.show();
                            }


                            //PÄÄSIMULAATORIN ELEMENTIT ALKAA TÄSTÄ
                            if (primaryStage.isShowing() == true) {
                                //PÄÄSIMULAATORIN NAPIT.
                                aloitaButton = mainFXML_Controller.getBUTTON_ALOITA();
                                hidastaButton = mainFXML_Controller.getBUTTON_HITAAMMIN();
                                nopeutaButton = mainFXML_Controller.getBUTTON_NOPEAMMIN();
                                lopetaButton = mainFXML_Controller.getBUTTON_LOPETA();

                                ///PÄÄSIMULAAATORI Teksti/Label elementit

                                //POISHEITETTYJATE COUNTERIT
                                paaSim_ELEKTRO_JateCounter = mainFXML_Controller.getELEKTRO_POISHEITETTY_NUM();
                                paaSim_PALAVA_JateCounter = mainFXML_Controller.getPA_POISHEITETTY_NUM();
                                paaSim_PALAMATON_JateCounter = mainFXML_Controller.getEPA_POISHEITETTY_NUM();

                                ///JONOSSA COUNTER LABEL/TEXT

                                //PALAVAJÄTE JONO
                                paaSim_JONOINFO_PALAVAJATE = mainFXML_Controller.getJONOSSAINFO_PA();

                                //ELEKTRONIIKA JONO
                                paaSim_JONOINFO_ELEKTRONIIKKAJATE = mainFXML_Controller.getJONOSSAINFO_ELEKTRO();

                                //PALAMATONJÄTE JONO
                                paaSim_JONOINFO_PALAMATONJATE = mainFXML_Controller.getJONOSSAINFO_EPA();

                                //SAAPUMISEN JONO
                                paaSim_JONOINFO_SAAPUMINEN = mainFXML_Controller.getJONOSSA_SAAPUMINEN();

                                //SAAPUMISTEN MÄÄRÄ TÄLLÄ HETKELLÄ LABLE
                                paaSim_SAAPUMISIAYHT_COUNTER = mainFXML_Controller.getJONOSSAINFO_SAAPUMINEN();

                                //POISTUNUT ASIAKKAAT YHTEENSÄ TÄLLÄ HETKELLÄ LABLE
                                paaSim_POISTUNUT_COUNTER = mainFXML_Controller.getPOISTUNUTINFO();


                                System.out.println("Siirytään Pääsimulaatorille.");
                                try {
                                    aloitaButton.setOnAction(event1 -> {
                                        if (onkoSimuloitu) {
                                            onkoSimuloitu = false;
                                            Parent root2 = loaderStrategia.getRoot();
                                            primaryStage.setScene(root2.getScene());
                                            restartProgram(primaryStage);
                                        } else {
                                            kontrolleri.setVisualisointi(getVisualisointi());
                                            try {
                                                kontrolleri.kaynnistaSimulointi();
                                                onkoSimuloitu = true;
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }


                                    });

                                    hidastaButton.setOnAction(event2 -> {
                                        kontrolleri.hidasta();
                                    });

                                    nopeutaButton.setOnAction(event3 -> {
                                        kontrolleri.nopeuta();
                                    });
                                    lopetaButton.setOnAction(event4 -> {
                                        try {
                                            kontrolleri.lopetaSimulointi();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Pääsimulaatori ei ole vielä päälä.");
                            }
                        }
                    }
                }
            });

            primaryStage.setScene(rootStrategia.getScene());

            primaryStage.setTitle("Sortti-Asema Simu");

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------

    /**
     * Tämä kutsutaan kun painetaan käynnistä uudelleen nappia, se avaa uudestaan strategia ikkunan missä syötetään uusia syötteitä.
     */
    public void restartProgram(Stage primaryStage) {
        onkoSimuloitu = false;
        Platform.runLater(() -> {
            try {
                primaryStage.close();
                FXMLLoader loaderStrategia = new FXMLLoader(getClass().getResource("/uifxml/Strategia.fxml"));
                strategiaFXML_Controller = new STRATEGIA_FXML_CONTROLLER(kontrolleri);
                loaderStrategia.setController(strategiaFXML_Controller);
                rootStrategia = loaderStrategia.load();
                Parent root = loaderStrategia.getRoot();
                Scene scene = new Scene(root);
                primaryStage.centerOnScreen();
                primaryStage.setScene(scene);
                primaryStage.setHeight(Screen.getPrimary().getBounds().getMaxY()*0.9);
                primaryStage.setMaxWidth(Screen.getPrimary().getBounds().getMaxX());
                primaryStage.show();
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA

    /**
     * Kun näytä tulokset nappia on painettu tai kun simulaatio on päätynyt, mennään tähän methodiin joka avaa tulokset ikkunan ja tulostaa tarvittavan datan.
     */
    @Override
    public void showTulokset(ArrayList<SimulaatioData> datatulokset) {
        Platform.runLater(
                () -> {
                    try {
                        TULOKSET_FXML_CONTROLLER = new TULOKSET_FXML_CONTROLLER(kontrolleri);
                        ObservableList<SimulaatioData> dataob = FXCollections.observableArrayList(datatulokset);
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(SimulaattoriGUIver2.class.getResource("/uifxml/Tulokset.fxml"));
                        loader.setController(TULOKSET_FXML_CONTROLLER);
                        AnchorPane page = (AnchorPane) loader.load();
                        Stage tuloksetStage = new Stage();
                        tuloksetStage.setTitle("Tulokset");
                        tuloksetStage.initModality(Modality.WINDOW_MODAL);
                        tuloksetStage.initOwner(primaryStagePara);
                        Scene scene = new Scene(page);
                        tuloksetStage.centerOnScreen();
                        tuloksetStage.setScene(scene);

                        TableView TABLE_VIEW_DATA = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA();
                        TableColumn<SimulaatioData, Integer> idCOLUMN = new TableColumn<SimulaatioData, Integer>("#");
                        idCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, Integer>("id"));
                        TableColumn<SimulaatioData, LocalDate> aikaCOLUMN = new TableColumn<SimulaatioData, LocalDate>("Päivämäärä");
                        aikaCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, LocalDate>("paivamaara"));
                        TableColumn<SimulaatioData, Boolean> ajetaankoTyhjaksiCOLUMN = new TableColumn<SimulaatioData, Boolean>("Ajetaanko Tyhjäksi");
                        ajetaankoTyhjaksiCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, Boolean>("ajetaanTyhjaksi"));

                        TABLE_VIEW_DATA.getColumns().addAll(idCOLUMN, aikaCOLUMN, ajetaankoTyhjaksiCOLUMN);
                        aikaCOLUMN.setCellValueFactory(
                                cellData -> cellData.getValue().paivamaaraProperty());
                        idCOLUMN.setCellValueFactory(
                                cellData -> cellData.getValue().idProperty().asObject());
                        TABLE_VIEW_DATA.setItems(dataob);
                        ajetaankoTyhjaksiCOLUMN.setCellValueFactory(
                                cellData -> cellData.getValue().simulaatioTyhjaksiProperty());
                        TABLE_VIEW_DATA.setItems(dataob);

                        tuloksetStage.setOnCloseRequest(event -> {
                            datatulokset.clear();
                        });


                        TABLE_VIEW_DATA.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                            valittuData(obs, newSelection, TULOKSET_FXML_CONTROLLER);
                            SimulaatioData selectedItem = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().getSelectedItem();

                            //Poista valittu Tulos Nappi.
                            tuloksetPoistaTulosButton.setOnAction(event -> {
                                try {
                                    poistaData(selectedItem.getId());
                                    dataob.remove(selectedItem);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Data Poistettu");
                            });

                            tuloksetPoistaKaikkiTuloksetButton.setOnAction(actionEvent -> {
                                try {
                                    kontrolleri.avaaDATAYHTEYS();
                                    kontrolleri.poistaKaikkiDATA();
                                    dataob.clear();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                        });
                        tuloksetStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    tuloksetPoistaTulosButton = TULOKSET_FXML_CONTROLLER.getTULOKSET_POISTANAPPI();
                    tuloksetPoistaKaikkiTuloksetButton = TULOKSET_FXML_CONTROLLER.getTULOKSET_POISTAKAIKKI();
                    TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().selectLast();


                });
    }

    /**
     * Tämä poistaa tietyn tuloksen joka on valittu.
     */
    @Override
    public void poistaData(int ID) throws SQLException {
        kontrolleri.poistaTulos(ID);
    }

    /**
     * Kun tulos ikkunassa valitaan dataa, täälä päivetetään valitun datan mukaan tietoja tuloksiin.
     */
    public void valittuData(ObservableValue obs, Object newSelection, TULOKSET_FXML_CONTROLLER tuloksetkontrolleri) {
        if (newSelection != null) {
            SimulaatioData selectedItem = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().getSelectedItem();
            //SIMUAIKA TULOS
            tuloksetkontrolleri.getTULOKSET_SIMUAIKA().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(4).doubleValue()));
            //ROSKEN KOKONAIS MÄÄRÄ
            tuloksetkontrolleri.getTULOKSET_HEITETTY_YHT().setText(selectedItem.getTulokset().getTuloksetDOUBLE().get(5).doubleValue() + " Kg");
            //ELEKTROJÄTE
            tuloksetkontrolleri.getTULOKSET_HEITETTY_ELEKTRO().setText(selectedItem.getTulokset().getTuloksetDOUBLE().get(24).doubleValue() + " Kg");
            //PALAMATONJÄTE
            tuloksetkontrolleri.getTULOKSET_HEITETTY_PALAMATON().setText(selectedItem.getTulokset().getTuloksetDOUBLE().get(25).doubleValue() + " Kg");
            //PALAVAJÄTE
            tuloksetkontrolleri.getTULOKSET_HEITETTY_PALAVA().setText(selectedItem.getTulokset().getTuloksetDOUBLE().get(26).doubleValue() + " Kg");
            //Saapumeiden lukumäärä
            tuloksetkontrolleri.getTULOKSET_SAAPUNUT_LKM().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(0).intValue()));
            //Palveltujien lukumäärä
            tuloksetkontrolleri.getTULOKSET_PALVELTU_LKM().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(1).intValue()));
            //Palveltujien lukumäärä SaapumisPiste
            tuloksetkontrolleri.getTULOKSET_PALVELTU_LK_SAAPUMINEN().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(6).intValue()));
            //Palveltujien lukumäärä Elektro
            tuloksetkontrolleri.getTULOKSET_PALVELTU_LK_ELEKTRO().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(7).intValue()));
            //Palveltujien lukumäärä Palamaton
            tuloksetkontrolleri.getTULOKSET_PALVELTU_LK_PALAMATON().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(8).intValue()));
            //Palveltujien lukumäärä Palava
            tuloksetkontrolleri.getTULOKSET_PALVELTU_LK_PALAVA().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(9).intValue()));
            //Oleskeluaika Saapuminen
            tuloksetkontrolleri.getTULOKSET_OLESKELUAIKA_SAAPUMINEN().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(2).intValue()));
            //Oleskeluaika Elektroniikka
            tuloksetkontrolleri.getTULOKSET_OLESKELUAIKA_ELEKTRO().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(3).intValue()));
            //Oleskeluaika Palamaton
            tuloksetkontrolleri.getTULOKSET_OLESKELUAIKA_PALAMATON().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(4).intValue()));
            //Oleskeluaika Palava
            tuloksetkontrolleri.getTULOKSET_OLESKELUAIKA_PALAVA().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(5).intValue()));
            //Oleskeluaika Palava
            tuloksetkontrolleri.getTULOKSET_OLESKELUAIKA_PALAVA().setText(Integer.toString(selectedItem.getTulokset().getTuloksetINT().get(5).intValue()));
            //Aktiivisuus Saapuminen
            tuloksetkontrolleri.getTULOKSET_AKTIIVISUUSYHT_SAAPUMISPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(0).doubleValue()));
            //Aktiivisuus Elektro
            tuloksetkontrolleri.getTULOKSET_AKTIIVISUUSYHT_ELEKTRO().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(1).doubleValue()));
            //Aktiivisuus Palamaton
            tuloksetkontrolleri.getTULOKSET_AKTIIVISUUSYHT_PALAMATON().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(2).doubleValue()));
            //Aktiivisuus Palava
            tuloksetkontrolleri.getTULOKSET_AKTIIVISUUSYHT_PALAVA().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(3).doubleValue()));
            //Asiakkaita per aikayksikkö
            tuloksetkontrolleri.getTULOKSET_ASIAKASPER_AIKAYKSIKKO().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(6).doubleValue()));
            //Keskimääräinen jonon pituus Saapuva
            tuloksetkontrolleri.getTULOKSET_KESKMAARA_JONO_SAAPUMISPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(7).doubleValue()));
            //Keskimääräinen jonon pituus Elektro
            tuloksetkontrolleri.getTULOKSET_KESKMAARA_JONO_ELEKTROPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(8).doubleValue()));
            //Keskimääräinen jonon pituus Palamaton
            tuloksetkontrolleri.getTULOKSET_KESKMAARA_JONO_PALAMATONPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(9).doubleValue()));
            //Keskimääräinen jonon pituus Palava
            tuloksetkontrolleri.getTULOKSET_KESKMAARA_JONO_PALAVAPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(10).doubleValue()));
            //Keskimääräinen Läpimenoaika Saapuminen
            tuloksetkontrolleri.getTULOKSET_KESKLAPIMENO_SAAPUMINEN().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(11).doubleValue()));
            //Keskimääräinen Läpimenoaika Elektro
            tuloksetkontrolleri.getTULOKSET_KESKLAPIMENO_ELEKTRO().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(12).doubleValue()));
            //Keskimääräinen Läpimenoaika Palamaton
            tuloksetkontrolleri.getTULOKSET_KESKLAPIMENO_PALAMATON().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(13).doubleValue()));
            //Keskimääräinen Läpimenoaika Palava
            tuloksetkontrolleri.getTULOKSET_KESKLAPIMENO_PALAVA().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(14).doubleValue()));
            //Käyttöaste Saapuminen
            tuloksetkontrolleri.getTULOKSET_KAYTTOASTE_SAAPUMISPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(15).doubleValue()));
            //Käyttöaste Elektro
            tuloksetkontrolleri.getTULOKSET_KAYTTOASTE_ELEKTROPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(16).doubleValue()));
            //Käyttöaste Palamaton
            tuloksetkontrolleri.getTULOKSET_KAYTTOASTE_PALAMATONPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(17).doubleValue()));
            //Käyttöaste Palava
            tuloksetkontrolleri.getTULOKSET_KAYTTOASTE_PALAVAPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(18).doubleValue()));
            //Käyttöaste Palava
            tuloksetkontrolleri.getTULOKSET_KAYTTOASTE_PALAVAPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(18).doubleValue()));
            //Keskimääräinen Palveluaika Saapuminen
            tuloksetkontrolleri.getTULOKSET_KESKPALVELUAIKA_SAAPUMISPISTE().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(19).doubleValue()));
            //Keskimääräinen Palveluaika Elektro
            tuloksetkontrolleri.getTULOKSET_KESKPALVELUAIKA_ELEKTRO().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(20).doubleValue()));
            //Keskimääräinen Palveluaika Palamaton
            tuloksetkontrolleri.getTULOKSET_KESKPALVELUAIKA_PALAMATON().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(21).doubleValue()));
            //Keskimääräinen Palveluaika Palava
            tuloksetkontrolleri.getTULOKSET_KESKPALVELUAIKA_PALAVA().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(22).doubleValue()));
            //Keskimääräinen Jäte (Asiakasjäte / palvelupistejäte /kokonaisjäte)
            tuloksetkontrolleri.getTULOKSET_KESKJATEMAARA_APKJ().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(23).doubleValue()));

            //INPUTS
            //INPUT VIIVE
            tuloksetkontrolleri.getTULOKSET_INPUT_VIIVE().setText(selectedItem.getParametrit().getViive() / 1000 + "/ Sekuntia");
            //INPUT_AIKA
            tuloksetkontrolleri.getTULOKSET_INPUT_AIKA().setText(selectedItem.getParametrit().getAika() + "/ AikaYksikköä");
            //INPUT MIN JA MAX
            tuloksetkontrolleri.getTULOKSET_INPUT_MIN_KG().setText(Double.toString(selectedItem.getParametrit().getVmin()));
            tuloksetkontrolleri.getTULOKSET_INPUT_MAX_KG().setText(Double.toString(selectedItem.getParametrit().getVmax()));

            //INPUT KG kesto per sec
            tuloksetkontrolleri.getTULOKSET_INPUT_AIKA_PER_KG().setText(Double.toString(selectedItem.getParametrit().getPurkunopeus()));
            //INPUT Aktiivisuus
            tuloksetkontrolleri.getTULOKSET_INPUT_AKTIIVISUUS().setText(selectedItem.getParametrit().getAktiivisuus());
            //INPUT_PROSENTTI_ELEKTRO
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_ELEKTRO().setText(Integer.toString(selectedItem.getParametrit().getJateTE()) + "%");
            //INPUT_PROSENTTI_PALAMATON
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_PALAMATON().setText(Integer.toString(selectedItem.getParametrit().getJateTPJ()) + "%");
            //INPUT_PROSENTTI_PALAVA
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_PALAVA().setText(Integer.toString(selectedItem.getParametrit().getJateTPNJ()) + "%");


        } else {
            System.out.println("Ei mitään valittuna ROW");
        }
    }
    //------------------------------------------------------------------------------

    @Override
    /**Haetaan käyttäjän syöttämä simulaatioaika.*/
    public double getAika() {
        return Double.parseDouble(simulointiAikaInput.getText());
    }

    @Override
    /**Haetaan simulaation viive.*/
    public long getViive() {
        return simulaatioViive;
    }

    @Override
    /**Haetaan käyttäjän valitsema aktiivisuus.*/
    public String getAktiivisuus() {
        //Radiobuttonin "getter" radiogroupista.
        RadioButton id = (RadioButton) aktiivisuusRadioGroup.getSelectedToggle();

        //switchcase joka katsoo mikä radiobutton on valittu.
        switch (id.getId()) {
            case "1":
                System.out.println("Aktiivisuus : Rauhallinen");
                id.getId();
                return "Rauhallinen";
            case "2":
                System.out.println("Aktiivisuus : Normaali");
                return "Normaali";
            case "3":
                System.out.println("Aktiivisuus : Ruuhka");
                return "Ruuhka";
        }
        return "Ei ole valittu aktiivisuuta (Eli normina pysyy)";
    }

    // Elektroniikka jonon pituus tekstin setteri
    public void setEJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_ELEKTRONIIKKAJATE.setText(String.valueOf(pituus));
                });
    }

    @Override
    /**Asetetaan Palavajätepiste jono tekstiin tällä hetkellä oleva jonon pituus.*/
    public void setPJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_PALAVAJATE.setText(String.valueOf(pituus));
                });
    }

    @Override
    public void setAloitaButtonText() {
        aloitaButton.setText("Käynnistä uudelleen");
    }

    @Override
    /**Asetetaan Saapumispiste jono tekstiin tällä hetkellä oleva jonon pituus.*/
    public void setSAAPUMINENJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_SAAPUMISIAYHT_COUNTER.setText(String.valueOf(pituus));
                });
    }

    @Override
    public Double getPurkuNopeus() {
        Double kgPerSec = Double.parseDouble(strategiaFXML_Controller.getSTRATEGIA_KGMAARA_SEKUNTEJA().getText());
        return kgPerSec;
    }

    @Override
    public boolean getAjeetaankoLoppuun() {
        return (strategiaFXML_Controller.getSTRATEGIA_JONOT_AJALOPPUUN().isSelected() ? true : false);
    }

    @Override
    /**Asetetaan Palaamattomanjätepisteen jono tekstiin tällä hetkellä oleva jonon pituus.*/
    public void setPTJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_PALAMATONJATE.setText(String.valueOf(pituus));
                });

    }

    @Override
    public int[] getVaihteluvali() {
        int[] vaihteluvali = new int[2];
        try {
            vaihteluvali[0] = Integer.parseInt(asiakasJateMIN_INPUT.getText());
            vaihteluvali[1] = Integer.parseInt(asiakasJateMAX_INPUT.getText());
            return vaihteluvali;
        } catch (Exception er) {
            er.printStackTrace();
        }
        return vaihteluvali;
    }

    @Override
    /**Haetaan käyttäjän syöttämät jätelaji prosentit.*/
    public int[] getJateLaijenProsentit() {
        int[] prosenttiLista = new int[0];
        if (elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti == 100) {
            prosenttiLista = new int[]{elektroJateProsentti, palamatonJateProsentti, palavaJateProsentti};
            System.out.println("Prosentit on tasan 100% yhteensä, se on hyvä!");
            return prosenttiLista;
        }
        return prosenttiLista;
    }

    @Override
    public IVisualisointi getVisualisointi() {
        try {
            naytto = new Visualisointi2(mainFXML_Controller, kontrolleri, this);
            return naytto;
        } catch (IOException e) {
            System.out.println("Visualisointia ei saadu kiini");
            throw new RuntimeException(e);

        }
    }

    @Override
    public void setAnimaationViive(int viive) {
        naytto.setAnimaationViive(viive);
    }
}
