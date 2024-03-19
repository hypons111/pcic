package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjPay2PrimaryKey implements Serializable {
        private String wkut;
        private String prjno;
        private String year;
        private String month;

    public BidPrjPay2PrimaryKey() {
    }

    public BidPrjPay2PrimaryKey(String wkut, String prjno, String year, String month) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.year = year;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
        if (!(o instanceof BidPrjPay2PrimaryKey)) return false;
        BidPrjPay2PrimaryKey that = (BidPrjPay2PrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year)&& month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year,month);
    }

    @Override
    public String toString() {
        return "BidPrjPayPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
