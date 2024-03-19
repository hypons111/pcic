package gov.pcc.pwc.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

public class AdmUnitDTO {

    @Size(max = 20)
    private String unitId;

    @Size(max = 20)
    private String masterUnitId;

    @Size(max = 20)
    private String adminOrgId;

    @Size(max = 60)
    private String unitName;

    @Size(max = 500)
    private String addr;

    @Size(max = 20)
    private String tel;

    @Size(max = 20)
    private String fax;

    @Size(max = 500)
    private String descript;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getMasterUnitId() {
        return masterUnitId;
    }

    public void setMasterUnitId(String masterUnitId) {
        this.masterUnitId = masterUnitId;
    }

    public String getAdminOrgId() {
        return adminOrgId;
    }

    public void setAdminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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
        AdmUnitDTO that = (AdmUnitDTO) o;
        return rowNum == that.rowNum && Objects.equals(unitId, that.unitId) && Objects.equals(masterUnitId, that.masterUnitId) && Objects.equals(adminOrgId, that.adminOrgId) && Objects.equals(unitName, that.unitName) && Objects.equals(addr, that.addr) && Objects.equals(tel, that.tel) && Objects.equals(fax, that.fax) && Objects.equals(descript, that.descript) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitId, masterUnitId, adminOrgId, unitName, addr, tel, fax, descript, updateUser, updateTime, rowNum);
    }

    @Override
    public String toString() {
        return "AdmUnitDTO{" +
                "unitId='" + unitId + '\'' +
                ", masterUnitId='" + masterUnitId + '\'' +
                ", adminOrgId='" + adminOrgId + '\'' +
                ", unitName='" + unitName + '\'' +
                ", addr='" + addr + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", descript='" + descript + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
