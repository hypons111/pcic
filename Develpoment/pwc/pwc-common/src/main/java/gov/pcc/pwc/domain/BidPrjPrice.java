package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjPrice.
 */
@Entity
@IdClass(BidPrjPricePrimaryKey.class)
@Table(name = "bid_prj_price")
public class BidPrjPrice implements Serializable {

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
    @Size(max = 20)
    @Column(name = "p_unit", length = 20, nullable = false)
    private String pUnit;

    @Id
    @NotNull
    @Size(max = 1)
    @Column(name = "grade", length = 1, nullable = false)
    private String grade;

    @Size(max = 30)
    @Column(name = "p_type", length = 30)
    private String pType;

    @Size(max = 30)
    @Column(name = "p_name", length = 30)
    private String pName;

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

    @Column(name = "p_sdate")
    private LocalDate pSdate;

    @Column(name = "p_edate")
    private LocalDate pEdate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjPricePrimaryKey getId() {
        return new BidPrjPricePrimaryKey(this.wkut,this.prjno,this.year,this.pUnit,this.grade);
    }

    public BidPrjPrice id(BidPrjPricePrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjPricePrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.pUnit = id.getpUnit();
        this.grade = id.getGrade();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjPrice wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjPrice prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYear() {
        return this.year;
    }

    public BidPrjPrice year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getpUnit() {
        return this.pUnit;
    }

    public BidPrjPrice pUnit(String pUnit) {
        this.setpUnit(pUnit);
        return this;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    public String getGrade() {
        return this.grade;
    }

    public BidPrjPrice grade(String grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getpType() {
        return this.pType;
    }

    public BidPrjPrice pType(String pType) {
        this.setpType(pType);
        return this;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpName() {
        return this.pName;
    }

    public BidPrjPrice pName(String pName) {
        this.setpName(pName);
        return this;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjPrice createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjPrice createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjPrice updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjPrice updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjPrice uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public LocalDate getpSdate() {
        return this.pSdate;
    }

    public BidPrjPrice pSdate(LocalDate pSdate) {
        this.setpSdate(pSdate);
        return this;
    }

    public void setpSdate(LocalDate pSdate) {
        this.pSdate = pSdate;
    }

    public LocalDate getpEdate() {
        return this.pEdate;
    }

    public BidPrjPrice pEdate(LocalDate pEdate) {
        this.setpEdate(pEdate);
        return this;
    }

    public void setpEdate(LocalDate pEdate) {
        this.pEdate = pEdate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjPrice)) return false;
        BidPrjPrice that = (BidPrjPrice) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(year, that.year) && Objects.equals(pUnit, that.pUnit) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, pUnit, grade);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjPrice{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", pUnit='" + getpUnit() + "'" +
            ", grade='" + getGrade() + "'" +
            ", pType='" + getpType() + "'" +
            ", pName='" + getpName() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", pSdate='" + getpSdate() + "'" +
            ", pEdate='" + getpEdate() + "'" +
            "}";
    }
}
