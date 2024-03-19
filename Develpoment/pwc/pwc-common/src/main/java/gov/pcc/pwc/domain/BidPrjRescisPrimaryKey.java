package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjRescisPrimaryKey implements Serializable {
    private String wkut;

    private String prjno;

    private LocalDate stopDate;

    public BidPrjRescisPrimaryKey(){

    }

    public BidPrjRescisPrimaryKey(String wkut, String prjno, LocalDate stopDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.stopDate = stopDate;
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

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjRescisPrimaryKey)) return false;
        BidPrjRescisPrimaryKey that = (BidPrjRescisPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(stopDate, that.stopDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, stopDate);
    }

    @Override
    public String toString() {
        return "BidPrjRescisPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", stopDate=" + stopDate +
                '}';
    }
}
