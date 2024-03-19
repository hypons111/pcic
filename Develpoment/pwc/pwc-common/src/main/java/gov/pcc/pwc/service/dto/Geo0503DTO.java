package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoMember} entity.
 */
public class Geo0503DTO implements Serializable {

    //    @NotNull
    private Long geoMemberId;

    @Size(max = 50)
    @Schema(description = "type: NVARCHAR(50)")
    private String chName;

    @Size(max = 1)
    @Schema(description = "type: VARCHAR(1)")
    private String sex;

    @Schema(description = "type: DATE")
    private Instant birthday;

    @Size(max = 10)
    @Schema(description = "type: VARCHAR(10)")
    private String homeAddrZip;

    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String homeAddrCity;

    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String homeAddrTown;

    @Size(max = 100)
    @Schema(description = "type: NVARCHAR(100)")
    private String homeAddrOther;

    @Size(max = 100)
    @Schema(description = "type: VARCHAR(100)", required = true)
    private String email;

    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String workTel;


    @Size(max = 510)
    private String fax;

    @Size(max = 15)
    @Schema(description = "type: VARCHAR(15)")
    private String mobileTel;

    @Size(max = 510)
    private String examineeDegree;

    @Size(max = 510)
    private String highestDegree;

    private LocalDate firstexamDate;

    @Size(max = 510)
    private String firstexamCertNo;

    @Size(max = 510)
    private String idno;

    private String isEnable;

    @Size(max = 100)
    private String jobType;

    @Size(max = 2)
    private String jobKind;

    @Size(max = 100)
    private String salaryRange;

    @Size(max = 510)
    private String workPlace;

    @Size(max = 510)
    private String jobDemand;

    @Size(max = 510)
    private String remark;


    public Long getGeoMemberId() {
        return geoMemberId;
    }

    public void setGeoMemberId(Long geoMemberId) {
        this.geoMemberId = geoMemberId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getHomeAddrZip() {
        return homeAddrZip;
    }

    public void setHomeAddrZip(String homeAddrZip) {
        this.homeAddrZip = homeAddrZip;
    }

    public String getHomeAddrCity() {
        return homeAddrCity;
    }

    public void setHomeAddrCity(String homeAddrCity) {
        this.homeAddrCity = homeAddrCity;
    }

    public String getHomeAddrTown() {
        return homeAddrTown;
    }

    public void setHomeAddrTown(String homeAddrTown) {
        this.homeAddrTown = homeAddrTown;
    }

    public String getHomeAddrOther() {
        return homeAddrOther;
    }

    public void setHomeAddrOther(String homeAddrOther) {
        this.homeAddrOther = homeAddrOther;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }



    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getExamineeDegree() {
        return examineeDegree;
    }

    public void setExamineeDegree(String examineeDegree) {
        this.examineeDegree = examineeDegree;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public LocalDate getFirstexamDate() {
        return firstexamDate;
    }

    public void setFirstexamDate(LocalDate firstexamDate) {
        this.firstexamDate = firstexamDate;
    }

    public String getFirstexamCertNo() {
        return firstexamCertNo;
    }

    public void setFirstexamCertNo(String firstexamCertNo) {
        this.firstexamCertNo = firstexamCertNo;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobKind() {
        return jobKind;
    }

    public void setJobKind(String jobKind) {
        this.jobKind = jobKind;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobDemand() {
        return jobDemand;
    }

    public void setJobDemand(String jobDemand) {
        this.jobDemand = jobDemand;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geo0503DTO)) {
            return false;
        }

        Geo0503DTO geo0503DTO = (Geo0503DTO) o;
        if (this.geoMemberId == null) {
            return false;
        }
        return Objects.equals(this.geoMemberId, geo0503DTO.geoMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.geoMemberId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoMemberDTO{" +
                "geoMemberId=" + getGeoMemberId() +
                ", chName='" + getChName() + "'" +
                ", homeAddrZip='" + getHomeAddrZip() + "'" +
                ", homeAddrCity='" + getHomeAddrCity() + "'" +
                ", homeAddrTown='" + getHomeAddrTown() + "'" +
                ", homeAddrOther='" + getHomeAddrOther() + "'" +
                ", idno='" + getIdno() + "'" +
                ", jobDemand='" + getJobDemand() + "'" +
                ", sex='" + getSex() + "'" +
                ", birthday='" + getBirthday() + "'" +
                ", email='" + getEmail() + "'" +
                ", workTel='" + getWorkTel() + "'" +
                ", mobileTel='" + getMobileTel() + "'" +
                ", fax='" + getFax() + "'" +
                ", examineeDegree='" + getExamineeDegree() + "'" +
                ", highestDegree='" + getHighestDegree() + "'" +
                ", firstexamDate='" + getFirstexamDate() + "'" +
                ", firstexamCertNo='" + getFirstexamCertNo() + "'" +
                ", jobType='" + getJobType() + "'" +
                ", salaryRange='" + getSalaryRange() + "'" +
                ", workPlace='" + getWorkPlace() + "'" +
                ", remark='" + getRemark() + "'" +
                ", isEnable='" + getIsEnable() + "'" +
                ", jobKind='" + getJobKind() + "'" +
                "}";
    }
}
