package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GeoCompany.
 */
@Entity
@Table(name = "geo_company")
public class GeoCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 32)
    @Id
    @Column(name = "geo_company_id", length = 32)
    private String geoCompanyId;

    @Size(max = 510)
    @Column(name = "company_name", length = 510)
    private String companyName;

    @Size(max = 100)
    @Column(name = "company_type", length = 100)
    private String companyType;

    @Size(max = 510)
    @Column(name = "company_id", length = 510)
    private String companyId;

    @Size(max = 510)
    @Column(name = "contact_person", length = 510)
    private String contactPerson;

    @Size(max = 1)
    @Column(name = "is_approve", length = 1)
    private String isApprove;

    @Size(max = 510)
    @Column(name = "work_tel", length = 510)
    private String workTel;

    @Size(max = 510)
    @Column(name = "email", length = 510)
    private String email;

    @Size(max = 510)
    @Column(name = "remark", length = 510)
    private String remark;

    @Column(name = "apply_date")
    private Instant applyDate;

    @Size(max = 510)
    @Column(name = "mobile_tel", length = 510)
    private String mobileTel;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 40)
    @Column(name = "update_user", length = 40)
    private String updateUser;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 40)
    @Column(name = "create_user", length = 40)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getGeoCompanyId() {
        return this.geoCompanyId;
    }

    public GeoCompany geoCompanyId(String geoCompanyId) {
        this.setGeoCompanyId(geoCompanyId);
        return this;
    }

    public void setGeoCompanyId(String geoCompanyId) {
        this.geoCompanyId = geoCompanyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public GeoCompany companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return this.companyType;
    }

    public GeoCompany companyType(String companyType) {
        this.setCompanyType(companyType);
        return this;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public GeoCompany companyId(String companyId) {
        this.setCompanyId(companyId);
        return this;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public GeoCompany contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getIsApprove() {
        return this.isApprove;
    }

    public GeoCompany isApprove(String isApprove) {
        this.setIsApprove(isApprove);
        return this;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getWorkTel() {
        return this.workTel;
    }

    public GeoCompany workTel(String workTel) {
        this.setWorkTel(workTel);
        return this;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getEmail() {
        return this.email;
    }

    public GeoCompany email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return this.remark;
    }

    public GeoCompany remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getApplyDate() {
        return this.applyDate;
    }

    public GeoCompany applyDate(Instant applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(Instant applyDate) {
        this.applyDate = applyDate;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public GeoCompany mobileTel(String mobileTel) {
        this.setMobileTel(mobileTel);
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public GeoCompany updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public GeoCompany updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public GeoCompany createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public GeoCompany createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public GeoCompany transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public GeoCompany transferTime(Instant transferTime) {
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
        if (!(o instanceof GeoCompany)) {
            return false;
        }
        return geoCompanyId != null && geoCompanyId.equals(((GeoCompany) o).geoCompanyId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoCompany{" +
            "geoCompanyId=" + getGeoCompanyId() +
            ", companyName='" + getCompanyName() + "'" +
            ", companyType='" + getCompanyType() + "'" +
            ", companyId='" + getCompanyId() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", isApprove='" + getIsApprove() + "'" +
            ", workTel='" + getWorkTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", remark='" + getRemark() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
