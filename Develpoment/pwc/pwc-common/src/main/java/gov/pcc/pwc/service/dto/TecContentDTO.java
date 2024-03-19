package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.TecContent} entity.
 */
@Schema(description = "TEC")
public class TecContentDTO implements Serializable {

    /**
     * type: BIGINT
     */
    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long contentId;

    /**
     * type: BIGINT
     */
    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long pageId;

    /**
     * type: NVARCHAR(200)
     */
    @Size(max = 200)
    @Schema(description = "type: NVARCHAR(200)")
    private String contentTitle;

    /**
     * type: NVARCHAR(MAX)
     */
    @NotNull
    @Size(max = 4000)
    @Schema(description = "type: NVARCHAR(MAX)", required = true)
    private String content;

    /**
     * type: BIT
     */
    @NotNull
    @Schema(description = "type: BIT", required = true)
    private Boolean isdelete;

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

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
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
        if (!(o instanceof TecContentDTO)) {
            return false;
        }

        TecContentDTO tecContentDTO = (TecContentDTO) o;
        if (this.contentId == null) {
            return false;
        }
        return Objects.equals(this.contentId, tecContentDTO.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.contentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecContentDTO{" +
            "contentId=" + getContentId() +
            ", pageId=" + getPageId() +
            ", contentTitle='" + getContentTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", isdelete='" + getIsdelete() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
