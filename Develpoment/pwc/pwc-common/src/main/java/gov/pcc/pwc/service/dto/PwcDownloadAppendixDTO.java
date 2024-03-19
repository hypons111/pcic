package gov.pcc.pwc.service.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcDownloadAppendix} entity.
 */
public class PwcDownloadAppendixDTO implements Serializable {

    /**
     * type:bigint
     */
    @NotNull
    @Schema(description = "type:bigint", required = true)
    private Long proDownloadAppendixId;

    /**
     * type:bigint
     */
    @Schema(description = "type:bigint")
    private Long proDownloadId;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String attributes;

    /**
     * type:nvarchar(1000)
     */
    @Size(max = 1000)
    @Schema(description = "type:nvarchar(1000)")
    private String text;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String appendix;

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

    public Long getProDownloadAppendixId() {
        return proDownloadAppendixId;
    }

    public void setProDownloadAppendixId(Long proDownloadAppendixId) {
        this.proDownloadAppendixId = proDownloadAppendixId;
    }

    public Long getProDownloadId() {
        return proDownloadId;
    }

    public void setProDownloadId(Long proDownloadId) {
        this.proDownloadId = proDownloadId;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
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
        if (!(o instanceof PwcDownloadAppendixDTO)) {
            return false;
        }

        PwcDownloadAppendixDTO pwcDownloadAppendixDTO = (PwcDownloadAppendixDTO) o;
        if (this.proDownloadAppendixId == null) {
            return false;
        }
        return Objects.equals(this.proDownloadAppendixId, pwcDownloadAppendixDTO.proDownloadAppendixId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proDownloadAppendixId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadAppendixDTO{" +
            "proDownloadAppendixId=" + getProDownloadAppendixId() +
            ", proDownloadId=" + getProDownloadId() +
            ", attributes='" + getAttributes() + "'" +
            ", text='" + getText() + "'" +
            ", appendix='" + getAppendix() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
