package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngTeacher} entity.
 */
public class EngTeacherDTO implements Serializable {

    @NotNull
    @Size(max = 20)
    private String engTeacherId;

    @NotNull
    @Size(max = 400)
    private String teacherName;

    @Size(max = 200)
    private String teacherHighestEdu;

    @Size(max = 1000)
    private String teacherJob;

    @Size(max = 1000)
    private String teacherExperience;

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

    public String getEngTeacherId() {
        return engTeacherId;
    }

    public void setEngTeacherId(String engTeacherId) {
        this.engTeacherId = engTeacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherHighestEdu() {
        return teacherHighestEdu;
    }

    public void setTeacherHighestEdu(String teacherHighestEdu) {
        this.teacherHighestEdu = teacherHighestEdu;
    }

    public String getTeacherJob() {
        return teacherJob;
    }

    public void setTeacherJob(String teacherJob) {
        this.teacherJob = teacherJob;
    }

    public String getTeacherExperience() {
        return teacherExperience;
    }

    public void setTeacherExperience(String teacherExperience) {
        this.teacherExperience = teacherExperience;
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
        if (!(o instanceof EngTeacherDTO)) {
            return false;
        }

        EngTeacherDTO engTeacherDTO = (EngTeacherDTO) o;
        if (this.engTeacherId == null) {
            return false;
        }
        return Objects.equals(this.engTeacherId, engTeacherDTO.engTeacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engTeacherId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngTeacherDTO{" +
            "engTeacherId='" + getEngTeacherId() + "'" +
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
