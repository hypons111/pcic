package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjRescis.
 */
@Entity
@IdClass(BidPrjRescisPrimaryKey.class)
@Table(name = "bid_prj_rescis")
public class BidPrjRescis implements Serializable {

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
    @Column(name = "stop_date", nullable = false)
    private LocalDate stopDate;

    @Size(max = 1)
    @Column(name = "contract_type", length = 1)
    private String contractType;

    @Size(max = 100)
    @Column(name = "prm_no", length = 100)
    private String prmNo;

    @Size(max = 2)
    @Column(name = "stop_reson", length = 2)
    private String stopReson;

    @Size(max = 1)
    @Column(name = "blong", length = 1)
    private String blong;

    @Size(max = 2000)
    @Column(name = "stop_remark", length = 2000)
    private String stopRemark;

    @Size(max = 2000)
    @Column(name = "contract_item", length = 2000)
    private String contractItem;

    @Size(max = 1)
    @Column(name = "is_redo", length = 1)
    private String isRedo;

    @Column(name = "redo_date")
    private LocalDate redoDate;

    @Size(max = 40)
    @Column(name = "redo_prjno", length = 40)
    private String redoPrjno;

    @Size(max = 2000)
    @Column(name = "no_redo_reson", length = 2000)
    private String noRedoReson;

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

    public BidPrjRescisPrimaryKey getId() {
        return new BidPrjRescisPrimaryKey(this.wkut,this.prjno,this.stopDate);
    }

    public BidPrjRescis id(BidPrjRescisPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPrjRescisPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.stopDate = id.getStopDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjRescis wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjRescis prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getStopDate() {
        return this.stopDate;
    }

    public BidPrjRescis stopDate(LocalDate stopDate) {
        this.setStopDate(stopDate);
        return this;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public String getContractType() {
        return this.contractType;
    }

    public BidPrjRescis contractType(String contractType) {
        this.setContractType(contractType);
        return this;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPrmNo() {
        return this.prmNo;
    }

    public BidPrjRescis prmNo(String prmNo) {
        this.setPrmNo(prmNo);
        return this;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getStopReson() {
        return this.stopReson;
    }

    public BidPrjRescis stopReson(String stopReson) {
        this.setStopReson(stopReson);
        return this;
    }

    public void setStopReson(String stopReson) {
        this.stopReson = stopReson;
    }

    public String getBlong() {
        return this.blong;
    }

    public BidPrjRescis blong(String blong) {
        this.setBlong(blong);
        return this;
    }

    public void setBlong(String blong) {
        this.blong = blong;
    }

    public String getStopRemark() {
        return this.stopRemark;
    }

    public BidPrjRescis stopRemark(String stopRemark) {
        this.setStopRemark(stopRemark);
        return this;
    }

    public void setStopRemark(String stopRemark) {
        this.stopRemark = stopRemark;
    }

    public String getContractItem() {
        return this.contractItem;
    }

    public BidPrjRescis contractItem(String contractItem) {
        this.setContractItem(contractItem);
        return this;
    }

    public void setContractItem(String contractItem) {
        this.contractItem = contractItem;
    }

    public String getIsRedo() {
        return this.isRedo;
    }

    public BidPrjRescis isRedo(String isRedo) {
        this.setIsRedo(isRedo);
        return this;
    }

    public void setIsRedo(String isRedo) {
        this.isRedo = isRedo;
    }

    public LocalDate getRedoDate() {
        return this.redoDate;
    }

    public BidPrjRescis redoDate(LocalDate redoDate) {
        this.setRedoDate(redoDate);
        return this;
    }

    public void setRedoDate(LocalDate redoDate) {
        this.redoDate = redoDate;
    }

    public String getRedoPrjno() {
        return this.redoPrjno;
    }

    public BidPrjRescis redoPrjno(String redoPrjno) {
        this.setRedoPrjno(redoPrjno);
        return this;
    }

    public void setRedoPrjno(String redoPrjno) {
        this.redoPrjno = redoPrjno;
    }

    public String getNoRedoReson() {
        return this.noRedoReson;
    }

    public BidPrjRescis noRedoReson(String noRedoReson) {
        this.setNoRedoReson(noRedoReson);
        return this;
    }

    public void setNoRedoReson(String noRedoReson) {
        this.noRedoReson = noRedoReson;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjRescis createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjRescis createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjRescis updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjRescis updateUser(String updateUser) {
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
        if (!(o instanceof BidPrjRescis)) return false;
        BidPrjRescis that = (BidPrjRescis) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(stopDate, that.stopDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, stopDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjRescis{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", stopDate='" + getStopDate() + "'" +
            ", contractType='" + getContractType() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", stopReson='" + getStopReson() + "'" +
            ", blong='" + getBlong() + "'" +
            ", stopRemark='" + getStopRemark() + "'" +
            ", contractItem='" + getContractItem() + "'" +
            ", isRedo='" + getIsRedo() + "'" +
            ", redoDate='" + getRedoDate() + "'" +
            ", redoPrjno='" + getRedoPrjno() + "'" +
            ", noRedoReson='" + getNoRedoReson() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
