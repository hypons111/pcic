package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.MrpSysCode} entity.
 */
public class MrpSysCodeDTO implements Serializable {

    @NotNull
    private Long sortNo;

    @NotNull
    @Size(max = 50)
    private String dataType;

    @NotNull
    @Size(max = 50)
    private String dataKey;

    @NotNull
    @Size(max = 20)
    private String dataKeyType;

    @NotNull
    @Size(max = 100)
    private String dataValue;

    @Size(max = 500)
    private String codeDescript;

    @Size(max = 1000)
    private String enCodeDescript;

    @Size(max = 100)
    private String remark;

    @NotNull
    private Integer isValid;

    private Instant validFromDate;

    private Integer isModify;

    @Size(max = 100)
    private String attr1;

    @Size(max = 100)
    private String attr2;

    @Size(max = 100)
    private String attr3;

    @Size(max = 100)
    private String attr4;

    @Size(max = 100)
    private String attr5;

    @Size(max = 20)
    private String updateId;

    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    private String createId;

    private Instant createDate;

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataKeyType() {
        return dataKeyType;
    }

    public void setDataKeyType(String dataKeyType) {
        this.dataKeyType = dataKeyType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getCodeDescript() {
        return codeDescript;
    }

    public void setCodeDescript(String codeDescript) {
        this.codeDescript = codeDescript;
    }

    public String getEnCodeDescript() {
        return enCodeDescript;
    }

    public void setEnCodeDescript(String enCodeDescript) {
        this.enCodeDescript = enCodeDescript;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Instant getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Integer getIsModify() {
        return isModify;
    }

    public void setIsModify(Integer isModify) {
        this.isModify = isModify;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MrpSysCodeDTO)) {
            return false;
        }

        MrpSysCodeDTO mrpSysCodeDTO = (MrpSysCodeDTO) o;
        if (this.sortNo == null) {
            return false;
        }
        return Objects.equals(this.sortNo, mrpSysCodeDTO.sortNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sortNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MrpSysCodeDTO{" +
            "sortNo=" + getSortNo() +
            ", dataType='" + getDataType() + "'" +
            ", dataKey='" + getDataKey() + "'" +
            ", dataKeyType='" + getDataKeyType() + "'" +
            ", dataValue='" + getDataValue() + "'" +
            ", codeDescript='" + getCodeDescript() + "'" +
            ", enCodeDescript='" + getEnCodeDescript() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isValid=" + getIsValid() +
            ", validFromDate='" + getValidFromDate() + "'" +
            ", isModify=" + getIsModify() +
            ", attr1='" + getAttr1() + "'" +
            ", attr2='" + getAttr2() + "'" +
            ", attr3='" + getAttr3() + "'" +
            ", attr4='" + getAttr4() + "'" +
            ", attr5='" + getAttr5() + "'" +
            ", updateId='" + getUpdateId() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createId='" + getCreateId() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
