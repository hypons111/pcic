package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecProject} entity.
 */
public class RecProjectDTO implements Serializable {

//    private Long id;

    @NotNull
    @Size(max = 22)
    private String projectNo;

    @NotNull
    @Size(max = 36)
    private String typhoonNo;

    @Size(max = 20)
    private String projectCode;

    @Size(max = 20)
    private String applyOrgId;

    @Size(max = 3)
    private String applySubmitNo;

    @Size(max = 3)
    private String approveNo;

    @Size(max = 200)
    private String projectName;

    private Instant damageDate;

    @Size(max = 4000)
    private String damageDescript;

    private Long dangerousDegree;

    @Size(max = 4000)
    private String dangerousDescript;

    @Size(max = 44)
    private String reasonAnalysis;

    @Size(max = 4000)
    private String otherReason;

    private BigDecimal applyAmount;

    @Size(max = 20)
    private String projectStatus;

    @Size(max = 20)
    private String applyUserId;

    @Size(max = 20)
    private String deleteUserId;

    private Instant approveDate;

    private BigDecimal reviewAmount;

    @Size(max = 4000)
    private String reviewDescript;

    private Boolean isReviewWatch;

    private Boolean isReviewInCalList;

    @Size(max = 20)
    private String executionStatus;

    private BigDecimal changeAmount;

    @Size(max = 4000)
    private String changeDescript;

    private Boolean isReviewChangeAmount;

    @Size(max = 4000)
    private String reviewChangeDescript;

    private Boolean isCancelAmount;

    @Size(max = 4000)
    private String cancelDescript;

    @Size(max = 20)
    private String reviewProjectCode;

    private Instant statusChangeDate;

    private BigDecimal centerAllowanceAmount;

    private BigDecimal localReservesAmount;

    private BigDecimal returnAmount;

    @Size(max = 20)
    private String returnStatus;

    private Boolean isSurveyPlan;

    private Instant surveyPlanDate;

    private Boolean isNoDemand;

    private Instant surveyPlanEndDate;

    private Instant surveyPlanDate1;

    private Instant surveyPlanDate2;

    private Instant surveyPlanDate3;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant updateTime;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getTyphoonNo() {
        return typhoonNo;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getReasonAnalysis() {
        return reasonAnalysis;
    }

    public void setReasonAnalysis(String reasonAnalysis) {
        this.reasonAnalysis = reasonAnalysis;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Instant getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Instant approveDate) {
        this.approveDate = approveDate;
    }

    public BigDecimal getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(BigDecimal reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getReviewDescript() {
        return reviewDescript;
    }

    public void setReviewDescript(String reviewDescript) {
        this.reviewDescript = reviewDescript;
    }

    public Boolean getIsReviewWatch() {
        return isReviewWatch;
    }

    public void setIsReviewWatch(Boolean isReviewWatch) {
        this.isReviewWatch = isReviewWatch;
    }

    public Boolean getIsReviewInCalList() {
        return isReviewInCalList;
    }

    public void setIsReviewInCalList(Boolean isReviewInCalList) {
        this.isReviewInCalList = isReviewInCalList;
    }

    public String getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeDescript() {
        return changeDescript;
    }

    public void setChangeDescript(String changeDescript) {
        this.changeDescript = changeDescript;
    }

    public Boolean getIsReviewChangeAmount() {
        return isReviewChangeAmount;
    }

    public void setIsReviewChangeAmount(Boolean isReviewChangeAmount) {
        this.isReviewChangeAmount = isReviewChangeAmount;
    }

    public String getReviewChangeDescript() {
        return reviewChangeDescript;
    }

    public void setReviewChangeDescript(String reviewChangeDescript) {
        this.reviewChangeDescript = reviewChangeDescript;
    }

    public Boolean getIsCancelAmount() {
        return isCancelAmount;
    }

    public void setIsCancelAmount(Boolean isCancelAmount) {
        this.isCancelAmount = isCancelAmount;
    }

    public String getCancelDescript() {
        return cancelDescript;
    }

    public void setCancelDescript(String cancelDescript) {
        this.cancelDescript = cancelDescript;
    }

    public String getReviewProjectCode() {
        return reviewProjectCode;
    }

    public void setReviewProjectCode(String reviewProjectCode) {
        this.reviewProjectCode = reviewProjectCode;
    }

    public Instant getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Instant statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public BigDecimal getCenterAllowanceAmount() {
        return centerAllowanceAmount;
    }

    public void setCenterAllowanceAmount(BigDecimal centerAllowanceAmount) {
        this.centerAllowanceAmount = centerAllowanceAmount;
    }

    public BigDecimal getLocalReservesAmount() {
        return localReservesAmount;
    }

    public void setLocalReservesAmount(BigDecimal localReservesAmount) {
        this.localReservesAmount = localReservesAmount;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Boolean getIsSurveyPlan() {
        return isSurveyPlan;
    }

    public void setIsSurveyPlan(Boolean isSurveyPlan) {
        this.isSurveyPlan = isSurveyPlan;
    }

    public Instant getSurveyPlanDate() {
        return surveyPlanDate;
    }

    public void setSurveyPlanDate(Instant surveyPlanDate) {
        this.surveyPlanDate = surveyPlanDate;
    }

    public Boolean getIsNoDemand() {
        return isNoDemand;
    }

    public void setIsNoDemand(Boolean isNoDemand) {
        this.isNoDemand = isNoDemand;
    }

    public Instant getSurveyPlanEndDate() {
        return surveyPlanEndDate;
    }

    public void setSurveyPlanEndDate(Instant surveyPlanEndDate) {
        this.surveyPlanEndDate = surveyPlanEndDate;
    }

    public Instant getSurveyPlanDate1() {
        return surveyPlanDate1;
    }

    public void setSurveyPlanDate1(Instant surveyPlanDate1) {
        this.surveyPlanDate1 = surveyPlanDate1;
    }

    public Instant getSurveyPlanDate2() {
        return surveyPlanDate2;
    }

    public void setSurveyPlanDate2(Instant surveyPlanDate2) {
        this.surveyPlanDate2 = surveyPlanDate2;
    }

    public Instant getSurveyPlanDate3() {
        return surveyPlanDate3;
    }

    public void setSurveyPlanDate3(Instant surveyPlanDate3) {
        this.surveyPlanDate3 = surveyPlanDate3;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecProjectDTO)) {
            return false;
        }

        RecProjectDTO recProjectDTO = (RecProjectDTO) o;
        if (this.projectNo == null) {
            return false;
        }
        return Objects.equals(this.projectNo, recProjectDTO.projectNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.projectNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecProjectDTO{" +
//            "id=" + getId() +
            "projectNo='" + getProjectNo() + "'" +
            ", typhoonNo='" + getTyphoonNo() + "'" +
            ", projectCode='" + getProjectCode() + "'" +
            ", applyOrgId='" + getApplyOrgId() + "'" +
            ", applySubmitNo='" + getApplySubmitNo() + "'" +
            ", approveNo='" + getApproveNo() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", damageDate='" + getDamageDate() + "'" +
            ", damageDescript='" + getDamageDescript() + "'" +
            ", dangerousDegree=" + getDangerousDegree() +
            ", dangerousDescript='" + getDangerousDescript() + "'" +
            ", reasonAnalysis='" + getReasonAnalysis() + "'" +
            ", otherReason='" + getOtherReason() + "'" +
            ", applyAmount=" + getApplyAmount() +
            ", projectStatus='" + getProjectStatus() + "'" +
            ", applyUserId='" + getApplyUserId() + "'" +
            ", deleteUserId='" + getDeleteUserId() + "'" +
            ", approveDate='" + getApproveDate() + "'" +
            ", reviewAmount=" + getReviewAmount() +
            ", reviewDescript='" + getReviewDescript() + "'" +
            ", isReviewWatch='" + getIsReviewWatch() + "'" +
            ", isReviewInCalList='" + getIsReviewInCalList() + "'" +
            ", executionStatus='" + getExecutionStatus() + "'" +
            ", changeAmount=" + getChangeAmount() +
            ", changeDescript='" + getChangeDescript() + "'" +
            ", isReviewChangeAmount='" + getIsReviewChangeAmount() + "'" +
            ", reviewChangeDescript='" + getReviewChangeDescript() + "'" +
            ", isCancelAmount='" + getIsCancelAmount() + "'" +
            ", cancelDescript='" + getCancelDescript() + "'" +
            ", reviewProjectCode='" + getReviewProjectCode() + "'" +
            ", statusChangeDate='" + getStatusChangeDate() + "'" +
            ", centerAllowanceAmount=" + getCenterAllowanceAmount() +
            ", localReservesAmount=" + getLocalReservesAmount() +
            ", returnAmount=" + getReturnAmount() +
            ", returnStatus='" + getReturnStatus() + "'" +
            ", isSurveyPlan='" + getIsSurveyPlan() + "'" +
            ", surveyPlanDate='" + getSurveyPlanDate() + "'" +
            ", isNoDemand='" + getIsNoDemand() + "'" +
            ", surveyPlanEndDate='" + getSurveyPlanEndDate() + "'" +
            ", surveyPlanDate1='" + getSurveyPlanDate1() + "'" +
            ", surveyPlanDate2='" + getSurveyPlanDate2() + "'" +
            ", surveyPlanDate3='" + getSurveyPlanDate3() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
