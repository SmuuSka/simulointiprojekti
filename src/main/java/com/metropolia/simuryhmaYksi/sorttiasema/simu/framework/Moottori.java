package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Palvelupiste;

import java.sql.SQLException;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	protected IKontrolleriMtoV kontrolleri;
	
	protected Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	protected boolean ajetaanTyhjaksi = true;
	protected boolean lopetaSimu = false;
	private long viive;

	public Moottori(IKontrolleriMtoV kontrolleri){
		this.kontrolleri = kontrolleri;
		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	public double getSimulointiaika() {
		return simulointiaika;
	}

	public void run(){
		kello.setAika(0);
		alustukset();
		while (simuloidaan()) {
			viive();
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());

			Trace.out(Trace.Level.INFO, "\nB-vaihe:");
			suoritaBTapahtumat();
			setTekstit();

			Trace.out(Trace.Level.INFO, "\nC-vaihe:");
			yritaCTapahtumat();

			setVarattu();
		}
		try {
			tulokset();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
			}

	}

	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		if (lopetaSimu){
			return lopetaSimu;
		}
		if (ajetaanTyhjaksi){
			return !tapahtumalista.getLista().isEmpty();
		}
		return simulointiaika > Kello.getInstance().getAika();
	}
	

	public synchronized void viive() {
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			System.out.println("viive keskeytetty");
		}
	}

	@Override
	public void setViive(long viive) {
		interrupt();
		this.viive = viive;
	}

	@Override
	public long getViive() {
		return viive;
	}

	@Override
	public void setAjetaanTyhjaksi(boolean ajetaanTyhjaksi){
		this.ajetaanTyhjaksi = ajetaanTyhjaksi;
	}
	
	@Override
	public boolean getAjetaanTyhjaksi(){
		return ajetaanTyhjaksi;
	}
	public void lopetaSimuMootori(){
		interrupt();
		setSimulointiaika(0);
	}
	protected abstract void alustukset(); // M????ritell????n simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // M????ritell????n simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset() throws SQLException; // M????ritell????n simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void setTekstit();

	protected abstract void setVarattu();
	
}