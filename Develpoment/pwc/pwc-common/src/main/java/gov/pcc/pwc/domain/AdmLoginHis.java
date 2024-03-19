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
 * A AdmLoginHis.
 */
@Entity
@Table(name = "adm_login_his")
public class AdmLoginHis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "sys_category", length = 10, nullable = false)
    private String sysCategory;

    /**
     * type: varchar(MAX)
     */
    @NotNull
    @Schema(description = "type: varchar(MAX)", required = true)
    @Column(name = "jwt_token", nullable = false)
    private String jwtToken;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "result", length = 1, nullable = false)
    private String result;

    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "descript", length = 100, nullable = false)
    private String descript;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "source_ip", length = 100, nullable = false)
    private String sourceIp;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "browser", length = 100, nullable = false)
    private String browser;

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

    public AdmLoginHis id(Long id) {
        this.id = id;
        return this;
    }

    public String getSysCategory() {
        return this.sysCategory;
    }

    public AdmLoginHis sysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
        return this;
    }

    public void setSysCategory(String sysCategory) {
        this.sysCategory = sysCategory;
    }

    public String getResult() {
        return this.result;
    }

    public AdmLoginHis result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public AdmLoginHis jwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getDescript() {
        return this.descript;
    }

    public AdmLoginHis descript(String descript) {
        this.descript = descript;
        return this;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getSourceIp() {
        return this.sourceIp;
    }

    public AdmLoginHis sourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getBrowser() {
        return this.browser;
    }

    public AdmLoginHis browser(String browser) {
        this.browser = browser;
        return this;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmLoginHis createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserSys() {
        return this.createUserSys;
    }

    public AdmLoginHis createUserSys(String createUserSys) {
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
        if (!(o instanceof AdmLoginHis)) {
            return false;
        }
        return id != null && id.equals(((AdmLoginHis) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmLoginHis{" +
            "id=" + getId() +
            ", sysCategory='" + getSysCategory() + "'" +
            ", jwtToken='" + getJwtToken() + "'" +
            ", result='" + getResult() + "'" +
            ", descript='" + getDescript() + "'" +
            ", sourceIp='" + getSourceIp() + "'" +
            ", browser='" + getBrowser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUserSys='" + getCreateUserSys() + "'" +
            "}";
    }
}
