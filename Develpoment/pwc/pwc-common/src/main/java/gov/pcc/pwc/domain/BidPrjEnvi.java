package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjEnvi.
 */
@Entity
@IdClass(BidPrjEnviPrimaryKey.class)
@Table(name = "bid_prj_envi")
public class BidPrjEnvi implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @NotNull
  @Size(max = 255)
  @Column(name = "wkut", length = 21, nullable = false)
  private String wkut;

  @Id
  @NotNull
  @Size(max = 40)
  @Column(name = "prjno", length = 40, nullable = false)
  private String prjno;

  @Id
  @NotNull
  @Column(name = "sdate", nullable = false)
  private LocalDate sdate;

  @NotNull
  @Size(max = 100)
  @Column(name = "prm_no", length = 100, nullable = false)
  private String prmNo;

  @NotNull
  @Size(max = 2)
  @Column(name = "ct_unit", length = 2, nullable = false)
  private String ctUnit;

  @Size(max = 10)
  @Column(name = "ccut", length = 10)
  private String ccut;

  @Size(max = 2)
  @Column(name = "stype", length = 2)
  private String stype;

  @NotNull
  @Column(name = "violate_date", nullable = false)
  private LocalDate violateDate;

  @NotNull
  @Size(max = 2000)
  @Column(name = "prm_title", length = 2000, nullable = false)
  private String prmTitle;

  @NotNull
  @Size(max = 2000)
  @Column(name = "violate_fact", length = 2000, nullable = false)
  private String violateFact;

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

  @Size(max = 60)
  @Column(name = "u_ip", length = 60)
  private String uIp;

  @NotNull
  @Column(name = "wdayed", nullable = false)
  private Float wdayed;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public BidPrjEnviPrimaryKey getId() {
    return new BidPrjEnviPrimaryKey(wkut, prjno, sdate);
  }

  public BidPrjEnvi id(BidPrjEnviPrimaryKey id) {
    this.setId(id);
    return this;
  }

  public void setId(BidPrjEnviPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.sdate = id.getSdate();
  }

  public String getWkut() {
    return this.wkut;
  }

  public BidPrjEnvi wkut(String wkut) {
    this.setWkut(wkut);
    return this;
  }

  public void setWkut(String wkut) {
    this.wkut = wkut;
  }

  public String getPrjno() {
    return this.prjno;
  }

  public BidPrjEnvi prjno(String prjno) {
    this.setPrjno(prjno);
    return this;
  }

  public void setPrjno(String prjno) {
    this.prjno = prjno;
  }

  public LocalDate getSdate() {
    return this.sdate;
  }

  public BidPrjEnvi sdate(LocalDate sdate) {
    this.setSdate(sdate);
    return this;
  }

  public void setSdate(LocalDate sdate) {
    this.sdate = sdate;
  }

  public String getPrmNo() {
    return this.prmNo;
  }

  public BidPrjEnvi prmNo(String prmNo) {
    this.setPrmNo(prmNo);
    return this;
  }

  public void setPrmNo(String prmNo) {
    this.prmNo = prmNo;
  }

  public String getCtUnit() {
    return this.ctUnit;
  }

  public BidPrjEnvi ctUnit(String ctUnit) {
    this.setCtUnit(ctUnit);
    return this;
  }

  public void setCtUnit(String ctUnit) {
    this.ctUnit = ctUnit;
  }

  public String getCcut() {
    return this.ccut;
  }

  public BidPrjEnvi ccut(String ccut) {
    this.setCcut(ccut);
    return this;
  }

  public void setCcut(String ccut) {
    this.ccut = ccut;
  }

  public String getStype() {
    return this.stype;
  }

  public BidPrjEnvi stype(String stype) {
    this.setStype(stype);
    return this;
  }

  public void setStype(String stype) {
    this.stype = stype;
  }

  public LocalDate getViolateDate() {
    return this.violateDate;
  }

  public BidPrjEnvi violateDate(LocalDate violateDate) {
    this.setViolateDate(violateDate);
    return this;
  }

  public void setViolateDate(LocalDate violateDate) {
    this.violateDate = violateDate;
  }

  public String getPrmTitle() {
    return this.prmTitle;
  }

  public BidPrjEnvi prmTitle(String prmTitle) {
    this.setPrmTitle(prmTitle);
    return this;
  }

  public void setPrmTitle(String prmTitle) {
    this.prmTitle = prmTitle;
  }

  public String getViolateFact() {
    return this.violateFact;
  }

  public BidPrjEnvi violateFact(String violateFact) {
    this.setViolateFact(violateFact);
    return this;
  }

  public void setViolateFact(String violateFact) {
    this.violateFact = violateFact;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public BidPrjEnvi createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public String getCreateUser() {
    return this.createUser;
  }

  public BidPrjEnvi createUser(String createUser) {
    this.setCreateUser(createUser);
    return this;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public Instant getUpdateDate() {
    return this.updateDate;
  }

  public BidPrjEnvi updateDate(Instant updateDate) {
    this.setUpdateDate(updateDate);
    return this;
  }

  public void setUpdateDate(Instant updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdateUser() {
    return this.updateUser;
  }

  public BidPrjEnvi updateUser(String updateUser) {
    this.setUpdateUser(updateUser);
    return this;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public String getuIp() {
    return this.uIp;
  }

  public BidPrjEnvi uIp(String uIp) {
    this.setuIp(uIp);
    return this;
  }

  public void setuIp(String uIp) {
    this.uIp = uIp;
  }

  public Float getWdayed() {
    return this.wdayed;
  }

  public BidPrjEnvi wdayed(Float wdayed) {
    this.setWdayed(wdayed);
    return this;
  }

  public void setWdayed(Float wdayed) {
    this.wdayed = wdayed;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjEnvi)) return false;
    BidPrjEnvi that = (BidPrjEnvi) o;
    return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(sdate, that.sdate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, sdate);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjEnvi{" +
            "id=" + getId() +
            "wkut=" + getWkut() +
            ", prjno='" + getPrjno() + "'" +
            ", sdate='" + getSdate() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", ctUnit='" + getCtUnit() + "'" +
            ", ccut='" + getCcut() + "'" +
            ", stype='" + getStype() + "'" +
            ", violateDate='" + getViolateDate() + "'" +
            ", prmTitle='" + getPrmTitle() + "'" +
            ", violateFact='" + getViolateFact() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", wdayed=" + getWdayed() +
            "}";
    }
}
