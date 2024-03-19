package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjDelete} entity.
 */
public class BidPrjDeleteDTO implements Serializable {


    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @Size(max = 280)
    private String name;

    @Size(max = 21)
    private String plnCode;

    @Size(max = 20)
    private String plnNo;

    @Size(max = 2)
    private String typeCode;

    private BigDecimal totAmt;

    private BigDecimal bdgt1;

    private LocalDate opDate;

    private LocalDate aopDate;

    private LocalDate pctDate;

    private LocalDate actDate;

    private BigDecimal ctSum;

    @Size(max = 80)
    private String cntid;

    private BigDecimal pays;

    @Size(max = 1)
    private String ctmeth;

    @Size(max = 2)
    private String actmth;

    @Size(max = 1)
    private String wKind;

    private Integer wDays;

    @Size(max = 4)
    private String placeCode;

    @Size(max = 80)
    private String man;

    @Size(max = 40)
    private String tel;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    @Size(max = 40)
    private String lnkNo2;

    private BigDecimal fcost2;

    @Size(max = 40)
    private String lnkCode;

    @Size(max = 20)
    private String lnkCode2;

    @Size(max = 40)
    private String lnkCode3;

    private LocalDate delDate;

    @Size(max = 500)
    private String delResn;

    @Size(max = 20)
    private String uniquekey;

    public BidProjectPrimaryKey getId() {
        return new BidProjectPrimaryKey(this.wkut,this.prjno);
    }

    public void setId(BidProjectPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnNo() {
        return plnNo;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    public BigDecimal getBdgt1() {
        return bdgt1;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public LocalDate getOpDate() {
        return opDate;
    }

    public void setOpDate(LocalDate opDate) {
        this.opDate = opDate;
    }

    public LocalDate getAopDate() {
        return aopDate;
    }

    public void setAopDate(LocalDate aopDate) {
        this.aopDate = aopDate;
    }

    public LocalDate getPctDate() {
        return pctDate;
    }

    public void setPctDate(LocalDate pctDate) {
        this.pctDate = pctDate;
    }

    public LocalDate getActDate() {
        return actDate;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

    public BigDecimal getCtSum() {
        return ctSum;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public String getCntid() {
        return cntid;
    }

    public void setCntid(String cntid) {
        this.cntid = cntid;
    }

    public BigDecimal getPays() {
        return pays;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public String getCtmeth() {
        return ctmeth;
    }

    public void setCtmeth(String ctmeth) {
        this.ctmeth = ctmeth;
    }

    public String getActmth() {
        return actmth;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public String getwKind() {
        return wKind;
    }

    public void setwKind(String wKind) {
        this.wKind = wKind;
    }

    public Integer getwDays() {
        return wDays;
    }

    public void setwDays(Integer wDays) {
        this.wDays = wDays;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
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

    public String getLnkNo2() {
        return lnkNo2;
    }

    public void setLnkNo2(String lnkNo2) {
        this.lnkNo2 = lnkNo2;
    }

    public BigDecimal getFcost2() {
        return fcost2;
    }

    public void setFcost2(BigDecimal fcost2) {
        this.fcost2 = fcost2;
    }

    public String getLnkCode() {
        return lnkCode;
    }

    public void setLnkCode(String lnkCode) {
        this.lnkCode = lnkCode;
    }

    public String getLnkCode2() {
        return lnkCode2;
    }

    public void setLnkCode2(String lnkCode2) {
        this.lnkCode2 = lnkCode2;
    }

    public String getLnkCode3() {
        return lnkCode3;
    }

    public void setLnkCode3(String lnkCode3) {
        this.lnkCode3 = lnkCode3;
    }

    public LocalDate getDelDate() {
        return delDate;
    }

    public void setDelDate(LocalDate delDate) {
        this.delDate = delDate;
    }

    public String getDelResn() {
        return delResn;
    }

    public void setDelResn(String delResn) {
        this.delResn = delResn;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjDeleteDTO)) return false;
        BidPrjDeleteDTO that = (BidPrjDeleteDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjDeleteDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", name='" + getName() + "'" +
            ", plnCode='" + getPlnCode() + "'" +
            ", plnNo='" + getPlnNo() + "'" +
            ", typeCode='" + getTypeCode() + "'" +
            ", totAmt=" + getTotAmt() +
            ", bdgt1=" + getBdgt1() +
            ", opDate='" + getOpDate() + "'" +
            ", aopDate='" + getAopDate() + "'" +
            ", pctDate='" + getPctDate() + "'" +
            ", actDate='" + getActDate() + "'" +
            ", ctSum=" + getCtSum() +
            ", cntid='" + getCntid() + "'" +
            ", pays=" + getPays() +
            ", ctmeth='" + getCtmeth() + "'" +
            ", actmth='" + getActmth() + "'" +
            ", wKind='" + getwKind() + "'" +
            ", wDays=" + getwDays() +
            ", placeCode='" + getPlaceCode() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", lnkNo2='" + getLnkNo2() + "'" +
            ", fcost2=" + getFcost2() +
            ", lnkCode='" + getLnkCode() + "'" +
            ", lnkCode2='" + getLnkCode2() + "'" +
            ", lnkCode3='" + getLnkCode3() + "'" +
            ", delDate='" + getDelDate() + "'" +
            ", delResn='" + getDelResn() + "'" +
            ", uniquekey='" + getUniquekey() + "'" +
            "}";
    }

    public String getIdString(){
        return this.wkut+"-"+ this.prjno;
    }
}
