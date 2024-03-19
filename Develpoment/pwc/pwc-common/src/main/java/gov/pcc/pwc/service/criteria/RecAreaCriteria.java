package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.RecAreaService;
import gov.pcc.pwc.service.dto.RecAreaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RecAreaCriteria extends Criteria<RecAreaDTO> {

    private final Logger log = LoggerFactory.getLogger(RecAreaService.class);

    //宣告傳入參數
    private String projectNo;

    private String areaNo;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    //override equals方法
    @Override
    public boolean equals(Object obj) {
        //比較記憶體位置
        if (this == obj) return true;
        //比較Class
        if (obj == null || getClass() != obj.getClass()) return false;
        RecAreaCriteria that = (RecAreaCriteria) obj;
        return (
                //與傳入值比較
                Objects.equal(projectNo, that.projectNo) && Objects.equal(areaNo, that.areaNo)
        );
    }

    //override hashCode方法
    @Override
    public int hashCode() {
        return Objects.hashCode(projectNo, areaNo);
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
                .add("projectNo", projectNo)
                .add("areaNo", areaNo)
                .toString();
    }



    @Override
    public Predicate toPredicate(Root<RecAreaDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //確認排序功能
    @Override
    public String getOrderBy()  {
        String sortByParam;
        if (this.getSortBy()[0].equals("projectNo")) {
            sortByParam = "PROJECT_NO";
        } else {
            sortByParam = "AREA_NO";
        }
        return sortByParam + " " + this.getSortDirection().name();
    }
}