package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulaattoriGUIver2 extends Application implements ISimulaattoriUI {
    private AnchorPane MainSoftwarePane_STRATEGIA;

    private Button aloitaButton, nopeutaButton, hidastaButton, strategiaButton,lopetaButton;

    private TextField simulointiAikaInput,simulointiAikaViiveInput, asiakasJateMIN_INPUT, asiakasJateMAX_INPUT, elektroniikkaJatePROSENTTI,
            palavaJatePROSENTTI, palamatonJatePROSENTTI, asiakasPurku_KG_Sekunti;

    private RadioButton RauhallinenAktiivisuus, NormaaliAktiivisuus, RuuhkainenAktiivisuus;

    private CheckBox AsiakasAuto_Hajioa, SaapumispisteOnglema;

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

    private FXML_CONTROLLER FXMLcontroller;
    private Parent root;
    private IKontrolleriVtoM kontrolleri;

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
            FXMLLoader loaderStrategia = new FXMLLoader(getClass().getResource("/uifxml/Strategia.fxml"));
            FXMLLoader loaderSIMU = new FXMLLoader(getClass().getResource("/uifxml/ui.fxml"));
            FXMLcontroller = new FXML_CONTROLLER(kontrolleri);
            loaderStrategia.setController(FXMLcontroller);
            root = loaderStrategia.load();

            //Hae STRATEGIA Napit FXML CONTROLLERISTA

            // (TÄÄLÄ ON KAIKKI STRATEGIA NÄKYMÄN ELEMENTIT)//
            //Strategia näkymän napit.
            strategiaButton = FXMLcontroller.getSTRATEGIA_SIIRY_SIMULAATIOON();

            //Hae TextFields FXML CONTROLLERISTA//

            //SimulointiAika ja viive
            simulointiAikaInput = FXMLcontroller.getSTRATEGIA_SIMULOINTIAIKA();
            simulointiAikaViiveInput = FXMLcontroller.getSTRATEGIA_SIMULOINTIVIIVE();

            //Min ja Max kg määrä per asiakas.
            asiakasJateMIN_INPUT = FXMLcontroller.getSTRATEGIA_ASIAKAS_MIN_JÄTEMÄÄRÄ();
            asiakasJateMAX_INPUT = FXMLcontroller.getSTRATEGIA_ASIAKAS_MAX_JÄTEMÄÄRÄ();

            /// Purku aika per kg
            asiakasPurku_KG_Sekunti = FXMLcontroller.getSTRATEGIA_KGMAARA_SEKUNTEJA();

            //Jäte Prosentti määrät (kuinka paljon tuodaan jätettä)
            elektroniikkaJatePROSENTTI = FXMLcontroller.getSTRATEGIA_ELEKTRONIIKKAJÄTE_PROSENTTIMÄÄRÄ();
            palavaJatePROSENTTI = FXMLcontroller.getSTRATEGIA_PALAVAJÄTE_PROSENTTIMÄÄRÄ();
            palamatonJatePROSENTTI = FXMLcontroller.getSTRATEGIA_PALAAMATONJÄTE_PROSENTTIMÄÄRÄ();

            //RadioButtonGroup
            aktiivisuusRadioGroup = FXMLcontroller.getAktiivisuusGroup();
            //-------------------------------------------------------------------------------------------

            //Hae Checkboxit/RadioButtonit FXML CONTROLLERISTA//

            //Aktiivisuus
            RauhallinenAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_RAUHALLINEN_CHECK();
            RauhallinenAktiivisuus.setId("1");

            NormaaliAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_NORMAALIA_CHECK();
            NormaaliAktiivisuus.setId("2");

            RuuhkainenAktiivisuus = FXMLcontroller.getSTRATEGIA_RUUHKA_RUUHKA_CHECK();
            RuuhkainenAktiivisuus.setId("3");

            //Tapahtumat
            AsiakasAuto_Hajioa = FXMLcontroller.getSTRATEGIA_TAPAHTUMAT_ASIAKASAUTO();
            AsiakasAuto_Hajioa.setId("4");

            SaapumispisteOnglema = FXMLcontroller.getSTRATEGIA_TAPAHTUMAT_SAAPUMISPISTEONGELMA();
            SaapumispisteOnglema.setId("5");

            //-------------------------------------------------------------------------------------------

            //-ASETETAAN STRATEGIA SCENE-//
            scene = new Scene(root);

            //Siiry PÄÄSIMULAATIO IKKUNAAN KUN PAINETAAN OK NAPPIA STRATEGIASSA
            strategiaButton.setOnAction(event -> {
                try{
                    simulaatioAika = Integer.parseInt(simulointiAikaInput.getText());
                    simulaatioViive = Integer.parseInt(simulointiAikaViiveInput.getText());
                    elektroJateProsentti = Integer.parseInt(elektroniikkaJatePROSENTTI.getText());
                    palavaJateProsentti = Integer.parseInt(palavaJatePROSENTTI.getText());
                    palamatonJateProsentti = Integer.parseInt(palamatonJatePROSENTTI.getText());
                }catch(NumberFormatException numberex){
                    System.out.println("Kaikkiin kenttiin pitää syöttää numero arvoja.");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Varoitus");
                    alert.setHeaderText("Varoitus:");
                    alert.setContentText("Syöte kenttiin ei voi syötää kirjaimia arvona!");
                    alert.show();
                }

                if(elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti != 100){
                   int summa = elektroJateProsentti + palavaJateProsentti + palamatonJateProsentti;
                    System.out.println("Jäteprosentti luvut pitää olla yhteensä 100%, sinulla on " + summa + "%" );
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Varoitus");
                    alert.setHeaderText("Varoitus:");
                    alert.setContentText("Prosentit pitää olla tasan 100% yhteensä, sinulla on " + summa + "%");
                    alert.show();
                }else{
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
                                    loaderSIMU.setController(FXMLcontroller);
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
                                    aloitaButton = FXMLcontroller.getBUTTON_ALOITA();
                                    hidastaButton = FXMLcontroller.getBUTTON_HITAAMMIN();
                                    nopeutaButton = FXMLcontroller.getBUTTON_NOPEAMMIN();
                                    lopetaButton = FXMLcontroller.getBUTTON_LOPETA();

                                    ///PÄÄSIMULAAATORI Teksti/Label elementit

                                    //POISHEITETTYJATE COUNTERIT
                                    paaSim_ELEKTRO_JateCounter = FXMLcontroller.getELEKTRO_POISHEITETTY_NUM();
                                    paaSim_PALAVA_JateCounter = FXMLcontroller.getPA_POISHEITETTY_NUM();
                                    paaSim_PALAMATON_JateCounter = FXMLcontroller.getEPA_POISHEITETTY_NUM();

                                    ///JONOSSA COUNTER LABEL/TEXT

                                    //PALAVAJÄTE JONO
                                    paaSim_JONOINFO_PALAVAJATE = FXMLcontroller.getJONOSSAINFO_PA();

                                    //ELEKTRONIIKA JONO
                                    paaSim_JONOINFO_ELEKTRONIIKKAJATE = FXMLcontroller.getJONOSSAINFO_ELEKTRO();

                                    //PALAMATONJÄTE JONO
                                    paaSim_JONOINFO_PALAMATONJATE = FXMLcontroller.getJONOSSAINFO_EPA();

                                    //SAAPUMISEN JONO
                                    paaSim_JONOINFO_SAAPUMINEN = FXMLcontroller.getJONOSSA_SAAPUMINEN();

                                    //SAAPUMISTEN MÄÄRÄ TÄLLÄ HETKELLÄ LABLE
                                    paaSim_SAAPUMISIAYHT_COUNTER = FXMLcontroller.getJONOSSAINFO_SAAPUMINEN();

                                    //POISTUNUT ASIAKKAAT YHTEENSÄ TÄLLÄ HETKELLÄ LABLE
                                    paaSim_POISTUNUT_COUNTER = FXMLcontroller.getPOISTUNUTINFO();


                                    System.out.println("Siirytään Pääsimulaatorille.");
                                    try {
                                        aloitaButton.setOnAction(event1 -> {
                                            kontrolleri.setVisualisointi(getVisualisointi());
                                            kontrolleri.kaynnistaSimulointi();
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
        if (AsiakasAuto_Hajioa.isSelected() == true && SaapumispisteOnglema.isSelected() == false) {
            System.out.println("TAPAHTUMA MAHDOLLISUUS : ASIAKKAAN AUTO HAJOOA");
            return 1;
        } else if (SaapumispisteOnglema.isSelected() == true && AsiakasAuto_Hajioa.isSelected() == false) {
            System.out.println("TAPAHTUMA MAHDOLLISUUS : SAAPUMISPISTEESSÄ VOI OLLA ONGELMIA");
            return 2;
        } else if (SaapumispisteOnglema.isSelected() == true && AsiakasAuto_Hajioa.isSelected() == true) {
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
    public void setEJateJonossa(int pituus){
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
    public int getPalamatonJateCounter() {
        return Integer.parseInt(paaSim_PALAMATON_JateCounter.getText());
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
            prosenttiLista = new int[]{elektroJateProsentti, palavaJateProsentti, palamatonJateProsentti};
            System.out.println("Prosentit on tasan 100% yhteensä, se on hyvä!");
            return prosenttiLista;
        }
            return prosenttiLista;
    }

    @Override
    public IVisualisointi getVisualisointi() {
        try {
            naytto = new Visualisointi2(FXMLcontroller,kontrolleri,this);
            return naytto;
        } catch (IOException e) {
            System.out.println("Visualisointia ei saadu kiini");
            throw new RuntimeException(e);

        }
    }

    

}
