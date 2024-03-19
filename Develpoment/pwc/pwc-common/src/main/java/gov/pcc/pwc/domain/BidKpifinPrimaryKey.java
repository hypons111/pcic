package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidKpifinPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate idate;

    private Integer seq;

    public BidKpifinPrimaryKey() {
    }

    public BidKpifinPrimaryKey(String wkut, String prjno, LocalDate idate, Integer seq) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.idate = idate;
        this.seq = seq;
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

    public LocalDate getIdate() {
        return idate;
    }

    public void setIdate(LocalDate idate) {
        this.idate = idate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidKpifinPrimaryKey)) return false;
        BidKpifinPrimaryKey that = (BidKpifinPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && idate.equals(that.idate) && seq.equals(that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate, seq);
    }

    @Override
    public String toString() {
        return "BidKpifinPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", idate=" + idate +
                ", seq=" + seq +
                '}';
    }
}
