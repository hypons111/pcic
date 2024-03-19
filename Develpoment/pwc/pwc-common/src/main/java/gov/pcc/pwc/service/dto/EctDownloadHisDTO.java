package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctDownloadHis} entity.
 */
public class EctDownloadHisDTO implements Serializable {

    @NotNull
    private Long ectDownloadHisId;

    @NotNull
    private Integer certificateType;

    @NotNull
    private Long ectPublishId;

    @NotNull
    private Long certificateNo;

    private Instant lastDownloadTime;

    @Size(max = 50)
    private String sourceIp;

    @NotNull
    @Size(max = 40)
    private String createUser;

    @NotNull
    private Instant createTime;

    @Size(max = 40)
    private String updateUser;

    private Instant updateTime;

    public Long getEctDownloadHisId() {
        return ectDownloadHisId;
    }

    public void setEctDownloadHisId(Long ectDownloadHisId) {
        this.ectDownloadHisId = ectDownloadHisId;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Long getEctPublishId() {
        return ectPublishId;
    }

    public void setEctPublishId(Long ectPublishId) {
        this.ectPublishId = ectPublishId;
    }

    public Long getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(Long certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Instant getLastDownloadTime() {
        return lastDownloadTime;
    }

    public void setLastDownloadTime(Instant lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EctDownloadHisDTO)) {
            return false;
        }

        EctDownloadHisDTO ectDownloadHisDTO = (EctDownloadHisDTO) o;
        if (this.ectDownloadHisId == null) {
            return false;
        }
        return Objects.equals(this.ectDownloadHisId, ectDownloadHisDTO.ectDownloadHisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ectDownloadHisId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctDownloadHisDTO{" +
            "ectDownloadHisId=" + getEctDownloadHisId() +
            ", certificateType=" + getCertificateType() +
            ", ectPublishId=" + getEctPublishId() +
            ", certificateNo=" + getCertificateNo() +
            ", lastDownloadTime='" + getLastDownloadTime() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
