package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AdmCompany.
 */
@Entity
@Table(name = "adm_company")
public class AdmCompany implements Serializable {

    private static final long serialVersionUID = 1L;

//    /**
//     * type: varchar(8)
//     */
//    @NotNull
//    @Size(max = 8)
//    @Id
//    @Column(name = "ban", length = 8, nullable = false)
//    private String ban;

    @Id
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "comp_idno", length = 10, nullable = false)
    private String compIdno;

    /**
     * type: nvarchar(60)
     */
    @Size(max = 60)
    @Column(name = "rp_name", length = 60)
    private String rpName;

    /**
     * type: nvarchar(60)
     */
    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Column(name = "address_city", length = 10)
    private String addressCity;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Column(name = "address_town", length = 10)
    private String addressTown;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Column(name = "address_village", length = 10)
    private String addressVillage;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Column(name = "address_lin", length = 10)
    private String addressLin;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Column(name = "address_road", length = 10)
    private String addressRoad;

    /**
     * type: decimal(20,0)
     */
    @DecimalMin(value = "0")
    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    /**
     * type: date
     */
    @Column(name = "con_date")
    private Instant conDate;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

//    public String getBan() {
//        return this.ban;
//    }
//
//    public AdmCompany ban(String ban) {
//        this.setBan(ban);
//        return this;
//    }
//
//    public void setBan(String ban) {
//        this.ban = ban;
//    }

    public String getCompIdno() {
        return compIdno;
    }

    public AdmCompany compIdno(String compIdno) {
        this.compIdno = compIdno;
        return this;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getRpName() {
        return this.rpName;
    }

    public AdmCompany rpName(String rpName) {
        this.setRpName(rpName);
        return this;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getName() {
        return this.name;
    }

    public AdmCompany name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public AdmCompany addressCity(String addressCity) {
        this.setAddressCity(addressCity);
        return this;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressTown() {
        return this.addressTown;
    }

    public AdmCompany addressTown(String addressTown) {
        this.setAddressTown(addressTown);
        return this;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressVillage() {
        return this.addressVillage;
    }

    public AdmCompany addressVillage(String addressVillage) {
        this.setAddressVillage(addressVillage);
        return this;
    }

    public void setAddressVillage(String addressVillage) {
        this.addressVillage = addressVillage;
    }

    public String getAddressLin() {
        return this.addressLin;
    }

    public AdmCompany addressLin(String addressLin) {
        this.setAddressLin(addressLin);
        return this;
    }

    public void setAddressLin(String addressLin) {
        this.addressLin = addressLin;
    }

    public String getAddressRoad() {
        return this.addressRoad;
    }

    public AdmCompany addressRoad(String addressRoad) {
        this.setAddressRoad(addressRoad);
        return this;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public AdmCompany amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getConDate() {
        return this.conDate;
    }

    public AdmCompany conDate(Instant conDate) {
        this.setConDate(conDate);
        return this;
    }

    public void setConDate(Instant conDate) {
        this.conDate = conDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmCompany updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmCompany updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmCompany)) {
            return false;
        }
//        return ban != null && ban.equals(((AdmCompany) o).ban);
        return compIdno != null && compIdno.equals(((AdmCompany) o).compIdno);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmCompany{" +
//            "ban=" + getBan() +
            "compIdno=" + getCompIdno() +
            ", rpName='" + getRpName() + "'" +
            ", name='" + getName() + "'" +
            ", addressCity='" + getAddressCity() + "'" +
            ", addressTown='" + getAddressTown() + "'" +
            ", addressVillage='" + getAddressVillage() + "'" +
            ", addressLin='" + getAddressLin() + "'" +
            ", addressRoad='" + getAddressRoad() + "'" +
            ", amount=" + getAmount() +
            ", conDate='" + getConDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
