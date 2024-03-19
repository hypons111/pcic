package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.VwBidProject;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

//參考用 目前沒用到
public class VwBidProjectForLoginCriteria extends Criteria<VwBidProject> {

    private String prjno;

    private String wkut;

    private String prjName;

    private String typeCodeFirstNotEqual;

    private String typeCodeFirstEqual;

    private Float bdgtUpperBound;

    private Float bdgtLowerBound;

    private Boolean aokAeDateDefault;

    private LocalDate aokDateUpperBound;

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getTypeCodeFirstNotEqual() {
        return typeCodeFirstNotEqual;
    }

    public void setTypeCodeFirstNotEqual(String typeCodeFirstNotEqual) {
        this.typeCodeFirstNotEqual = typeCodeFirstNotEqual;
    }

    public Float getBdgtUpperBound() {
        return bdgtUpperBound;
    }

    public void setBdgtUpperBound(Float bdgtUpperBound) {
        this.bdgtUpperBound = bdgtUpperBound;
    }

    public Float getBdgtLowerBound() {
        return bdgtLowerBound;
    }

    public void setBdgtLowerBound(Float bdgtLowerBound) {
        this.bdgtLowerBound = bdgtLowerBound;
    }

    public Boolean getAokAeDateDefault() {
        return aokAeDateDefault;
    }

    public void setAokAeDateDefault(Boolean aokAeDateDefault) {
        this.aokAeDateDefault = aokAeDateDefault;
    }

    public LocalDate getAokDateUpperBound() {
        return aokDateUpperBound;
    }

    public void setAokDateUpperBound(LocalDate aokDateUpperBound) {
        this.aokDateUpperBound = aokDateUpperBound;
    }

    public String getTypeCodeFirsEqual() {
        return typeCodeFirstEqual;
    }

    public void setTypeCodeFirsEqual(String typeCodeFirsEqual) {
        this.typeCodeFirstEqual = typeCodeFirsEqual;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<VwBidProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        LocalDate specificLocaldate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 16);
        if(StringUtils.isNotBlank(wkut)){
            predicates.add(criteriaBuilder.like(root.get("wkut"),wkut+WILD_CARD));
        }

        if(StringUtils.isNotBlank(prjno)){
            predicates.add(criteriaBuilder.equal(root.get("prjno"),WILD_CARD+prjName+WILD_CARD));
        }
        if(StringUtils.isNotBlank(prjName)){
            predicates.add(criteriaBuilder.like(root.get("projectName"),WILD_CARD+prjName+WILD_CARD));
        }

        if(StringUtils.isNotBlank(typeCodeFirstNotEqual)){
            predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
        }

        if(StringUtils.isNotBlank(typeCodeFirstEqual)){
            predicates.add(criteriaBuilder.equal(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
        }

        if(bdgtUpperBound !=null && bdgtUpperBound >0 ){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("bdgt1"), bdgtUpperBound));
        }

        if(bdgtLowerBound !=null && bdgtLowerBound >0 ){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("bdgt1"), bdgtLowerBound));
        }

        if(aokAeDateDefault){
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.isNull(root.get("aokDate")),
                            criteriaBuilder.isNull(root.get("aeDate")),
                            criteriaBuilder.greaterThan(root.get("aokDate"),specificLocaldate)));
        }

        if(aokDateUpperBound !=null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("aokDate"), aokDateUpperBound));
        }


        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
