package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjGdfk;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BidPrjGdfkCriteria extends Criteria<BidPrjGdfk> {

    private String wkut;

    private String prjno;

    private String useType;

    private LocalDate startDate;




    public BidPrjGdfkCriteria(){
    }

    public BidPrjGdfkCriteria(String wkut, String prjno, String useType, LocalDate startDate){
       this.wkut = wkut;
       this.prjno = prjno;
       this.useType = useType;
       this.startDate = startDate;
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

    public String getUseType() {return useType;}

    public void setUseType(String useType) {this.useType = useType;}

    public LocalDate getStartDate() {return startDate;}

    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjGdfkCriteria that = (BidPrjGdfkCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
                        && Objects.equal(useType, that.useType)
                        && Objects.equal(startDate, that.startDate)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno, useType, startDate);
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
                ", syr='" +useType + '\'' +
                ", month='" +startDate + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjGdfk> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
