package gov.pcc.pwc.service.criteria;

import com.google.common.base.Objects;
import com.google.common.base.MoreObjects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngEngrCertificate;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EngEngrCertificateCriteria extends Criteria<EngEngrCertificate> {
    private String engrName;
    private String idno;
    private LocalDate applyDateStart;
    private LocalDate applyDateEnd;
    private LocalDate publicDateStart;
    private LocalDate publicDateEnd;
    private String status;
    private String subject;
    private String engrCertificateDocNo;
    private String engrCertificateDocNoOld;
    private String receiveNo;
    private String engEngrCertificateNo;

    public String getEngrName() {
        return engrName;
    }

    public void setEngrName(String engrName) {
        this.engrName = engrName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEngrCertificateDocNo() {
        return engrCertificateDocNo;
    }

    public void setEngrCertificateDocNo(String engrCertificateDocNo) {
        this.engrCertificateDocNo = engrCertificateDocNo;
    }

    public String getEngrCertificateDocNoOld() {
        return engrCertificateDocNoOld;
    }

    public void setEngrCertificateDocNoOld(String engrCertificateDocNoOld) {
        this.engrCertificateDocNoOld = engrCertificateDocNoOld;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getEngEngrCertificateNo() {
        return engEngrCertificateNo;
    }

    public void setEngEngrCertificateNo(String engEngrCertificateNo) {
        this.engEngrCertificateNo = engEngrCertificateNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrCertificateCriteria that = (EngEngrCertificateCriteria) o;
        return
            Objects.equal(engrName, that.engrName) &&
            Objects.equal(idno, that.idno) &&
            Objects.equal(applyDateStart, that.applyDateStart) &&
            Objects.equal(applyDateEnd, that.applyDateEnd) &&
            Objects.equal(publicDateStart, that.publicDateStart) &&
            Objects.equal(publicDateEnd, that.publicDateEnd) &&
            Objects.equal(status, that.status) &&
            Objects.equal(subject, that.subject) &&
            Objects.equal(engrCertificateDocNo, that.engrCertificateDocNo) &&
            Objects.equal(engrCertificateDocNoOld, that.engrCertificateDocNoOld) &&
            Objects.equal(receiveNo, that.receiveNo) &&
            Objects.equal(engEngrCertificateNo, that.engEngrCertificateNo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(engrName, idno, applyDateStart, applyDateEnd, publicDateStart, publicDateEnd, status, subject,
            engrCertificateDocNo, engrCertificateDocNoOld, receiveNo, engEngrCertificateNo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("engrName", engrName)
            .add("idno", idno)
            .add("applyDateStart", applyDateStart)
            .add("applyDateEnd", applyDateEnd)
            .add("publicDateStart", publicDateStart)
            .add("publicDateEnd", publicDateEnd)
            .add("status", status)
            .add("subject", subject)
            .add("engrCertificateDocNo", engrCertificateDocNo)
            .add("engrCertificateDocNoOld", engrCertificateDocNoOld)
            .add("receiveNo", receiveNo)
            .add("engEngrCertificateNo", engEngrCertificateNo)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<EngEngrCertificate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (StringUtils.isNotBlank(engrName)) {
            predicates.add(criteriaBuilder.or(
                criteriaBuilder.like(root.get("chName"), WILD_CARD + engrName + WILD_CARD),
                criteriaBuilder.like(root.get("enName"), WILD_CARD + engrName + WILD_CARD)
            ));
        }
        if (StringUtils.isNotBlank(idno)) {
            predicates.add(criteriaBuilder.equal(root.get("idno"), idno));
        }
        if (applyDateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyDate"), applyDateStart));
        }
        if (applyDateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("applyDate"), applyDateEnd));
        }
        if (publicDateStart != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publicDate"), publicDateStart));
        }
        if (publicDateEnd != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("publicDate"), publicDateEnd));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        }
        if (StringUtils.isNotBlank(subject)) {
            predicates.add(criteriaBuilder.equal(root.get("subject"), subject));
        }
        if (StringUtils.isNotBlank(engrCertificateDocNo)) {
            predicates.add(criteriaBuilder.like(root.get("engrCertificateDocNo"), WILD_CARD + engrCertificateDocNo + WILD_CARD));
        }
        if (StringUtils.isNotBlank(engrCertificateDocNoOld)) {
            predicates.add(criteriaBuilder.like(root.get("engrCertificateDocNoOld"), WILD_CARD + engrCertificateDocNoOld + WILD_CARD));
        }
        if (StringUtils.isNotBlank(receiveNo)) {
            predicates.add(criteriaBuilder.like(root.get("receiveNo"), WILD_CARD + receiveNo + WILD_CARD));
        }
        if (StringUtils.isNotBlank(engEngrCertificateNo)) {
            predicates.add(criteriaBuilder.equal(root.get("engEngrCertificateNo"), engEngrCertificateNo));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }
}
