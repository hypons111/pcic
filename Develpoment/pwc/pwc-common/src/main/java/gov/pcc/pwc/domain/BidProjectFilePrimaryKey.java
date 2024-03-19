package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidProjectFilePrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String type;

    private String seq;

    public BidProjectFilePrimaryKey() {
    }

    public BidProjectFilePrimaryKey(String wkut, String prjno, String type, String seq) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectFilePrimaryKey)) return false;
        BidProjectFilePrimaryKey that = (BidProjectFilePrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(type, that.type) && Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, type, seq);
    }
}
