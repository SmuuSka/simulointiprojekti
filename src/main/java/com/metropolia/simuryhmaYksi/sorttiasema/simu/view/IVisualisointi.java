package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

public interface IVisualisointi {
    public void tyhjennaNaytto();

    void lisaaSaapumistenMaara(int maara);

    void lisaaPoistunutMaara(int maara);

    void uusiAsiakas();
    // Asiakaan liikumisen methodit (kutsutaan kun asiakas siirtyy johonkin jätelava pisteelle tai poistuu sieltä)
    void moveAsiakasPALAVA();
    void moveAsiakasEPA();
    void moveAsiakasELEKTRO();

    void moveAsiakasPALAVA_POISTUMINEN();
    void moveAsiakasEPA_POISTUMINEN();
    void moveAsiakasELEKTRO_POISTUMINEN();

    void moveAsiakasELEKTRO_PALAVA();
    void moveAsiakasPALAVA_ELEKTRO();
    void moveAsiakasELEKTRO_EPA();
    void moveAsiakasEPA_ELEKTRO();
    void moveAsiakasPA_EPA();
    void moveAsiakasEPA_PA();

    //Palvelupisteen valo menee punaiseksi kun asiakas on kyseisessä pisteessä.
    void setPALAVA_VARATTU(boolean onkovarattu);
    void setEPA_VARATTU(boolean onkovarattu);
    void setELEKTRO_VARATTU(boolean onkovarattu);
    void setSAAPUMINEN_VARATTU(boolean onkovarattu);

    void setELEKTRO_COUNTER(double value);

    void setPALAVA_COUNTER(double value);

    void setPALAMATON_COUNTER(double value);

    void addJONOPALIKKA_PALAVA();

    void addJONOPALIKKA_EPA();

    void addJONOPALIKKA_SAAPUMINEN();

    void addJONOPALIKKA_ELEKTRO();

    void removeJONOPALIKKA_PALAVA(int sizenow);

    void removeJONOPALIKKA_EPA(int sizenow);

    void removeJONOPALIKKA_ELEKTRO(int sizenow);

    void removeJONOPALIKKA_SAAPUMINEN(int sizenow);

    void setAnimaationViive(int viive);

}
