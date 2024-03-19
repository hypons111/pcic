package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.Size;

public class AdmFunctionDTO {

    @Size(max = 20)
    private String functionId;

    @Size(max = 20)
    private String functionName;

    @Size(max = 500)
    private String functionDescript;

    @Size(max = 500)
    private String functionPath;

    @Size(max = 1)
    private String functionCategory;

    @Size(max = 10)
    private String sysCategory;

    private Integer sortNo;

    @Size(max = 20)
    private String masterFunctionId;

    @Size(max = 1)
    private String status;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private String roleFunctionExtendList;

    private String[] roleFunctionExtendListCheckBox;

    private int rowNum;

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

    public String getFunctionDescript() {
        return functionDescript;
    }

    public void setFunctionDescript(String functionDescript) {
        this.functionDescript = functionDescript;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public String getFunctionCategory() {
        return functionCategory;
    }

    public void setFunctionCategory(String functionCategory) {
        this.functionCategory = functionCategory;
    }

    public String getSysCategory() {
        return sysCategory;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getMasterFunctionId() {
        return masterFunctionId;
    }

    public void setMasterFunctionId(String masterFunctionId) {
        this.masterFunctionId = masterFunctionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRoleFunctionExtendList() {
        return roleFunctionExtendList;
    }

    public void setRoleFunctionExtendList(String roleFunctionExtendList) {
        this.roleFunctionExtendList = roleFunctionExtendList;
    }

    public String[] getRoleFunctionExtendListCheckBox() {
        return roleFunctionExtendListCheckBox;
    }

    public void setRoleFunctionExtendListCheckBox(String[] roleFunctionExtendListCheckBox) {
        this.roleFunctionExtendListCheckBox = roleFunctionExtendListCheckBox;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmFunctionDTO)) {
            return false;
        }

        AdmFunctionDTO admFunctionDTO = (AdmFunctionDTO) o;
        if (this.functionId == null) {
            return false;
        }
        return Objects.equals(this.functionId, admFunctionDTO.functionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.functionId);
    }

    @Override
    public String toString() {
        return "AdmFunctionDTO{" +
                "functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescript='" + functionDescript + '\'' +
                ", functionPath='" + functionPath + '\'' +
                ", functionCategory='" + functionCategory + '\'' +
                ", sysCategory='" + sysCategory + '\'' +
                ", sortNo=" + sortNo +
                ", masterFunctionId='" + masterFunctionId + '\'' +
                ", status='" + status + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", roleFunctionExtendList='" + roleFunctionExtendList + '\'' +
                ", roleFunctionExtendListCheckBox=" + Arrays.toString(roleFunctionExtendListCheckBox) +
                ", rowNum=" + rowNum +
                '}';
    }
}
