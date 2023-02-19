package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface IVisualisointi {
    public void tyhjennaNaytto();
    public void uusiAsiakas();
    // Asiakaan liikumisen methodit (kutsutaan kun asiakas siirtyy johonkin jätelava pisteelle tai poistuu sieltä)
    public void moveAsiakasPALAVA();
    public void moveAsiakasEPA();
    public void moveAsiakasELEKTRO();

    public void moveAsiakasPALAVA_POISTUMINEN();
    public void moveAsiakasEPA_POISTUMINEN();
    public  void moveAsiakasELEKTRO_POISTUMINEN();

    //Palvelupisteen valo menee punaiseksi kun asiakas on kyseisessä pisteessä.
    public void setPALAVA_VARATTU();
    public void setEPA_VARATTU();
    public void setELEKTRO_VARATTU();


}
