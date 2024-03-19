package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.PwcPengingViewDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PwcPengingViewCriteria extends Criteria<PwcPengingViewDTO> {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Specification<PwcPengingViewDTO> and(Specification<PwcPengingViewDTO> other) {
        return super.and(other);
    }

    @Override
    public Specification<PwcPengingViewDTO> or(Specification<PwcPengingViewDTO> other) {
        return super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<PwcPengingViewDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
