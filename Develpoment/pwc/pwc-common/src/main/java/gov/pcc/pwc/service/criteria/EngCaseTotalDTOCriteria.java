package gov.pcc.pwc.service.criteria;

import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.service.dto.EngCaseTotalDTO;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EngCaseTotalDTOCriteria extends Criteria<EngCaseTotalDTO> {

    private String caseType;
    private String recordItemsList;
    private String projectType;
    private String caseNo;
    private String caseName;
    private String execOrgName;
    private String execOrgId;
    private LocalDate execDateStart;
    private LocalDate execDateEnd;
    private LocalDate tenderDateStart;
    private LocalDate tenderDateEnd;
    private String compName;
    private String compIdno;
    private String engrName;
    private String engrId;

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getRecordItemsList() {
        return recordItemsList;
    }

    public void setRecordItemsList(String recordItemsList) {
        this.recordItemsList = recordItemsList;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getExecOrgName() {
        return execOrgName;
    }

    public void setExecOrgName(String execOrgName) {
        this.execOrgName = execOrgName;
    }

    public String getExecOrgId() {
        return execOrgId;
    }

    public void setExecOrgId(String execOrgId) {
        this.execOrgId = execOrgId;
    }

    public LocalDate getExecDateStart() {
        return execDateStart;
    }

    public void setExecDateStart(LocalDate execDateStart) {
        this.execDateStart = execDateStart;
    }

    public LocalDate getExecDateEnd() {
        return execDateEnd;
    }

    public void setExecDateEnd(LocalDate execDateEnd) {
        this.execDateEnd = execDateEnd;
    }

    public LocalDate getTenderDateStart() {
        return tenderDateStart;
    }

    public void setTenderDateStart(LocalDate tenderDateStart) {
        this.tenderDateStart = tenderDateStart;
    }

    public LocalDate getTenderDateEnd() {
        return tenderDateEnd;
    }

    public void setTenderDateEnd(LocalDate tenderDateEnd) {
        this.tenderDateEnd = tenderDateEnd;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getEngrName() {
        return engrName;
    }

    public void setEngrName(String engrName) {
        this.engrName = engrName;
    }

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngCaseTotalDTOCriteria that = (EngCaseTotalDTOCriteria) o;
        return Objects.equals(caseType, that.caseType) && Objects.equals(recordItemsList, that.recordItemsList) && Objects.equals(projectType, that.projectType) && Objects.equals(caseNo, that.caseNo) && Objects.equals(caseName, that.caseName) && Objects.equals(execOrgName, that.execOrgName) && Objects.equals(execOrgId, that.execOrgId) && Objects.equals(execDateStart, that.execDateStart) && Objects.equals(execDateEnd, that.execDateEnd) && Objects.equals(tenderDateStart, that.tenderDateStart) && Objects.equals(tenderDateEnd, that.tenderDateEnd) && Objects.equals(compName, that.compName) && Objects.equals(compIdno, that.compIdno) && Objects.equals(engrName, that.engrName) && Objects.equals(engrId, that.engrId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseType, recordItemsList, projectType, caseNo, caseName, execOrgName, execOrgId, execDateStart, execDateEnd, tenderDateStart, tenderDateEnd, compName, compIdno, engrName, engrId);
    }

    @Override
    public String toString() {
        return "EngCaseTotalDTOCriteria{" +
                "caseType='" + caseType + '\'' +
                ", recordItemsList='" + recordItemsList + '\'' +
                ", projectType='" + projectType + '\'' +
                ", caseNo='" + caseNo + '\'' +
                ", caseName='" + caseName + '\'' +
                ", execOrgName='" + execOrgName + '\'' +
                ", execOrgId='" + execOrgId + '\'' +
                ", execDateStart=" + execDateStart +
                ", execDateEnd=" + execDateEnd +
                ", tenderDateStart=" + tenderDateStart +
                ", tenderDateEnd=" + tenderDateEnd +
                ", compName='" + compName + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", engrName='" + engrName + '\'' +
                ", engrId='" + engrId + '\'' +
                '}';
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }

    @Override
    public Predicate toPredicate(Root<EngCaseTotalDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(caseType)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("caseType"), caseType));
        }
        if (StringUtils.isNotBlank(recordItemsList)) {
            predicates.add(criteriaBuilder.equal(
                    root.get("recordItemsList"), recordItemsList));
        }
        if (StringUtils.isNotBlank(projectType)) {
            predicates.add(criteriaBuilder.equal(root.get("projectType"), projectType));
        }
        if (StringUtils.isNotBlank(caseNo)) {
            predicates.add(criteriaBuilder.like(root.get("caseNo"),  WILD_CARD + caseNo + WILD_CARD));
        }
        if (StringUtils.isNotBlank(caseName)) {
            predicates.add(criteriaBuilder.like(root.get("caseName"), WILD_CARD + caseName  + WILD_CARD));
        }
        if (StringUtils.isNotBlank(execOrgName)) {
            predicates.add(criteriaBuilder.equal(root.get("execOrgName"), execOrgName));
        }
        if (StringUtils.isNotBlank(execOrgId)) {
            predicates.add(criteriaBuilder.equal(root.get("execOrgId"), execOrgId));
        }
        if (execDateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("execDateStart"), execDateStart));
        }
        if (execDateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("execDateEnd"), execDateEnd));
        }
        if (tenderDateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("tenderDate"), tenderDateStart));
        }
        if (tenderDateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("tenderDate"), tenderDateEnd));
        }
        if (StringUtils.isNotBlank(compName)) {
            predicates.add(criteriaBuilder.like(root.get("compName"), WILD_CARD + compName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(compIdno)) {
            predicates.add(criteriaBuilder.equal(root.get("compIdno"), compIdno));
        }
        if (StringUtils.isNotBlank(engrName)) {
            predicates.add(criteriaBuilder.like(root.get("engrName"), WILD_CARD + engrName + WILD_CARD));
        }
        if (StringUtils.isNotBlank(engrId)) {
            predicates.add(criteriaBuilder.equal(root.get("engrId"), engrId));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
