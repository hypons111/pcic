package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.Objects;

public class AdmFunctionClickDTO {

    private Long id;

    private String userId;

    private String functionId;

    private String functionName;

    private String functionExtendId;

    private String functionExtendName;

    private Instant createTime;

    private String useCount;

    private int rowNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionExtendId() {
        return functionExtendId;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public String getFunctionExtendName() {
        return functionExtendName;
    }

    public void setFunctionExtendName(String functionExtendName) {
        this.functionExtendName = functionExtendName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUseCount() {
        return useCount;
    }

    public void setUseCount(String useCount) {
        this.useCount = useCount;
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
        AdmFunctionClickDTO that = (AdmFunctionClickDTO) o;
        return rowNum == that.rowNum && Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(functionId, that.functionId) && Objects.equals(functionName, that.functionName) && Objects.equals(functionExtendId, that.functionExtendId) && Objects.equals(functionExtendName, that.functionExtendName) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, functionId, functionName, functionExtendId, functionExtendName, createTime, rowNum);
    }

    @Override
    public String toString() {
        return "AdmFunctionClickDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionExtendId='" + functionExtendId + '\'' +
                ", functionExtendName='" + functionExtendName + '\'' +
                ", createTime=" + createTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
