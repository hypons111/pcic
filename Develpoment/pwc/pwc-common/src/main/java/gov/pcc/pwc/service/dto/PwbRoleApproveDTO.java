package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbRoleApprove} entity.
 */
public class PwbRoleApproveDTO implements Serializable {

    /**
     * type: BIGINT
     */
//    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long id;

    /**
     * type: VARCHAR(3)
     */
//    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String roleType;

    /**
     * type: VARCHAR(3)
     */
//    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String moduleType;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String businessType;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String userId;

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

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        if (!(o instanceof PwbRoleApproveDTO)) {
            return false;
        }

        PwbRoleApproveDTO pwbRoleApproveDTO = (PwbRoleApproveDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbRoleApproveDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbRoleApproveDTO{" +
            "id=" + getId() +
            ", roleType='" + getRoleType() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", userId='" + getUserId() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
