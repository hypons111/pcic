package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmFunctionClickDTO;
import gov.pcc.pwc.service.dto.AdmFunctionUsedRecordDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class AdmFunctionClickCriteria extends Criteria<AdmFunctionClickDTO> {

    private String functionId;

    private String functionName;

    private String functionExtendId;

    private Instant dateStart;

    private Instant dateEnd;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionExtendId() {
        return functionExtendId;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public Instant getDateStart() {
        return dateStart;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public Predicate toPredicate(Root<AdmFunctionClickDTO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(functionId)) {
            predicates.add(criteriaBuilder.like(root.get("functionId"), WILD_CARD + functionId + WILD_CARD));
        }
        if (StringUtils.isNotBlank(functionName)) {
            predicates.add(criteriaBuilder.like(root.get("functionName"), WILD_CARD + functionName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(functionExtendId)) {
            predicates.add(criteriaBuilder.equal(root.get("functionExtendId"), functionExtendId));
        }
        if (dateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateStart"), dateStart));
        }
        if (dateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), dateEnd));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }


    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("functionId")) {
            sortBy = "afc.function_Id";
        } else if (this.getSortBy()[0].equals("functionName")) {
            sortBy = "afc.function_Name";
        } else if (this.getSortBy()[0].equals("functionExtendId")) {
            sortBy = "afc.function_Extend_Id";
        } else if (this.getSortBy()[0].equals("createTime")) {
            sortBy = "afc.create_Time";
        } else {
            sortBy = "afc.create_Time";
        }
        return sortBy + " " + this.getSortDirection().name();
    }

    public String getStatisticsOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("functionId")) {
            sortBy = "afc.function_Id";
        } else if (this.getSortBy()[0].equals("functionName")) {
            sortBy = "af.function_Name";
        } else if (this.getSortBy()[0].equals("functionExtendId")) {
            sortBy = "afc.function_Extend_Id";
        } else {
            sortBy = "afur.function_Id";
        }
        return sortBy + " " + this.getSortDirection().name();
    }

}
