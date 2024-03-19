package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoJob} entity.
 */
public class GeoJobDTO implements Serializable {


    @Size(max = 510)
    private String companyName;

    private String email;


    private Long id;

    private Long geoJobId;

    @Size(max = 20)
    private String salaryRange;

    @Size(max = 510)
    private String workPlaceList;

    @Size(max = 510)
    private String workPlaceDesc;

    @Size(max = 510)
    private String otherComment;

    private Instant publishDateStart;

    private Instant publishDateEnd;

    @Size(max = 1)
    private String publishStatus;

    @Size(max = 510)
    private String jobTitle;

    @Size(max = 1)
    private String isApprove;

    @Size(max = 100)
    private String jobType;

    @Size(max = 100)
    private String type1List;

    @Size(max = 100)
    private String type2List;

    @Size(max = 100)
    private String type3List;

    private BigDecimal personCount;

    @Size(max = 510)
    private String rejectReason;

    @Size(max = 1)
    private String isPublic;

    @Size(max = 510)
    private String mobileTel;

    @Size(max = 510)
    private String contactTel;

    @Size(max = 510)
    private String contactPerson;

    @Size(max = 40)
    private String createUser;

    private Instant createTime;

    @Size(max = 40)
    private String updateUser;

    private Instant updateTime;

    private Instant transferDate;

    @Size(max = 100)
    private String transferFrom;

    @Size(max = 2)
    private String jobKind;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGeoJobId() {
        return geoJobId;
    }

    public void setGeoJobId(Long geoJobId) {
        this.geoJobId = geoJobId;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlaceList() {
        return workPlaceList;
    }

    public void setWorkPlaceList(String workPlaceList) {
        this.workPlaceList = workPlaceList;
    }

    public String getWorkPlaceDesc() {
        return workPlaceDesc;
    }

    public void setWorkPlaceDesc(String workPlaceDesc) {
        this.workPlaceDesc = workPlaceDesc;
    }

    public String getOtherComment() {
        return otherComment;
    }

    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment;
    }

    public Instant getPublishDateStart() {
        return publishDateStart;
    }

    public void setPublishDateStart(Instant publishDateStart) {
        this.publishDateStart = publishDateStart;
    }

    public Instant getPublishDateEnd() {
        return publishDateEnd;
    }

    public void setPublishDateEnd(Instant publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getType1List() {
        return type1List;
    }

    public void setType1List(String type1List) {
        this.type1List = type1List;
    }

    public String getType2List() {
        return type2List;
    }

    public void setType2List(String type2List) {
        this.type2List = type2List;
    }

    public String getType3List() {
        return type3List;
    }

    public void setType3List(String type3List) {
        this.type3List = type3List;
    }

    public BigDecimal getPersonCount() {
        return personCount;
    }

    public void setPersonCount(BigDecimal personCount) {
        this.personCount = personCount;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public Instant getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoJobDTO)) {
            return false;
        }

        GeoJobDTO geoJobDTO = (GeoJobDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, geoJobDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoJobDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", email='" + getEmail() + "'" +
            ", geoJobId=" + getGeoJobId() +
            ", salaryRange='" + getSalaryRange() + "'" +
            ", workPlaceList='" + getWorkPlaceList() + "'" +
            ", workPlaceDesc='" + getWorkPlaceDesc() + "'" +
            ", otherComment='" + getOtherComment() + "'" +
            ", publishDateStart='" + getPublishDateStart() + "'" +
            ", publishDateEnd='" + getPublishDateEnd() + "'" +
            ", publishStatus='" + getPublishStatus() + "'" +
            ", jobTitle='" + getJobTitle() + "'" +
            ", isApprove='" + getIsApprove() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", type1List='" + getType1List() + "'" +
            ", type2List='" + getType2List() + "'" +
            ", type3List='" + getType3List() + "'" +
            ", personCount=" + getPersonCount() +
            ", rejectReason='" + getRejectReason() + "'" +
            ", isPublic='" + getIsPublic() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", contactTel='" + getContactTel() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", transferDate='" + getTransferDate() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", jobKind='" + getJobKind() + "'" +
            "}";
    }
}
