package com.metropolia.simuryhmaYksi.sorttiasema.simu.model;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

/*
 * Simulaattorissa käytettävät tapahtumatyypit
 */

public enum TapahtumanTyyppi{
	/**
	 * Asiakas saapuu palvelutiskin jonoon
	 */
	PALVELUTISKI_SAAPUMINEN,
	/**
	 * Asiakas saapuu elektroniikkalavan jonoon
	 */
	ELEKTRONIIKKA_SAAPUMINEN,
	/**
	 * Asiakas saapuu palamattoman jätteen jonoon
	 */
	PALAMATONJATE_SAAPUMINEN,
	/**
	 * Asiakas saapuu palavan jätteen jonoon
	 */
	PALAVAJATE_SAAPUMINEN,
	/**
	 * Asiakas poistuu joltakin jätelavalta 
	 * */ 
	POISTUMINEN
}