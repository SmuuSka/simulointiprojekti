package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.model.Laskenta;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

public interface IKontrolleriVtoM {
  void kaynnistaSimulointi() throws SQLException;
    void nopeuta();
   void hidasta();
   IVisualisointi setVisualisointi(IVisualisointi visualisointi);
    void lopetaSimulointi() throws SQLException;
   void poistaTulos(int ID) throws SQLException;

  void showTuloksetAction() throws SQLException;
}
