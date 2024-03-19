package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectUnitPrimaryKey;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProjectUnit} entity.
 */
public class BidProjectUnitDTO implements Serializable {

  @NotNull
  @Size(max = 21)
  private String wkut;

  @NotNull
  @Size(max = 40)
  private String prjno;

  @NotNull
  @Size(max = 2)
  private String type;

  @NotNull
  @Size(max = 2)
  private String seq;

  @Size(max = 21)
  private String dsut;

  private BigDecimal dsct;

  @Size(max = 21)
  private String scut;

  private BigDecimal scct;

  @Size(max = 21)
  private String ccut;

  private BigDecimal crat;

  @Size(max = 1)
  private String ccwkA;

  @Size(max = 1)
  private String ccwkB;

  @Size(max = 1)
  private String ccwkC;

  @NotNull
  private Instant createDate;

  @NotNull
  @Size(max = 20)
  private String createUser;

  private Instant updateDate;

  @Size(max = 20)
  private String updateUser;

  private String dsutName;

  private String scutName;

  private String ccutName;

  private String name;

  private String subCcut;

  private String pcmut;

  public BidProjectUnitPrimaryKey getId() {
    return new BidProjectUnitPrimaryKey(wkut, prjno, type, seq);
  }

  public void setId(BidProjectUnitPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.type = id.getType();
    this.seq = id.getSeq();
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSeq() {
    return seq;
  }

  public void setSeq(String seq) {
    this.seq = seq;
  }

  public String getDsut() {
    return dsut;
  }

  public void setDsut(String dsut) {
    this.dsut = dsut;
  }

  public BigDecimal getDsct() {
    return dsct;
  }

  public void setDsct(BigDecimal dsct) {
    this.dsct = dsct;
  }

  public String getScut() {
    return scut;
  }

  public void setScut(String scut) {
    this.scut = scut;
  }

  public BigDecimal getScct() {
    return scct;
  }

  public void setScct(BigDecimal scct) {
    this.scct = scct;
  }

  public String getCcut() {
    return ccut;
  }

  public void setCcut(String ccut) {
    this.ccut = ccut;
  }

  public BigDecimal getCrat() {
    return crat;
  }

  public void setCrat(BigDecimal crat) {
    this.crat = crat;
  }

  public String getCcwkA() {
    return ccwkA;
  }

  public void setCcwkA(String ccwkA) {
    this.ccwkA = ccwkA;
  }

  public String getCcwkB() {
    return ccwkB;
  }

  public void setCcwkB(String ccwkB) {
    this.ccwkB = ccwkB;
  }

  public String getCcwkC() {
    return ccwkC;
  }

  public void setCcwkC(String ccwkC) {
    this.ccwkC = ccwkC;
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

  public String getDsutName() {
    return dsutName;
  }

  public void setDsutName(String dsutName) {
    this.dsutName = dsutName;
  }

  public String getScutName() {
    return scutName;
  }

  public void setScutName(String scutName) {
    this.scutName = scutName;
  }

  public String getCcutName() {
    return ccutName;
  }

  public void setCcutName(String ccutName) {
    this.ccutName = ccutName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSubCcut() {
    return subCcut;
  }

  public void setSubCcut(String subCcut) {
    this.subCcut = subCcut;
  }

  public String getPcmut() {
    return pcmut;
  }

  public void setPcmut(String pcmut) {
    this.pcmut = pcmut;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidProjectUnitDTO)) return false;
    BidProjectUnitDTO that = (BidProjectUnitDTO) o;
    return (
      wkut.equals(that.wkut) &&
      prjno.equals(that.prjno) &&
      type.equals(that.type) &&
      seq.equals(that.seq)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, type, seq);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectUnitDTO{" +
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

  public String getIdString() {
    return getPrjno() + "-" + getPrjno() + "-" + getType() + "-" + getSeq();
  }
}
