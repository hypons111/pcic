package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.RecArea} entity.
 */
public class RecAreaDTO implements Serializable {

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Schema(description = "type: VARCHAR(22)", required = true)
    private String projectNo;

    /**
     * type: VARCHAR(22)
     */
    @NotNull
    @Size(max = 22)
    @Schema(description = "type: VARCHAR(22)", required = true)
    private String areaNo;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Schema(description = "type: NVARCHAR(50)", required = true)
    private String city;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Schema(description = "type: NVARCHAR(50)", required = true)
    private String village;

    /**
     * type: NVARCHAR(50)
     */
    @NotNull
    @Schema(description = "type: NVARCHAR(50)", required = true)
    private String landmark;

    /**
     * type: VARCHAR(20)
     */
    @Schema(description = "type: VARCHAR(20)")
    private String gpsX;

    /**
     * type: VARCHAR(20)
     */
    @Schema(description = "type: VARCHAR(20)")
    private String gpsY;

    /**
     * type: VARCHAR(20)
     */
    @Schema(description = "type: VARCHAR(20)")
    private String gmX;

    /**
     * type: VARCHAR(20)
     */
    @Schema(description = "type: VARCHAR(20)")
    private String gmY;

    /**
     * type: BIT
     */
    @Schema(description = "type: BIT")
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
    private BigDecimal applyAmount;

    /**
     * type: NVARCHAR(4000)
     */
    @Schema(description = "type: NVARCHAR(4000)")
    private String concept;

    /**
     * type: bigint
     */
    @Schema(description = "type: bitint")
    private Long conceptFile;

    /**
     * type: NVARCHAR(4000)
     */
    @Schema(description = "type: NVARCHAR(4000)")
    private String others;

    /**
     * type: decimal(10,0)
     */
    @Schema(description = "type: decimal(10,0)")
    private BigDecimal reviewAmount;

    /**
     * type: NVARCHAR(10)
     */
    @Schema(description = "type: NVARCHAR(10)")
    private String geoSys;

    /**
     * type: BIT
     */
    @Schema(description = "type: BIT")
    private Boolean isLandslides;

    /**
     * type: BIT
     */
    @Schema(description = "type: BIT")
    private Boolean isWoodland;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Schema(description = "type: VARCHAR(20)")
    private String createUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant createTime;

    /**
     * type: NVARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String updateUser;

    /**
     * type: DATETIME
     */
    @NotNull
    @Schema(description = "type: DATETIME")
    private Instant updateTime;

    // getter & setter

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    public String getGmX() {
        return gmX;
    }

    public void setGmX(String gmX) {
        this.gmX = gmX;
    }

    public String getGmY() {
        return gmY;
    }

    public void setGmY(String gmY) {
        this.gmY = gmY;
    }

    public Boolean getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public BigDecimal getOriginalBuiltYear() {
        return originalBuiltYear;
    }

    public void setOriginalBuiltYear(BigDecimal originalBuiltYear) {
        this.originalBuiltYear = originalBuiltYear;
    }

    public String getOriginalDesign() {
        return originalDesign;
    }

    public void setOriginalDesign(String originalDesign) {
        this.originalDesign = originalDesign;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Long getConceptFile() {
        return conceptFile;
    }

    public void setConceptFile(Long conceptFile) {
        this.conceptFile = conceptFile;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public BigDecimal getReviewAmount() {
        return reviewAmount;
    }

    public void setReviewAmount(BigDecimal reviewAmount) {
        this.reviewAmount = reviewAmount;
    }

    public String getGeoSys() {
        return geoSys;
    }

    public void setGeoSys(String geoSys) {
        this.geoSys = geoSys;
    }

    public Boolean getIsLandslides() {
        return isLandslides;
    }

    public void setIsLandslides(Boolean landslides) {
        isLandslides = landslides;
    }

    public Boolean getIsWoodland() {
        return isWoodland;
    }

    public void setIsWoodland(Boolean woodland) {
        isWoodland = woodland;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
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
        if (!(o instanceof RecAreaDTO)) {
            return false;
        }

        RecAreaDTO recAreaDTO = (RecAreaDTO) o;
        if (this.areaNo == null) {
            return false;
        }
        return Objects.equals(this.areaNo, recAreaDTO.areaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.areaNo);
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
