package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidGovPrecheck.
 */
@Entity
@Table(name = "bid_gov_precheck")
@IdClass(BidGovPrecheckPrimaryKey.class)
public class BidGovPrecheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 40)
    @Column(name = "prjno", length = 40, nullable = false)
    private String prjno;

    @NotNull
    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    @Column(name = "check_date_end")
    private LocalDate checkDateEnd;

    @Size(max = 21)
    @Column(name = "check_unit", length = 21)
    private String checkUnit;

    @Size(max = 2)
    @Column(name = "check_subunit", length = 2)
    private String checkSubunit;

    @Size(max = 30)
    @Column(name = "checker1", length = 30)
    private String checker1;

    @Size(max = 50)
    @Column(name = "check_leader", length = 50)
    private String checkLeader;

    @Size(max = 200)
    @Column(name = "check_member", length = 200)
    private String checkMember;

    @Size(max = 1)
    @Column(name = "data_status", length = 1)
    private String dataStatus;

    @Size(max = 1)
    @Column(name = "method", length = 1)
    private String method;

    @Size(max = 200)
    @Column(name = "check_purp", length = 200)
    private String checkPurp;

    @Size(max = 20)
    @Column(name = "checker_tel", length = 20)
    private String checkerTel;

    @Size(max = 1)
    @Column(name = "can", length = 1)
    private String can;

    @Size(max = 20)
    @Column(name = "column1", length = 20)
    private String column1;

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

    public BidGovPrecheckPrimaryKey getId() {
        return new BidGovPrecheckPrimaryKey(wkut,prjno,checkDate);
    }

    public BidGovPrecheck id(BidGovPrecheckPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidGovPrecheckPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidGovPrecheck wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidGovPrecheck prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return this.checkDate;
    }

    public BidGovPrecheck checkDate(LocalDate checkDate) {
        this.setCheckDate(checkDate);
        return this;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public LocalDate getCheckDateEnd() {
        return this.checkDateEnd;
    }

    public BidGovPrecheck checkDateEnd(LocalDate checkDateEnd) {
        this.setCheckDateEnd(checkDateEnd);
        return this;
    }

    public void setCheckDateEnd(LocalDate checkDateEnd) {
        this.checkDateEnd = checkDateEnd;
    }

    public String getCheckUnit() {
        return this.checkUnit;
    }

    public BidGovPrecheck checkUnit(String checkUnit) {
        this.setCheckUnit(checkUnit);
        return this;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getCheckSubunit() {
        return this.checkSubunit;
    }

    public BidGovPrecheck checkSubunit(String checkSubunit) {
        this.setCheckSubunit(checkSubunit);
        return this;
    }

    public void setCheckSubunit(String checkSubunit) {
        this.checkSubunit = checkSubunit;
    }

    public String getChecker1() {
        return this.checker1;
    }

    public BidGovPrecheck checker1(String checker1) {
        this.setChecker1(checker1);
        return this;
    }

    public void setChecker1(String checker1) {
        this.checker1 = checker1;
    }

    public String getCheckLeader() {
        return this.checkLeader;
    }

    public BidGovPrecheck checkLeader(String checkLeader) {
        this.setCheckLeader(checkLeader);
        return this;
    }

    public void setCheckLeader(String checkLeader) {
        this.checkLeader = checkLeader;
    }

    public String getCheckMember() {
        return this.checkMember;
    }

    public BidGovPrecheck checkMember(String checkMember) {
        this.setCheckMember(checkMember);
        return this;
    }

    public void setCheckMember(String checkMember) {
        this.checkMember = checkMember;
    }

    public String getDataStatus() {
        return this.dataStatus;
    }

    public BidGovPrecheck dataStatus(String dataStatus) {
        this.setDataStatus(dataStatus);
        return this;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getMethod() {
        return this.method;
    }

    public BidGovPrecheck method(String method) {
        this.setMethod(method);
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCheckPurp() {
        return this.checkPurp;
    }

    public BidGovPrecheck checkPurp(String checkPurp) {
        this.setCheckPurp(checkPurp);
        return this;
    }

    public void setCheckPurp(String checkPurp) {
        this.checkPurp = checkPurp;
    }

    public String getCheckerTel() {
        return this.checkerTel;
    }

    public BidGovPrecheck checkerTel(String checkerTel) {
        this.setCheckerTel(checkerTel);
        return this;
    }

    public void setCheckerTel(String checkerTel) {
        this.checkerTel = checkerTel;
    }

    public String getCan() {
        return this.can;
    }

    public BidGovPrecheck can(String can) {
        this.setCan(can);
        return this;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getColumn1() {
        return this.column1;
    }

    public BidGovPrecheck column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidGovPrecheck createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidGovPrecheck createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidGovPrecheck updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidGovPrecheck updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidGovPrecheck)) return false;
        BidGovPrecheck that = (BidGovPrecheck) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovPrecheck{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", checkDateEnd='" + getCheckDateEnd() + "'" +
            ", checkUnit='" + getCheckUnit() + "'" +
            ", checkSubunit='" + getCheckSubunit() + "'" +
            ", checker1='" + getChecker1() + "'" +
            ", checkLeader='" + getCheckLeader() + "'" +
            ", checkMember='" + getCheckMember() + "'" +
            ", dataStatus='" + getDataStatus() + "'" +
            ", method='" + getMethod() + "'" +
            ", checkPurp='" + getCheckPurp() + "'" +
            ", checkerTel='" + getCheckerTel() + "'" +
            ", can='" + getCan() + "'" +
            ", column1='" + getColumn1() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
