package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngTeacher.
 */
@Entity
@Table(name = "eng_teacher")
public class EngTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_teacher_id", length = 20, nullable = false)
    private String engTeacherId;

    @NotNull
    @Size(max = 400)
    @Column(name = "teacher_name", length = 400, nullable = false)
    private String teacherName;

    @Size(max = 200)
    @Column(name = "teacher_highest_edu", length = 200)
    private String teacherHighestEdu;

    @Size(max = 1000)
    @Column(name = "teacher_job", length = 1000)
    private String teacherJob;

    @Size(max = 1000)
    @Column(name = "teacher_experience", length = 1000)
    private String teacherExperience;

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

    public String getEngTeacherId() {
        return this.engTeacherId;
    }

    public EngTeacher engTeacherId(String engTeacherId) {
        this.setEngTeacherId(engTeacherId);
        return this;
    }

    public void setEngTeacherId(String engTeacherId) {
        this.engTeacherId = engTeacherId;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public EngTeacher teacherName(String teacherName) {
        this.setTeacherName(teacherName);
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherHighestEdu() {
        return this.teacherHighestEdu;
    }

    public EngTeacher teacherHighestEdu(String teacherHighestEdu) {
        this.setTeacherHighestEdu(teacherHighestEdu);
        return this;
    }

    public void setTeacherHighestEdu(String teacherHighestEdu) {
        this.teacherHighestEdu = teacherHighestEdu;
    }

    public String getTeacherJob() {
        return this.teacherJob;
    }

    public EngTeacher teacherJob(String teacherJob) {
        this.setTeacherJob(teacherJob);
        return this;
    }

    public void setTeacherJob(String teacherJob) {
        this.teacherJob = teacherJob;
    }

    public String getTeacherExperience() {
        return this.teacherExperience;
    }

    public EngTeacher teacherExperience(String teacherExperience) {
        this.setTeacherExperience(teacherExperience);
        return this;
    }

    public void setTeacherExperience(String teacherExperience) {
        this.teacherExperience = teacherExperience;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngTeacher updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngTeacher updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngTeacher createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngTeacher createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngTeacher transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngTeacher transferTime(Instant transferTime) {
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
        if (!(o instanceof EngTeacher)) {
            return false;
        }
        return engTeacherId != null && engTeacherId.equals(((EngTeacher) o).engTeacherId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngTeacher{" +
            "engTeacherId=" + getEngTeacherId() +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherHighestEdu='" + getTeacherHighestEdu() + "'" +
            ", teacherJob='" + getTeacherJob() + "'" +
            ", teacherExperience='" + getTeacherExperience() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
