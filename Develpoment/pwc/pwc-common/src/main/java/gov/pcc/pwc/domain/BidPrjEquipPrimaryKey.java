package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjEquipPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String year;

    private String month;

    private String eCode;



    public BidPrjEquipPrimaryKey() {
    }

    public BidPrjEquipPrimaryKey(String wkut, String prjno, String year, String month,String eCode) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.year = year;
        this.month = month;
        this.eCode = eCode;
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

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjEquipPrimaryKey)) return false;
        BidPrjEquipPrimaryKey that = (BidPrjEquipPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month) && eCode.equals(that.eCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month, eCode);
    }

    @Override
    public String toString() {
        return "BidPrjEquipPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", e_code='" + eCode +
                '}';
    }
}
