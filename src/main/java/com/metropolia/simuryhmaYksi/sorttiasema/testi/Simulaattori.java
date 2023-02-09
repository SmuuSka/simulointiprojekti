package com.metropolia.simuryhmaYksi.sorttiasema.testi;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.OmaMoottori;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {
		Trace.setTraceLevel(Trace.Level.INFO);
		Moottori m = new OmaMoottori(new IKontrolleriMtoV() {
            @Override
            public void naytaLoppuaika(double aika) {

            }
            @Override
            public void visualisoiAsiakas() {

            }
        });
		m.setSimulointiaika(50);
		m.start();
	}
}
