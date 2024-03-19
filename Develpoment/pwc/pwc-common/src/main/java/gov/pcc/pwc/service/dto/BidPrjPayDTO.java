package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjPayPrimaryKey;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjPay} entity.
 */
public class BidPrjPayDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String monNo;

    private BigDecimal payMonT;

    private BigDecimal payMonU;

    private LocalDate trueDate;

    private BigDecimal trueMon;

    private LocalDate tenDate;

    private BigDecimal tenMon;

    private LocalDate payDate;

    private BigDecimal payMon;

    private BigDecimal payMonS;

    @Size(max = 40)
    private String chkNo;

    @Size(max = 2000)
    private String payRemark;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String dsts;

    private BigDecimal confOut;

    @Size(max = 60)
    private String uIp;

    private BigDecimal noPayMon;

    public BidPrjPayPrimaryKey getId() {
        return new BidPrjPayPrimaryKey(
                this.wkut,this.prjno,this.monNo
        );
    }

    public void setId(BidPrjPayPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.monNo = id.getMonNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getMonNo() {
        return this.monNo;
    }

    public void setMonNo(String monNo) {
        this.monNo = monNo;
    }

    public BigDecimal getPayMonT() {
        return this.payMonT;
    }

    public void setPayMonT(BigDecimal payMonT) {
        this.payMonT = payMonT;
    }

    public BigDecimal getPayMonU() {
        return this.payMonU;
    }

    public void setPayMonU(BigDecimal payMonU) {
        this.payMonU = payMonU;
    }

    public LocalDate getTrueDate() {
        return this.trueDate;
    }

    public void setTrueDate(LocalDate trueDate) {
        this.trueDate = trueDate;
    }

    public BigDecimal getTrueMon() {
        return this.trueMon;
    }

    public void setTrueMon(BigDecimal trueMon) {
        this.trueMon = trueMon;
    }

    public LocalDate getTenDate() {
        return this.tenDate;
    }

    public void setTenDate(LocalDate tenDate) {
        this.tenDate = tenDate;
    }

    public BigDecimal getTenMon() {
        return this.tenMon;
    }

    public void setTenMon(BigDecimal tenMon) {
        this.tenMon = tenMon;
    }

    public LocalDate getPayDate() {
        return this.payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayMon() {
        return this.payMon;
    }

    public void setPayMon(BigDecimal payMon) {
        this.payMon = payMon;
    }

    public BigDecimal getPayMonS() {
        return this.payMonS;
    }

    public void setPayMonS(BigDecimal payMonS) {
        this.payMonS = payMonS;
    }

    public String getChkNo() {
        return this.chkNo;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public String getPayRemark() {
        return this.payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public BigDecimal getNoPayMon() {
        return this.noPayMon;
    }

    public void setNoPayMon(BigDecimal noPayMon) {
        this.noPayMon = noPayMon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidPrjPayDTO that = (BidPrjPayDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(monNo, that.monNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, monNo);
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
