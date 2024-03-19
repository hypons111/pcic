package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngCourse.
 */
@Entity
@Table(name = "eng_course")
public class EngCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_course_id", length = 20, nullable = false)
    private String engCourseId;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Size(max = 200)
    @Column(name = "comp_id", length = 200)
    private String compId;

    @Size(max = 200)
    @Column(name = "admin_comp_name", length = 200)
    private String adminCompName;

    @Size(max = 600)
    @Column(name = "course_name", length = 600)
    private String courseName;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "upload_deadline")
    private LocalDate uploadDeadline;

    @Size(max = 100)
    @Column(name = "place", length = 100)
    private String place;

    @Size(max = 20)
    @Column(name = "contact_person", length = 20)
    private String contactPerson;

    @Size(max = 200)
    @Column(name = "contact_tel", length = 200)
    private String contactTel;

    @Size(max = 50)
    @Column(name = "contact_email", length = 50)
    private String contactEmail;

    @NotNull
    @Size(max = 3)
    @Column(name = "status", length = 3, nullable = false)
    private String status;

    @Size(max = 20)
    @Column(name = "review_staff", length = 20)
    private String reviewStaff;

    @Column(name = "review_time")
    private Instant reviewTime;

    @Size(max = 2000)
    @Column(name = "review_desc", length = 2000)
    private String reviewDesc;

    @Size(max = 500)
    @Column(name = "web_url", length = 500)
    private String webUrl;

    @Size(max = 20)
    @Column(name = "doc_no", length = 20)
    private String docNo;

    @Size(max = 1)
    @Column(name = "is_need_check", length = 1)
    private String isNeedCheck;

    @Size(max = 1)
    @Column(name = "is_media", length = 1)
    private String isMedia;

    @Size(max = 1200)
    @Column(name = "remark", length = 1200)
    private String remark;

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

    public EngCourse engCourseId(String engCourseId) {
        this.setEngCourseId(engCourseId);
        return this;
    }

    public void setEngCourseId(String engCourseId) {
        this.engCourseId = engCourseId;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public EngCourse applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getCompId() {
        return this.compId;
    }

    public EngCourse compId(String compId) {
        this.setCompId(compId);
        return this;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getAdminCompName() {
        return this.adminCompName;
    }

    public EngCourse adminCompName(String adminCompName) {
        this.setAdminCompName(adminCompName);
        return this;
    }

    public void setAdminCompName(String adminCompName) {
        this.adminCompName = adminCompName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public EngCourse courseName(String courseName) {
        this.setCourseName(courseName);
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getDateStart() {
        return this.dateStart;
    }

    public EngCourse dateStart(LocalDate dateStart) {
        this.setDateStart(dateStart);
        return this;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return this.dateEnd;
    }

    public EngCourse dateEnd(LocalDate dateEnd) {
        this.setDateEnd(dateEnd);
        return this;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getUploadDeadline() {
        return this.uploadDeadline;
    }

    public EngCourse uploadDeadline(LocalDate uploadDeadline) {
        this.setUploadDeadline(uploadDeadline);
        return this;
    }

    public void setUploadDeadline(LocalDate uploadDeadline) {
        this.uploadDeadline = uploadDeadline;
    }

    public String getPlace() {
        return this.place;
    }

    public EngCourse place(String place) {
        this.setPlace(place);
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public EngCourse contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public EngCourse contactTel(String contactTel) {
        this.setContactTel(contactTel);
        return this;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public EngCourse contactEmail(String contactEmail) {
        this.setContactEmail(contactEmail);
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getStatus() {
        return this.status;
    }

    public EngCourse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStaff() {
        return this.reviewStaff;
    }

    public EngCourse reviewStaff(String reviewStaff) {
        this.setReviewStaff(reviewStaff);
        return this;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return this.reviewTime;
    }

    public EngCourse reviewTime(Instant reviewTime) {
        this.setReviewTime(reviewTime);
        return this;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return this.reviewDesc;
    }

    public EngCourse reviewDesc(String reviewDesc) {
        this.setReviewDesc(reviewDesc);
        return this;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public EngCourse webUrl(String webUrl) {
        this.setWebUrl(webUrl);
        return this;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDocNo() {
        return this.docNo;
    }

    public EngCourse docNo(String docNo) {
        this.setDocNo(docNo);
        return this;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getIsNeedCheck() {
        return this.isNeedCheck;
    }

    public EngCourse isNeedCheck(String isNeedCheck) {
        this.setIsNeedCheck(isNeedCheck);
        return this;
    }

    public void setIsNeedCheck(String isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    public String getIsMedia() {
        return this.isMedia;
    }

    public EngCourse isMedia(String isMedia) {
        this.setIsMedia(isMedia);
        return this;
    }

    public void setIsMedia(String isMedia) {
        this.isMedia = isMedia;
    }

    public String getRemark() {
        return this.remark;
    }

    public EngCourse remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngCourse updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngCourse updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngCourse createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngCourse createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngCourse transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngCourse transferTime(Instant transferTime) {
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
        if (!(o instanceof EngCourse)) {
            return false;
        }
        return engCourseId != null && engCourseId.equals(((EngCourse) o).engCourseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCourse{" +
            "engCourseId=" + getEngCourseId() +
            ", applyDate='" + getApplyDate() + "'" +
            ", compId='" + getCompId() + "'" +
            ", adminCompName='" + getAdminCompName() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", dateStart='" + getDateStart() + "'" +
            ", dateEnd='" + getDateEnd() + "'" +
            ", uploadDeadline='" + getUploadDeadline() + "'" +
            ", place='" + getPlace() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactTel='" + getContactTel() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", status='" + getStatus() + "'" +
            ", reviewStaff='" + getReviewStaff() + "'" +
            ", reviewTime='" + getReviewTime() + "'" +
            ", reviewDesc='" + getReviewDesc() + "'" +
            ", webUrl='" + getWebUrl() + "'" +
            ", docNo='" + getDocNo() + "'" +
            ", isNeedCheck='" + getIsNeedCheck() + "'" +
            ", isMedia='" + getIsMedia() + "'" +
            ", remark='" + getRemark() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
