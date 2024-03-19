package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbRoleApply} entity.
 */
public class PwbRoleApplyDTO implements Serializable {

    /**
     * type: BIGINT
     */
//    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long id;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String userId;

    /**
     * type: DATETIME
     */
    @Schema(description = "type: DATETIME")
    private Instant applyTime;

    /**
     * type: DATETIME
     */
    @Schema(description = "type: DATETIME")
    private Instant approveTime;

    /**
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Schema(description = "type: VARCHAR(1)")
    private String status;

    /**
     * type: VARCHAR(3)
     */
//    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String moduleType;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Instant applyTime) {
        this.applyTime = applyTime;
    }

    public Instant getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Instant approveTime) {
        this.approveTime = approveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbRoleApplyDTO)) {
            return false;
        }

        PwbRoleApplyDTO pwbRoleApplyDTO = (PwbRoleApplyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbRoleApplyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbRoleApplyDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", approveTime='" + getApproveTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
