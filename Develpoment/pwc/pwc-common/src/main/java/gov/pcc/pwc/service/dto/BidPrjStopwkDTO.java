package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjStopwkPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjStopwk} entity.
 */
public class BidPrjStopwkDTO implements Serializable {

    private BidPrjStopwkPrimaryKey id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate startDate;

    @Size(max = 1)
    private String respons;

    @Size(max = 1)
    private String duty;

    @Size(max = 100)
    private String prmNo;

    @Size(max = 1000)
    private String negoUnit;

    @Size(max = 2)
    private String stopReson;

    @Size(max = 2)
    private String negoLevel;

    @Size(max = 20)
    private String column1;

    @Size(max = 20)
    private String column2;

    @Size(max = 20)
    private String column3;

    @Size(max = 20)
    private String column4;

    @Size(max = 20)
    private String column5;

    @Size(max = 2000)
    private String remark;

    @Size(max = 2000)
    private String eRemark;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String createUser;

    @Size(max = 20)
    private String updateUser;

    private BigDecimal confOut;

    private Integer noCntDay;

    private Instant createDate;

    private Instant updateDate;

    private LocalDate peDate;

    private LocalDate endDate;

    public BidPrjStopwkPrimaryKey getId() {
        return new BidPrjStopwkPrimaryKey(this.wkut,this.prjno,this.startDate);
    }

    public void setId(BidPrjStopwkPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getRespons() {
        return respons;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPrmNo() {
        return prmNo;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getNegoUnit() {
        return negoUnit;
    }

    public void setNegoUnit(String negoUnit) {
        this.negoUnit = negoUnit;
    }

    public String getStopReson() {
        return stopReson;
    }

    public void setStopReson(String stopReson) {
        this.stopReson = stopReson;
    }

    public String getNegoLevel() {
        return negoLevel;
    }

    public void setNegoLevel(String negoLevel) {
        this.negoLevel = negoLevel;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String geteRemark() {
        return eRemark;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public Integer getNoCntDay() {
        return noCntDay;
    }

    public void setNoCntDay(Integer noCntDay) {
        this.noCntDay = noCntDay;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getPeDate() {
        return peDate;
    }

    public void setPeDate(LocalDate peDate) {
        this.peDate = peDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjStopwkDTO)) {
            return false;
        }

        BidPrjStopwkDTO bidPrjStopwkDTO = (BidPrjStopwkDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bidPrjStopwkDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjStopwkDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", respons='" + getRespons() + "'" +
            ", duty='" + getDuty() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", negoUnit='" + getNegoUnit() + "'" +
            ", stopReson='" + getStopReson() + "'" +
            ", negoLevel='" + getNegoLevel() + "'" +
            ", column1='" + getColumn1() + "'" +
            ", column2='" + getColumn2() + "'" +
            ", column3='" + getColumn3() + "'" +
            ", column4='" + getColumn4() + "'" +
            ", column5='" + getColumn5() + "'" +
            ", remark='" + getRemark() + "'" +
            ", eRemark='" + geteRemark() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", confOut=" + getConfOut() +
            ", noCntDay=" + getNoCntDay() +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", peDate='" + getPeDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }

    public String getIdString(){
        return getWkut()+"-"+getPrjno()+"-"+getStartDate().toString();
    }
}
