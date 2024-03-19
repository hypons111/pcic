package gov.pcc.pwc.service.criteria;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;

public class AdmAccountContactCriteria extends Criteria<AdmAccountContactDTO> {

    private String userId;

    private String chName;

    private String chNameRome;

    private String userType;

    private String pccOrgId;

    private String unitId;

    private String compIdno;

    private String status;

    private String auditStatus;

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

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmAccountContactCriteria that = (AdmAccountContactCriteria) o;
        return Objects.equals(userId, that.userId) && Objects.equals(chName, that.chName) && Objects.equals(chNameRome, that.chNameRome) && Objects.equals(userType, that.userType) && Objects.equals(pccOrgId, that.pccOrgId) && Objects.equals(unitId, that.unitId) && Objects.equals(compIdno, that.compIdno) && Objects.equals(status, that.status) && Objects.equals(auditStatus, that.auditStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chName, chNameRome, userType, pccOrgId, unitId, compIdno, status, auditStatus);
    }

    @Override
    public Predicate toPredicate(Root<AdmAccountContactDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(userId)) {
            predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
        }
        if (StringUtils.isNotBlank(chName)) {
            predicates.add(criteriaBuilder.like(root.get("chName"), WILD_CARD + chName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(chNameRome)) {
            predicates.add(criteriaBuilder.like(root.get("chNameRome"), WILD_CARD + chNameRome + WILD_CARD));
        }
        if (StringUtils.isNotBlank(userType)) {
            predicates.add(criteriaBuilder.equal(root.get("userType"), userType));
        }
        if (StringUtils.isNotBlank(pccOrgId)) {
            predicates.add(criteriaBuilder.equal(root.get("pccOrgId"), pccOrgId));
        }
        if (StringUtils.isNotBlank(unitId)) {
            predicates.add(criteriaBuilder.equal(root.get("unitId"), unitId));
        }
        if (StringUtils.isNotBlank(compIdno)) {
            predicates.add(criteriaBuilder.equal(root.get("compIdno"), compIdno));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        if (StringUtils.isNotBlank(auditStatus)) {
            predicates.add(criteriaBuilder.equal(root.get("auditStatus"), auditStatus));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("userId")) {
            sortBy = "a.user_Id";
        } else if (this.getSortBy()[0].equals("chName")) {
            sortBy = "c.ch_Name";
        } else if (this.getSortBy()[0].equals("chNameRome")) {
            sortBy = "c.ch_Name_Rome";
        } else if (this.getSortBy()[0].equals("userType")) {
            sortBy = "a.user_Type";
        } else if (this.getSortBy()[0].equals("pccOrgId")) {
            sortBy = "a.pcc_Org_Id";
        } else if (this.getSortBy()[0].equals("unitId")) {
            sortBy = "a.user_Id";
        } else if (this.getSortBy()[0].equals("compIdno")) {
            sortBy = "a.comp_Idno";
        } else if (this.getSortBy()[0].equals("status")) {
            sortBy = "a.status";
        } else if (this.getSortBy()[0].equals("auditStatus")) {
            sortBy = "a.audit_Status";
        } else {
            sortBy = "a.user_Id";
        }

        return sortBy + " " + this.getSortDirection().name();
    }
}
