package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjSubco} entity.
 */
public class BidPrjSubcoDTO implements Serializable {

  @Size(max = 21)
  private String wkut;

  @Size(max = 40)
  private String prjno;

  @Size(max = 4)
  private String subcoNo;

  @Size(max = 20)
  private String subCcut;

  private String subCcutName;

  @Size(max = 200)
  private String rmtfil;


  private String subcoMemo;

  @Size(max = 2)
  private String sType;

  @Size(max = 1)
  private String met01;

  @Size(max = 1)
  private String met02;

  @Size(max = 1)
  private String met03;

  @Size(max = 1)
  private String met04;

  @Size(max = 1)
  private String met05;

  @Size(max = 1)
  private String met06;

  @Size(max = 1)
  private String met07;

  @Size(max = 1)
  private String met08;

  @Size(max = 1)
  private String met99;

  @Size(max = 1)
  private String qchk;

  @Size(max = 1)
  private String met09;

  @Size(max = 1)
  private String met10;

  @Size(max = 1)
  private String met11;

  @Size(max = 1)
  private String met12;

  @Size(max = 1)
  private String met13;

  @Size(max = 1)
  private String met14;

  @Size(max = 1)
  private String met15;

  @Size(max = 1)
  private String met16;

  @Size(max = 20)
  private String upCcut;

  private BigDecimal subBdgt1;

  @Size(max = 1)
  private String met01b;

  @Size(max = 1)
  private String met01c;

  @Size(max = 1)
  private String met02b;

  @Size(max = 1)
  private String met03b;

  private BigDecimal met01v;

  private BigDecimal met02v;

  private BigDecimal met03v;

  private BigDecimal met04v;

  private BigDecimal met05v;

  private BigDecimal met06v;

  private BigDecimal met07v;

  private BigDecimal met08v;

  private BigDecimal met09v;

  private BigDecimal met10v;

  private BigDecimal met11v;

  private BigDecimal met12v;

  private BigDecimal met13v;

  private BigDecimal met14v;

  private BigDecimal met15v;

  private BigDecimal met16v;

  private BigDecimal met99v;

  @Size(max = 1)
  private String met01d;

  private BigDecimal met01bv;

  private BigDecimal met01cv;

  private BigDecimal met01dv;

  private BigDecimal met02bv;

  private BigDecimal met03bv;

  @Size(max = 1)
  private String spcw01;

  @Size(max = 1)
  private String spcw02;

  @Size(max = 1)
  private String spcw03;

  @Size(max = 1)
  private String spcw04;

  @Size(max = 1)
  private String spcw05;

  @Size(max = 1)
  private String spcw06;

  @Size(max = 1)
  private String spcw07;

  @Size(max = 1)
  private String spcw08;

  @Size(max = 1)
  private String spcw09;

  @Size(max = 1)
  private String spcw10;

  @Size(max = 1)
  private String spcw11;

  @Size(max = 1)
  private String spcw12;

  @Size(max = 50)
  private String coRegNo;

  @Size(max = 100)
  private String met99str;

  @Size(max = 1)
  private String met02c;

  @Size(max = 1)
  private String met02d;

  @Size(max = 1)
  private String met02e;

  private BigDecimal met02cv;

  private BigDecimal met02dv;

  private BigDecimal met02ev;

  @Size(max = 1)
  private String met02f;

  private BigDecimal met02fv;

  @Size(max = 1)
  private String confA;

  @Size(max = 1)
  private String confB;

  private Instant createDate;

  @Size(max = 20)
  private String createUser;

  private Instant updateDate;

  @Size(max = 20)
  private String updateUser;

  @Size(max = 60)
  private String uIp;

  private List<BidPrjScequipDTO> scequips ;

  private String eCode;

  private String spcw;


  public BidPrjSubcoPrimaryKey getId() {
    return new BidPrjSubcoPrimaryKey(this.wkut, this.prjno, this.subcoNo);
  }

  public void setId(BidPrjSubcoPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.subcoNo = id.getSubcoNo();
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

  public String getSubcoNo() {
    return subcoNo;
  }

  public void setSubcoNo(String subcoNo) {
    this.subcoNo = subcoNo;
  }

  public String getSubCcut() {
    return subCcut;
  }

  public void setSubCcut(String subCcut) {
    this.subCcut = subCcut;
  }

  public String getSubCcutName() {
    return subCcutName;
  }

  public void setSubCcutName(String subCcutName) {
    this.subCcutName = subCcutName;
  }

  public String getRmtfil() {
    return rmtfil;
  }

  public void setRmtfil(String rmtfil) {
    this.rmtfil = rmtfil;
  }

  public String getSubcoMemo() {
    return subcoMemo;
  }

  public void setSubcoMemo(String subcoMemo) {
    this.subcoMemo = subcoMemo;
  }

  public String getsType() {
    return sType;
  }

  public void setsType(String sType) {
    this.sType = sType;
  }

  public String getMet01() {
    return met01;
  }

  public void setMet01(String met01) {
    this.met01 = met01;
  }

  public String getMet02() {
    return met02;
  }

  public void setMet02(String met02) {
    this.met02 = met02;
  }

  public String getMet03() {
    return met03;
  }

  public void setMet03(String met03) {
    this.met03 = met03;
  }

  public String getMet04() {
    return met04;
  }

  public void setMet04(String met04) {
    this.met04 = met04;
  }

  public String getMet05() {
    return met05;
  }

  public void setMet05(String met05) {
    this.met05 = met05;
  }

  public String getMet06() {
    return met06;
  }

  public void setMet06(String met06) {
    this.met06 = met06;
  }

  public String getMet07() {
    return met07;
  }

  public void setMet07(String met07) {
    this.met07 = met07;
  }

  public String getMet08() {
    return met08;
  }

  public void setMet08(String met08) {
    this.met08 = met08;
  }

  public String getMet99() {
    return met99;
  }

  public void setMet99(String met99) {
    this.met99 = met99;
  }

  public String getQchk() {
    return qchk;
  }

  public void setQchk(String qchk) {
    this.qchk = qchk;
  }

  public String getMet09() {
    return met09;
  }

  public void setMet09(String met09) {
    this.met09 = met09;
  }

  public String getMet10() {
    return met10;
  }

  public void setMet10(String met10) {
    this.met10 = met10;
  }

  public String getMet11() {
    return met11;
  }

  public void setMet11(String met11) {
    this.met11 = met11;
  }

  public String getMet12() {
    return met12;
  }

  public void setMet12(String met12) {
    this.met12 = met12;
  }

  public String getMet13() {
    return met13;
  }

  public void setMet13(String met13) {
    this.met13 = met13;
  }

  public String getMet14() {
    return met14;
  }

  public void setMet14(String met14) {
    this.met14 = met14;
  }

  public String getMet15() {
    return met15;
  }

  public void setMet15(String met15) {
    this.met15 = met15;
  }

  public String getMet16() {
    return met16;
  }

  public void setMet16(String met16) {
    this.met16 = met16;
  }

  public String getUpCcut() {
    return upCcut;
  }

  public void setUpCcut(String upCcut) {
    this.upCcut = upCcut;
  }

  public BigDecimal getSubBdgt1() {
    return subBdgt1;
  }

  public void setSubBdgt1(BigDecimal subBdgt1) {
    this.subBdgt1 = subBdgt1;
  }

  public String getMet01b() {
    return met01b;
  }

  public void setMet01b(String met01b) {
    this.met01b = met01b;
  }

  public String getMet01c() {
    return met01c;
  }

  public void setMet01c(String met01c) {
    this.met01c = met01c;
  }

  public String getMet02b() {
    return met02b;
  }

  public void setMet02b(String met02b) {
    this.met02b = met02b;
  }

  public String getMet03b() {
    return met03b;
  }

  public void setMet03b(String met03b) {
    this.met03b = met03b;
  }

  public BigDecimal getMet01v() {
    return met01v;
  }

  public void setMet01v(BigDecimal met01v) {
    this.met01v = met01v;
  }

  public BigDecimal getMet02v() {
    return met02v;
  }

  public void setMet02v(BigDecimal met02v) {
    this.met02v = met02v;
  }

  public BigDecimal getMet03v() {
    return met03v;
  }

  public void setMet03v(BigDecimal met03v) {
    this.met03v = met03v;
  }

  public BigDecimal getMet04v() {
    return met04v;
  }

  public void setMet04v(BigDecimal met04v) {
    this.met04v = met04v;
  }

  public BigDecimal getMet05v() {
    return met05v;
  }

  public void setMet05v(BigDecimal met05v) {
    this.met05v = met05v;
  }

  public BigDecimal getMet06v() {
    return met06v;
  }

  public void setMet06v(BigDecimal met06v) {
    this.met06v = met06v;
  }

  public BigDecimal getMet07v() {
    return met07v;
  }

  public void setMet07v(BigDecimal met07v) {
    this.met07v = met07v;
  }

  public BigDecimal getMet08v() {
    return met08v;
  }

  public void setMet08v(BigDecimal met08v) {
    this.met08v = met08v;
  }

  public BigDecimal getMet09v() {
    return met09v;
  }

  public void setMet09v(BigDecimal met09v) {
    this.met09v = met09v;
  }

  public BigDecimal getMet10v() {
    return met10v;
  }

  public void setMet10v(BigDecimal met10v) {
    this.met10v = met10v;
  }

  public BigDecimal getMet11v() {
    return met11v;
  }

  public void setMet11v(BigDecimal met11v) {
    this.met11v = met11v;
  }

  public BigDecimal getMet12v() {
    return met12v;
  }

  public void setMet12v(BigDecimal met12v) {
    this.met12v = met12v;
  }

  public BigDecimal getMet13v() {
    return met13v;
  }

  public void setMet13v(BigDecimal met13v) {
    this.met13v = met13v;
  }

  public BigDecimal getMet14v() {
    return met14v;
  }

  public void setMet14v(BigDecimal met14v) {
    this.met14v = met14v;
  }

  public BigDecimal getMet15v() {
    return met15v;
  }

  public void setMet15v(BigDecimal met15v) {
    this.met15v = met15v;
  }

  public BigDecimal getMet16v() {
    return met16v;
  }

  public void setMet16v(BigDecimal met16v) {
    this.met16v = met16v;
  }

  public BigDecimal getMet99v() {
    return met99v;
  }

  public void setMet99v(BigDecimal met99v) {
    this.met99v = met99v;
  }

  public String getMet01d() {
    return met01d;
  }

  public void setMet01d(String met01d) {
    this.met01d = met01d;
  }

  public BigDecimal getMet01bv() {
    return met01bv;
  }

  public void setMet01bv(BigDecimal met01bv) {
    this.met01bv = met01bv;
  }

  public BigDecimal getMet01cv() {
    return met01cv;
  }

  public void setMet01cv(BigDecimal met01cv) {
    this.met01cv = met01cv;
  }

  public BigDecimal getMet01dv() {
    return met01dv;
  }

  public void setMet01dv(BigDecimal met01dv) {
    this.met01dv = met01dv;
  }

  public BigDecimal getMet02bv() {
    return met02bv;
  }

  public void setMet02bv(BigDecimal met02bv) {
    this.met02bv = met02bv;
  }

  public BigDecimal getMet03bv() {
    return met03bv;
  }

  public void setMet03bv(BigDecimal met03bv) {
    this.met03bv = met03bv;
  }

  public String getSpcw01() {
    return spcw01;
  }

  public void setSpcw01(String spcw01) {
    this.spcw01 = spcw01;
  }

  public String getSpcw02() {
    return spcw02;
  }

  public void setSpcw02(String spcw02) {
    this.spcw02 = spcw02;
  }

  public String getSpcw03() {
    return spcw03;
  }

  public void setSpcw03(String spcw03) {
    this.spcw03 = spcw03;
  }

  public String getSpcw04() {
    return spcw04;
  }

  public void setSpcw04(String spcw04) {
    this.spcw04 = spcw04;
  }

  public String getSpcw05() {
    return spcw05;
  }

  public void setSpcw05(String spcw05) {
    this.spcw05 = spcw05;
  }

  public String getSpcw06() {
    return spcw06;
  }

  public void setSpcw06(String spcw06) {
    this.spcw06 = spcw06;
  }

  public String getSpcw07() {
    return spcw07;
  }

  public void setSpcw07(String spcw07) {
    this.spcw07 = spcw07;
  }

  public String getSpcw08() {
    return spcw08;
  }

  public void setSpcw08(String spcw08) {
    this.spcw08 = spcw08;
  }

  public String getSpcw09() {
    return spcw09;
  }

  public void setSpcw09(String spcw09) {
    this.spcw09 = spcw09;
  }

  public String getSpcw10() {
    return spcw10;
  }

  public void setSpcw10(String spcw10) {
    this.spcw10 = spcw10;
  }

  public String getSpcw11() {
    return spcw11;
  }

  public void setSpcw11(String spcw11) {
    this.spcw11 = spcw11;
  }

  public String getSpcw12() {
    return spcw12;
  }

  public void setSpcw12(String spcw12) {
    this.spcw12 = spcw12;
  }

  public String getCoRegNo() {
    return coRegNo;
  }

  public void setCoRegNo(String coRegNo) {
    this.coRegNo = coRegNo;
  }

  public String getMet99str() {
    return met99str;
  }

  public void setMet99str(String met99str) {
    this.met99str = met99str;
  }

  public String getMet02c() {
    return met02c;
  }

  public void setMet02c(String met02c) {
    this.met02c = met02c;
  }

  public String getMet02d() {
    return met02d;
  }

  public void setMet02d(String met02d) {
    this.met02d = met02d;
  }

  public String getMet02e() {
    return met02e;
  }

  public void setMet02e(String met02e) {
    this.met02e = met02e;
  }

  public BigDecimal getMet02cv() {
    return met02cv;
  }

  public void setMet02cv(BigDecimal met02cv) {
    this.met02cv = met02cv;
  }

  public BigDecimal getMet02dv() {
    return met02dv;
  }

  public void setMet02dv(BigDecimal met02dv) {
    this.met02dv = met02dv;
  }

  public BigDecimal getMet02ev() {
    return met02ev;
  }

  public void setMet02ev(BigDecimal met02ev) {
    this.met02ev = met02ev;
  }

  public String getMet02f() {
    return met02f;
  }

  public void setMet02f(String met02f) {
    this.met02f = met02f;
  }

  public BigDecimal getMet02fv() {
    return met02fv;
  }

  public void setMet02fv(BigDecimal met02fv) {
    this.met02fv = met02fv;
  }

  public String getConfA() {
    return confA;
  }

  public void setConfA(String confA) {
    this.confA = confA;
  }

  public String getConfB() {
    return confB;
  }

  public void setConfB(String confB) {
    this.confB = confB;
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

  public List<BidPrjScequipDTO> getScequips() {
    return scequips;
  }

  public void setScequips(List<BidPrjScequipDTO> scequips) {
    this.scequips = scequips;
  }

  public String geteCode() {
    return eCode;
  }

  public void seteCode(String eCode) {
    this.eCode = eCode;
  }

  public String getSpcw() {
    return spcw;
  }

  public void setSpcw(String spcw) {
    this.spcw = spcw;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BidPrjSubcoDTO)) {
      return false;
    }

    BidPrjSubcoDTO that = (BidPrjSubcoDTO) o;
    return (
      wkut.equals(that.wkut) &&
      prjno.equals(that.prjno) &&
      subcoNo.equals(that.subcoNo)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(wkut, prjno, subcoNo);
  }

  // prettier-ignore


  @Override
  public String toString() {
    return "BidPrjSubcoDTO{" +
            "wkut='" + wkut + '\'' +
            ", prjno='" + prjno + '\'' +
            ", subcoNo='" + subcoNo + '\'' +
            ", subCcut='" + subCcut + '\'' +
            ", subCcutName='" + subCcutName + '\'' +
            ", rmtfil='" + rmtfil + '\'' +
            ", subcoMemo='" + subcoMemo + '\'' +
            ", sType='" + sType + '\'' +
            ", met01='" + met01 + '\'' +
            ", met02='" + met02 + '\'' +
            ", met03='" + met03 + '\'' +
            ", met04='" + met04 + '\'' +
            ", met05='" + met05 + '\'' +
            ", met06='" + met06 + '\'' +
            ", met07='" + met07 + '\'' +
            ", met08='" + met08 + '\'' +
            ", met99='" + met99 + '\'' +
            ", qchk='" + qchk + '\'' +
            ", met09='" + met09 + '\'' +
            ", met10='" + met10 + '\'' +
            ", met11='" + met11 + '\'' +
            ", met12='" + met12 + '\'' +
            ", met13='" + met13 + '\'' +
            ", met14='" + met14 + '\'' +
            ", met15='" + met15 + '\'' +
            ", met16='" + met16 + '\'' +
            ", upCcut='" + upCcut + '\'' +
            ", subBdgt1=" + subBdgt1 +
            ", met01b='" + met01b + '\'' +
            ", met01c='" + met01c + '\'' +
            ", met02b='" + met02b + '\'' +
            ", met03b='" + met03b + '\'' +
            ", met01v=" + met01v +
            ", met02v=" + met02v +
            ", met03v=" + met03v +
            ", met04v=" + met04v +
            ", met05v=" + met05v +
            ", met06v=" + met06v +
            ", met07v=" + met07v +
            ", met08v=" + met08v +
            ", met09v=" + met09v +
            ", met10v=" + met10v +
            ", met11v=" + met11v +
            ", met12v=" + met12v +
            ", met13v=" + met13v +
            ", met14v=" + met14v +
            ", met15v=" + met15v +
            ", met16v=" + met16v +
            ", met99v=" + met99v +
            ", met01d='" + met01d + '\'' +
            ", met01bv=" + met01bv +
            ", met01cv=" + met01cv +
            ", met01dv=" + met01dv +
            ", met02bv=" + met02bv +
            ", met03bv=" + met03bv +
            ", spcw01='" + spcw01 + '\'' +
            ", spcw02='" + spcw02 + '\'' +
            ", spcw03='" + spcw03 + '\'' +
            ", spcw04='" + spcw04 + '\'' +
            ", spcw05='" + spcw05 + '\'' +
            ", spcw06='" + spcw06 + '\'' +
            ", spcw07='" + spcw07 + '\'' +
            ", spcw08='" + spcw08 + '\'' +
            ", spcw09='" + spcw09 + '\'' +
            ", spcw10='" + spcw10 + '\'' +
            ", spcw11='" + spcw11 + '\'' +
            ", spcw12='" + spcw12 + '\'' +
            ", coRegNo='" + coRegNo + '\'' +
            ", met99str='" + met99str + '\'' +
            ", met02c='" + met02c + '\'' +
            ", met02d='" + met02d + '\'' +
            ", met02e='" + met02e + '\'' +
            ", met02cv=" + met02cv +
            ", met02dv=" + met02dv +
            ", met02ev=" + met02ev +
            ", met02f='" + met02f + '\'' +
            ", met02fv=" + met02fv +
            ", confA='" + confA + '\'' +
            ", confB='" + confB + '\'' +
            ", createDate=" + createDate +
            ", createUser='" + createUser + '\'' +
            ", updateDate=" + updateDate +
            ", updateUser='" + updateUser + '\'' +
            ", uIp='" + uIp + '\'' +
            ", scequips=" + scequips +
            ", eCode='" + eCode + '\'' +
            ", spcw='" + spcw + '\'' +
            '}';
  }
}
