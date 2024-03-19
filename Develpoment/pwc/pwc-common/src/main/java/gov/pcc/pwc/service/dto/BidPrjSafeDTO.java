package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjSafePrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjSafe} entity.
 */
public class BidPrjSafeDTO implements Serializable {

//    private Long id;

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    private LocalDate sdate;

    private LocalDate sdateNew;

    @Size(max = 2)
    private String stype;

    @Size(max = 40)
    private String media;

    private BigDecimal diedNum;

    private BigDecimal hurtNum;

    @Size(max = 2000)
    private String disasterScript;

    private LocalDate peDate;

    @Size(max = 100)
    private String prmNo;

    private LocalDate meDate;

    @Size(max = 100)
    private String mrmNo;

    @Size(max = 2000)
    private String remark;

    private BigDecimal confOut;

    @Size(max = 2)
    private String ctUnit;

    @Size(max = 20)
    private String ctrNo;

    @Size(max = 60)
    private String ccnt;

    @Size(max = 10)
    private String ccut;

    @Size(max = 10)
    private String respons;

    private BigDecimal wdayed;

    @Size(max = 10)
    private String respons2;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    @Size(max = 100)
    private String pedatm;

    @Size(max = 100)
    private String medatm;

    public BidPrjSafePrimaryKey getId() {
        return new BidPrjSafePrimaryKey(this.wkut,this.prjno,this.sdate);
    }

    public void setId(BidPrjSafePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.sdate = id.getsdate();
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

    public LocalDate getSdate() {
        return sdate;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public LocalDate getSdateNew() {
        return sdateNew;
    }

    public void setSdateNew(LocalDate sdateNew) {
        this.sdateNew = sdateNew;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public BigDecimal getDiedNum() {
        return diedNum;
    }

    public void setDiedNum(BigDecimal diedNum) {
        this.diedNum = diedNum;
    }

    public BigDecimal getHurtNum() {
        return hurtNum;
    }

    public void setHurtNum(BigDecimal hurtNum) {
        this.hurtNum = hurtNum;
    }

    public String getDisasterScript() {
        return disasterScript;
    }

    public void setDisasterScript(String disasterScript) {
        this.disasterScript = disasterScript;
    }

    public LocalDate getPeDate() {
        return peDate;
    }

    public void setPeDate(LocalDate peDate) {
        this.peDate = peDate;
    }

    public String getPrmNo() {
        return prmNo;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public LocalDate getMeDate() {
        return meDate;
    }

    public void setMeDate(LocalDate meDate) {
        this.meDate = meDate;
    }

    public String getMrmNo() {
        return mrmNo;
    }

    public void setMrmNo(String mrmNo) {
        this.mrmNo = mrmNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getCtUnit() {
        return ctUnit;
    }

    public void setCtUnit(String ctUnit) {
        this.ctUnit = ctUnit;
    }

    public String getCtrNo() {
        return ctrNo;
    }

    public void setCtrNo(String ctrNo) {
        this.ctrNo = ctrNo;
    }

    public String getCcnt() {
        return ccnt;
    }

    public void setCcnt(String ccnt) {
        this.ccnt = ccnt;
    }

    public String getCcut() {
        return ccut;
    }

    public void setCcut(String ccut) {
        this.ccut = ccut;
    }

    public String getRespons() {
        return respons;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public BigDecimal getWdayed() {
        return wdayed;
    }

    public void setWdayed(BigDecimal wdayed) {
        this.wdayed = wdayed;
    }

    public String getRespons2() {
        return respons2;
    }

    public void setRespons2(String respons2) {
        this.respons2 = respons2;
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

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getPedatm() {
        return pedatm;
    }

    public void setPedatm(String pedatm) {
        this.pedatm = pedatm;
    }

    public String getMedatm() {
        return medatm;
    }

    public void setMedatm(String medatm) {
        this.medatm = medatm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjSafeDTO)) {
            return false;
        }

        BidPrjSafeDTO that = (BidPrjSafeDTO) o;

        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && sdate.equals(that.sdate) && prmNo.equals(that.prmNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,sdate,prmNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSafeDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", sdate='" + getSdate() + "'" +
            ", sdateNew='" + getSdateNew() + "'" +
            ", stype='" + getStype() + "'" +
            ", media='" + getMedia() + "'" +
            ", diedNum=" + getDiedNum() +
            ", hurtNum=" + getHurtNum() +
            ", disasterScript='" + getDisasterScript() + "'" +
            ", peDate='" + getPeDate() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", meDate='" + getMeDate() + "'" +
            ", mrmNo='" + getMrmNo() + "'" +
            ", remark='" + getRemark() + "'" +
            ", confOut=" + getConfOut() +
            ", ctUnit='" + getCtUnit() + "'" +
            ", ctrNo='" + getCtrNo() + "'" +
            ", ccnt='" + getCcnt() + "'" +
            ", ccut='" + getCcut() + "'" +
            ", respons='" + getRespons() + "'" +
            ", wdayed=" + getWdayed() +
            ", respons2='" + getRespons2() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", pedatm='" + getPedatm() + "'" +
            ", medatm='" + getMedatm() + "'" +
            "}";
    }
}
