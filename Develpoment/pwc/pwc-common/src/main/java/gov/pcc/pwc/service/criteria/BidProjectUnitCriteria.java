package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidProjectUnit;
import gov.pcc.pwc.service.dto.BidProjectUnitDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class BidProjectUnitCriteria extends Criteria<BidProjectUnit> {
    private String wkut;
    private String prjno;
    private String ccut;
    private String scut;
    private String name;
    private String type;
    private String pcmut;
    private String subccut;

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

    public String getCcut() {
        return ccut;
    }

    public void setCcut(String ccut) {
        this.ccut = ccut;
    }

    public String getScut() {
        return scut;
    }

    public void setScut(String scut) {
        this.scut = scut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPcmut() {
        return pcmut;
    }

    public void setPcmut(String pcmut) {
        this.pcmut = pcmut;
    }

    public String getSubccut() {
        return subccut;
    }

    public void setSubccut(String subccut) {
        this.subccut = subccut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidProjectUnitCriteria that = (BidProjectUnitCriteria) o;
        return (
                Objects.equal(ccut, that.ccut) && Objects.equal(scut, that.scut)&& Objects.equal(name, that.name)&& Objects.equal(type, that.type)
                        && Objects.equal(pcmut, that.pcmut)&& Objects.equal(subccut, that.subccut)&& Objects.equal(wkut, that.wkut)&& Objects.equal(prjno, that.prjno)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(wkut,prjno,ccut,scut, name,type,pcmut,subccut);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("wkut", wkut)
                .add("prjno", prjno)
                .add("ccut", ccut)
                .add("scut", scut)
                .add("name", name)
                .add("type", type)
                .add("pcmut", pcmut)
                .add("subccut", subccut)
                .toString();
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("wkut")) {
            sortBy = "WKUT";
        }else if  (this.getSortBy()[0].equals("prjno")){
            sortBy = "PRJNO";
        } else if  (this.getSortBy()[0].equals("type")){
            sortBy = "TYPE";
        } else if  (this.getSortBy()[0].equals("seq")){
            sortBy = "SEQ";
        }else{
            sortBy = "WKUT";
        }
        return sortBy + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<BidProjectUnit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("wkut"), wkut));
        predicates.add(criteriaBuilder.equal(root.get("prjno"), prjno));
        query.orderBy(criteriaBuilder.desc(root.get("wkut")));

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

    }
}
