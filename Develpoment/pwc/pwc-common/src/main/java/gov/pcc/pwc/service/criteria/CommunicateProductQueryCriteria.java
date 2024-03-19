package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.CommunicateProductQueryDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommunicateProductQueryCriteria extends Criteria<CommunicateProductQueryDTO> {


    private String proInnovativeProductId;

    private String innovateName;

    private String status;

    private String admCompanyName;

    private String orgStatus;

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

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

    public String getAdmCompanyName() {
        return admCompanyName;
    }

    public void setAdmCompanyName(String admCompanyName) {
        this.admCompanyName = admCompanyName;
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<CommunicateProductQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cm that = (Cm) o;
//        return (
//            Objects.equal(type, that.type) &&
//            Objects.equal(name, that.name) &&
//            Objects.equal(chairman, that.chairman) &&
//            Objects.equal(useStatus, that.useStatus)
//        );
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(type, name, chairman, useStatus);
//    }
//
//    @Override
//    public String toString() {
//        return MoreObjects
//            .toStringHelper(this)
//            .add("perPage", perPage)
//            .add("currentPage", currentPage)
//            .add("sortBy", sortBy)
//            .add("sortDirection", sortDirection)
//            .add("type", type)
//            .add("name", name)
//            .add("chairman", chairman)
//            .add("useStatus", useStatus)
//            .toString();
//    }
//
//    @Override
//    public Predicate toPredicate(Root<CmsComp> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        Set<Predicate> predicates = new HashSet<>();
//        if (StringUtils.isNotBlank(name)) {
//            predicates.add(criteriaBuilder.like(root.get("name"), WILD_CARD + name + WILD_CARD));
//        }
//        if (StringUtils.isNotBlank(chairman)) {
//            predicates.add(criteriaBuilder.like(root.get("chairman"), WILD_CARD + chairman + WILD_CARD));
//        }
//        if (StringUtils.isNotBlank(type)) {
//            predicates.add(criteriaBuilder.equal(root.get("type"), type));
//        }
//        if (StringUtils.isNotBlank(useStatus)) {
//            predicates.add(criteriaBuilder.equal(root.get("useStatus"), useStatus));
//        }
//        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
//    }
//
//    @Override
//    public String getOrderBy() {
//        return sortBy[0] + " " + this.getSortDirection().name();
//    }

}
