package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulaatioData{
    private final IntegerProperty id;
    private final BooleanProperty simulaatioTyhjaksi;
    private final ObjectProperty<LocalDate> paivamaara;
    private static SimulaationParametrit parametrit;

    public SimulaatioData(int id,LocalDate paivamaara, int simulaatioTyhjaksi){
        this.id = new SimpleIntegerProperty(id);
        this.paivamaara = new SimpleObjectProperty<LocalDate>(paivamaara);
        if(simulaatioTyhjaksi == 0){
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(false);
        }else {
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(true);
        }
    }

    public static SimulaationParametrit getParametrit() {
        return parametrit;
    }

    public static void setParametrit(SimulaationParametrit parametrit) {
        SimulaatioData.parametrit = parametrit;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public boolean isSimulaatioTyhjaksi() {
        return simulaatioTyhjaksi.get();
    }

    public BooleanProperty simulaatioTyhjaksiProperty() {
        return simulaatioTyhjaksi;
    }

    public LocalDate getPaivamaara() {
        return paivamaara.get();
    }

    public ObjectProperty<LocalDate> paivamaaraProperty() {
        return paivamaara;
    }

    public class SimulaationParametrit{

        private final IntegerProperty vmin, vmax, jateTE, jateTPJ, jateTPNJ;
        private final DoubleProperty aika;

        public SimulaationParametrit(double aika, int vmin, int vmax,int jateTE, int jateTPJ, int jateTPNJ){
            this.aika = new SimpleDoubleProperty(aika);
            this.vmin = new SimpleIntegerProperty(vmin);
            this.vmax = new SimpleIntegerProperty(vmax);
            this.jateTE = new SimpleIntegerProperty(jateTE);
            this.jateTPJ = new SimpleIntegerProperty(jateTPJ);
            this.jateTPNJ = new SimpleIntegerProperty(jateTPNJ);
        }

         @Override
         public String toString() {
             return "SimulaationParametrit{" +
                     "vmin=" + vmin +
                     ", vmax=" + vmax +
                     ", jateTE=" + jateTE +
                     ", jateTPJ=" + jateTPJ +
                     ", jateTPNJ=" + jateTPNJ +
                     ", aika=" + aika +
                     '}';
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

        public double getAika() {
            return aika.get();
        }

        public DoubleProperty aikaProperty() {
            return aika;
        }
    }
    public class SimulaattorinTulokset{
        private HashMap<SimpleStringProperty, SimpleIntegerProperty> tuloksetInt;
        private HashMap<SimpleStringProperty, SimpleDoubleProperty> tuloksetDouble;
        public SimulaattorinTulokset(HashMap<SimpleStringProperty, SimpleIntegerProperty> tuloksetInt, HashMap<SimpleStringProperty, SimpleDoubleProperty> tuloksetDouble){
            this.tuloksetInt = tuloksetInt;
            this.tuloksetDouble = tuloksetDouble;
        }

        public HashMap<SimpleStringProperty, SimpleIntegerProperty> getTuloksetInt() {
            return tuloksetInt;
        }

        public HashMap<SimpleStringProperty, SimpleDoubleProperty> getTuloksetDouble() {
            return tuloksetDouble;
        }
    }

}
