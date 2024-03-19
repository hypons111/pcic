package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import gov.pcc.pwc.service.RecTyphoonService;
import gov.pcc.pwc.service.dto.RecTyphoonQueryDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecTyphoonCriteria extends Criteria<RecTyphoonQueryDTO> {

    private final Logger log = LoggerFactory.getLogger(RecTyphoonService.class);

    //宣告傳入參數
    private String typhoonName;

    private Instant applyStartTimeFrom;

    private Instant applyStartTimeTo;

    private Instant applyEndTimeFrom;

    private Instant applyEndTimeTo;

    private Boolean isLocalOnly;

    private Boolean isEditSubmitData;

    private Boolean isDenyBudgetChange;

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public Instant getApplyStartTimeFrom() {
        return applyStartTimeFrom;
    }

    public void setApplyStartTimeFrom(Instant applyStartTimeFrom) {
        this.applyStartTimeFrom = applyStartTimeFrom;
    }

    public Instant getApplyStartTimeTo() {
        return applyStartTimeTo;
    }

    public void setApplyStartTimeTo(Instant applyStartTimeTo) {
        this.applyStartTimeTo = applyStartTimeTo;
    }

    public Instant getApplyEndTimeFrom() {
        return applyEndTimeFrom;
    }

    public void setApplyEndTimeFrom(Instant applyEndTimeFrom) {
        this.applyEndTimeFrom = applyEndTimeFrom;
    }

    public Instant getApplyEndTimeTo() {
        return applyEndTimeTo;
    }

    public void setApplyEndTimeTo(Instant applyEndTimeTo) {
        this.applyEndTimeTo = applyEndTimeTo;
    }

    public Boolean getIsLocalOnly() {
        return isLocalOnly;
    }

    public void setIsLocalOnly(Boolean isLocalOnly) {
        this.isLocalOnly = isLocalOnly;
    }

    public Boolean getIsEditSubmitData() {
        return isEditSubmitData;
    }

    public void setIsEditSubmitData(Boolean isEditSubmitData) {
        this.isEditSubmitData = isEditSubmitData;
    }

    public Boolean getIsDenyBudgetChange() {
        return isDenyBudgetChange;
    }

    public void setIsDenyBudgetChange(Boolean isDenyBudgetChange) {
        this.isDenyBudgetChange = isDenyBudgetChange;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        RecTyphoonCriteria that = (RecTyphoonCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(typhoonName, that.typhoonName) &&
                        Objects.equal(applyStartTimeFrom, that.applyStartTimeFrom) &&
                        Objects.equal(applyStartTimeTo, that.applyStartTimeTo) &&
                        Objects.equal(applyEndTimeFrom, that.applyEndTimeFrom) &&
                        Objects.equal(applyEndTimeTo, that.applyEndTimeTo) &&
                        Objects.equal(isLocalOnly, that.isLocalOnly) &&
                        Objects.equal(isEditSubmitData, that.isEditSubmitData) &&
                        Objects.equal(isDenyBudgetChange, that.isDenyBudgetChange)
        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(typhoonName, applyStartTimeFrom, applyStartTimeTo, applyEndTimeFrom, applyEndTimeTo, isLocalOnly, isEditSubmitData, isDenyBudgetChange);
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
                .add("typhoonName", typhoonName)
                .add("applyStartTimeFrom", applyStartTimeFrom)
                .add("applyStartTimeTo", applyStartTimeTo)
                .add("applyEndTimeFrom", applyEndTimeFrom)
                .add("applyEndTimeTo", applyEndTimeTo)
                .add("isLocalOnly", isLocalOnly)
                .add("isEditSubmitData", isEditSubmitData)
                .add("isDenyBudgetChange", isDenyBudgetChange)
                .toString();
    }

    //傳入Root定義FROM子句可以出現的類型
//    @Override
//    public Predicate toPredicate(Root<RecTyphoonQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        Set<Predicate> predicates = new HashSet<>();
//        if (StringUtils.isNotBlank(typhoonName)) {
//            predicates.add(criteriaBuilder.like(root.get("typhoonName"), WILD_CARD + typhoonName + WILD_CARD));
//        }
//        //大於applyStartTimeFrom及小於applyStartTimeTo
//        if (applyStartTimeFrom != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyStartTime"), applyStartTimeFrom));
//        }
//        if (applyStartTimeTo != null) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("applyStartTime"), applyStartTimeTo));
//        }
//        if (applyEndTimeFrom != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyEndTime"), applyEndTimeFrom));
//        }
//        if (applyEndTimeTo != null) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("applyEndTime"), applyEndTimeTo));
//        }
//        if (isLocalOnly != null) {
//            predicates.add(criteriaBuilder.equal(root.get("isLocalOnly"), isLocalOnly));
//        }
//        if (isEditSubmitData != null) {
//            predicates.add(criteriaBuilder.equal(root.get("isEditSubmitData"), isEditSubmitData));
//        }
//        if (isDenyBudgetChange != null) {
//            predicates.add(criteriaBuilder.equal(root.get("isDenyBudgetChange"), isDenyBudgetChange));
//        }
//        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

//    }

    @Override
    public Predicate toPredicate(Root<RecTyphoonQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //確認排序功能

    @Override
    public String getOrderBy() {
        String sortByParam;
        if (this.getSortBy()[0].equals("typhoonName")) {
            sortByParam = "TYPHOON_NAME";
        } else if (this.getSortBy()[0].equals("applyStartTime")) {
            sortByParam = "APPLY_START_TIME";
        } else if (this.getSortBy()[0].equals(("applyEndTime"))) {
            sortByParam = "APPLY_END_TIME";
        } else if (this.getSortBy()[0].equals("isLocalOnly")) {
            sortByParam = "IS_LOCAL_ONLY";
        } else if (this.getSortBy()[0].equals("finishDate1000")) {
            sortByParam = "FINISH_DATE1000";
        } else if (this.getSortBy()[0].equals("finishDate5000")) {
            sortByParam = "FINISH_DATE5000";
        } else if (this.getSortBy()[0].equals("isEditSubmitData")) {
            sortByParam = "IS_EDIT_SUBMIT_DATA";
        } else {
            sortByParam = "IS_DENY_BUDGET_CHANGE";
        }
        return sortByParam + " " + this.getSortDirection().name();
    }
}