package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProOrgSuggestion.
 */
@Entity
@Table(name = "pro_org_suggestion")
public class ProOrgSuggestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pro_org_suggestion_id", nullable = false)
    private Long proOrgSuggestionId;

    @Size(max = 10)
    @Column(name = "pro_innovative_product_id", length = 10)
    private String proInnovativeProductId;

    @Size(max = 20)
    @Column(name = "user_id", length = 20)
    private String userId;

    @Size(max = 20)
    @Column(name = "org_id", length = 20)
    private String orgId;

    @Size(max = 1)
    @Column(name = "type", length = 1)
    private String type;

    @Size(max = 500)
    @Column(name = "addr", length = 500)
    private String addr;

    @Column(name = "time")
    private Instant time;

    @Size(max = 2000)
    @Column(name = "outline", length = 2000)
    private String outline;

    @Size(max = 2000)
    @Column(name = "memo", length = 2000)
    private String memo;

    @Size(max = 2000)
    @Column(name = "other", length = 2000)
    private String other;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "comm_reason")
    private String commReason;

    @Size(max = 1)
    @Column(name = "write_condition", length = 1)
    private String writeCondition;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getProOrgSuggestionId() {
        return proOrgSuggestionId;
    }

    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
        this.proOrgSuggestionId = proOrgSuggestionId;
    }

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommReason() {
        return commReason;
    }

    public void setCommReason(String commReason) {
        this.commReason = commReason;
    }

    public String getWriteCondition() {
        return writeCondition;
    }

    public void setWriteCondition(String writeCondition) {
        this.writeCondition = writeCondition;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProOrgSuggestion that = (ProOrgSuggestion) o;
        return Objects.equals(proOrgSuggestionId, that.proOrgSuggestionId) && Objects.equals(proInnovativeProductId, that.proInnovativeProductId) && Objects.equals(userId, that.userId) && Objects.equals(orgId, that.orgId) && Objects.equals(type, that.type) && Objects.equals(addr, that.addr) && Objects.equals(time, that.time) && Objects.equals(outline, that.outline) && Objects.equals(memo, that.memo) && Objects.equals(other, that.other) && Objects.equals(status, that.status) && Objects.equals(commReason, that.commReason) && Objects.equals(writeCondition, that.writeCondition) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime) && Objects.equals(createUser, that.createUser) && Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proOrgSuggestionId, proInnovativeProductId, userId, orgId, type, addr, time, outline, memo, other, status, commReason, writeCondition, updateUser, updateTime, createUser, createTime);
    }

    @Override
    public String toString() {
        return "ProOrgSuggestion{" +
            "proOrgSuggestionId=" + getProOrgSuggestionId() +
            ", proInnovativeProductId='" + getProInnovativeProductId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", type='" + getType() + "'" +
            ", addr='" + getAddr() + "'" +
            ", time='" + getTime() + "'" +
            ", outline='" + getOutline() + "'" +
            ", memo='" + getMemo() + "'" +
            ", other='" + getOther() + "'" +
            ", status='" + getStatus() + "'" +
            ", commReason='" + getCommReason() + "'" +
            ", writeCondition='" + getWriteCondition() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
//    public Long getProOrgSuggestionId() {
//        return this.proOrgSuggestionId;
//    }
//
    public ProOrgSuggestion proOrgSuggestionId(Long proOrgSuggestionId) {
        this.setProOrgSuggestionId(proOrgSuggestionId);
        return this;
    }

//    public void setProOrgSuggestionId(Long proOrgSuggestionId) {
//        this.proOrgSuggestionId = proOrgSuggestionId;
//    }
//
//    public String getProInnovativeProductId() {
//        return this.proInnovativeProductId;
//    }
//
    public ProOrgSuggestion proInnovativeProductId(String proInnovativeProductId) {
        this.setProInnovativeProductId(proInnovativeProductId);
        return this;
    }
//
//    public void setProInnovativeProductId(String proInnovativeProductId) {
//        this.proInnovativeProductId = proInnovativeProductId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
    public ProOrgSuggestion userId(String userId) {
        this.setUserId(userId);
        return this;
    }
//
//    public String getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(String orgId) {
//        this.orgId = orgId;
//    }
//
//    public ProOrgSuggestion orgId(String orgId) {
//        this.setOrgId(orgId);
//        return this;
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getType() {
//        return this.type;
//    }
//
    public ProOrgSuggestion type(String type) {
        this.setType(type);
        return this;
    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getAddr() {
//        return this.addr;
//    }
//
    public ProOrgSuggestion addr(String addr) {
        this.setAddr(addr);
        return this;
    }
//
//    public void setAddr(String addr) {
//        this.addr = addr;
//    }
//
//    public Instant getTime() {
//        return this.time;
//    }
//
    public ProOrgSuggestion time(Instant time) {
        this.setTime(time);
        return this;
    }
//
//    public void setTime(Instant time) {
//        this.time = time;
//    }
//
//    public String getOutline() {
//        return this.outline;
//    }
//
    public ProOrgSuggestion outline(String outline) {
        this.setOutline(outline);
        return this;
    }
//
//    public void setOutline(String outline) {
//        this.outline = outline;
//    }
//
//    public String getMemo() {
//        return this.memo;
//    }
//
    public ProOrgSuggestion memo(String memo) {
        this.setMemo(memo);
        return this;
    }
//
//    public void setMemo(String memo) {
//        this.memo = memo;
//    }
//
//    public String getOther() {
//        return this.other;
//    }
//
    public ProOrgSuggestion other(String other) {
        this.setOther(other);
        return this;
    }
//
//    public void setOther(String other) {
//        this.other = other;
//    }
//
//    public String getStatus() {
//        return this.status;
//    }
//
    public ProOrgSuggestion status(String status) {
        this.setStatus(status);
        return this;
    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getCommReason() {
//        return this.commReason;
//    }
//
    public ProOrgSuggestion commReason(String commReason) {
        this.setCommReason(commReason);
        return this;
    }
//
//    public void setCommReason(String commReason) {
//        this.commReason = commReason;
//    }
//
//    public String getWriteCondition() {
//        return this.writeCondition;
//    }
//
    public ProOrgSuggestion writeCondition(String writeCondition) {
        this.setWriteCondition(writeCondition);
        return this;
    }
//
//    public void setWriteCondition(String writeCondition) {
//        this.writeCondition = writeCondition;
//    }
//
//    public String getUpdateUser() {
//        return this.updateUser;
//    }
//
    public ProOrgSuggestion updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }
//
//    public void setUpdateUser(String updateUser) {
//        this.updateUser = updateUser;
//    }
//
//    public Instant getUpdateTime() {
//        return this.updateTime;
//    }
//
    public ProOrgSuggestion updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }
//
//    public void setUpdateTime(Instant updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public String getCreateUser() {
//        return this.createUser;
//    }
//
    public ProOrgSuggestion createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }
//
//    public void setCreateUser(String createUser) {
//        this.createUser = createUser;
//    }
//
//    public Instant getCreateTime() {
//        return this.createTime;
//    }
//
    public ProOrgSuggestion createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }
//
//    public void setCreateTime(Instant createTime) {
//        this.createTime = createTime;
//    }
//
//    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof ProOrgSuggestion)) {
//            return false;
//        }
//        return proOrgSuggestionId != null && proOrgSuggestionId.equals(((ProOrgSuggestion) o).proOrgSuggestionId);
//    }
//
//    @Override
//    public int hashCode() {
//        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
//        return getClass().hashCode();
//    }
//
//    // prettier-ignore
//



}
