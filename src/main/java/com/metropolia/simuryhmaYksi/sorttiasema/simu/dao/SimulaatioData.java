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

    @Override
    public String toString() {
        return "SimulaatioData{" +
                "id=" + id +
                ", simulaatioTyhjaksi=" + simulaatioTyhjaksi +
                ", paivamaara=" + paivamaara +
                ", parametrit=" + parametrit +
                ", tulokset=" + tulokset +
                '}';
    }

    public class SimulaationParametrit{

        private final IntegerProperty vmin, vmax, jateTE, jateTPJ, jateTPNJ;
        private final DoubleProperty aika;

        public SimulaationParametrit(double aika, int vmin, int vmax,int jateTE, int jateTPJ, int jateTPNJ){
            this.aika = new SimpleDoubleProperty(aika);
            this.viive = new SimpleIntegerProperty(viive);
            this.purkunopeus = new SimpleDoubleProperty(purkunopeus);
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
        private ArrayList<SimpleIntegerProperty> tuloksetINT;
        private ArrayList<SimpleDoubleProperty> tuloksetDOUBLE;

        SimulaattorinTulokset(ArrayList<SimpleIntegerProperty> tuloksetINT,ArrayList<SimpleDoubleProperty> tuloksetDOUBLE ){
            this.tuloksetINT = tuloksetINT;
            this.tuloksetDOUBLE = tuloksetDOUBLE;
        }

        public ArrayList<SimpleIntegerProperty> getTuloksetINT() {
            return tuloksetINT;
        }

        public ArrayList<SimpleDoubleProperty> getTuloksetDOUBLE() {
            return tuloksetDOUBLE;
        }

        @Override
        public String toString() {
            return "SimulaattorinTulokset{" +
                    "tuloksetINT=" + tuloksetINT +
                    ", tuloksetDOUBLE=" + tuloksetDOUBLE +
                    '}';
        }
    }

}
