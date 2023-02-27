package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SimulaatioData{
    private final DoubleProperty aika, jatteidenKokonaismaara;
    private final IntegerProperty id,vmin, vmax, jateTE, jateTPJ, jateTPNJ;
    private final ObjectProperty<LocalDate> paivamaara;

    public SimulaatioData(int id,LocalDate paivamaara,double aika, int vmin, int vmax, int jateTE, int jateTPJ, int jateTPNJ ,double jatteidenKokonaismaara){
        this.id = new SimpleIntegerProperty(id);
        this.paivamaara = new SimpleObjectProperty<LocalDate>(paivamaara);
        this.aika = new SimpleDoubleProperty(aika);
        this.vmin = new SimpleIntegerProperty(vmin);
        this.vmax = new SimpleIntegerProperty(vmax);
        this.jateTE = new SimpleIntegerProperty(jateTE);
        this.jateTPJ = new SimpleIntegerProperty(jateTPJ);
        this.jateTPNJ = new SimpleIntegerProperty(jateTPNJ);
        this.jatteidenKokonaismaara = new SimpleDoubleProperty(jatteidenKokonaismaara);
    }

    public DoubleProperty getAika() {
        return aika;
    }

    public DoubleProperty aikaProperty() {
        return aika;
    }

    public LocalDate getPaivamaara() {
        return paivamaara.get();
    }

    public ObjectProperty<LocalDate> paivamaaraProperty() {
        return paivamaara;
    }

    public double getJatteidenKokonaismaara() {
        return jatteidenKokonaismaara.get();
    }

    public DoubleProperty jatteidenKokonaismaaraProperty() {
        return jatteidenKokonaismaara;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getVmin() {
        return vmin.get();
    }

    public IntegerProperty vminProperty() {
        return vmin;
    }

    public int getVmax() {
        return vmax.get();
    }

    public IntegerProperty vmaxProperty() {
        return vmax;
    }

    public int getJateTE() {
        return jateTE.get();
    }

    public IntegerProperty jateTEProperty() {
        return jateTE;
    }

    public int getJateTPJ() {
        return jateTPJ.get();
    }

    public IntegerProperty jateTPJProperty() {
        return jateTPJ;
    }

    public int getJateTPNJ() {
        return jateTPNJ.get();
    }

    public IntegerProperty jateTPNJProperty() {
        return jateTPNJ;
    }
}
