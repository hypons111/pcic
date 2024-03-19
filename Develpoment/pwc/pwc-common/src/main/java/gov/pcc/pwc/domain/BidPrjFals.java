package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjFals.
 */
@IdClass(BidPrjFalsPrimaryKey.class)
@Entity
@Table(name = "bid_prj_fals")
public class BidPrjFals implements Serializable {

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

    @Size(max = 3)
    @Column(name = "syr", length = 3)
    private String syr;

    @Size(max = 3)
    @Column(name = "mft", length = 3)
    private String mft;

    @Size(max = 2000)
    @Column(name = "mfas", length = 2000)
    private String mfas;

    @Size(max = 2000)
    @Column(name = "mfrt", length = 2000)
    private String mfrt;

    @Size(max = 2000)
    @Column(name = "mfru", length = 2000)
    private String mfru;

    @Column(name = "old_date")
    private LocalDate oldDate;

    @Column(name = "new_date")
    private LocalDate newDate;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjFalsPrimaryKey getId() {
        return new BidPrjFalsPrimaryKey(wkut,prjno,year,month);
    }

    public BidPrjFals id(BidPrjFalsPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjFalsPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
        this.month = id.getMonth();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjFals wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjFals prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYear() {
        return this.year;
    }

    public BidPrjFals year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjFals month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSyr() {
        return this.syr;
    }

    public BidPrjFals syr(String syr) {
        this.setSyr(syr);
        return this;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMft() {
        return this.mft;
    }

    public BidPrjFals mft(String mft) {
        this.setMft(mft);
        return this;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    public String getMfas() {
        return this.mfas;
    }

    public BidPrjFals mfas(String mfas) {
        this.setMfas(mfas);
        return this;
    }

    public void setMfas(String mfas) {
        this.mfas = mfas;
    }

    public String getMfrt() {
        return this.mfrt;
    }

    public BidPrjFals mfrt(String mfrt) {
        this.setMfrt(mfrt);
        return this;
    }

    public void setMfrt(String mfrt) {
        this.mfrt = mfrt;
    }

    public String getMfru() {
        return this.mfru;
    }

    public BidPrjFals mfru(String mfru) {
        this.setMfru(mfru);
        return this;
    }

    public void setMfru(String mfru) {
        this.mfru = mfru;
    }

    public LocalDate getOldDate() {
        return this.oldDate;
    }

    public BidPrjFals oldDate(LocalDate oldDate) {
        this.setOldDate(oldDate);
        return this;
    }

    public void setOldDate(LocalDate oldDate) {
        this.oldDate = oldDate;
    }

    public LocalDate getNewDate() {
        return this.newDate;
    }

    public BidPrjFals newDate(LocalDate newDate) {
        this.setNewDate(newDate);
        return this;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjFals createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjFals createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjFals updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjFals updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjFals dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjFals uIp(String uIp) {
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
        if (!(o instanceof BidPrjFals)) {
            return false;
        }
        BidPrjFals that = (BidPrjFals) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && syr.equals(that.syr) && month.equals(that.month);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut, prjno, syr, month);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjFals{" +
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
