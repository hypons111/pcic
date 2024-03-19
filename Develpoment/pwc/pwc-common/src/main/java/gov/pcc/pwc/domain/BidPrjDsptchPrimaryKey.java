package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjDsptchPrimaryKey implements Serializable {
    private String wkut;
    private String prjno;
    private String dspNo;

    public BidPrjDsptchPrimaryKey() {
    }

    public BidPrjDsptchPrimaryKey(String wkut, String prjno, String dspNo) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.dspNo = dspNo;
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

    public String getDspNo() {
        return dspNo;
    }

    public void setDspNo(String dspNo) {
        this.dspNo = dspNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjDsptchPrimaryKey)) return false;
        BidPrjDsptchPrimaryKey that = (BidPrjDsptchPrimaryKey) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno)&& Objects.equals(dspNo, that.dspNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,dspNo);
    }

    @Override
    public String toString() {
        return "BidPrjSafePrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", dspNo='" + dspNo + '\'' +
                '}';
    }
}
