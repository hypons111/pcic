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
 * A AdmFunctionClick.
 */
@Entity
@Table(name = "adm_function_click")
public class AdmFunctionClick implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "user_id", length = 20, nullable = true)
    private String userId;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_id", length = 20, nullable = false)
    private String functionId;

    /**
     * type: varchar(20)
     */
    @NotNull
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    @Column(name = "function_extend_id", length = 20, nullable = false)
    private String functionExtendId;

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

    public AdmFunctionClick id(Long id) {
        this.id = id;
        return this;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public AdmFunctionClick userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public AdmFunctionClick functionId(String functionId) {
        this.functionId = functionId;
        return this;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionExtendId() {
        return this.functionExtendId;
    }

    public AdmFunctionClick functionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
        return this;
    }

    public void setFunctionExtendId(String functionExtendId) {
        this.functionExtendId = functionExtendId;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmFunctionClick createTime(Instant createTime) {
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
        if (!(o instanceof AdmFunctionClick)) {
            return false;
        }
        return id != null && id.equals(((AdmFunctionClick) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmFunctionClick{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", functionId='" + getFunctionId() + "'" +
            ", functionExtendId='" + getFunctionExtendId() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
