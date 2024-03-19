package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjStopwkPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate startDate;

    public BidPrjStopwkPrimaryKey() {
    }

    public BidPrjStopwkPrimaryKey(String wkut, String prjno, LocalDate startDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.startDate = startDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjStopwkPrimaryKey)) return false;
        BidPrjStopwkPrimaryKey that = (BidPrjStopwkPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, startDate);
    }

    @Override
    public String toString() {
        return "BidPrjStopwkPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
