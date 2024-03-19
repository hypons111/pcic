package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidGovCheck;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BidGovCheckCriteria extends Criteria<BidGovCheck> {

    private String wkut;

    private String prjno;

    private LocalDate checkDate;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidGovCheckCriteria that = (BidGovCheckCriteria) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(checkDate, that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate);
    }

    @Override
    public Predicate toPredicate(Root<BidGovCheck> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(wkut)) {
            predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
        }
        if (StringUtils.isNotBlank(prjno)) {
            predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
        }
        if (checkDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("checkDate"), checkDate));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("wkut")) {
            sortBy = "wkut";
        } else if (this.getSortBy()[0].equals("prjno")) {
            sortBy = "prjno";
        } else if (this.getSortBy()[0].equals("checkDate")) {
            sortBy = "check_date";
        } else {
            sortBy = "wkut";
        }

        return sortBy + " " + this.getSortDirection().name();
    }
}
