package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjSubcoPrimaryKey implements Serializable {

  private String wkut;

  private String prjno;

  private String subcoNo;

  public BidPrjSubcoPrimaryKey() {}

  public BidPrjSubcoPrimaryKey(String wkut, String prjno, String subcoNo) {
    this.wkut = wkut;
    this.prjno = prjno;
    this.subcoNo = subcoNo;
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

  public String getSubcoNo() {
    return subcoNo;
  }

  public void setSubcoNo(String subcoNo) {
    this.subcoNo = subcoNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjSubcoPrimaryKey)) return false;
    BidPrjSubcoPrimaryKey that = (BidPrjSubcoPrimaryKey) o;
    return (
      Objects.equals(wkut, that.wkut) &&
      Objects.equals(prjno, that.prjno) &&
      Objects.equals(subcoNo, that.subcoNo)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, subcoNo);
  }

  @Override
  public String toString() {
    return (
      "BidPrjMonthPrimaryKey{" +
      "wkut='" +
      wkut +
      '\'' +
      ", prjno='" +
      prjno +
      '\'' +
      ", subcoNo='" +
      subcoNo +
      '\'' +
      '}'
    );
  }
}
