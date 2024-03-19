package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjWkmeng} entity.
 */
public class BidPrjWkmengDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 10)
    private String id;

    @NotNull
    @Size(max = 200)
    private String pname;

    @Size(max = 1)
    private String fullTime;

    @NotNull
    @Size(max = 1)
    private String job;

    @Size(max = 80)
    private String jobType;

    @Size(max = 40)
    private String workType;

    @Size(max = 40)
    private String sworkType;

    @Size(max = 40)
    private String certNo;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 1)
    private String duty;

    @Size(max = 1)
    private String isJoing;

    @Size(max = 1)
    private String reEntry;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 2000)
    private String remark;

    @Size(max = 200)
    private String man;

    @Size(max = 30)
    private String tel;

    @Size(max = 30)
    private String tel2;

    private Float confOut;

    @Size(max = 60)
    private String uIp;

    private List<BidWkmengReentryDTO> bidWkmengReentryDTOs;

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getSworkType() {
        return sworkType;
    }

    public void setSworkType(String sworkType) {
        this.sworkType = sworkType;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getIsJoing() {
        return isJoing;
    }

    public void setIsJoing(String isJoing) {
        this.isJoing = isJoing;
    }

    public String getReEntry() {
        return reEntry;
    }

    public void setReEntry(String reEntry) {
        this.reEntry = reEntry;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public Float getConfOut() {
        return confOut;
    }

    public void setConfOut(Float confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public List<BidWkmengReentryDTO> getBidWkmengReentryDTOs() {
        return bidWkmengReentryDTOs;
    }

    public void setBidWkmengReentryDTOs(List<BidWkmengReentryDTO> bidWkmengReentryDTOs) {
        this.bidWkmengReentryDTOs = bidWkmengReentryDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjWkmengDTO)) {
            return false;
        }

        BidPrjWkmengDTO bidPrjWkmengDTO = (BidPrjWkmengDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bidPrjWkmengDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjWkmengDTO{" +
            "wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", id='" + getId() + "'" +
            ", pname='" + getPname() + "'" +
            ", fullTime='" + getFullTime() + "'" +
            ", job='" + getJob() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", workType='" + getWorkType() + "'" +
            ", sworkType='" + getSworkType() + "'" +
            ", certNo='" + getCertNo() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", duty='" + getDuty() + "'" +
            ", isJoing='" + getIsJoing() + "'" +
            ", reEntry='" + getReEntry() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", remark='" + getRemark() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
