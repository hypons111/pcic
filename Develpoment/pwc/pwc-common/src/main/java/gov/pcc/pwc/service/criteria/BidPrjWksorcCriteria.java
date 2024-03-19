package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjSegmset;
import gov.pcc.pwc.domain.BidPrjWksorc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BidPrjWksorcCriteria extends Criteria<BidPrjWksorc> {

    private String wkut;

    private String prjno;

    private String syr;

    private String month;


    public BidPrjWksorcCriteria(){
    }

    public BidPrjWksorcCriteria(String wkut, String prjno, String syr, String month){
       this.wkut = wkut;
       this.prjno = prjno;
       this.syr = syr;
       this.month = month;
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

    public String getSyr() {return syr;}

    public void setSyr(String syr) {this.syr = syr;}

    public String getMonth() {return month;}

    public void setMonth(String month) {this.month = month;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjWksorcCriteria that = (BidPrjWksorcCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
                        && Objects.equal(syr, that.syr)
                        && Objects.equal(month, that.month)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno, syr, month);
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
                ", syr='" +syr + '\'' +
                ", month='" +month + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjWksorc> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
