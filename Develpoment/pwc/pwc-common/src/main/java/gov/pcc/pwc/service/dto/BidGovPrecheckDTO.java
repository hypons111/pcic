package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidGovPrecheck;
import gov.pcc.pwc.domain.BidGovPrecheckPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidGovPrecheck} entity.
 */
public class BidGovPrecheckDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate checkDate;

    private LocalDate checkDateEnd;

    @Size(max = 21)
    private String checkUnit;

    @Size(max = 2)
    private String checkSubunit;

    @Size(max = 30)
    private String checker1;

    @Size(max = 50)
    private String checkLeader;

    @Size(max = 200)
    private String checkMember;

    @Size(max = 1)
    private String dataStatus;

    @Size(max = 1)
    private String method;

    @Size(max = 200)
    private String checkPurp;

    @Size(max = 20)
    private String checkerTel;

    @Size(max = 1)
    private String can;

    @Size(max = 20)
    private String column1;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidGovPrecheckPrimaryKey getId() {
        return new BidGovPrecheckPrimaryKey(this.wkut,this.prjno,this.checkDate);
    }

    public void setId(BidGovPrecheckPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public LocalDate getCheckDateEnd() {
        return checkDateEnd;
    }

    public void setCheckDateEnd(LocalDate checkDateEnd) {
        this.checkDateEnd = checkDateEnd;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getCheckSubunit() {
        return checkSubunit;
    }

    public void setCheckSubunit(String checkSubunit) {
        this.checkSubunit = checkSubunit;
    }

    public String getChecker1() {
        return checker1;
    }

    public void setChecker1(String checker1) {
        this.checker1 = checker1;
    }

    public String getCheckLeader() {
        return checkLeader;
    }

    public void setCheckLeader(String checkLeader) {
        this.checkLeader = checkLeader;
    }

    public String getCheckMember() {
        return checkMember;
    }

    public void setCheckMember(String checkMember) {
        this.checkMember = checkMember;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCheckPurp() {
        return checkPurp;
    }

    public void setCheckPurp(String checkPurp) {
        this.checkPurp = checkPurp;
    }

    public String getCheckerTel() {
        return checkerTel;
    }

    public void setCheckerTel(String checkerTel) {
        this.checkerTel = checkerTel;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
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
        if (this == o) return true;
        if (!(o instanceof BidGovPrecheckDTO)) return false;
        BidGovPrecheckDTO that = (BidGovPrecheckDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovPrecheckDTO{" +
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

    public String getIdString(){
        return  getWkut()+"-"+getPrjno()+"-"+getCheckDate().toString();
    }
}
