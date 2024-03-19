package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcInformationRole} entity.
 */
public class PwcInformationRoleDTO implements Serializable {

    /**
     * 序號
     */
    @NotNull
    @Schema(description = "序號", required = true)
    private Long proInformationRoleId;

    /**
     * 訊息資料序號
     */
    @Schema(description = "訊息資料序號")
    private Long proInformationId;

    /**
     * 角色別
     */
    @Schema(description = "角色別")
    private String role;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Instant updateTime;

    /**
     * 更新人員
     */
    @Size(max = 20)
    @Schema(description = "更新人員")
    private String updateUser;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Instant createTime;

    /**
     * 建立人員
     */
    @Size(max = 20)
    @Schema(description = "建立人員")
    private String createUser;

    public Long getProInformationRoleId() {
        return proInformationRoleId;
    }

    public void setProInformationRoleId(Long proInformationRoleId) {
        this.proInformationRoleId = proInformationRoleId;
    }

    public Long getProInformationId() {
        return proInformationId;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcInformationRoleDTO)) {
            return false;
        }

        PwcInformationRoleDTO pwcInformationRoleDTO = (PwcInformationRoleDTO) o;
        if (this.proInformationRoleId == null) {
            return false;
        }
        return Objects.equals(this.proInformationRoleId, pwcInformationRoleDTO.proInformationRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInformationRoleId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationRoleDTO{" +
            "proInformationRoleId=" + getProInformationRoleId() +
            ", proInformationId=" + getProInformationId() +
            ", role='" + getRole() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
