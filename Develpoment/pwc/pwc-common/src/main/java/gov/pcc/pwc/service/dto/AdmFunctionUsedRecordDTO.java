package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.Objects;

public class AdmFunctionUsedRecordDTO {

    private Long id;

    private String functionId;

    private String functionExtendId;

    private String sysCategory;

    private String jwtToken;

    private String tableName;

    private String tableKey;

    private String updateValue;

    private String recordContent;

    private Instant createTime;

    private String createUserSys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionExtendId() {
        return functionExtendId;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public String getSysCategory() {
        return sysCategory;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getUpdateValue() {
        return updateValue;
    }

    public void setUpdateValue(String updateValue) {
        this.updateValue = updateValue;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserSys() {
        return createUserSys;
    }

    public void setCreateUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmFunctionUsedRecordDTO that = (AdmFunctionUsedRecordDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(functionId, that.functionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, functionId);
    }

    @Override
    public String toString() {
        return "AdmFunctionUsedRecordDTO{" +
                "id=" + id +
                ", functionId='" + functionId + '\'' +
                ", functionExtendId='" + functionExtendId + '\'' +
                ", sysCategory='" + sysCategory + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableKey='" + tableKey + '\'' +
                ", updateValue='" + updateValue + '\'' +
                ", recordContent='" + recordContent + '\'' +
                ", createTime=" + createTime +
                ", createUserSys='" + createUserSys + '\'' +
                '}';
    }
}
