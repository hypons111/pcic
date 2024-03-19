package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidMdlbasPrimaryKey implements Serializable {
    private String wkut;

    private String disNo;

    public BidMdlbasPrimaryKey() {
    }

    public BidMdlbasPrimaryKey(String wkut, String disNo) {
        this.wkut = wkut;
        this.disNo = disNo;
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidMdlbasPrimaryKey)) return false;
        BidMdlbasPrimaryKey that = (BidMdlbasPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(disNo, that.disNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,disNo);
    }

    @Override
    public String toString() {
        return "BidPrjSafePrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", disNo='" + disNo + '\'' +
                '}';
    }
}
