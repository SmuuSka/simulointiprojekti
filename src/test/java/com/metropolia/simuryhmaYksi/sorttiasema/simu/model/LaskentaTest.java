package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.Kello;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public class LaskentaTest {

    private final double DELTA = 0.001;

    private int saapuneidenLkm = 200;
    private int[] palveltujenLkm = {50,50,50,50};
    private double[] aktiiviajat = {2000, 300, 200, 100};
    private long[] kokonaisoleskeluajat = {300, 200, 100, 500};
    private double jatteenKokonaismaara = 4200.2;
    private double kokonaisaika = 20;
    

    Laskenta l = new Laskenta(saapuneidenLkm, palveltujenLkm, aktiiviajat, kokonaisoleskeluajat, jatteenKokonaismaara);

    @Test
    void testGetAktiiviajat() {
        l.laske();
        assertArrayEquals(aktiiviajat, l.getAktiiviajat(), DELTA, "getJatteenKokonaismaara ei toimi oikein.");
    }

    @Test
    void testGetJatteenKokonaismaara() {
        l.laske();
        assertEquals(jatteenKokonaismaara, l.getJatteenKokonaismaara(), DELTA,  "getJatteenKokonaismaara ei toimi oikein.");
    }

    @Test
    void testGetKayttoasteet() {
        l.laske();
        double[] kayttoasteet = new double[4];
        Kello.getInstance().setAika(kokonaisaika);
        for (int i = 0; i < 4; i++){
            kayttoasteet[i] = aktiiviajat[i]/kokonaisaika;
        }

        assertArrayEquals(kayttoasteet, l.getKayttoasteet(), DELTA, "getKayttoasteet ei toimi oikein.");
    }

    @Test
    void testGetKeskmJatteenmaara() {
        l.laske();
        int plkm = 0;
        for (int i : palveltujenLkm) plkm+=i;
        System.out.println(plkm);;
        double keskmJatteenMaara = jatteenKokonaismaara/plkm;
        System.out.println(keskmJatteenMaara);
        assertEquals(keskmJatteenMaara, l.getKeskmJatteenmaara(), DELTA, "getKeskmJatteenmaara ei toimi oikein.");
    }

    @Test
    void testGetKeskmJononpituudet() {
        l.laske();
        double[] jp = new double[4];
        Kello.getInstance().setAika(kokonaisaika);
        for (int i = 0; i < 4; i++){
            jp[i] = kokonaisoleskeluajat[i]/kokonaisaika;
        }
        assertArrayEquals(jp, l.getKeskmJononpituudet(), DELTA, "getKeskmJononPituudet ei toimi oikein.");
    }

    @Test
    void testGetKeskmLapimenoajat() {
        l.laske();
        double[] keskmla = new double[4];
        for (int i = 0;i<4;i++){
            keskmla[i] = kokonaisoleskeluajat[i]/palveltujenLkm[i];
        }
        assertArrayEquals(keskmla, l.getKeskmLapimenoajat(),  "getKeskmLapimenoajat ei toimi oikein.");
    }

    @Test
    void testGetKeskmPalveluajat() {
        l.laske();
        double[] keskmpa = new double[4];
        for (int i = 0;i<4;i++){
            keskmpa[i] = aktiiviajat[i]/palveltujenLkm[i];
        }
        assertArrayEquals(keskmpa, l.getKeskmPalveluajat(), "getKeskmPalveluajat ei toimi oikein.");
    }

    @Test
    void testGetKokonaisaika() {
        Kello.getInstance().setAika(kokonaisaika);
        assertEquals(kokonaisaika, Kello.getInstance().getAika(), "Kokonaisaika ei toimi oikein.");
    }

    @Test
    void testGetKokonaisoleskeluajat() {
        l.laske();
        assertArrayEquals(kokonaisoleskeluajat, l.getKokonaisoleskeluajat(),"getJatteenKokonaismaara ei toimi oikein.");
    }

    @Test
    void testGetPalveltujenLkm() {
        l.laske();
        assertArrayEquals(palveltujenLkm, l.getPalveltujenLkm(), "PalveltujenLkm ei toimi oikein.");
    }

    @Test
    void testGetPalveltujenMaara() {
        l.laske();
        int plkm = 0;
        for (int i : palveltujenLkm) plkm+=i;
        assertEquals(plkm, l.getPalveltujenMaara(), "Palveltujen kokonaismäärä ei toimi oikein.");
    }

    @Test
    void testGetSaapuneidenLkm() {
        l.laske();
        assertEquals(saapuneidenLkm, l.getSaapuneidenLkm(), DELTA,"SaapuneidenLkm ei toimi oikein.");
    }

    @Test
    void testGetSuoritusteho() {
        l.laske();
        int plkm = 0;
        for (int i : palveltujenLkm) plkm+=i;
        Kello.getInstance().setAika(kokonaisaika);
        double suoritusteho = plkm/kokonaisaika;
        assertEquals(suoritusteho, l.getSuoritusteho(), DELTA, "getJatteenKokonaismaara ei toimi oikein.");
    }   
}
