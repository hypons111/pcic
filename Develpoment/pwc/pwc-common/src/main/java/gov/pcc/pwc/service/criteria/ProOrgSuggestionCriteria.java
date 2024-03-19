package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.ComStatusSearchDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class ProOrgSuggestionCriteria extends Criteria<ComStatusSearchDTO> {

    private String innovateName;
    private String status;
    private String writeCondition;
    private String name;
    private String orgName;


    public String getInnovateName() {
        return innovateName;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWriteCondition() {
        return writeCondition;
    }

    public void setWriteCondition(String writeCondition) {
        this.writeCondition = writeCondition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        ProOrgSuggestionCriteria that = (ProOrgSuggestionCriteria) o;
        return (
                Objects.equal(innovateName, that.innovateName) &&
                Objects.equal(status, that.status) &&
                Objects.equal(writeCondition, that.writeCondition) &&
                Objects.equal(name, that.name) &&
                Objects.equal(orgName, that.orgName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(innovateName, status, writeCondition, name, orgName);
    }


    @Override
    public Predicate toPredicate(Root<ComStatusSearchDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(innovateName)) {
            predicates.add(criteriaBuilder.like(root.get("innovateName"), WILD_CARD + innovateName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        if (StringUtils.isNotBlank(writeCondition)) {
            predicates.add(criteriaBuilder.equal(root.get("writeCondition"), writeCondition));
        }
        if (StringUtils.isNotBlank(name)) {
            predicates.add(criteriaBuilder.like(root.get("name"), WILD_CARD + name + WILD_CARD));
        }
        if (StringUtils.isNotBlank(orgName)) {
            predicates.add(criteriaBuilder.like(root.get("orgName"), WILD_CARD + orgName + WILD_CARD));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
            if  (this.getSortBy()[0].equals("innovateName")) {
                sortBy = "inn.INNOVATE_NAME";
            } else if (this.getSortBy()[0].equals("status")) {
                sortBy = "org.STATUS";
            } else if (this.getSortBy()[0].equals("writeCondition")) {
                sortBy = "org.WRITE_CONDITION";
            } else if (this.getSortBy()[0].equals("name")) {
                sortBy = "com.NAME";
            } else if (this.getSortBy()[0].equals("orgName")) {
                sortBy = "agt.ORG_NAME";
            } else {
                sortBy = "org.STATUS";
            }
            return sortBy + " " + this.getSortDirection().name();
        }

}

