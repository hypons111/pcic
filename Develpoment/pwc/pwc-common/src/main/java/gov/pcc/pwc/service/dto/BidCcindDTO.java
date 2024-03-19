package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidCcindPrimaryKey;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidCcind} entity.
 */
public class BidCcindDTO implements Serializable {

    @NotNull
    @Size(max = 3)
    private String year;

    @NotNull
    @Size(max = 2)
    private String month;

    private BigDecimal ind1;

    private BigDecimal ind2;

    private BigDecimal ind3;

    @NotNull
    private Instant createDate;

    @NotNull
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidCcindPrimaryKey getId() {
        return new BidCcindPrimaryKey(
                this.year,this.month
        );
    }

    public void setId(BidCcindPrimaryKey id) {
        this.year = id.getYear();
        this.month = id.getMonth();
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getInd1() {
        return this.ind1;
    }

    public void setInd1(BigDecimal ind1) {
        this.ind1 = ind1;
    }

    public BigDecimal getInd2() {
        return this.ind2;
    }

    public void setInd2(BigDecimal ind2) {
        this.ind2 = ind2;
    }

    public BigDecimal getInd3() {
        return this.ind3;
    }

    public void setInd3(BigDecimal ind3) {
        this.ind3 = ind3;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidCcindDTO that = (BidCcindDTO) o;
        return Objects.equals(year, that.year) && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
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
