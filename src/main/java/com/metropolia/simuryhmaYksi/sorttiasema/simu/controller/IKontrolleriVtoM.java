package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;

import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

public interface IKontrolleriVtoM {
  public void kaynnistaSimulointi();
    public void nopeuta();
    public void hidasta();
    public IVisualisointi setVisualisointi(IVisualisointi visualisointi);
    public void lopetaSimulointi();
}
