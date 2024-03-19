package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.AdmBatchServiceDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdmBatchServiceCriteria extends Criteria<AdmBatchServiceDTO> {

    private String batchServiceNo;

    private String batchServiceName;

    private String batchServiceCategory;

    private String isRegular;

    public String getBatchServiceNo() {
        return batchServiceNo;
    }

    public void setBatchServiceNo(String batchServiceNo) {
        this.batchServiceNo = batchServiceNo;
    }

    public String getBatchServiceName() {
        return batchServiceName;
    }

    public void setBatchServiceName(String batchServiceName) {
        this.batchServiceName = batchServiceName;
    }

    public String getBatchServiceCategory() {
        return batchServiceCategory;
    }

    public void setBatchServiceCategory(String batchServiceCategory) {
        this.batchServiceCategory = batchServiceCategory;
    }

    public String getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(String isRegular) {
        this.isRegular = isRegular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmBatchServiceCriteria that = (AdmBatchServiceCriteria) o;
        return Objects.equals(batchServiceNo, that.batchServiceNo) && Objects.equals(batchServiceName, that.batchServiceName) && Objects.equals(batchServiceCategory, that.batchServiceCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchServiceNo, batchServiceName, batchServiceCategory, isRegular);
    }

    @Override
    public String toString() {
        return "AdmBatchServiceCriteria{" +
                "batchServiceNo='" + batchServiceNo + '\'' +
                ", batchServiceName='" + batchServiceName + '\'' +
                ", batchServiceCategory='" + batchServiceCategory + '\'' +
                ", isRegular=" + isRegular +
                '}';
    }

    @Override
    public Predicate toPredicate(Root<AdmBatchServiceDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();

        if (StringUtils.isNotBlank(batchServiceNo)) {
            predicates.add(criteriaBuilder.like(root.get("batchServiceNo"), WILD_CARD + batchServiceNo + WILD_CARD));
        }
        if (StringUtils.isNotBlank(batchServiceName)) {
            predicates.add(criteriaBuilder.like(root.get("batchServiceName"),WILD_CARD + batchServiceName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(batchServiceCategory)) {
            predicates.add(criteriaBuilder.equal(root.get("batchServiceCategory"), batchServiceCategory));
        }
        if (StringUtils.isNotBlank(isRegular)) {
            predicates.add(criteriaBuilder.equal(root.get("isRegular"), isRegular));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        String sortBy;
        if (this.getSortBy()[0].equals("batchServiceNo")) {
            sortBy = "batch_Service_No";
        } else if (this.getSortBy()[0].equals("batchServiceName")) {
            sortBy = "batch_Service_Name";
        } else if (this.getSortBy()[0].equals("batchServiceCategory")) {
            sortBy = "batch_Service_Category";
        } else if (this.getSortBy()[0].equals("isRegular")) {
            sortBy = "is_Regular";
        } else {
            sortBy = "batch_Service_No";
        }

        return sortBy + " " + this.getSortDirection().name();
    }
}
