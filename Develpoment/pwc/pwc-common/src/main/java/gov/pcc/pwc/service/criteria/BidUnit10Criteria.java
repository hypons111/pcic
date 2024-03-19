package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidUnit10;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class BidUnit10Criteria extends Criteria<BidUnit10> {

    private String code;

    private String name;

    private String type;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidUnit10Criteria that = (BidUnit10Criteria) o;
        return (
            Objects.equal(code, that.code) &&
            Objects.equal(name, that.name)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code, name);
    }

    @Override
    public String toString() {
        return MoreObjects
            .toStringHelper(this)
            .add("perPage", perPage)
            .add("currentPage", currentPage)
            .add("sortBy", sortBy)
            .add("sortDirection", sortDirection)
            .add("code", code)
            .add("name", name)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<BidUnit10> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(code)) {
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
        if (StringUtils.isNotBlank(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), WILD_CARD + name + WILD_CARD));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
