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

    public void moveAsiakasELEKTRO_PALAVA();
    public void moveAsiakasPALAVA_ELEKTRO();
    public void moveAsiakasELEKTRO_EPA();
    public void moveAsiakasEPA_ELEKTRO();
    public void moveAsiakasPA_EPA();
    public void moveAsiakasEPA_PA();

    //Palvelupisteen valo menee punaiseksi kun asiakas on kyseisessä pisteessä.
    public void setPALAVA_VARATTU(boolean onkovarattu);
    public void setEPA_VARATTU(boolean onkovarattu);
    public void setELEKTRO_VARATTU(boolean onkovarattu);
    public void setSAAPUMINEN_VARATTU(boolean onkovarattu);

}
