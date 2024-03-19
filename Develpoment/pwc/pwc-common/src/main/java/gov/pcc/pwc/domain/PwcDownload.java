package gov.pcc.pwc.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A PwcDownload.
 */
@Entity
@Table(name = "pwc_download")
public class PwcDownload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type:nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "subject", length = 100, nullable = false)
    private String subject;

    /**
     * type:nvarchar(1000)
     */
    @NotNull
    @Size(max = 1000)
    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    /**
     * type:nvarchar(100)
     */
    @Size(max = 100)
    @Column(name = "kind", length = 100)
    private String kind;

    @Pattern(regexp = "[0A-C]")
    @Column(name = "location")
    private String location;

    /**
     * type:date
     */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * type:date
     */
    @Column(name = "down_date")
    private LocalDate downDate;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Column(name = "release_unit", length = 20)
    private String releaseUnit;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Column(name = "contact_person", length = 20)
    private String contactPerson;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Column(name = "contact_information_tel", length = 50)
    private String contactInformationTel;

    /**
     * type:nvarchar(50)
     */
    @Size(max = 50)
    @Column(name = "contact_information_email", length = 50)
    private String contactInformationEmail;

    @Pattern(regexp = "[YN]")
    @Column(name = "enable")
    private String enable;

    /**
     * type:datetime
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * type:nvarchar(20)
     */
    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * type:datetime
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * type:nvarchar(20)
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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PwcDownload id(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return this.subject;
    }

    public PwcDownload subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return this.text;
    }

    public PwcDownload text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return this.kind;
    }

    public PwcDownload kind(String kind) {
        this.kind = kind;
        return this;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return this.location;
    }

    public PwcDownload location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public PwcDownload releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getDownDate() {
        return this.downDate;
    }

    public PwcDownload downDate(LocalDate downDate) {
        this.downDate = downDate;
        return this;
    }

    public void setDownDate(LocalDate downDate) {
        this.downDate = downDate;
    }

    public String getReleaseUnit() {
        return this.releaseUnit;
    }

    public PwcDownload releaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
        return this;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public PwcDownload contactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInformationTel() {
        return contactInformationTel;
    }

    public PwcDownload contactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
        return this;
    }

    public void setContactInformationTel(String contactInformationTel) {
        this.contactInformationTel = contactInformationTel;
    }

    public String getContactInformationEmail() {
        return contactInformationEmail;
    }

    public PwcDownload contactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
        return this;
    }

    public void setContactInformationEmail(String contactInformationEmail) {
        this.contactInformationEmail = contactInformationEmail;
    }

    public String getEnable() {
        return this.enable;
    }

    public PwcDownload enable(String enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public PwcDownload updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public PwcDownload updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public PwcDownload createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public PwcDownload createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public PwcDownload type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwcDownload)) {
            return false;
        }
        return id != null && id.equals(((PwcDownload) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PwcDownload{" +
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
            "}";
    }
}
