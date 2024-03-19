package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjStc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class BidPrjStcCriteria extends Criteria<BidPrjStc> {
    private String wkut;
    private String prjno;
    private LocalDate checkDate;

    public BidPrjStcCriteria() {
        this.setSortBy(new String[]{"checkDate"});
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

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno,checkDate);
    }

    //override toString方法，主要是為印出log時用
    @Override
    public String toString() {
        //MoreObjects.toStringHelper方法在add放字串，後面放原本的變數，可以用來把各種型別轉String
        return MoreObjects.toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("wkut", wkut)
                .add("prjno", prjno)
                .add("checkDate", checkDate)
                .toString();
    }
    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("wkut")) {
            sortBy = "WKUT";
        }else if  (this.getSortBy()[0].equals("prjno")){
            sortBy = "PRJNO";
        } else if  (this.getSortBy()[0].equals("disasterScript")){
            sortBy = "DISASTER_SCRIPT";
        } else if  (this.getSortBy()[0].equals("checkDate")){
            sortBy = "CHECK_DATE";
        }else{
            sortBy = "CHECK_DATE";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidPrjStc> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno));
    }
}
