package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPlnunitb;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class BidPlnunitbCriteria extends Criteria<BidPlnunitb> {

    private String wkut;

    private String plnCode;

    private String bos2Code;

    private String bos2bCode;

    private String bos2cCode;

    private String bos2dCode;

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getBos2Code() {
        return bos2Code;
    }

    public void setBos2Code(String bos2Code) {
        this.bos2Code = bos2Code;
    }

    public String getBos2bCode() {
        return bos2bCode;
    }

    public void setBos2bCode(String bos2bCode) {
        this.bos2bCode = bos2bCode;
    }

    public String getBos2cCode() {
        return bos2cCode;
    }

    public void setBos2cCode(String bos2cCode) {
        this.bos2cCode = bos2cCode;
    }

    public String getBos2dCode() {
        return bos2dCode;
    }

    public void setBos2dCode(String bos2dCode) {
        this.bos2dCode = bos2dCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPlnunitbCriteria that = (BidPlnunitbCriteria) o;
        return (
            Objects.equal(wkut, that.wkut) &&
            Objects.equal(plnCode, that.plnCode) &&
            Objects.equal(bos2Code, that.bos2Code) &&
            Objects.equal(bos2bCode, that.bos2bCode) &&
            Objects.equal(bos2cCode, that.bos2cCode) &&
            Objects.equal(bos2dCode, that.bos2dCode)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(wkut, plnCode, bos2Code, bos2bCode, bos2cCode, bos2dCode);
    }

    @Override
    public String toString() {
        return MoreObjects
            .toStringHelper(this)
            .add("wkut", wkut)
            .add("plnCode", plnCode)
            .add("bos2Code", bos2Code)
            .add("bos2bCode", bos2bCode)
            .add("bos2cCode", bos2cCode)
            .add("bos2dCode", bos2dCode)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<BidPlnunitb> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(wkut)) {
            predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
        }
        if (StringUtils.isNotBlank(plnCode)) {
            predicates.add(criteriaBuilder.equal(root.get("plnCode"), plnCode));
        }
        if (StringUtils.isNotBlank(bos2Code)) {
            predicates.add(criteriaBuilder.equal(root.get("bos2Code"), bos2Code));
        }
        if (StringUtils.isNotBlank(bos2bCode)) {
            predicates.add(criteriaBuilder.equal(root.get("bos2bCode"), bos2bCode));
        }
        if (StringUtils.isNotBlank(bos2cCode)) {
            predicates.add(criteriaBuilder.equal(root.get("bos2cCode"), bos2cCode));
        }
        if (StringUtils.isNotBlank(bos2dCode)) {
            predicates.add(criteriaBuilder.equal(root.get("bos2dCode"), bos2dCode));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
