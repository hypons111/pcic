package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.Size;

public class AdmFunctionExtendDTO {

    @Size(max = 20)
    private String functionExtendId;

    @Size(max = 20)
    private String functionExtendName;

    @Size(max = 500)
    private String functionExtendDescript;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

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

    public String getFunctionExtendDescript() {
        return functionExtendDescript;
    }

    public void setFunctionExtendDescript(String functionExtendDescript) {
        this.functionExtendDescript = functionExtendDescript;
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmFunctionExtendDTO)) {
            return false;
        }

        AdmFunctionExtendDTO admFunctionDTO = (AdmFunctionExtendDTO) o;
        if (this.functionExtendId == null) {
            return false;
        }
        return Objects.equals(this.functionExtendId, admFunctionDTO.functionExtendId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.functionExtendId);
    }

    @Override
    public String toString() {
        return "AdmFunctionExtendDTO{" +
                "functionExtendId='" + functionExtendId + '\'' +
                ", functionExtendName='" + functionExtendName + '\'' +
                ", functionExtendDescript='" + functionExtendDescript + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
