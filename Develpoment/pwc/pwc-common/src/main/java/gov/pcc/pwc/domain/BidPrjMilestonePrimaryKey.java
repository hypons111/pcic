package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.util.Objects;

public class BidPrjMilestonePrimaryKey implements Serializable {
    private String wkut;

    private String prjno;

    private String milestoneNo;

    public BidPrjMilestonePrimaryKey() {
    }

    public BidPrjMilestonePrimaryKey(String wkut, String prjno, String milestoneNo) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.milestoneNo = milestoneNo;
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

    public String getMilestoneNo() {
        return milestoneNo;
    }

    public void setMilestoneNo(String milestoneNo) {
        this.milestoneNo = milestoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjMilestonePrimaryKey)) return false;
        BidPrjMilestonePrimaryKey that = (BidPrjMilestonePrimaryKey) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && milestoneNo.equals(that.milestoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, milestoneNo);
    }

    @Override
    public String toString() {
        return "BidPrjMilestonePrimaryKey{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", milestoneNo='" + milestoneNo + '\'' +
                '}';
    }
}
