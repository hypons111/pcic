package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjMonth;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BidPrjMonthCriteria extends Criteria<BidPrjMonth> {
    private String wkut;
    private String prjno;


    public BidPrjMonthCriteria() {
    }

    public BidPrjMonthCriteria(String wkut, String prjno) {
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
    public Predicate toPredicate(Root<BidPrjMonth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno));
    }

    @Override
    public String toString() {
        return "BidPrjMonthCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                '}';
    }
}
