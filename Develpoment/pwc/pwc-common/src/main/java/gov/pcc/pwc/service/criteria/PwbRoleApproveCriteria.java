package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.PwbRoleApproveDTO;
import gov.pcc.pwc.service.dto.PwbRoleDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PwbRoleApproveCriteria extends Criteria<PwbRoleDTO> {

    private String userType;

    private String moduleType;

    private String status;

    private String userId;

    private String chName;

    private String roleType;

    public String getUserType() {return userType;}

    public void setUserType(String userType) {this.userType = userType;}

    public String getModuleType() {return moduleType;}

    public void setModuleType(String moduleType) {this.moduleType = moduleType;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    public String getChName() {return chName;}

    public void setChName(String chName) {this.chName = chName;}

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PwbRoleApproveCriteria that = (PwbRoleApproveCriteria) o;
        return (
                Objects.equals(userType, that.userType) &&
                Objects.equals(moduleType, that.moduleType) &&
                Objects.equals(status, that.status) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(chName, that.chName) &&
                Objects.equals(roleType, that.roleType)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(userType, moduleType, status, userId, chName);
    }

    @Override
    public Predicate toPredicate(Root<PwbRoleDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
