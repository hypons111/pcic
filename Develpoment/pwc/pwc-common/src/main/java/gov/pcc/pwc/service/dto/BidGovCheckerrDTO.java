package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidGovCheckerrPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidGovCheckerr} entity.
 */
public class BidGovCheckerrDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate checkDate;

    @NotNull
    @Size(max = 20)
    private String errNum;

    @Size(max = 2000)
    private String errMain;

    @Size(max = 200)
    private String errRtn;

    @Size(max = 1)
    private String dsts;

    @Size(max = 60)
    private String uIp;

    @Size(max = 2)
    private String errFlag;

    private int roveFlag;

    @Size(max = 10)
    private String roveUser;

    @Size(max = 10)
    private String roveTo;

    @Size(max = 1)
    private String rovePcm;

    @Size(max = 1)
    private String roveSc;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidGovCheckerrPrimaryKey getId() {
        return new BidGovCheckerrPrimaryKey(this.wkut, this.prjno, this.checkDate, this.errNum);
    }

    public void setId(BidGovCheckerrPrimaryKey id) {
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

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMain() {
        return errMain;
    }

    public void setErrMain(String errMain) {
        this.errMain = errMain;
    }

    public String getErrRtn() {
        return errRtn;
    }

    public void setErrRtn(String errRtn) {
        this.errRtn = errRtn;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getErrFlag() {
        return errFlag;
    }

    public void setErrFlag(String errFlag) {
        this.errFlag = errFlag;
    }

    public int getRoveFlag() {
        return roveFlag;
    }

    public void setRoveFlag(int roveFlag) {
        this.roveFlag = roveFlag;
    }

    public String getRoveUser() {
        return roveUser;
    }

    public void setRoveUser(String roveUser) {
        this.roveUser = roveUser;
    }

    public String getRoveTo() {
        return roveTo;
    }

    public void setRoveTo(String roveTo) {
        this.roveTo = roveTo;
    }

    public String getRovePcm() {
        return rovePcm;
    }

    public void setRovePcm(String rovePcm) {
        this.rovePcm = rovePcm;
    }

    public String getRoveSc() {
        return roveSc;
    }

    public void setRoveSc(String roveSc) {
        this.roveSc = roveSc;
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
        if (!(o instanceof BidGovCheckerrDTO)) return false;
        BidGovCheckerrDTO that = (BidGovCheckerrDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate) && errNum.equals(that.errNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate, errNum);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovCheckerrDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", errNum='" + getErrNum() + "'" +
            ", errMain='" + getErrMain() + "'" +
            ", errRtn='" + getErrRtn() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", errFlag='" + getErrFlag() + "'" +
            ", roveFlag=" + getRoveFlag() +
            ", roveUser='" + getRoveUser() + "'" +
            ", roveTo='" + getRoveTo() + "'" +
            ", rovePcm='" + getRovePcm() + "'" +
            ", roveSc='" + getRoveSc() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
