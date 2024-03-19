package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngDocNo} entity.
 */
public class EngDocNoDTO implements Serializable {

    @NotNull
    @Size(max = 2)
    private String engDocNoType;

    @NotNull
    @Size(max = 20)
    private String engDocNoNo;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngDocNoType() {
        return engDocNoType;
    }

    public void setEngDocNoType(String engDocNoType) {
        this.engDocNoType = engDocNoType;
    }

    public String getEngDocNoNo() {
        return engDocNoNo;
    }

    public void setEngDocNoNo(String engDocNoNo) {
        this.engDocNoNo = engDocNoNo;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngDocNoDTO)) {
            return false;
        }

        EngDocNoDTO engDocNoDTO = (EngDocNoDTO) o;
        if (this.engDocNoType == null) {
            return false;
        }
        return Objects.equals(this.engDocNoType, engDocNoDTO.engDocNoType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engDocNoType);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngDocNoDTO{" +
            "engDocNoType='" + getEngDocNoType() + "'" +
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
