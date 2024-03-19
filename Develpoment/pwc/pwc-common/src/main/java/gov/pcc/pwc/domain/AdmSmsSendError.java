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
 * A AdmSmsSendError.
 */
@Entity
@Table(name = "adm_sms_send_error")
public class AdmSmsSendError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: datetime
     */
    @NotNull
    @Schema(description = "type: datetime", required = true)
    @Column(name = "real_send_time", nullable = false)
    private Instant realSendTime;

    /**
     * type: varchar(50)
     */
    @NotNull
    @Size(max = 50)
    @Schema(description = "type: varchar(50)", required = true)
    @Column(name = "sender", length = 50, nullable = false)
    private String sender;

    /**
     * type: varchar(MAX)
     */
    @NotNull
    @Schema(description = "type: varchar(MAX)", required = true)
    @Column(name = "receiver", nullable = false)
    private String receiver;

    /**
     * type: nvarchar(1000)
     */
    @NotNull
    @Size(max = 1000)
    @Schema(description = "type: nvarchar(1000)", required = true)
    @Column(name = "message", length = 1000, nullable = false)
    private String message;

    /**
     * type: nvarchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: nvarchar(100)", required = true)
    @Column(name = "error_message", length = 100, nullable = false)
    private String errorMessage;

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

    public AdmSmsSendError id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getRealSendTime() {
        return this.realSendTime;
    }

    public AdmSmsSendError realSendTime(Instant realSendTime) {
        this.realSendTime = realSendTime;
        return this;
    }

    public void setRealSendTime(Instant realSendTime) {
        this.realSendTime = realSendTime;
    }

    public String getSender() {
        return this.sender;
    }

    public AdmSmsSendError sender(String sender) {
        this.sender = sender;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public AdmSmsSendError receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return this.message;
    }

    public AdmSmsSendError message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public AdmSmsSendError errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public AdmSmsSendError createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmSmsSendError createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmSmsSendError updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmSmsSendError updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmSmsSendError)) {
            return false;
        }
        return id != null && id.equals(((AdmSmsSendError) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmSmsSendError{" +
            "id=" + getId() +
            ", realSendTime='" + getRealSendTime() + "'" +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", message='" + getMessage() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
