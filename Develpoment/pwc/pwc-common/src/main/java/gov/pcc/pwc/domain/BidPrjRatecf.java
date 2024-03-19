package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjRatecf.
 */
@Entity
@IdClass(BidPrjRatecfPrimaryKey.class)
@Table(name = "bid_prj_ratecf")
public class BidPrjRatecf implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "prjno", length = 20, nullable = false)
    private String prjno;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "syr", length = 3, nullable = false)
    private String syr;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "chkunit", length = 20, nullable = false)
    private String chkunit;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "mft", length = 3, nullable = false)
    private String mft;

    @Column(name = "ptrate", precision = 21, scale = 2)
    private BigDecimal ptrate;

    @Column(name = "ptrate1", precision = 21, scale = 2)
    private BigDecimal ptrate1;

    @Column(name = "atrate", precision = 21, scale = 2)
    private BigDecimal atrate;

    @Column(name = "atrate1", precision = 21, scale = 2)
    private BigDecimal atrate1;

    @Size(max = 2000)
    @Column(name = "mfas", length = 2000)
    private String mfas;

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

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 20)
    @Column(name = "mftno", length = 20)
    private String mftno;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjRatecfPrimaryKey getId() {
        return new BidPrjRatecfPrimaryKey(wkut, prjno, syr, month, chkunit, mft);
    }

    public BidPrjRatecf id(BidPrjRatecfPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjRatecfPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
        this.month = id.getMonth();
        this.chkunit = id.getChkunit();
        this.mft = id.getMft();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjRatecf wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjRatecf prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getSyr() {
        return this.syr;
    }

    public BidPrjRatecf syr(String syr) {
        this.setSyr(syr);
        return this;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjRatecf month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getChkunit() {
        return this.chkunit;
    }

    public BidPrjRatecf chkunit(String chkunit) {
        this.setChkunit(chkunit);
        return this;
    }

    public void setChkunit(String chkunit) {
        this.chkunit = chkunit;
    }

    public String getMft() {
        return this.mft;
    }

    public BidPrjRatecf mft(String mft) {
        this.setMft(mft);
        return this;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    public BigDecimal getPtrate() {
        return this.ptrate;
    }

    public BidPrjRatecf ptrate(BigDecimal ptrate) {
        this.setPtrate(ptrate);
        return this;
    }

    public void setPtrate(BigDecimal ptrate) {
        this.ptrate = ptrate;
    }

    public BigDecimal getPtrate1() {
        return this.ptrate1;
    }

    public BidPrjRatecf ptrate1(BigDecimal ptrate1) {
        this.setPtrate1(ptrate1);
        return this;
    }

    public void setPtrate1(BigDecimal ptrate1) {
        this.ptrate1 = ptrate1;
    }

    public BigDecimal getAtrate() {
        return this.atrate;
    }

    public BidPrjRatecf atrate(BigDecimal atrate) {
        this.setAtrate(atrate);
        return this;
    }

    public void setAtrate(BigDecimal atrate) {
        this.atrate = atrate;
    }

    public BigDecimal getAtrate1() {
        return this.atrate1;
    }

    public BidPrjRatecf atrate1(BigDecimal atrate1) {
        this.setAtrate1(atrate1);
        return this;
    }

    public void setAtrate1(BigDecimal atrate1) {
        this.atrate1 = atrate1;
    }

    public String getMfas() {
        return this.mfas;
    }

    public BidPrjRatecf mfas(String mfas) {
        this.setMfas(mfas);
        return this;
    }

    public void setMfas(String mfas) {
        this.mfas = mfas;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjRatecf createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjRatecf createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjRatecf updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjRatecf updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjRatecf uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getMftno() {
        return this.mftno;
    }

    public BidPrjRatecf mftno(String mftno) {
        this.setMftno(mftno);
        return this;
    }

    public void setMftno(String mftno) {
        this.mftno = mftno;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjRatecf)) {return false;}
        BidPrjRatecf that = (BidPrjRatecf) o;
        return wkut.equals(that.wkut)
                && prjno.equals(that.prjno)
                && syr.equals(that.syr)
                && month.equals(that.month)
                && chkunit.equals(that.chkunit)
                && mft.equals(that.mft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, syr, month, chkunit, mft);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjRatecf{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", syr='" + getSyr() + "'" +
            ", month='" + getMonth() + "'" +
            ", chkunit='" + getChkunit() + "'" +
            ", mft='" + getMft() + "'" +
            ", ptrate=" + getPtrate() +
            ", ptrate1=" + getPtrate1() +
            ", atrate=" + getAtrate() +
            ", atrate1=" + getAtrate1() +
            ", mfas='" + getMfas() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", mftno='" + getMftno() + "'" +
            "}";
    }
}
