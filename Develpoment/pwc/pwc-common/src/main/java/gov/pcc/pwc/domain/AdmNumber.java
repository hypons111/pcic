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
 * A AdmNumber.
 */
@Entity
@Table(name = "adm_number")
public class AdmNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(20)
     */
    @Size(max = 50)
    @Schema(description = "type: varchar(50)")
    @Column(name = "code_type", length = 50)
    private String codeType;

    /**
     * type: varchar(3)
     */
    @Size(max = 3)
    @Schema(description = "type: varchar(3)")
    @Column(name = "code_year", length = 3)
    private String codeYear;

    /**
     * type: varchar(2)
     */
    @Size(max = 2)
    @Schema(description = "type: varchar(2)")
    @Column(name = "code_month", length = 2)
    private String codeMonth;

    /**
     * type: varchar(2)
     */
    @Size(max = 2)
    @Schema(description = "type: varchar(2)")
    @Column(name = "code_date", length = 2)
    private String codeDate;

    /**
     * type: varchar(8)
     */
    @NotNull
    @Size(max = 8)
    @Schema(description = "type: varchar(8)", required = true)
    @Column(name = "number", length = 8, nullable = false)
    private String number;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)")
    @Column(name = "update_user", length = 20)
    private String updateUser;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime")
    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmNumber id(Long id) {
        this.id = id;
        return this;
    }

    public String getCodeType() {
        return this.codeType;
    }

    public AdmNumber codeType(String codeType) {
        this.codeType = codeType;
        return this;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeYear() {
        return this.codeYear;
    }

    public AdmNumber codeYear(String codeYear) {
        this.codeYear = codeYear;
        return this;
    }

    public void setCodeYear(String codeYear) {
        this.codeYear = codeYear;
    }

    public String getCodeMonth() {
        return this.codeMonth;
    }

    public AdmNumber codeMonth(String codeMonth) {
        this.codeMonth = codeMonth;
        return this;
    }

    public void setCodeMonth(String codeMonth) {
        this.codeMonth = codeMonth;
    }

    public String getCodeDate() {
        return this.codeDate;
    }

    public AdmNumber codeDate(String codeDate) {
        this.codeDate = codeDate;
        return this;
    }

    public void setCodeDate(String codeDate) {
        this.codeDate = codeDate;
    }

    public String getNumber() {
        return this.number;
    }

    public AdmNumber number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public AdmNumber createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmNumber createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmNumber updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmNumber updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmNumber)) {
            return false;
        }
        return id != null && id.equals(((AdmNumber) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmNumber{" +
            "id=" + getId() +
            ", codeType='" + getCodeType() + "'" +
            ", codeYear='" + getCodeYear() + "'" +
            ", codeMonth='" + getCodeMonth() + "'" +
            ", codeDate='" + getCodeDate() + "'" +
            ", number='" + getNumber() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
