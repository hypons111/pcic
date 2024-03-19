package gov.pcc.pwc.service.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcDownloadModule} entity.
 */
public class PwcDownloadModuleDTO implements Serializable {

    /**
     * type:bigint
     */
    @NotNull
    @Schema(description = "type:bigint", required = true)
    private Long proDownloadModuleId;

    /**
     * type:bigint
     */
    @Schema(description = "type:bigint")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String module;

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

    public Long getProDownloadModuleId() {
        return proDownloadModuleId;
    }

    public void setProDownloadModuleId(Long proDownloadModuleId) {
        this.proDownloadModuleId = proDownloadModuleId;
    }

    public Long getProDownloadId() {
        return proDownloadId;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
        if (!(o instanceof PwcDownloadModuleDTO)) {
            return false;
        }

        PwcDownloadModuleDTO pwcDownloadModuleDTO = (PwcDownloadModuleDTO) o;
        if (this.proDownloadModuleId == null) {
            return false;
        }
        return Objects.equals(this.proDownloadModuleId, pwcDownloadModuleDTO.proDownloadModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proDownloadModuleId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadModuleDTO{" +
            "proDownloadModuleId=" + getProDownloadModuleId() +
            ", proDownloadId=" + getProDownloadId() +
            ", module='" + getModule() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
