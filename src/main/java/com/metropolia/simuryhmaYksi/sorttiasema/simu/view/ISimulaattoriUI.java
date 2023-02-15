package com.metropolia.simuryhmaYksi.sorttiasema.simu.view;

public interface ISimulaattoriUI {
    public int[] getVaihteluvali();
    public double getAika();
    public long getViive();

    public void setLoppuaika();
    public IVisualisointi getVisualisointi();
}
