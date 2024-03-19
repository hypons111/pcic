package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjScequipPrimaryKey implements Serializable {

    private String wkut;

    private String prjno;

    private String subCcut;

    private String eCode;

    public BidPrjScequipPrimaryKey() {}
    public BidPrjScequipPrimaryKey(String wkut, String prjno, String subCcut,String eCode) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.subCcut = subCcut;
        this.eCode = eCode;
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

    public String getSubCcut() {
        return subCcut;
    }

    public void setSubCcut(String subCcut) {
        this.subCcut = subCcut;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjScequipPrimaryKey)) return false;
        BidPrjScequipPrimaryKey that = (BidPrjScequipPrimaryKey) o;
        return (
                Objects.equals(wkut, that.wkut) &&
                        Objects.equals(prjno, that.prjno) &&
                        Objects.equals(subCcut, that.subCcut) &&
                        Objects.equals(eCode, that.eCode)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, subCcut, eCode);
    }

    @Override
    public String toString() {
        return "BidPrjScequipPrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", subCcut='" + subCcut + '\'' +
                ", eCode='" + eCode + '\'' +
                '}';
    }
}
