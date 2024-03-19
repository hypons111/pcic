package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidGovCheckerr.
 */
@Entity
@IdClass(BidGovCheckerrPrimaryKey.class)
@Table(name = "bid_gov_checkerr")
public class BidGovCheckerr implements Serializable {

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

    @Id
    @NotNull
    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "err_num", length = 20, nullable = false)
    private String errNum;

    @Size(max = 2000)
    @Column(name = "err_main", length = 2000)
    private String errMain;

    @Size(max = 200)
    @Column(name = "err_rtn", length = 200)
    private String errRtn;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 2)
    @Column(name = "err_flag", length = 2)
    private String errFlag;

    @Column(name = "rove_flag")
    private int roveFlag;

    @Size(max = 10)
    @Column(name = "rove_user", length = 10)
    private String roveUser;

    @Size(max = 10)
    @Column(name = "rove_to", length = 10)
    private String roveTo;

    @Size(max = 1)
    @Column(name = "rove_pcm", length = 1)
    private String rovePcm;

    @Size(max = 1)
    @Column(name = "rove_sc", length = 1)
    private String roveSc;

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

    public BidGovCheckerrPrimaryKey getId() {
        return new BidGovCheckerrPrimaryKey(wkut, prjno, checkDate, errNum);
    }

    public BidGovCheckerr id(BidGovCheckerrPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidGovCheckerrPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidGovCheckerr wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidGovCheckerr prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return this.checkDate;
    }

    public BidGovCheckerr checkDate(LocalDate checkDate) {
        this.setCheckDate(checkDate);
        return this;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getErrNum() {
        return this.errNum;
    }

    public BidGovCheckerr errNum(String errNum) {
        this.setErrNum(errNum);
        return this;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMain() {
        return this.errMain;
    }

    public BidGovCheckerr errMain(String errMain) {
        this.setErrMain(errMain);
        return this;
    }

    public void setErrMain(String errMain) {
        this.errMain = errMain;
    }

    public String getErrRtn() {
        return this.errRtn;
    }

    public BidGovCheckerr errRtn(String errRtn) {
        this.setErrRtn(errRtn);
        return this;
    }

    public void setErrRtn(String errRtn) {
        this.errRtn = errRtn;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidGovCheckerr dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidGovCheckerr uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getErrFlag() {
        return this.errFlag;
    }

    public BidGovCheckerr errFlag(String errFlag) {
        this.setErrFlag(errFlag);
        return this;
    }

    public void setErrFlag(String errFlag) {
        this.errFlag = errFlag;
    }

    public int getRoveFlag() {
        return this.roveFlag;
    }

    public BidGovCheckerr roveFlag(int roveFlag) {
        this.setRoveFlag(roveFlag);
        return this;
    }

    public void setRoveFlag(int roveFlag) {
        this.roveFlag = roveFlag;
    }

    public String getRoveUser() {
        return this.roveUser;
    }

    public BidGovCheckerr roveUser(String roveUser) {
        this.setRoveUser(roveUser);
        return this;
    }

    public void setRoveUser(String roveUser) {
        this.roveUser = roveUser;
    }

    public String getRoveTo() {
        return this.roveTo;
    }

    public BidGovCheckerr roveTo(String roveTo) {
        this.setRoveTo(roveTo);
        return this;
    }

    public void setRoveTo(String roveTo) {
        this.roveTo = roveTo;
    }

    public String getRovePcm() {
        return this.rovePcm;
    }

    public BidGovCheckerr rovePcm(String rovePcm) {
        this.setRovePcm(rovePcm);
        return this;
    }

    public void setRovePcm(String rovePcm) {
        this.rovePcm = rovePcm;
    }

    public String getRoveSc() {
        return this.roveSc;
    }

    public BidGovCheckerr roveSc(String roveSc) {
        this.setRoveSc(roveSc);
        return this;
    }

    public void setRoveSc(String roveSc) {
        this.roveSc = roveSc;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidGovCheckerr createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidGovCheckerr createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidGovCheckerr updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidGovCheckerr updateUser(String updateUser) {
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
        if (!(o instanceof BidGovCheckerr)) return false;
        BidGovCheckerr that = (BidGovCheckerr) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate) && errNum.equals(that.errNum);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovCheckerr{" +
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
