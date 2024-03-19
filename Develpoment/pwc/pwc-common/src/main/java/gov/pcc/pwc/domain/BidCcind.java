package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidCcind.
 */
@Schema(description = "手動加複合主鍵(year、month)")
@IdClass(BidCcindPrimaryKey.class)
@Entity
@Table(name = "bid_ccind")
public class BidCcind implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 3)
    @Id
    @Column(name = "year", length = 3, nullable = false)
    private String year;

    @NotNull
    @Size(max = 2)
    @Id
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @Column(name = "ind1")
    private BigDecimal ind1;

    @Column(name = "ind2")
    private BigDecimal ind2;

    @Column(name = "ind3")
    private BigDecimal ind3;

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

    public BidCcindPrimaryKey getId() {
        return new BidCcindPrimaryKey(year, month);
    }

    public BidCcind id(BidCcindPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidCcindPrimaryKey id) {
        this.year = id.getYear();
        this.month = id.getMonth();
    }

    public String getYear() {
        return this.year;
    }

    public BidCcind year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public BidCcind month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getInd1() {
        return this.ind1;
    }

    public BidCcind ind1(BigDecimal ind1) {
        this.setInd1(ind1);
        return this;
    }

    public void setInd1(BigDecimal ind1) {
        this.ind1 = ind1;
    }

    public BigDecimal getInd2() {
        return this.ind2;
    }

    public BidCcind ind2(BigDecimal ind2) {
        this.setInd2(ind2);
        return this;
    }

    public void setInd2(BigDecimal ind2) {
        this.ind2 = ind2;
    }

    public BigDecimal getInd3() {
        return this.ind3;
    }

    public BidCcind ind3(BigDecimal ind3) {
        this.setInd3(ind3);
        return this;
    }

    public void setInd3(BigDecimal ind3) {
        this.ind3 = ind3;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidCcind createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidCcind createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidCcind updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidCcind updateUser(String updateUser) {
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
        if (!(o instanceof BidCcind)) {
            return false;
        }
        return year != null && year.equals(((BidCcind) o).year);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidCcind{" +
            "year=" + getYear() +
            ", month='" + getMonth() + "'" +
            ", ind1=" + getInd1() +
            ", ind2=" + getInd2() +
            ", ind3=" + getInd3() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
