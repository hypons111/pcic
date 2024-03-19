package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.BidPrjSur2DTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.Instant;

public class BidPrjSur2Criteria extends Criteria<BidPrjSur2DTO> {
    private String wkut;

    private String prjno;

    private String disNo;

    private BigDecimal amnta;

    private BigDecimal amntb;

    private BigDecimal amnt;

    private Instant createDate;

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

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public BigDecimal getAmnta() {
        return amnta;
    }

    public void setAmnta(BigDecimal amnta) {
        this.amnta = amnta;
    }

    public BigDecimal getAmntb() {
        return amntb;
    }

    public void setAmntb(BigDecimal amntb) {
        this.amntb = amntb;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        BidPrjSur2Criteria that = (BidPrjSur2Criteria) obj;
        return (
                //與傳入值比較
                Objects.equal(wkut, that.wkut)
                        && Objects.equal(prjno, that.prjno)
                        && Objects.equal(disNo, that.disNo)
                        && Objects.equal(amnta, that.amnta)
                        && Objects.equal(amntb, that.amntb)
                        && Objects.equal(amnt, that.amnt)
                        && Objects.equal(createDate, that.createDate)

        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno,disNo,amnt,amnta,amntb,createDate);
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
                .add("disNo", disNo)
                .add("amnt", amnt)
                .add("amnta", amnta)
                .add("amntb", amntb)
                .add("createDate", createDate)
                .toString();
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("wkut")) {
            sortBy = "WKUT";
        }else if  (this.getSortBy()[0].equals("prjno")){
            sortBy = "PRJNO";
        }else if  (this.getSortBy()[0].equals("disNo")){
            sortBy = "DIS_NO";
        }else if  (this.getSortBy()[0].equals("amnt")){
            sortBy = "AMNT";
        }else if  (this.getSortBy()[0].equals("amnta")){
            sortBy = "AMNTA";
        }else if  (this.getSortBy()[0].equals("amntb")){
            sortBy = "AMNTB";
        }else if  (this.getSortBy()[0].equals("createDate")){
            sortBy = "CREATE_DATE";
        }else{
            sortBy = "CREATE_DATE";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidPrjSur2DTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
