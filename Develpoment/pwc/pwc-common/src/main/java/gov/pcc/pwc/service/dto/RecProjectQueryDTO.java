package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.Objects;

//查詢時用DTO
public class RecProjectQueryDTO {

    private String typhoonName;

    private String approveNo;

    private String projectCode;

    private String projCodeName;

    private String orgName;

    private String projectName;

    private String city;

    private String village;

    private Double reviewAmount;

    private Double applyAmount;

    private Double bidProgress;

    private Instant scheduledFinishDate;

    private Instant bidDate;

    private Instant actualFinishDate;

    private Instant surveyPlanDate1;

    private Instant surveyPlanDate2;

    private Instant surveyPlanDate3;

    private int rowNum;

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
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

    public String getProjCodeName() {
        return projCodeName;
    }

    public void setProjCodeName(String projCodeName) {
        this.projCodeName = projCodeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Double getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(Double reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public Double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(Double applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Double getBidProgress() {
        return bidProgress;
    }

    public void setBidProgress(Double bidProgress) {
        this.bidProgress = bidProgress;
    }

    public Instant getScheduledFinishDate() {
        return scheduledFinishDate;
    }

    public void setScheduledFinishDate(Instant scheduledFinishDate) {
        this.scheduledFinishDate = scheduledFinishDate;
    }

    public Instant getBidDate() {
        return bidDate;
    }

    public void setBidDate(Instant bidDate) {
        this.bidDate = bidDate;
    }

    public Instant getActualFinishDate() {
        return actualFinishDate;
    }

    public void setActualFinishDate(Instant actualFinishDate) {
        this.actualFinishDate = actualFinishDate;
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
        if (!(o instanceof RecProjectQueryDTO)) {
            return false;
        }
        RecProjectQueryDTO recProjectQueryDTO = (RecProjectQueryDTO) o;
        if (this.typhoonName == null) {
            return false;
        }
        return Objects.equals(this.typhoonName, recProjectQueryDTO.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.typhoonName);
    }

    @Override
    public String toString() {
        return "RecProjectQueryDTO{" +
                "typhoonName='" + getTyphoonName() + "'" +
                ", approveNo='" + getApproveNo() + "'" +
                ", projectCode='" + getProjectCode() + "'" +
                ", projCodeName='" + getProjectName() + "'" +
                ", orgName='" + getOrgName() + "'" +
                ", projectName='" + getProjectName() + "'" +
                ", city='" + getCity() + "'" +
                ", village='" + getVillage() + "'" +
                ", reviewAmount=" + getReviewAmount() + "'" +
                ", applyAmount=" + getApplyAmount() + "'" +
                ", bidProgress=" + getBidProgress() + "'" +
                ", scheduledFinishDate=" + getScheduledFinishDate() + "'" +
                ", bidDate=" + getBidDate() + "'" +
                ", actualFinishDate=" + getActualFinishDate() +
                ", surveyPlanDate1=" + getSurveyPlanDate1() + "'" +
                ", surveyPlanDate2=" + getSurveyPlanDate2() + "'" +
                ", surveyPlanDate3=" + getSurveyPlanDate3() + "'" +
                ", rowNum=" + getRowNum() +
                '}';
    }
}
