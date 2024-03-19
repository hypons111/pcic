package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjModify;
import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class BidPrjSubcoCriteria extends Criteria<BidPrjSubco> {

    private String wkut;
    private String prjno;

    public BidPrjSubcoCriteria() {
        this.sortBy = new String[]{"subcoNo"};
    }

    public BidPrjSubcoCriteria(String wkut, String prjno) {
        this.wkut = wkut;
        this.prjno = prjno;
        setDefaultSorBy();
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

    private void setDefaultSorBy(){
        this.sortBy = new String[]{"subcoNo"};
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<BidPrjSubco> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno));
    }

    @Override
    public String toString() {
        return "BidPrjSubcoCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                '}';
    }
}
