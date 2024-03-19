package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcInformationAppendix} entity.
 */
public class PwcInformationAppendixDTO implements Serializable {

    /**
     * type:bigint
     */
    @NotNull
    @Schema(description = "type:bigint", required = true)
    private Long proInformationAppendixId;

    /**
     * type:bigint
     */
    @Schema(description = "type:bigint")
    private Long proInformationId;

    /**
     * type:varchar(1)
     */
    @Schema(description = "type:varchar(1)")
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

    public Long getProInformationAppendixId() {
        return proInformationAppendixId;
    }

    public void setProInformationAppendixId(Long proInformationAppendixId) {
        this.proInformationAppendixId = proInformationAppendixId;
    }

    public Long getProInformationId() {
        return proInformationId;
    }

    public void setProInformationId(Long proInformationId) {
        this.proInformationId = proInformationId;
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
        if (!(o instanceof PwcInformationAppendixDTO)) {
            return false;
        }

        PwcInformationAppendixDTO pwcInformationAppendixDTO = (PwcInformationAppendixDTO) o;
        if (this.proInformationAppendixId == null) {
            return false;
        }
        return Objects.equals(this.proInformationAppendixId, pwcInformationAppendixDTO.proInformationAppendixId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInformationAppendixId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationAppendixDTO{" +
            "proInformationAppendixId=" + getProInformationAppendixId() +
            ", proInformationId=" + getProInformationId() +
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
