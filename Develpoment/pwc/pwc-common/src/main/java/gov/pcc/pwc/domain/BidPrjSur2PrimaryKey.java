package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjSur2PrimaryKey implements Serializable {
    private String wkut;
    private String prjno;
    private String disNo;

    public BidPrjSur2PrimaryKey() {
    }

    public BidPrjSur2PrimaryKey(String wkut, String prjno, String disNo) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.disNo = disNo;
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

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjSur2PrimaryKey)) return false;
        BidPrjSur2PrimaryKey that = (BidPrjSur2PrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(disNo, that.disNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno,disNo);
    }

    @Override
    public String toString() {
        return "BidPrjPayPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", disNo='" + disNo + '\'' +
                '}';
    }
}
