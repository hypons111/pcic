package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngReviewLog.
 */
@Entity
@Table(name = "eng_review_log")
public class EngReviewLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_review_log_id", length = 20, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private String engReviewLogId;

    @NotNull
    @Size(max = 20)
    @Column(name = "external_id", length = 20, nullable = false)
    private String externalId;

    @Column(name = "external_version", precision = 3, scale = 1)
    private BigDecimal externalVersion;

    @NotNull
    @Size(max = 3)
    @Column(name = "review_case_type", length = 3, nullable = false)
    private String reviewCaseType;

    @NotNull
    @Column(name = "item_no", nullable = false)
    private Integer itemNo;

    @NotNull
    @Size(max = 3)
    @Column(name = "review_status", length = 3, nullable = false)
    private String reviewStatus;

    @NotNull
    @Size(max = 20)
    @Column(name = "review_staff", length = 20, nullable = false)
    private String reviewStaff;

    @NotNull
    @Column(name = "review_time", nullable = false)
    private Instant reviewTime;

    @Size(max = 2000)
    @Column(name = "review_desc", length = 2000)
    private String reviewDesc;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngReviewLogId() {
        return this.engReviewLogId;
    }

    public EngReviewLog engReviewLogId(String engReviewLogId) {
        this.setEngReviewLogId(engReviewLogId);
        return this;
    }

    public void setEngReviewLogId(String engReviewLogId) {
        this.engReviewLogId = engReviewLogId;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public EngReviewLog externalId(String externalId) {
        this.setExternalId(externalId);
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getExternalVersion() {
        return this.externalVersion;
    }

    public EngReviewLog externalVersion(BigDecimal externalVersion) {
        this.setExternalVersion(externalVersion);
        return this;
    }

    public void setExternalVersion(BigDecimal externalVersion) {
        this.externalVersion = externalVersion;
    }

    public String getReviewCaseType() {
        return this.reviewCaseType;
    }

    public EngReviewLog reviewCaseType(String reviewCaseType) {
        this.setReviewCaseType(reviewCaseType);
        return this;
    }

    public void setReviewCaseType(String reviewCaseType) {
        this.reviewCaseType = reviewCaseType;
    }

    public Integer getItemNo() {
        return this.itemNo;
    }

    public EngReviewLog itemNo(Integer itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getReviewStatus() {
        return this.reviewStatus;
    }

    public EngReviewLog reviewStatus(String reviewStatus) {
        this.setReviewStatus(reviewStatus);
        return this;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewStaff() {
        return this.reviewStaff;
    }

    public EngReviewLog reviewStaff(String reviewStaff) {
        this.setReviewStaff(reviewStaff);
        return this;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return this.reviewTime;
    }

    public EngReviewLog reviewTime(Instant reviewTime) {
        this.setReviewTime(reviewTime);
        return this;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return this.reviewDesc;
    }

    public EngReviewLog reviewDesc(String reviewDesc) {
        this.setReviewDesc(reviewDesc);
        return this;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngReviewLog updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngReviewLog updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngReviewLog createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngReviewLog createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngReviewLog transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngReviewLog transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngReviewLog)) {
            return false;
        }
        return engReviewLogId != null && engReviewLogId.equals(((EngReviewLog) o).engReviewLogId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngReviewLog{" +
            "engReviewLogId=" + getEngReviewLogId() +
            ", externalId='" + getExternalId() + "'" +
            ", externalVersion=" + getExternalVersion() +
            ", reviewCaseType='" + getReviewCaseType() + "'" +
            ", itemNo=" + getItemNo() +
            ", reviewStatus='" + getReviewStatus() + "'" +
            ", reviewStaff='" + getReviewStaff() + "'" +
            ", reviewTime='" + getReviewTime() + "'" +
            ", reviewDesc='" + getReviewDesc() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
