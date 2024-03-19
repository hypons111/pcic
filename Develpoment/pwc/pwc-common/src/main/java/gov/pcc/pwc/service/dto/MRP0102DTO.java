package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

public class MRP0102DTO implements Serializable {

    @NotNull
    @Size(max = 3)
    private String moduleType;

    @NotNull
    @Size(max = 20)
    private String dataType;

    @NotNull
    private String sortNo;

    @NotNull
    @Size(max = 20)
    private String dataKey;

    @NotNull
    @Size(max = 100)
    private String value;

    @NotNull
    @Size(max = 500)
    private String codeDescript;

    @NotNull
    @Size(max = 1000)
    private String enCodeDescript;

    @NotNull
    @Size(max = 100)
    private String remark;

    @NotNull
    private Boolean isValid;

    @NotNull
    private Instant validFromDate;

    @NotNull
    private Boolean isModify;
    
    @NotNull
    @Size(max = 50)
    private String attr1;

    @NotNull
    @Size(max = 50)
    private String attr2;

    @NotNull
    private String createUser;

    @NotNull
    private Instant createTime;

    @NotNull
    private String updateUser;

    @NotNull
    private Instant updateTime;

    public String getModuleType() {
        return moduleType;
    }

    public String getDataType() {
        return dataType;
    }

    public String getSortNo() {
        return sortNo;
    }

    public String getDataKey() {
        return dataKey;
    }

    public String getValue() {
        return value;
    }

    public String getCodeDescript() {
        return codeDescript;
    }

    public String getEnCodeDescript() {
        return enCodeDescript;
    }

    public String getRemark() {
        return remark;
    }

    public Boolean getValid() {
        return isValid;
    }

    public Instant getValidFromDate() {
        return validFromDate;
    }

    public Boolean getModify() {
        return isModify;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCodeDescript(String codeDescript) {
        this.codeDescript = codeDescript;
    }

    public void setEnCodeDescript(String enCodeDescript) {
        this.enCodeDescript = enCodeDescript;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public void setValidFromDate(Instant validFromDate) {
        this.validFromDate = validFromDate;
    }

    public void setModify(Boolean modify) {
        isModify = modify;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
}
