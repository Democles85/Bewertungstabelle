package com.hcm.TabelleBewertungsvergleich;

public class Warengruppen {
  public Warengruppen(String WGID, String warenGruppenTitel) {
    WarenGruppenTitel = warenGruppenTitel;
    this.WGID = WGID;
  }

  private String WarenGruppenTitel;
  private String WGID;

  public String getWarenGruppenTitel() {
    return WarenGruppenTitel;
  }

  public void setWarenGruppenTitel(String warenGruppenTitel) {
    WarenGruppenTitel = warenGruppenTitel;
  }

  public String getWGID() {
    return WGID;
  }

  public void setWGID(String WGID) {
    this.WGID = WGID;
  }
}
