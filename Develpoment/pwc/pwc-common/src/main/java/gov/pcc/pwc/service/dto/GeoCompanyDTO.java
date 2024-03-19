package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoCompany} entity.
 */
public class GeoCompanyDTO implements Serializable {

    @Size(max = 32)
    private String geoCompanyId;

    @Size(max = 510)
    private String companyName;

    @Size(max = 100)
    private String companyType;

    @Size(max = 510)
    private String companyId;

    @Size(max = 510)
    private String contactPerson;

    @Size(max = 1)
    private String isApprove;

    @Size(max = 510)
    private String workTel;

    @Size(max = 510)
    private String email;

    @Size(max = 510)
    private String remark;

    private Instant applyDate;

    @Size(max = 510)
    private String mobileTel;

    private Instant updateTime;

    @Size(max = 40)
    private String updateUser;

    private Instant createTime;

    @Size(max = 40)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getGeoCompanyId() {
        return geoCompanyId;
    }

    public void setGeoCompanyId(String geoCompanyId) {
        this.geoCompanyId = geoCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Instant applyDate) {
        this.applyDate = applyDate;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
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
        if (!(o instanceof GeoCompanyDTO)) {
            return false;
        }

        GeoCompanyDTO geoCompanyDTO = (GeoCompanyDTO) o;
        if (this.geoCompanyId == null) {
            return false;
        }
        return Objects.equals(this.geoCompanyId, geoCompanyDTO.geoCompanyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.geoCompanyId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoCompanyDTO{" +
            "geoCompanyId='" + getGeoCompanyId() + "'" +
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
