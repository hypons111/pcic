package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmCertRecord.
 */
@Entity
@Table(name = "adm_cert_record")
public class AdmCertRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_id", length = 20, nullable = false)
    private String functionId;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "cert_category", length = 10, nullable = false)
    private String certCategory;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "cert_sn", length = 20, nullable = false)
    private String certSn;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "sign_ops", length = 100, nullable = false)
    private String signOps;

    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "enc_ops", length = 100, nullable = false)
    private String encOps;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "create_user_sys", length = 20, nullable = false)
    private String createUserSys;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmCertRecord id(Long id) {
        this.id = id;
        return this;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public AdmCertRecord functionId(String functionId) {
        this.functionId = functionId;
        return this;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getCertCategory() {
        return this.certCategory;
    }

    public AdmCertRecord certCategory(String certCategory) {
        this.certCategory = certCategory;
        return this;
    }

    public void setCertCategory(String certCategory) {
        this.certCategory = certCategory;
    }

    public String getCertSn() {
        return this.certSn;
    }

    public AdmCertRecord certSn(String certSn) {
        this.certSn = certSn;
        return this;
    }

    public void setCertSn(String certSn) {
        this.certSn = certSn;
    }

    public String getSignOps() {
        return this.signOps;
    }

    public AdmCertRecord signOps(String signOps) {
        this.signOps = signOps;
        return this;
    }

    public void setSignOps(String signOps) {
        this.signOps = signOps;
    }

    public String getEncOps() {
        return this.encOps;
    }

    public AdmCertRecord encOps(String encOps) {
        this.encOps = encOps;
        return this;
    }

    public void setEncOps(String encOps) {
        this.encOps = encOps;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmCertRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserSys() {
        return this.createUserSys;
    }

    public AdmCertRecord createUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
        return this;
    }

    public void setCreateUserSys(String createUserSys) {
        this.createUserSys = createUserSys;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmCertRecord)) {
            return false;
        }
        return id != null && id.equals(((AdmCertRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmCertRecord{" +
            "id=" + getId() +
            ", functionId='" + getFunctionId() + "'" +
            ", certCategory='" + getCertCategory() + "'" +
            ", certSn='" + getCertSn() + "'" +
            ", signOps='" + getSignOps() + "'" +
            ", encOps='" + getEncOps() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUserSys='" + getCreateUserSys() + "'" +
            "}";
    }
}
