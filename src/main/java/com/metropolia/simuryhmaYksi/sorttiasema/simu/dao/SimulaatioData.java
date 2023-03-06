package com.metropolia.simuryhmaYksi.sorttiasema.simu.dao;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public class SimulaatioData{
    private final IntegerProperty id;
    private final BooleanProperty simulaatioTyhjaksi;
    private final ObjectProperty<LocalDate> paivamaara;
    private SimulaationParametrit parametrit;
    private SimulaattorinTulokset tulokset;

    public SimulaatioData(int id,LocalDate paivamaara, int simulaatioTyhjaksi){
        this.id = new SimpleIntegerProperty(id);
        this.paivamaara = new SimpleObjectProperty<LocalDate>(paivamaara);
        if(simulaatioTyhjaksi == 0){
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(false);
        }else {
            this.simulaatioTyhjaksi = new SimpleBooleanProperty(true);
        }
    }

    public SimulaationParametrit getParametrit() {
        return parametrit;
    }

    public void setParametrit(SimulaationParametrit parametrit) {
        this.parametrit = parametrit;
    }

    public SimulaattorinTulokset getTulokset() {
        return tulokset;
    }

    public void setTulokset(SimulaattorinTulokset tulokset) {
        this.tulokset = tulokset;
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
        private final DoubleProperty aika, purkunopeus, viive;

        public SimulaationParametrit(double aika,double viive, double purkunopeus, int vmin, int vmax,int jateTE, int jateTPNJ,int jateTPJ){
            this.aika = new SimpleDoubleProperty(aika);
            this.viive = new SimpleDoubleProperty(viive);
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
                    ", purkunopeus=" + purkunopeus +
                    ", viive=" + viive +
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

        public double getPurkunopeus() {
            return purkunopeus.get();
        }

        public DoubleProperty purkunopeusProperty() {
            return purkunopeus;
        }

        public double getViive() {
            return viive.get();
        }

        public DoubleProperty viiveProperty() {
            return viive;
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
