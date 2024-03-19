package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidQcReentry} entity.
 */
public class BidQcReentryDTO implements Serializable {

    @NotNull
    @Size(max = 10)
    private String id;

    @NotNull
    @Size(max = 2)
    private String fullTime;

    @NotNull
    private LocalDate reStartDate;

    private LocalDate reEndDate;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public LocalDate getReStartDate() {
        return reStartDate;
    }

    public void setReStartDate(LocalDate reStartDate) {
        this.reStartDate = reStartDate;
    }

    public LocalDate getReEndDate() {
        return reEndDate;
    }

    public void setReEndDate(LocalDate reEndDate) {
        this.reEndDate = reEndDate;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidQcReentryDTO)) {
            return false;
        }

        BidQcReentryDTO bidQcReentryDTO = (BidQcReentryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bidQcReentryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidQcReentryDTO{" +
            "id='" + getId() + "'" +
            ", fullTime='" + getFullTime() + "'" +
            ", reStartDate='" + getReStartDate() + "'" +
            ", reEndDate='" + getReEndDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
