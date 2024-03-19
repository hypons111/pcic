package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmSysCodeDTO;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmSysCodeCriteria extends Criteria<AdmSysCodeDTO> {

    private String moduleType;

    private String dataType;

    private String dataKey;

    private String isValid;

    private String isModify;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getIsModify() {
        return isModify;
    }

    public void setIsModify(String isModify) {
        this.isModify = isModify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmSysCodeCriteria that = (AdmSysCodeCriteria) o;
        return Objects.equals(moduleType, that.moduleType) && Objects.equals(dataType, that.dataType) && Objects.equals(dataKey, that.dataKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleType, dataType, dataKey);
    }

    @Override
    public Predicate toPredicate(Root<AdmSysCodeDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(moduleType)) {
            predicates.add(criteriaBuilder.equal(root.get("moduleType"), moduleType));
        }
        if (StringUtils.isNotBlank(dataType)) {
            predicates.add(criteriaBuilder.like(root.get("dataType"), WILD_CARD + dataType + WILD_CARD));
        }
        if (StringUtils.isNotBlank(dataKey)) {
            predicates.add(criteriaBuilder.like(root.get("dataKey"), WILD_CARD + dataKey + WILD_CARD));
        }
        if (StringUtils.isNotBlank(isValid)) {
            predicates.add(criteriaBuilder.equal(root.get("isValid"), isValid));
        }
        if (StringUtils.isNotBlank(isModify)) {
            predicates.add(criteriaBuilder.equal(root.get("isModify"), isModify));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("moduleType")) {
            sortBy = "module_type";
        } else if (this.getSortBy()[0].equals("dataType")) {
            sortBy = "data_type";
        } else if (this.getSortBy()[0].equals("dataKey")) {
            sortBy = "data_key";
        } else if (this.getSortBy()[0].equals("isValid")) {
            sortBy = "is_valid";
        } else if (this.getSortBy()[0].equals("isModify")) {
            sortBy = "is_modify";
        } else {
            sortBy = "module_type";
        }

        return sortBy + " " + this.getSortDirection().name();
    }
}
