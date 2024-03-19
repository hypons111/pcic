package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.AdmSysParameterDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmSysParameterCriteria extends Criteria<AdmSysParameterDTO> {

    private String sysName;

    private String sysType;

    private String sysValue;

    private String status;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmSysParameterCriteria that = (AdmSysParameterCriteria) o;
        return Objects.equals(sysName, that.sysName) && Objects.equals(sysType, that.sysType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysName, sysType);
    }

    @Override
    public Predicate toPredicate(Root<AdmSysParameterDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(sysName)) {
            predicates.add(criteriaBuilder.like(root.get("sysName"), WILD_CARD + sysName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(sysType)) {
            predicates.add(criteriaBuilder.like(root.get("sysType"), WILD_CARD + sysType + WILD_CARD));
        }
        if (StringUtils.isNotBlank(sysValue)) {
            predicates.add(criteriaBuilder.equal(root.get("sysValue"), sysValue));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("sysName")) {
            sortBy = "sys_name";
        } else if (this.getSortBy()[0].equals("sysType")) {
            sortBy = "sys_type";
        } else if (this.getSortBy()[0].equals("sysValue")) {
            sortBy = "sys_value";
        } else if (this.getSortBy()[0].equals("status")) {
            sortBy = "status";
        } else {
            sortBy = "sys_name";
        }

        return sortBy + " " + this.getSortDirection().name();
    }
}
