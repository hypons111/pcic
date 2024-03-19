package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjMonthPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String syr;

    private String month;

    public BidPrjMonthPrimaryKey() {
    }

    public BidPrjMonthPrimaryKey(String wkut, String prjno, String syr, String month) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.syr = syr;
        this.month = month;
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

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjMonthPrimaryKey)) return false;
        BidPrjMonthPrimaryKey that = (BidPrjMonthPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(syr, that.syr) && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, syr, month);
    }

    @Override
    public String toString() {
        return "BidPrjMonthPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", syr='" + syr + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
