package com.hcm.TabelleBewertungsvergleich;

import java.util.Date;

public class Lieferant {
    public Lieferant(String lieferantenBezeichnung, String lfNR, String status, Integer bewKat1, Integer bewKat2, Integer bewKat3, Integer bewKat4, Integer bewKat5, String ansprechpartner, String anlagedatum, String WGID) {
        LieferantenBezeichnung = lieferantenBezeichnung;
        LfNR = lfNR;
        Status = status;
        BewKat1 = bewKat1;
        BewKat2 = bewKat2;
        BewKat3 = bewKat3;
        BewKat4 = bewKat4;
        BewKat5 = bewKat5;
        Ansprechpartner = ansprechpartner;
        Anlagedatum = anlagedatum;
        this.WGID = WGID;
    }

    private String LieferantenBezeichnung;
    private String LfNR;
    private String Status;
    private Integer BewKat1;
    private Integer  BewKat2;
    private Integer BewKat3;
    private Integer BewKat4;
    private Integer BewKat5;
    private String Ansprechpartner;
    private String Anlagedatum;
    private String WGID;

    public String getLieferantenBezeichnung() {
        return LieferantenBezeichnung;
    }

    public void setLieferantenBezeichnung(String lieferantenBezeichnung) {
        LieferantenBezeichnung = lieferantenBezeichnung;
    }

    public String getLfNR() {
        return LfNR;
    }

    public void setLfNR(String lfNR) {
        LfNR = lfNR;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getBewKat1() {
        return BewKat1;
    }

    public void setBewKat1(Integer bewKat1) {
        BewKat1 = bewKat1;
    }

    public Integer getBewKat2() {
        return BewKat2;
    }

    public void setBewKat2(Integer bewKat2) {
        BewKat2 = bewKat2;
    }

    public Integer getBewKat3() {
        return BewKat3;
    }

    public void setBewKat3(Integer bewKat3) {
        BewKat3 = bewKat3;
    }

    public Integer getBewKat4() {
        return BewKat4;
    }

    public void setBewKat4(Integer bewKat4) {
        BewKat4 = bewKat4;
    }

    public Integer getBewKat5() {
        return BewKat5;
    }

    public void setBewKat5(Integer bewKat5) {
        BewKat5 = bewKat5;
    }

    public String getAnsprechpartner() {
        return Ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        Ansprechpartner = ansprechpartner;
    }

    public String getAnlagedatum() {
        return Anlagedatum;
    }

    public void setAnlagedatum(String anlagedatum) {
        Anlagedatum = anlagedatum;
    }

    public String getWGID() {
        return WGID;
    }

    public void setWGID(String WGID) {
      this.WGID = WGID;
    }


}
