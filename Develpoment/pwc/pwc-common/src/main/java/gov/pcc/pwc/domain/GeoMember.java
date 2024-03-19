package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GeoMember.
 */
@Entity
@Table(name = "geo_member")
public class GeoMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 32)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private String geoMemberId;

    @Size(max = 510)
    @Column(name = "user_name", length = 510)
    private String userName;

    @Size(max = 510)
    @Column(name = "work_tel", length = 510)
    private String workTel;

    @Size(max = 510)
    @Column(name = "addr_zip", length = 510)
    private String addrZip;
    @Size(max = 510)
    @Column(name = "addr_city", length = 510)
    private String addrCity;
    @Size(max = 510)
    @Column(name = "addr_town", length = 510)
    private String addrTown;
    @Size(max = 510)
    @Column(name = "addr_other", length = 510)
    private String addrOther;

    @Size(max = 510)
    @Column(name = "idno", length = 510)
    private String idno;

    @Size(max = 510)
    @Column(name = "user_id", length = 510)
    private String userId;

    @Column(name = "apply_date")
    private Instant applyDate;

    @Size(max = 510)
    @Column(name = "job_demand", length = 510)
    private String jobDemand;

    @Size(max = 1)
    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Size(max = 510)
    @Column(name = "email", length = 510)
    private String email;

    @Size(max = 510)
    @Column(name = "home_tel", length = 510)
    private String homeTel;

    @Size(max = 510)
    @Column(name = "fax", length = 510)
    private String fax;

    @Size(max = 510)
    @Column(name = "mobile_tel", length = 510)
    private String mobileTel;

    @Size(max = 510)
    @Column(name = "examinee_degree", length = 510)
    private String examineeDegree;

    @Size(max = 510)
    @Column(name = "highest_degree", length = 510)
    private String highestDegree;

    @Column(name = "firstexam_date", length = 510)
    private LocalDate firstexamDate;

    @Size(max = 510)
    @Column(name = "firstexam_cert_no", length = 510)
    private String firstexamCertNo;

    @Size(max = 510)
    @Column(name = "firstexam_subject", length = 510)
    private String firstexamSubject;

    @Column(name = "is_approve")
    private String isApprove;

    @Column(name = "job_kind", length = 2)
    private String jobKind;

    @Size(max = 100)
    @Column(name = "job_type", length = 100)
    private String jobType;

    @Size(max = 100)
    @Column(name = "salary_range", length = 100)
    private String salaryRange;

    @Size(max = 510)
    @Column(name = "work_place", length = 510)
    private String workPlace;

    @Size(max = 510)
    @Column(name = "remark", length = 510)
    private String remark;

    @Column(name = "is_enable")
    private String isEnable;

    @Size(max = 100)
    @Column(name = "ranking", length = 100)
    private String ranking;

    @Size(max = 100)
    @Column(name = "serial_no", length = 100)
    private String serialNo;

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

    public GeoMember() {
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getGeoMemberId() {
        return geoMemberId;
    }

    public void setGeoMemberId(String geoMemberId) {
        this.geoMemberId = geoMemberId;
    }

    public GeoMember geoMemberId(String geoMemberId) {
        this.geoMemberId = geoMemberId;
        return this;
    }

    public String getUserName() {
        return this.userName;
    }

    public GeoMember userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkTel() {
        return this.workTel;
    }

    public GeoMember workTel(String workTel) {
        this.workTel = workTel;
        return this;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }




    public String getAddrZip() {
        return this.addrZip;
    }
    public GeoMember addrZip(String addrZip) {
        this.addrZip = addrZip;
        return this;
    }
    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }


    public String getAddrCity() {
        return this.addrCity;
    }
    public GeoMember addrCity(String addrCity) {
        this.addrCity = addrCity;
        return this;
    }
    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }



    public String getAddrTown() {
        return this.addrTown;
    }
    public GeoMember addrTown(String addrTown) {
        this.addrTown = addrTown;
        return this;
    }
    public void setAddrTown(String addrTown) {
        this.addrTown = addrTown;
    }


    public String getAddrOther() {
        return this.addrOther;
    }
    public GeoMember addrOther(String addrOther) {
        this.addrOther = addrOther;
        return this;
    }
    public void setAddrOther(String addrOther) {
        this.addrOther = addrOther;
    }



    public String getIdno() {
        return this.idno;
    }

    public GeoMember idn(String idn) {
        this.idno = idn;
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getUserId() {
        return this.userId;
    }

    public GeoMember userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getApplyDate() {
        return this.applyDate;
    }

    public GeoMember applyDate(Instant applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(Instant applyDate) {
        this.applyDate = applyDate;
    }

    public String getJobDemand() {
        return this.jobDemand;
    }

    public GeoMember jobDemand(String jobDemand) {
        this.jobDemand = jobDemand;
        return this;
    }

    public void setJobDemand(String jobDemand) {
        this.jobDemand = jobDemand;
    }

    public String getSex() {
        return this.sex;
    }

    public GeoMember sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public LocalDate getBirthday() {
        return this.birthday;
    }

    public GeoMember birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public GeoMember email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeTel() {
        return this.homeTel;
    }

    public GeoMember homeTel(String homeTel) {
        this.homeTel = homeTel;
        return this;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getFax() {
        return this.fax;
    }

    public GeoMember fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public GeoMember mobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getExamineeDegree() {
        return this.examineeDegree;
    }

    public GeoMember examineeDegree(String examineeDegree) {
        this.examineeDegree = examineeDegree;
        return this;
    }

    public void setExamineeDegree(String examineeDegree) {
        this.examineeDegree = examineeDegree;
    }

    public String getHighestDegree() {
        return this.highestDegree;
    }

    public GeoMember highestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
        return this;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public LocalDate getFirstexamDate() {
        return this.firstexamDate;
    }

    public GeoMember firstexamDate(LocalDate firstexamDate) {
        this.firstexamDate = firstexamDate;
        return this;
    }

    public void setFirstexamDate(LocalDate firstexamDate) {
        this.firstexamDate = firstexamDate;
    }

    public String getFirstexamCertNo() {
        return this.firstexamCertNo;
    }

    public GeoMember firstexamCertNo(String firstexamCertNo) {
        this.firstexamCertNo = firstexamCertNo;
        return this;
    }

    public void setFirstexamCertNo(String firstexamCertNo) {
        this.firstexamCertNo = firstexamCertNo;
    }

    public String getFirstexamSubject() {
        return this.firstexamSubject;
    }

    public GeoMember firstexamSubject(String firstexamSubject) {
        this.firstexamSubject = firstexamSubject;
        return this;
    }

    public void setFirstexamSubject(String firstexamSubject) {
        this.firstexamSubject = firstexamSubject;
    }

    public String getIsApprove() {
        return this.isApprove;
    }

    public GeoMember isApprove(String isApprove) {
        this.isApprove = isApprove;
        return this;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getJobType() {
        return this.jobType;
    }

    public GeoMember jobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobKind() {
        return this.jobKind;
    }

    public GeoMember jobKind(String jobKind) {
        this.jobKind = jobKind;
        return this;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    public String getSalaryRange() {
        return this.salaryRange;
    }

    public GeoMember salaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
        return this;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlace() {
        return this.workPlace;
    }

    public GeoMember workPlace(String workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getRemark() {
        return this.remark;
    }

    public GeoMember remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsEnable() {
        return this.isEnable;
    }

    public GeoMember isEnable(String isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getRanking() {
        return this.ranking;
    }

    public GeoMember ranking(String ranking) {
        this.ranking = ranking;
        return this;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public GeoMember serialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public GeoMember updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public GeoMember updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public GeoMember createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public GeoMember createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public GeoMember transferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public GeoMember transferTime(Instant transferTime) {
        this.transferTime = transferTime;
        return this;
    }


    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public String getApprove() {
        return isApprove;
    }

    public void setApprove(String approve) {
        isApprove = approve;
    }


    public String getEnable() {
        return isEnable;
    }

    public void setEnable(String enable) {
        isEnable = enable;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoMember)) {
            return false;
        }
        return geoMemberId != null && geoMemberId.equals(((GeoMember) o).geoMemberId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoMember{" +
            "geoMemberId=" + getGeoMemberId() +
            ", userName='" + getUserName() + "'" +
            ", workTel='" + getWorkTel() + "'" +
            ", addrZip='" + getAddrZip() + "'" +
            ", addrCity='" + getAddrCity() + "'" +
            ", addrTown='" + getAddrTown() + "'" +
            ", addrOther='" + getAddrOther() + "'" +
            ", idno='" + getIdno() + "'" +
            ", userId='" + getUserId() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", jobDemand='" + getJobDemand() + "'" +
            ", sex='" + getSex() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", email='" + getEmail() + "'" +
            ", homeTel='" + getHomeTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", examineeDegree='" + getExamineeDegree() + "'" +
            ", highestDegree='" + getHighestDegree() + "'" +
            ", firstexamDate='" + getFirstexamDate() + "'" +
            ", firstexamCertNo='" + getFirstexamCertNo() + "'" +
            ", firstexamSubject='" + getFirstexamSubject() + "'" +
            ", isApprove='" + getIsApprove() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", salaryRange='" + getSalaryRange() + "'" +
            ", workPlace='" + getWorkPlace() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isEnable='" + getIsEnable() + "'" +
            ", ranking='" + getRanking() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
