package com.metropolia.simuryhmaYksi.sorttiasema.simu.controller;


import com.metropolia.simuryhmaYksi.sorttiasema.simu.view.IVisualisointi;

import java.sql.SQLException;

/**
 * @author Samu Aikio, Kaspar Tullus, Joel Tikkanen
 */

/**
 * Rajapinta näkymästä malliin
 */

public interface IKontrolleriVtoM {

  /**
   * Käynnistää simuloinnin
   * @throws SQLException
   */
  void kaynnistaSimulointi() throws SQLException;
  /**
   * Nopeuttaa simulointi, eli vähentää viivettä
   */
  void nopeuta();
  /**
   * Hidastaa simulointi, eli lisää viivettä
   */
  void hidasta();
  /**
   * Asetetaan visualisointi
   * @param visualisointi Näkymän rajapinta
   */
  void setVisualisointi(IVisualisointi visualisointi);
  /**
   * Kutsuu moottorin lopetasimulaatio metodia
   * @throws SQLException
   */
  void lopetaSimulointi() throws SQLException;
  /**
   * Poistaa yksittäisen tuloksen
   * @param ID Ui:sta tuleva column id
   * @throws SQLException
   */
  void poistaTulos(int ID) throws SQLException;
  /**
   * Näyttää tietokantaikkunan
   * @throws SQLException
   */
  void showTuloksetAction() throws SQLException;
  /**
   * Poistaa kaikki taulut
   * @throws SQLException
   */
  void poistaKaikkiDATA() throws SQLException;
  /**
  * Tietokanta yhteyden avaaminen
  */
  void avaaDATAYHTEYS();
}
