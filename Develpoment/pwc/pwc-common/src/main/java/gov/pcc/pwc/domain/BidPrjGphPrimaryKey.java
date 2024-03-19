package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BidPrjGphPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String phrase;

    private Long seq;

    public BidPrjGphPrimaryKey() {
    }

    public BidPrjGphPrimaryKey(String wkut, String prjno, String phrase, Long seq) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.phrase = phrase;
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

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjGphPrimaryKey)) return false;
        BidPrjGphPrimaryKey that = (BidPrjGphPrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && phrase.equals(that.phrase) && seq.equals(that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, phrase, seq);
    }

    @Override
    public String toString() {
        return "BidPrjGphPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", phrase='" + phrase + '\'' +
                ", seq='" + seq + '\'' +
                '}';
    }
}
