package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.ISimulaattoriUI;
import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

public interface IKontrolleriVtoM {
  public void kaynnistaSimulointi() throws SQLException;
    public void nopeuta();
    public void hidasta();
    public IVisualisointi setVisualisointi(IVisualisointi visualisointi);
    public void lopetaSimulointi();
}
