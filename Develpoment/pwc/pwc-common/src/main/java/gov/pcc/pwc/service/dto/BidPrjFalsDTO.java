package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjFalsPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjFals} entity.
 */
public class BidPrjFalsDTO implements Serializable {


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

    @Size(max = 3)
    private String syr;

    @Size(max = 3)
    private String mft;

    private String mftName;

    @Size(max = 2000)
    private String mfas;

    @Size(max = 2000)
    private String mfrt;

    @Size(max = 2000)
    private String mfru;

    private LocalDate oldDate;

    private LocalDate newDate;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String dsts;

    @Size(max = 60)
    private String uIp;

    public BidPrjFalsPrimaryKey getId() {
        return new BidPrjFalsPrimaryKey(this.wkut,this.prjno,this.syr,this.month);
    }

    public void setId(BidPrjFalsPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
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

    public String getMftName() {
        return mftName;
    }

    public void setMftName(String mftName) {
        this.mftName = mftName;
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

    public String getMfru() {
        return mfru;
    }

    public void setMfru(String mfru) {
        this.mfru = mfru;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjFalsDTO)) {
            return false;
        }

        BidPrjFalsDTO that = (BidPrjFalsDTO) o;

        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && syr.equals(that.syr) && month.equals(that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, syr, month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFalsDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", syr='" + getSyr() + "'" +
            ", mft='" + getMft() + "'" +
            ", mfas='" + getMfas() + "'" +
            ", mfrt='" + getMfrt() + "'" +
            ", mfru='" + getMfru() + "'" +
            ", oldDate='" + getOldDate() + "'" +
            ", newDate='" + getNewDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
