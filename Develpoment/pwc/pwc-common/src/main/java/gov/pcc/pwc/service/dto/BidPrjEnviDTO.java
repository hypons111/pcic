package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjEnviPrimaryKey;
import gov.pcc.pwc.domain.BidPrjEquipPrimaryKey;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjEnvi} entity.
 */
public class BidPrjEnviDTO implements Serializable {

  @NotNull
  @Size(max = 21)
  private String wkut;

  @NotNull
  @Size(max = 40)
  private String prjno;

  @NotNull
  private LocalDate sdate;

  @NotNull
  @Size(max = 100)
  private String prmNo;

  @NotNull
  @Size(max = 2)
  private String ctUnit;

  @Size(max = 10)
  private String ccut;

  @Size(max = 2)
  private String stype;

  @NotNull
  private LocalDate violateDate;

  @NotNull
  @Size(max = 2000)
  private String prmTitle;

  @NotNull
  @Size(max = 2000)
  private String violateFact;

  @NotNull
  private Instant createDate;

  @NotNull
  @Size(max = 20)
  private String createUser;

  private Instant updateDate;

  @Size(max = 20)
  private String updateUser;

  @Size(max = 60)
  private String uIp;

  @NotNull
  private Float wdayed;

  public BidPrjEnviPrimaryKey getId() {
    return new BidPrjEnviPrimaryKey(
      this.wkut,
      this.prjno,
      this.sdate
    );
  }

  public void setId(BidPrjEnviPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.sdate = id.getSdate();
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

  public LocalDate getSdate() {
    return sdate;
  }

  public void setSdate(LocalDate sdate) {
    this.sdate = sdate;
  }

  public String getPrmNo() {
    return prmNo;
  }

  public void setPrmNo(String prmNo) {
    this.prmNo = prmNo;
  }

  public String getCtUnit() {
    return ctUnit;
  }

  public void setCtUnit(String ctUnit) {
    this.ctUnit = ctUnit;
  }

  public String getCcut() {
    return ccut;
  }

  public void setCcut(String ccut) {
    this.ccut = ccut;
  }

  public String getStype() {
    return stype;
  }

  public void setStype(String stype) {
    this.stype = stype;
  }

  public LocalDate getViolateDate() {
    return violateDate;
  }

  public void setViolateDate(LocalDate violateDate) {
    this.violateDate = violateDate;
  }

  public String getPrmTitle() {
    return prmTitle;
  }

  public void setPrmTitle(String prmTitle) {
    this.prmTitle = prmTitle;
  }

  public String getViolateFact() {
    return violateFact;
  }

  public void setViolateFact(String violateFact) {
    this.violateFact = violateFact;
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

  public String getuIp() {
    return uIp;
  }

  public void setuIp(String uIp) {
    this.uIp = uIp;
  }

  public Float getWdayed() {
    return wdayed;
  }

  public void setWdayed(Float wdayed) {
    this.wdayed = wdayed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BidPrjEnviDTO)) return false;
    BidPrjEnviDTO that = (BidPrjEnviDTO) o;
    return (
      Objects.equals(wkut, that.wkut) &&
      Objects.equals(prjno, that.prjno) &&
      Objects.equals(sdate, that.sdate)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, sdate);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjEnviDTO{" +
            "id='" + getId() +
            "wkut='" + getWkut() + "'" +
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
