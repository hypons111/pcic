package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjSegmsetPrimaryKey implements Serializable {

  private String wkut;

  private String prjno;

  private LocalDate agreeDate;


  public BidPrjSegmsetPrimaryKey() {}

  public BidPrjSegmsetPrimaryKey(String wkut, String prjno, LocalDate agreeDate) {
    this.wkut = wkut;
    this.prjno = prjno;
    this.agreeDate = agreeDate;
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

  public LocalDate getAgreeDate() {return agreeDate;}

  public void setAgreeDate(LocalDate agreeDate) {this.agreeDate = agreeDate;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjSegmsetPrimaryKey)) return false;
    BidPrjSegmsetPrimaryKey that = (BidPrjSegmsetPrimaryKey) o;
    return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(agreeDate, that.agreeDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, agreeDate);
  }

  @Override
  public String toString() {
    return "BidPrjEnviPrimaryKey{" +
            "wkut='" + wkut + '\'' +
            ", prjno='" + prjno + '\'' +
            ", sdate=" + agreeDate +
            '}';
  }
}
