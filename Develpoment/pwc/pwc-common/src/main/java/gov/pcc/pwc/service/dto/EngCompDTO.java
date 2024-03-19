package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngComp} entity.
 */
public class EngCompDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCompId;

    @NotNull
    @Size(max = 1)
    private String type;

    @Size(max = 20)
    private String pccOrgId;

    @Size(max = 20)
    private String userId;

    @Size(max = 100)
    private String chName;

    @Size(max = 100)
    private String enName;

    @Size(max = 20)
    private String compIdno;

    @Size(max = 20)
    private String engCstLicenseId;

    @Size(max = 40)
    private String licenseNo;

    private LocalDate establishDate;

    private BigDecimal capital;

    @Size(max = 100)
    private String subjectList;

    @Size(max = 100)
    private String masterName;

    @Size(max = 100)
    private String masterNameRome;

    @Size(max = 1)
    private String isMasterEngr;

    @Size(max = 500)
    private String engrIdList;

    @Size(max = 1)
    private String isUse;

    @Size(max = 160)
    private String remark;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 40)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 40)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngCompId() {
        return engCompId;
    }

    public void setEngCompId(String engCompId) {
        this.engCompId = engCompId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getEngCstLicenseId() {
        return engCstLicenseId;
    }

    public void setEngCstLicenseId(String engCstLicenseId) {
        this.engCstLicenseId = engCstLicenseId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LocalDate getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(LocalDate establishDate) {
        this.establishDate = establishDate;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterNameRome() {
        return masterNameRome;
    }

    public void setMasterNameRome(String masterNameRome) {
        this.masterNameRome = masterNameRome;
    }

    public String getIsMasterEngr() {
        return isMasterEngr;
    }

    public void setIsMasterEngr(String isMasterEngr) {
        this.isMasterEngr = isMasterEngr;
    }

    public String getEngrIdList() {
        return engrIdList;
    }

    public void setEngrIdList(String engrIdList) {
        this.engrIdList = engrIdList;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCompDTO)) {
            return false;
        }

        EngCompDTO engCompDTO = (EngCompDTO) o;
        if (this.engCompId == null) {
            return false;
        }
        return Objects.equals(this.engCompId, engCompDTO.engCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engCompId);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "EngCompDTO{" +
                "engCompId='" + engCompId + '\'' +
                ", type='" + type + '\'' +
                ", pccOrgId='" + pccOrgId + '\'' +
                ", userId='" + userId + '\'' +
                ", chName='" + chName + '\'' +
                ", enName='" + enName + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", engCstLicenseId='" + engCstLicenseId + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                ", establishDate=" + establishDate +
                ", capital=" + capital +
                ", subjectList='" + subjectList + '\'' +
                ", masterName='" + masterName + '\'' +
                ", masterNameRome='" + masterNameRome + '\'' +
                ", isMasterEngr='" + isMasterEngr + '\'' +
                ", engrIdList='" + engrIdList + '\'' +
                ", isUse='" + isUse + '\'' +
                ", remark='" + remark + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", transferFrom='" + transferFrom + '\'' +
                ", transferTime=" + transferTime +
                '}';
    }
}
