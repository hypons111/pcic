package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidGovCheckerr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;

public class BidGovCheckerrCriteria extends Criteria<BidGovCheckerr> {

    private String wkut;
    private String prjno;

    private LocalDate checkDate;

    public BidGovCheckerrCriteria() {
        this.sortBy = new String[]{"checkDate"};
    }

    public BidGovCheckerrCriteria(String wkut, String prjno, LocalDate checkDate) {
        this.wkut = wkut;
        this.prjno = prjno;
        this.checkDate = checkDate;
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
        public Predicate toPredicate(Root<BidGovCheckerr> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno),
                criteriaBuilder.equal(root.get("checkDate"), checkDate));
    }

    @Override
    public String toString() {
        return "BidGovCheckerrCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", checkDate='" + checkDate + '\'' +
                '}';
    }
}
