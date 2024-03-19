package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjSafePrimaryKey implements Serializable {
    private String wkut;

    private String prjno;

    private LocalDate sdate;



    public BidPrjSafePrimaryKey(){

    }

    public BidPrjSafePrimaryKey(String wkut, String prjno, LocalDate sdate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.sdate = sdate;

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

    public LocalDate getsdate() {
        return sdate;
    }

    public void setsdate(LocalDate sdate) {
        this.sdate = sdate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjSafePrimaryKey)) return false;
        BidPrjSafePrimaryKey that = (BidPrjSafePrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(sdate, that.sdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno,sdate);
    }

    @Override
    public String toString() {
        return "BidPrjSafePrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", sDate='" + sdate + '\'' +
                '}';
    }
}
