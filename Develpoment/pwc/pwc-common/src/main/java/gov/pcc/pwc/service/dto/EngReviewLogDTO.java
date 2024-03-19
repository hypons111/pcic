package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngReviewLog} entity.
 */
public class EngReviewLogDTO implements Serializable {

    @Size(max = 20)
    private String engReviewLogId;

    @NotNull
    @Size(max = 20)
    private String externalId;

    private BigDecimal externalVersion;

    @NotNull
    @Size(max = 3)
    private String reviewCaseType;

    public enum reviewCaseType {
        ENGR_CERTIFICATE("1"), // 技師證書
        ENGR_LICENSE("2"),     // 技師執業執照
        CST_LICENSE("3"),      // 顧問公司許可登記
        ENGR_DISCIPLINE("4"),  // 技師懲戒
        COURSE("5"),           // 課程主檔
        ENGRCLASS_RECORD("6"), // 技師上課紀錄
        ENGR_SUBJECTPOINT("7"),// 技師書面積分審查
        COMP_CHECK("8"),       // 事務所/公司查核
        YEARREPORT("9");       // 年度業務報告

        private final String value;
        reviewCaseType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    @NotNull
    private Integer itemNo;

    @NotNull
    @Size(max = 3)
    private String reviewStatus;

    @NotNull
    @Size(max = 20)
    private String reviewStaff;

    @NotNull
    private Instant reviewTime;

    @Size(max = 2000)
    private String reviewDesc;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngReviewLogId() {
        return engReviewLogId;
    }

    public void setEngReviewLogId(String engReviewLogId) {
        this.engReviewLogId = engReviewLogId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getExternalVersion() {
        return externalVersion;
    }

    public void setExternalVersion(BigDecimal externalVersion) {
        this.externalVersion = externalVersion;
    }

    public String getReviewCaseType() {
        return reviewCaseType;
    }

    public void setReviewCaseType(String reviewCaseType) {
        this.reviewCaseType = reviewCaseType;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewStaff() {
        return reviewStaff;
    }

    public void setReviewStaff(String reviewStaff) {
        this.reviewStaff = reviewStaff;
    }

    public Instant getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Instant reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
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

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngReviewLogDTO)) {
            return false;
        }

        EngReviewLogDTO engReviewLogDTO = (EngReviewLogDTO) o;
        if (this.engReviewLogId == null) {
            return false;
        }
        return Objects.equals(this.engReviewLogId, engReviewLogDTO.engReviewLogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engReviewLogId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngReviewLogDTO{" +
            "engReviewLogId='" + getEngReviewLogId() + "'" +
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
