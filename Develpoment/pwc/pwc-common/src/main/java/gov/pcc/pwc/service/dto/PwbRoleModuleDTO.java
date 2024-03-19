package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbRoleModule} entity.
 */
public class PwbRoleModuleDTO implements Serializable {

    /**
     * type: BIGINT
     */
//    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long id;

    /**
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Schema(description = "type: VARCHAR(1)")
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
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String roleId;

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
    private String approveLevel;

    private String roleName;

    /**
     *
     * type:List<Map<String, String>>
     */
    private List<PwbRoleModuleDTO> table;

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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<PwbRoleModuleDTO> getTable() {
        return table;
    }

    public void setTable(List<PwbRoleModuleDTO> table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbRoleModuleDTO)) {
            return false;
        }

        PwbRoleModuleDTO pwbRoleModuleDTO = (PwbRoleModuleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbRoleModuleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "PwbRoleModuleDTO{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                ", moduleType='" + moduleType + '\'' +
                ", roleId='" + roleId + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", businessType='" + businessType + '\'' +
                ", approveLevel='" + approveLevel + '\'' +
                ", roleName='" + roleName + '\'' +
                ", table=" + table +
                '}';
    }
}
