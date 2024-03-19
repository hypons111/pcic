package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.TecPage} entity.
 */
public class TecPageDTO implements Serializable {

    /**
     * type: BIGINT
     */
    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long pageId;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String menu;

    /**
     * type: NVARCHAR(20)
     */
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)")
    private String subMenu;

    /**
     * type: NVARCHAR(50)
     */
    @Size(max = 50)
    @Schema(description = "type: NVARCHAR(50)")
    private String contentType;

    /**
     * type: NVARCHAR(200)
     */
    @Size(max = 200)
    @Schema(description = "type: NVARCHAR(200)")
    private String pageTitle;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant pageStart;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant pageEnd;

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

    //加入TecContent.CONTENT
    @NotNull
    @Size(max = 4000)
    @Schema(description = "type: NVARCHAR(MAX)", required = true)
    private String content;




    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Instant getPageStart() {
        return pageStart;
    }

    public void setPageStart(Instant pageStart) {
        this.pageStart = pageStart;
    }

    public Instant getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(Instant pageEnd) {
        this.pageEnd = pageEnd;
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

    //加入content的getter/setter
    public String getContent() { return content; }

    public void setContent() { this.content = content; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TecPageDTO)) {
            return false;
        }

        TecPageDTO tecPageDTO = (TecPageDTO) o;
        if (this.pageId == null) {
            return false;
        }
        return Objects.equals(this.pageId, tecPageDTO.pageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pageId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecPageDTO{" +
            "pageId=" + getPageId() +
            ", menu='" + getMenu() + "'" +
            ", subMenu='" + getSubMenu() + "'" +
            ", contentType='" + getContentType() + "'" +
            ", pageTitle='" + getPageTitle() + "'" +
            ", pageStart='" + getPageStart() + "'" +
            ", pageEnd='" + getPageEnd() + "'" +
            ", isdelete='" + getIsdelete() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", content='" + getContent()+ "'" +
            "}";
    }
}
