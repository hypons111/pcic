package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngMember;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class EngMemberCriteria extends Criteria<EngMember> {

    private String type = "0";
    private String chName;
    private String idno;


    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }
    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngMemberCriteria that = (EngMemberCriteria) o;
        return
                Objects.equal(chName, that.chName) &&
                Objects.equal(idno, that.idno);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chName,idno);
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
                .add("chName", chName)
                .add("idno", idno)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<EngMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("type"), type));
        if (StringUtils.isNotBlank(chName)) {
            predicates.add(criteriaBuilder.like(root.get("chName"), WILD_CARD + chName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(idno)) {
            predicates.add(criteriaBuilder.equal(root.get("idno"), idno));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
