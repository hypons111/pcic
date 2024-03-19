package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbUnitApply} entity.
 */
public class PwbUnitApplyDTO implements Serializable {

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
    private String unitId;

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
    private String approveUnit;

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
     * type: VARCHAR(3)
     */
//    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String userType;

    /**
     * type: VARCHAR(50)
     */
    @Size(max = 50)
    @Schema(description = "type: VARCHAR(50)", required = true)
    private String chName;

    /* ADM_AGENT */
    private String orgName;

    /* ADM_SYS_CODE */
    private String moduleName;

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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getApproveUnit() {
        return approveUnit;
    }

    public void setApproveUnit(String approveUnit) {
        this.approveUnit = approveUnit;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbUnitApplyDTO)) {
            return false;
        }

        PwbUnitApplyDTO pwbUnitApplyDTO = (PwbUnitApplyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbUnitApplyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwbUnitApplyDTO{" +
            "id=" + getId() +
            ", unitId='" + getUnitId() + "'" +
            ", applyTime='" + getApplyTime() + "'" +
            ", approveTime='" + getApproveTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", moduleType='" + getModuleType() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", approveUnit='" + getApproveUnit() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", userType='" + getUserType() + "'" +
            ", chName='" + getChName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", moduleName='" + getModuleName() + "'" +
            "}";
    }
}
