package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.AdmPadHis} entity.
 */
public class AdmPadHisDTO implements Serializable {

    /**
     * type: bigint
     */
    @Schema(description = "type: bigint", required = true)
    private Long id;

    /**
     * type: varchar(20)
     */
    @Size(max = 20)
    @Schema(description = "type: varchar(20)", required = true)
    private String userId;

    /**
     * type: varchar(max)
     */
    @Schema(description = "type: varchar(max)", required = true)
    private String userPadHash;

    /**
     * type: datetime
     */
    @Schema(description = "type: datetime", required = true)
    private Instant createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPadHash() {
        return userPadHash;
    }

    public void setUserPadHash(String userPadHash) {
        this.userPadHash = userPadHash;
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
        if (!(o instanceof AdmPadHisDTO)) {
            return false;
        }

        AdmPadHisDTO admPadHisDTO = (AdmPadHisDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, admPadHisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdmPadHisDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", userPadHash='" + getUserPadHash() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
