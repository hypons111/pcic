package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidProjectPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    public BidProjectPrimaryKey() {
    }

    public BidProjectPrimaryKey(String wkut, String prjno) {
        this.wkut = wkut;
        this.prjno = prjno;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectPrimaryKey)) return false;
        BidProjectPrimaryKey that = (BidProjectPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }
}
