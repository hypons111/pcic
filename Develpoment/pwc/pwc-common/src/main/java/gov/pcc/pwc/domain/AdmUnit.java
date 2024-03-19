package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmUnit.
 */
@Entity
@Table(name = "adm_unit")
public class AdmUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String unitId;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "master_unit_id", length = 20)
    private String masterUnitId;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "admin_org_id", length = 20)
    private String adminOrgId;

    /**
     * type: nvarchar(60)
     */
    @Size(max = 60)
    @Schema(description = "type: nvarchar(60)")
    @Column(name = "unit_name", length = 60)
    private String unitName;

    /**
     * type: nvarchar(500)
     */
    @Size(max = 500)
    @Schema(description = "type: nvarchar(500)")
    @Column(name = "addr", length = 500)
    private String addr;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "tel", length = 20)
    private String tel;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "fax", length = 20)
    private String fax;

    /**
     * type: nvarchar(500)
     */
    @Size(max = 500)
    @Schema(description = "type: nvarchar(500)")
    @Column(name = "descript", length = 500)
    private String descript;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public AdmUnit unitId(String unitId) {
        this.unitId = unitId;
        return this;
    }

    public String getMasterUnitId() {
        return this.masterUnitId;
    }

    public AdmUnit masterUnitId(String masterUnitId) {
        this.masterUnitId = masterUnitId;
        return this;
    }

    public void setMasterUnitId(String masterUnitId) {
        this.masterUnitId = masterUnitId;
    }

    public String getAdminOrgId() {
        return this.adminOrgId;
    }

    public AdmUnit adminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
        return this;
    }

    public void setAdminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public AdmUnit unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAddr() {
        return this.addr;
    }

    public AdmUnit addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return this.tel;
    }

    public AdmUnit tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public AdmUnit fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDescript() {
        return this.descript;
    }

    public AdmUnit descript(String descript) {
        this.descript = descript;
        return this;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmUnit updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmUnit updateTime(Instant updateTime) {
        this.updateTime = updateTime;
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
        if (!(o instanceof AdmUnit)) {
            return false;
        }
        return unitId != null && unitId.equals(((AdmUnit) o).unitId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmUnit{" +
            "unitId=" + getUnitId() +
            ", masterUnitId='" + getMasterUnitId() + "'" +
            ", adminOrgId='" + getAdminOrgId() + "'" +
            ", unitName='" + getUnitName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", descript='" + getDescript() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
