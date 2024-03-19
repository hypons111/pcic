package gov.pcc.pwc.service.criteria;

import com.google.common.base.CaseFormat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngEngrLicense;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EngEngrLicenseCriteria extends Criteria<EngEngrLicense> {

    private String engrId;

    private String engEngrLicenseNo;

    private String chName;

    private String subjectList;

    private String idno;

    private String receiveNo;

    private String applyType;

    private LocalDate reciveDate;

    private LocalDate publicDate;

    private String status;

    private String engrName;
    private LocalDate applyDateStart;
    private LocalDate applyDateEnd;
    private LocalDate publicDateStart;
    private LocalDate publicDateEnd;
    private String reviewStatus;
    private String engrLicenseDocNo;
    private String compName;
    private String engEngrCertificateNo;

    private String subject;

    public String getEngrId() {
        return engrId;
    }

    public void setEngrId(String engrId) {
        this.engrId = engrId;
    }

    public String getEngEngrLicenseNo() {
        return engEngrLicenseNo;
    }

    public void setEngEngrLicenseNo(String engEngrLicenseNo) {
        this.engEngrLicenseNo = engEngrLicenseNo;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public LocalDate getReciveDate() {
        return reciveDate;
    }

    public void setReciveDate(LocalDate reciveDate) {
        this.reciveDate = reciveDate;
    }

    public LocalDate getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(LocalDate publicDate) {
        this.publicDate = publicDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEngrName() {
        return engrName;
    }

    public void setEngrName(String engrName) {
        this.engrName = engrName;
    }

    public LocalDate getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(LocalDate applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public LocalDate getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(LocalDate applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public LocalDate getPublicDateStart() {
        return publicDateStart;
    }

    public void setPublicDateStart(LocalDate publicDateStart) {
        this.publicDateStart = publicDateStart;
    }

    public LocalDate getPublicDateEnd() {
        return publicDateEnd;
    }

    public void setPublicDateEnd(LocalDate publicDateEnd) {
        this.publicDateEnd = publicDateEnd;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getEngrLicenseDocNo() {
        return engrLicenseDocNo;
    }

    public void setEngrLicenseDocNo(String engrLicenseDocNo) {
        this.engrLicenseDocNo = engrLicenseDocNo;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getEngEngrCertificateNo() {
        return engEngrCertificateNo;
    }

    public void setEngEngrCertificateNo(String engEngrCertificateNo) {
        this.engEngrCertificateNo = engEngrCertificateNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrLicenseCriteria that = (EngEngrLicenseCriteria) o;
        return
                Objects.equal(engrId, that.engrId) &&
                Objects.equal(engEngrLicenseNo, that.engEngrLicenseNo) &&
                Objects.equal(chName, that.chName) &&
                Objects.equal(subjectList, that.subjectList) &&
                Objects.equal(idno, that.idno) &&
                Objects.equal(receiveNo, that.receiveNo) &&
                Objects.equal(applyType, that.applyType) &&
                Objects.equal(reciveDate, that.reciveDate) &&
                Objects.equal(publicDate, that.publicDate) &&
                Objects.equal(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engrId, engEngrLicenseNo, chName, subjectList, idno, receiveNo, applyType,
                reciveDate, publicDate, status);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("question", engrId)
                .add("engEngrLicenseNo", engEngrLicenseNo)
                .add("chName", chName)
                .add("subjectList", subjectList)
                .add("idno", idno)
                .add("receiveNo", receiveNo)
                .add("applyType", applyType)
                .add("reciveDate", reciveDate)
                .add("publicDate", publicDate)
                .add("status", status)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<EngEngrLicense> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("engrId"), engrId));

        if (StringUtils.isNotBlank(engEngrLicenseNo)) {
            predicates.add(criteriaBuilder.equal(root.get("engEngrLicenseNo"), engEngrLicenseNo));
        }

        if (StringUtils.isNotBlank(chName)) {
            predicates.add(criteriaBuilder.equal(root.get("chName"), chName));
        }

        if (StringUtils.isNotBlank(subjectList)) {
            predicates.add(criteriaBuilder.equal(root.get("subjectList"), subjectList));
        }

        if (StringUtils.isNotBlank(idno)) {
            predicates.add(criteriaBuilder.equal(root.get("idno"), idno));
        }

        if (StringUtils.isNotBlank(receiveNo)) {
            predicates.add(criteriaBuilder.equal(root.get("receiveNo"), receiveNo));
        }

        if (StringUtils.isNotBlank(applyType)) {
            predicates.add(criteriaBuilder.equal(root.get("applyType"), applyType));
        }

        if (reciveDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("reciveDate"), reciveDate));
        }

        if (publicDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publicDate"), publicDate));
        }

        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }
}
