package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmSysParameterDTO {

    @NotNull
    @Size(max = 20)
    private String sysName;

    @NotNull
    @Size(max = 20)
    private String sysType;

    @NotNull
    @Size(max = 50)
    private String sysValue;

    @Size(max = 500)
    private String descript;

    @NotNull
    @Size(max = 1)
    private String status;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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
        AdmSysParameterDTO that = (AdmSysParameterDTO) o;
        return Objects.equals(sysName, that.sysName) && Objects.equals(sysType, that.sysType) && Objects.equals(sysValue, that.sysValue) && Objects.equals(descript, that.descript) && Objects.equals(status, that.status) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysName, sysType, sysValue, descript, status, updateUser, updateTime);
    }

    @Override
    public String toString() {
        return "AdmSysParameterDTO{" +
                "sysName='" + sysName + '\'' +
                ", sysType='" + sysType + '\'' +
                ", sysValue='" + sysValue + '\'' +
                ", descript='" + descript + '\'' +
                ", status='" + status + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
