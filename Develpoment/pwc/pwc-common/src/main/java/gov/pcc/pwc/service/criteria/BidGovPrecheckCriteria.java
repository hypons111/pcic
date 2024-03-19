package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.domain.BidPrjModify;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class BidGovPrecheckCriteria extends Criteria<BidGovPrecheck> {

    private String wkut;
    private String prjno;

    public BidGovPrecheckCriteria() {
        this.sortBy = new String[]{"checkDate"};
    }

    public BidGovPrecheckCriteria(String wkut, String prjno) {
        this.wkut = wkut;
        this.prjno = prjno;
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
        public Predicate toPredicate(Root<BidGovPrecheck> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno));
    }

    @Override
    public String toString() {
        return "BidPrjModifyCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                '}';
    }
}
