package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPlnunitb} entity.
 */
public class BidPlnunitbDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String plnoid;

    @NotNull
    @Size(max = 150)
    private String plnname;

    @NotNull
    @Size(max = 200)
    private String bosname;

    @Size(max = 200)
    private String unitname;

    @Size(max = 200)
    private String unitname2;

    private LocalDate bdate;

    private LocalDate edate;

    private BigDecimal plnAmt;

    @Size(max = 50)
    private String boscode;

    @Size(max = 50)
    private String unicode;

    @Size(max = 50)
    private String unicode2;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlnoid() {
        return plnoid;
    }

    public void setPlnoid(String plnoid) {
        this.plnoid = plnoid;
    }

    public String getPlnname() {
        return plnname;
    }

    public void setPlnname(String plnname) {
        this.plnname = plnname;
    }

    public String getBosname() {
        return bosname;
    }

    public void setBosname(String bosname) {
        this.bosname = bosname;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitname2() {
        return unitname2;
    }

    public void setUnitname2(String unitname2) {
        this.unitname2 = unitname2;
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public LocalDate getEdate() {
        return edate;
    }

    public void setEdate(LocalDate edate) {
        this.edate = edate;
    }

    public BigDecimal getPlnAmt() {
        return plnAmt;
    }

    public void setPlnAmt(BigDecimal plnAmt) {
        this.plnAmt = plnAmt;
    }

    public String getBoscode() {
        return boscode;
    }

    public void setBoscode(String boscode) {
        this.boscode = boscode;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode2() {
        return unicode2;
    }

    public void setUnicode2(String unicode2) {
        this.unicode2 = unicode2;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPlnunitbDTO)) {
            return false;
        }

        BidPlnunitbDTO bidPlnunitbDTO = (BidPlnunitbDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bidPlnunitbDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPlnunitbDTO{" +
            "id=" + getId() +
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
