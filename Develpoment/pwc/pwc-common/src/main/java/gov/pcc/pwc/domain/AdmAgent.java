package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmAgent.
 */
@Entity
@Table(name = "adm_agent")
public class AdmAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "org_id", length = 20, nullable = false)
    private String orgId;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "pcc_org_id", length = 20, nullable = false)
    private String pccOrgId;

    /**
     * type: nvarchar(60)
     */
    @NotNull
    @Size(max = 60)
    @Schema(description = "type: nvarchar(60)", required = true)
    @Column(name = "org_name", length = 60, nullable = false)
    private String orgName;

    /**
     * type: varchar(500)
     */
    @Size(max = 500)
    @Schema(description = "type: varchar(500)")
    @Column(name = "org_name_en", length = 500)
    private String orgNameEn;

    /**
     * type: nvarchar(100)
     */
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)")
    @Column(name = "addr", length = 100)
    private String addr;

    /**
     * type: varchar(30)
     */
    @Size(max = 30)
    @Schema(description = "type: varchar(30)")
    @Column(name = "tel", length = 30)
    private String tel;

    /**
     * type: varchar(30)
     */
    @Size(max = 30)
    @Schema(description = "type: varchar(30)")
    @Column(name = "fax", length = 30)
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
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "admin_org_id", length = 20)
    private String adminOrgId;

    /**
     * type: decimal(1,0)
     */
    @DecimalMin(value = "0")
    @Schema(description = "type: decimal(1,0)")
    @Column(name = "org_level", precision = 21, scale = 2)
    private BigDecimal orgLevel;

    /**
     * type: varchar(1)
     */
    @Size(max = 1)
    @Schema(description = "type: varchar(1)")
    @Column(name = "cut_mark", length = 1)
    private String cutMark;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "old_org_id", length = 20)
    private String oldOrgId;

    /**
     * type: nvarchar(60)
     */
    @Size(max = 60)
    @Schema(description = "type: nvarchar(60)")
    @Column(name = "old_org_name", length = 60)
    private String oldOrgName;

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
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public AdmAgent orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getPccOrgId() {
        return this.pccOrgId;
    }

    public AdmAgent pccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
        return this;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public AdmAgent orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNameEn() {
        return this.orgNameEn;
    }

    public AdmAgent orgNameEn(String orgNameEn) {
        this.orgNameEn = orgNameEn;
        return this;
    }

    public void setOrgNameEn(String orgNameEn) {
        this.orgNameEn = orgNameEn;
    }

    public String getAddr() {
        return this.addr;
    }

    public AdmAgent addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return this.tel;
    }

    public AdmAgent tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public AdmAgent fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDescript() {
        return this.descript;
    }

    public AdmAgent descript(String descript) {
        this.descript = descript;
        return this;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getAdminOrgId() {
        return this.adminOrgId;
    }

    public AdmAgent adminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
        return this;
    }

    public void setAdminOrgId(String adminOrgId) {
        this.adminOrgId = adminOrgId;
    }

    public BigDecimal getOrgLevel() {
        return this.orgLevel;
    }

    public AdmAgent orgLevel(BigDecimal orgLevel) {
        this.orgLevel = orgLevel;
        return this;
    }

    public void setOrgLevel(BigDecimal orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getCutMark() {
        return this.cutMark;
    }

    public AdmAgent cutMark(String cutMark) {
        this.cutMark = cutMark;
        return this;
    }

    public void setCutMark(String cutMark) {
        this.cutMark = cutMark;
    }

    public String getOldOrgId() {
        return this.oldOrgId;
    }

    public AdmAgent oldOrgId(String oldOrgId) {
        this.oldOrgId = oldOrgId;
        return this;
    }

    public void setOldOrgId(String oldOrgId) {
        this.oldOrgId = oldOrgId;
    }

    public String getOldOrgName() {
        return this.oldOrgName;
    }

    public AdmAgent oldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName;
        return this;
    }

    public void setOldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmAgent updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmAgent updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmAgent)) {
            return false;
        }
        return orgId != null && orgId.equals(((AdmAgent) o).orgId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmAgent{" +
            "orgId=" + getOrgId() +
            ", pccOrgId='" + getPccOrgId() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgNameEn='" + getOrgNameEn() + "'" +
            ", addr='" + getAddr() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", descript='" + getDescript() + "'" +
            ", adminOrgId='" + getAdminOrgId() + "'" +
            ", orgLevel=" + getOrgLevel() +
            ", cutMark='" + getCutMark() + "'" +
            ", oldOrgId='" + getOldOrgId() + "'" +
            ", oldOrgName='" + getOldOrgName() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
