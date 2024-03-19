package gov.pcc.pwc.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjFal.
 */
@Entity
@IdClass(BidPrjFalPrimaryKey.class)
@Table(name = "bid_prj_fal")
public class BidPrjFal implements Serializable {

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
    @Size(max = 3)
    @Column(name = "year", length = 3, nullable = false)
    private String year;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @NotNull
    @Size(max = 3)
    @Column(name = "syr", length = 3, nullable = false)
    private String syr;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "mft", length = 3, nullable = false)
    private String mft;

    @Size(max = 2000)
    @Column(name = "mfas", length = 2000)
    private String mfas;

    @Size(max = 2000)
    @Column(name = "mfrt", length = 2000)
    private String mfrt;

    @Column(name = "ok_date", length = 5)
    private LocalDate okDate;

    @Size(max = 1)
    @Column(name = "d_status", length = 1)
    private String dStatus;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 2000)
    @Column(name = "mfru", length = 2000)
    private String mfru;

    @Size(max = 1)
    @Column(name = "mfrs", length = 1)
    private String mfrs;

    @Column(name = "fal_rate")
    private Float falRate;

    @Size(max = 1)
    @Column(name = "respons", length = 1)
    private String respons;

    @Size(max = 2)
    @Column(name = "mether", length = 2)
    private String mether;

    @Size(max = 20)
    @Column(name = "column_1", length = 20)
    private String column1;

    @Size(max = 20)
    @Column(name = "column_2", length = 20)
    private String column2;

    @Size(max = 20)
    @Column(name = "column_3", length = 20)
    private String column3;

    @Size(max = 20)
    @Column(name = "column_4", length = 20)
    private String column4;

    @Size(max = 20)
    @Column(name = "column_5", length = 20)
    private String column5;

    @Size(max = 20)
    @Column(name = "column_6", length = 20)
    private String column6;

    @Size(max = 20)
    @Column(name = "column_7", length = 20)
    private String column7;

    @Size(max = 20)
    @Column(name = "column_8", length = 20)
    private String column8;

    @Size(max = 20)
    @Column(name = "column_9", length = 20)
    private String column9;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjFalPrimaryKey getId() {
        return new BidPrjFalPrimaryKey(wkut,prjno, year, month , mft);
    }

    public BidPrjFal id(BidPrjFalPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjFalPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
        this.mft = id.getMft();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjFal wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjFal prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYear() {
        return this.year;
    }

    public BidPrjFal year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjFal month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSyr() {
        return this.syr;
    }

    public BidPrjFal syr(String syr) {
        this.setSyr(syr);
        return this;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMft() {
        return this.mft;
    }

    public BidPrjFal mft(String mft) {
        this.setMft(mft);
        return this;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    public String getMfas() {
        return this.mfas;
    }

    public BidPrjFal mfas(String mfas) {
        this.setMfas(mfas);
        return this;
    }

    public void setMfas(String mfas) {
        this.mfas = mfas;
    }

    public String getMfrt() {
        return this.mfrt;
    }

    public BidPrjFal mfrt(String mfrt) {
        this.setMfrt(mfrt);
        return this;
    }

    public void setMfrt(String mfrt) {
        this.mfrt = mfrt;
    }

    public LocalDate getOkDate() {
        return this.okDate;
    }

    public BidPrjFal okDate(LocalDate okDate) {
        this.setOkDate(okDate);
        return this;
    }

    public void setOkDate(LocalDate okDate) {
        this.okDate = okDate;
    }

    public String getdStatus() {
        return this.dStatus;
    }

    public BidPrjFal dStatus(String dStatus) {
        this.setdStatus(dStatus);
        return this;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjFal createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjFal createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjFal uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjFal updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjFal updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getMfru() {
        return this.mfru;
    }

    public BidPrjFal mfru(String mfru) {
        this.setMfru(mfru);
        return this;
    }

    public void setMfru(String mfru) {
        this.mfru = mfru;
    }

    public String getMfrs() {
        return this.mfrs;
    }

    public BidPrjFal mfrs(String mfrs) {
        this.setMfrs(mfrs);
        return this;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs;
    }

    public Float getFalRate() {
        return this.falRate;
    }

    public BidPrjFal falRate(Float falRate) {
        this.setFalRate(falRate);
        return this;
    }

    public void setFalRate(Float falRate) {
        this.falRate = falRate;
    }

    public String getRespons() {
        return this.respons;
    }

    public BidPrjFal respons(String respons) {
        this.setRespons(respons);
        return this;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public String getMether() {
        return this.mether;
    }

    public BidPrjFal mether(String mether) {
        this.setMether(mether);
        return this;
    }

    public void setMether(String mether) {
        this.mether = mether;
    }

    public String getColumn1() {
        return this.column1;
    }

    public BidPrjFal column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return this.column2;
    }

    public BidPrjFal column2(String column2) {
        this.setColumn2(column2);
        return this;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return this.column3;
    }

    public BidPrjFal column3(String column3) {
        this.setColumn3(column3);
        return this;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return this.column4;
    }

    public BidPrjFal column4(String column4) {
        this.setColumn4(column4);
        return this;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return this.column5;
    }

    public BidPrjFal column5(String column5) {
        this.setColumn5(column5);
        return this;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return this.column6;
    }

    public BidPrjFal column6(String column6) {
        this.setColumn6(column6);
        return this;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return this.column7;
    }

    public BidPrjFal column7(String column7) {
        this.setColumn7(column7);
        return this;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    public String getColumn8() {
        return this.column8;
    }

    public BidPrjFal column8(String column8) {
        this.setColumn8(column8);
        return this;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    public String getColumn9() {
        return this.column9;
    }

    public BidPrjFal column9(String column9) {
        this.setColumn9(column9);
        return this;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjFal)) return false;
        BidPrjFal bidPrjFal = (BidPrjFal) o;
        return wkut.equals(bidPrjFal.wkut) && prjno.equals(bidPrjFal.prjno) && year.equals(bidPrjFal.year) && month.equals(bidPrjFal.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFal{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", syr='" + getSyr() + "'" +
            ", mft='" + getMft() + "'" +
            ", mfas='" + getMfas() + "'" +
            ", mfrt='" + getMfrt() + "'" +
            ", okDate='" + getOkDate() + "'" +
            ", dStatus='" + getdStatus() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", mfru='" + getMfru() + "'" +
            ", mfrs='" + getMfrs() + "'" +
            ", falRate=" + getFalRate() +
            ", respons='" + getRespons() + "'" +
            ", mether='" + getMether() + "'" +
            ", column1='" + getColumn1() + "'" +
            ", column2='" + getColumn2() + "'" +
            ", column3='" + getColumn3() + "'" +
            ", column4='" + getColumn4() + "'" +
            ", column5='" + getColumn5() + "'" +
            ", column6='" + getColumn6() + "'" +
            ", column7='" + getColumn7() + "'" +
            ", column8='" + getColumn8() + "'" +
            ", column9='" + getColumn9() + "'" +
            "}";
    }
}
