package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AdmContact.
 */
@Entity
@Table(name = "adm_contact")
public class AdmContact implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * type: VARCHAR(20)
     */
    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * type: NVARCHAR(50)
     */
    @Size(max = 50)
    @Column(name = "ch_name", length = 50)
    private String chName;

    /**
     * type: NVARCHAR(60)
     */
    @Size(max = 60)
    @Column(name = "title", length = 60)
    private String title;

    /**
     * type: DATE
     */
    @Column(name = "birthday")
    private Instant birthday;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "idno", length = 10)
    private String idno;

    /**
     * type: VARCHAR(1)
     */
    @Size(max = 1)
    @Column(name = "sex", length = 1)
    private String sex;

    /**
     * type: VARCHAR(20)
     */
    @Size(max = 20)
    @Column(name = "work_tel", length = 20)
    private String workTel;

    /**
     * type: VARCHAR(15)
     */
    @Size(max = 15)
    @Column(name = "mobile_tel", length = 15)
    private String mobileTel;

    /**
     * type: VARCHAR(100)
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "home_addr_zip", length = 10)
    private String homeAddrZip;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "home_addr_city", length = 10)
    private String homeAddrCity;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "home_addr_town", length = 10)
    private String homeAddrTown;

    /**
     * type: NVARCHAR(100)
     */
    @Size(max = 100)
    @Column(name = "home_addr_other", length = 100)
    private String homeAddrOther;

    /**
     * type: VARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "work_addr_zip", length = 10)
    private String workAddrZip;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "work_addr_city", length = 10)
    private String workAddrCity;

    /**
     * type: NVARCHAR(10)
     */
    @Size(max = 10)
    @Column(name = "work_addr_town", length = 10)
    private String workAddrTown;

    /**
     * type: NVARCHAR(100)
     */
    @Size(max = 100)
    @Column(name = "work_addr_other", length = 100)
    private String workAddrOther;

    /**
     * type: VARCHAR(100)
     */
    @Size(max = 100)
    @Column(name = "line_token", length = 100)
    private String lineToken;

    /**
     * type: VARCHAR(20)
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

    /**
     * type: VARCHAR(50)
     */
    @Size(max = 20)
    @Column(name = "ch_name_rome", length = 20)
    private String chNameRome;

    @Column(name = "FAX", length = 20)
    private String fax;

    @Column(name = "UNIT", length = 50)
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getUserId() {
        return this.userId;
    }

    public AdmContact userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChName() {
        return this.chName;
    }

    public AdmContact chName(String chName) {
        this.setChName(chName);
        return this;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getTitle() {
        return this.title;
    }

    public AdmContact title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getBirthday() {
        return this.birthday;
    }

    public AdmContact birthday(Instant birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getIdno() {
        return this.idno;
    }

    public AdmContact idno(String idno) {
        this.setIdno(idno);
        return this;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSex() {
        return this.sex;
    }

    public AdmContact sex(String sex) {
        this.setSex(sex);
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkTel() {
        return this.workTel;
    }

    public AdmContact workTel(String workTel) {
        this.setWorkTel(workTel);
        return this;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getMobileTel() {
        return this.mobileTel;
    }

    public AdmContact mobileTel(String mobileTel) {
        this.setMobileTel(mobileTel);
        return this;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getEmail() {
        return this.email;
    }

    public AdmContact email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddrZip() {
        return this.homeAddrZip;
    }

    public AdmContact homeAddrZip(String homeAddrZip) {
        this.setHomeAddrZip(homeAddrZip);
        return this;
    }

    public void setHomeAddrZip(String homeAddrZip) {
        this.homeAddrZip = homeAddrZip;
    }

    public String getHomeAddrCity() {
        return this.homeAddrCity;
    }

    public AdmContact homeAddrCity(String homeAddrCity) {
        this.setHomeAddrCity(homeAddrCity);
        return this;
    }

    public void setHomeAddrCity(String homeAddrCity) {
        this.homeAddrCity = homeAddrCity;
    }

    public String getHomeAddrTown() {
        return this.homeAddrTown;
    }

    public AdmContact homeAddrTown(String homeAddrTown) {
        this.setHomeAddrTown(homeAddrTown);
        return this;
    }

    public void setHomeAddrTown(String homeAddrTown) {
        this.homeAddrTown = homeAddrTown;
    }

    public String getHomeAddrOther() {
        return this.homeAddrOther;
    }

    public AdmContact homeAddrOther(String homeAddrOther) {
        this.setHomeAddrOther(homeAddrOther);
        return this;
    }

    public void setHomeAddrOther(String homeAddrOther) {
        this.homeAddrOther = homeAddrOther;
    }

    public String getWorkAddrZip() {
        return this.workAddrZip;
    }

    public AdmContact workAddrZip(String workAddrZip) {
        this.setWorkAddrZip(workAddrZip);
        return this;
    }

    public void setWorkAddrZip(String workAddrZip) {
        this.workAddrZip = workAddrZip;
    }

    public String getWorkAddrCity() {
        return this.workAddrCity;
    }

    public AdmContact workAddrCity(String workAddrCity) {
        this.setWorkAddrCity(workAddrCity);
        return this;
    }

    public void setWorkAddrCity(String workAddrCity) {
        this.workAddrCity = workAddrCity;
    }

    public String getWorkAddrTown() {
        return this.workAddrTown;
    }

    public AdmContact workAddrTown(String workAddrTown) {
        this.setWorkAddrTown(workAddrTown);
        return this;
    }

    public void setWorkAddrTown(String workAddrTown) {
        this.workAddrTown = workAddrTown;
    }

    public String getWorkAddrOther() {
        return this.workAddrOther;
    }

    public AdmContact workAddrOther(String workAddrOther) {
        this.setWorkAddrOther(workAddrOther);
        return this;
    }

    public void setWorkAddrOther(String workAddrOther) {
        this.workAddrOther = workAddrOther;
    }

    public String getLineToken() {
        return this.lineToken;
    }

    public AdmContact lineToken(String lineToken) {
        this.setLineToken(lineToken);
        return this;
    }

    public void setLineToken(String lineToken) {
        this.lineToken = lineToken;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmContact updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmContact updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getChNameRome() {
        return this.chNameRome;
    }

    public AdmContact chNameRome(String chNameRome) {
        this.setChNameRome(chNameRome);
        return this;
    }

    public void setChNameRome(String chNameRome) {
        this.chNameRome = chNameRome;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmContact)) {
            return false;
        }
        return userId != null && userId.equals(((AdmContact) o).userId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmContact{" +
            "userId=" + getUserId() +
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
