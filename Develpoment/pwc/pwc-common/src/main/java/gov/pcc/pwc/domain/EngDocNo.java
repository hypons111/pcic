package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngDocNo.
 */
@Entity
@Table(name = "eng_doc_no")
@IdClass(EngDocNoPK.class)
public class EngDocNo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 2)
    @Id
    @Column(name = "eng_doc_no_type", length = 2, nullable = false)
    private String engDocNoType;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "eng_doc_no_no", length = 20, nullable = false)
    private String engDocNoNo;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngDocNoType() {
        return this.engDocNoType;
    }

    public EngDocNo engDocNoType(String engDocNoType) {
        this.setEngDocNoType(engDocNoType);
        return this;
    }

    public void setEngDocNoType(String engDocNoType) {
        this.engDocNoType = engDocNoType;
    }

    public String getEngDocNoNo() {
        return this.engDocNoNo;
    }

    public EngDocNo engDocNoNo(String engDocNoNo) {
        this.setEngDocNoNo(engDocNoNo);
        return this;
    }

    public void setEngDocNoNo(String engDocNoNo) {
        this.engDocNoNo = engDocNoNo;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngDocNo updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngDocNo updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngDocNo createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngDocNo createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngDocNo transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngDocNo transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngDocNo)) {
            return false;
        }
        return engDocNoType != null && engDocNoType.equals(((EngDocNo) o).engDocNoType);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngDocNo{" +
            "engDocNoType=" + getEngDocNoType() +
            ", engDocNoNo='" + getEngDocNoNo() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
