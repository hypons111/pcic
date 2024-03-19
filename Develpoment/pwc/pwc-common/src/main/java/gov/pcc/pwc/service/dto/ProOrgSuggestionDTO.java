package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProOrgSuggestion} entity.
 */
public class ProOrgSuggestionDTO implements Serializable {

    @NotNull
    private Long proOrgSuggestionId;

    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 20)
    private String userId;

    @Size(max = 1)
    private String type;

    @Size(max = 500)
    private String addr;

    private Instant time;

    @Size(max = 2000)
    private String outline;

    @Size(max = 2000)
    private String memo;

    @Size(max = 2000)
    private String other;

    @Size(max = 1)
    private String status;

    private String commReason;

    @Size(max = 1)
    private String writeCondition;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

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
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProOrgSuggestionDTO)) {
            return false;
        }

        ProOrgSuggestionDTO proOrgSuggestionDTO = (ProOrgSuggestionDTO) o;
        if (this.proOrgSuggestionId == null) {
            return false;
        }
        return Objects.equals(this.proOrgSuggestionId, proOrgSuggestionDTO.proOrgSuggestionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proOrgSuggestionId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProOrgSuggestionDTO{" +
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
}
