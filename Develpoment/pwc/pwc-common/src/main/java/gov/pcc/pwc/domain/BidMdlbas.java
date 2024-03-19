package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidMdlbas.
 */
@IdClass(BidMdlbasPrimaryKey.class)
@Entity
@Table(name = "bid_mdlbas")
public class BidMdlbas implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator")
//    @Column(name = "id")
//    private Long id;
    @Id
    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Id
    @Size(max = 20)
    @Column(name = "dis_no", length = 20)
    private String disNo;

    @Size(max = 300)
    @Column(name = "dis_name", length = 300)
    private String disName;

    @Size(max = 200)
    @Column(name = "dis_plce", length = 200)
    private String disPlce;

    @Size(max = 2)
    @Column(name = "work_type", length = 2)
    private String workType;

    @Column(name = "est_budg", precision = 21, scale = 2)
    private BigDecimal estBudg;

    @Size(max = 2000)
    @Column(name = "work_item", length = 2000)
    private String workItem;

    @Column(name = "veri_budg", precision = 21, scale = 2)
    private BigDecimal veriBudg;

    @Column(name = "veri_2_budg", precision = 21, scale = 2)
    private BigDecimal veri2Budg;

    @Size(max = 2000)
    @Column(name = "veri_2_item", length = 2000)
    private String veri2Item;

    @Column(name = "veri_3_budg", precision = 21, scale = 2)
    private BigDecimal veri3Budg;

    @Size(max = 2000)
    @Column(name = "veri_3_item", length = 2000)
    private String veri3Item;

    @Size(max = 200)
    @Column(name = "tm_2_x", length = 200)
    private String tm2x;

    @Size(max = 200)
    @Column(name = "tm_2_y", length = 200)
    private String tm2y;

    @Size(max = 200)
    @Column(name = "noty_budg", length = 200)
    private String notyBudg;

    @Column(name = "noty_budg_0", precision = 21, scale = 2)
    private BigDecimal notyBudg0;

    @Column(name = "veri_3_budg_0", precision = 21, scale = 2)
    private BigDecimal veri3Budg0;

    @Size(max = 1000)
    @Column(name = "dis_rmk", length = 1000)
    private String disRmk;

    @Column(name = "s_actdt")
    private LocalDate sActdt;

    @Column(name = "s_actdt_0")
    private LocalDate sActdt0;

    @Column(name = "s_aedat")
    private LocalDate sAedat;

    @Column(name = "s_aedat_0")
    private LocalDate sAedat0;

    @Size(max = 1000)
    @Column(name = "chg_rmk", length = 1000)
    private String chgRmk;

    @Size(max = 1)
    @Column(name = "del_resn", length = 1)
    private String delResn;

    @Size(max = 1)
    @Column(name = "del_resn_2", length = 1)
    private String delResn2;

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

    public BidMdlbasPrimaryKey getId() {
        return new BidMdlbasPrimaryKey(wkut,disNo);
    }

    public BidMdlbas id(BidMdlbasPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidMdlbasPrimaryKey id) {
        this.wkut = id.getWkut();
        this.disNo = id.getDisNo();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidMdlbas wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getDisNo() {
        return this.disNo;
    }

    public BidMdlbas disNo(String disNo) {
        this.setDisNo(disNo);
        return this;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public String getDisName() {
        return this.disName;
    }

    public BidMdlbas disName(String disName) {
        this.setDisName(disName);
        return this;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisPlce() {
        return this.disPlce;
    }

    public BidMdlbas disPlce(String disPlce) {
        this.setDisPlce(disPlce);
        return this;
    }

    public void setDisPlce(String disPlce) {
        this.disPlce = disPlce;
    }

    public String getWorkType() {
        return this.workType;
    }

    public BidMdlbas workType(String workType) {
        this.setWorkType(workType);
        return this;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public BigDecimal getEstBudg() {
        return this.estBudg;
    }

    public BidMdlbas estBudg(BigDecimal estBudg) {
        this.setEstBudg(estBudg);
        return this;
    }

    public void setEstBudg(BigDecimal estBudg) {
        this.estBudg = estBudg;
    }

    public String getWorkItem() {
        return this.workItem;
    }

    public BidMdlbas workItem(String workItem) {
        this.setWorkItem(workItem);
        return this;
    }

    public void setWorkItem(String workItem) {
        this.workItem = workItem;
    }

    public BigDecimal getVeriBudg() {
        return this.veriBudg;
    }

    public BidMdlbas veriBudg(BigDecimal veriBudg) {
        this.setVeriBudg(veriBudg);
        return this;
    }

    public void setVeriBudg(BigDecimal veriBudg) {
        this.veriBudg = veriBudg;
    }

    public BigDecimal getVeri2Budg() {
        return this.veri2Budg;
    }

    public BidMdlbas veri2Budg(BigDecimal veri2Budg) {
        this.setVeri2Budg(veri2Budg);
        return this;
    }

    public void setVeri2Budg(BigDecimal veri2Budg) {
        this.veri2Budg = veri2Budg;
    }

    public String getVeri2Item() {
        return this.veri2Item;
    }

    public BidMdlbas veri2Item(String veri2Item) {
        this.setVeri2Item(veri2Item);
        return this;
    }

    public void setVeri2Item(String veri2Item) {
        this.veri2Item = veri2Item;
    }

    public BigDecimal getVeri3Budg() {
        return this.veri3Budg;
    }

    public BidMdlbas veri3Budg(BigDecimal veri3Budg) {
        this.setVeri3Budg(veri3Budg);
        return this;
    }

    public void setVeri3Budg(BigDecimal veri3Budg) {
        this.veri3Budg = veri3Budg;
    }

    public String getVeri3Item() {
        return this.veri3Item;
    }

    public BidMdlbas veri3Item(String veri3Item) {
        this.setVeri3Item(veri3Item);
        return this;
    }

    public void setVeri3Item(String veri3Item) {
        this.veri3Item = veri3Item;
    }

    public String getTm2x() {
        return this.tm2x;
    }

    public BidMdlbas tm2x(String tm2x) {
        this.setTm2x(tm2x);
        return this;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return this.tm2y;
    }

    public BidMdlbas tm2y(String tm2y) {
        this.setTm2y(tm2y);
        return this;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getNotyBudg() {
        return this.notyBudg;
    }

    public BidMdlbas notyBudg(String notyBudg) {
        this.setNotyBudg(notyBudg);
        return this;
    }

    public void setNotyBudg(String notyBudg) {
        this.notyBudg = notyBudg;
    }

    public BigDecimal getNotyBudg0() {
        return this.notyBudg0;
    }

    public BidMdlbas notyBudg0(BigDecimal notyBudg0) {
        this.setNotyBudg0(notyBudg0);
        return this;
    }

    public void setNotyBudg0(BigDecimal notyBudg0) {
        this.notyBudg0 = notyBudg0;
    }

    public BigDecimal getVeri3Budg0() {
        return this.veri3Budg0;
    }

    public BidMdlbas veri3Budg0(BigDecimal veri3Budg0) {
        this.setVeri3Budg0(veri3Budg0);
        return this;
    }

    public void setVeri3Budg0(BigDecimal veri3Budg0) {
        this.veri3Budg0 = veri3Budg0;
    }

    public String getDisRmk() {
        return this.disRmk;
    }

    public BidMdlbas disRmk(String disRmk) {
        this.setDisRmk(disRmk);
        return this;
    }

    public void setDisRmk(String disRmk) {
        this.disRmk = disRmk;
    }

    public LocalDate getsActdt() {
        return this.sActdt;
    }

    public BidMdlbas sActdt(LocalDate sActdt) {
        this.setsActdt(sActdt);
        return this;
    }

    public void setsActdt(LocalDate sActdt) {
        this.sActdt = sActdt;
    }

    public LocalDate getsActdt0() {
        return this.sActdt0;
    }

    public BidMdlbas sActdt0(LocalDate sActdt0) {
        this.setsActdt0(sActdt0);
        return this;
    }

    public void setsActdt0(LocalDate sActdt0) {
        this.sActdt0 = sActdt0;
    }

    public LocalDate getsAedat() {
        return this.sAedat;
    }

    public BidMdlbas sAedat(LocalDate sAedat) {
        this.setsAedat(sAedat);
        return this;
    }

    public void setsAedat(LocalDate sAedat) {
        this.sAedat = sAedat;
    }

    public LocalDate getsAedat0() {
        return this.sAedat0;
    }

    public BidMdlbas sAedat0(LocalDate sAedat0) {
        this.setsAedat0(sAedat0);
        return this;
    }

    public void setsAedat0(LocalDate sAedat0) {
        this.sAedat0 = sAedat0;
    }

    public String getChgRmk() {
        return this.chgRmk;
    }

    public BidMdlbas chgRmk(String chgRmk) {
        this.setChgRmk(chgRmk);
        return this;
    }

    public void setChgRmk(String chgRmk) {
        this.chgRmk = chgRmk;
    }

    public String getDelResn() {
        return this.delResn;
    }

    public BidMdlbas delResn(String delResn) {
        this.setDelResn(delResn);
        return this;
    }

    public void setDelResn(String delResn) {
        this.delResn = delResn;
    }

    public String getDelResn2() {
        return this.delResn2;
    }

    public BidMdlbas delResn2(String delResn2) {
        this.setDelResn2(delResn2);
        return this;
    }

    public void setDelResn2(String delResn2) {
        this.delResn2 = delResn2;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidMdlbas createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidMdlbas createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidMdlbas updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidMdlbas updateUser(String updateUser) {
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
        if (!(o instanceof BidMdlbas)) {
            return false;
        }

        BidMdlbas that = (BidMdlbas) o;
        return wkut.equals(that.wkut) && disNo.equals(that.disNo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return Objects.hash(wkut,disNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidMdlbas{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", disNo='" + getDisNo() + "'" +
            ", disName='" + getDisName() + "'" +
            ", disPlce='" + getDisPlce() + "'" +
            ", workType='" + getWorkType() + "'" +
            ", estBudg=" + getEstBudg() +
            ", workItem='" + getWorkItem() + "'" +
            ", veriBudg=" + getVeriBudg() +
            ", veri2Budg=" + getVeri2Budg() +
            ", veri2Item='" + getVeri2Item() + "'" +
            ", veri3Budg=" + getVeri3Budg() +
            ", veri3Item='" + getVeri3Item() + "'" +
            ", tm2x='" + getTm2x() + "'" +
            ", tm2y='" + getTm2y() + "'" +
            ", notyBudg='" + getNotyBudg() + "'" +
            ", notyBudg0=" + getNotyBudg0() +
            ", veri3Budg0=" + getVeri3Budg0() +
            ", disRmk='" + getDisRmk() + "'" +
            ", sActdt='" + getsActdt() + "'" +
            ", sActdt0='" + getsActdt0() + "'" +
            ", sAedat='" + getsAedat() + "'" +
            ", sAedat0='" + getsAedat0() + "'" +
            ", chgRmk='" + getChgRmk() + "'" +
            ", delResn='" + getDelResn() + "'" +
            ", delResn2='" + getDelResn2() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
