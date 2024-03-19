package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BidPrjPricePrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String year;

    private String pUnit;

    private String grade;

    public BidPrjPricePrimaryKey() {
    }

    public BidPrjPricePrimaryKey(String wkut, String prjno, String year, String pUnit, String grade) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.year = year;
        this.pUnit = pUnit;
        this.grade = grade;
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

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjPricePrimaryKey)) return false;
        BidPrjPricePrimaryKey that = (BidPrjPricePrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(year, that.year) && Objects.equals(pUnit, that.pUnit) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, pUnit, grade);
    }

    @Override
    public String toString() {
        return "BidPrjPricePrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", year='" + year + '\'' +
                ", pUnit='" + pUnit + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
