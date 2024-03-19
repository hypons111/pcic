package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BidPrjModifyPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private LocalDate chgDate;

    public BidPrjModifyPrimaryKey() {
    }

    public BidPrjModifyPrimaryKey(String wkut, String prjno, LocalDate chgDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.chgDate = chgDate;
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

    public LocalDate getChgDate() {
        return chgDate;
    }

    public void setChgDate(LocalDate chgDate) {
        this.chgDate = chgDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjModifyPrimaryKey)) return false;
        BidPrjModifyPrimaryKey that = (BidPrjModifyPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && chgDate.equals(that.chgDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, chgDate);
    }

    @Override
    public String toString() {
        return "BidPrjModifyPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", chgDate=" + chgDate +
                '}';
    }
}
