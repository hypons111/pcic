package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TecPage.
 */
@Entity
@Table(name = "tec_page")
public class TecPage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: BIGINT
     */
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "page_id", nullable = false)
    private Long pageId;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "menu", length = 20, nullable = false)
    private String menu;

    /**
     * type: NVARCHAR(20)
     */
    @Size(max = 20)
    @Column(name = "sub_menu", length = 20)
    private String subMenu;

    /**
     * type: NVARCHAR(50)
     */
    @Size(max = 50)
    @Column(name = "content_type", length = 50)
    private String contentType;

    /**
     * type: NVARCHAR(200)
     */
    @Size(max = 200)
    @Column(name = "page_title", length = 200)
    private String pageTitle;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "page_start", nullable = false)
    private Instant pageStart;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "page_end", nullable = false)
    private Instant pageEnd;

    /**
     * type: BIT
     */
    @NotNull
    @Column(name = "isdelete", nullable = false)
    private Boolean isdelete;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPageId() {
        return this.pageId;
    }

    public TecPage pageId(Long pageId) {
        this.setPageId(pageId);
        return this;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getMenu() {
        return this.menu;
    }

    public TecPage menu(String menu) {
        this.setMenu(menu);
        return this;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSubMenu() {
        return this.subMenu;
    }

    public TecPage subMenu(String subMenu) {
        this.setSubMenu(subMenu);
        return this;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getContentType() {
        return this.contentType;
    }

    public TecPage contentType(String contentType) {
        this.setContentType(contentType);
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public TecPage pageTitle(String pageTitle) {
        this.setPageTitle(pageTitle);
        return this;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Instant getPageStart() {
        return this.pageStart;
    }

    public TecPage pageStart(Instant pageStart) {
        this.setPageStart(pageStart);
        return this;
    }

    public void setPageStart(Instant pageStart) {
        this.pageStart = pageStart;
    }

    public Instant getPageEnd() {
        return this.pageEnd;
    }

    public TecPage pageEnd(Instant pageEnd) {
        this.setPageEnd(pageEnd);
        return this;
    }

    public void setPageEnd(Instant pageEnd) {
        this.pageEnd = pageEnd;
    }

    public Boolean getIsdelete() {
        return this.isdelete;
    }

    public TecPage isdelete(Boolean isdelete) {
        this.setIsdelete(isdelete);
        return this;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public TecPage updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public TecPage updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public TecPage createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public TecPage createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TecPage)) {
            return false;
        }
        return pageId != null && pageId.equals(((TecPage) o).pageId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecPage{" +
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
            "}";
    }
}
