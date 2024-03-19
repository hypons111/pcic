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
 * A AdmPadHis.
 */
@Entity
@Table(name = "adm_pad_his")
public class AdmPadHis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "user_id", length = 20, nullable = false)
    private String userId;

    /**
     * type: varchar(max)
     */
    @NotNull
    @Schema(description = "type: varchar(max)", required = true)
    @Column(name = "user_pad_hash", nullable = false)
    private String userPadHash;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmPadHis id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public AdmPadHis userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPadHash() {
        return this.userPadHash;
    }

    public AdmPadHis userPadHash(String userPadHash) {
        this.userPadHash = userPadHash;
        return this;
    }

    public void setUserPadHash(String userPadHash) {
        this.userPadHash = userPadHash;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmPadHis createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmPadHis)) {
            return false;
        }
        return id != null && id.equals(((AdmPadHis) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmPadHis{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", userPadHash='" + getUserPadHash() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
