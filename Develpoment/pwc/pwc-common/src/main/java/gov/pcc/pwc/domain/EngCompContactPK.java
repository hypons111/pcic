package gov.pcc.pwc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class EngCompContactPK implements Serializable {

    private String engCompId;

    private int itemNo;

    public EngCompContactPK() {}

    public EngCompContactPK(String engCompId, int itemNo) {
        this.engCompId = engCompId;
        this.itemNo = itemNo;
    }

    public String getEngCompId() {
        return engCompId;
    }

    public void setEngCompId(String engCompId) {
        this.engCompId = engCompId;
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
        EngCompContactPK that = (EngCompContactPK) o;
        return itemNo == that.itemNo && Objects.equals(engCompId, that.engCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engCompId, itemNo);
    }
}
