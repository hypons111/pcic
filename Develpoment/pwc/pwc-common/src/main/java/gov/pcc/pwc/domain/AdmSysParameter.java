package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 需手動加複合主鍵(SysName、SysType)
 */
@Schema(description = "手動加複合主鍵(SysName、SysType)")
@IdClass(AdmSysParameterPK.class)
@Entity
@Table(name = "adm_sys_parameter")
public class AdmSysParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: varchar(20)
     */
    @Id
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "sys_name", length = 20, nullable = false)
    private String sysName;

    /**
     * type: varchar(20)
     */
    @Id
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "sys_type", length = 20, nullable = false)
    private String sysType;

    /**
     * type: varchar(50)
     */
    @NotNull
    @Size(max = 50)
    @Schema(description = "type: varchar(50)", required = true)
    @Column(name = "sys_value", length = 50, nullable = false)
    private String sysValue;

    /**
     * type: varchar(500)
     */
    @NotNull
    @Size(max = 500)
    @Schema(description = "type: varchar(500)", required = true)
    @Column(name = "descript", length = 500, nullable = false)
    private String descript;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    public String getId() { return this.sysName + this.sysType; }

    public String getSysName() {
        return this.sysName;
    }

    public AdmSysParameter sysName(String sysName) {
        this.sysName = sysName;
        return this;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysType() {
        return this.sysType;
    }

    public AdmSysParameter sysType(String sysType) {
        this.sysType = sysType;
        return this;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getSysValue() {
        return this.sysValue;
    }

    public AdmSysParameter sysValue(String sysValue) {
        this.sysValue = sysValue;
        return this;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public String getDescript() {
        return this.descript;
    }

    public AdmSysParameter descript(String descript) {
        this.descript = descript;
        return this;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getStatus() {
        return this.status;
    }

    public AdmSysParameter status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmSysParameter updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmSysParameter updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmSysParameter that = (AdmSysParameter) o;
        return sysName.equals(that.sysName) && sysType.equals(that.sysType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysName, sysType);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmSysParameter{" +
            ", sysName='" + getSysName() + "'" +
            ", sysType='" + getSysType() + "'" +
            ", sysValue='" + getSysValue() + "'" +
            ", descript='" + getDescript() + "'" +
            ", status='" + getStatus() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
