package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjPay2PrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjPay2} entity.
 */
public class BidPrjPay2DTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 3)
    private String year;

    @Size(max = 3)
    private String month;

    private BigDecimal trueMon;

    private BigDecimal payMonT;

    private BigDecimal payMonU;

    private LocalDate tenDate;

    private BigDecimal tenMon;

    private LocalDate payDate;

    private BigDecimal payMon;

    private BigDecimal payMonS;

    @Size(max = 40)
    private String chkNo;

    @Size(max = 2000)
    private String payRmk;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    private LocalDate trueDate;

    @Size(max = 1)
    private String dsts;

    private BigDecimal confOut;

    @Size(max = 60)
    private String uIp;

    public BidPrjPay2PrimaryKey getId() {
        return new BidPrjPay2PrimaryKey(this.wkut,this.prjno,this.year,this.month);
    }

    public void setId(BidPrjPay2PrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTrueMon() {
        return trueMon;
    }

    public void setTrueMon(BigDecimal trueMon) {
        this.trueMon = trueMon;
    }

    public BigDecimal getPayMonT() {
        return payMonT;
    }

    public void setPayMonT(BigDecimal payMonT) {
        this.payMonT = payMonT;
    }

    public BigDecimal getPayMonU() {
        return payMonU;
    }

    public void setPayMonU(BigDecimal payMonU) {
        this.payMonU = payMonU;
    }

    public LocalDate getTenDate() {
        return tenDate;
    }

    public void setTenDate(LocalDate tenDate) {
        this.tenDate = tenDate;
    }

    public BigDecimal getTenMon() {
        return tenMon;
    }

    public void setTenMon(BigDecimal tenMon) {
        this.tenMon = tenMon;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayMon() {
        return payMon;
    }

    public void setPayMon(BigDecimal payMon) {
        this.payMon = payMon;
    }

    public BigDecimal getPayMonS() {
        return payMonS;
    }

    public void setPayMonS(BigDecimal payMonS) {
        this.payMonS = payMonS;
    }

    public String getChkNo() {
        return chkNo;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public String getPayRmk() {
        return payRmk;
    }

    public void setPayRmk(String payRmk) {
        this.payRmk = payRmk;
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

    public LocalDate getTrueDate() {
        return trueDate;
    }

    public void setTrueDate(LocalDate trueDate) {
        this.trueDate = trueDate;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjPay2DTO)) {
            return false;
        }

        BidPrjPay2DTO that = (BidPrjPay2DTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(year, that.year) && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,year,month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPay2DTO{" +
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
