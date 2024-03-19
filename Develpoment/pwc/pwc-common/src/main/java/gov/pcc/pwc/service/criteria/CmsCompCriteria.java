package gov.pcc.pwc.service.criteria;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.CmsComp;

public class CmsCompCriteria extends Criteria<CmsComp> {

    private String type;

    private String name;

    private String chairman;

    private String useStatus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsCompCriteria that = (CmsCompCriteria) o;
        return (
            Objects.equal(type, that.type) &&
            Objects.equal(name, that.name) &&
            Objects.equal(chairman, that.chairman) &&
            Objects.equal(useStatus, that.useStatus)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, name, chairman, useStatus);
    }

    @Override
    public String toString() {
        return MoreObjects
            .toStringHelper(this)
            .add("perPage", perPage)
            .add("currentPage", currentPage)
            .add("sortBy", sortBy)
            .add("sortDirection", sortDirection)
            .add("type", type)
            .add("name", name)
            .add("chairman", chairman)
            .add("useStatus", useStatus)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<CmsComp> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), WILD_CARD + name + WILD_CARD));
        }
        if (StringUtils.isNotBlank(chairman)) {
            predicates.add(criteriaBuilder.like(root.get("chairman"), WILD_CARD + chairman + WILD_CARD));
        }
        if (StringUtils.isNotBlank(type)) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (StringUtils.isNotBlank(useStatus)) {
            predicates.add(criteriaBuilder.equal(root.get("useStatus"), useStatus));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
