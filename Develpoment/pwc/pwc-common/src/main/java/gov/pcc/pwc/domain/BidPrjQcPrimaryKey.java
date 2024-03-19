package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjQcPrimaryKey implements Serializable {
    private String wkut;

    private String prjno;

    private String id;

    private LocalDate startDate;

    public BidPrjQcPrimaryKey() {
    }

    public BidPrjQcPrimaryKey(String wkut, String prjno, String id, LocalDate startDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof BidPrjQcPrimaryKey)) return false;
        BidPrjQcPrimaryKey that = (BidPrjQcPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(id, that.id)&& Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno,id,startDate);
    }

    @Override
    public String toString() {
        return "BidPrjSafePrimaryKey{" +
                "wkut ='" + wkut + '\'' +
                ", prjno ='" + prjno + '\'' +
                ", id ='" + id + '\'' +
                ", startDate ='" + startDate + '\'' +
                '}';
    }
}
