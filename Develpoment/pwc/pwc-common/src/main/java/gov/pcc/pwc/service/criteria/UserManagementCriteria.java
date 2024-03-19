package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import gov.pcc.pwc.service.dto.UserManagementDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserManagementCriteria extends Criteria<UserManagementDTO> {

    private String userId;
    private String status;
    private String chName;
    private String roleId;
    private String pccOrgId;
    private String compIndo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getCompIndo() {
        return compIndo;
    }

    public void setCompIndo(String compIndo) {
        this.compIndo = compIndo;
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if  (this.getSortBy()[0].equals("userId")) {
            sortBy = "len(USER_ID), USER_ID";
        } else if (this.getSortBy()[0].equals("chName")) {
            sortBy = "CH_NAME";
        } else if (this.getSortBy()[0].equals("status")) {
            sortBy = "STATUS";
        } else {
            sortBy = "ROLE_NAME";
        }
        return sortBy + " " + this.getSortDirection().name();
    }

    @Override
    public Predicate toPredicate(Root<UserManagementDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}

