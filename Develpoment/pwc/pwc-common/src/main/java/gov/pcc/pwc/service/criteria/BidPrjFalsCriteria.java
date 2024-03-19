package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjFals;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BidPrjFalsCriteria extends Criteria<BidPrjFals> {
    private String wkut;
    private String prjno;

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

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        BidPrjFalsCriteria that = (BidPrjFalsCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(wkut, that.wkut)
                        && Objects.equal(prjno, that.prjno)

        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno);
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
                .toString();
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("wkut")) {
            sortBy = "WKUT";
        }else if  (this.getSortBy()[0].equals("prjno")){
            sortBy = "PRJNO";
        } else if  (this.getSortBy()[0].equals("syr")){
            sortBy = "SYR";
        } else if  (this.getSortBy()[0].equals("month")){
            sortBy = "YEAR";
        }else{
            sortBy = "MILESTONE_NO";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidPrjFals> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("wkut"), wkut),
                criteriaBuilder.equal(root.get("prjno"), prjno));
    }
}
