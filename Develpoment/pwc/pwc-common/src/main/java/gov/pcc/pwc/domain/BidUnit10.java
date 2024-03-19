package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidUnit10.
 */
@Entity
@Table(name = "bid_unit10")
public class BidUnit10 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 21)
    @Id
    @Column(name = "code", length = 21)
    private String code;

    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    @Size(max = 60)
    @Column(name = "addr", length = 60)
    private String addr;

    @Size(max = 20)
    @Column(name = "tel", length = 20)
    private String tel;

    @Size(max = 20)
    @Column(name = "fax", length = 20)
    private String fax;

    @Size(max = 4)
    @Column(name = "cdty", length = 4)
    private String cdty;

    @Size(max = 1)
    @Column(name = "type", length = 1)
    private String type;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 100)
    @Column(name = "newcode", length = 100)
    private String newcode;

    @Size(max = 20)
    @Column(name = "name0", length = 20)
    private String name0;

    @Size(max = 8)
    @Column(name = "can_01", length = 8)
    private String can01;

    @Size(max = 20)
    @Column(name = "hot_line", length = 20)
    private String hotLine;

    @Column(name = "capital")
    private BigDecimal capital;

    @Size(max = 7)
    @Column(name = "rejsd", length = 7)
    private String rejsd;

    @Size(max = 7)
    @Column(name = "rejed", length = 7)
    private String rejed;

    @Column(name = "create_date")
    private Instant createDate;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
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

    public BidUnit10 code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public BidUnit10 name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return this.addr;
    }

    public BidUnit10 addr(String addr) {
        this.setAddr(addr);
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return this.tel;
    }

    public BidUnit10 tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public BidUnit10 fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCdty() {
        return this.cdty;
    }

    public BidUnit10 cdty(String cdty) {
        this.setCdty(cdty);
        return this;
    }

    public void setCdty(String cdty) {
        this.cdty = cdty;
    }

    public String getType() {
        return this.type;
    }

    public BidUnit10 type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidUnit10 dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getNewcode() {
        return this.newcode;
    }

    public BidUnit10 newcode(String newcode) {
        this.setNewcode(newcode);
        return this;
    }

    public void setNewcode(String newcode) {
        this.newcode = newcode;
    }

    public String getName0() {
        return this.name0;
    }

    public BidUnit10 name0(String name0) {
        this.setName0(name0);
        return this;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public String getCan01() {
        return this.can01;
    }

    public BidUnit10 can01(String can01) {
        this.setCan01(can01);
        return this;
    }

    public void setCan01(String can01) {
        this.can01 = can01;
    }

    public String getHotLine() {
        return this.hotLine;
    }

    public BidUnit10 hotLine(String hotLine) {
        this.setHotLine(hotLine);
        return this;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public BigDecimal getCapital() {
        return this.capital;
    }

    public BidUnit10 capital(BigDecimal capital) {
        this.setCapital(capital);
        return this;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getRejsd() {
        return this.rejsd;
    }

    public BidUnit10 rejsd(String rejsd) {
        this.setRejsd(rejsd);
        return this;
    }

    public void setRejsd(String rejsd) {
        this.rejsd = rejsd;
    }

    public String getRejed() {
        return this.rejed;
    }

    public BidUnit10 rejed(String rejed) {
        this.setRejed(rejed);
        return this;
    }

    public void setRejed(String rejed) {
        this.rejed = rejed;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidUnit10 createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidUnit10 createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidUnit10 updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidUnit10 updateUser(String updateUser) {
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
        if (!(o instanceof BidUnit10)) {
            return false;
        }
        return code != null && code.equals(((BidUnit10) o).code);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidUnit10{" +
            "code=" + getCode() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", cdty='" + getCdty() + "'" +
            ", type='" + getType() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", newcode='" + getNewcode() + "'" +
            ", name0='" + getName0() + "'" +
            ", can01='" + getCan01() + "'" +
            ", hotLine='" + getHotLine() + "'" +
            ", capital=" + getCapital() +
            ", rejsd='" + getRejsd() + "'" +
            ", rejed='" + getRejed() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
