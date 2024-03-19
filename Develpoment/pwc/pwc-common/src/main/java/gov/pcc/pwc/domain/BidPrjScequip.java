package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjScequip.
 */
@Entity
@IdClass(BidPrjScequipPrimaryKey.class)
@Table(name = "bid_prj_scequip")
public class BidPrjScequip implements Serializable {

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
    @Size(max = 20)
    @Column(name = "sub_ccut", length = 20)
    private String subCcut;

    @Id
    @Size(max = 5)
    @Column(name = "e_code", length = 5)
    private String eCode;

    @Size(max = 20)
    @Column(name = "e_cnt", length = 20)
    private String eCnt;

    @Size(max = 2000)
    @Column(name = "e_remark", length = 2000)
    private String eRemark;

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

    @Size(max = 1)
    @Column(name = "confA", length = 1)
    private String confA;

    @Size(max = 1)
    @Column(name = "confB", length = 1)
    private String confB;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    public BidPrjScequipPrimaryKey getId() {
        return new BidPrjScequipPrimaryKey(wkut, prjno, subCcut, eCode);
    }

    public BidPrjScequip id(BidPrjScequipPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjScequipPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.subCcut = id.getSubCcut();
        this.eCode = id.geteCode();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjScequip wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjScequip prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getSubCcut() {
        return this.subCcut;
    }

    public BidPrjScequip subCcut(String subCcut) {
        this.setSubCcut(subCcut);
        return this;
    }

    public void setSubCcut(String subCcut) {
        this.subCcut = subCcut;
    }

    public String geteCode() {
        return this.eCode;
    }

    public BidPrjScequip eCode(String eCode) {
        this.seteCode(eCode);
        return this;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteCnt() {
        return this.eCnt;
    }

    public BidPrjScequip eCnt(String eCnt) {
        this.seteCnt(eCnt);
        return this;
    }

    public void seteCnt(String eCnt) {
        this.eCnt = eCnt;
    }

    public String geteRemark() {
        return this.eRemark;
    }

    public BidPrjScequip eRemark(String eRemark) {
        this.seteRemark(eRemark);
        return this;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjScequip createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjScequip createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjScequip updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjScequip updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getConfA() {
        return this.confA;
    }

    public BidPrjScequip confA(String confA) {
        this.setConfA(confA);
        return this;
    }

    public void setConfA(String confA) {
        this.confA = confA;
    }

    public String getConfB() {
        return this.confB;
    }

    public BidPrjScequip confB(String confB) {
        this.setConfB(confB);
        return this;
    }

    public void setConfB(String confB) {
        this.confB = confB;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjScequip uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjScequip)) {
            return false;
        }
        BidPrjScequip that = (BidPrjScequip) o;
        return (
                wkut.equals(that.wkut) &&
                        prjno.equals(that.prjno) &&
                        subCcut.equals(that.subCcut) &&
                        eCode.equals(that.eCode)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, subCcut,eCode);
    }

    @Override
    public String toString() {
        return "BidPrjScequip{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", subCcut='" + subCcut + '\'' +
                ", eCode='" + eCode + '\'' +
                ", eCnt='" + eCnt + '\'' +
                ", eRemark='" + eRemark + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", updateDate=" + updateDate +
                ", updateUser='" + updateUser + '\'' +
                ", confA='" + confA + '\'' +
                ", confB='" + confB + '\'' +
                ", uIp='" + uIp + '\'' +
                '}';
    }
}
