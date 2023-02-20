package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualisointi extends Canvas implements IVisualisointi{

    private GraphicsContext gc;

    double i = 0;
    double j = 10;


    public Visualisointi(int w, int h) {
        super(w, h);
        gc = this.getGraphicsContext2D();
        tyhjennaNaytto();
    }


    public void tyhjennaNaytto() {
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void uusiAsiakas() {
        gc.setFill(Color.RED);
        gc.fillOval(i,j,10,10);

        i = (i + 10 % this.getWidth());
        //j = (j + 12) % this.getHeight();
        if (i==0) j+=10;
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
    public void moveAsiakasELEKTRO_PALAVA() {

    }

    @Override
    public void moveAsiakasPALAVA_ELEKTRO() {

    }

    @Override
    public void moveAsiakasELEKTRO_EPA() {

    }

    @Override
    public void moveAsiakasEPA_ELEKTRO() {

    }

    @Override
    public void moveAsiakasPA_EPA() {

    }

    @Override
    public void moveAsiakasEPA_PA() {

    }

    @Override
    public void setPALAVA_VARATTU(boolean onkovarattu) {

    }

    @Override
    public void setEPA_VARATTU(boolean onkovarattu) {

    }

    @Override
    public void setELEKTRO_VARATTU(boolean onkovarattu) {

    }

    @Override
    public void setSAAPUMINEN_VARATTU(boolean onkovarattu) {

    }

}
