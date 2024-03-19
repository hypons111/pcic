package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidCheckerr.
 */
@Entity
@Table(name = "bid_checkerr")
public class BidCheckerr implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    @Id
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Size(max = 1200)
    @Column(name = "name", length = 1200)
    private String name;

    @Size(max = 1)
    @Column(name = "typ", length = 1)
    private String typ;

    @Column(name = "ord")
    private Integer ord;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

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

    public String getCode() {
        return this.code;
    }

    public BidCheckerr code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public BidCheckerr name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return this.typ;
    }

    public BidCheckerr typ(String typ) {
        this.setTyp(typ);
        return this;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getOrd() {
        return this.ord;
    }

    public BidCheckerr ord(Integer ord) {
        this.setOrd(ord);
        return this;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getFlag() {
        return this.flag;
    }

    public BidCheckerr flag(String flag) {
        this.setFlag(flag);
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidCheckerr createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidCheckerr createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidCheckerr updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidCheckerr updateUser(String updateUser) {
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
        if (!(o instanceof BidCheckerr)) {
            return false;
        }
        return code != null && code.equals(((BidCheckerr) o).code);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidCheckerr{" +
            "code=" + getCode() +
            ", name='" + getName() + "'" +
            ", typ='" + getTyp() + "'" +
            ", ord=" + getOrd() +
            ", flag='" + getFlag() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
