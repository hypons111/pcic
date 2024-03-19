package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.EngEngrExam;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EngEngrExamCriteria extends Criteria<EngEngrExam> {

    private LocalDate importDate;

    private String subject;

    private String name;

    private String sex;

    private LocalDate birthDate;

    private String idno;

    private String addrZip;

    private String addrCity;

    private String addrTown;

    private String addrOther;

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getAddrZip() {
        return addrZip;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrTown() {
        return addrTown;
    }

    public void setAddrTown(String addrTown) {
        this.addrTown = addrTown;
    }

    public String getAddrOther() {
        return addrOther;
    }

    public void setAddrOther(String addrOther) {
        this.addrOther = addrOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngEngrExamCriteria that = (EngEngrExamCriteria) o;
        return
            Objects.equal(importDate, that.importDate) &&
            Objects.equal(subject, that.subject) &&
            Objects.equal(name, that.name) &&
            Objects.equal(sex, that.sex) &&
            Objects.equal(birthDate, that.birthDate) &&
            Objects.equal(idno, that.idno) &&
            Objects.equal(addrZip, that.addrZip) &&
            Objects.equal(addrCity, that.addrCity) &&
            Objects.equal(addrTown, that.addrTown) &&
            Objects.equal(addrOther, that.addrOther);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(importDate, subject, name, sex, birthDate, idno, addrZip, addrCity,
                addrTown, addrOther);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("importDate", importDate)
            .add("subject", subject)
            .add("name", name)
            .add("sex", sex)
            .add("birthDate", birthDate)
            .add("idno", idno)
            .add("addrZip", addrZip)
            .add("addrCity", addrCity)
            .add("addrTown", addrTown)
            .add("addrOther", addrOther)
            .toString();
    }

    @Override
    public Predicate toPredicate(Root<EngEngrExam> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        if (importDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("importDate"), importDate));
        }

        if (importDate != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("importDate"), importDate.plusYears(1)));
        }

        if (StringUtils.isNotBlank(subject)) {
            predicates.add(criteriaBuilder.equal(root.get("subject"), subject));
        }

        if (StringUtils.isNotBlank(name)) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }

        if (StringUtils.isNotBlank(sex)) {
            predicates.add(criteriaBuilder.equal(root.get("sex"), sex));
        }

        if (birthDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), birthDate));
        }

        if (StringUtils.isNotBlank(idno)) {
            predicates.add(criteriaBuilder.equal(root.get("idno"), idno));
        }

        if (StringUtils.isNotBlank(addrZip)) {
            predicates.add(criteriaBuilder.equal(root.get("addrZip"), addrZip));
        }

        if (StringUtils.isNotBlank(addrCity)) {
            predicates.add(criteriaBuilder.equal(root.get("addrCity"), addrCity));
        }

        if (StringUtils.isNotBlank(addrTown)) {
            predicates.add(criteriaBuilder.equal(root.get("addrTown"), addrTown));
        }

        if (StringUtils.isNotBlank(addrOther)) {
            predicates.add(criteriaBuilder.equal(root.get("addrOther"), addrOther));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return sortBy[0] + " " + sortDirection.name();
    }
}
