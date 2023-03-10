package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;
/**
 * @Author Kaspar Tullus,Samu Aikio, Joel Tikkanen
 */

/**
 * Täälä hoitetaan kaikki animaatioon liityvät methotid
 */
public interface IVisualisointi {
    /**
     * Resetoi animaatio näkymän.
     */
    void tyhjennaNaytto();
    /**
     * Tässä lisätään saapumisen määrää visualisointia varten, kutsutaan joka kerta kun on saapuminen.
     * @param maara Lisätään visualintia varten saapumis määrät.
     */
    void lisaaSaapumistenMaara(int maara);
    /**
     * Tässä lisätään saapumisen määrää visualisointia varten, kutsutaan joka kerta kun on poistuminen.
     * @param maara Lisätään visualintia varten poistumis määrät.
     */
    void lisaaPoistunutMaara(int maara);
    // Asiakaan liikumisen methodit (kutsutaan kun asiakas siirtyy johonkin jätelava pisteelle tai poistuu sieltä)
    /**
     *Animoitaan Asiakaan siirtyminen PALAVA JÄTE  pisteelle*/
    void moveAsiakasPALAVA();
    /**
     *Animoitaan Asiakaan siirtyminen PALAMATON JÄTE pisteelle*/
    void moveAsiakasEPA();
    /**
     *Animoitaan Asiakaan siirtyminen ELEKTRONIIKKA JÄTE pisteelle*/
    void moveAsiakasELEKTRO();
    /**
     *Animoitaan Asiakaan siirtyminen PALAVA ---> POISTUMINEN*/
    void moveAsiakasPALAVA_POISTUMINEN();
    /**
     *Animoitaan Asiakaan siirtyminen PALAMATON ---> POISTUMINEN*/
    void moveAsiakasEPA_POISTUMINEN();
    /**
     *Animoitaan Asiakaan siirtyminen ELEKTRONIIKKA---> POISTUMINEN*/
    void moveAsiakasELEKTRO_POISTUMINEN();

    /**
     *Animoitaan Asiakaan siirtyminen ELEKTRONIIKKA ---> PALAVA*/
    void moveAsiakasELEKTRO_PALAVA();
    /**
     *Animoitaan Asiakaan siirtyminen PALAVA ---> ELEKTRONIIKKA*/
    void moveAsiakasPALAVA_ELEKTRO();
    /**
     *Animoitaan Asiakaan siirtyminen ELEKTRONIIKKA ---> PALAMATON*/
    void moveAsiakasELEKTRO_EPA();
    /**
     *Animoitaan Asiakaan siirtyminen PALAMATON ---> ELEKTRONIIKKA*/
    void moveAsiakasEPA_ELEKTRO();
    /**
     *Animoitaan Asiakaan siirtyminen PALAVA ---> PALAMATON*/
    void moveAsiakasPA_EPA();
    /**
     *Animoitaan Asiakaan siirtyminen PALAMATON ---> PALAVA*/
    void moveAsiakasEPA_PA();

    //Palvelupisteen valo menee punaiseksi kun asiakas on kyseisessä pisteessä.
    /**
     *Animoitaan PALAVA JÄTE palvelupisteen onko varattu valoja.
     * @param onkovarattu Annaetaan false tai true ( jos false palvelupiste on vapaa, jos true se on varattu.*/
    void setPALAVA_VARATTU(boolean onkovarattu);
    /**
     *Animoitaan PALAMATON JÄTE palvelupisteen onko varattu valoja.
     * @param onkovarattu Annaetaan false tai true ( jos false palvelupiste on vapaa, jos true se on varattu.*/
    void setEPA_VARATTU(boolean onkovarattu);
    /**
     *Animoitaan ELEKTRONIIKKA JÄTE palvelupisteen onko varattu valoja.
     * @param onkovarattu Annaetaan false tai true ( jos false palvelupiste on vapaa, jos true se on varattu.*/
    void setELEKTRO_VARATTU(boolean onkovarattu);
    /**
     *Animoitaan Saapmumisen palvelupisteen onko varattu valoja.
     * @param onkovarattu Annaetaan false tai true ( jos false palvelupiste on vapaa, jos true se on varattu.*/
    void setSAAPUMINEN_VARATTU(boolean onkovarattu);

    /**
     *Lasketaan ELEKTRONIIKKA JÄTE pisteen pois heitetty kokonaisjäte visualisointiin.
     * @param value Annetaan tämän hetkinen poisheitetty jätteen kokonaismäärä*/
    void setELEKTRO_COUNTER(double value);
    /**
     *Lasketaan PALAVA JÄTE pisteen pois heitetty kokonaisjäte visualisointiin.
     * @param value Annetaan tämän hetkinen poisheitetty jätteen kokonaismäärä*/
    void setPALAVA_COUNTER(double value);
    /**
     *Lasketaan PALAMATON JÄTE pisteen pois heitetty kokonaisjäte visualisointiin.
     * @param value Annetaan tämän hetkinen poisheitetty jätteen kokonaismäärä*/
    void setPALAMATON_COUNTER(double value);

    /**
     *Lisätään Visualisointiin Jono, (PALAVA JÄTE)*/
    void addJONOPALIKKA_PALAVA();
    /**
     *Lisätään Visualisointiin Jono, (PALAMATON JÄTE)*/
    void addJONOPALIKKA_EPA();
    /**
     *Lisätään Visualisointiin Jono, (SAAPMUMINEN)*/
    void addJONOPALIKKA_SAAPUMINEN();
    /**
     *Lisätään Visualisointiin Jono, (ELEKTRONIIKKA JÄTE)*/
    void addJONOPALIKKA_ELEKTRO();
    /**
     *Poistetaan Visualisointiin Jono, (PALAVA JÄTE)*/
    void removeJONOPALIKKA_PALAVA(int sizenow);
    /**
     *Poistetaan Visualisointiin Jono, (PALAMATON JÄTE)*/
    void removeJONOPALIKKA_EPA(int sizenow);
    /**
     *Poistetaan Visualisointiin Jono, (ELEKTRONIIKKA JÄTE)*/
    void removeJONOPALIKKA_ELEKTRO(int sizenow);
    /**
     *Poistetaan Visualisointiin Jono, (SAAPMUMINEN)*/
    void removeJONOPALIKKA_SAAPUMINEN(int sizenow);
    /**Asenetaan animaation nopeus
     * @param viive Animaation Viive (Millisekunteina)*/
    void setAnimaationViive(int viive);

}
