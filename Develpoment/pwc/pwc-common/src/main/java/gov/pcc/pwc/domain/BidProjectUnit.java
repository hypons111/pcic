package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidProjectUnit.
 */
@Entity
@IdClass(BidProjectUnitPrimaryKey.class)
@Table(name = "bid_project_unit")
public class BidProjectUnit implements Serializable {

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
  @Size(max = 2)
  @Column(name = "type", length = 2, nullable = false)
  private String type;

  @Id
  @NotNull
  @Size(max = 2)
  @Column(name = "seq", length = 2, nullable = false)
  private String seq;

  @Size(max = 21)
  @Column(name = "dsut", length = 21)
  private String dsut;

  @Column(name = "dsct", precision = 21, scale = 2)
  private BigDecimal dsct;

  @Size(max = 21)
  @Column(name = "scut", length = 21)
  private String scut;

  @Column(name = "scct", precision = 21, scale = 2)
  private BigDecimal scct;

  @Size(max = 21)
  @Column(name = "ccut", length = 21)
  private String ccut;

  @Column(name = "crat", precision = 21, scale = 2)
  private BigDecimal crat;

  @Size(max = 1)
  @Column(name = "ccwk_a", length = 1)
  private String ccwkA;

  @Size(max = 1)
  @Column(name = "ccwk_b", length = 1)
  private String ccwkB;

  @Size(max = 1)
  @Column(name = "ccwk_c", length = 1)
  private String ccwkC;

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

  public BidProjectUnitPrimaryKey getId() {
    return new BidProjectUnitPrimaryKey(wkut, prjno, type, seq);
  }

  public BidProjectUnit id(BidProjectUnitPrimaryKey id) {
    this.setId(id);
    return this;
  }

  public void setId(BidProjectUnitPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.type = id.getType();
    this.seq = id.getSeq();
  }

  public String getWkut() {
    return this.wkut;
  }

  public BidProjectUnit wkut(String wkut) {
    this.setWkut(wkut);
    return this;
  }

  public void setWkut(String wkut) {
    this.wkut = wkut;
  }

  public String getPrjno() {
    return this.prjno;
  }

  public BidProjectUnit prjno(String prjno) {
    this.setPrjno(prjno);
    return this;
  }

  public void setPrjno(String prjno) {
    this.prjno = prjno;
  }

  public String getType() {
    return this.type;
  }

  public BidProjectUnit type(String type) {
    this.setType(type);
    return this;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSeq() {
    return this.seq;
  }

  public BidProjectUnit seq(String seq) {
    this.setSeq(seq);
    return this;
  }

  public void setSeq(String seq) {
    this.seq = seq;
  }

  public String getDsut() {
    return this.dsut;
  }

  public BidProjectUnit dsut(String dsut) {
    this.setDsut(dsut);
    return this;
  }

  public void setDsut(String dsut) {
    this.dsut = dsut;
  }

  public BigDecimal getDsct() {
    return this.dsct;
  }

  public BidProjectUnit dsct(BigDecimal dsct) {
    this.setDsct(dsct);
    return this;
  }

  public void setDsct(BigDecimal dsct) {
    this.dsct = dsct;
  }

  public String getScut() {
    return this.scut;
  }

  public BidProjectUnit scut(String scut) {
    this.setScut(scut);
    return this;
  }

  public void setScut(String scut) {
    this.scut = scut;
  }

  public BigDecimal getScct() {
    return this.scct;
  }

  public BidProjectUnit scct(BigDecimal scct) {
    this.setScct(scct);
    return this;
  }

  public void setScct(BigDecimal scct) {
    this.scct = scct;
  }

  public String getCcut() {
    return this.ccut;
  }

  public BidProjectUnit ccut(String ccut) {
    this.setCcut(ccut);
    return this;
  }

  public void setCcut(String ccut) {
    this.ccut = ccut;
  }

  public BigDecimal getCrat() {
    return this.crat;
  }

  public BidProjectUnit crat(BigDecimal crat) {
    this.setCrat(crat);
    return this;
  }

  public void setCrat(BigDecimal crat) {
    this.crat = crat;
  }

  public String getCcwkA() {
    return this.ccwkA;
  }

  public BidProjectUnit ccwkA(String ccwkA) {
    this.setCcwkA(ccwkA);
    return this;
  }

  public void setCcwkA(String ccwkA) {
    this.ccwkA = ccwkA;
  }

  public String getCcwkB() {
    return this.ccwkB;
  }

  public BidProjectUnit ccwkB(String ccwkB) {
    this.setCcwkB(ccwkB);
    return this;
  }

  public void setCcwkB(String ccwkB) {
    this.ccwkB = ccwkB;
  }

  public String getCcwkC() {
    return this.ccwkC;
  }

  public BidProjectUnit ccwkC(String ccwkC) {
    this.setCcwkC(ccwkC);
    return this;
  }

  public void setCcwkC(String ccwkC) {
    this.ccwkC = ccwkC;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public BidProjectUnit createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public String getCreateUser() {
    return this.createUser;
  }

  public BidProjectUnit createUser(String createUser) {
    this.setCreateUser(createUser);
    return this;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public Instant getUpdateDate() {
    return this.updateDate;
  }

  public BidProjectUnit updateDate(Instant updateDate) {
    this.setUpdateDate(updateDate);
    return this;
  }

  public void setUpdateDate(Instant updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdateUser() {
    return this.updateUser;
  }

  public BidProjectUnit updateUser(String updateUser) {
    this.setUpdateUser(updateUser);
    return this;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidProjectUnit)) return false;
    BidProjectUnit that = (BidProjectUnit) o;
    return (
      Objects.equals(wkut, that.wkut) &&
      Objects.equals(prjno, that.prjno) &&
      Objects.equals(type, that.type) &&
      Objects.equals(seq, that.seq)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, type, seq);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectUnit{" +
                "id=" + getId() +
                ", wkut='" + getWkut() + "'" +
                ", prjno='" + getPrjno() + "'" +
                ", type='" + getType() + "'" +
                ", seq='" + getSeq() + "'" +
                ", dsut='" + getDsut() + "'" +
                ", dsct=" + getDsct() +
                ", scut='" + getScut() + "'" +
                ", scct=" + getScct() +
                ", ccut='" + getCcut() + "'" +
                ", crat=" + getCrat() +
                ", ccwkA='" + getCcwkA() + "'" +
                ", ccwkB='" + getCcwkB() + "'" +
                ", ccwkC='" + getCcwkC() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                "}";
    }
}
