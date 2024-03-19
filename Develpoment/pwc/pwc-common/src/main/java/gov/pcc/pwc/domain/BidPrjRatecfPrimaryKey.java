package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjRatecfPrimaryKey implements Serializable {

  private String wkut;

  private String prjno;

  private String syr;

  private String month;

  private String chkunit;

  private String mft;


  public BidPrjRatecfPrimaryKey() {}

  public BidPrjRatecfPrimaryKey(String wkut, String prjno,  String syr,  String month, String chkunit, String mft) {
    this.wkut = wkut;
    this.prjno = prjno;
    this.syr = syr;
    this.month = month;
    this.chkunit = chkunit;
    this.mft = mft;
  }

  public String getWkut() {
    return wkut;
  }

  public void setWkut(String wkut) {
    this.wkut = wkut;
  }

  public String getPrjno() {
    return prjno;
  }

  public void setPrjno(String prjno) {
    this.prjno = prjno;
  }

  public String getSyr() {return syr;}

  public void setSyr(String syr) {this.syr = syr;}

  public String getMonth() {return month;}

  public void setMonth(String month) {this.month = month;}

  public String getChkunit() {return chkunit;}

  public void setChkunit(String chkunit) {this.chkunit = chkunit;}

  public String getMft() {return mft;}

  public void setMft(String mft) {this.mft = mft;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjRatecfPrimaryKey)) return false;
    BidPrjRatecfPrimaryKey that = (BidPrjRatecfPrimaryKey) o;
    return Objects.equals(wkut, that.wkut)
            && Objects.equals(prjno, that.prjno)
            && Objects.equals(syr, that.syr)
            && Objects.equals(month, that.month)
            && Objects.equals(chkunit, that.chkunit)
            && Objects.equals(mft, that.mft);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, syr, month, chkunit, mft);
  }

  @Override
  public String toString() {
    return "BidPrjWksorcPrimaryKey{" +
            "wkut='" + wkut + '\'' +
            ", prjno='" + prjno + '\'' +
            ", syr='" + syr + '\'' +
            ", month='" + month + '\'' +
            ", chkunit='" + chkunit + '\'' +
            ", mft='" + mft + '\'' +
            '}';
  }
}
