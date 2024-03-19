package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPrjRatecf;
import gov.pcc.pwc.domain.BidPrjWksorc;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BidPrjRatecfCriteria extends Criteria<BidPrjRatecf> {

    private String wkut;

    private String prjno;

    private String syr;

    private String month;

    private String chkunit;

    private String mft;


    public BidPrjRatecfCriteria(){
    }

    public BidPrjRatecfCriteria(String wkut, String prjno, String syr, String month, String chkunit, String mft){
       this.wkut = wkut;
       this.prjno = prjno;
       this.syr = syr;
       this.month = month;
       this.chkunit = chkunit;
       this.mft = mft;
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

    public String getChkunit() {return chkunit;}

    public void setChkunit(String chkunit) {this.chkunit = chkunit;}

    public String getMft() {return mft;}

    public void setMft(String mft) {this.mft = mft;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjRatecfCriteria that = (BidPrjRatecfCriteria) o;
        return (
                Objects.equal(wkut, that.wkut) &&
                        Objects.equal(prjno, that.prjno)
                        && Objects.equal(syr, that.syr)
                        && Objects.equal(month, that.month)
                        && Objects.equal(chkunit, that.chkunit)
                        && Objects.equal(mft, that.mft)
        );
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, prjno, syr, month, chkunit, mft);
    }

    @Override
    public String toString() {
        return "BidPrjRatecfCriteria{" +
                "perPage=" + perPage +
                ", currentPage=" + currentPage +
                ", sortBy=" + Arrays.toString(sortBy) +
                ", sortDirection=" + sortDirection +
                ", wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", syr='" +syr + '\'' +
                ", month='" +month + '\'' +
                ", chkunit='" +chkunit + '\'' +
                ", mft='" +mft + '\'' +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<BidPrjRatecf> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
            predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
            predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
//            predicates.add(criteriaBuilder.equal(root.get("agreeDate"), agreeDate));
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        List<String> list = Arrays.stream(sortBy).map(s -> CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, s) + " " + sortDirection.name()).collect(Collectors.toList());
        return StringUtils.join(list, ", ");
    }

}
