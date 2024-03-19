package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A RecProject.
 */
@Entity
@Table(name = "rec_project")
public class RecProject implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;


    @NotNull
    @Size(max = 22)
    @Id
    @Column(name = "project_no", length = 22, nullable = false)
    private String projectNo;

    @NotNull
    @Size(max = 36)
    @Column(name = "typhoon_no", length = 36, nullable = false)
    private String typhoonNo;

    @Size(max = 20)
    @Column(name = "project_code", length = 20)
    private String projectCode;

    @Size(max = 20)
    @Column(name = "apply_org_id", length = 20)
    private String applyOrgId;

    @Size(max = 3)
    @Column(name = "apply_submit_no", length = 3)
    private String applySubmitNo;

    @Size(max = 3)
    @Column(name = "approve_no", length = 3)
    private String approveNo;

    @Size(max = 200)
    @Column(name = "project_name", length = 200)
    private String projectName;

    @Column(name = "damage_date")
    private Instant damageDate;

    @Size(max = 4000)
    @Column(name = "damage_descript", length = 4000)
    private String damageDescript;

    @Column(name = "dangerous_degree")
    private Long dangerousDegree;

    @Size(max = 4000)
    @Column(name = "dangerous_descript", length = 4000)
    private String dangerousDescript;

    @Size(max = 44)
    @Column(name = "reason_analysis", length = 44)
    private String reasonAnalysis;

    @Size(max = 4000)
    @Column(name = "other_reason", length = 4000)
    private String otherReason;

    @Column(name = "apply_amount", precision = 21, scale = 2)
    private BigDecimal applyAmount;

    @Size(max = 20)
    @Column(name = "project_status", length = 20)
    private String projectStatus;

    @Size(max = 20)
    @Column(name = "apply_user_id", length = 20)
    private String applyUserId;

    @Size(max = 20)
    @Column(name = "delete_user_id", length = 20)
    private String deleteUserId;

    @Column(name = "approve_date")
    private Instant approveDate;

    @Column(name = "review_amount", precision = 21, scale = 2)
    private BigDecimal reviewAmount;

    @Size(max = 4000)
    @Column(name = "review_descript", length = 4000)
    private String reviewDescript;

    @Column(name = "is_review_watch")
    private Boolean isReviewWatch;

    @Column(name = "is_review_in_cal_list")
    private Boolean isReviewInCalList;

    @Size(max = 20)
    @Column(name = "execution_status", length = 20)
    private String executionStatus;

    @Column(name = "change_amount", precision = 21, scale = 2)
    private BigDecimal changeAmount;

    @Size(max = 4000)
    @Column(name = "change_descript", length = 4000)
    private String changeDescript;

    @Column(name = "is_review_change_amount")
    private Boolean isReviewChangeAmount;

    @Size(max = 4000)
    @Column(name = "review_change_descript", length = 4000)
    private String reviewChangeDescript;

    @Column(name = "is_cancel_amount")
    private Boolean isCancelAmount;

    @Size(max = 4000)
    @Column(name = "cancel_descript", length = 4000)
    private String cancelDescript;

    @Size(max = 20)
    @Column(name = "review_project_code", length = 20)
    private String reviewProjectCode;

    @Column(name = "status_change_date")
    private Instant statusChangeDate;

    @Column(name = "center_allowance_amount", precision = 21, scale = 2)
    private BigDecimal centerAllowanceAmount;

    @Column(name = "local_reserves_amount", precision = 21, scale = 2)
    private BigDecimal localReservesAmount;

    @Column(name = "return_amount", precision = 21, scale = 2)
    private BigDecimal returnAmount;

    @Size(max = 20)
    @Column(name = "return_status", length = 20)
    private String returnStatus;

    @Column(name = "is_survey_plan")
    private Boolean isSurveyPlan;

    @Column(name = "survey_plan_date")
    private Instant surveyPlanDate;

    @Column(name = "is_no_demand")
    private Boolean isNoDemand;

    @Column(name = "survey_plan_end_date")
    private Instant surveyPlanEndDate;

    @Column(name = "survey_plan_date1")
    private Instant surveyPlanDate1;

    @Column(name = "survey_plan_date2")
    private Instant surveyPlanDate2;

    @Column(name = "survey_plan_date3")
    private Instant surveyPlanDate3;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

//    public Long getId() {
//        return this.id;
//    }
//
//    public RecProject id(Long id) {
//        this.setId(id);
//        return this;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getProjectNo() {
        return this.projectNo;
    }

    public RecProject projectNo(String projectNo) {
        this.setProjectNo(projectNo);
        return this;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getTyphoonNo() {
        return this.typhoonNo;
    }

    public RecProject typhoonNo(String typhoonNo) {
        this.setTyphoonNo(typhoonNo);
        return this;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public RecProject projectCode(String projectCode) {
        this.setProjectCode(projectCode);
        return this;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getApplyOrgId() {
        return this.applyOrgId;
    }

    public RecProject applyOrgId(String applyOrgId) {
        this.setApplyOrgId(applyOrgId);
        return this;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId;
    }

    public String getApplySubmitNo() {
        return this.applySubmitNo;
    }

    public RecProject applySubmitNo(String applySubmitNo) {
        this.setApplySubmitNo(applySubmitNo);
        return this;
    }

    public void setApplySubmitNo(String applySubmitNo) {
        this.applySubmitNo = applySubmitNo;
    }

    public String getApproveNo() {
        return this.approveNo;
    }

    public RecProject approveNo(String approveNo) {
        this.setApproveNo(approveNo);
        return this;
    }

    public void setApproveNo(String approveNo) {
        this.approveNo = approveNo;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public RecProject projectName(String projectName) {
        this.setProjectName(projectName);
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Instant getDamageDate() {
        return this.damageDate;
    }

    public RecProject damageDate(Instant damageDate) {
        this.setDamageDate(damageDate);
        return this;
    }

    public void setDamageDate(Instant damageDate) {
        this.damageDate = damageDate;
    }

    public String getDamageDescript() {
        return this.damageDescript;
    }

    public RecProject damageDescript(String damageDescript) {
        this.setDamageDescript(damageDescript);
        return this;
    }

    public void setDamageDescript(String damageDescript) {
        this.damageDescript = damageDescript;
    }

    public Long getDangerousDegree() {
        return this.dangerousDegree;
    }

    public RecProject dangerousDegree(Long dangerousDegree) {
        this.setDangerousDegree(dangerousDegree);
        return this;
    }

    public void setDangerousDegree(Long dangerousDegree) {
        this.dangerousDegree = dangerousDegree;
    }

    public String getDangerousDescript() {
        return this.dangerousDescript;
    }

    public RecProject dangerousDescript(String dangerousDescript) {
        this.setDangerousDescript(dangerousDescript);
        return this;
    }

    public void setDangerousDescript(String dangerousDescript) {
        this.dangerousDescript = dangerousDescript;
    }

    public String getReasonAnalysis() {
        return this.reasonAnalysis;
    }

    public RecProject reasonAnalysis(String reasonAnalysis) {
        this.setReasonAnalysis(reasonAnalysis);
        return this;
    }

    public void setReasonAnalysis(String reasonAnalysis) {
        this.reasonAnalysis = reasonAnalysis;
    }

    public String getOtherReason() {
        return this.otherReason;
    }

    public RecProject otherReason(String otherReason) {
        this.setOtherReason(otherReason);
        return this;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public BigDecimal getApplyAmount() {
        return this.applyAmount;
    }

    public RecProject applyAmount(BigDecimal applyAmount) {
        this.setApplyAmount(applyAmount);
        return this;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getProjectStatus() {
        return this.projectStatus;
    }

    public RecProject projectStatus(String projectStatus) {
        this.setProjectStatus(projectStatus);
        return this;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getApplyUserId() {
        return this.applyUserId;
    }

    public RecProject applyUserId(String applyUserId) {
        this.setApplyUserId(applyUserId);
        return this;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getDeleteUserId() {
        return this.deleteUserId;
    }

    public RecProject deleteUserId(String deleteUserId) {
        this.setDeleteUserId(deleteUserId);
        return this;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public Instant getApproveDate() {
        return this.approveDate;
    }

    public RecProject approveDate(Instant approveDate) {
        this.setApproveDate(approveDate);
        return this;
    }

    public void setApproveDate(Instant approveDate) {
        this.approveDate = approveDate;
    }

    public BigDecimal getReviewAmount() {
        return this.reviewAmount;
    }

    public RecProject reviewAmount(BigDecimal reviewAmount) {
        this.setReviewAmount(reviewAmount);
        return this;
    }

    public void setReviewAmount(BigDecimal reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getReviewDescript() {
        return this.reviewDescript;
    }

    public RecProject reviewDescript(String reviewDescript) {
        this.setReviewDescript(reviewDescript);
        return this;
    }

    public void setReviewDescript(String reviewDescript) {
        this.reviewDescript = reviewDescript;
    }

    public Boolean getIsReviewWatch() {
        return this.isReviewWatch;
    }

    public RecProject isReviewWatch(Boolean isReviewWatch) {
        this.setIsReviewWatch(isReviewWatch);
        return this;
    }

    public void setIsReviewWatch(Boolean isReviewWatch) {
        this.isReviewWatch = isReviewWatch;
    }

    public Boolean getIsReviewInCalList() {
        return this.isReviewInCalList;
    }

    public RecProject isReviewInCalList(Boolean isReviewInCalList) {
        this.setIsReviewInCalList(isReviewInCalList);
        return this;
    }

    public void setIsReviewInCalList(Boolean isReviewInCalList) {
        this.isReviewInCalList = isReviewInCalList;
    }

    public String getExecutionStatus() {
        return this.executionStatus;
    }

    public RecProject executionStatus(String executionStatus) {
        this.setExecutionStatus(executionStatus);
        return this;
    }

    public void setExecutionStatus(String executionStatus) {
        this.executionStatus = executionStatus;
    }

    public BigDecimal getChangeAmount() {
        return this.changeAmount;
    }

    public RecProject changeAmount(BigDecimal changeAmount) {
        this.setChangeAmount(changeAmount);
        return this;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeDescript() {
        return this.changeDescript;
    }

    public RecProject changeDescript(String changeDescript) {
        this.setChangeDescript(changeDescript);
        return this;
    }

    public void setChangeDescript(String changeDescript) {
        this.changeDescript = changeDescript;
    }

    public Boolean getIsReviewChangeAmount() {
        return this.isReviewChangeAmount;
    }

    public RecProject isReviewChangeAmount(Boolean isReviewChangeAmount) {
        this.setIsReviewChangeAmount(isReviewChangeAmount);
        return this;
    }

    public void setIsReviewChangeAmount(Boolean isReviewChangeAmount) {
        this.isReviewChangeAmount = isReviewChangeAmount;
    }

    public String getReviewChangeDescript() {
        return this.reviewChangeDescript;
    }

    public RecProject reviewChangeDescript(String reviewChangeDescript) {
        this.setReviewChangeDescript(reviewChangeDescript);
        return this;
    }

    public void setReviewChangeDescript(String reviewChangeDescript) {
        this.reviewChangeDescript = reviewChangeDescript;
    }

    public Boolean getIsCancelAmount() {
        return this.isCancelAmount;
    }

    public RecProject isCancelAmount(Boolean isCancelAmount) {
        this.setIsCancelAmount(isCancelAmount);
        return this;
    }

    public void setIsCancelAmount(Boolean isCancelAmount) {
        this.isCancelAmount = isCancelAmount;
    }

    public String getCancelDescript() {
        return this.cancelDescript;
    }

    public RecProject cancelDescript(String cancelDescript) {
        this.setCancelDescript(cancelDescript);
        return this;
    }

    public void setCancelDescript(String cancelDescript) {
        this.cancelDescript = cancelDescript;
    }

    public String getReviewProjectCode() {
        return this.reviewProjectCode;
    }

    public RecProject reviewProjectCode(String reviewProjectCode) {
        this.setReviewProjectCode(reviewProjectCode);
        return this;
    }

    public void setReviewProjectCode(String reviewProjectCode) {
        this.reviewProjectCode = reviewProjectCode;
    }

    public Instant getStatusChangeDate() {
        return this.statusChangeDate;
    }

    public RecProject statusChangeDate(Instant statusChangeDate) {
        this.setStatusChangeDate(statusChangeDate);
        return this;
    }

    public void setStatusChangeDate(Instant statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public BigDecimal getCenterAllowanceAmount() {
        return this.centerAllowanceAmount;
    }

    public RecProject centerAllowanceAmount(BigDecimal centerAllowanceAmount) {
        this.setCenterAllowanceAmount(centerAllowanceAmount);
        return this;
    }

    public void setCenterAllowanceAmount(BigDecimal centerAllowanceAmount) {
        this.centerAllowanceAmount = centerAllowanceAmount;
    }

    public BigDecimal getLocalReservesAmount() {
        return this.localReservesAmount;
    }

    public RecProject localReservesAmount(BigDecimal localReservesAmount) {
        this.setLocalReservesAmount(localReservesAmount);
        return this;
    }

    public void setLocalReservesAmount(BigDecimal localReservesAmount) {
        this.localReservesAmount = localReservesAmount;
    }

    public BigDecimal getReturnAmount() {
        return this.returnAmount;
    }

    public RecProject returnAmount(BigDecimal returnAmount) {
        this.setReturnAmount(returnAmount);
        return this;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnStatus() {
        return this.returnStatus;
    }

    public RecProject returnStatus(String returnStatus) {
        this.setReturnStatus(returnStatus);
        return this;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Boolean getIsSurveyPlan() {
        return this.isSurveyPlan;
    }

    public RecProject isSurveyPlan(Boolean isSurveyPlan) {
        this.setIsSurveyPlan(isSurveyPlan);
        return this;
    }

    public void setIsSurveyPlan(Boolean isSurveyPlan) {
        this.isSurveyPlan = isSurveyPlan;
    }

    public Instant getSurveyPlanDate() {
        return this.surveyPlanDate;
    }

    public RecProject surveyPlanDate(Instant surveyPlanDate) {
        this.setSurveyPlanDate(surveyPlanDate);
        return this;
    }

    public void setSurveyPlanDate(Instant surveyPlanDate) {
        this.surveyPlanDate = surveyPlanDate;
    }

    public Boolean getIsNoDemand() {
        return this.isNoDemand;
    }

    public RecProject isNoDemand(Boolean isNoDemand) {
        this.setIsNoDemand(isNoDemand);
        return this;
    }

    public void setIsNoDemand(Boolean isNoDemand) {
        this.isNoDemand = isNoDemand;
    }

    public Instant getSurveyPlanEndDate() {
        return this.surveyPlanEndDate;
    }

    public RecProject surveyPlanEndDate(Instant surveyPlanEndDate) {
        this.setSurveyPlanEndDate(surveyPlanEndDate);
        return this;
    }

    public void setSurveyPlanEndDate(Instant surveyPlanEndDate) {
        this.surveyPlanEndDate = surveyPlanEndDate;
    }

    public Instant getSurveyPlanDate1() {
        return this.surveyPlanDate1;
    }

    public RecProject surveyPlanDate1(Instant surveyPlanDate1) {
        this.setSurveyPlanDate1(surveyPlanDate1);
        return this;
    }

    public void setSurveyPlanDate1(Instant surveyPlanDate1) {
        this.surveyPlanDate1 = surveyPlanDate1;
    }

    public Instant getSurveyPlanDate2() {
        return this.surveyPlanDate2;
    }

    public RecProject surveyPlanDate2(Instant surveyPlanDate2) {
        this.setSurveyPlanDate2(surveyPlanDate2);
        return this;
    }

    public void setSurveyPlanDate2(Instant surveyPlanDate2) {
        this.surveyPlanDate2 = surveyPlanDate2;
    }

    public Instant getSurveyPlanDate3() {
        return this.surveyPlanDate3;
    }

    public RecProject surveyPlanDate3(Instant surveyPlanDate3) {
        this.setSurveyPlanDate3(surveyPlanDate3);
        return this;
    }

    public void setSurveyPlanDate3(Instant surveyPlanDate3) {
        this.surveyPlanDate3 = surveyPlanDate3;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public RecProject createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public RecProject createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public RecProject updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public RecProject updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecProject)) {
            return false;
        }
        return projectNo != null && projectNo.equals(((RecProject) o).projectNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecProject{" +
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
