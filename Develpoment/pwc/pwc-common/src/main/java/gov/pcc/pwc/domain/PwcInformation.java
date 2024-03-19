package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A PwcInformation.
 */
@Entity
@Table(name = "pwc_information")
public class PwcInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主旨
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "subject", length = 100, nullable = false)
    private String subject;

    /**
     * 內文
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    /**
     * 類別
     */
    @Size(max = 100)
    @Column(name = "kind", length = 100)
    private String kind;

    /**
     * 位置
     */
    @Pattern(regexp = "[A-C]")
    @Column(name = "location")
    private String location;

    /**
     * 上架日期
     */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * 下架日期
     */
    @Column(name = "down_date")
    private LocalDate downDate;

    /**
     * 發布單位
     */
    @Size(max = 20)
    @Column(name = "release_unit", length = 20)
    private String releaseUnit;

    /**
     * 聯絡人
     */
    @Size(max = 20)
    @Column(name = "contact_person", length = 20)
    private String contactPerson;

    /**
     * 聯絡資訊tel
     */
    @Size(max = 50)
    @Column(name = "contact_information_tel", length = 50)
    private String contactInformationTel;

    /**
     * 聯絡資訊Email
     */
    @Size(max = 50)
    @Column(name = "contact_information_email", length = 50)
    private String contactInformationEmail;

    /**
     * 是否啟用
     */
    @Pattern(regexp = "[YN]")
    @Column(name = "enable")
    private String enable;

    /**
     * 更新時間
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 更新人員
     */
    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * 建立時間
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 建立人員
     */
    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    /**
     * 類型
     */
    @Size(max = 10)
    @Column(name = "type", length = 10)
    private String type;

    /**
     * 置頂
     */
    @Pattern(regexp = "[YN]")
    @Column(name = "is_top")
    private String isTop;



    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PwcInformation id(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return this.subject;
    }

    public PwcInformation subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return this.text;
    }

    public PwcInformation text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return this.kind;
    }

    public PwcInformation kind(String kind) {
        this.kind = kind;
        return this;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return this.location;
    }

    public PwcInformation location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public String getAttributes() {
//        return this.attributes;
//    }
//
//    public PwcInformation attributes(String attributes) {
//        this.attributes = attributes;
//        return this;
//    }
//
//    public void setAttributes(String attributes) {
//        this.attributes = attributes;
//    }

//    public String getRole() {
//        return this.role;
//    }
//
//    public PwcInformation role(String role) {
//        this.role = role;
//        return this;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

//    public String getAppendixLink() {
//        return this.appendixLink;
//    }
//
//    public PwcInformation appendixLink(String appendixLink) {
//        this.appendixLink = appendixLink;
//        return this;
//    }
//
//    public void setAppendixLink(String appendixLink) {
//        this.appendixLink = appendixLink;
//    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public PwcInformation releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getDownDate() {
        return this.downDate;
    }

    public PwcInformation downDate(LocalDate downDate) {
        this.downDate = downDate;
        return this;
    }

    public void setDownDate(LocalDate downDate) {
        this.downDate = downDate;
    }

    public String getReleaseUnit() {
        return this.releaseUnit;
    }

    public PwcInformation releaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
        return this;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public PwcInformation contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInformationTel() {
        return contactInformationTel;
    }

    public PwcInformation contactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
        return this;
    }

    public void setContactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
    }

    public String getContactInformationEmail() {
        return contactInformationEmail;
    }

    public PwcInformation contactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
        return this;
    }

    public void setContactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
    }

    public String getEnable() {
        return this.enable;
    }

    public PwcInformation enable(String enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcInformation updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcInformation updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcInformation createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcInformation createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public PwcInformation type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsTop() {
        return isTop;
    }

    public PwcInformation isTop(String isTop) {
        this.isTop = isTop;
        return this;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcInformation)) {
            return false;
        }
        return id != null && id.equals(((PwcInformation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcInformation{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", text='" + getText() + "'" +
            ", kind='" + getKind() + "'" +
            ", location='" + getLocation() + "'" +
//            ", attributes='" + getAttributes() + "'" +
//            ", role='" + getRole() + "'" +
//            ", appendixLink='" + getAppendixLink() + "'" +
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
            "}";
    }
}
