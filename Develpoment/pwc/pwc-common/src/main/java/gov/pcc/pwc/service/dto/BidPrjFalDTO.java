package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjFalPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjFal} entity.
 */
public class BidPrjFalDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String year;

    @NotNull
    @Size(max = 2)
    private String month;

    @NotNull
    @Size(max = 3)
    private String syr;

    @NotNull
    @Size(max = 3)
    private String mft;

    @Size(max = 2000)
    private String mfas;

    @Size(max = 2000)
    private String mfrt;

    @Size(max = 5)
    private LocalDate okDate;

    @Size(max = 1)
    private String dStatus;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @NotNull
    private Instant createDate;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String updateUser;

    private Instant updateDate;

    @Size(max = 2000)
    private String mfru;

    @Size(max = 1)
    private String mfrs;

    private Float falRate;

    @Size(max = 1)
    private String respons;

    @Size(max = 2)
    private String mether;

    @Size(max = 20)
    private String column1;

    @Size(max = 20)
    private String column2;

    @Size(max = 20)
    private String column3;

    @Size(max = 20)
    private String column4;

    @Size(max = 20)
    private String column5;

    @Size(max = 20)
    private String column6;

    @Size(max = 20)
    private String column7;

    @Size(max = 20)
    private String column8;

    @Size(max = 20)
    private String column9;

    public BidPrjFalPrimaryKey getId() {
        return new BidPrjFalPrimaryKey(
                this.wkut,this.prjno,this.year,this.month,this.mft
        );
    }

    public void setId(BidPrjFalPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
        this.mft = id.getMft();
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

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMft() {
        return mft;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    public String getMfas() {
        return mfas;
    }

    public void setMfas(String mfas) {
        this.mfas = mfas;
    }

    public String getMfrt() {
        return mfrt;
    }

    public void setMfrt(String mfrt) {
        this.mfrt = mfrt;
    }

    public LocalDate getOkDate() {
        return okDate;
    }

    public void setOkDate(LocalDate okDate) {
        this.okDate = okDate;
    }

    public String getdStatus() {
        return dStatus;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getMfru() {
        return mfru;
    }

    public void setMfru(String mfru) {
        this.mfru = mfru;
    }

    public String getMfrs() {
        return mfrs;
    }

    public void setMfrs(String mfrs) {
        this.mfrs = mfrs;
    }

    public Float getFalRate() {
        return falRate;
    }

    public void setFalRate(Float falRate) {
        this.falRate = falRate;
    }

    public String getRespons() {
        return respons;
    }

    public void setRespons(String respons) {
        this.respons = respons;
    }

    public String getMether() {
        return mether;
    }

    public void setMether(String mether) {
        this.mether = mether;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    public String getColumn8() {
        return column8;
    }

    public void setColumn8(String column8) {
        this.column8 = column8;
    }

    public String getColumn9() {
        return column9;
    }

    public void setColumn9(String column9) {
        this.column9 = column9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjFalDTO)) return false;
        BidPrjFalDTO that = (BidPrjFalDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month) && mft.equals(that.mft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month , mft);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFalDTO{" +
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
