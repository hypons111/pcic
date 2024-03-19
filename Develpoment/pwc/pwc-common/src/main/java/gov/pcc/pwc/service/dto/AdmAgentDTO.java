package gov.pcc.pwc.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class AdmAgentDTO {

    @Size(max = 20)
    private String orgId;

    @Size(max = 20)
    private String pccOrgId;

    @Size(max = 60)
    private String orgName;

    @Size(max = 500)
    private String orgNameEn;

    @Size(max = 100)
    private String addr;

    @Size(max = 20)
    private String tel;

    @Size(max = 20)
    private String fax;

    @Size(max = 500)
    private String descript;

    @Size(max = 20)
    private String adminOrgId;

    @DecimalMin(value = "0")
    @DecimalMax(value = "9")
    private BigDecimal orgLevel;

    @Size(max = 1)
    private String cutMark;

    @Size(max = 20)
    private String oldOrgId;

    @Size(max = 50)
    private String oldOrgName;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    private int rowNum;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNameEn() {
        return orgNameEn;
    }

    public void setOrgNameEn(String orgNameEn) {
        this.orgNameEn = orgNameEn;
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

    public String getAdminOrgId() {
        return adminOrgId;
    }

    public void setAdminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
    }

    public BigDecimal getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(BigDecimal orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getCutMark() {
        return cutMark;
    }

    public void setCutMark(String cutMark) {
        this.cutMark = cutMark;
    }

    public String getOldOrgId() {
        return oldOrgId;
    }

    public void setOldOrgId(String oldOrgId) {
        this.oldOrgId = oldOrgId;
    }

    public String getOldOrgName() {
        return oldOrgName;
    }

    public void setOldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName;
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
        AdmAgentDTO that = (AdmAgentDTO) o;
        return orgId.equals(that.orgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId);
    }

    @Override
    public String toString() {
        return "AdmAgentDTO{" +
                "orgId='" + orgId + '\'' +
                ", pccOrgId='" + pccOrgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgNameEn='" + orgNameEn + '\'' +
                ", addr='" + addr + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", descript='" + descript + '\'' +
                ", adminOrgId='" + adminOrgId + '\'' +
                ", orgLevel=" + orgLevel +
                ", cutMark='" + cutMark + '\'' +
                ", oldOrgId='" + oldOrgId + '\'' +
                ", oldOrgName='" + oldOrgName + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", rowNum=" + rowNum +
                '}';
    }
}
