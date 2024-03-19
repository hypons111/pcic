package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.AdmCompany} entity.
 */
public class AdmCompanyDTO implements Serializable {

//    /**
//     * type: varchar(8)
//     */
////    @NotNull
//    @Size(max = 8)
//    @Schema(description = "type: varchar(8)", required = true)
//    private String ban;

    @Id
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    private String compIdno;

    /**
     * type: nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: nvarchar(50)")
    private String rpName;

    /**
     * type: nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: nvarchar(50)")
    private String name;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)")
    private String addressCity;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)")
    private String addressTown;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)")
    private String addressVillage;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)")
    private String addressLin;

    /**
     * type: nvarchar(10)
     */
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)")
    private String addressRoad;

    /**
     * type: decimal(20,0)
     */
    @DecimalMin(value = "0")
    @Schema(description = "type: decimal(20,0)")
    private BigDecimal amount;

    /**
     * type: date
     */
    @Schema(description = "type: date")
    private Instant conDate;

    /**
     * type: varchar(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    private String updateUser;

    /**
     * type: datetime
     */
//    @NotNull
    @Schema(description = "type: datetime", required = true)
    private Instant updateTime;

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressVillage() {
        return addressVillage;
    }

    public void setAddressVillage(String addressVillage) {
        this.addressVillage = addressVillage;
    }

    public String getAddressLin() {
        return addressLin;
    }

    public void setAddressLin(String addressLin) {
        this.addressLin = addressLin;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getConDate() {
        return conDate;
    }

    public void setConDate(Instant conDate) {
        this.conDate = conDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmCompanyDTO)) {
            return false;
        }

        AdmCompanyDTO admCompanyDTO = (AdmCompanyDTO) o;
        if (this.compIdno == null) {
            return false;
        }
        return Objects.equals(this.compIdno, admCompanyDTO.compIdno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.compIdno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmCompanyDTO{" +
            "compIdno='" + getCompIdno() + "'" +
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
