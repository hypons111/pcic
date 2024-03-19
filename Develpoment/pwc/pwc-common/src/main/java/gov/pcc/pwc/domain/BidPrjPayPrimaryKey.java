package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BidPrjPayPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String monNo;

    public BidPrjPayPrimaryKey() {
    }

    public BidPrjPayPrimaryKey(String wkut, String prjno, String monNo) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.monNo = monNo;
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

    public String getMonNo() {
        return monNo;
    }

    public void setMonNo(String monNo) {
        this.monNo = monNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjPayPrimaryKey)) return false;
        BidPrjPayPrimaryKey that = (BidPrjPayPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && monNo.equals(that.monNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, monNo);
    }

    @Override
    public String toString() {
        return "BidPrjPayPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", mon_no='" + monNo + '\'' +
                '}';
    }
}
