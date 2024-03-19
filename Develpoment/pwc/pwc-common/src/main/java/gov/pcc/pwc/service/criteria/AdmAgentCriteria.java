package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmAgentDTO;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmAgentCriteria extends Criteria<AdmAgentDTO> {

    private String orgId;

    private String pccOrgId;

    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmAgentCriteria that = (AdmAgentCriteria) o;
        return orgId.equals(that.orgId) && Objects.equals(pccOrgId, that.pccOrgId) && Objects.equals(orgName, that.orgName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId, pccOrgId, orgName);
    }

    @Override
    public Predicate toPredicate(Root<AdmAgentDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(orgId)) {
            predicates.add(criteriaBuilder.equal(root.get("orgId"), orgId));
        }
        if (StringUtils.isNotBlank(pccOrgId)) {
            predicates.add(criteriaBuilder.equal(root.get("pccOrgId"), pccOrgId));
        }
        if (StringUtils.isNotBlank(orgName)) {
            predicates.add(criteriaBuilder.equal(root.get("orgName"), orgName));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("orgId")) {
            sortBy = "org_Id";
        } else if (this.getSortBy()[0].equals("orgName")) {
            sortBy = "org_Name";
        } else if (this.getSortBy()[0].equals("pccOrgId")) {
            sortBy = "pcc_Org_Id";
        } else if (this.getSortBy()[0].equals("adminOrgId")) {
            sortBy = "admin_Org_Id";
        } else if (this.getSortBy()[0].equals("orgLevel")) {
            sortBy = "org_Level";
        } else if (this.getSortBy()[0].equals("cutMark")) {
            sortBy = "cut_Mark";
        } else {
            sortBy = "pcc_Org_Id";
        }
        return sortBy + " " + this.getSortDirection().name();
    }
}
