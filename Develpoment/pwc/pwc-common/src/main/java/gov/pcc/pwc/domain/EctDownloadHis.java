package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EctDownloadHis.
 */
@Entity
@Table(name = "ect_download_his")
public class EctDownloadHis implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ect_download_his_id", nullable = false)
    private Long ectDownloadHisId;

    @NotNull
    @Column(name = "certificate_type", nullable = false)
    private Integer certificateType;

    @NotNull
    @Column(name = "ect_publish_id", nullable = false)
    private Long ectPublishId;

    @NotNull
    @Column(name = "certificate_no", nullable = false)
    private Long certificateNo;

    @Column(name = "last_download_time")
    private Instant lastDownloadTime;

    @Size(max = 50)
    @Column(name = "source_ip", length = 50)
    private String sourceIp;

    @NotNull
    @Size(max = 40)
    @Column(name = "create_user", length = 40, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @Size(max = 40)
    @Column(name = "update_user", length = 40)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEctDownloadHisId() {
        return this.ectDownloadHisId;
    }

    public EctDownloadHis ectDownloadHisId(Long ectDownloadHisId) {
        this.setEctDownloadHisId(ectDownloadHisId);
        return this;
    }

    public void setEctDownloadHisId(Long ectDownloadHisId) {
        this.ectDownloadHisId = ectDownloadHisId;
    }

    public Integer getCertificateType() {
        return this.certificateType;
    }

    public EctDownloadHis certificateType(Integer certificateType) {
        this.setCertificateType(certificateType);
        return this;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public Long getEctPublishId() {
        return this.ectPublishId;
    }

    public EctDownloadHis ectPublishId(Long ectPublishId) {
        this.setEctPublishId(ectPublishId);
        return this;
    }

    public void setEctPublishId(Long ectPublishId) {
        this.ectPublishId = ectPublishId;
    }

    public Long getCertificateNo() {
        return this.certificateNo;
    }

    public EctDownloadHis certificateNo(Long certificateNo) {
        this.setCertificateNo(certificateNo);
        return this;
    }

    public void setCertificateNo(Long certificateNo) {
        this.certificateNo = certificateNo;
    }

    public Instant getLastDownloadTime() {
        return this.lastDownloadTime;
    }

    public EctDownloadHis lastDownloadTime(Instant lastDownloadTime) {
        this.setLastDownloadTime(lastDownloadTime);
        return this;
    }

    public void setLastDownloadTime(Instant lastDownloadTime) {
        this.lastDownloadTime = lastDownloadTime;
    }

    public String getSourceIp() {
        return this.sourceIp;
    }

    public EctDownloadHis sourceIp(String sourceIp) {
        this.setSourceIp(sourceIp);
        return this;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EctDownloadHis createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EctDownloadHis createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EctDownloadHis updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EctDownloadHis updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EctDownloadHis)) {
            return false;
        }
        return ectDownloadHisId != null && ectDownloadHisId.equals(((EctDownloadHis) o).ectDownloadHisId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EctDownloadHis{" +
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
