package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.PwbRoleModuleDTO;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PwbRoleModuleCriteria extends Criteria<PwbRoleModuleDTO> {

    private String moduleType;

    private String businessType;

    private String roleId;

    private String roleType;

    private String approveLevel;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<PwbRoleModuleDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
