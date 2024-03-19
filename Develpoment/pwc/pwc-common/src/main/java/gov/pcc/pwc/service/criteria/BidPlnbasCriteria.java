package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidPlnbas;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class BidPlnbasCriteria extends Criteria<BidPlnbas> {

    private String plnno;

    private String name;

    private String wkut;

    private String plnCode;

    private String bos2Code;

    private String bos2bCode;

    private String bos2cCode;

    private String bos2dCode;

    private String isExtend;

    public String getPlnno() {
        return plnno;
    }

    public void setPlnno(String plnno) {
        this.plnno = plnno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getIsExtend() {
        return isExtend;
    }

    public void setIsExtend(String isExtend) {
        this.isExtend = isExtend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPlnbasCriteria that = (BidPlnbasCriteria) o;
        return (
                Objects.equal(plnno, that.plnno) &&
                Objects.equal(name, that.name) &&
                Objects.equal(wkut, that.wkut) &&
                Objects.equal(plnCode, that.plnCode) &&
                Objects.equal(bos2Code, that.bos2Code) &&
                Objects.equal(bos2bCode, that.bos2bCode) &&
                Objects.equal(bos2cCode, that.bos2cCode) &&
                Objects.equal(bos2dCode, that.bos2dCode) &&
                Objects.equal(isExtend, that.isExtend)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plnno, name, wkut, plnCode, bos2Code, bos2bCode, bos2cCode, bos2dCode, isExtend);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("plnno", plnno)
                .add("name", name)
                .add("wkut", wkut)
                .add("plnCode", plnCode)
                .add("bos2Code", bos2Code)
                .add("bos2bCode", bos2bCode)
                .add("bos2cCode", bos2cCode)
                .add("bos2dCode", bos2dCode)
                .add("isExtend", isExtend)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<BidPlnbas> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }

}
