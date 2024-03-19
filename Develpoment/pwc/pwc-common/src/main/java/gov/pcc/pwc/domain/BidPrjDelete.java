package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjDelete.
 */
@Entity
@Table(name = "bid_prj_delete")
@IdClass(BidProjectPrimaryKey.class)
public class BidPrjDelete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40, nullable = false)
    private String prjno;

    @Size(max = 280)
    @Column(name = "name", length = 280)
    private String name;

    @Size(max = 21)
    @Column(name = "pln_code", length = 21)
    private String plnCode;

    @Size(max = 20)
    @Column(name = "pln_no", length = 20)
    private String plnNo;

    @Size(max = 2)
    @Column(name = "type_code", length = 2)
    private String typeCode;

    @Column(name = "tot_amt", precision = 21, scale = 2)
    private BigDecimal totAmt;

    @Column(name = "bdgt1", precision = 21, scale = 2)
    private BigDecimal bdgt1;

    @Column(name = "op_date")
    private LocalDate opDate;

    @Column(name = "aop_date")
    private LocalDate aopDate;

    @Column(name = "pct_date")
    private LocalDate pctDate;

    @Column(name = "act_date")
    private LocalDate actDate;

    @Column(name = "ct_sum", precision = 21, scale = 2)
    private BigDecimal ctSum;

    @Size(max = 80)
    @Column(name = "cntid", length = 80)
    private String cntid;

    @Column(name = "pays", precision = 21, scale = 2)
    private BigDecimal pays;

    @Size(max = 1)
    @Column(name = "ctmeth", length = 1)
    private String ctmeth;

    @Size(max = 2)
    @Column(name = "actmth", length = 2)
    private String actmth;

    @Size(max = 1)
    @Column(name = "w_kind", length = 1)
    private String wKind;

    @Column(name = "w_days")
    private Integer wDays;

    @Size(max = 4)
    @Column(name = "place_code", length = 4)
    private String placeCode;

    @Size(max = 80)
    @Column(name = "man", length = 80)
    private String man;

    @Size(max = 40)
    @Column(name = "tel", length = 40)
    private String tel;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 40)
    @Column(name = "lnk_no2", length = 40)
    private String lnkNo2;

    @Column(name = "fcost2", precision = 21, scale = 2)
    private BigDecimal fcost2;

    @Size(max = 40)
    @Column(name = "lnk_code", length = 40)
    private String lnkCode;

    @Size(max = 20)
    @Column(name = "lnk_code2", length = 20)
    private String lnkCode2;

    @Size(max = 40)
    @Column(name = "lnk_code3", length = 40)
    private String lnkCode3;

    @Column(name = "del_date")
    private LocalDate delDate;

    @Size(max = 500)
    @Column(name = "del_resn", length = 500)
    private String delResn;

    @Size(max = 20)
    @Column(name = "uniquekey", length = 20)
    private String uniquekey;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidProjectPrimaryKey getId() {
        return new BidProjectPrimaryKey(wkut,prjno);
    }

    public BidPrjDelete id(BidProjectPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidProjectPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjDelete wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjDelete prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getName() {
        return this.name;
    }

    public BidPrjDelete name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlnCode() {
        return this.plnCode;
    }

    public BidPrjDelete plnCode(String plnCode) {
        this.setPlnCode(plnCode);
        return this;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnNo() {
        return this.plnNo;
    }

    public BidPrjDelete plnNo(String plnNo) {
        this.setPlnNo(plnNo);
        return this;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public BidPrjDelete typeCode(String typeCode) {
        this.setTypeCode(typeCode);
        return this;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public BigDecimal getTotAmt() {
        return this.totAmt;
    }

    public BidPrjDelete totAmt(BigDecimal totAmt) {
        this.setTotAmt(totAmt);
        return this;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    public BigDecimal getBdgt1() {
        return this.bdgt1;
    }

    public BidPrjDelete bdgt1(BigDecimal bdgt1) {
        this.setBdgt1(bdgt1);
        return this;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public LocalDate getOpDate() {
        return this.opDate;
    }

    public BidPrjDelete opDate(LocalDate opDate) {
        this.setOpDate(opDate);
        return this;
    }

    public void setOpDate(LocalDate opDate) {
        this.opDate = opDate;
    }

    public LocalDate getAopDate() {
        return this.aopDate;
    }

    public BidPrjDelete aopDate(LocalDate aopDate) {
        this.setAopDate(aopDate);
        return this;
    }

    public void setAopDate(LocalDate aopDate) {
        this.aopDate = aopDate;
    }

    public LocalDate getPctDate() {
        return this.pctDate;
    }

    public BidPrjDelete pctDate(LocalDate pctDate) {
        this.setPctDate(pctDate);
        return this;
    }

    public void setPctDate(LocalDate pctDate) {
        this.pctDate = pctDate;
    }

    public LocalDate getActDate() {
        return this.actDate;
    }

    public BidPrjDelete actDate(LocalDate actDate) {
        this.setActDate(actDate);
        return this;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

    public BigDecimal getCtSum() {
        return this.ctSum;
    }

    public BidPrjDelete ctSum(BigDecimal ctSum) {
        this.setCtSum(ctSum);
        return this;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public String getCntid() {
        return this.cntid;
    }

    public BidPrjDelete cntid(String cntid) {
        this.setCntid(cntid);
        return this;
    }

    public void setCntid(String cntid) {
        this.cntid = cntid;
    }

    public BigDecimal getPays() {
        return this.pays;
    }

    public BidPrjDelete pays(BigDecimal pays) {
        this.setPays(pays);
        return this;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public String getCtmeth() {
        return this.ctmeth;
    }

    public BidPrjDelete ctmeth(String ctmeth) {
        this.setCtmeth(ctmeth);
        return this;
    }

    public void setCtmeth(String ctmeth) {
        this.ctmeth = ctmeth;
    }

    public String getActmth() {
        return this.actmth;
    }

    public BidPrjDelete actmth(String actmth) {
        this.setActmth(actmth);
        return this;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public String getwKind() {
        return this.wKind;
    }

    public BidPrjDelete wKind(String wKind) {
        this.setwKind(wKind);
        return this;
    }

    public void setwKind(String wKind) {
        this.wKind = wKind;
    }

    public Integer getwDays() {
        return this.wDays;
    }

    public BidPrjDelete wDays(Integer wDays) {
        this.setwDays(wDays);
        return this;
    }

    public void setwDays(Integer wDays) {
        this.wDays = wDays;
    }

    public String getPlaceCode() {
        return this.placeCode;
    }

    public BidPrjDelete placeCode(String placeCode) {
        this.setPlaceCode(placeCode);
        return this;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getMan() {
        return this.man;
    }

    public BidPrjDelete man(String man) {
        this.setMan(man);
        return this;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return this.tel;
    }

    public BidPrjDelete tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjDelete createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjDelete createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjDelete updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjDelete updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjDelete uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getLnkNo2() {
        return this.lnkNo2;
    }

    public BidPrjDelete lnkNo2(String lnkNo2) {
        this.setLnkNo2(lnkNo2);
        return this;
    }

    public void setLnkNo2(String lnkNo2) {
        this.lnkNo2 = lnkNo2;
    }

    public BigDecimal getFcost2() {
        return this.fcost2;
    }

    public BidPrjDelete fcost2(BigDecimal fcost2) {
        this.setFcost2(fcost2);
        return this;
    }

    public void setFcost2(BigDecimal fcost2) {
        this.fcost2 = fcost2;
    }

    public String getLnkCode() {
        return this.lnkCode;
    }

    public BidPrjDelete lnkCode(String lnkCode) {
        this.setLnkCode(lnkCode);
        return this;
    }

    public void setLnkCode(String lnkCode) {
        this.lnkCode = lnkCode;
    }

    public String getLnkCode2() {
        return this.lnkCode2;
    }

    public BidPrjDelete lnkCode2(String lnkCode2) {
        this.setLnkCode2(lnkCode2);
        return this;
    }

    public void setLnkCode2(String lnkCode2) {
        this.lnkCode2 = lnkCode2;
    }

    public String getLnkCode3() {
        return this.lnkCode3;
    }

    public BidPrjDelete lnkCode3(String lnkCode3) {
        this.setLnkCode3(lnkCode3);
        return this;
    }

    public void setLnkCode3(String lnkCode3) {
        this.lnkCode3 = lnkCode3;
    }

    public LocalDate getDelDate() {
        return this.delDate;
    }

    public BidPrjDelete delDate(LocalDate delDate) {
        this.setDelDate(delDate);
        return this;
    }

    public void setDelDate(LocalDate delDate) {
        this.delDate = delDate;
    }

    public String getDelResn() {
        return this.delResn;
    }

    public BidPrjDelete delResn(String delResn) {
        this.setDelResn(delResn);
        return this;
    }

    public void setDelResn(String delResn) {
        this.delResn = delResn;
    }

    public String getUniquekey() {
        return this.uniquekey;
    }

    public BidPrjDelete uniquekey(String uniquekey) {
        this.setUniquekey(uniquekey);
        return this;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjDelete)) return false;
        BidPrjDelete that = (BidPrjDelete) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjDelete{" +
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
}
