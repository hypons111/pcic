package gov.pcc.pwc.service.criteria;


import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.BidProject;
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

public class BidProjectCriteria extends Criteria<BidProject> {

    private String queryType;

    private String queryCase;

    private String prjno;

    private String prjName;

    private String prjName0;

    private String wkut;

    private String add92;

    private String placeCode;

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

    @Override
    public Predicate toPredicate(Root<BidProject> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        LocalDate specificLocaldate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 16);
        switch (this.queryType){
            //關鍵字搜尋
            case "keyWord":
                if(StringUtils.isNotBlank(prjno)){
                    predicates.add(criteriaBuilder.equal(root.get("prjno"),prjno));
                }
                if(StringUtils.isNotBlank(prjName)){
                    predicates.add(criteriaBuilder.like(root.get("name"),WILD_CARD+prjName+WILD_CARD));
                }
                break;
            case "bidAdc001":
                if(StringUtils.isNotBlank(prjName)){
                    predicates.add(criteriaBuilder.like(root.get("name0"),WILD_CARD+prjName0+WILD_CARD));
                }
                if(StringUtils.isNotBlank(wkut)){
                    predicates.add(criteriaBuilder.equal(root.get("wkut"),wkut));
                }
                predicates.add(criteriaBuilder.notEqual(criteriaBuilder.substring(root.get("typeCode"),1,1),"9"));

                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(root.get("aokDate")),
                                criteriaBuilder.greaterThanOrEqualTo(root.get("aokDate"),LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1)),
                                criteriaBuilder.equal(root.get("dsts"),"Z")
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
                ", perPage = " + getPerPage()  + '\'' +
                ", currentPage = " + getCurrentPage()  + '\'' +
                '}';
    }
}
