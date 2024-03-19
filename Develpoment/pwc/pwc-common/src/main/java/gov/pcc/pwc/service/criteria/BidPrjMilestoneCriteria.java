package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjMilestone;
import gov.pcc.pwc.service.dto.BidPrjMilestoneDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BidPrjMilestoneCriteria extends Criteria<BidPrjMilestone> {
    private String wkut;
    private String prjno;
    private String milestoneNo;
    private LocalDate applyDate;
    private String milestoneName;
    private String milestoneType;
    private String milestoneStatus;

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

    public String getMilestoneNo() {
        return milestoneNo;
    }

    public void setMilestoneNo(String milestoneNo) {
        this.milestoneNo = milestoneNo;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getMilestoneType() {
        return milestoneType;
    }

    public void setMilestoneType(String milestoneType) {
        this.milestoneType = milestoneType;
    }

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        BidPrjMilestoneCriteria that = (BidPrjMilestoneCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(wkut, that.wkut)
                        && Objects.equal(prjno, that.prjno)
                        && Objects.equal(milestoneNo, that.milestoneNo)
        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno,milestoneNo);
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
                .add("milestoneNo", milestoneNo)
                .toString();
    }
    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("wkut")) {
            sortBy = "WKUT";
        }else if  (this.getSortBy()[0].equals("prjno")){
            sortBy = "PRJNO";
        } else if  (this.getSortBy()[0].equals("milestoneNo")){
            sortBy = "MILESTONE_NO";
        } else{
            sortBy = "MILESTONE_NO";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidPrjMilestone> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(wkut)) {
            predicates.add(criteriaBuilder.equal(root.get("wkut"),  wkut));
        }
        if (StringUtils.isNotBlank(prjno)) {
            predicates.add(criteriaBuilder.equal(root.get("prjno"),  prjno));
        }
        if (applyDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("applyDate"),  applyDate));
        }
        if (StringUtils.isNotBlank(milestoneName)) {
            predicates.add(criteriaBuilder.equal(root.get("milestoneName"),  milestoneName));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
