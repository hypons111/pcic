package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngCourseDtl.
 */
@Entity
@IdClass(EngCourseDtlPK.class)
@Table(name = "eng_course_detail")
public class EngCourseDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_course_id", length = 20, nullable = false)
    private String engCourseId;

    @NotNull
    @Id
    @Column(name = "eng_course_detail_id", precision = 21, scale = 2, nullable = false)
    private Long engCourseDetailId;

    @Size(max = 600)
    @Column(name = "course_name", length = 600)
    private String courseName;

    @Column(name = "course_date")
    private LocalDate courseDate;

    @Size(max = 5)
    @Column(name = "time_start", length = 5)
    private String timeStart;

    @Size(max = 5)
    @Column(name = "time_end", length = 5)
    private String timeEnd;

    @Size(max = 1)
    @Column(name = "law", length = 1)
    private String law;

    @Column(name = "point", precision = 21, scale = 2)
    private Long point;

    @Size(max = 150)
    @Column(name = "subject_list", length = 150)
    private String subjectList;

    @Column(name = "remark")
    private String remark;

    @Size(max = 1)
    @Column(name = "is_review", length = 1)
    private String isReview;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngCourseId() {
        return this.engCourseId;
    }

    public EngCourseDtl engCourseId(String engCourseId) {
        this.setEngCourseId(engCourseId);
        return this;
    }

    public void setEngCourseId(String engCourseId) {
        this.engCourseId = engCourseId;
    }

    public Long getEngCourseDetailId() {
        return this.engCourseDetailId;
    }

    public EngCourseDtl engCourseDetailId(Long engCourseDetailId) {
        this.setEngCourseDetailId(engCourseDetailId);
        return this;
    }

    public void setEngCourseDetailId(Long engCourseDetailId) {
        this.engCourseDetailId = engCourseDetailId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public EngCourseDtl courseName(String courseName) {
        this.setCourseName(courseName);
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCourseDate() {
        return this.courseDate;
    }

    public EngCourseDtl courseDate(LocalDate courseDate) {
        this.setCourseDate(courseDate);
        return this;
    }

    public void setCourseDate(LocalDate courseDate) {
        this.courseDate = courseDate;
    }

    public String getTimeStart() {
        return this.timeStart;
    }

    public EngCourseDtl timeStart(String timeStart) {
        this.setTimeStart(timeStart);
        return this;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return this.timeEnd;
    }

    public EngCourseDtl timeEnd(String timeEnd) {
        this.setTimeEnd(timeEnd);
        return this;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLaw() {
        return this.law;
    }

    public EngCourseDtl law(String law) {
        this.setLaw(law);
        return this;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public Long getPoint() {
        return this.point;
    }

    public EngCourseDtl point(Long point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public EngCourseDtl subjectList(String subjectList) {
        this.setSubjectList(subjectList);
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngCourseDtl remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsReview() {
        return this.isReview;
    }

    public EngCourseDtl isReview(String isReview) {
        this.setIsReview(isReview);
        return this;
    }

    public void setIsReview(String isReview) {
        this.isReview = isReview;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngCourseDtl updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngCourseDtl updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngCourseDtl createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngCourseDtl createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngCourseDtl transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngCourseDtl transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCourseDtl)) {
            return false;
        }
        return engCourseId != null && engCourseId.equals(((EngCourseDtl) o).engCourseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCourseDtl{" +
            "engCourseId=" + getEngCourseId() +
            ", engCourseDetailId=" + getEngCourseDetailId() +
            ", courseName='" + getCourseName() + "'" +
            ", courseDate='" + getCourseDate() + "'" +
            ", timeStart='" + getTimeStart() + "'" +
            ", timeEnd='" + getTimeEnd() + "'" +
            ", law='" + getLaw() + "'" +
            ", point=" + getPoint() +
            ", subjectList='" + getSubjectList() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isReview='" + getIsReview() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
