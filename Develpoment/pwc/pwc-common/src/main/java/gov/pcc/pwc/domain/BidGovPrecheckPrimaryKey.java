package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidGovPrecheckPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate checkDate;

    public BidGovPrecheckPrimaryKey() {
    }

    public BidGovPrecheckPrimaryKey(String wkut, String prjno, LocalDate checkDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.checkDate = checkDate;
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidGovPrecheckPrimaryKey)) return false;
        BidGovPrecheckPrimaryKey that = (BidGovPrecheckPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate);
    }

    @Override
    public String toString() {
        return "BidGovCheckPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", check_date='" + checkDate + '\'' +
                '}';
    }
}
