package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.RecProjectQueryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.List;

public class RecProjectQueryCriteria extends Criteria<RecProjectQueryDTO> {

    private List<String> typhoonName;

    private String typhoonNameSingle;

    private List<String> projectCodes;

    private List<String> applyOrgId;

    private String city;

    private Double bidProgressMin;

    private Double bidProgressMax;

    private String projectName;

    private Double reviewAmountMin;

    private Double reviewAmountMax;

    private String fileName;

    private Instant expiredDate;

    public List<String> getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(List<String> typhoonName) {
        this.typhoonName = typhoonName;
    }

    public List<String> getProjectCodes() {
        return projectCodes;
    }

    public void setProjectCodes(List<String> projectCodes) {
        this.projectCodes = projectCodes;
    }

    public List<String> getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(List<String> applyOrgId) {
        this.applyOrgId = applyOrgId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getBidProgressMin() {
        return bidProgressMin;
    }

    public void setBidProgressMin(Double bidProgressMin) {
        this.bidProgressMin = bidProgressMin;
    }

    public Double getBidProgressMax() {
        return bidProgressMax;
    }

    public void setBidProgressMax(Double bidProgressMax) {
        this.bidProgressMax = bidProgressMax;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getReviewAmountMin() {
        return reviewAmountMin;
    }

    public void setReviewAmountMin(Double reviewAmountMin) {
        this.reviewAmountMin = reviewAmountMin;
    }

    public Double getReviewAmountMax() {
        return reviewAmountMax;
    }

    public void setReviewAmountMax(Double reviewAmountMax) {
        this.reviewAmountMax = reviewAmountMax;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTyphoonNameSingle() {
        return typhoonNameSingle;
    }

    public void setTyphoonNameSingle(String typhoonNameSingle) {
        this.typhoonNameSingle = typhoonNameSingle;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("typhoonName",typhoonName)
                .add("projectCodes",projectCodes)
                .add("applyOrgId",applyOrgId)
                .add("city",city)
                .add("bidProgressMin",bidProgressMin)
                .add("bidProgressMax",bidProgressMax)
                .add("projectName",projectName)
                .add("reviewAmountMin",reviewAmountMin)
                .add("reviewAmountMax",reviewAmountMax)
                .add("fileName",fileName)
                .add("typhoonNameSingle",typhoonNameSingle)
                .add("expiredDate",expiredDate)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<RecProjectQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    @Override
    public String getOrderBy() {
        String sortByParam;
        switch (this.getSortBy()[0]){
            case "typhoonName": sortByParam = "TYPHOON_NAME"; break;
            case "projCodeName": sortByParam = "PROJ_CODE_NAME"; break;
            case "orgName": sortByParam = "ORG_NAME"; break;
            case "projectName": sortByParam = "PROJECT_NAME"; break;
            case "city": sortByParam = "CITY"; break;
            case "village": sortByParam = "VILLAGE"; break;
            case "reviewAmount": sortByParam = "REVIEW_AMOUNT"; break;
            case "applyAmount": sortByParam = "APPLY_AMOUNT"; break;
            case "bidProgress": sortByParam = "BID_PROGRESS"; break;
            case "scheduledFinishDate": sortByParam = "SCHEDULED_FINISH_DATE"; break;
            case "bidDate": sortByParam = "BID_DATE"; break;
            case "actualFinishDate": sortByParam = "ACTUAL_FINISH_DATE"; break;
            default: sortByParam = "APPROVE_NO, PROJECT_CODE"; break;
        }
        return sortByParam + " " + this.getSortDirection().name();
    }
}
