package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.RecUserProjectCodeQueryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RecUserProjectCodeCriteria extends Criteria<RecUserProjectCodeQueryDTO> {

    private String userId;

    private String chName;

    private String orgName;

    private String roleName;

    private String status;

    private String loginId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("userId",userId)
                .add("chName",chName)
                .add("roleName",roleName)
                .add("orgName",orgName)
                .add("status",status)
                .add("loginId",loginId)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<RecUserProjectCodeQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    //前端排序時用
    @Override
    public String getOrderBy() {
        String sortByParam;
        switch (this.getSortBy()[0]){
            case "chName": sortByParam = "CH_NAME"; break;
            case "orgName": sortByParam = "ORG_NAME"; break;
            case "workTel": sortByParam = "WORK_TEL"; break;
            case "email": sortByParam = "EMAIL"; break;
            case "roleName": sortByParam = "ROLE_NAME"; break;
            case "status": sortByParam = "STATUS"; break;
            default:  sortByParam = "USER_ID"; break;
        }
        return sortByParam + " "+this.getSortDirection().name();
    }
}
