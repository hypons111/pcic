package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.AdmContact} entity.
 */
public class AdmContactDTO implements Serializable {

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String userId;

    /**
     * type: NVARCHAR(50)
     */
    @Size(max = 50)
    @Schema(description = "type: NVARCHAR(50)")
    private String chName;

    /**
     * type: NVARCHAR(60)
     */
    @Size(max = 60)
    @Schema(description = "type: NVARCHAR(60)")
    private String title;

    /**
     * type: DATE
     */
    @Schema(description = "type: DATE")
    private Instant birthday;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: VARCHAR(10)")
    private String idno;

    /**
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Schema(description = "type: VARCHAR(1)")
    private String sex;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)")
    private String workTel;

    /**
     * type: VARCHAR(15)
     */
    @Size(max = 15)
    @Schema(description = "type: VARCHAR(15)")
    private String mobileTel;

    /**
     * type: VARCHAR(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: VARCHAR(100)", required = true)
    private String email;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: VARCHAR(10)")
    private String homeAddrZip;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String homeAddrCity;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String homeAddrTown;

    /**
     * type: NVARCHAR(100)
     */
    @Size(max = 100)
    @Schema(description = "type: NVARCHAR(100)")
    private String homeAddrOther;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: VARCHAR(10)")
    private String workAddrZip;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String workAddrCity;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Schema(description = "type: NVARCHAR(10)")
    private String workAddrTown;

    /**
     * type: NVARCHAR(100)
     */
    @Size(max = 100)
    @Schema(description = "type: NVARCHAR(100)")
    private String workAddrOther;

    /**
     * type: VARCHAR(100)
     */
    @Size(max = 100)
    @Schema(description = "type: VARCHAR(100)")
    private String lineToken;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String updateUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    private Instant updateTime;

    /**
     * type: VARCHAR(50)
     */
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(50)")
    private String chNameRome;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddrZip() {
        return homeAddrZip;
    }

    public void setHomeAddrZip(String homeAddrZip) {
        this.homeAddrZip = homeAddrZip;
    }

    public String getHomeAddrCity() {
        return homeAddrCity;
    }

    public void setHomeAddrCity(String homeAddrCity) {
        this.homeAddrCity = homeAddrCity;
    }

    public String getHomeAddrTown() {
        return homeAddrTown;
    }

    public void setHomeAddrTown(String homeAddrTown) {
        this.homeAddrTown = homeAddrTown;
    }

    public String getHomeAddrOther() {
        return homeAddrOther;
    }

    public void setHomeAddrOther(String homeAddrOther) {
        this.homeAddrOther = homeAddrOther;
    }

    public String getWorkAddrZip() {
        return workAddrZip;
    }

    public void setWorkAddrZip(String workAddrZip) {
        this.workAddrZip = workAddrZip;
    }

    public String getWorkAddrCity() {
        return workAddrCity;
    }

    public void setWorkAddrCity(String workAddrCity) {
        this.workAddrCity = workAddrCity;
    }

    public String getWorkAddrTown() {
        return workAddrTown;
    }

    public void setWorkAddrTown(String workAddrTown) {
        this.workAddrTown = workAddrTown;
    }

    public String getWorkAddrOther() {
        return workAddrOther;
    }

    public void setWorkAddrOther(String workAddrOther) {
        this.workAddrOther = workAddrOther;
    }

    public String getLineToken() {
        return lineToken;
    }

    public void setLineToken(String lineToken) {
        this.lineToken = lineToken;
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

    public String getChNameRome() {
        return chNameRome;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmContactDTO)) {
            return false;
        }

        AdmContactDTO admContactDTO = (AdmContactDTO) o;
        if (this.userId == null) {
            return false;
        }
        return Objects.equals(this.userId, admContactDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmContactDTO{" +
            "userId='" + getUserId() + "'" +
            ", chName='" + getChName() + "'" +
            ", title='" + getTitle() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", idno='" + getIdno() + "'" +
            ", sex='" + getSex() + "'" +
            ", workTel='" + getWorkTel() + "'" +
            ", mobileTel='" + getMobileTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", homeAddrZip='" + getHomeAddrZip() + "'" +
            ", homeAddrCity='" + getHomeAddrCity() + "'" +
            ", homeAddrTown='" + getHomeAddrTown() + "'" +
            ", homeAddrOther='" + getHomeAddrOther() + "'" +
            ", workAddrZip='" + getWorkAddrZip() + "'" +
            ", workAddrCity='" + getWorkAddrCity() + "'" +
            ", workAddrTown='" + getWorkAddrTown() + "'" +
            ", workAddrOther='" + getWorkAddrOther() + "'" +
            ", lineToken='" + getLineToken() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", chNameRome='" + getChNameRome() + "'" +
            "}";
    }
}
