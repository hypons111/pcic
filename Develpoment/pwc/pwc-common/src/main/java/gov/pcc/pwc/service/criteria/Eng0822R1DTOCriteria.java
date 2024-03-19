package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.Eng0822R1DTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Eng0822R1DTOCriteria extends Criteria<Eng0822R1DTO> {

    private String bosName; // 前端的主管機關的變數名稱

    private String type;  // 公會類型

    private String subject; // 科別

    private String chName; // 公會名稱

    private String masterName; // 理事長

    private String status;  // 狀態

    public String getBosName() {
        return bosName;
    }

    public void setBosName(String bosName) {
        this.bosName = bosName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
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
        Eng0822R1DTOCriteria that = (Eng0822R1DTOCriteria) o;
        return Objects.equals(bosName, that.bosName) && Objects.equals(type, that.type) &&
                Objects.equals(subject, that.subject) && Objects.equals(chName, that.chName) &&
                Objects.equals(masterName, that.masterName) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bosName, type, subject, chName, masterName, status);
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<Eng0822R1DTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(bosName)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                root.get("chName"), WILD_CARD + bosName + WILD_CARD
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(type)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("type"), type
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(subject)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.like(
                    root.get("subjectList"), WILD_CARD + subject + WILD_CARD
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(chName)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("chName"), chName
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(masterName)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("masterName"), masterName
            ));  // 資料庫欄位名稱
        }
        if (StringUtils.isNotBlank(status)) {  // 前端欄位名稱
            predicates.add(criteriaBuilder.equal(
                    root.get("isUse"), status
            ));  // 資料庫欄位名稱
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String toString() {
        return "Eng0822R1DTOCriteria{" +
                "bosName='" + bosName + '\'' +
                ", type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", chName='" + chName + '\'' +
                ", masterName='" + masterName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
