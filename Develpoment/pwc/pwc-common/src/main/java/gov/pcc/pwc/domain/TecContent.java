package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * TEC
 */
@Entity
@Table(name = "tec_content")
public class TecContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: BIGINT
     */
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "content_id", nullable = false)
    private Long contentId;

    /**
     * type: BIGINT
     */
    @NotNull
    @Column(name = "page_id", nullable = false)
    private Long pageId;

    /**
     * type: NVARCHAR(200)
     */
    @Size(max = 200)
    @Column(name = "content_title", length = 200)
    private String contentTitle;

    /**
     * type: NVARCHAR(MAX)
     */
    @NotNull
    @Size(max = 4000)
    @Column(name = "content", length = 4000, nullable = false)
    private String content;

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

    public Long getContentId() {
        return this.contentId;
    }

    public TecContent contentId(Long contentId) {
        this.setContentId(contentId);
        return this;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getPageId() {
        return this.pageId;
    }

    public TecContent pageId(Long pageId) {
        this.setPageId(pageId);
        return this;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getContentTitle() {
        return this.contentTitle;
    }

    public TecContent contentTitle(String contentTitle) {
        this.setContentTitle(contentTitle);
        return this;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return this.content;
    }

    public TecContent content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsdelete() {
        return this.isdelete;
    }

    public TecContent isdelete(Boolean isdelete) {
        this.setIsdelete(isdelete);
        return this;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public TecContent updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public TecContent updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public TecContent createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public TecContent createTime(Instant createTime) {
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
        if (!(o instanceof TecContent)) {
            return false;
        }
        return contentId != null && contentId.equals(((TecContent) o).contentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecContent{" +
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
