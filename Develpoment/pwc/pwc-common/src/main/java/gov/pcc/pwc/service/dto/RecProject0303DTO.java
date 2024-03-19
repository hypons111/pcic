package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

public class RecProject0303DTO {

    private String projectNo;

    private String applySubmitNo;

    private String approveNo;

    private String projectCode;

    private String projectCodeName;

    private String projectName;

    private String city;

    private String village;

    private String applyAmount;

    private String reviewAmount;

    private String projectStatus;

    private String projectStatusName;

    private Instant statusChangeDate;

    private Instant damageDate;

    private String damageDescript;

    private Long dangerousDegree;

    private String dangerousDescript;

    private String createUser;

    private Instant createTime;

    private String updateUser;

    private Instant updateTime;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getApplySubmitNo() {
        return applySubmitNo;
    }

    public void setApplySubmitNo(String applySubmitNo) {
        this.applySubmitNo = applySubmitNo;
    }

    public String getApproveNo() {
        return approveNo;
    }

    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(String reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatusName() {
        return projectStatusName;
    }

    public void setProjectStatusName(String projectStatusName) {
        this.projectStatusName = projectStatusName;
    }

    public Instant getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Instant statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public Instant getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(Instant damageDate) {
        this.damageDate = damageDate;
    }

    public String getDamageDescript() {
        return damageDescript;
    }

    public void setDamageDescript(String damageDescript) {
        this.damageDescript = damageDescript;
    }

    public Long getDangerousDegree() {
        return dangerousDegree;
    }

    public void setDangerousDegree(Long dangerousDegree) {
        this.dangerousDegree = dangerousDegree;
    }

    public String getDangerousDescript() {
        return dangerousDescript;
    }

    public void setDangerousDescript(String dangerousDescript) {
        this.dangerousDescript = dangerousDescript;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
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
    
    @Override
    public String toString() {
        return "RecProject0303DTO{" +
                "projectNo=" + getProjectNo() +
                "}";
    }
}
