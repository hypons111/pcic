package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwcDownload} entity.
 */
public class PwcDownloadDTO implements Serializable {

    /**
     * type:bigint
     */
    @NotNull
    @Schema(description = "type:bigint", required = true)
    private Long id;

    /**
     * type:nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type:nvarchar(100)", required = true)
    private String subject;

    /**
     * type:nvarchar(1000)
     */
    @NotNull
    @Size(max = 1000)
    @Schema(description = "type:nvarchar(1000)", required = true)
    private String text;

    /**
     * type:nvarchar(100)
     */
    @Size(max = 100)
    @Schema(description = "type:nvarchar(100)")
    private String kind;

    @Pattern(regexp = "[0A-C]")
    private String location;

    /**
     * type:date
     */
    @Schema(description = "type:date")
    private LocalDate releaseDate;

    /**
     * type:date
     */
    @Schema(description = "type:date")
    private LocalDate downDate;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String releaseUnit;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String contactPerson;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type:nvarchar(50)")
    private String contactInformationTel;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type:nvarchar(50)")
    private String contactInformationEmail;

    @Pattern(regexp = "[YN]")
    private String enable;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant updateTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String updateUser;

    /**
     * type:datetime
     */
    @Schema(description = "type:datetime")
    private Instant createTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type:nvarchar(20)")
    private String createUser;

    /**
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String type;

    /* ADM_ACCOUNT */
    @Size(max = 20)
    private String userId;



    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getDownDate() {
        return downDate;
    }

    public void setDownDate(LocalDate downDate) {
        this.downDate = downDate;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInformationTel() {
        return contactInformationTel;
    }

    public void setContactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
    }

    public String getContactInformationEmail() {
        return contactInformationEmail;
    }

    public void setContactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcDownloadDTO)) {
            return false;
        }

        PwcDownloadDTO pwcDownloadDTO = (PwcDownloadDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwcDownloadDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownloadDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", text='" + getText() + "'" +
            ", kind='" + getKind() + "'" +
            ", location='" + getLocation() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", downDate='" + getDownDate() + "'" +
            ", releaseUnit='" + getReleaseUnit() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactInformationTel='" + getContactInformationTel() + "'" +
            ", contactInformationEmail='" + getContactInformationEmail() + "'" +
            ", enable='" + getEnable() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", type='" + getType() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
