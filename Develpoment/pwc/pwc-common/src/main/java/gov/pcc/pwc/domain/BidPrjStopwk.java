package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjStopwk.
 */
@Entity
@IdClass(BidPrjStopwkPrimaryKey.class)
@Table(name = "bid_prj_stopwk")
public class BidPrjStopwk implements Serializable {

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
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Size(max = 1)
    @Column(name = "respons", length = 1)
    private String respons;

    @Size(max = 1)
    @Column(name = "duty", length = 1)
    private String duty;

    @Size(max = 100)
    @Column(name = "prm_no", length = 100)
    private String prmNo;

    @Size(max = 1000)
    @Column(name = "nego_unit", length = 1000)
    private String negoUnit;

    @Size(max = 2)
    @Column(name = "stop_reson", length = 2)
    private String stopReson;

    @Size(max = 2)
    @Column(name = "nego_level", length = 2)
    private String negoLevel;

    @Size(max = 20)
    @Column(name = "column1", length = 20)
    private String column1;

    @Size(max = 20)
    @Column(name = "column2", length = 20)
    private String column2;

    @Size(max = 20)
    @Column(name = "column3", length = 20)
    private String column3;

    @Size(max = 20)
    @Column(name = "column4", length = 20)
    private String column4;

    @Size(max = 20)
    @Column(name = "column5", length = 20)
    private String column5;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Size(max = 2000)
    @Column(name = "e_remark", length = 2000)
    private String eRemark;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Column(name = "no_cnt_day")
    private Integer noCntDay;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @Column(name = "pe_date")
    private LocalDate peDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjStopwkPrimaryKey getId() {
        return new BidPrjStopwkPrimaryKey(wkut,prjno,startDate);
    }

    public BidPrjStopwk id(BidPrjStopwkPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjStopwkPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.startDate = id.getStartDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjStopwk wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjStopwk prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidPrjStopwk startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getRespons() {
        return this.respons;
    }

    public BidPrjStopwk respons(String respons) {
        this.setRespons(respons);
        return this;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public String getDuty() {
        return this.duty;
    }

    public BidPrjStopwk duty(String duty) {
        this.setDuty(duty);
        return this;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPrmNo() {
        return this.prmNo;
    }

    public BidPrjStopwk prmNo(String prmNo) {
        this.setPrmNo(prmNo);
        return this;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getNegoUnit() {
        return this.negoUnit;
    }

    public BidPrjStopwk negoUnit(String negoUnit) {
        this.setNegoUnit(negoUnit);
        return this;
    }

    public void setNegoUnit(String negoUnit) {
        this.negoUnit = negoUnit;
    }

    public String getStopReson() {
        return this.stopReson;
    }

    public BidPrjStopwk stopReson(String stopReson) {
        this.setStopReson(stopReson);
        return this;
    }

    public void setStopReson(String stopReson) {
        this.stopReson = stopReson;
    }

    public String getNegoLevel() {
        return this.negoLevel;
    }

    public BidPrjStopwk negoLevel(String negoLevel) {
        this.setNegoLevel(negoLevel);
        return this;
    }

    public void setNegoLevel(String negoLevel) {
        this.negoLevel = negoLevel;
    }

    public String getColumn1() {
        return this.column1;
    }

    public BidPrjStopwk column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return this.column2;
    }

    public BidPrjStopwk column2(String column2) {
        this.setColumn2(column2);
        return this;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return this.column3;
    }

    public BidPrjStopwk column3(String column3) {
        this.setColumn3(column3);
        return this;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return this.column4;
    }

    public BidPrjStopwk column4(String column4) {
        this.setColumn4(column4);
        return this;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return this.column5;
    }

    public BidPrjStopwk column5(String column5) {
        this.setColumn5(column5);
        return this;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjStopwk remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String geteRemark() {
        return this.eRemark;
    }

    public BidPrjStopwk eRemark(String eRemark) {
        this.seteRemark(eRemark);
        return this;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjStopwk uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjStopwk createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjStopwk updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjStopwk confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public Integer getNoCntDay() {
        return this.noCntDay;
    }

    public BidPrjStopwk noCntDay(Integer noCntDay) {
        this.setNoCntDay(noCntDay);
        return this;
    }

    public void setNoCntDay(Integer noCntDay) {
        this.noCntDay = noCntDay;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjStopwk createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjStopwk updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getPeDate() {
        return this.peDate;
    }

    public BidPrjStopwk peDate(LocalDate peDate) {
        this.setPeDate(peDate);
        return this;
    }

    public void setPeDate(LocalDate peDate) {
        this.peDate = peDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidPrjStopwk endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjStopwk)) return false;
        BidPrjStopwk that = (BidPrjStopwk) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, startDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjStopwk{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", respons='" + getRespons() + "'" +
            ", duty='" + getDuty() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", negoUnit='" + getNegoUnit() + "'" +
            ", stopReson='" + getStopReson() + "'" +
            ", negoLevel='" + getNegoLevel() + "'" +
            ", column1='" + getColumn1() + "'" +
            ", column2='" + getColumn2() + "'" +
            ", column3='" + getColumn3() + "'" +
            ", column4='" + getColumn4() + "'" +
            ", column5='" + getColumn5() + "'" +
            ", remark='" + getRemark() + "'" +
            ", eRemark='" + geteRemark() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", confOut=" + getConfOut() +
            ", noCntDay=" + getNoCntDay() +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", peDate='" + getPeDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
