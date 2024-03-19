package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjSubco.
 */
@Entity
@IdClass(BidPrjSubcoPrimaryKey.class)
@Table(name = "bid_prj_subco")
public class BidPrjSubco implements Serializable {

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
  @Size(max = 40)
  @Column(name = "prjno", length = 40)
  private String prjno;

  @Id
  @Size(max = 4)
  @Column(name = "subco_no", length = 4)
  private String subcoNo;

  @Size(max = 20)
  @Column(name = "sub_ccut", length = 20)
  private String subCcut;

  @Size(max = 50)
  @Column(name = "co_regno", length = 50)
  private String coRegNo;

  @Column(name = "sub_bdgt1")
  private Double subBdgt1;

  @Size(max = 20)
  @Column(name = "up_ccut", length = 20)
  private String upCcut;

  @Size(max = 2)
  @Column(name = "stype", length = 2)
  private String stype;

  @Size(max = 1)
  @Column(name = "spcw01", length = 1)
  private String spcw01;

  @Size(max = 1)
  @Column(name = "spcw02", length = 1)
  private String spcw02;

  @Size(max = 1)
  @Column(name = "spcw03", length = 1)
  private String spcw03;

  @Size(max = 1)
  @Column(name = "spcw04", length = 1)
  private String spcw04;

  @Size(max = 1)
  @Column(name = "spcw05", length = 1)
  private String spcw05;

  @Size(max = 1)
  @Column(name = "spcw06", length = 1)
  private String spcw06;

  @Size(max = 1)
  @Column(name = "spcw07", length = 1)
  private String spcw07;

  @Size(max = 1)
  @Column(name = "spcw08", length = 1)
  private String spcw08;

  @Size(max = 1)
  @Column(name = "spcw09", length = 1)
  private String spcw09;

  @Size(max = 1)
  @Column(name = "spcw10", length = 1)
  private String spcw10;

  @Size(max = 1)
  @Column(name = "spcw11", length = 1)
  private String spcw11;

  @Size(max = 1)
  @Column(name = "spcw12", length = 1)
  private String spcw12;

  @Column(name = "subco_memo")
  private String subcoMemo;

  @Size(max = 1)
  @Column(name = "met01", length = 1)
  private String met01;

  @Size(max = 1)
  @Column(name = "met01b", length = 1)
  private String met01b;

  @Size(max = 1)
  @Column(name = "met01c", length = 1)
  private String met01c;

  @Size(max = 1)
  @Column(name = "met05", length = 1)
  private String met05;

  @Size(max = 1)
  @Column(name = "met02f", length = 1)
  private String met02f;

  @Size(max = 1)
  @Column(name = "met01d", length = 1)
  private String met01d;

  @Size(max = 1)
  @Column(name = "met02", length = 1)
  private String met02;

  @Size(max = 1)
  @Column(name = "met02b", length = 1)
  private String met02b;

  @Size(max = 1)
  @Column(name = "met04", length = 1)
  private String met04;

  @Size(max = 1)
  @Column(name = "met03", length = 1)
  private String met03;

  @Size(max = 1)
  @Column(name = "met03b", length = 1)
  private String met03b;

  @Size(max = 1)
  @Column(name = "met02c", length = 1)
  private String met02c;

  @Size(max = 1)
  @Column(name = "met02d", length = 1)
  private String met02d;

  @Size(max = 1)
  @Column(name = "met02e", length = 1)
  private String met02e;

  @Column(name = "met01v")
  private Double met01v;

  @Column(name = "met01bv")
  private Double met01bv;

  @Column(name = "met01cv")
  private Double met01cv;

  @Column(name = "met05v")
  private Double met05v;

  @Column(name = "met02fv")
  private Double met02fv;

  @Column(name = "met01dv")
  private Double met01dv;

  @Column(name = "met02v")
  private Double met02v;

  @Column(name = "met02bv")
  private Double met02bv;

  @Column(name = "met04v")
  private Double met04v;

  @Column(name = "met03v")
  private Double met03v;

  @Column(name = "met03bv")
  private Double met03bv;

  @Column(name = "met02cv")
  private Double met02cv;

  @Column(name = "met02dv")
  private Double met02dv;

  @Column(name = "met02ev")
  private Double met02ev;

  @Size(max = 1)
  @Column(name = "qchk", length = 1)
  private String qchk;

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

  @Size(max = 200)
  @Column(name = "rmtfil", length = 200)
  private String rmtfil;

  @Size(max = 60)
  @Column(name = "u_ip", length = 60)
  private String uIp;

  @Size(max = 1)
  @Column(name = "met06", length = 1)
  private String met06;

  @Size(max = 1)
  @Column(name = "met07", length = 1)
  private String met07;

  @Size(max = 1)
  @Column(name = "met08", length = 1)
  private String met08;

  @Size(max = 1)
  @Column(name = "met09", length = 1)
  private String met09;

  @Size(max = 1)
  @Column(name = "met10", length = 1)
  private String met10;

  @Size(max = 1)
  @Column(name = "met11", length = 1)
  private String met11;

  @Size(max = 1)
  @Column(name = "met12", length = 1)
  private String met12;

  @Size(max = 1)
  @Column(name = "met13", length = 1)
  private String met13;

  @Size(max = 1)
  @Column(name = "met14", length = 1)
  private String met14;

  @Size(max = 1)
  @Column(name = "met15", length = 1)
  private String met15;

  @Size(max = 1)
  @Column(name = "met16", length = 1)
  private String met16;

  @Size(max = 1)
  @Column(name = "met99", length = 1)
  private String met99;

  @Column(name = "met06v")
  private Double met06v;

  @Column(name = "met07v")
  private Double met07v;

  @Column(name = "met08v")
  private Double met08v;

  @Column(name = "met09v")
  private Double met09v;

  @Column(name = "met10v")
  private Double met10v;

  @Column(name = "met11v")
  private Double met11v;

  @Column(name = "met12v")
  private Double met12v;

  @Column(name = "met13v")
  private Double met13v;

  @Column(name = "met14v")
  private Double met14v;

  @Column(name = "met15v")
  private Double met15v;

  @Column(name = "met16v")
  private Double met16v;

  @Column(name = "met99v")
  private Double met99v;

  @Size(max = 100)
  @Column(name = "met99str", length = 100)
  private String met99str;

  @Size(max = 1)
  @Column(name = "confa", length = 1)
  private String confa;

  @Size(max = 1)
  @Column(name = "confb", length = 1)
  private String confb;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public BidPrjSubcoPrimaryKey getId() {
    return new BidPrjSubcoPrimaryKey(wkut, prjno, subcoNo);
  }

  public BidPrjSubco id(BidPrjSubcoPrimaryKey id) {
    this.setId(id);
    return this;
  }

  public void setId(BidPrjSubcoPrimaryKey id) {
    this.wkut = id.getWkut();
    this.prjno = id.getPrjno();
    this.subcoNo = id.getSubcoNo();
  }

  public String getWkut() {
    return this.wkut;
  }

  public BidPrjSubco wkut(String wkut) {
    this.setWkut(wkut);
    return this;
  }

  public void setWkut(String wkut) {
    this.wkut = wkut;
  }

  public String getPrjno() {
    return this.prjno;
  }

  public BidPrjSubco prjno(String prjno) {
    this.setPrjno(prjno);
    return this;
  }

  public void setPrjno(String prjno) {
    this.prjno = prjno;
  }

  public String getSubcoNo() {
    return this.subcoNo;
  }

  public BidPrjSubco subcoNo(String subcoNo) {
    this.setSubcoNo(subcoNo);
    return this;
  }

  public void setSubcoNo(String subcoNo) {
    this.subcoNo = subcoNo;
  }

  public String getSubCcut() {
    return this.subCcut;
  }

  public BidPrjSubco subCcut(String subCcut) {
    this.setSubCcut(subCcut);
    return this;
  }

  public void setSubCcut(String subCcut) {
    this.subCcut = subCcut;
  }

  public String getCoRegNo() {
    return this.coRegNo;
  }

  public BidPrjSubco coRegNo(String coRegNo) {
    this.setCoRegNo(coRegNo);
    return this;
  }

  public void setCoRegNo(String coRegNo) {
    this.coRegNo = coRegNo;
  }

  public Double getSubBdgt1() {
    return this.subBdgt1;
  }

  public BidPrjSubco subBdgt1(Double subBdgt1) {
    this.setSubBdgt1(subBdgt1);
    return this;
  }

  public void setSubBdgt1(Double subBdgt1) {
    this.subBdgt1 = subBdgt1;
  }

  public String getUpCcut() {
    return this.upCcut;
  }

  public BidPrjSubco upCcut(String upCcut) {
    this.setUpCcut(upCcut);
    return this;
  }

  public void setUpCcut(String upCcut) {
    this.upCcut = upCcut;
  }

  public String getStype() {
    return this.stype;
  }

  public BidPrjSubco stype(String stype) {
    this.setStype(stype);
    return this;
  }

  public void setStype(String stype) {
    this.stype = stype;
  }

  public String getSpcw01() {
    return this.spcw01;
  }

  public BidPrjSubco spcw01(String spcw01) {
    this.setSpcw01(spcw01);
    return this;
  }

  public void setSpcw01(String spcw01) {
    this.spcw01 = spcw01;
  }

  public String getSpcw02() {
    return this.spcw02;
  }

  public BidPrjSubco spcw02(String spcw02) {
    this.setSpcw02(spcw02);
    return this;
  }

  public void setSpcw02(String spcw02) {
    this.spcw02 = spcw02;
  }

  public String getSpcw03() {
    return this.spcw03;
  }

  public BidPrjSubco spcw03(String spcw03) {
    this.setSpcw03(spcw03);
    return this;
  }

  public void setSpcw03(String spcw03) {
    this.spcw03 = spcw03;
  }

  public String getSpcw04() {
    return this.spcw04;
  }

  public BidPrjSubco spcw04(String spcw04) {
    this.setSpcw04(spcw04);
    return this;
  }

  public void setSpcw04(String spcw04) {
    this.spcw04 = spcw04;
  }

  public String getSpcw05() {
    return this.spcw05;
  }

  public BidPrjSubco spcw05(String spcw05) {
    this.setSpcw05(spcw05);
    return this;
  }

  public void setSpcw05(String spcw05) {
    this.spcw05 = spcw05;
  }

  public String getSpcw06() {
    return this.spcw06;
  }

  public BidPrjSubco spcw06(String spcw06) {
    this.setSpcw06(spcw06);
    return this;
  }

  public void setSpcw06(String spcw06) {
    this.spcw06 = spcw06;
  }

  public String getSpcw07() {
    return this.spcw07;
  }

  public BidPrjSubco spcw07(String spcw07) {
    this.setSpcw07(spcw07);
    return this;
  }

  public void setSpcw07(String spcw07) {
    this.spcw07 = spcw07;
  }

  public String getSpcw08() {
    return this.spcw08;
  }

  public BidPrjSubco spcw08(String spcw08) {
    this.setSpcw08(spcw08);
    return this;
  }

  public void setSpcw08(String spcw08) {
    this.spcw08 = spcw08;
  }

  public String getSpcw09() {
    return this.spcw09;
  }

  public BidPrjSubco spcw09(String spcw09) {
    this.setSpcw09(spcw09);
    return this;
  }

  public void setSpcw09(String spcw09) {
    this.spcw09 = spcw09;
  }

  public String getSpcw10() {
    return this.spcw10;
  }

  public BidPrjSubco spcw10(String spcw10) {
    this.setSpcw10(spcw10);
    return this;
  }

  public void setSpcw10(String spcw10) {
    this.spcw10 = spcw10;
  }

  public String getSpcw11() {
    return this.spcw11;
  }

  public BidPrjSubco spcw11(String spcw11) {
    this.setSpcw11(spcw11);
    return this;
  }

  public void setSpcw11(String spcw11) {
    this.spcw11 = spcw11;
  }

  public String getSpcw12() {
    return this.spcw12;
  }

  public BidPrjSubco spcw12(String spcw12) {
    this.setSpcw12(spcw12);
    return this;
  }

  public void setSpcw12(String spcw12) {
    this.spcw12 = spcw12;
  }

  public String getSubcoMemo() {
    return this.subcoMemo;
  }

  public BidPrjSubco subcoMemo(String subcoMemo) {
    this.setSubcoMemo(subcoMemo);
    return this;
  }

  public void setSubcoMemo(String subcoMemo) {
    this.subcoMemo = subcoMemo;
  }

  public String getMet01() {
    return this.met01;
  }

  public BidPrjSubco met01(String met01) {
    this.setMet01(met01);
    return this;
  }

  public void setMet01(String met01) {
    this.met01 = met01;
  }

  public String getMet01b() {
    return this.met01b;
  }

  public BidPrjSubco met01b(String met01b) {
    this.setMet01b(met01b);
    return this;
  }

  public void setMet01b(String met01b) {
    this.met01b = met01b;
  }

  public String getMet01c() {
    return this.met01c;
  }

  public BidPrjSubco met01c(String met01c) {
    this.setMet01c(met01c);
    return this;
  }

  public void setMet01c(String met01c) {
    this.met01c = met01c;
  }

  public String getMet05() {
    return this.met05;
  }

  public BidPrjSubco met05(String met05) {
    this.setMet05(met05);
    return this;
  }

  public void setMet05(String met05) {
    this.met05 = met05;
  }

  public String getMet02f() {
    return this.met02f;
  }

  public BidPrjSubco met02f(String met02f) {
    this.setMet02f(met02f);
    return this;
  }

  public void setMet02f(String met02f) {
    this.met02f = met02f;
  }

  public String getMet01d() {
    return this.met01d;
  }

  public BidPrjSubco met01d(String met01d) {
    this.setMet01d(met01d);
    return this;
  }

  public void setMet01d(String met01d) {
    this.met01d = met01d;
  }

  public String getMet02() {
    return this.met02;
  }

  public BidPrjSubco met02(String met02) {
    this.setMet02(met02);
    return this;
  }

  public void setMet02(String met02) {
    this.met02 = met02;
  }

  public String getMet02b() {
    return this.met02b;
  }

  public BidPrjSubco met02b(String met02b) {
    this.setMet02b(met02b);
    return this;
  }

  public void setMet02b(String met02b) {
    this.met02b = met02b;
  }

  public String getMet04() {
    return this.met04;
  }

  public BidPrjSubco met04(String met04) {
    this.setMet04(met04);
    return this;
  }

  public void setMet04(String met04) {
    this.met04 = met04;
  }

  public String getMet03() {
    return this.met03;
  }

  public BidPrjSubco met03(String met03) {
    this.setMet03(met03);
    return this;
  }

  public void setMet03(String met03) {
    this.met03 = met03;
  }

  public String getMet03b() {
    return this.met03b;
  }

  public BidPrjSubco met03b(String met03b) {
    this.setMet03b(met03b);
    return this;
  }

  public void setMet03b(String met03b) {
    this.met03b = met03b;
  }

  public String getMet02c() {
    return this.met02c;
  }

  public BidPrjSubco met02c(String met02c) {
    this.setMet02c(met02c);
    return this;
  }

  public void setMet02c(String met02c) {
    this.met02c = met02c;
  }

  public String getMet02d() {
    return this.met02d;
  }

  public BidPrjSubco met02d(String met02d) {
    this.setMet02d(met02d);
    return this;
  }

  public void setMet02d(String met02d) {
    this.met02d = met02d;
  }

  public String getMet02e() {
    return this.met02e;
  }

  public BidPrjSubco met02e(String met02e) {
    this.setMet02e(met02e);
    return this;
  }

  public void setMet02e(String met02e) {
    this.met02e = met02e;
  }

  public Double getMet01v() {
    return this.met01v;
  }

  public BidPrjSubco met01v(Double met01v) {
    this.setMet01v(met01v);
    return this;
  }

  public void setMet01v(Double met01v) {
    this.met01v = met01v;
  }

  public Double getMet01bv() {
    return this.met01bv;
  }

  public BidPrjSubco met01bv(Double met01bv) {
    this.setMet01bv(met01bv);
    return this;
  }

  public void setMet01bv(Double met01bv) {
    this.met01bv = met01bv;
  }

  public Double getMet01cv() {
    return this.met01cv;
  }

  public BidPrjSubco met01cv(Double met01cv) {
    this.setMet01cv(met01cv);
    return this;
  }

  public void setMet01cv(Double met01cv) {
    this.met01cv = met01cv;
  }

  public Double getMet05v() {
    return this.met05v;
  }

  public BidPrjSubco met05v(Double met05v) {
    this.setMet05v(met05v);
    return this;
  }

  public void setMet05v(Double met05v) {
    this.met05v = met05v;
  }

  public Double getMet02fv() {
    return this.met02fv;
  }

  public BidPrjSubco met02fv(Double met02fv) {
    this.setMet02fv(met02fv);
    return this;
  }

  public void setMet02fv(Double met02fv) {
    this.met02fv = met02fv;
  }

  public Double getMet01dv() {
    return this.met01dv;
  }

  public BidPrjSubco met01dv(Double met01dv) {
    this.setMet01dv(met01dv);
    return this;
  }

  public void setMet01dv(Double met01dv) {
    this.met01dv = met01dv;
  }

  public Double getMet02v() {
    return this.met02v;
  }

  public BidPrjSubco met02v(Double met02v) {
    this.setMet02v(met02v);
    return this;
  }

  public void setMet02v(Double met02v) {
    this.met02v = met02v;
  }

  public Double getMet02bv() {
    return this.met02bv;
  }

  public BidPrjSubco met02bv(Double met02bv) {
    this.setMet02bv(met02bv);
    return this;
  }

  public void setMet02bv(Double met02bv) {
    this.met02bv = met02bv;
  }

  public Double getMet04v() {
    return this.met04v;
  }

  public BidPrjSubco met04v(Double met04v) {
    this.setMet04v(met04v);
    return this;
  }

  public void setMet04v(Double met04v) {
    this.met04v = met04v;
  }

  public Double getMet03v() {
    return this.met03v;
  }

  public BidPrjSubco met03v(Double met03v) {
    this.setMet03v(met03v);
    return this;
  }

  public void setMet03v(Double met03v) {
    this.met03v = met03v;
  }

  public Double getMet03bv() {
    return this.met03bv;
  }

  public BidPrjSubco met03bv(Double met03bv) {
    this.setMet03bv(met03bv);
    return this;
  }

  public void setMet03bv(Double met03bv) {
    this.met03bv = met03bv;
  }

  public Double getMet02cv() {
    return this.met02cv;
  }

  public BidPrjSubco met02cv(Double met02cv) {
    this.setMet02cv(met02cv);
    return this;
  }

  public void setMet02cv(Double met02cv) {
    this.met02cv = met02cv;
  }

  public Double getMet02dv() {
    return this.met02dv;
  }

  public BidPrjSubco met02dv(Double met02dv) {
    this.setMet02dv(met02dv);
    return this;
  }

  public void setMet02dv(Double met02dv) {
    this.met02dv = met02dv;
  }

  public Double getMet02ev() {
    return this.met02ev;
  }

  public BidPrjSubco met02ev(Double met02ev) {
    this.setMet02ev(met02ev);
    return this;
  }

  public void setMet02ev(Double met02ev) {
    this.met02ev = met02ev;
  }

  public String getQchk() {
    return this.qchk;
  }

  public BidPrjSubco qchk(String qchk) {
    this.setQchk(qchk);
    return this;
  }

  public void setQchk(String qchk) {
    this.qchk = qchk;
  }

  public Instant getCreateDate() {
    return this.createDate;
  }

  public BidPrjSubco createDate(Instant createDate) {
    this.setCreateDate(createDate);
    return this;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public String getCreateUser() {
    return this.createUser;
  }

  public BidPrjSubco createUser(String createUser) {
    this.setCreateUser(createUser);
    return this;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public Instant getUpdateDate() {
    return this.updateDate;
  }

  public BidPrjSubco updateDate(Instant updateDate) {
    this.setUpdateDate(updateDate);
    return this;
  }

  public void setUpdateDate(Instant updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdateUser() {
    return this.updateUser;
  }

  public BidPrjSubco updateUser(String updateUser) {
    this.setUpdateUser(updateUser);
    return this;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public String getRmtfil() {
    return this.rmtfil;
  }

  public BidPrjSubco rmtfil(String rmtfil) {
    this.setRmtfil(rmtfil);
    return this;
  }

  public void setRmtfil(String rmtfil) {
    this.rmtfil = rmtfil;
  }

  public String getuIp() {
    return this.uIp;
  }

  public BidPrjSubco uIp(String uIp) {
    this.setuIp(uIp);
    return this;
  }

  public void setuIp(String uIp) {
    this.uIp = uIp;
  }

  public String getMet06() {
    return this.met06;
  }

  public BidPrjSubco met06(String met06) {
    this.setMet06(met06);
    return this;
  }

  public void setMet06(String met06) {
    this.met06 = met06;
  }

  public String getMet07() {
    return this.met07;
  }

  public BidPrjSubco met07(String met07) {
    this.setMet07(met07);
    return this;
  }

  public void setMet07(String met07) {
    this.met07 = met07;
  }

  public String getMet08() {
    return this.met08;
  }

  public BidPrjSubco met08(String met08) {
    this.setMet08(met08);
    return this;
  }

  public void setMet08(String met08) {
    this.met08 = met08;
  }

  public String getMet09() {
    return this.met09;
  }

  public BidPrjSubco met09(String met09) {
    this.setMet09(met09);
    return this;
  }

  public void setMet09(String met09) {
    this.met09 = met09;
  }

  public String getMet10() {
    return this.met10;
  }

  public BidPrjSubco met10(String met10) {
    this.setMet10(met10);
    return this;
  }

  public void setMet10(String met10) {
    this.met10 = met10;
  }

  public String getMet11() {
    return this.met11;
  }

  public BidPrjSubco met11(String met11) {
    this.setMet11(met11);
    return this;
  }

  public void setMet11(String met11) {
    this.met11 = met11;
  }

  public String getMet12() {
    return this.met12;
  }

  public BidPrjSubco met12(String met12) {
    this.setMet12(met12);
    return this;
  }

  public void setMet12(String met12) {
    this.met12 = met12;
  }

  public String getMet13() {
    return this.met13;
  }

  public BidPrjSubco met13(String met13) {
    this.setMet13(met13);
    return this;
  }

  public void setMet13(String met13) {
    this.met13 = met13;
  }

  public String getMet14() {
    return this.met14;
  }

  public BidPrjSubco met14(String met14) {
    this.setMet14(met14);
    return this;
  }

  public void setMet14(String met14) {
    this.met14 = met14;
  }

  public String getMet15() {
    return this.met15;
  }

  public BidPrjSubco met15(String met15) {
    this.setMet15(met15);
    return this;
  }

  public void setMet15(String met15) {
    this.met15 = met15;
  }

  public String getMet16() {
    return this.met16;
  }

  public BidPrjSubco met16(String met16) {
    this.setMet16(met16);
    return this;
  }

  public void setMet16(String met16) {
    this.met16 = met16;
  }

  public String getMet99() {
    return this.met99;
  }

  public BidPrjSubco met99(String met99) {
    this.setMet99(met99);
    return this;
  }

  public void setMet99(String met99) {
    this.met99 = met99;
  }

  public Double getMet06v() {
    return this.met06v;
  }

  public BidPrjSubco met06v(Double met06v) {
    this.setMet06v(met06v);
    return this;
  }

  public void setMet06v(Double met06v) {
    this.met06v = met06v;
  }

  public Double getMet07v() {
    return this.met07v;
  }

  public BidPrjSubco met07v(Double met07v) {
    this.setMet07v(met07v);
    return this;
  }

  public void setMet07v(Double met07v) {
    this.met07v = met07v;
  }

  public Double getMet08v() {
    return this.met08v;
  }

  public BidPrjSubco met08v(Double met08v) {
    this.setMet08v(met08v);
    return this;
  }

  public void setMet08v(Double met08v) {
    this.met08v = met08v;
  }

  public Double getMet09v() {
    return this.met09v;
  }

  public BidPrjSubco met09v(Double met09v) {
    this.setMet09v(met09v);
    return this;
  }

  public void setMet09v(Double met09v) {
    this.met09v = met09v;
  }

  public Double getMet10v() {
    return this.met10v;
  }

  public BidPrjSubco met10v(Double met10v) {
    this.setMet10v(met10v);
    return this;
  }

  public void setMet10v(Double met10v) {
    this.met10v = met10v;
  }

  public Double getMet11v() {
    return this.met11v;
  }

  public BidPrjSubco met11v(Double met11v) {
    this.setMet11v(met11v);
    return this;
  }

  public void setMet11v(Double met11v) {
    this.met11v = met11v;
  }

  public Double getMet12v() {
    return this.met12v;
  }

  public BidPrjSubco met12v(Double met12v) {
    this.setMet12v(met12v);
    return this;
  }

  public void setMet12v(Double met12v) {
    this.met12v = met12v;
  }

  public Double getMet13v() {
    return this.met13v;
  }

  public BidPrjSubco met13v(Double met13v) {
    this.setMet13v(met13v);
    return this;
  }

  public void setMet13v(Double met13v) {
    this.met13v = met13v;
  }

  public Double getMet14v() {
    return this.met14v;
  }

  public BidPrjSubco met14v(Double met14v) {
    this.setMet14v(met14v);
    return this;
  }

  public void setMet14v(Double met14v) {
    this.met14v = met14v;
  }

  public Double getMet15v() {
    return this.met15v;
  }

  public BidPrjSubco met15v(Double met15v) {
    this.setMet15v(met15v);
    return this;
  }

  public void setMet15v(Double met15v) {
    this.met15v = met15v;
  }

  public Double getMet16v() {
    return this.met16v;
  }

  public BidPrjSubco met16v(Double met16v) {
    this.setMet16v(met16v);
    return this;
  }

  public void setMet16v(Double met16v) {
    this.met16v = met16v;
  }

  public Double getMet99v() {
    return this.met99v;
  }

  public BidPrjSubco met99v(Double met99v) {
    this.setMet99v(met99v);
    return this;
  }

  public void setMet99v(Double met99v) {
    this.met99v = met99v;
  }

  public String getMet99str() {
    return this.met99str;
  }

  public BidPrjSubco met99str(String met99str) {
    this.setMet99str(met99str);
    return this;
  }

  public void setMet99str(String met99str) {
    this.met99str = met99str;
  }

  public String getConfa() {
    return this.confa;
  }

  public BidPrjSubco confa(String confa) {
    this.setConfa(confa);
    return this;
  }

  public void setConfa(String confa) {
    this.confa = confa;
  }

  public String getConfb() {
    return this.confb;
  }

  public BidPrjSubco confb(String confb) {
    this.setConfb(confb);
    return this;
  }

  public void setConfb(String confb) {
    this.confb = confb;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BidPrjSubco)) {
      return false;
    }
    BidPrjSubco that = (BidPrjSubco) o;
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
    return "BidPrjSubco{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", subcoNo='" + getSubcoNo() + "'" +
            ", subCcut='" + getSubCcut() + "'" +
            ", coRegNo='" + getCoRegNo() + "'" +
            ", subBdgt1=" + getSubBdgt1() +
            ", upCcut='" + getUpCcut() + "'" +
            ", stype='" + getStype() + "'" +
            ", spcw01='" + getSpcw01() + "'" +
            ", spcw02='" + getSpcw02() + "'" +
            ", spcw03='" + getSpcw03() + "'" +
            ", spcw04='" + getSpcw04() + "'" +
            ", spcw05='" + getSpcw05() + "'" +
            ", spcw06='" + getSpcw06() + "'" +
            ", spcw07='" + getSpcw07() + "'" +
            ", spcw08='" + getSpcw08() + "'" +
            ", spcw09='" + getSpcw09() + "'" +
            ", spcw10='" + getSpcw10() + "'" +
            ", spcw11='" + getSpcw11() + "'" +
            ", spcw12='" + getSpcw12() + "'" +
            ", subcoMemo='" + getSubcoMemo() + "'" +
            ", met01='" + getMet01() + "'" +
            ", met01b='" + getMet01b() + "'" +
            ", met01c='" + getMet01c() + "'" +
            ", met05='" + getMet05() + "'" +
            ", met02f='" + getMet02f() + "'" +
            ", met01d='" + getMet01d() + "'" +
            ", met02='" + getMet02() + "'" +
            ", met02b='" + getMet02b() + "'" +
            ", met04='" + getMet04() + "'" +
            ", met03='" + getMet03() + "'" +
            ", met03b='" + getMet03b() + "'" +
            ", met02c='" + getMet02c() + "'" +
            ", met02d='" + getMet02d() + "'" +
            ", met02e='" + getMet02e() + "'" +
            ", met01v=" + getMet01v() +
            ", met01bv=" + getMet01bv() +
            ", met01cv=" + getMet01cv() +
            ", met05v=" + getMet05v() +
            ", met02fv=" + getMet02fv() +
            ", met01dv=" + getMet01dv() +
            ", met02v=" + getMet02v() +
            ", met02bv=" + getMet02bv() +
            ", met04v=" + getMet04v() +
            ", met03v=" + getMet03v() +
            ", met03bv=" + getMet03bv() +
            ", met02cv=" + getMet02cv() +
            ", met02dv=" + getMet02dv() +
            ", met02ev=" + getMet02ev() +
            ", qchk='" + getQchk() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", rmtfil='" + getRmtfil() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", met06='" + getMet06() + "'" +
            ", met07='" + getMet07() + "'" +
            ", met08='" + getMet08() + "'" +
            ", met09='" + getMet09() + "'" +
            ", met10='" + getMet10() + "'" +
            ", met11='" + getMet11() + "'" +
            ", met12='" + getMet12() + "'" +
            ", met13='" + getMet13() + "'" +
            ", met14='" + getMet14() + "'" +
            ", met15='" + getMet15() + "'" +
            ", met16='" + getMet16() + "'" +
            ", met99='" + getMet99() + "'" +
            ", met06v=" + getMet06v() +
            ", met07v=" + getMet07v() +
            ", met08v=" + getMet08v() +
            ", met09v=" + getMet09v() +
            ", met10v=" + getMet10v() +
            ", met11v=" + getMet11v() +
            ", met12v=" + getMet12v() +
            ", met13v=" + getMet13v() +
            ", met14v=" + getMet14v() +
            ", met15v=" + getMet15v() +
            ", met16v=" + getMet16v() +
            ", met99v=" + getMet99v() +
            ", met99str='" + getMet99str() + "'" +
            ", confa='" + getConfa() + "'" +
            ", confb='" + getConfb() + "'" +
            "}";
  }
}
