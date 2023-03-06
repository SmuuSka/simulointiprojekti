package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;


import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

public interface IKontrolleriVtoM {
  void kaynnistaSimulointi() throws SQLException;
    void nopeuta();
   void hidasta();
   IVisualisointi setVisualisointi(IVisualisointi visualisointi);
    void lopetaSimulointi() throws SQLException;
   void poistaTulos(int ID) throws SQLException;
    boolean onkoSimulointiPaalla();
  void showTuloksetAction() throws SQLException;
}
