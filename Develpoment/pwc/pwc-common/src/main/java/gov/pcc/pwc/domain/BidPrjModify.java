package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjModify.
 */
@Entity
@IdClass(BidPrjModifyPrimaryKey.class)
@Table(name = "bid_prj_modify")
public class BidPrjModify implements Serializable {

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

    @Id
    @NotNull
    @Column(name = "chg_date", nullable = false)
    private LocalDate chgDate;

    @Size(max = 1)
    @Column(name = "mdy_press", length = 1)
    private String mdyPress;

    @Size(max = 100)
    @Column(name = "ok_no", length = 100)
    private String okNo;

    @Size(max = 2)
    @Column(name = "mdy_reson", length = 2)
    private String mdyReson;

    @Size(max = 2000)
    @Column(name = "mdy_remark", length = 2000)
    private String mdyRemark;

    @Column(name = "chg_rate", precision = 21, scale = 2)
    private BigDecimal chgRate;

    @Column(name = "old_add_amt", precision = 21, scale = 2)
    private BigDecimal oldAddAmt;

    @Column(name = "old_subtract_amt", precision = 21, scale = 2)
    private BigDecimal oldSubtractAmt;

    @Column(name = "old_amt", precision = 21, scale = 2)
    private BigDecimal oldAmt;

    @Column(name = "add_amt", precision = 21, scale = 2)
    private BigDecimal addAmt;

    @Column(name = "subtract_amt", precision = 21, scale = 2)
    private BigDecimal subtractAmt;

    @Column(name = "new_amt", precision = 21, scale = 2)
    private BigDecimal newAmt;

    @Column(name = "old_date")
    private LocalDate oldDate;

    @Column(name = "new_date")
    private LocalDate newDate;

    @Column(name = "dlyd1")
    private Integer dlyd1;

    @Column(name = "dlyd2")
    private Integer dlyd2;

    @Column(name = "dlyd3")
    private Integer dlyd3;

    @Column(name = "dlyd4")
    private Integer dlyd4;

    @Column(name = "dlyd5")
    private Integer dlyd5;

    @Column(name = "n_days")
    private Integer nDays;

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

    @Size(max = 20)
    @Column(name = "dsts", length = 20)
    private String dsts;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 20)
    @Column(name = "merge_name", length = 20)
    private String mergeName;

    @Size(max = 20)
    @Column(name = "merge_no", length = 20)
    private String mergeNo;

    @Column(name = "mergect_sum", precision = 21, scale = 2)
    private BigDecimal mergectSum;

    @Size(max = 60)
    @Column(name = "remark", length = 60)
    private String remark;

    @Size(max = 2000)
    @Column(name = "remark1", length = 2000)
    private String remark1;

    @Size(max = 2000)
    @Column(name = "remark2", length = 2000)
    private String remark2;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjModifyPrimaryKey getId() {
        return new BidPrjModifyPrimaryKey(wkut,prjno,chgDate);
    }

    public BidPrjModify id(BidPrjModifyPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjModifyPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.chgDate = id.getChgDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjModify wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjModify prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getChgDate() {
        return this.chgDate;
    }

    public BidPrjModify chgDate(LocalDate chgDate) {
        this.setChgDate(chgDate);
        return this;
    }

    public void setChgDate(LocalDate chgDate) {
        this.chgDate = chgDate;
    }

    public String getMdyPress() {
        return this.mdyPress;
    }

    public BidPrjModify mdyPress(String mdyPress) {
        this.setMdyPress(mdyPress);
        return this;
    }

    public void setMdyPress(String mdyPress) {
        this.mdyPress = mdyPress;
    }

    public String getOkNo() {
        return this.okNo;
    }

    public BidPrjModify okNo(String okNo) {
        this.setOkNo(okNo);
        return this;
    }

    public void setOkNo(String okNo) {
        this.okNo = okNo;
    }

    public String getMdyReson() {
        return this.mdyReson;
    }

    public BidPrjModify mdyReson(String mdyReson) {
        this.setMdyReson(mdyReson);
        return this;
    }

    public void setMdyReson(String mdyReson) {
        this.mdyReson = mdyReson;
    }

    public String getMdyRemark() {
        return this.mdyRemark;
    }

    public BidPrjModify mdyRemark(String mdyRemark) {
        this.setMdyRemark(mdyRemark);
        return this;
    }

    public void setMdyRemark(String mdyRemark) {
        this.mdyRemark = mdyRemark;
    }

    public BigDecimal getChgRate() {
        return this.chgRate;
    }

    public BidPrjModify chgRate(BigDecimal chgRate) {
        this.setChgRate(chgRate);
        return this;
    }

    public void setChgRate(BigDecimal chgRate) {
        this.chgRate = chgRate;
    }

    public BigDecimal getOldAddAmt() {
        return this.oldAddAmt;
    }

    public BidPrjModify oldAddAmt(BigDecimal oldAddAmt) {
        this.setOldAddAmt(oldAddAmt);
        return this;
    }

    public void setOldAddAmt(BigDecimal oldAddAmt) {
        this.oldAddAmt = oldAddAmt;
    }

    public BigDecimal getOldSubtractAmt() {
        return this.oldSubtractAmt;
    }

    public BidPrjModify oldSubtractAmt(BigDecimal oldSubtractAmt) {
        this.setOldSubtractAmt(oldSubtractAmt);
        return this;
    }

    public void setOldSubtractAmt(BigDecimal oldSubtractAmt) {
        this.oldSubtractAmt = oldSubtractAmt;
    }

    public BigDecimal getOldAmt() {
        return this.oldAmt;
    }

    public BidPrjModify oldAmt(BigDecimal oldAmt) {
        this.setOldAmt(oldAmt);
        return this;
    }

    public void setOldAmt(BigDecimal oldAmt) {
        this.oldAmt = oldAmt;
    }

    public BigDecimal getAddAmt() {
        return this.addAmt;
    }

    public BidPrjModify addAmt(BigDecimal addAmt) {
        this.setAddAmt(addAmt);
        return this;
    }

    public void setAddAmt(BigDecimal addAmt) {
        this.addAmt = addAmt;
    }

    public BigDecimal getSubtractAmt() {
        return this.subtractAmt;
    }

    public BidPrjModify subtractAmt(BigDecimal subtractAmt) {
        this.setSubtractAmt(subtractAmt);
        return this;
    }

    public void setSubtractAmt(BigDecimal subtractAmt) {
        this.subtractAmt = subtractAmt;
    }

    public BigDecimal getNewAmt() {
        return this.newAmt;
    }

    public BidPrjModify newAmt(BigDecimal newAmt) {
        this.setNewAmt(newAmt);
        return this;
    }

    public void setNewAmt(BigDecimal newAmt) {
        this.newAmt = newAmt;
    }

    public LocalDate getOldDate() {
        return this.oldDate;
    }

    public BidPrjModify oldDate(LocalDate oldDate) {
        this.setOldDate(oldDate);
        return this;
    }

    public void setOldDate(LocalDate oldDate) {
        this.oldDate = oldDate;
    }

    public LocalDate getNewDate() {
        return this.newDate;
    }

    public BidPrjModify newDate(LocalDate newDate) {
        this.setNewDate(newDate);
        return this;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }

    public Integer getDlyd1() {
        return this.dlyd1;
    }

    public BidPrjModify dlyd1(Integer dlyd1) {
        this.setDlyd1(dlyd1);
        return this;
    }

    public void setDlyd1(Integer dlyd1) {
        this.dlyd1 = dlyd1;
    }

    public Integer getDlyd2() {
        return this.dlyd2;
    }

    public BidPrjModify dlyd2(Integer dlyd2) {
        this.setDlyd2(dlyd2);
        return this;
    }

    public void setDlyd2(Integer dlyd2) {
        this.dlyd2 = dlyd2;
    }

    public Integer getDlyd3() {
        return this.dlyd3;
    }

    public BidPrjModify dlyd3(Integer dlyd3) {
        this.setDlyd3(dlyd3);
        return this;
    }

    public void setDlyd3(Integer dlyd3) {
        this.dlyd3 = dlyd3;
    }

    public Integer getDlyd4() {
        return this.dlyd4;
    }

    public BidPrjModify dlyd4(Integer dlyd4) {
        this.setDlyd4(dlyd4);
        return this;
    }

    public void setDlyd4(Integer dlyd4) {
        this.dlyd4 = dlyd4;
    }

    public Integer getDlyd5() {
        return this.dlyd5;
    }

    public BidPrjModify dlyd5(Integer dlyd5) {
        this.setDlyd5(dlyd5);
        return this;
    }

    public void setDlyd5(Integer dlyd5) {
        this.dlyd5 = dlyd5;
    }

    public Integer getnDays() {
        return this.nDays;
    }

    public BidPrjModify nDays(Integer nDays) {
        this.setnDays(nDays);
        return this;
    }

    public void setnDays(Integer nDays) {
        this.nDays = nDays;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjModify createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjModify createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjModify updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjModify updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjModify dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjModify uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getMergeName() {
        return this.mergeName;
    }

    public BidPrjModify mergeName(String mergeName) {
        this.setMergeName(mergeName);
        return this;
    }

    public void setMergeName(String mergeName) {
        this.mergeName = mergeName;
    }

    public String getMergeNo() {
        return this.mergeNo;
    }

    public BidPrjModify mergeNo(String mergeNo) {
        this.setMergeNo(mergeNo);
        return this;
    }

    public void setMergeNo(String mergeNo) {
        this.mergeNo = mergeNo;
    }

    public BigDecimal getMergectSum() {
        return this.mergectSum;
    }

    public BidPrjModify mergectSum(BigDecimal mergectSum) {
        this.setMergectSum(mergectSum);
        return this;
    }

    public void setMergectSum(BigDecimal mergectSum) {
        this.mergectSum = mergectSum;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjModify remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return this.remark1;
    }

    public BidPrjModify remark1(String remark1) {
        this.setRemark1(remark1);
        return this;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return this.remark2;
    }

    public BidPrjModify remark2(String remark2) {
        this.setRemark2(remark2);
        return this;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjModify)) return false;
        BidPrjModify that = (BidPrjModify) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && chgDate.equals(that.chgDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, chgDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjModify{" +
            "id=" + getId() +
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
