package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjPay.
 */
@Entity
@IdClass(BidPrjPayPrimaryKey.class)
@Table(name = "bid_prj_pay")
public class BidPrjPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "mon_no", length = 3)
    private String monNo;

    @Column(name = "pay_mon_t")
    private BigDecimal payMonT;

    @Column(name = "pay_mon_u")
    private BigDecimal payMonU;

    @Column(name = "true_date")
    private LocalDate trueDate;

    @Column(name = "true_mon")
    private BigDecimal trueMon;

    @Column(name = "ten_date")
    private LocalDate tenDate;

    @Column(name = "ten_mon")
    private BigDecimal tenMon;

    @Column(name = "pay_date")
    private LocalDate payDate;

    @Column(name = "pay_mon")
    private BigDecimal payMon;

    @Column(name = "pay_mon_s")
    private BigDecimal payMonS;

    @Size(max = 40)
    @Column(name = "chk_no", length = 40)
    private String chkNo;

    @Size(max = 2000)
    @Column(name = "pay_remark", length = 2000)
    private String payRemark;

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

    @Column(name = "conf_out")
    private BigDecimal confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Column(name = "no_pay_mon")
    private BigDecimal noPayMon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjPayPrimaryKey getId() {
        return new BidPrjPayPrimaryKey(wkut, prjno, monNo);
    }

    public BidPrjPay id(BidPrjPayPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjPayPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.monNo = id.getMonNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjPay wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjPay prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getMonNo() {
        return this.monNo;
    }

    public BidPrjPay monNo(String monNo) {
        this.setMonNo(monNo);
        return this;
    }

    public void setMonNo(String monNo) {
        this.monNo = monNo;
    }

    public BigDecimal getPayMonT() {
        return this.payMonT;
    }

    public BidPrjPay payMonT(BigDecimal payMonT) {
        this.setPayMonT(payMonT);
        return this;
    }

    public void setPayMonT(BigDecimal payMonT) {
        this.payMonT = payMonT;
    }

    public BigDecimal getPayMonU() {
        return this.payMonU;
    }

    public BidPrjPay payMonU(BigDecimal payMonU) {
        this.setPayMonU(payMonU);
        return this;
    }

    public void setPayMonU(BigDecimal payMonU) {
        this.payMonU = payMonU;
    }

    public LocalDate getTrueDate() {
        return this.trueDate;
    }

    public BidPrjPay trueDate(LocalDate trueDate) {
        this.setTrueDate(trueDate);
        return this;
    }

    public void setTrueDate(LocalDate trueDate) {
        this.trueDate = trueDate;
    }

    public BigDecimal getTrueMon() {
        return this.trueMon;
    }

    public BidPrjPay trueMon(BigDecimal trueMon) {
        this.setTrueMon(trueMon);
        return this;
    }

    public void setTrueMon(BigDecimal trueMon) {
        this.trueMon = trueMon;
    }

    public LocalDate getTenDate() {
        return this.tenDate;
    }

    public BidPrjPay tenDate(LocalDate tenDate) {
        this.setTenDate(tenDate);
        return this;
    }

    public void setTenDate(LocalDate tenDate) {
        this.tenDate = tenDate;
    }

    public BigDecimal getTenMon() {
        return this.tenMon;
    }

    public BidPrjPay tenMon(BigDecimal tenMon) {
        this.setTenMon(tenMon);
        return this;
    }

    public void setTenMon(BigDecimal tenMon) {
        this.tenMon = tenMon;
    }

    public LocalDate getPayDate() {
        return this.payDate;
    }

    public BidPrjPay payDate(LocalDate payDate) {
        this.setPayDate(payDate);
        return this;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayMon() {
        return this.payMon;
    }

    public BidPrjPay payMon(BigDecimal payMon) {
        this.setPayMon(payMon);
        return this;
    }

    public void setPayMon(BigDecimal payMon) {
        this.payMon = payMon;
    }

    public BigDecimal getPayMonS() {
        return this.payMonS;
    }

    public BidPrjPay payMonS(BigDecimal payMonS) {
        this.setPayMonS(payMonS);
        return this;
    }

    public void setPayMonS(BigDecimal payMonS) {
        this.payMonS = payMonS;
    }

    public String getChkNo() {
        return this.chkNo;
    }

    public BidPrjPay chkNo(String chkNo) {
        this.setChkNo(chkNo);
        return this;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public String getPayRemark() {
        return this.payRemark;
    }

    public BidPrjPay payRemark(String payRemark) {
        this.setPayRemark(payRemark);
        return this;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjPay createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjPay createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjPay updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjPay updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjPay dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjPay confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjPay uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public BigDecimal getNoPayMon() {
        return this.noPayMon;
    }

    public BidPrjPay noPayMon(BigDecimal noPayMon) {
        this.setNoPayMon(noPayMon);
        return this;
    }

    public void setNoPayMon(BigDecimal noPayMon) {
        this.noPayMon = noPayMon;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjPay that = (BidPrjPay) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && monNo.equals(that.monNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPay{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", monNo='" + getMonNo() + "'" +
            ", payMonT=" + getPayMonT() +
            ", payMonU=" + getPayMonU() +
            ", trueDate='" + getTrueDate() + "'" +
            ", trueMon=" + getTrueMon() +
            ", tenDate='" + getTenDate() + "'" +
            ", tenMon=" + getTenMon() +
            ", payDate='" + getPayDate() + "'" +
            ", payMon=" + getPayMon() +
            ", payMonS=" + getPayMonS() +
            ", chkNo='" + getChkNo() + "'" +
            ", payRemark='" + getPayRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            ", noPayMon=" + getNoPayMon() +
            "}";
    }
}
