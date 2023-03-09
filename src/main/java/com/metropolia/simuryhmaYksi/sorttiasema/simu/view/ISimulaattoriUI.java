package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.dao.SimulaatioData;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface ISimulaattoriUI {
    void showTulokset(ArrayList<SimulaatioData> dataColumn);

    void poistaData(int ID) throws SQLException;

    double getAika();

    long getViive();

    //Asiakaan Jäte MIN ja MAX määrä getteri.
    int[] getVaihteluvali();

    int[] getJateLaijenProsentit();

    Double getPurkuNopeus();

    boolean getAjeetaankoLoppuun();

    IVisualisointi getVisualisointi();

    void setEJateJonossa(int pituus);

    void setPTJateJonossa(int pituus);

    void setPJateJonossa(int pituus);

    void setSAAPUMINENJonossa(int pituus);

    void setAnimaationViive(int viive);

    // Käynnistä button teksti
    void setAloitaButtonText();

    String getAktiivisuus();
}
