package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcDownloadRole} entity.
 */
public class PwcDownloadRoleDTO implements Serializable {

    /**
     * type:bigint
     */
    @NotNull
    @Schema(description = "type:bigint", required = true)
    private Long proDownloadRoleId;

    /**
     * type:bigint
     */
    @Schema(description = "type:bigint")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String role;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant updateTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String updateUser;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant createTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String createUser;

    public Long getProDownloadRoleId() {
        return proDownloadRoleId;
    }

    public void setProDownloadRoleId(Long proDownloadRoleId) {
        this.proDownloadRoleId = proDownloadRoleId;
    }

    public Long getProDownloadId() {
        return proDownloadId;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
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
        if (!(o instanceof PwcDownloadRoleDTO)) {
            return false;
        }

        PwcDownloadRoleDTO pwcDownloadRoleDTO = (PwcDownloadRoleDTO) o;
        if (this.proDownloadRoleId == null) {
            return false;
        }
        return Objects.equals(this.proDownloadRoleId, pwcDownloadRoleDTO.proDownloadRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proDownloadRoleId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadRoleDTO{" +
            "proDownloadRoleId=" + getProDownloadRoleId() +
            ", proDownloadId=" + getProDownloadId() +
            ", role='" + getRole() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
