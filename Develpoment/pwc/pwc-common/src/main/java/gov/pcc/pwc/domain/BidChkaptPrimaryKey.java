package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidChkaptPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate idate;

    public BidChkaptPrimaryKey() {
    }

    public BidChkaptPrimaryKey(String wkut, String prjno, LocalDate idate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.idate = idate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidChkaptPrimaryKey)) return false;
        BidChkaptPrimaryKey that = (BidChkaptPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(idate, that.idate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate);
    }

    @Override
    public String toString() {
        return "BidChkaptPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", idate=" + idate +
                '}';
    }
}
