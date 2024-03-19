package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjDsptch.
 */
@IdClass(BidPrjDsptchPrimaryKey.class)
@Entity
@Table(name = "bid_prj_dsptch")
public class BidPrjDsptch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Id
    @Size(max = 40)
    @Column(name = "dsp_no", length = 40)
    private String dspNo;

    @Column(name = "dsp_sdat")
    private LocalDate dspSdat;

    @Size(max = 30)
    @Column(name = "ccut1_tel", length = 30)
    private String ccut1Tel;

    @Size(max = 30)
    @Column(name = "ccut1_fax", length = 30)
    private String ccut1Fax;

    @Size(max = 20)
    @Column(name = "ccut1er", length = 20)
    private String ccut1er;

    @Size(max = 20)
    @Column(name = "ccut1er_tel", length = 20)
    private String ccut1erTel;

    @Size(max = 30)
    @Column(name = "scut_tel", length = 30)
    private String scutTel;

    @Size(max = 30)
    @Column(name = "scut_fax", length = 30)
    private String scutFax;

    @Size(max = 20)
    @Column(name = "scuter", length = 20)
    private String scuter;

    @Size(max = 20)
    @Column(name = "scuter_tel", length = 20)
    private String scuterTel;

    @Size(max = 2)
    @Column(name = "dsp_speed", length = 2)
    private String dspSpeed;

    @Size(max = 2000)
    @Column(name = "dsp_item", length = 2000)
    private String dspItem;

    @Column(name = "dsp_pmon", precision = 21, scale = 2)
    private BigDecimal dspPmon;

    @Column(name = "dsp_pedat")
    private LocalDate dspPedat;

    @Column(name = "dsp_aedat")
    private LocalDate dspAedat;

    @Column(name = "dsp_f1dat")
    private LocalDate dspF1dat;

    @Column(name = "dsp_amon", precision = 21, scale = 2)
    private BigDecimal dspAmon;

    @Column(name = "dsp_f2dat")
    private LocalDate dspF2dat;

    @Size(max = 20)
    @Column(name = "sender", length = 20)
    private String sender;

    @Size(max = 30)
    @Column(name = "sendtel", length = 30)
    private String sendtel;

    @Size(max = 30)
    @Column(name = "sendfax", length = 30)
    private String sendfax;

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

    @Size(max = 1000)
    @Column(name = "dsp_rmk", length = 1000)
    private String dspRmk;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 20)
    @Column(name = "dsp_temp", length = 20)
    private String dspTemp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjDsptchPrimaryKey getId() {
        return new BidPrjDsptchPrimaryKey(wkut,prjno,dspNo);
    }

    public BidPrjDsptch id(BidPrjDsptchPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjDsptchPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.dspNo = id.getDspNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjDsptch wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjDsptch prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getDspNo() {
        return this.dspNo;
    }

    public BidPrjDsptch dspNo(String dspNo) {
        this.setDspNo(dspNo);
        return this;
    }

    public void setDspNo(String dspNo) {
        this.dspNo = dspNo;
    }

    public LocalDate getDspSdat() {
        return this.dspSdat;
    }

    public BidPrjDsptch dspSdat(LocalDate dspSdat) {
        this.setDspSdat(dspSdat);
        return this;
    }

    public void setDspSdat(LocalDate dspSdat) {
        this.dspSdat = dspSdat;
    }

    public String getCcut1Tel() {
        return this.ccut1Tel;
    }

    public BidPrjDsptch ccut1Tel(String ccut1Tel) {
        this.setCcut1Tel(ccut1Tel);
        return this;
    }

    public void setCcut1Tel(String ccut1Tel) {
        this.ccut1Tel = ccut1Tel;
    }

    public String getCcut1Fax() {
        return this.ccut1Fax;
    }

    public BidPrjDsptch ccut1Fax(String ccut1Fax) {
        this.setCcut1Fax(ccut1Fax);
        return this;
    }

    public void setCcut1Fax(String ccut1Fax) {
        this.ccut1Fax = ccut1Fax;
    }

    public String getCcut1er() {
        return this.ccut1er;
    }

    public BidPrjDsptch ccut1er(String ccut1er) {
        this.setCcut1er(ccut1er);
        return this;
    }

    public void setCcut1er(String ccut1er) {
        this.ccut1er = ccut1er;
    }

    public String getCcut1erTel() {
        return this.ccut1erTel;
    }

    public BidPrjDsptch ccut1erTel(String ccut1erTel) {
        this.setCcut1erTel(ccut1erTel);
        return this;
    }

    public void setCcut1erTel(String ccut1erTel) {
        this.ccut1erTel = ccut1erTel;
    }

    public String getScutTel() {
        return this.scutTel;
    }

    public BidPrjDsptch scutTel(String scutTel) {
        this.setScutTel(scutTel);
        return this;
    }

    public void setScutTel(String scutTel) {
        this.scutTel = scutTel;
    }

    public String getScutFax() {
        return this.scutFax;
    }

    public BidPrjDsptch scutFax(String scutFax) {
        this.setScutFax(scutFax);
        return this;
    }

    public void setScutFax(String scutFax) {
        this.scutFax = scutFax;
    }

    public String getScuter() {
        return this.scuter;
    }

    public BidPrjDsptch scuter(String scuter) {
        this.setScuter(scuter);
        return this;
    }

    public void setScuter(String scuter) {
        this.scuter = scuter;
    }

    public String getScuterTel() {
        return this.scuterTel;
    }

    public BidPrjDsptch scuterTel(String scuterTel) {
        this.setScuterTel(scuterTel);
        return this;
    }

    public void setScuterTel(String scuterTel) {
        this.scuterTel = scuterTel;
    }

    public String getDspSpeed() {
        return this.dspSpeed;
    }

    public BidPrjDsptch dspSpeed(String dspSpeed) {
        this.setDspSpeed(dspSpeed);
        return this;
    }

    public void setDspSpeed(String dspSpeed) {
        this.dspSpeed = dspSpeed;
    }

    public String getDspItem() {
        return this.dspItem;
    }

    public BidPrjDsptch dspItem(String dspItem) {
        this.setDspItem(dspItem);
        return this;
    }

    public void setDspItem(String dspItem) {
        this.dspItem = dspItem;
    }

    public BigDecimal getDspPmon() {
        return this.dspPmon;
    }

    public BidPrjDsptch dspPmon(BigDecimal dspPmon) {
        this.setDspPmon(dspPmon);
        return this;
    }

    public void setDspPmon(BigDecimal dspPmon) {
        this.dspPmon = dspPmon;
    }

    public LocalDate getDspPedat() {
        return this.dspPedat;
    }

    public BidPrjDsptch dspPedat(LocalDate dspPedat) {
        this.setDspPedat(dspPedat);
        return this;
    }

    public void setDspPedat(LocalDate dspPedat) {
        this.dspPedat = dspPedat;
    }

    public LocalDate getDspAedat() {
        return this.dspAedat;
    }

    public BidPrjDsptch dspAedat(LocalDate dspAedat) {
        this.setDspAedat(dspAedat);
        return this;
    }

    public void setDspAedat(LocalDate dspAedat) {
        this.dspAedat = dspAedat;
    }

    public LocalDate getDspF1dat() {
        return this.dspF1dat;
    }

    public BidPrjDsptch dspF1dat(LocalDate dspF1dat) {
        this.setDspF1dat(dspF1dat);
        return this;
    }

    public void setDspF1dat(LocalDate dspF1dat) {
        this.dspF1dat = dspF1dat;
    }

    public BigDecimal getDspAmon() {
        return this.dspAmon;
    }

    public BidPrjDsptch dspAmon(BigDecimal dspAmon) {
        this.setDspAmon(dspAmon);
        return this;
    }

    public void setDspAmon(BigDecimal dspAmon) {
        this.dspAmon = dspAmon;
    }

    public LocalDate getDspF2dat() {
        return this.dspF2dat;
    }

    public BidPrjDsptch dspF2dat(LocalDate dspF2dat) {
        this.setDspF2dat(dspF2dat);
        return this;
    }

    public void setDspF2dat(LocalDate dspF2dat) {
        this.dspF2dat = dspF2dat;
    }

    public String getSender() {
        return this.sender;
    }

    public BidPrjDsptch sender(String sender) {
        this.setSender(sender);
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendtel() {
        return this.sendtel;
    }

    public BidPrjDsptch sendtel(String sendtel) {
        this.setSendtel(sendtel);
        return this;
    }

    public void setSendtel(String sendtel) {
        this.sendtel = sendtel;
    }

    public String getSendfax() {
        return this.sendfax;
    }

    public BidPrjDsptch sendfax(String sendfax) {
        this.setSendfax(sendfax);
        return this;
    }

    public void setSendfax(String sendfax) {
        this.sendfax = sendfax;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjDsptch createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjDsptch createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjDsptch updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjDsptch updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDspRmk() {
        return this.dspRmk;
    }

    public BidPrjDsptch dspRmk(String dspRmk) {
        this.setDspRmk(dspRmk);
        return this;
    }

    public void setDspRmk(String dspRmk) {
        this.dspRmk = dspRmk;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjDsptch dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjDsptch uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getDspTemp() {
        return this.dspTemp;
    }

    public BidPrjDsptch dspTemp(String dspTemp) {
        this.setDspTemp(dspTemp);
        return this;
    }

    public void setDspTemp(String dspTemp) {
        this.dspTemp = dspTemp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjDsptch)) {
            return false;
        }
        BidPrjDsptch that = (BidPrjDsptch) o;

        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && dspNo.equals(that.dspNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut,prjno,dspNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjDsptch{" +
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
            "}";
    }
}
