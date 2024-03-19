package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidWkmengReentry.
 */
@Entity
@Table(name = "bid_wkmeng_reentry")
public class BidWkmengReentry implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 10)
    @Id
    @Column(name = "id", length = 10, nullable = false)
    private String id;

    @NotNull
    @Size(max = 2)
    @Column(name = "full_time", length = 2, nullable = false)
    private String fullTime;

    @NotNull
    @Column(name = "re_start_date", nullable = false)
    private LocalDate reStartDate;

    @Column(name = "re_end_date")
    private LocalDate reEndDate;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Column(name = "update_date")
    private Instant updateDate;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public BidWkmengReentry id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullTime() {
        return this.fullTime;
    }

    public BidWkmengReentry fullTime(String fullTime) {
        this.setFullTime(fullTime);
        return this;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public LocalDate getReStartDate() {
        return this.reStartDate;
    }

    public BidWkmengReentry reStartDate(LocalDate reStartDate) {
        this.setReStartDate(reStartDate);
        return this;
    }

    public void setReStartDate(LocalDate reStartDate) {
        this.reStartDate = reStartDate;
    }

    public LocalDate getReEndDate() {
        return this.reEndDate;
    }

    public BidWkmengReentry reEndDate(LocalDate reEndDate) {
        this.setReEndDate(reEndDate);
        return this;
    }

    public void setReEndDate(LocalDate reEndDate) {
        this.reEndDate = reEndDate;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidWkmengReentry createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidWkmengReentry createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidWkmengReentry updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidWkmengReentry updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidWkmengReentry)) {
            return false;
        }
        return id != null && id.equals(((BidWkmengReentry) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidWkmengReentry{" +
            "id=" + getId() +
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
