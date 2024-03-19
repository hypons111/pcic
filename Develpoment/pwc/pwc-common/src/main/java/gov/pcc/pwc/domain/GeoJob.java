package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GeoJob.
 */
@Entity
@Table(name = "geo_job")
public class GeoJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "geo_job_id")
    private Long geoJobId;

    @Size(max = 20)
    @Column(name = "salary_range", length = 20)
    private String salaryRange;

    @Size(max = 510)
    @Column(name = "work_place_list", length = 510)
    private String workPlaceList;

    @Size(max = 510)
    @Column(name = "work_place_desc", length = 510)
    private String workPlaceDesc;

    @Size(max = 510)
    @Column(name = "other_comment", length = 510)
    private String otherComment;

    @Column(name = "publish_date_start")
    private Instant publishDateStart;

    @Column(name = "publish_date_end")
    private Instant publishDateEnd;

    @Size(max = 1)
    @Column(name = "publish_status", length = 1)
    private String publishStatus;

    @Size(max = 510)
    @Column(name = "job_title", length = 510)
    private String jobTitle;

    @Size(max = 1)
    @Column(name = "is_approve", length = 1)
    private String isApprove;

    @Size(max = 100)
    @Column(name = "job_type", length = 100)
    private String jobType;

    @Size(max = 100)
    @Column(name = "type1_list", length = 100)
    private String type1List;

    @Size(max = 100)
    @Column(name = "type2_list", length = 100)
    private String type2List;

    @Size(max = 100)
    @Column(name = "type3_list", length = 100)
    private String type3List;

    @Column(name = "person_count", precision = 21, scale = 2)
    private BigDecimal personCount;

    @Size(max = 510)
    @Column(name = "reject_reason", length = 510)
    private String rejectReason;

    @Size(max = 1)
    @Column(name = "is_public", length = 1)
    private String isPublic;

    @Size(max = 510)
    @Column(name = "mobile_tel", length = 510)
    private String mobileTel;

    @Size(max = 510)
    @Column(name = "contact_tel", length = 510)
    private String contactTel;

    @Size(max = 510)
    @Column(name = "contact_person", length = 510)
    private String contactPerson;

    @Size(max = 40)
    @Column(name = "create_user", length = 40)
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    @Size(max = 40)
    @Column(name = "update_user", length = 40)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "transfer_date")
    private Instant transferDate;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Size(max = 2)
    @Column(name = "job_kind", length = 2)
    private String jobKind;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GeoJob id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGeoJobId() {
        return this.geoJobId;
    }

    public GeoJob geoJobId(Long geoJobId) {
        this.setGeoJobId(geoJobId);
        return this;
    }

    public void setGeoJobId(Long geoJobId) {
        this.geoJobId = geoJobId;
    }

    public String getSalaryRange() {
        return this.salaryRange;
    }

    public GeoJob salaryRange(String salaryRange) {
        this.setSalaryRange(salaryRange);
        return this;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlaceList() {
        return this.workPlaceList;
    }

    public GeoJob workPlaceList(String workPlaceList) {
        this.setWorkPlaceList(workPlaceList);
        return this;
    }

    public void setWorkPlaceList(String workPlaceList) {
        this.workPlaceList = workPlaceList;
    }

    public String getWorkPlaceDesc() {
        return this.workPlaceDesc;
    }

    public GeoJob workPlaceDesc(String workPlaceDesc) {
        this.setWorkPlaceDesc(workPlaceDesc);
        return this;
    }

    public void setWorkPlaceDesc(String workPlaceDesc) {
        this.workPlaceDesc = workPlaceDesc;
    }

    public String getOtherComment() {
        return this.otherComment;
    }

    public GeoJob otherComment(String otherComment) {
        this.setOtherComment(otherComment);
        return this;
    }

    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment;
    }

    public Instant getPublishDateStart() {
        return this.publishDateStart;
    }

    public GeoJob publishDateStart(Instant publishDateStart) {
        this.setPublishDateStart(publishDateStart);
        return this;
    }

    public void setPublishDateStart(Instant publishDateStart) {
        this.publishDateStart = publishDateStart;
    }

    public Instant getPublishDateEnd() {
        return this.publishDateEnd;
    }

    public GeoJob publishDateEnd(Instant publishDateEnd) {
        this.setPublishDateEnd(publishDateEnd);
        return this;
    }

    public void setPublishDateEnd(Instant publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public String getPublishStatus() {
        return this.publishStatus;
    }

    public GeoJob publishStatus(String publishStatus) {
        this.setPublishStatus(publishStatus);
        return this;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public GeoJob jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIsApprove() {
        return this.isApprove;
    }

    public GeoJob isApprove(String isApprove) {
        this.setIsApprove(isApprove);
        return this;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getJobType() {
        return this.jobType;
    }

    public GeoJob jobType(String jobType) {
        this.setJobType(jobType);
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getType1List() {
        return this.type1List;
    }

    public GeoJob type1List(String type1List) {
        this.setType1List(type1List);
        return this;
    }

    public void setType1List(String type1List) {
        this.type1List = type1List;
    }

    public String getType2List() {
        return this.type2List;
    }

    public GeoJob type2List(String type2List) {
        this.setType2List(type2List);
        return this;
    }

    public void setType2List(String type2List) {
        this.type2List = type2List;
    }

    public String getType3List() {
        return this.type3List;
    }

    public GeoJob type3List(String type3List) {
        this.setType3List(type3List);
        return this;
    }

    public void setType3List(String type3List) {
        this.type3List = type3List;
    }

    public BigDecimal getPersonCount() {
        return this.personCount;
    }

    public GeoJob personCount(BigDecimal personCount) {
        this.setPersonCount(personCount);
        return this;
    }

    public void setPersonCount(BigDecimal personCount) {
        this.personCount = personCount;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public GeoJob rejectReason(String rejectReason) {
        this.setRejectReason(rejectReason);
        return this;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getIsPublic() {
        return this.isPublic;
    }

    public GeoJob isPublic(String isPublic) {
        this.setIsPublic(isPublic);
        return this;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public GeoJob mobileTel(String mobileTel) {
        this.setMobileTel(mobileTel);
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public GeoJob contactTel(String contactTel) {
        this.setContactTel(contactTel);
        return this;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public GeoJob contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public GeoJob createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public GeoJob createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public GeoJob updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public GeoJob updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getTransferDate() {
        return this.transferDate;
    }

    public GeoJob transferDate(Instant transferDate) {
        this.setTransferDate(transferDate);
        return this;
    }

    public void setTransferDate(Instant transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public GeoJob transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getJobKind() {
        return this.jobKind;
    }

    public GeoJob jobKind(String jobKind) {
        this.setJobKind(jobKind);
        return this;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoJob)) {
            return false;
        }
        return id != null && id.equals(((GeoJob) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoJob{" +
            "id=" + getId() +
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
