package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPlnunitb.
 */
@Entity
@Table(name = "bid_plnunitb")
public class BidPlnunitb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 50)
    @Column(name = "plnoid", length = 50, nullable = false)
    private String plnoid;

    @NotNull
    @Size(max = 150)
    @Column(name = "plnname", length = 150, nullable = false)
    private String plnname;

    @NotNull
    @Size(max = 200)
    @Column(name = "bosname", length = 200, nullable = false)
    private String bosname;

    @Size(max = 200)
    @Column(name = "unitname", length = 200)
    private String unitname;

    @Size(max = 200)
    @Column(name = "unitname_2", length = 200)
    private String unitname2;

    @Column(name = "bdate")
    private LocalDate bdate;

    @Column(name = "edate")
    private LocalDate edate;

    @Column(name = "pln_amt", precision = 21, scale = 2)
    private BigDecimal plnAmt;

    @Size(max = 50)
    @Column(name = "boscode", length = 50)
    private String boscode;

    @Size(max = 50)
    @Column(name = "unicode", length = 50)
    private String unicode;

    @Size(max = 50)
    @Column(name = "unicode_2", length = 50)
    private String unicode2;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getPlnoid() {
        return this.plnoid;
    }

    public BidPlnunitb plnoid(String plnoid) {
        this.setPlnoid(plnoid);
        return this;
    }

    public void setPlnoid(String plnoid) {
        this.plnoid = plnoid;
    }

    public String getPlnname() {
        return this.plnname;
    }

    public BidPlnunitb plnname(String plnname) {
        this.setPlnname(plnname);
        return this;
    }

    public void setPlnname(String plnname) {
        this.plnname = plnname;
    }

    public String getBosname() {
        return this.bosname;
    }

    public BidPlnunitb bosname(String bosname) {
        this.setBosname(bosname);
        return this;
    }

    public void setBosname(String bosname) {
        this.bosname = bosname;
    }

    public String getUnitname() {
        return this.unitname;
    }

    public BidPlnunitb unitname(String unitname) {
        this.setUnitname(unitname);
        return this;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitname2() {
        return this.unitname2;
    }

    public BidPlnunitb unitname2(String unitname2) {
        this.setUnitname2(unitname2);
        return this;
    }

    public void setUnitname2(String unitname2) {
        this.unitname2 = unitname2;
    }

    public LocalDate getBdate() {
        return this.bdate;
    }

    public BidPlnunitb bdate(LocalDate bdate) {
        this.setBdate(bdate);
        return this;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public LocalDate getEdate() {
        return this.edate;
    }

    public BidPlnunitb edate(LocalDate edate) {
        this.setEdate(edate);
        return this;
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }

    public BigDecimal getPlnAmt() {
        return this.plnAmt;
    }

    public BidPlnunitb plnAmt(BigDecimal plnAmt) {
        this.setPlnAmt(plnAmt);
        return this;
    }

    public void setPlnAmt(BigDecimal plnAmt) {
        this.plnAmt = plnAmt;
    }

    public String getBoscode() {
        return this.boscode;
    }

    public BidPlnunitb boscode(String boscode) {
        this.setBoscode(boscode);
        return this;
    }

    public void setBoscode(String boscode) {
        this.boscode = boscode;
    }

    public String getUnicode() {
        return this.unicode;
    }

    public BidPlnunitb unicode(String unicode) {
        this.setUnicode(unicode);
        return this;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode2() {
        return this.unicode2;
    }

    public BidPlnunitb unicode2(String unicode2) {
        this.setUnicode2(unicode2);
        return this;
    }

    public void setUnicode2(String unicode2) {
        this.unicode2 = unicode2;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPlnunitb createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPlnunitb createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPlnunitb updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPlnunitb updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPlnunitb)) {
            return false;
        }
        return plnoid != null && plnoid.equals(((BidPlnunitb) o).plnoid);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPlnunitb{" +
            ", plnoid='" + getPlnoid() + "'" +
            ", plnname='" + getPlnname() + "'" +
            ", bosname='" + getBosname() + "'" +
            ", unitname='" + getUnitname() + "'" +
            ", unitname2='" + getUnitname2() + "'" +
            ", bdate='" + getBdate() + "'" +
            ", edate='" + getEdate() + "'" +
            ", plnAmt=" + getPlnAmt() +
            ", boscode='" + getBoscode() + "'" +
            ", unicode='" + getUnicode() + "'" +
            ", unicode2='" + getUnicode2() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
