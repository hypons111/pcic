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
 * A AdmLineSend.
 */
@Entity
@Table(name = "adm_line_send")
public class AdmLineSend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * type: varchar(10)
     */
    @NotNull
    @Size(max = 10)
    @Schema(description = "type: varchar(10)", required = true)
    @Column(name = "line_type", length = 10, nullable = false)
    private String lineType;

    /**
     * type: varchar(100)
     */
    @NotNull
    @Size(max = 100)
    @Schema(description = "type: varchar(100)", required = true)
    @Column(name = "source_id", length = 100, nullable = false)
    private String sourceId;

    /**
     * type: varchar(50)
     */
    @Size(max = 50)
    @Schema(description = "type: varchar(50)")
    @Column(name = "sender", length = 50)
    private String sender;

    /**
     * type: varchar(MAX)
     */
    @Schema(description = "type: varchar(MAX)")
    @Column(name = "receiver")
    private String receiver;

    /**
     * type: nvarchar(1000)
     */
    @Size(max = 1000)
    @Schema(description = "type: nvarchar(1000)")
    @Column(name = "message", length = 1000)
    private String message;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime")
    @Column(name = "expect_send_time")
    private Instant expectSendTime;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime")
    @Column(name = "real_send_time")
    private Instant realSendTime;

    /**
     * type: varchar(1)
     */
    @NotNull
    @Size(max = 1)
    @Schema(description = "type: varchar(1)", required = true)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    /**
     * type: nvarchar(MAX)
     */
    @Schema(description = "type: nvarchar(MAX)")
    @Column(name = "process_message")
    private String processMessage;

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

    public AdmLineSend id(Long id) {
        this.id = id;
        return this;
    }

    public String getLineType() {
        return this.lineType;
    }

    public AdmLineSend lineType(String lineType) {
        this.lineType = lineType;
        return this;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public AdmLineSend sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSender() {
        return this.sender;
    }

    public AdmLineSend sender(String sender) {
        this.sender = sender;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public AdmLineSend receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return this.message;
    }

    public AdmLineSend message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getExpectSendTime() {
        return this.expectSendTime;
    }

    public AdmLineSend expectSendTime(Instant expectSendTime) {
        this.expectSendTime = expectSendTime;
        return this;
    }

    public void setExpectSendTime(Instant expectSendTime) {
        this.expectSendTime = expectSendTime;
    }

    public Instant getRealSendTime() {
        return this.realSendTime;
    }

    public AdmLineSend realSendTime(Instant realSendTime) {
        this.realSendTime = realSendTime;
        return this;
    }

    public void setRealSendTime(Instant realSendTime) {
        this.realSendTime = realSendTime;
    }

    public String getStatus() {
        return this.status;
    }

    public AdmLineSend status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessMessage() {
        return this.processMessage;
    }

    public AdmLineSend processMessage(String processMessage) {
        this.processMessage = processMessage;
        return this;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public AdmLineSend createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public AdmLineSend createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public AdmLineSend updateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public AdmLineSend updateTime(Instant updateTime) {
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
        if (!(o instanceof AdmLineSend)) {
            return false;
        }
        return id != null && id.equals(((AdmLineSend) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmLineSend{" +
            "id=" + getId() +
            ", lineType='" + getLineType() + "'" +
            ", sourceId='" + getSourceId() + "'" +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", message='" + getMessage() + "'" +
            ", expectSendTime='" + getExpectSendTime() + "'" +
            ", realSendTime='" + getRealSendTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", processMessage='" + getProcessMessage() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
