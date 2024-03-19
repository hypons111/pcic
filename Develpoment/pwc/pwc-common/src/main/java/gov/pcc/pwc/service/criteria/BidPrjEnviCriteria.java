package gov.pcc.pwc.service.criteria;
import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjEnvi;
import gov.pcc.pwc.service.dto.BidPrjEnviDTO;
import org.apache.commons.lang3.StringUtils;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
//import java.util.Objects;
import java.util.Set;

public class BidPrjEnviCriteria extends Criteria<BidPrjEnvi> {

    private String wkut;

    private String prjno;

    private LocalDate sdate;


    public BidPrjEnviCriteria(){
    }

    public BidPrjEnviCriteria(String wkut, String prjno){
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

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjEnviCriteria that = (BidPrjEnviCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
                        && Objects.equal(sdate, that.sdate)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno,sdate);
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
                ", sdate='" +sdate + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjEnvi> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
