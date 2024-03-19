package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbUnitRole} entity.
 */
public class PwbUnitRoleDTO implements Serializable {

    /**
     * type: BIGINT
     */
    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long id;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String unitId;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String roleId;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant updateTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        if (!(o instanceof PwbUnitRoleDTO)) {
            return false;
        }

        PwbUnitRoleDTO pwbUnitRoleDTO = (PwbUnitRoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbUnitRoleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbUnitRoleDTO{" +
            "id=" + getId() +
            ", unitId='" + getUnitId() + "'" +
            ", roleId='" + getRoleId() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
