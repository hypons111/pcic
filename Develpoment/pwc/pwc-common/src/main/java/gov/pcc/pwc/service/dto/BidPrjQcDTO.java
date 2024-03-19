package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjQcPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjQc} entity.
 */
public class BidPrjQcDTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 10)
    private String id;

    private String name;

    @Size(max = 2)
    private String dsts;

    @Size(max = 1)
    private String jobType;

    @Size(max = 20)
    private String major;

    @Size(max = 100)
    private String company;

    @Size(max = 30)
    private String siteTel;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 1)
    private String duty;

    @Size(max = 1)
    private String reEntry;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 2000)
    private String remark;

    @Size(max = 20)
    private String man;

    @Size(max = 30)
    private String tel;

    private List<BidQcReentryDTO> bidQcReentryDTOs;

    // 複合主鍵
    public BidPrjQcPrimaryKey getPrjQcKey() {
        return new BidPrjQcPrimaryKey(wkut, prjno, id, startDate);
    }

    public void setPrjQcKey(BidPrjQcPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.id = id.getId();
        this.startDate = id.getStartDate();
    }


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiteTel() {
        return siteTel;
    }

    public void setSiteTel(String siteTel) {
        this.siteTel = siteTel;
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

    public List<BidQcReentryDTO> getBidQcReentryDTOs() {
        return bidQcReentryDTOs;
    }

    public void setBidQcReentryDTOs(List<BidQcReentryDTO> bidQcReentryDTOs) {
        this.bidQcReentryDTOs = bidQcReentryDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjQcDTO)) {
            return false;
        }

        BidPrjQcDTO bidPrjQcDTO = (BidPrjQcDTO) o;
        if (this.id == null) {
            return false;
        }
        BidPrjQcDTO that = (BidPrjQcDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && id.equals(that.id) && startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,id,startDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjQcDTO{" +
                "bidPrjQc='" + getPrjQcKey() + "'" +
            "wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", id='" + getId() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", major='" + getMajor() + "'" +
            ", company='" + getCompany() + "'" +
            ", siteTel='" + getSiteTel() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", duty='" + getDuty() + "'" +
            ", reEntry='" + getReEntry() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", remark='" + getRemark() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            "}";
    }
}
