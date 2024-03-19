package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjGdfkPrimaryKey implements Serializable {

  private String wkut;

  private String prjno;

  private String useType;

  private LocalDate startDate;


  public BidPrjGdfkPrimaryKey() {}

  public BidPrjGdfkPrimaryKey(String wkut, String prjno, String useType, LocalDate startDate) {
    this.wkut = wkut;
    this.prjno = prjno;
    this.useType = useType;
    this.startDate = startDate;
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

  public String getUseType() {return useType;}

  public void setUseType(String useType) {this.useType = useType;}

  public LocalDate getStartDate() {return startDate;}

  public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjGdfkPrimaryKey)) return false;
    BidPrjGdfkPrimaryKey that = (BidPrjGdfkPrimaryKey) o;
    return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(useType, that.useType) && Objects.equals(startDate, that.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, useType, startDate);
  }

  @Override
  public String toString() {
    return "BidPrjWksorcPrimaryKey{" +
            "wkut='" + wkut + '\'' +
            ", prjno='" + prjno + '\'' +
            ", syr='" + useType + '\'' +
            ", month='" + startDate + '\'' +
            '}';
  }
}
