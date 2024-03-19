package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjEquip.
 */
@Entity
@IdClass(BidPrjEquipPrimaryKey.class)
@Table(name = "bid_prj_equip")
public class BidPrjEquip implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;

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
    @Size(max = 3)
    @Column(name = "year", length = 3, nullable = false)
    private String year;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @Id
    @Size(max = 5)
    @Column(name = "e_code", length = 5)
    private String eCode;

    @Size(max = 20)
    @Column(name = "e_cnt", length = 20)
    private String eCnt;

    @Size(max = 200)
    @Column(name = "e_remark", length = 200)
    private String eRemark;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 10)
    @Column(name = "sup_ccut", length = 10)
    private String supCcut;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjEquipPrimaryKey getId() {
        return new BidPrjEquipPrimaryKey(wkut,prjno, year, month,eCode);
    }

    public BidPrjEquip id(BidPrjEquipPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjEquipPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.year = id.getYear();
        this.month = id.getMonth();
        this.eCode = id.geteCode();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjEquip wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjEquip prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYear() {
        return this.year;
    }

    public BidPrjEquip year(String year) {
        this.setYear(year);
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjEquip month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String geteCode() {
        return this.eCode;
    }

    public BidPrjEquip eCode(String eCode) {
        this.seteCode(eCode);
        return this;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteCnt() {
        return this.eCnt;
    }

    public BidPrjEquip eCnt(String eCnt) {
        this.seteCnt(eCnt);
        return this;
    }

    public void seteCnt(String eCnt) {
        this.eCnt = eCnt;
    }

    public String geteRemark() {
        return this.eRemark;
    }

    public BidPrjEquip eRemark(String eRemark) {
        this.seteRemark(eRemark);
        return this;
    }

    public void seteRemark(String eRemark) {
        this.eRemark = eRemark;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjEquip uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getSupCcut() {
        return this.supCcut;
    }

    public BidPrjEquip supCcut(String supCcut) {
        this.setSupCcut(supCcut);
        return this;
    }

    public void setSupCcut(String supCcut) {
        this.supCcut = supCcut;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjEquip createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjEquip createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjEquip updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjEquip updateUser(String updateUser) {
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
        if (!(o instanceof BidPrjEquip)) return false;
        BidPrjEquip that = (BidPrjEquip) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && year.equals(that.year) && month.equals(that.month) && eCode.equals(that.eCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, year, month,eCode);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjEquip{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", eCode='" + geteCode() + "'" +
            ", eCnt='" + geteCnt() + "'" +
            ", eRemark='" + geteRemark() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", supCcut='" + getSupCcut() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
