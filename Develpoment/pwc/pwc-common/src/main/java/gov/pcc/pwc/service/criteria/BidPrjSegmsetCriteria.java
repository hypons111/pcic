package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.domain.BidPrjSegmset;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BidPrjSegmsetCriteria extends Criteria<BidPrjSegmset> {

    private String wkut;

    private String prjno;

    private LocalDate agreeDate;


    public BidPrjSegmsetCriteria(){
    }

    public BidPrjSegmsetCriteria(String wkut, String prjno, LocalDate agreeDate){
       this.wkut = wkut;
       this.prjno = prjno;
       this.agreeDate = agreeDate;
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

    public LocalDate getAgreeDate() {return agreeDate;}

    public void setAgreeDate(LocalDate agreeDate) {this.agreeDate = agreeDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjSegmsetCriteria that = (BidPrjSegmsetCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
                        && Objects.equal(agreeDate, that.agreeDate)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno, agreeDate);
    }

    @Override
    public String toString() {
        return "BidPrjEnviCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", sdate='" +agreeDate + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjSegmset> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
            predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
            predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
//            predicates.add(criteriaBuilder.equal(root.get("agreeDate"), agreeDate));
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
