package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjSur2.
 */
@IdClass(BidPrjSur2PrimaryKey.class)
@Entity
@Table(name = "bid_prj_sur2")
public class BidPrjSur2 implements Serializable {

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
    @Size(max = 20)
    @Column(name = "dis_no", length = 20)
    private String disNo;

    @Column(name = "amnta", precision = 21, scale = 2)
    private BigDecimal amnta;

    @Column(name = "amntb", precision = 21, scale = 2)
    private BigDecimal amntb;

    @Column(name = "amnt", precision = 21, scale = 2)
    private BigDecimal amnt;

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

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjSur2PrimaryKey getId() {
        return new BidPrjSur2PrimaryKey(wkut,prjno,disNo);
    }

    public BidPrjSur2 id(BidPrjSur2PrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjSur2PrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.disNo = id.getDisNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjSur2 wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjSur2 prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getDisNo() {
        return this.disNo;
    }

    public BidPrjSur2 disNo(String disNo) {
        this.setDisNo(disNo);
        return this;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public BigDecimal getAmnta() {
        return this.amnta;
    }

    public BidPrjSur2 amnta(BigDecimal amnta) {
        this.setAmnta(amnta);
        return this;
    }

    public void setAmnta(BigDecimal amnta) {
        this.amnta = amnta;
    }

    public BigDecimal getAmntb() {
        return this.amntb;
    }

    public BidPrjSur2 amntb(BigDecimal amntb) {
        this.setAmntb(amntb);
        return this;
    }

    public void setAmntb(BigDecimal amntb) {
        this.amntb = amntb;
    }

    public BigDecimal getAmnt() {
        return this.amnt;
    }

    public BidPrjSur2 amnt(BigDecimal amnt) {
        this.setAmnt(amnt);
        return this;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjSur2 createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjSur2 createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjSur2 updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjSur2 updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjSur2 dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjSur2 confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjSur2 uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjSur2)) {
            return false;
        }
        BidPrjSur2 that = (BidPrjSur2) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && disNo.equals(that.disNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,disNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSur2{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", disNo='" + getDisNo() + "'" +
            ", amnta=" + getAmnta() +
            ", amntb=" + getAmntb() +
            ", amnt=" + getAmnt() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
