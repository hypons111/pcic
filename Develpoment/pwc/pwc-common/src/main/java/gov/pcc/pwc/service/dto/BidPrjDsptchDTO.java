package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjDsptchPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjDsptch} entity.
 */
public class BidPrjDsptchDTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 40)
    private String dspNo;

    private LocalDate dspSdat;

    @Size(max = 30)
    private String ccut1Tel;

    @Size(max = 30)
    private String ccut1Fax;

    @Size(max = 20)
    private String ccut1er;

    @Size(max = 20)
    private String ccut1erTel;

    @Size(max = 30)
    private String scutTel;

    @Size(max = 30)
    private String scutFax;

    @Size(max = 20)
    private String scuter;

    @Size(max = 20)
    private String scuterTel;

    @Size(max = 2)
    private String dspSpeed;

    @Size(max = 2000)
    private String dspItem;

    private BigDecimal dspPmon;

    private LocalDate dspPedat;

    private LocalDate dspAedat;

    private LocalDate dspF1dat;

    private BigDecimal dspAmon;

    private LocalDate dspF2dat;

    @Size(max = 20)
    private String sender;

    @Size(max = 30)
    private String sendtel;

    @Size(max = 30)
    private String sendfax;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1000)
    private String dspRmk;

    @Size(max = 1)
    private String dsts;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String dspTemp;

    private  BigDecimal ctSum;

    private  BigDecimal ctSumF;

    public BidPrjDsptchPrimaryKey getId() {
        return new BidPrjDsptchPrimaryKey(this.wkut,this.prjno,this.dspNo);
    }

    public void setId(BidPrjDsptchPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.dspNo = id.getDspNo();
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

    public String getDspNo() {
        return dspNo;
    }

    public void setDspNo(String dspNo) {
        this.dspNo = dspNo;
    }

    public LocalDate getDspSdat() {
        return dspSdat;
    }

    public void setDspSdat(LocalDate dspSdat) {
        this.dspSdat = dspSdat;
    }

    public String getCcut1Tel() {
        return ccut1Tel;
    }

    public void setCcut1Tel(String ccut1Tel) {
        this.ccut1Tel = ccut1Tel;
    }

    public String getCcut1Fax() {
        return ccut1Fax;
    }

    public void setCcut1Fax(String ccut1Fax) {
        this.ccut1Fax = ccut1Fax;
    }

    public String getCcut1er() {
        return ccut1er;
    }

    public void setCcut1er(String ccut1er) {
        this.ccut1er = ccut1er;
    }

    public String getCcut1erTel() {
        return ccut1erTel;
    }

    public void setCcut1erTel(String ccut1erTel) {
        this.ccut1erTel = ccut1erTel;
    }

    public String getScutTel() {
        return scutTel;
    }

    public void setScutTel(String scutTel) {
        this.scutTel = scutTel;
    }

    public String getScutFax() {
        return scutFax;
    }

    public void setScutFax(String scutFax) {
        this.scutFax = scutFax;
    }

    public String getScuter() {
        return scuter;
    }

    public void setScuter(String scuter) {
        this.scuter = scuter;
    }

    public String getScuterTel() {
        return scuterTel;
    }

    public void setScuterTel(String scuterTel) {
        this.scuterTel = scuterTel;
    }

    public String getDspSpeed() {
        return dspSpeed;
    }

    public void setDspSpeed(String dspSpeed) {
        this.dspSpeed = dspSpeed;
    }

    public String getDspItem() {
        return dspItem;
    }

    public void setDspItem(String dspItem) {
        this.dspItem = dspItem;
    }

    public BigDecimal getDspPmon() {
        return dspPmon;
    }

    public void setDspPmon(BigDecimal dspPmon) {
        this.dspPmon = dspPmon;
    }

    public LocalDate getDspPedat() {
        return dspPedat;
    }

    public void setDspPedat(LocalDate dspPedat) {
        this.dspPedat = dspPedat;
    }

    public LocalDate getDspAedat() {
        return dspAedat;
    }

    public void setDspAedat(LocalDate dspAedat) {
        this.dspAedat = dspAedat;
    }

    public LocalDate getDspF1dat() {
        return dspF1dat;
    }

    public void setDspF1dat(LocalDate dspF1dat) {
        this.dspF1dat = dspF1dat;
    }

    public BigDecimal getDspAmon() {
        return dspAmon;
    }

    public void setDspAmon(BigDecimal dspAmon) {
        this.dspAmon = dspAmon;
    }

    public LocalDate getDspF2dat() {
        return dspF2dat;
    }

    public void setDspF2dat(LocalDate dspF2dat) {
        this.dspF2dat = dspF2dat;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendtel() {
        return sendtel;
    }

    public void setSendtel(String sendtel) {
        this.sendtel = sendtel;
    }

    public String getSendfax() {
        return sendfax;
    }

    public void setSendfax(String sendfax) {
        this.sendfax = sendfax;
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

    public String getDspRmk() {
        return dspRmk;
    }

    public void setDspRmk(String dspRmk) {
        this.dspRmk = dspRmk;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getDspTemp() {
        return dspTemp;
    }

    public void setDspTemp(String dspTemp) {
        this.dspTemp = dspTemp;
    }

    public BigDecimal getCtSum() {
        return ctSum;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public BigDecimal getCtSumF() {
        return ctSumF;
    }

    public void setCtSumF(BigDecimal ctSumF) {
        this.ctSumF = ctSumF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjDsptchDTO)) {
            return false;
        }

        BidPrjDsptchDTO that = (BidPrjDsptchDTO) o;

        return  wkut.equals(that.wkut) && prjno.equals(that.prjno) && dspNo.equals(that.dspNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,dspNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjDsptchDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", dspNo='" + getDspNo() + "'" +
            ", dspSdat='" + getDspSdat() + "'" +
            ", ccut1Tel='" + getCcut1Tel() + "'" +
            ", ccut1Fax='" + getCcut1Fax() + "'" +
            ", ccut1er='" + getCcut1er() + "'" +
            ", ccut1erTel='" + getCcut1erTel() + "'" +
            ", scutTel='" + getScutTel() + "'" +
            ", scutFax='" + getScutFax() + "'" +
            ", scuter='" + getScuter() + "'" +
            ", scuterTel='" + getScuterTel() + "'" +
            ", dspSpeed='" + getDspSpeed() + "'" +
            ", dspItem='" + getDspItem() + "'" +
            ", dspPmon=" + getDspPmon() +
            ", dspPedat='" + getDspPedat() + "'" +
            ", dspAedat='" + getDspAedat() + "'" +
            ", dspF1dat='" + getDspF1dat() + "'" +
            ", dspAmon=" + getDspAmon() +
            ", dspF2dat='" + getDspF2dat() + "'" +
            ", sender='" + getSender() + "'" +
            ", sendtel='" + getSendtel() + "'" +
            ", sendfax='" + getSendfax() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dspRmk='" + getDspRmk() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", dspTemp='" + getDspTemp() + "'" +
            ", ctSum='" + getCtSum() + "'" +
            "}";
    }
}
