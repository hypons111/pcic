package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjModifyPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjModify} entity.
 */
public class BidPrjModifyDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate chgDate;

    @Size(max = 1)
    private String mdyPress;

    @Size(max = 100)
    private String okNo;

    @Size(max = 2)
    private String mdyReson;

    @Size(max = 2000)
    private String mdyRemark;

    private BigDecimal chgRate;

    private BigDecimal oldAddAmt;

    private BigDecimal oldSubtractAmt;

    private BigDecimal oldAmt;

    private BigDecimal addAmt;

    private BigDecimal subtractAmt;

    private BigDecimal newAmt;

    private LocalDate oldDate;

    private LocalDate newDate;

    private Integer dlyd1;

    private Integer dlyd2;

    private Integer dlyd3;

    private Integer dlyd4;

    private Integer dlyd5;

    private Integer nDays;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 20)
    private String dsts;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String mergeName;

    @Size(max = 20)
    private String mergeNo;

    private BigDecimal mergectSum;

    @Size(max = 60)
    private String remark;

    @Size(max = 2000)
    private String remark1;

    @Size(max = 2000)
    private String remark2;

    public BidPrjModifyPrimaryKey getId() {
        return new BidPrjModifyPrimaryKey(this.wkut,this.prjno,this.chgDate);
    }

    public void setId(BidPrjModifyPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.chgDate = id.getChgDate();
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

    public LocalDate getChgDate() {
        return chgDate;
    }

    public void setChgDate(LocalDate chgDate) {
        this.chgDate = chgDate;
    }

    public String getMdyPress() {
        return mdyPress;
    }

    public void setMdyPress(String mdyPress) {
        this.mdyPress = mdyPress;
    }

    public String getOkNo() {
        return okNo;
    }

    public void setOkNo(String okNo) {
        this.okNo = okNo;
    }

    public String getMdyReson() {
        return mdyReson;
    }

    public void setMdyReson(String mdyReson) {
        this.mdyReson = mdyReson;
    }

    public String getMdyRemark() {
        return mdyRemark;
    }

    public void setMdyRemark(String mdyRemark) {
        this.mdyRemark = mdyRemark;
    }

    public BigDecimal getChgRate() {
        return chgRate;
    }

    public void setChgRate(BigDecimal chgRate) {
        this.chgRate = chgRate;
    }

    public BigDecimal getOldAddAmt() {
        return oldAddAmt;
    }

    public void setOldAddAmt(BigDecimal oldAddAmt) {
        this.oldAddAmt = oldAddAmt;
    }

    public BigDecimal getOldSubtractAmt() {
        return oldSubtractAmt;
    }

    public void setOldSubtractAmt(BigDecimal oldSubtractAmt) {
        this.oldSubtractAmt = oldSubtractAmt;
    }

    public BigDecimal getOldAmt() {
        return oldAmt;
    }

    public void setOldAmt(BigDecimal oldAmt) {
        this.oldAmt = oldAmt;
    }

    public BigDecimal getAddAmt() {
        return addAmt;
    }

    public void setAddAmt(BigDecimal addAmt) {
        this.addAmt = addAmt;
    }

    public BigDecimal getSubtractAmt() {
        return subtractAmt;
    }

    public void setSubtractAmt(BigDecimal subtractAmt) {
        this.subtractAmt = subtractAmt;
    }

    public BigDecimal getNewAmt() {
        return newAmt;
    }

    public void setNewAmt(BigDecimal newAmt) {
        this.newAmt = newAmt;
    }

    public LocalDate getOldDate() {
        return oldDate;
    }

    public void setOldDate(LocalDate oldDate) {
        this.oldDate = oldDate;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }

    public Integer getDlyd1() {
        return dlyd1;
    }

    public void setDlyd1(Integer dlyd1) {
        this.dlyd1 = dlyd1;
    }

    public Integer getDlyd2() {
        return dlyd2;
    }

    public void setDlyd2(Integer dlyd2) {
        this.dlyd2 = dlyd2;
    }

    public Integer getDlyd3() {
        return dlyd3;
    }

    public void setDlyd3(Integer dlyd3) {
        this.dlyd3 = dlyd3;
    }

    public Integer getDlyd4() {
        return dlyd4;
    }

    public void setDlyd4(Integer dlyd4) {
        this.dlyd4 = dlyd4;
    }

    public Integer getDlyd5() {
        return dlyd5;
    }

    public void setDlyd5(Integer dlyd5) {
        this.dlyd5 = dlyd5;
    }

    public Integer getnDays() {
        return nDays;
    }

    public void setnDays(Integer nDays) {
        this.nDays = nDays;
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

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getMergeName() {
        return mergeName;
    }

    public void setMergeName(String mergeName) {
        this.mergeName = mergeName;
    }

    public String getMergeNo() {
        return mergeNo;
    }

    public void setMergeNo(String mergeNo) {
        this.mergeNo = mergeNo;
    }

    public BigDecimal getMergectSum() {
        return mergectSum;
    }

    public void setMergectSum(BigDecimal mergectSum) {
        this.mergectSum = mergectSum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjModifyDTO)) return false;
        BidPrjModifyDTO that = (BidPrjModifyDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && chgDate.equals(that.chgDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, chgDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjModifyDTO{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", chgDate='" + getChgDate() + "'" +
            ", mdyPress='" + getMdyPress() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", mdyReson='" + getMdyReson() + "'" +
            ", mergeNo='" + getMergeNo() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", okNo='" + getOkNo() + "'" +
            ", mergeName='" + getMergeName() + "'" +
            ", mdyRemark='" + getMdyRemark() + "'" +
            ", remark='" + getRemark() + "'" +
            ", remark1='" + getRemark1() + "'" +
            ", remark2='" + getRemark2() + "'" +
            ", oldDate='" + getOldDate() + "'" +
            ", newDate='" + getNewDate() + "'" +
            ", chgRate=" + getChgRate() +
            ", oldAddAmt=" + getOldAddAmt() +
            ", oldSubtractAmt=" + getOldSubtractAmt() +
            ", oldAmt=" + getOldAmt() +
            ", addAmt=" + getAddAmt() +
            ", subtractAmt=" + getSubtractAmt() +
            ", newAmt=" + getNewAmt() +
            ", dlyd1=" + getDlyd1() +
            ", dlyd2=" + getDlyd2() +
            ", dlyd3=" + getDlyd3() +
            ", dlyd4=" + getDlyd4() +
            ", dlyd5=" + getDlyd5() +
            ", nDays=" + getnDays() +
            ", mergectSum=" + getMergectSum() +
            ", createUser='" + getCreateUser() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
