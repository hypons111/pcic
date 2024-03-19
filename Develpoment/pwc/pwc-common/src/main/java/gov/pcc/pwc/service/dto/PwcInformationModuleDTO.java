package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcInformationModule} entity.
 */
public class PwcInformationModuleDTO implements Serializable {

    /**
     * 序號
     */
    @NotNull
    @Schema(description = "序號", required = true)
    private Long proInformationModuleId;

    /**
     * 訊息資料序號
     */
    @Schema(description = "訊息資料序號")
    private Long proInformationId;

    /**
     * 模組別
     */
    @Schema(description = "模組別")
    private String module;

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

    public Long getProInformationModuleId() {
        return proInformationModuleId;
    }

    public void setProInformationModuleId(Long proInformationModuleId) {
        this.proInformationModuleId = proInformationModuleId;
    }

    public Long getProInformationId() {
        return proInformationId;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
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
        if (!(o instanceof PwcInformationModuleDTO)) {
            return false;
        }

        PwcInformationModuleDTO pwcInformationModuleDTO = (PwcInformationModuleDTO) o;
        if (this.proInformationModuleId == null) {
            return false;
        }
        return Objects.equals(this.proInformationModuleId, pwcInformationModuleDTO.proInformationModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInformationModuleId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationModuleDTO{" +
            "proInformationModuleId=" + getProInformationModuleId() +
            ", proInformationId=" + getProInformationId() +
            ", module='" + getModule() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
