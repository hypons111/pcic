package gov.pcc.pwc.domain;

import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjSafe.
 */
@Entity
@IdClass(BidPrjSafePrimaryKey.class)
@Table(name = "bid_prj_safe")
public class BidPrjSafe implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;
    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;
    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 20)
    private String prjno;
    @Id
    @Column(name = "sdate")
    private LocalDate sdate;

    @Size(max = 2)
    @Column(name = "stype", length = 2)
    private String stype;

    @Size(max = 40)
    @Column(name = "media", length = 40)
    private String media;

    @Column(name = "died_num", precision = 21, scale = 2)
    private BigDecimal diedNum;

    @Column(name = "hurt_num", precision = 21, scale = 2)
    private BigDecimal hurtNum;

    @Size(max = 2000)
    @Column(name = "disaster_script", length = 2000)
    private String disasterScript;

    @Column(name = "pe_date")
    private LocalDate peDate;

    @Size(max = 100)
    @Column(name = "prm_no", length = 100)
    private String prmNo;

    @Column(name = "me_date")
    private LocalDate meDate;

    @Size(max = 100)
    @Column(name = "mrm_no", length = 100)
    private String mrmNo;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Size(max = 2)
    @Column(name = "ct_unit", length = 2)
    private String ctUnit;

    @Size(max = 20)
    @Column(name = "ctr_no", length = 20)
    private String ctrNo;

    @Size(max = 60)
    @Column(name = "ccnt", length = 60)
    private String ccnt;

    @Size(max = 10)
    @Column(name = "ccut", length = 10)
    private String ccut;

    @Size(max = 10)
    @Column(name = "respons", length = 10)
    private String respons;

    @Column(name = "wdayed", precision = 21, scale = 2)
    private BigDecimal wdayed;

    @Size(max = 10)
    @Column(name = "respons2", length = 10)
    private String respons2;

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

    @Size(max = 100)
    @Column(name = "pedatm", length = 100)
    private String pedatm;

    @Size(max = 100)
    @Column(name = "medatm", length = 100)
    private String medatm;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjSafePrimaryKey getId() {
        return new BidPrjSafePrimaryKey(wkut,prjno,sdate);
    }

    public BidPrjSafe id(BidPrjSafePrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjSafePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.sdate = id.getsdate();

    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjSafe wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjSafe prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getSdate() {
        return this.sdate;
    }

    public BidPrjSafe sdate(LocalDate sdate) {
        this.setSdate(sdate);
        return this;
    }

    public void setSdate(LocalDate sdate) {
        this.sdate = sdate;
    }

    public String getStype() {
        return this.stype;
    }

    public BidPrjSafe stype(String stype) {
        this.setStype(stype);
        return this;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getMedia() {
        return this.media;
    }

    public BidPrjSafe media(String media) {
        this.setMedia(media);
        return this;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public BigDecimal getDiedNum() {
        return this.diedNum;
    }

    public BidPrjSafe diedNum(BigDecimal diedNum) {
        this.setDiedNum(diedNum);
        return this;
    }

    public void setDiedNum(BigDecimal diedNum) {
        this.diedNum = diedNum;
    }

    public BigDecimal getHurtNum() {
        return this.hurtNum;
    }

    public BidPrjSafe hurtNum(BigDecimal hurtNum) {
        this.setHurtNum(hurtNum);
        return this;
    }

    public void setHurtNum(BigDecimal hurtNum) {
        this.hurtNum = hurtNum;
    }

    public String getDisasterScript() {
        return this.disasterScript;
    }

    public BidPrjSafe disasterScript(String disasterScript) {
        this.setDisasterScript(disasterScript);
        return this;
    }

    public void setDisasterScript(String disasterScript) {
        this.disasterScript = disasterScript;
    }

    public LocalDate getPeDate() {
        return this.peDate;
    }

    public BidPrjSafe peDate(LocalDate peDate) {
        this.setPeDate(peDate);
        return this;
    }

    public void setPeDate(LocalDate peDate) {
        this.peDate = peDate;
    }

    public String getPrmNo() {
        return this.prmNo;
    }

    public BidPrjSafe prmNo(String prmNo) {
        this.setPrmNo(prmNo);
        return this;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public LocalDate getMeDate() {
        return this.meDate;
    }

    public BidPrjSafe meDate(LocalDate meDate) {
        this.setMeDate(meDate);
        return this;
    }

    public void setMeDate(LocalDate meDate) {
        this.meDate = meDate;
    }

    public String getMrmNo() {
        return this.mrmNo;
    }

    public BidPrjSafe mrmNo(String mrmNo) {
        this.setMrmNo(mrmNo);
        return this;
    }

    public void setMrmNo(String mrmNo) {
        this.mrmNo = mrmNo;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjSafe remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjSafe confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getCtUnit() {
        return this.ctUnit;
    }

    public BidPrjSafe ctUnit(String ctUnit) {
        this.setCtUnit(ctUnit);
        return this;
    }

    public void setCtUnit(String ctUnit) {
        this.ctUnit = ctUnit;
    }

    public String getCtrNo() {
        return this.ctrNo;
    }

    public BidPrjSafe ctrNo(String ctrNo) {
        this.setCtrNo(ctrNo);
        return this;
    }

    public void setCtrNo(String ctrNo) {
        this.ctrNo = ctrNo;
    }

    public String getCcnt() {
        return this.ccnt;
    }

    public BidPrjSafe ccnt(String ccnt) {
        this.setCcnt(ccnt);
        return this;
    }

    public void setCcnt(String ccnt) {
        this.ccnt = ccnt;
    }

    public String getCcut() {
        return this.ccut;
    }

    public BidPrjSafe ccut(String ccut) {
        this.setCcut(ccut);
        return this;
    }

    public void setCcut(String ccut) {
        this.ccut = ccut;
    }

    public String getRespons() {
        return this.respons;
    }

    public BidPrjSafe respons(String respons) {
        this.setRespons(respons);
        return this;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public BigDecimal getWdayed() {
        return this.wdayed;
    }

    public BidPrjSafe wdayed(BigDecimal wdayed) {
        this.setWdayed(wdayed);
        return this;
    }

    public void setWdayed(BigDecimal wdayed) {
        this.wdayed = wdayed;
    }

    public String getRespons2() {
        return this.respons2;
    }

    public BidPrjSafe respons2(String respons2) {
        this.setRespons2(respons2);
        return this;
    }

    public void setRespons2(String respons2) {
        this.respons2 = respons2;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjSafe createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjSafe createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjSafe updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjSafe updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjSafe uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getPedatm() {
        return this.pedatm;
    }

    public BidPrjSafe pedatm(String pedatm) {
        this.setPedatm(pedatm);
        return this;
    }

    public void setPedatm(String pedatm) {
        this.pedatm = pedatm;
    }

    public String getMedatm() {
        return this.medatm;
    }

    public BidPrjSafe medatm(String medatm) {
        this.setMedatm(medatm);
        return this;
    }

    public void setMedatm(String medatm) {
        this.medatm = medatm;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjSafe)) {
            return false;
        }
        BidPrjSafe that = (BidPrjSafe) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && sdate.equals(that.sdate) && prmNo.equals(that.prmNo);

    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,sdate,prmNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSafe{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", sdate='" + getSdate() + "'" +
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
