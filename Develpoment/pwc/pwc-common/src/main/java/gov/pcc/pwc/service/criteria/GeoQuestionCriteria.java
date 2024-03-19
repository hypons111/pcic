package gov.pcc.pwc.service.criteria;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import gov.pcc.pwc.db.Criteria;
import gov.pcc.pwc.domain.GeoQuestion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

public class GeoQuestionCriteria extends Criteria<GeoQuestion> {

    private String question;
    private LocalDate createTimeStart;
    private LocalDate createTimeEnd;
    private String isDel = "N";
    private String role;
    private String userId;
    private String questionType;
    private String status;
    private String questionPerson;

    private String memberRemark;
    private String companyRemark;

    public GeoQuestionCriteria(){
        this.sortDirection = Sort.Direction.DESC;
        this.sortBy = new String[] {"createTime"};
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDate createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDate getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDate createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionPerson() {
        return questionPerson;
    }

    public void setQuestionPerson(String questionPerson) {
        this.questionPerson = questionPerson;
    }


    public String getMemberRemark() {
        return memberRemark;
    }

    public void setMemberRemark(String memberRemark) {
        this.memberRemark = memberRemark;
    }

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoQuestionCriteria that = (GeoQuestionCriteria) o;
        return
                Objects.equal(question, that.question) &&
                Objects.equal(createTimeStart, that.createTimeStart) &&
                Objects.equal(createTimeEnd, that.createTimeEnd) &&
                Objects.equal(questionType, that.questionType) &&
                Objects.equal(status, that.status) &&
                Objects.equal(questionPerson, that.questionPerson)&&
                Objects.equal(memberRemark, that.memberRemark)&&
                Objects.equal(companyRemark, that.companyRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(question, createTimeStart, createTimeEnd, questionType, status, questionPerson,memberRemark,companyRemark);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("perPage", perPage)
                .add("currentPage", currentPage)
                .add("sortBy", sortBy)
                .add("sortDirection", sortDirection)
                .add("question", question)
                .add("createTimeStart", createTimeStart)
                .add("createTimeEnd", createTimeEnd)
                .add("isDel", isDel)
                .add("role", role)
                .add("userId", userId)
                .add("questionType", questionType)
                .add("status", status)
                .add("questionPerson", questionPerson)
                .add("memberRemark", memberRemark)
                .add("companyRemark", companyRemark)
                .toString();
    }

    @Override
    public Predicate toPredicate(Root<GeoQuestion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Set<Predicate> predicates = new HashSet<>();
        predicates.add(criteriaBuilder.equal(root.get("isDel"), isDel));
        if (StringUtils.isNotBlank(question)) {
            predicates.add(criteriaBuilder.like(root.get("question"), WILD_CARD + question + WILD_CARD));
        }
        if (createTimeStart != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), createTimeStart.toInstant(ZoneOffset.UTC)));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), createTimeStart));
        }
        if (createTimeEnd != null) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), createTimeEnd.toInstant(ZoneOffset.UTC)));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), createTimeEnd));
        }
        if (StringUtils.isNotBlank(questionType)) {
            predicates.add(criteriaBuilder.equal(root.get("roleId"), questionType ));
        }
        if (StringUtils.isNotBlank(status)) {
            predicates.add(criteriaBuilder.equal(root.get("status"), status ));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    @Override
    public String getOrderBy() {
        return null;
    }
}
