package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriVtoM;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.Kontrolleri;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Trace;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class SimulaattoriGUI extends Application implements ISimulaattoriUI {
    private IKontrolleriVtoM kontrolleri;
    // Käyttöliittymäkomponentit:
    private TextField aika;
    private TextField viive;
    private Label tulos;
    private Label aikaLabel;
    private Label viiveLabel;
    private Label tulosLabel;

    private Button kaynnistaButton;
    private Button hidastaButton;
    private Button nopeutaButton;

    private IVisualisointi naytto;

    @Override
    public void init() {
        Trace.setTraceLevel(Trace.Level.INFO);
        kontrolleri = new Kontrolleri(this);

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

//            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent t) {
//                    Platform.exit();
//                    System.exit(0);
//                }
//            });


            primaryStage.setTitle("Simulaattori");

            kaynnistaButton = new Button();
            kaynnistaButton.setText("Käynnistä simulointi");
            kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //Kun "Käynnistä simulointi"-näppäintä on painettu,
                    //Kutsutaan Kontrolli-luokan metodia kaynnistaSimulointi()
                    //moottori käynnistyy
                    kontrolleri.kaynnistaSimulointi();
                    kaynnistaButton.setDisable(true);
                }
            });

            hidastaButton = new Button();
            hidastaButton.setText("Hidasta");
            hidastaButton.setOnAction(e -> kontrolleri.hidasta());

            nopeutaButton = new Button();
            nopeutaButton.setText("Nopeuta");
            nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

            aikaLabel = new Label("Simulointiaika:");
            aikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            aika = new TextField("Syötä aika");
            aika.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            aika.setPrefWidth(150);

            viiveLabel = new Label("Viive:");
            viiveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            viive = new TextField("Syötä viive");
            viive.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            viive.setPrefWidth(150);

            tulosLabel = new Label("Kokonaisaika:");
            tulosLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            tulos = new Label();
            tulos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            tulos.setPrefWidth(150);

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(15, 12, 15, 12)); // marginaalit ylä, oikea, ala, vasen
            hBox.setSpacing(10);   // noodien välimatka 10 pikseliä

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(10);
            grid.setHgap(5);

            grid.add(aikaLabel, 0, 0);   // sarake, rivi
            grid.add(aika, 1, 0);          // sarake, rivi
            grid.add(viiveLabel, 0, 1);      // sarake, rivi
            grid.add(viive, 1, 1);           // sarake, rivi
            grid.add(tulosLabel, 0, 2);      // sarake, rivi
            grid.add(tulos, 1, 2);           // sarake, rivi
            grid.add(kaynnistaButton,0, 3);  // sarake, rivi
            grid.add(nopeutaButton, 0, 4);   // sarake, rivi
            grid.add(hidastaButton, 1, 4);   // sarake, rivi

            naytto = new Visualisointi2(400,200);

            // Täytetään boxi:
            hBox.getChildren().addAll(grid, (Canvas)naytto);

            Scene scene = new Scene(hBox);
            primaryStage.setScene(scene);
            primaryStage.show();



        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public double getAika() {
        return Double.parseDouble(aika.getText());
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