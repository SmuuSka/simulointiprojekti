package com.metropolia.simuryhmaYksi.sorttiasema.simu.framework;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori {
	
	private double simulointiaika = 0;
	protected IKontrolleriMtoV kontrolleri;
	
	protected Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	protected boolean ajetaanTyhjaksi = true;

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
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		//Kunnes viimeinen asiakas on poistunut
		while (simuloidaan()){
			viive();
			
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());
			
			Trace.out(Trace.Level.INFO, "\nB-vaihe:" );
			suoritaBTapahtumat();

			setTekstit();
			
			Trace.out(Trace.Level.INFO, "\nC-vaihe:" );
			yritaCTapahtumat();

			setVarattu();

		}
		tulokset();
		
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
		if (ajetaanTyhjaksi){
			return !tapahtumalista.getLista().isEmpty();
		}
		return simulointiaika > Kello.getInstance().getAika();
	}
	

	public void viive() {
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setViive(long viive) {
		this.viive = viive;
	}

	@Override
	public long getViive() {
		return viive;
	}
			

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	protected abstract void setTekstit();

	protected abstract void setVarattu();
	
}