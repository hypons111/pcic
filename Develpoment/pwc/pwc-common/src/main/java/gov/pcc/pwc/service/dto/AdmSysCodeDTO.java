package gov.pcc.pwc.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class AdmSysCodeDTO {

    @Size(max = 3)
    private String moduleType;

    @Size(max = 20)
    private String dataType;


    @DecimalMin(value = "0")
    @DecimalMax(value = "255")
    private BigDecimal sortNo;

    @Size(max = 20)
    private String dataKey;

    @Size(max = 100)
    private String value;

    @Size(max = 500)
    private String codeDescript;

    @Size(max = 1000)
    private String enCodeDescript;

    @Size(max = 100)
    private String remark;

    private Boolean isValid;

    private Instant validFromDate;

    private Boolean isModify;

    @Size(max = 50)
    private String attr1;

    @Size(max = 50)
    private String attr2;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;


    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getIsModify() {
        return isModify;
    }

    public void setIsModify(Boolean isModify) {
        this.isModify = isModify;
    }

    public Instant getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmSysCodeDTO that = (AdmSysCodeDTO) o;
        return Objects.equals(moduleType, that.moduleType) && Objects.equals(dataType, that.dataType) && Objects.equals(sortNo, that.sortNo) && Objects.equals(dataKey, that.dataKey) && Objects.equals(codeDescript, that.codeDescript) && Objects.equals(enCodeDescript, that.enCodeDescript) && Objects.equals(remark, that.remark) && Objects.equals(isValid, that.isValid) && Objects.equals(validFromDate, that.validFromDate) && Objects.equals(isModify, that.isModify) && Objects.equals(attr1, that.attr1) && Objects.equals(attr2, that.attr2) && Objects.equals(createUser, that.createUser) && Objects.equals(createTime, that.createTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleType, dataType, sortNo, dataKey, codeDescript, enCodeDescript, remark, isValid, validFromDate, isModify, attr1, attr2, createUser, createTime, updateUser, updateTime);
    }

    @Override
    public String toString() {
        return "AdmSysCodeDTO{" +
                "moduleType='" + moduleType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", sortNo=" + sortNo +
                ", dataKey='" + dataKey + '\'' +
                ", codeDescript='" + codeDescript + '\'' +
                ", enCodeDescript='" + enCodeDescript + '\'' +
                ", remark='" + remark + '\'' +
                ", isValid=" + isValid +
                ", validFromDate=" + validFromDate +
                ", isModify=" + isModify +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
