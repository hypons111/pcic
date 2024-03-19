package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A EngComp
 */
@Entity
@Table(name = "eng_comp")
public class EngComp implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_comp_id", length = 20, nullable = false)
    private String engCompId;

    @NotNull
    @Size(max = 1)
    @Column(name = "type", length = 1, nullable = false)
    private String type;

    @Size(max = 20)
    @Column(name = "PCC_ORG_ID", length = 20)
    private String pccOrgId;

    @Size(max = 20)
    @Column(name = "USER_ID", length = 20)
    private String userId;


    @Size(max = 100)
    @Column(name = "ch_name", length = 100)
    private String chName;

    @Size(max = 100)
    @Column(name = "en_name", length = 100)
    private String enName;

    @Size(max = 20)
    @Column(name = "comp_idno", length = 20)
    private String compIdno;

    @Size(max = 20)
    @Column(name = "eng_cst_license_id", length = 20)
    private String engCstLicenseId;

    @Size(max = 40)
    @Column(name = "license_no", length = 40)
    private String licenseNo;

    @Column(name = "establish_date")
    private LocalDate establishDate;

    @Column(name = "capital", precision = 13, scale = 0)
    private BigDecimal capital;

    @Size(max = 100)
    @Column(name = "subject_list", length = 100)
    private String subjectList;

    @Size(max = 100)
    @Column(name = "master_name", length = 100)
    private String masterName;

    @Size(max = 100)
    @Column(name = "master_name_rome", length = 100)
    private String masterNameRome;

    @Size(max = 1)
    @Column(name = "is_master_engr", length = 1)
    private String isMasterEngr;

    @Size(max = 500)
    @Column(name = "engr_id_list", length = 500)
    private String engrIdList;

    @Size(max = 1)
    @Column(name = "is_use", length = 1)
    private String isUse;

    @Size(max = 160)
    @Column(name = "remark", length = 160)
    private String remark;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 40)
    @Column(name = "update_user", length = 40, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 40)
    @Column(name = "create_user", length = 40, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngCompId() {
        return this.engCompId;
    }

    public EngComp engCompId(String engCompId) {
        this.setEngCompId(engCompId);
        return this;
    }

    public void setEngCompId(String engCompId) {
        this.engCompId = engCompId;
    }

    public String getType() {
        return this.type;
    }

    public EngComp type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChName() {
        return this.chName;
    }

    public EngComp chName(String chName) {
        this.setChName(chName);
        return this;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getEnName() {
        return this.enName;
    }

    public EngComp enName(String enName) {
        this.setEnName(enName);
        return this;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCompIdno() {
        return this.compIdno;
    }

    public EngComp compIdno(String compIdno) {
        this.setCompIdno(compIdno);
        return this;
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

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getEngCstLicenseId() {
        return this.engCstLicenseId;
    }

    public EngComp engCstLicenseId(String engCstLicenseId) {
        this.setEngCstLicenseId(engCstLicenseId);
        return this;
    }

    public void setEngCstLicenseId(String engCstLicenseId) {
        this.engCstLicenseId = engCstLicenseId;
    }

    public String getLicenseNo() {
        return this.licenseNo;
    }

    public EngComp licenseNo(String licenseNo) {
        this.setLicenseNo(licenseNo);
        return this;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public LocalDate getEstablishDate() {
        return this.establishDate;
    }

    public EngComp establishDate(LocalDate establishDate) {
        this.setEstablishDate(establishDate);
        return this;
    }

    public void setEstablishDate(LocalDate establishDate) {
        this.establishDate = establishDate;
    }

    public BigDecimal getCapital() {
        return this.capital;
    }

    public EngComp capital(BigDecimal capital) {
        this.setCapital(capital);
        return this;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public EngComp subjectList(String subjectList) {
        this.setSubjectList(subjectList);
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getMasterName() {
        return this.masterName;
    }

    public EngComp masterName(String masterName) {
        this.setMasterName(masterName);
        return this;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterNameRome() {
        return this.masterNameRome;
    }

    public EngComp masterNameRome(String masterNameRome) {
        this.setMasterNameRome(masterNameRome);
        return this;
    }

    public void setMasterNameRome(String masterNameRome) {
        this.masterNameRome = masterNameRome;
    }

    public String getIsMasterEngr() {
        return this.isMasterEngr;
    }

    public EngComp isMasterEngr(String isMasterEngr) {
        this.setIsMasterEngr(isMasterEngr);
        return this;
    }

    public void setIsMasterEngr(String isMasterEngr) {
        this.isMasterEngr = isMasterEngr;
    }

    public String getEngrIdList() {
        return this.engrIdList;
    }

    public EngComp engrIdList(String engrIdList) {
        this.setEngrIdList(engrIdList);
        return this;
    }

    public void setEngrIdList(String engrIdList) {
        this.engrIdList = engrIdList;
    }

    public String getIsUse() {
        return this.isUse;
    }

    public EngComp isUse(String isUse) {
        this.setIsUse(isUse);
        return this;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngComp remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngComp updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngComp updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngComp createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngComp createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngComp transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngComp transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngComp)) {
            return false;
        }
        return engCompId != null && engCompId.equals(((EngComp) o).engCompId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "EngComp{" +
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
