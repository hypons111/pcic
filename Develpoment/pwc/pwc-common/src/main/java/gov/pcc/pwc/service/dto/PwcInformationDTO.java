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
 * A DTO for the {@link gov.pcc.pwc.domain.PwcInformation} entity.
 */
public class PwcInformationDTO implements Serializable {

    /**
     * 序號
     */
    @NotNull
    @Schema(description = "序號", required = true)
    private Long id;

    /**
     * 主旨
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "主旨", required = true)
    private String subject;

    /**
     * 內文
     */
    @NotNull
    @Size(max = 1000)
    @Schema(description = "內文", required = true)
    private String text;

    /**
     * 類別
     */
    @Size(max = 100)
    @Schema(description = "類別")
    private String kind;

    /**
     * 位置
     */
    @Pattern(regexp = "[0A-C]")
    @Schema(description = "位置")
    private String location;

    /**
     * 上架日期
     */
    @Schema(description = "上架日期")
    private LocalDate releaseDate;

    /**
     * 下架日期
     */
    @Schema(description = "下架日期")
    private LocalDate downDate;

    /**
     * 發布單位
     */
    @Size(max = 20)
    @Schema(description = "發布單位")
    private String releaseUnit;

    /**
     * 聯絡人
     */
    @Size(max = 20)
    @Schema(description = "聯絡人")
    private String contactPerson;

    /**
     * 聯絡資訊
     */
    @Size(max = 50)
    @Schema(description = "聯絡資訊電話")
    private String contactInformationTel;

    /**
     * 聯絡資訊
     */
    @Size(max = 50)
    @Schema(description = "聯絡資訊Email")
    private String contactInformationEmail;

    /**
     * 是否啟用
     */
    @Pattern(regexp = "[YN]")
    @Schema(description = "是否啟用")
    private String enable;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Instant updateTime;

    /**
     * 更新人員
     */
    @Size(max = 20)
    @Schema(description = "更新人員")
    private String updateUser;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Instant createTime;

    /**
     * 建立人員
     */
    @Size(max = 20)
    @Schema(description = "建立人員")
    private String createUser;

    /**
     * 類型
     * type:varchar
     */
    @Schema(description = "type:varchar")
    private String type;

    /**
     * 置頂
     * type:varchar
     */
    @Pattern(regexp = "[YN]")
    @Schema(description = "type:varchar")
    private String isTop;

    /* ADM_ACCOUNT */
    @Size(max = 20)
    private String userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getContactInformationEmail() {return contactInformationEmail;}

    public void setContactInformationEmail(String contactInformationEmail) {this.contactInformationEmail = contactInformationEmail;}

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcInformationDTO)) {
            return false;
        }

        PwcInformationDTO pwcInformationDTO = (PwcInformationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwcInformationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformationDTO{" +
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
            ", isTop='" + getIsTop() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }
}
