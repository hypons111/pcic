package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.common.enums.CommunicateType;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.ProInnovativeProductProUserDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

//需要查詢的資料
public class ProInnovativeProductProUserCriteria extends Criteria<ProInnovativeProductProUserDTO> {

    private String proInnovativeProductId;

    private String innovateName;

    private String innovateType;

    private String companyName;

    private String statusInnovativeProduct;

    private String statusOrgSuggestion;

    private String writeCondition;

    private String orgName;

    private String userId;




    private CommunicateType communicateType;

    public String getInnovateName() {
        return innovateName;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getInnovateType() {
        return innovateType;
    }

    public void setInnovateType(String innovateType) {
        this.innovateType = innovateType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatusInnovativeProduct() {
        return statusInnovativeProduct;
    }

    public void setStatusInnovativeProduct(String statusInnovativeProduct) {
        this.statusInnovativeProduct = statusInnovativeProduct;
    }

    public String getStatusOrgSuggestion() {
        return statusOrgSuggestion;
    }

    public void setStatusOrgSuggestion(String statusOrgSuggestion) {
        this.statusOrgSuggestion = statusOrgSuggestion;
    }

    public String getWriteCondition() {
        return writeCondition;
    }

    public void setWriteCondition(String writeCondition) {
        this.writeCondition = writeCondition;
    }

    public CommunicateType getCommunicateType() {
        return communicateType;
    }

    public void setCommunicateType(CommunicateType communicateType) {
        this.communicateType = communicateType;
    }

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProInnovativeProductProUserCriteria that = (ProInnovativeProductProUserCriteria) o;
        return Objects.equals(proInnovativeProductId, that.proInnovativeProductId) && Objects.equals(innovateName, that.innovateName) && Objects.equals(innovateType, that.innovateType) && Objects.equals(companyName, that.companyName) && Objects.equals(statusInnovativeProduct, that.statusInnovativeProduct) && Objects.equals(statusOrgSuggestion, that.statusOrgSuggestion) && Objects.equals(writeCondition, that.writeCondition) && Objects.equals(orgName, that.orgName) && Objects.equals(userId, that.userId) && communicateType == that.communicateType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(proInnovativeProductId, innovateName, innovateType, companyName, statusInnovativeProduct, statusOrgSuggestion, writeCondition, orgName, userId, communicateType);
    }

    @Override
    public String toString() {
        return "ProInnovativeProductProUserCriteria{" +
                "proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", innovateName='" + innovateName + '\'' +
                ", innovateType='" + innovateType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", statusInnovativeProduct='" + statusInnovativeProduct + '\'' +
                ", statusOrgSuggestion='" + statusOrgSuggestion + '\'' +
                ", writeCondition='" + writeCondition + '\'' +
                ", orgName='" + orgName + '\'' +
                ", userId='" + userId + '\'' +
                ", communicateType=" + communicateType +
                '}';
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + this.getSortDirection().name();
    }


    @Override
    public Predicate toPredicate(Root<ProInnovativeProductProUserDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(innovateName)) {
            predicates.add(criteriaBuilder.like(root.get("innovateName"), WILD_CARD + innovateName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(companyName)) {
            predicates.add(criteriaBuilder.like(root.get("companyName"), WILD_CARD + companyName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(innovateType)) {
            predicates.add(criteriaBuilder.equal(root.get("innovateType"), innovateType));
        }
        if (StringUtils.isNotBlank(statusInnovativeProduct)) {
            predicates.add(criteriaBuilder.equal(root.get("statusInnovativeProduct"), statusInnovativeProduct));
        }
        if (StringUtils.isNotBlank(statusOrgSuggestion)) {
            predicates.add(criteriaBuilder.equal(root.get("statusOrgSuggestion"), statusOrgSuggestion));
        }
        if (StringUtils.isNotBlank(writeCondition)) {
            predicates.add(criteriaBuilder.equal(root.get("writeCondition"), writeCondition));
        }
        if (Optional.ofNullable(communicateType).isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("communicateType"), communicateType));
        }
        if (Optional.ofNullable(userId).isPresent()) {
            predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
