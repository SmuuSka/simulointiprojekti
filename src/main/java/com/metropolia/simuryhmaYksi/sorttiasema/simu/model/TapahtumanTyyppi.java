package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

public enum TapahtumanTyyppi{
	PALVELUTISKI_SAAPUMINEN, // Asiakas saapuu palvelutiskin jonoon
	ELEKTRONIIKKA_SAAPUMINEN, // Asiakas saapuu elektroniikkalavan jonoon
	PALAMATONJATE_SAAPUMINEN, // Asiakas saapuu palamattoman jätteen jonoon
	PALAVAJATE_SAAPUMINEN, // Asiakas saapuu palavan jätteen jonoon
	POISTUMINEN // Asiakas poistuu joltakin jätelavalta (demoversiossa tämä on palavan jätteen lava)
}