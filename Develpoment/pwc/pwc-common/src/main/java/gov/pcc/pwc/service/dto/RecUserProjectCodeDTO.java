package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecUserProjectCode} entity.
 */
public class RecUserProjectCodeDTO implements Serializable {

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String userId;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
//    @Size(max = 20)
//    @Schema(description = "type: VARCHAR(20)", required = true)
//    private String projectCode;


//    @NotNull
//    @Size(max = 20)
//    @Schema(description = "type: VARCHAR(20)", required = true)
    private List<String> projectCodes;

    /**
     * type: NVARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String createUser;

    /**
     * type: DATETIME
     */
//    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String updateUser;

    /**
     * type: DATETIME
     */
//    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public String getProjectCode() {
//        return projectCode;
//    }
//
//    public void setProjectCode(String projectCode) {
//        this.projectCode = projectCode;
//    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getProjectCodes() {
        return projectCodes;
    }

    public void setProjectCodes(List<String> projectCodes) {
        this.projectCodes = projectCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecUserProjectCodeDTO)) {
            return false;
        }

        RecUserProjectCodeDTO recUserProjectCodeDTO = (RecUserProjectCodeDTO) o;
        if (this.userId == null) {
            return false;
        }
        return Objects.equals(this.userId, recUserProjectCodeDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecUserProjectCodeDTO{" +
            "userId='" + getUserId() + "'" +
            ", projectCodes='" + getProjectCodes() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
