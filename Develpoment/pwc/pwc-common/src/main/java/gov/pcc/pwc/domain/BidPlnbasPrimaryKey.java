package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPlnbasPrimaryKey implements Serializable {

    private String plncode;

    private String plnno;

    public BidPlnbasPrimaryKey() {
    }

    public BidPlnbasPrimaryKey(String plncode, String plnno) {
        this.plncode = plncode;
        this.plnno = plnno;
    }

    public String getPlncode() {
        return plncode;
    }

    public void setPlncode(String plncode) {
        this.plncode = plncode;
    }

    public String getPlnno() {
        return plnno;
    }

    public void setPlnno(String plnno) {
        this.plnno = plnno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPlnbasPrimaryKey)) return false;
        BidPlnbasPrimaryKey that = (BidPlnbasPrimaryKey) o;
        return Objects.equals(plncode, that.plncode) && Objects.equals(plnno, that.plnno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plncode, plnno);
    }
}
