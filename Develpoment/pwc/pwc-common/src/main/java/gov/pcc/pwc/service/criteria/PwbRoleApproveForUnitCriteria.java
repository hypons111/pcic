package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.PwbUnitApplyDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PwbRoleApproveForUnitCriteria extends Criteria<PwbUnitApplyDTO> {

    private String businessType;

    private String moduleType;

    private String status;

    private String unitId;

    private String chName;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getModuleType() {return moduleType;}

    public void setModuleType(String moduleType) {this.moduleType = moduleType;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getChName() {return chName;}

    public void setChName(String chName) {this.chName = chName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PwbRoleApproveForUnitCriteria that = (PwbRoleApproveForUnitCriteria) o;
        return (
                Objects.equals(businessType, that.businessType) &&
                Objects.equals(moduleType, that.moduleType) &&
                Objects.equals(status, that.status) &&
                Objects.equals(unitId, that.unitId) &&
                Objects.equals(chName, that.chName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessType, moduleType, status, unitId, chName);
    }

    @Override
    public Predicate toPredicate(Root<PwbUnitApplyDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(businessType)) {
            predicates.add(criteriaBuilder.equal(root.get("businessType"), businessType));
        }
        if (StringUtils.isNotBlank(moduleType)) {
            predicates.add(criteriaBuilder.equal(root.get("moduleType"), moduleType));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.like(root.get("status"), status));
        }
        if (StringUtils.isNotBlank(unitId.toString())) {
            predicates.add(criteriaBuilder.like(root.get("id"), WILD_CARD + unitId.toString() + WILD_CARD));
        }
        if (StringUtils.isNotBlank(chName)) {
            predicates.add(criteriaBuilder.equal(root.get("chName"), WILD_CARD + chName + WILD_CARD));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
