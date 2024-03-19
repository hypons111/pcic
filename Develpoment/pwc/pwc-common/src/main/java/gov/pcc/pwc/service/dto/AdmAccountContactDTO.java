package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class AdmAccountContactDTO {

    @NotNull
    @Size(max = 50)
    private String userId;

    @Size(max = 20)
    private String pccOrgId;

    private String unitId;

    @Size(max = 10)
    private String compIdno;

    private Long no;

    private String userPadHash;

    @Size(max = 1)
    private String status;

    @Size(max = 1)
    private String auditStatus;

    @Size(max = 1)
    private String userType;

    private String roleIdList;

    @Size(max = 20)
    private String updateUser;

    private Instant PadUpdateTime;

    private Instant updateTime;

    @Size(max = 50)
    private String chName;

    @Size(max = 50)
    private String chNameRome;

    @Size(max = 60)
    private String title;

    private Instant birthday;

    @Size(max = 10)
    private String idno;

    @Size(max = 1)
    private String sex;

    @Size(max = 20)
    private String workTel;

    @Size(max = 15)
    private String mobileTel;

    @Size(max = 100)
    private String email;

    @Size(max = 10)
    private String homeAddrZip;

    @Size(max = 10)
    private String homeAddrCity;

    @Size(max = 10)
    private String homeAddrTown;

    @Size(max = 100)
    private String homeAddrOther;

    @Size(max = 10)
    private String workAddrZip;

    @Size(max = 10)
    private String workAddrCity;

    @Size(max = 10)
    private String workAddrTown;

    @Size(max = 100)
    private String workAddrOther;

    private String agencyCardType;

    private String wkutNormal;

    private int rowNum;

    private String loginUserId;

    private List<PwcSharedDTO> jurisdictionRoles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getUserPadHash() {
        return userPadHash;
    }

    public void setUserPadHash(String userPadHash) {
        this.userPadHash = userPadHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(String roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getPadUpdateTime() {
        return PadUpdateTime;
    }

    public void setPadUpdateTime(Instant padUpdateTime) {
        PadUpdateTime = padUpdateTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddrZip() {
        return homeAddrZip;
    }

    public void setHomeAddrZip(String homeAddrZip) {
        this.homeAddrZip = homeAddrZip;
    }

    public String getHomeAddrCity() {
        return homeAddrCity;
    }

    public void setHomeAddrCity(String homeAddrCity) {
        this.homeAddrCity = homeAddrCity;
    }

    public String getHomeAddrTown() {
        return homeAddrTown;
    }

    public void setHomeAddrTown(String homeAddrTown) {
        this.homeAddrTown = homeAddrTown;
    }

    public String getHomeAddrOther() {
        return homeAddrOther;
    }

    public void setHomeAddrOther(String homeAddrOther) {
        this.homeAddrOther = homeAddrOther;
    }

    public String getWorkAddrZip() {
        return workAddrZip;
    }

    public void setWorkAddrZip(String workAddrZip) {
        this.workAddrZip = workAddrZip;
    }

    public String getWorkAddrCity() {
        return workAddrCity;
    }

    public void setWorkAddrCity(String workAddrCity) {
        this.workAddrCity = workAddrCity;
    }

    public String getWorkAddrTown() {
        return workAddrTown;
    }

    public void setWorkAddrTown(String workAddrTown) {
        this.workAddrTown = workAddrTown;
    }

    public String getWorkAddrOther() {
        return workAddrOther;
    }

    public void setWorkAddrOther(String workAddrOther) {
        this.workAddrOther = workAddrOther;
    }

    public String getAgencyCardType() {
        return agencyCardType;
    }

    public void setAgencyCardType(String agencyCardType) {
        this.agencyCardType = agencyCardType;
    }

    public String getWkutNormal() {
        return wkutNormal;
    }

    public void setWkutNormal(String wkutNormal) {
        this.wkutNormal = wkutNormal;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public List<PwcSharedDTO> getJurisdictionRoles() {
        return jurisdictionRoles;
    }

    public void setJurisdictionRoles(List<PwcSharedDTO> jurisdictionRoles) {
        this.jurisdictionRoles = jurisdictionRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmAccountContactDTO)) {
            return false;
        }

        AdmAccountContactDTO admAccountContractDTO = (AdmAccountContactDTO) o;
        if (this.userId == null) {
            return false;
        }
        return Objects.equals(this.userId, admAccountContractDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    @Override
    public String toString() {
        return "AdmAccountContactDTO{" +
                "userId='" + userId + '\'' +
                ", pccOrgId='" + pccOrgId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", no=" + no +
                ", userPadHash='" + userPadHash + '\'' +
                ", status='" + status + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", userType='" + userType + '\'' +
                ", roleIdList='" + roleIdList + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", PadUpdateTime=" + PadUpdateTime +
                ", updateTime=" + updateTime +
                ", chName='" + chName + '\'' +
                ", chNameRome='" + chNameRome + '\'' +
                ", title='" + title + '\'' +
                ", birthday=" + birthday +
                ", idno='" + idno + '\'' +
                ", sex='" + sex + '\'' +
                ", workTel='" + workTel + '\'' +
                ", mobileTel='" + mobileTel + '\'' +
                ", email='" + email + '\'' +
                ", homeAddrZip='" + homeAddrZip + '\'' +
                ", homeAddrCity='" + homeAddrCity + '\'' +
                ", homeAddrTown='" + homeAddrTown + '\'' +
                ", homeAddrOther='" + homeAddrOther + '\'' +
                ", workAddrZip='" + workAddrZip + '\'' +
                ", workAddrCity='" + workAddrCity + '\'' +
                ", workAddrTown='" + workAddrTown + '\'' +
                ", workAddrOther='" + workAddrOther + '\'' +
                ", agencyCardType='" + agencyCardType + '\'' +
                ", wkutNormal='" + wkutNormal + '\'' +
                ", rowNum=" + rowNum + '\'' +
                ", loginUserId=" + loginUserId + '\'' +
                ", jurisdictionRoles=" + jurisdictionRoles +
                '}';
    }
}
