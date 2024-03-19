package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjFalPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String year;

    private String month;

    private String mft;

    public BidPrjFalPrimaryKey() {
    }

    public BidPrjFalPrimaryKey(String wkut, String prjno, String year, String month,String mft) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.year = year;
        this.month = month;
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

    public String getMft() {
        return mft;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjFalPrimaryKey)) return false;
        BidPrjFalPrimaryKey that = (BidPrjFalPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month) && mft.equals(that.mft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month, mft);
    }

    @Override
    public String toString() {
        return "BidPrjMonthPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", mft='" + mft +
                '}';
    }
}
