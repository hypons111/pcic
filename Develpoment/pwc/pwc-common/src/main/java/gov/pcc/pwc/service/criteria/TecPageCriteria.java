package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.TecPage;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TecPageCriteria extends Criteria<TecPage> {





    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<TecPage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
