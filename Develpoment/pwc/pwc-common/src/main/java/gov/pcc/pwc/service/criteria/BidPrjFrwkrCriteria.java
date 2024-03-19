package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjFrwkr;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BidPrjFrwkrCriteria extends Criteria<BidPrjFrwkr> {

    private String wkut;

    private String prjno;


    public BidPrjFrwkrCriteria(){
    }

    public BidPrjFrwkrCriteria(String wkut, String prjno){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjFrwkrCriteria that = (BidPrjFrwkrCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno);
    }

    @Override
    public String toString() {
        return "BidPrjFrwkrCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjFrwkr> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
            predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
            predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
