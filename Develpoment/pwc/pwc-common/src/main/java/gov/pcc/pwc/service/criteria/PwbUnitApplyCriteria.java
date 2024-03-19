package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PwbUnitApplyCriteria extends Criteria<PwbUnitApplyDTO> {

    private String orgName;

    private String status;

    private String pccOrgId;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Specification<PwbUnitApplyDTO> and(Specification<PwbUnitApplyDTO> other) {
        return super.and(other);
    }

    @Override
    public Specification<PwbUnitApplyDTO> or(Specification<PwbUnitApplyDTO> other) {
        return super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<PwbUnitApplyDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
