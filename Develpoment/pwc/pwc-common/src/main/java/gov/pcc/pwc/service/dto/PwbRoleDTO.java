package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class PwbRoleDTO implements Serializable {


    /* ADM_ACCOUNT */
    @Schema(description = "type: VARCHAR")
    private String userType;

    /* ADM_CONTACT */
    @Size(max = 50)
    @Schema(description = "type: NVARCHAR(50)")
    private String chName;

    /* PWB_ROLE_APPROVE */
    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String moduleType;

    @NotNull
    @Size(max = 3)
    @Schema(description = "type: VARCHAR(3)", required = true)
    private String roleType;

    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String businessType;

    /* PWB_ROLE_APPLY */
    @Schema(description = "type: BIGINT", required = true)
    private Long id;

    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String userId;

    @Size(max = 1)
    @Schema(description = "type: VARCHAR(1)")
    private String status;

    @Schema(description = "type: DATETIME")
    private Instant applyTime;

    @Schema(description = "type: DATETIME")
    private Instant approveTime;

    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String updateUser;

    @Schema(description = "type: DATETIME", required = true)
    private Instant updateTime;

    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String createUser;

    @Schema(description = "type: DATETIME", required = true)
    private Instant createTime;

    public String getUserType() {return userType;}
    public void setUserType(String userType) {this.userType = userType;}

    public String getChName() {return chName;}
    public void setChName(String chName) {this.chName = chName;}

    public String getModuleType() {return moduleType;}
    public void setModuleType(String moduleType) {this.moduleType = moduleType;}

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Instant getApplyTime() {return applyTime;}
    public void setApplyTime(Instant applyTime) {this.applyTime = applyTime;}

    public Instant getApproveTime() {return approveTime;}
    public void setApproveTime(Instant approveTime) {this.approveTime = approveTime;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getUpdateUser() {return updateUser;}
    public void setUpdateUser(String updateUser) {this.updateUser = updateUser;}

    public Instant getUpdateTime() {return updateTime;}
    public void setUpdateTime(Instant updateTime) {this.updateTime = updateTime;}

    public String getCreateUser() {return createUser;}
    public void setCreateUser(String createUser) {this.createUser = createUser;}

    public Instant getCreateTime() {return createTime;}
    public void setCreateTime(Instant createTime) {this.createTime = createTime;}

    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbRoleDTO)) {
            return false;
        }

        PwbRoleDTO pwbRoleDTO = (PwbRoleDTO) o;
        if (this.userId == null) {
            return false;
        }
        return Objects.equals(this.userId, pwbRoleDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    @Override
    public String toString() {
        return "PwbRoleDTO{" +
                "userType='" + userType + '\'' +
                ", chName='" + chName + '\'' +
                ", moduleType='" + moduleType + '\'' +
                ", roleType='" + roleType + '\'' +
                ", businessType='" + businessType + '\'' +
                ", id=" + id +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", applyTime=" + applyTime +
                ", approveTime=" + approveTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
