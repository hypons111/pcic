package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjSafe;
import gov.pcc.pwc.service.dto.BidPrjSafeDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BidPrjSafeCriteria extends Criteria<BidPrjSafe> {
    private String wkut;
    private String prjno;
    private LocalDate sdate;
    private String prmNO;
    private String disasterScript;
    private LocalDate meDate;
    private LocalDate peDate;

    public BidPrjSafeCriteria() {
    }

    public BidPrjSafeCriteria(String wkut, String prjno) {
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

    public String getPrmNO() {
        return prmNO;
    }

    public void setPrmNO(String prmNO) {
        this.prmNO = prmNO;
    }

    public String getDisasterScript() {
        return disasterScript;
    }

    public void setDisasterScript(String disasterScript) {
        this.disasterScript = disasterScript;
    }

    public LocalDate getMeDate() {
        return meDate;
    }

    public void setMeDate(LocalDate meDate) {
        this.meDate = meDate;
    }

    public LocalDate getPeDate() {
        return peDate;
    }

    public void setPeDate(LocalDate peDate) {
        this.peDate = peDate;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        BidPrjSafeCriteria that = (BidPrjSafeCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(wkut, that.wkut)
                        && Objects.equal(prjno, that.prjno)
                        && Objects.equal(sdate, that.sdate)
                        && Objects.equal(prmNO, that.prmNO)
                        && Objects.equal(disasterScript, that.disasterScript)
                        && Objects.equal(meDate, that.meDate)
                        && Objects.equal(peDate, that.peDate)
        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno,sdate,prmNO,disasterScript,meDate,peDate);
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
                .add("sdate", sdate)
                .add("prmNO",prmNO)
                .add("disasterScript",disasterScript)
                .add("meDate",meDate)
                .add("peDate",peDate)
                .toString();
    }
    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("sdate")) {
            sortBy = "SDATE";
        }else if  (this.getSortBy()[0].equals("peDate")){
            sortBy = "PE_DATE";
        } else if  (this.getSortBy()[0].equals("disasterScript")){
            sortBy = "DISASTER_SCRIPT";
        } else if  (this.getSortBy()[0].equals("meDate")){
            sortBy = "ME_DATE";
        }else{
            sortBy = "SDATE";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidPrjSafe> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut ));
        predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno ));
//        if (StringUtils.isNotBlank(wkut)) {
//            predicates.add(criteriaBuilder.like(root.get("wkut"), WILD_CARD + wkut + WILD_CARD));
//        }
//        if (StringUtils.isNotBlank(prjno)) {
//            predicates.add(criteriaBuilder.like(root.get("prjno"), WILD_CARD + prjno + WILD_CARD));
//        }
//        if (sdate != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sdate"),  sdate));
//        }
//        if (StringUtils.isNotBlank(prmNO)) {
//            predicates.add(criteriaBuilder.like(root.get("prmNO"), WILD_CARD + prmNO + WILD_CARD));
//        }
//        if (StringUtils.isNotBlank(disasterScript)) {
//            predicates.add(criteriaBuilder.like(root.get("prjno"), WILD_CARD + disasterScript + WILD_CARD));
//        }
//        if (meDate != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("meDate"), meDate));
//        }
//        if (peDate != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("peDate"), peDate));
//        }
//        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
