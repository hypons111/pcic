package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjFrwkrPrimaryKey implements Serializable {

  private String wkut;

  private String prjno;

  private LocalDate sdate;


  public BidPrjFrwkrPrimaryKey() {}

  public BidPrjFrwkrPrimaryKey(String wkut, String prjno, LocalDate sdate) {
    this.wkut = wkut;
    this.prjno = prjno;
    this.sdate = sdate;
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

  public LocalDate getSdate() {
    return sdate;
  }

  public void setSdate(LocalDate sdate) {
    this.sdate = sdate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjFrwkrPrimaryKey)) return false;
    BidPrjFrwkrPrimaryKey that = (BidPrjFrwkrPrimaryKey) o;
    return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(sdate, that.sdate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, sdate);
  }

  @Override
  public String toString() {
    return "BidPrjEnviPrimaryKey{" +
            "wkut='" + wkut + '\'' +
            ", prjno='" + prjno + '\'' +
            ", sdate=" + sdate +
            '}';
  }
}
