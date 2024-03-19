package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A AdmPostAddr.
 */
@Entity
@Table(name = "adm_post_addr")
public class AdmPostAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(5)
     */
    @NotNull
    @Size(max = 5)
    @Schema(description = "type: varchar(5)", required = true)
    @Column(name = "zip", length = 5, nullable = false)
    private String zip;

    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "city", length = 100, nullable = false)
    private String city;

    /**
     * type: nvarchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)", required = true)
    @Column(name = "city_code", length = 10, nullable = false)
    private String cityCode;


    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "area", length = 100, nullable = false)
    private String area;

    /**
     * type: nvarchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: nvarchar(10)", required = true)
    @Column(name = "area_code", length = 10, nullable = false)
    private String areaCode;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmPostAddr id(Long id) {
        this.id = id;
        return this;
    }

    public String getZip() {
        return this.zip;
    }

    public AdmPostAddr zip(String zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return this.city;
    }

    public AdmPostAddr city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public AdmPostAddr cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return this.area;
    }

    public AdmPostAddr area(String area) {
        this.area = area;
        return this;
    }


    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public AdmPostAddr areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmPostAddr updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmPostAddr updateTime(Instant updateTime) {
        this.updateTime = updateTime;
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
        if (!(o instanceof AdmPostAddr)) {
            return false;
        }
        return id != null && id.equals(((AdmPostAddr) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmPostAddr{" +
                "id=" + getId() +
                ", zip5='" + getZip() + "'" +
                ", city='" + getCity() + "'" +
                ", area='" + getArea() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", updateTime='" + getUpdateTime() + "'" +
                "}";
    }
}
