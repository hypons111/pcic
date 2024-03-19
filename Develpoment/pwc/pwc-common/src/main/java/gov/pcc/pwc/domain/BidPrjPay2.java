package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjPay2.
 */
@IdClass(BidPrjPay2PrimaryKey.class)
@Entity
@Table(name = "bid_prj_pay2")
public class BidPrjPay2 implements Serializable {

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
    @Size(max = 3)
    @Column(name = "year", length = 3)
    private String year;

    @Id
    @Size(max = 3)
    @Column(name = "month", length = 3)
    private String month;

    @Column(name = "true_mon", precision = 21, scale = 2)
    private BigDecimal trueMon;

    @Column(name = "pay_mon_t", precision = 21, scale = 2)
    private BigDecimal payMonT;

    @Column(name = "pay_mon_u", precision = 21, scale = 2)
    private BigDecimal payMonU;

    @Column(name = "ten_date")
    private LocalDate tenDate;

    @Column(name = "ten_mon", precision = 21, scale = 2)
    private BigDecimal tenMon;

    @Column(name = "pay_date")
    private LocalDate payDate;

    @Column(name = "pay_mon", precision = 21, scale = 2)
    private BigDecimal payMon;

    @Column(name = "pay_mon_s", precision = 21, scale = 2)
    private BigDecimal payMonS;

    @Size(max = 40)
    @Column(name = "chk_no", length = 40)
    private String chkNo;

    @Size(max = 2000)
    @Column(name = "pay_rmk", length = 2000)
    private String payRmk;

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

    @Column(name = "true_date")
    private LocalDate trueDate;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjPay2PrimaryKey getId() {
        return new BidPrjPay2PrimaryKey(wkut,prjno,year,month);
    }

    public BidPrjPay2 id(BidPrjPay2PrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjPay2PrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjPay2 wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjPay2 prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYear() {
        return this.year;
    }

    public BidPrjPay2 year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjPay2 month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTrueMon() {
        return this.trueMon;
    }

    public BidPrjPay2 trueMon(BigDecimal trueMon) {
        this.setTrueMon(trueMon);
        return this;
    }

    public void setTrueMon(BigDecimal trueMon) {
        this.trueMon = trueMon;
    }

    public BigDecimal getPayMonT() {
        return this.payMonT;
    }

    public BidPrjPay2 payMonT(BigDecimal payMonT) {
        this.setPayMonT(payMonT);
        return this;
    }

    public void setPayMonT(BigDecimal payMonT) {
        this.payMonT = payMonT;
    }

    public BigDecimal getPayMonU() {
        return this.payMonU;
    }

    public BidPrjPay2 payMonU(BigDecimal payMonU) {
        this.setPayMonU(payMonU);
        return this;
    }

    public void setPayMonU(BigDecimal payMonU) {
        this.payMonU = payMonU;
    }

    public LocalDate getTenDate() {
        return this.tenDate;
    }

    public BidPrjPay2 tenDate(LocalDate tenDate) {
        this.setTenDate(tenDate);
        return this;
    }

    public void setTenDate(LocalDate tenDate) {
        this.tenDate = tenDate;
    }

    public BigDecimal getTenMon() {
        return this.tenMon;
    }

    public BidPrjPay2 tenMon(BigDecimal tenMon) {
        this.setTenMon(tenMon);
        return this;
    }

    public void setTenMon(BigDecimal tenMon) {
        this.tenMon = tenMon;
    }

    public LocalDate getPayDate() {
        return this.payDate;
    }

    public BidPrjPay2 payDate(LocalDate payDate) {
        this.setPayDate(payDate);
        return this;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayMon() {
        return this.payMon;
    }

    public BidPrjPay2 payMon(BigDecimal payMon) {
        this.setPayMon(payMon);
        return this;
    }

    public void setPayMon(BigDecimal payMon) {
        this.payMon = payMon;
    }

    public BigDecimal getPayMonS() {
        return this.payMonS;
    }

    public BidPrjPay2 payMonS(BigDecimal payMonS) {
        this.setPayMonS(payMonS);
        return this;
    }

    public void setPayMonS(BigDecimal payMonS) {
        this.payMonS = payMonS;
    }

    public String getChkNo() {
        return this.chkNo;
    }

    public BidPrjPay2 chkNo(String chkNo) {
        this.setChkNo(chkNo);
        return this;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public String getPayRmk() {
        return this.payRmk;
    }

    public BidPrjPay2 payRmk(String payRmk) {
        this.setPayRmk(payRmk);
        return this;
    }

    public void setPayRmk(String payRmk) {
        this.payRmk = payRmk;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjPay2 createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjPay2 createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjPay2 updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjPay2 updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDate getTrueDate() {
        return this.trueDate;
    }

    public BidPrjPay2 trueDate(LocalDate trueDate) {
        this.setTrueDate(trueDate);
        return this;
    }

    public void setTrueDate(LocalDate trueDate) {
        this.trueDate = trueDate;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjPay2 dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjPay2 confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjPay2 uIp(String uIp) {
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
        if (!(o instanceof BidPrjPay2)) {
            return false;
        }
        BidPrjPay2 that = (BidPrjPay2) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,year,month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPay2{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", trueMon=" + getTrueMon() +
            ", payMonT=" + getPayMonT() +
            ", payMonU=" + getPayMonU() +
            ", tenDate='" + getTenDate() + "'" +
            ", tenMon=" + getTenMon() +
            ", payDate='" + getPayDate() + "'" +
            ", payMon=" + getPayMon() +
            ", payMonS=" + getPayMonS() +
            ", chkNo='" + getChkNo() + "'" +
            ", payRmk='" + getPayRmk() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", trueDate='" + getTrueDate() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
