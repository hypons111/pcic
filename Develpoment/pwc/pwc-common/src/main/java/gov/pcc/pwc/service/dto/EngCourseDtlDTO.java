package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngCourseDtl} entity.
 */
public class EngCourseDtlDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engCourseId;

    @NotNull
    private Long engCourseDetailId;

    @Size(max = 600)
    private String courseName;

    private LocalDate courseDate;

    @Size(max = 5)
    private String timeStart;

    @Size(max = 5)
    private String timeEnd;

    @Size(max = 1)
    private String law;

    private Long point;

    @Size(max = 150)
    private String subjectList;

    private String remark;

    @Size(max = 1)
    private String isReview;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngCourseId() {
        return engCourseId;
    }

    public void setEngCourseId(String engCourseId) {
        this.engCourseId = engCourseId;
    }

    public Long getEngCourseDetailId() {
        return engCourseDetailId;
    }

    public void setEngCourseDetailId(Long engCourseDetailId) {
        this.engCourseDetailId = engCourseDetailId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(LocalDate courseDate) {
        this.courseDate = courseDate;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsReview() {
        return isReview;
    }

    public void setIsReview(String isReview) {
        this.isReview = isReview;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngCourseDtlDTO)) {
            return false;
        }

        EngCourseDtlDTO engCourseDtlDTO = (EngCourseDtlDTO) o;
        if (this.engCourseId == null) {
            return false;
        }
        return Objects.equals(this.engCourseId, engCourseDtlDTO.engCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engCourseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngCourseDtlDTO{" +
            "engCourseId='" + getEngCourseId() + "'" +
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
