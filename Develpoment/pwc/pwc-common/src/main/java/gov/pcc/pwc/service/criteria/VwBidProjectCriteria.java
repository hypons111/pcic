package gov.pcc.pwc.service.criteria;


import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.VwBidProject;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class VwBidProjectCriteria extends Criteria<VwBidProject> {

    private String queryType;

    private String queryCase;

    private String prjno;

    private String prjName;

    private String wkut;

    private String add92;

    private String placeCode;

    private String specificUnitName;

    private String typeCodeFirstNotEqual;

    private String typeCodeFirstEqual;

    private Float bdgtUpperBound;

    private Float bdgtLowerBound;

    private String aokAeDateCase;
    /**
     * bothNull   => aok 和 ae date都要為空
     * 0116       => aok = null or ae =null or aok > year +0116
     * aeThisYear => aok = null and ae >this year 0101
     */

    private LocalDate aokDateUpperBound;

    public String getQueryType() { return queryType; }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getQueryCase() { return queryCase; }

    public void setQueryCase(String queryCase) { this.queryCase = queryCase; }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getAdd92() {
        return add92;
    }

    public void setAdd92(String add92) {
        this.add92 = add92;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getSpecificUnitName() {
        return specificUnitName;
    }

    public void setSpecificUnitName(String specificUnitName) {
        this.specificUnitName = specificUnitName;
    }

    public String getTypeCodeFirstNotEqual() {
        return typeCodeFirstNotEqual;
    }

    public void setTypeCodeFirstNotEqual(String typeCodeFirstNotEqual) {
        this.typeCodeFirstNotEqual = typeCodeFirstNotEqual;
    }

    public String getTypeCodeFirstEqual() {
        return typeCodeFirstEqual;
    }

    public void setTypeCodeFirstEqual(String typeCodeFirstEqual) {
        this.typeCodeFirstEqual = typeCodeFirstEqual;
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

    public String getAokAeDateCase() {
        return aokAeDateCase;
    }

    public void setAokAeDateCase(String aokAeDateCase) {
        this.aokAeDateCase = aokAeDateCase;
    }

    public LocalDate getAokDateUpperBound() {
        return aokDateUpperBound;
    }

    public void setAokDateUpperBound(LocalDate aokDateUpperBound) {
        this.aokDateUpperBound = aokDateUpperBound;
    }

    @Override
    public Predicate toPredicate(Root<VwBidProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        LocalDate specificLocaldate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 16);
        LocalDate firstDateOfThisYear = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1);
        switch (this.queryType){
            //關鍵字搜尋
            case "keyWord":
                if(StringUtils.isNotBlank(prjno)){
                    predicates.add(criteriaBuilder.equal(root.get("prjno"),prjno));
                }
                if(StringUtils.isNotBlank(prjName)){
                    predicates.add(criteriaBuilder.like(root.get("projectName"),WILD_CARD+prjName+WILD_CARD));
                }
                break;
            //查詢方式+機關
            case "wkut":
                if(StringUtils.isNotBlank(wkut)){
                    predicates.add(criteriaBuilder.like(root.get("wkut"),wkut+WILD_CARD));
                }

                // AND ( SUBSTRING(type_code, 1, 1) != '9')
                predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
                //  驗收合格日期為空值或驗收合格日期>=當年1月16日

                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(root.get("aokDate")),
                                criteriaBuilder.greaterThan(root.get("aokDate"),specificLocaldate)));

                switch (this.queryCase){
                    case "normal":
                        predicates.add(criteriaBuilder.isNotNull(root.get("bdgt1")));
                        predicates.add(criteriaBuilder.greaterThan(root.get("bdgt1"),1000));
                        break;
                    case "GPMnet":
                        predicates.add(criteriaBuilder.isNotNull(root.get("plnUnicod")));
                        break;
                    case "union":
                        predicates.add(criteriaBuilder.equal(root.get("isUnion"),"Y"));
                        break;
                    case "pcm":
                        predicates.add(criteriaBuilder.isNotNull(root.get("pcmut")));
                        break;
                    //最有利標
                    case "favorable":
                        predicates.add(
                                criteriaBuilder.or(
                                        criteriaBuilder.equal(root.get("mctmth"),"3"),
                                        criteriaBuilder.equal(root.get("mctmth"),"9")));
                    break;
                }
                break;
            //專案類別+執行單位
            case "prjType":
                if(StringUtils.isNotBlank(wkut)){
                    predicates.add(criteriaBuilder.like(root.get("wkut"),wkut+WILD_CARD));
                }
                //AND  ISNULL(IS_ADD92, '9U') != '9U'
                //AND IS_ADD92= {選定之專案項目}
                predicates.add(criteriaBuilder.notEqual(root.get("isAdd92"),"9U"));
                predicates.add(criteriaBuilder.equal(root.get("isAdd92"),add92));
                // AND ( SUBSTRING(type_code, 1, 1) != '9')
                predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
                //  驗收合格日期為空值或驗收合格日期>=當年1月16日
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(root.get("aokDate")),
                                criteriaBuilder.greaterThan(root.get("aokDate"),specificLocaldate)));
                break;
            //關鍵字搜尋
            case "placeCode":
                if(StringUtils.isNotBlank(placeCode)){
                    predicates.add(criteriaBuilder.like(root.get("placeCode"), placeCode + WILD_CARD));
                }
                // 工程類別第一碼不為9
                predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
                // 發包預算>=100萬
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("bdgt1"),1000));
                // 決標金額
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ctSum"),500));
                // 驗收合格日期為空值或驗收合格日期>=今年
                predicates.add(
                        criteriaBuilder.or(
                            criteriaBuilder.isNull(root.get("aokDate")),
                            criteriaBuilder.greaterThanOrEqualTo(root.get("aokDate"),specificLocaldate)));
                break;
            case "projectLogin":
                if(StringUtils.isNotBlank(wkut)){
                    predicates.add(criteriaBuilder.like(root.get("wkut"),wkut+WILD_CARD));
                }

                if(StringUtils.isNotBlank(prjno)){
                    predicates.add(criteriaBuilder.like(root.get("prjno"),WILD_CARD+prjno+WILD_CARD));
                }
                if(StringUtils.isNotBlank(prjName)){
                    predicates.add(criteriaBuilder.like(root.get("projectName"),WILD_CARD+prjName+WILD_CARD));
                }

                if(StringUtils.isNotBlank(typeCodeFirstEqual)){
                    predicates.add(criteriaBuilder.equal(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
                }

                if(StringUtils.isNotBlank(typeCodeFirstNotEqual)){
                    predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));
                }
                if(bdgtUpperBound !=null && bdgtUpperBound >0 ){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("bdgt1"), bdgtUpperBound));
                }

                if(bdgtLowerBound !=null && bdgtLowerBound >0 ){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("bdgt1"), bdgtLowerBound));
                }

                if("0116".equals(aokAeDateCase)){
                    predicates.add(
                            criteriaBuilder.or(
                                    criteriaBuilder.isNull(root.get("aokDate")),
                                    criteriaBuilder.isNull(root.get("aeDate")),
                                    criteriaBuilder.greaterThan(root.get("aokDate"),specificLocaldate)));
                }

                if("bothNull".equals(aokAeDateCase)){
                    predicates.add(
                            criteriaBuilder.and(
                                    criteriaBuilder.isNull(root.get("aokDate")),
                                    criteriaBuilder.isNotNull(root.get("aeDate"))
                            )
                    );
                 }

                if("aeThisYear".equals(aokAeDateCase)){
                    predicates.add(
                            criteriaBuilder.and(
                                    criteriaBuilder.isNull(root.get("aokDate")),
                                    criteriaBuilder.greaterThan(root.get("aeDate"),firstDateOfThisYear)));
                }

                if(aokDateUpperBound !=null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("aokDate"), aokDateUpperBound));
                }
                break;
            case "bidAdc001":
                if(StringUtils.isNotBlank(prjName)){
                    predicates.add(criteriaBuilder.like(root.get("projectName"),WILD_CARD+prjName+WILD_CARD));
                }
                if(StringUtils.isNotBlank(wkut)){
                    predicates.add(criteriaBuilder.equal(root.get("wkut"),wkut));
                }
                predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));

                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(root.get("aokDate")),
                                criteriaBuilder.greaterThanOrEqualTo(root.get("aokDate"),LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1))
                        ));

                if("pullDownMenu".equals(queryCase)){
                    predicates.add(criteriaBuilder.isNotNull(root.get("ctSum")));
                }

                break;
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public String toString() {
        return "VwBidProjectCriteria{" +
                "queryType='" + queryType + '\'' +
                ", queryCase='" + queryCase + '\'' +
                ", prjno='" + prjno + '\'' +
                ", prjName='" + prjName + '\'' +
                ", wkut='" + wkut + '\'' +
                ", add92='" + add92 + '\'' +
                ", specificUnitName='" + specificUnitName + '\'' +
                ", typeCodeFirstNotEqual='" + typeCodeFirstNotEqual + '\'' +
                ", typeCodeFirstEqual='" + typeCodeFirstEqual + '\'' +
                ", bdgtUpperBound='" + bdgtUpperBound + '\'' +
                ", bdgtLowerBound='" + bdgtLowerBound + '\'' +
                ", aokAeDateCase='" + aokAeDateCase + '\'' +
                ", aokDateUpperBound='" + aokDateUpperBound + '\'' +
                ", perPage = " + getPerPage()  + '\'' +
                ", currentPage = " + getCurrentPage()  + '\'' +
                '}';
    }
}
