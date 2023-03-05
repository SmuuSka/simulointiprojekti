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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {
    private TULOKSET_FXML_CONTROLLER TULOKSET_FXML_CONTROLLER;
    private Button aloitaButton, nopeutaButton, hidastaButton, strategiaButton, lopetaButton, strategiaNaytaTuloksetButton, tuloksetPoistaTulosButton;

    private TextField simulointiAikaInput, simulointiAikaViiveInput, asiakasJateMIN_INPUT, asiakasJateMAX_INPUT, elektroniikkaJatePROSENTTI,
            palavaJatePROSENTTI, palamatonJatePROSENTTI, asiakasPurku_KG_Sekunti;

    private RadioButton rauhallinenAktiivisuus, normaaliAktiivisuus, ruuhkainenAktiivisuus;

    private CheckBox asiakasAuto_Hajioa, saapumispisteOnglema,ajetaanLoppuun;

    private Label paaSim_ELEKTRO_JateCounter, paaSim_PALAVA_JateCounter, paaSim_PALAMATON_JateCounter,
            paaSim_JONOINFO_SAAPUMINEN, paaSim_SAAPUMISIAYHT_COUNTER, paaSim_JONOINFO_PALAVAJATE,
            paaSim_JONOINFO_ELEKTRONIIKKAJATE, paaSim_JONOINFO_PALAMATONJATE, paaSim_POISTUNUT_COUNTER;
    private int elektroJateProsentti = 0;
    private int palavaJateProsentti = 0;
    private int palamatonJateProsentti = 0;
    private int simulaatioAika = 0;

    private int simulaatioViive = 0;
    private ToggleGroup aktiivisuusRadioGroup;
    private Scene scene;

    private PÄÄSIMULAATORI_FXML_CONTROLLER mainFXML_Controller;
    private STRATEGIA_FXML_CONTROLLER strategiaFXML_Controller;
    private Parent root;
    private IKontrolleriVtoM kontrolleri;
    private Stage primaryStagePara;
    private IVisualisointi naytto;
    //-------------------------------------------------------------------------------------------

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
            primaryStagePara = primaryStage;
            primaryStage.getIcons().add(new Image("/uifxml/LOGO.png"));
            FXMLLoader loaderStrategia = new FXMLLoader(getClass().getResource("/uifxml/Strategia.fxml"));
            FXMLLoader loaderSIMU = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            strategiaFXML_Controller = new STRATEGIA_FXML_CONTROLLER(kontrolleri);
            mainFXML_Controller = new PÄÄSIMULAATORI_FXML_CONTROLLER(kontrolleri);
            loaderStrategia.setController(strategiaFXML_Controller);
            root = loaderStrategia.load();

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
            asiakasJateMIN_INPUT = strategiaFXML_Controller.getSTRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ();
            asiakasJateMAX_INPUT = strategiaFXML_Controller.getSTRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ();

            /// Purku aika per kg
            asiakasPurku_KG_Sekunti = strategiaFXML_Controller.getSTRATEGIA_KGMAARA_SEKUNTEJA();

            //Jäte Prosentti määrät (kuinka paljon tuodaan jätettä)
            elektroniikkaJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ();
            palavaJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ();
            palamatonJatePROSENTTI = strategiaFXML_Controller.getSTRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ();

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

            //Tapahtumat
            asiakasAuto_Hajioa = strategiaFXML_Controller.getSTRATEGIA_TAPAHTUMAT_ASIAKASAUTO();
            asiakasAuto_Hajioa.setId("4");

            saapumispisteOnglema = strategiaFXML_Controller.getSTRATEGIA_TAPAHTUMAT_SAAPUMISPISTEONGELMA();
            saapumispisteOnglema.setId("5");

            //Ajetaanko Tyhjäksi?
            ajetaanLoppuun = strategiaFXML_Controller.getSTRATEGIA_JONOT_AJALOPPUUN();
            ajetaanLoppuun.setId("6");

            //-------------------------------------------------------------------------------------------

            //-ASETETAAN STRATEGIA SCENE-//
            scene = new Scene(root);
            strategiaNaytaTuloksetButton.setOnAction(actionEvent -> {
                try {
                    kontrolleri.showTuloksetAction();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            //Siiry PÄÄSIMULAATIO IKKUNAAN KUN PAINETAAN OK NAPPIA STRATEGIASSA
            strategiaButton.setOnAction(event -> {
                try {
                    simulaatioAika = Integer.parseInt(simulointiAikaInput.getText());
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

                if (elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti != 100) {
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

                            try {
                                loaderSIMU.setController(mainFXML_Controller);
                                root = loaderSIMU.load();
                                scene = new Scene(root);

                                primaryStage.setScene(scene);

                                primaryStage.setTitle("Sortti-Asema Simu");

                            } catch (IOException er) {
                                System.out.println("PÄÄSIMULAATIO ei ladannut oikein.");
                                er.printStackTrace();

                            }
                            primaryStage.show();

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
                                        kontrolleri.setVisualisointi(getVisualisointi());
                                        try {
                                            kontrolleri.kaynnistaSimulointi();
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });

                                    hidastaButton.setOnAction(event2 -> {
                                        kontrolleri.hidasta();
                                    });

                                    nopeutaButton.setOnAction(event3 -> {
                                        kontrolleri.nopeuta();
                                    });
                                    lopetaButton.setOnAction(event4 -> {
                                        naytto.setPALAVA_VARATTU(false);
                                        naytto.setELEKTRO_VARATTU(false);
                                        naytto.setSAAPUMINEN_VARATTU(false);
                                        naytto.setEPA_VARATTU(false);
                                        kontrolleri.lopetaSimulointi();
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

            primaryStage.setScene(scene);

            primaryStage.setTitle("Sortti-Asema Simu");

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------------------------------------

    //INTERFACE METHOTID
    //  TULOKSET IKKUNA
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
                        tuloksetStage.setScene(scene);

                        TableView TABLE_VIEW_DATA = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA();
                            TableColumn<SimulaatioData, Integer> idCOLUMN = new TableColumn<SimulaatioData, Integer>("#");
                            idCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, Integer>("id"));
                            TableColumn<SimulaatioData, LocalDate> aikaCOLUMN = new TableColumn<SimulaatioData, LocalDate>("Päivämäärä");
                            aikaCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, LocalDate>("paivamaara"));
                            TableColumn<SimulaatioData, LocalDate> ajetaankoTyhjaksiCOLUMN = new TableColumn<SimulaatioData, LocalDate>("Ajetaanko Tyhjäksi");
                            ajetaankoTyhjaksiCOLUMN.setCellValueFactory(new PropertyValueFactory<SimulaatioData, LocalDate>("ajetaanTyhjaksi"));

                            TABLE_VIEW_DATA.getColumns().addAll(idCOLUMN, aikaCOLUMN);
                            aikaCOLUMN.setCellValueFactory(
                                    cellData -> cellData.getValue().paivamaaraProperty());
                            idCOLUMN.setCellValueFactory(
                                    cellData -> cellData.getValue().idProperty().asObject());
                            TABLE_VIEW_DATA.setItems(dataob);

                        tuloksetStage.setOnCloseRequest(event -> {
                            datatulokset.clear();
                        });

                            tuloksetStage.show();

                            TABLE_VIEW_DATA.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                                System.out.println(obs.getValue());
                                valittuData(obs, newSelection, TULOKSET_FXML_CONTROLLER);
                                SimulaatioData selectedItem = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().getSelectedItem();
                                //Poista valittu dataNappi.
                                tuloksetPoistaTulosButton.setOnAction(event -> {
                                    try {
                                        poistaData(selectedItem.getId());
                                        TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().getSelectedItem();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println("POISTETTU DATA");
                                });
                            });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    tuloksetPoistaTulosButton = TULOKSET_FXML_CONTROLLER.getTULOKSET_POISTANAPPI();


                });
    }

    @Override
    public void poistaData(int ID) throws SQLException {
        kontrolleri.poistaTulos(ID);
    }

    public void valittuData(ObservableValue obs, Object newSelection, TULOKSET_FXML_CONTROLLER tuloksetkontrolleri){
        if (newSelection != null) {
            SimulaatioData selectedItem = TULOKSET_FXML_CONTROLLER.getTABLE_VIEW_DATA().getSelectionModel().getSelectedItem();
            //SIMUAIKA TULOS
            tuloksetkontrolleri.getTULOKSET_SIMUAIKA().setText(Double.toString(selectedItem.getParametrit().aikaProperty().doubleValue()) + "/Simulaattoriin syötetty aika");
//            //ROSKEN KOKONAIS MÄÄRÄ
          //tuloksetkontrolleri.getTULOKSET_HEITETTY_YHT().setText(Double.toString(selectedItem.getTulokset().getTuloksetDOUBLE().get(1).doubleValue()) + " Kg");
            //ELEKTROJÄTE
            //PALAVAJÄTE
            //PALAMATONJÄTE

            //INPUTS
            //INPUT_AIKA
            tuloksetkontrolleri.getTULOKSET_INPUT_AIKA().setText(Double.toString(selectedItem.getParametrit().getAika()) + "/AikaYksikköä");
            //INPUT_VIIVE

            //INPUT_PROSENTTI_ELEKTRO
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_ELEKTRO().setText(Integer.toString(selectedItem.getParametrit().getJateTE()) + "%");
            //INPUT_PROSENTTI_PALAMATON
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_PALAMATON().setText(Integer.toString(selectedItem.getParametrit().getJateTPJ())+"%");
            //INPUT_PROSENTTI_PALAVA
            tuloksetkontrolleri.getTULOKSET_INPUT_PROSENTTI_PALAVA().setText(Integer.toString(selectedItem.getParametrit().getJateTPNJ())+"%");


        } else {
            System.out.println("Ei mitään valittuna ROW");
        }
    }
    //------------------------------------------------------------------------------

    @Override
    public double getAika() {
        return Double.parseDouble(simulointiAikaInput.getText());
    }

    @Override
    public long getViive() {
        return simulaatioViive;
    }

    @Override
    public double getAsiakasKgPerSekunti() {
        return Double.parseDouble(asiakasPurku_KG_Sekunti.getText());
    }

    @Override
    public int[] getJatelajiProsentit() {
        return new int[0];
    }


    @Override
    public int getRuuhkaAika() {
        //Radiobuttonin "getter" radiogroupista.
        RadioButton id = (RadioButton) aktiivisuusRadioGroup.getSelectedToggle();

        //switchcase joka katsoo mikä radiobutton on valittu.
        switch (id.getId()) {
            case "1":
                System.out.println("Aktiivisuus : Rauhallinen");
                return 1;
            case "2":
                System.out.println("Aktiivisuus : Normaali");
                return 2;
            case "3":
                System.out.println("Aktiivisuus : Ruuhka");
                return 3;
        }
        return 0;
    }

    @Override
    public int getStrategiaTapahtumat() {

        //Palautaa Valitun TAPAHTUMAN
        if (asiakasAuto_Hajioa.isSelected() == true && saapumispisteOnglema.isSelected() == false) {
            System.out.println("TAPAHTUMA MAHDOLLISUUS : ASIAKKAAN AUTO HAJOOA");
            return 1;
        } else if (saapumispisteOnglema.isSelected() == true && asiakasAuto_Hajioa.isSelected() == false) {
            System.out.println("TAPAHTUMA MAHDOLLISUUS : SAAPUMISPISTEESSÄ VOI OLLA ONGELMIA");
            return 2;
        } else if (saapumispisteOnglema.isSelected() == true && asiakasAuto_Hajioa.isSelected() == true) {
            System.out.println("TAPAHTUMA MAHDOLLISUUS : SAAPUMISPISTEESSÄ VOI OLLA ONGELMIA JA ASIAKKAAN AUTO HAJOOA");
            return 3;
        }
        System.out.println("TAPAHTUMA MAHDOLLISUUS : EI VALITTU MITÄÄN");
        return 0;
    }

    @Override
    public int getElektro_JateCounter() {
        return Integer.parseInt(paaSim_ELEKTRO_JateCounter.getText());
    }

    // Elektroniikka jonon pituus tekstin setteri
    public void setEJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_ELEKTRONIIKKAJATE.setText(String.valueOf(pituus));
                });
    }

    @Override
    public int getPalavaJateCounter() {
        return Integer.parseInt(paaSim_PALAVA_JateCounter.getText());
    }

    @Override
    public void setPJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_PALAVAJATE.setText(String.valueOf(pituus));
                });
    }

    @Override
    public void setSAAPUMINENJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_SAAPUMISIAYHT_COUNTER.setText(String.valueOf(pituus));
                });
    }

    @Override
    public int getPalamatonJateCounter() {
        return Integer.parseInt(paaSim_PALAMATON_JateCounter.getText());
    }

    @Override
    public boolean getAjeetaankoLoppuun() {
        if(strategiaFXML_Controller.getSTRATEGIA_JONOT_AJALOPPUUN().isSelected() == true){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public STRATEGIA_FXML_CONTROLLER getStrategiaController() {
        return strategiaFXML_Controller;
    }

    @Override
    public void setPTJateJonossa(int pituus) {
        Platform.runLater(
                () -> {
                    paaSim_JONOINFO_PALAMATONJATE.setText(String.valueOf(pituus));
                });

    }

    @Override
    public void setLoppuaika() {

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
