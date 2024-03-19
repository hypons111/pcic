package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import gov.pcc.pwc.domain.EngReviewLog;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class EngReviewLogCriteria implements Specification<EngReviewLog> {
    private String externalId;
    private BigDecimal externalVersion;
    private String reviewCaseType;
    private Sort sort;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getExternalVersion() {
        return externalVersion;
    }

    public void setExternalVersion(BigDecimal externalVersion) {
        this.externalVersion = externalVersion;
    }

    public String getReviewCaseType() {
        return reviewCaseType;
    }

    public void setReviewCaseType(String reviewCaseType) {
        this.reviewCaseType = reviewCaseType;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngReviewLogCriteria that = (EngReviewLogCriteria) o;
        return Objects.equal(externalId, that.externalId) && Objects.equal(externalVersion, that.externalVersion) && Objects.equal(reviewCaseType, that.reviewCaseType) && Objects.equal(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(externalId, externalVersion, reviewCaseType, sort);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("externalId", externalId)
                .add("externalVersion", externalVersion)
                .add("reviewCaseType", reviewCaseType)
                .add("sort", sort)
                .toString();
    }

    @Override
    public Predicate toPredicate(@NotNull Root<EngReviewLog> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("externalId"), externalId));
        predicates.add(criteriaBuilder.equal(root.get("externalVersion"), externalVersion));
        predicates.add(criteriaBuilder.equal(root.get("reviewCaseType"), reviewCaseType));
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
