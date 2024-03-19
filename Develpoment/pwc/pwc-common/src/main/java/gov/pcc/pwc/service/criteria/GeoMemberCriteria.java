package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.GeoJob;
import gov.pcc.pwc.domain.GeoMember;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class GeoMemberCriteria extends Criteria<GeoMember> {

    private String keyWord;
    private String jobType;
    private String salaryRange;
    private String workPlace1;
    private String workPlace2;
    private String workPlace3;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlace1() {
        return workPlace1;
    }

    public void setWorkPlace1(String workPlace1) {
        this.workPlace1 = workPlace1;
    }

    public String getWorkPlace2() {
        return workPlace2;
    }

    public void setWorkPlace2(String workPlace2) {
        this.workPlace2 = workPlace2;
    }

    public String getWorkPlace3() {
        return workPlace3;
    }

    public void setWorkPlace3(String workPlace3) {
        this.workPlace3 = workPlace3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoMemberCriteria that = (GeoMemberCriteria) o;
        return
                Objects.equal(keyWord, that.keyWord)&&
                Objects.equal(jobType, that.jobType)&&
                Objects.equal(salaryRange, that.salaryRange)&&
                Objects.equal(workPlace1, that.workPlace1)&&
                Objects.equal(workPlace2, that.workPlace2)&&
                Objects.equal(workPlace3, that.workPlace3);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(keyWord,jobType,salaryRange,workPlace1,workPlace2,workPlace3);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("keyWord", keyWord)
                .add("jobType", jobType)
                .add("salaryRange", salaryRange)
                .add("workPlace1", workPlace1)
                .add("workPlace2", workPlace2)
                .add("workPlace3", workPlace3)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<GeoMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(keyWord)) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("userName"), WILD_CARD + keyWord + WILD_CARD),
                    criteriaBuilder.like(root.get("contactAddr"), WILD_CARD + keyWord + WILD_CARD),
                    criteriaBuilder.like(root.get("jobDemand"), WILD_CARD + keyWord + WILD_CARD),
                    criteriaBuilder.like(root.get("remark"), WILD_CARD + keyWord + WILD_CARD)
            ));
        }
        if (StringUtils.isNotBlank(jobType)) {
        predicates.add(criteriaBuilder.equal(root.get("jobType"), jobType));
        }
        if (StringUtils.isNotBlank(salaryRange)) {
        predicates.add(criteriaBuilder.equal(root.get("salaryRange"), salaryRange));
        }
        if (StringUtils.isNotBlank(workPlace1)) {
        predicates.add(criteriaBuilder.equal(root.get("workPlace"), workPlace1));
        }
        if (StringUtils.isNotBlank(workPlace2)) {
        predicates.add(criteriaBuilder.equal(root.get("workPlace"), workPlace2));
        }
        if (StringUtils.isNotBlank(workPlace3)) {
        predicates.add(criteriaBuilder.equal(root.get("workPlace"), workPlace3));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sortBy[0]) + " " + sortDirection.name();
    }
}
