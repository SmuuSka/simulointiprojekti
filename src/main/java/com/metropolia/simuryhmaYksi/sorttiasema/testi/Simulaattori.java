package com.metropolia.simuryhmaYksi.sorttiasema.testi;
import java.sql.SQLException;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.controller.IKontrolleriMtoV;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.framework.*;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.OmaMoottori;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {		
        Trace.setTraceLevel(Trace.Level.INFO);
    	Moottori m = new OmaMoottori(new IKontrolleriMtoV()  {
           @Override
           public void naytaLoppuaika(double aika) {
            }
        @Override
        public void tallennaTulokset(Laskenta suureet) throws SQLException {
            
        }
        @Override
        public IVisualisointi getVisualisointi() {
            return null;
        }
        @Override
        public void setEJononPituus(int pituus) {
            
        }
        @Override
        public void setPTJononPituus(int size) {
            
        }
        @Override
        public void setPJononPituus(int size) {
            
        }
        @Override
        public void setSAAPUMISJononPituus(int size) {
            
        }
                @Override
                public double getAktiivisuus() {
                        return 0;
                }
        });
		m.setSimulointiaika(50);
		m.start();
	}
}
