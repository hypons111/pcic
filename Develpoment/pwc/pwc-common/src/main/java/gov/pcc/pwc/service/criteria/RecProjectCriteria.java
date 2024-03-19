package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.RecProjectService;
import gov.pcc.pwc.service.dto.RecProjectDTO;
import gov.pcc.pwc.service.dto.RecProjectQueryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.Instant;

public class RecProjectCriteria extends Criteria<RecProjectDTO> {

    private final Logger log = LoggerFactory.getLogger(RecProjectService.class);

    //宣告傳入參數
    private String orgId;

    private String typhoonNo;

    private Instant applyEndTime;

    private String projectCode;

    private String projectName;

    private BigDecimal applyAmount;

    private String projectStatus;

    private String applyOrgId;

    private String city;

    private Boolean isReviewChangeAmount;

    private Boolean isSurveyPlan;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTyphoonNo() {
        return typhoonNo;
    }

    public void setTyphoonNo(String typhoonNo) {
        this.typhoonNo = typhoonNo;
    }

    public Instant getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Instant applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsReviewChangeAmount() {
        return isReviewChangeAmount;
    }

    public void setIsReviewChangeAmount(Boolean isReviewChangeAmount) {
        this.isReviewChangeAmount = isReviewChangeAmount;
    }

    public Boolean getIsSurveyPlan() {
        return isSurveyPlan;
    }

    public void setIsSurveyPlan(Boolean isSurveyPlan) {
        this.isSurveyPlan = isSurveyPlan;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        RecProjectCriteria that = (RecProjectCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(orgId, that.orgId) &&
                        Objects.equal(typhoonNo, that.typhoonNo) &&
                        Objects.equal(applyEndTime, that.applyEndTime) &&
                        Objects.equal(projectCode, that.projectCode) &&
                        Objects.equal(projectName, that.projectName) &&
                        Objects.equal(projectStatus, that.projectStatus) &&
                        Objects.equal(applyAmount, that.applyAmount) &&
                        Objects.equal(applyOrgId, that.applyOrgId) &&
                        Objects.equal(city, that.city) &&
                        Objects.equal(isReviewChangeAmount, that.isReviewChangeAmount) &&
                        Objects.equal(isSurveyPlan, that.isSurveyPlan)

        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(orgId, typhoonNo, applyEndTime, projectCode, projectName, projectStatus, applyAmount, applyOrgId, city, isReviewChangeAmount, isSurveyPlan);
    }

    //override toString方法，主要是為印出log時用
    @Override
    public String toString() {
        //MoreObjects.toStringHelper方法在add放字串，後面放原本的變數，可以用來把各種型別轉String
        return MoreObjects.toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("orgId", orgId)
                .add("typhoonNo", typhoonNo)
                .add("applyEndTime", applyEndTime)
                .add("projectCode", projectCode)
                .add("projectName", projectName)
                .add("projectStatus", projectStatus)
                .add("applyAmount", applyAmount)
                .add("applyOrgId", applyOrgId)
                .add("city", city)
                .add("isReviewChangeAmount", isReviewChangeAmount)
                .add("isSurveyPlan", isSurveyPlan)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<RecProjectDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //確認排序功能

    @Override
    public String getOrderBy() {
        String sortByParam;
        if (this.getSortBy()[0].equals("projectCode")) {
            sortByParam = "PROJECT_CODE";
        } else if (this.getSortBy()[0].equals("projectName")) {
            sortByParam = "PROJECT_NAME";
        } else if (this.getSortBy()[0].equals("projectStatus")) {
            sortByParam = "PROJECT_STATUS";
        } else if (this.getSortBy()[0].equals("applyAmount")) {
            sortByParam = "APPLY_AMOUNT";
        } else if (this.getSortBy()[0].equals("applyOrgId")) {
            sortByParam = "APPLY_ORG_ID";
        } else if (this.getSortBy()[0].equals("isReviewChangeAmount")) {
            sortByParam = "IS_REVIEW_CHANGE_AMOUNT";
        } else {
            sortByParam = "IS_SURVEY_PLAN";
        }
        return sortByParam + " " + this.getSortDirection().name();
    }
}