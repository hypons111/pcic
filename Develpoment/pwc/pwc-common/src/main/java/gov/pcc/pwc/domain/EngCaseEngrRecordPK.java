package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class EngCaseEngrRecordPK implements Serializable {

    private String engCaseId;

    private String engrId;

    private int itemNo;

    public EngCaseEngrRecordPK() {}

    public EngCaseEngrRecordPK(String engCaseId, String engrId, int itemNo) {
        this.engCaseId = engCaseId;
        this.engrId = engrId;
        this.itemNo = itemNo;
    }

    public String getEngCaseId() {
        return engCaseId;
    }

    public void setEngCaseId(String engCaseId) {
        this.engCaseId = engCaseId;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
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
        EngCaseEngrRecordPK that = (EngCaseEngrRecordPK) o;
        return itemNo == that.itemNo && Objects.equals(engCaseId, that.engCaseId) && Objects.equals(engrId, that.engrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCaseId, engrId, itemNo);
    }
}
