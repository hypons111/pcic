package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class EngCaseBidCompPK implements Serializable {

    private String engCaseId;

    private int itemNo;

    public EngCaseBidCompPK() {}

    public EngCaseBidCompPK(String engCaseId, int itemNo) {
        this.engCaseId = engCaseId;
        this.itemNo = itemNo;
    }

    public String getEngCaseId() {
        return engCaseId;
    }

    public void setEngCaseId(String engCaseId) {
        this.engCaseId = engCaseId;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngCaseBidCompPK that = (EngCaseBidCompPK) o;
        return itemNo == that.itemNo && Objects.equals(engCaseId, that.engCaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, itemNo);
    }
}
