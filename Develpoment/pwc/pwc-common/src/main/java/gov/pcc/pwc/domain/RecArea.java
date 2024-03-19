package gov.pcc.pwc.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A RecArea.
 */
@Entity
@Table(name = "rec_area")
public class RecArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Column(name = "project_no", length = 22, nullable = false)
    private String projectNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Id
    @Column(name = "area_no", length = 22, nullable = false)
    private String areaNo;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Column(name = "city")
    private String city;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Column(name = "village")
    private String village;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Column(name = "landmark")
    private String landmark;

    /**
     * type: VARCHAR(20)
     */
    @Column(name = "gps_x")
    private String gpsX;

    /**
     * type: VARCHAR(20)
     */
    @Column(name = "gps_y")
    private String gpsY;

    /**
     * type: VARCHAR(20)
     */
    @Column(name = "gm_x")
    private String gmX;

    /**
     * type: VARCHAR(20)
     */
    @Column(name = "gm_y")
    private String gmY;

    /**
     * type: BIT
     */
    @Column(name = "is_repeat")
    private Boolean isRepeat;

    /**
     * type: decimal(4,0)
     */
    @Schema(description = "type: decimal(4,0)")
    @Column(name = "original_built_year", precision = 4, scale = 0)
    private BigDecimal originalBuiltYear;

    /**
     * type: NVARCHAR(4000)
     */
    @Column(name = "original_design")
    private String originalDesign;

    /**
     * type: decimal(10,0)
     */
    @Schema(description = "type: decimal(10,0)")
    @Column(name = "apply_amount", precision = 10, scale = 0)
    private BigDecimal applyAmount;

    /**
     * type: NVARCHAR(4000)
     */
    @Column(name = "concept")
    private String concept;

    /**
     * type: bigint
     */
    @Column(name = "concept_file")
    private Long conceptFile;

    /**
     * type: NVARCHAR(4000)
     */
    @Column(name = "others")
    private String others;

    /**
     * type: decimal(10,0)
     */
    @Schema(description = "type: decimal(10,0)")
    @Column(name = "review_amount", precision = 10, scale = 0)
    private BigDecimal reviewAmount;

    /**
     * type: NVARCHAR(10)
     */
    @Column(name = "geo_sys")
    private String geoSys;

    /**
     * type: BIT
     */
    @Column(name = "is_landslides")
    private Boolean isLandslides;

    /**
     * type: BIT
     */
    @Column(name = "is_woodland")
    private Boolean isWoodland;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getProjectNo() {
        return projectNo;
    }

    public RecArea projectNo(String projectNo) {
        this.setProjectNo(projectNo);
        return this;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public RecArea areaNo(String areaNo) {
        this.setAreaNo(areaNo);
        return this;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getCity() {
        return city;
    }

    public RecArea city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public RecArea village(String village) {
        this.setVillage(village);
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getLandmark() {
        return landmark;
    }

    public RecArea landmark(String landmark) {
        this.setLandmark(landmark);
        return this;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getGpsX() {
        return gpsX;
    }

    public RecArea gpsX(String gpsX) {
        this.setGpsX(gpsX);
        return this;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public RecArea gpsY(String gpsY) {
        this.setGpsY(gpsY);
        return this;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    public String getGmX() {
        return gmX;
    }

    public RecArea gmX(String gmX) {
        this.setGmX(gmX);
        return this;
    }

    public void setGmX(String gmX) {
        this.gmX = gmX;
    }

    public String getGmY() {
        return gmY;
    }

    public RecArea gmY(String gmY) {
        this.setGmY(gmY);
        return this;
    }

    public void setGmY(String gmY) {
        this.gmY = gmY;
    }

    public Boolean getIsRepeat() {
        return isRepeat;
    }

    public RecArea isRepeat(Boolean isRepeat) {
        this.setIsRepeat(isRepeat);
        return this;
    }

    public void setIsRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public BigDecimal getOriginalBuiltYear() {
        return originalBuiltYear;
    }

    public RecArea originalBuiltYear(BigDecimal originalBuiltYear) {
        this.setOriginalBuiltYear(originalBuiltYear);
        return this;
    }

    public void setOriginalBuiltYear(BigDecimal originalBuiltYear) {
        this.originalBuiltYear = originalBuiltYear;
    }

    public String getOriginalDesign() {
        return originalDesign;
    }

    public RecArea originalDesign(String originalDesign) {
        this.setOriginalDesign(originalDesign);
        return this;
    }

    public void setOriginalDesign(String originalDesign) {
        this.originalDesign = originalDesign;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public RecArea applyAmount(BigDecimal applyAmount) {
        this.setApplyAmount(applyAmount);
        return this;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getConcept() {
        return concept;
    }

    public RecArea concept(String concept) {
        this.setConcept(concept);
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Long getConceptFile() {
        return conceptFile;
    }

    public RecArea conceptFile(Long conceptFile) {
        this.setConceptFile(conceptFile);
        return this;
    }

    public void setConceptFile(Long conceptFile) {
        this.conceptFile = conceptFile;
    }

    public String getOthers() {
        return others;
    }

    public RecArea others(String others) {
        this.setOthers(others);
        return this;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public BigDecimal getReviewAmount() {
        return reviewAmount;
    }

    public RecArea reviewAmount(BigDecimal reviewAmount) {
        this.setReviewAmount(reviewAmount);
        return this;
    }

    public void setReviewAmount(BigDecimal reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getGeoSys() {
        return geoSys;
    }

    public RecArea geoSys(String geoSys) {
        this.setGeoSys(geoSys);
        return this;
    }

    public void setGeoSys(String geoSys) {
        this.geoSys = geoSys;
    }

    public Boolean getIsLandslides() {
        return isLandslides;
    }

    public RecArea isLandslides(Boolean isLandslides) {
        this.setIsLandslides(isLandslides);
        return this;
    }

    public void setIsLandslides(Boolean landslides) {
        isLandslides = landslides;
    }

    public Boolean getIsWoodland() {
        return isWoodland;
    }

    public RecArea isWoodland(Boolean isWoodland) {
        this.setIsWoodland(isWoodland);
        return this;
    }

    public void setIsWoodland(Boolean woodland) {
        isWoodland = woodland;
    }

    public String getCreateUser() {
        return this.createUser;
    }
    public RecArea createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }
    public RecArea createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }
    public RecArea updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }
    public RecArea updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }
    public void setUpdateTime(Instant updateTime) { this.updateTime = updateTime; }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecArea)) {
            return false;
        }
        return areaNo != null && areaNo.equals(((RecArea) o).areaNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecArea{" +
                "projectNo=" + getProjectNo() +
                ", areaNo='" + getAreaNo() + "'" +
                ", city='" + getCity() + "'" +
                ", village='" + getVillage() + "'" +
                ", landmark='" + getLandmark() + "'" +
                ", gpsX='" + getGpsX() + "'" +
                ", gpsY='" + getGpsY() + "'" +
                ", gmX='" + getGmX() + "'" +
                ", gmY='" + getGmY() + "'" +
                ", isRepeat='" + getIsRepeat() + "'" +
                ", originalBuiltYear='" + getOriginalBuiltYear() + "'" +
                ", originalDesign='" + getOriginalDesign() + "'" +
                ", landmark='" + getLandmark() + "'" +
                ", applyAmount='" + getApplyAmount() + "'" +
                ", concept='" + getConcept() + "'" +
                ", conceptFile='" + getConceptFile() + "'" +
                ", others='" + getOthers() + "'" +
                ", reviewAmount='" + getReviewAmount() + "'" +
                ", geoSys='" + getGeoSys() + "'" +
                ", isLandslides='" + getIsLandslides() + "'" +
                ", isWoodland='" + getIsWoodland() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", createTime='" + getCreateTime() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", updateTime='" + getUpdateTime() + "'" +
                "}";
    }
}
