package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidGovCheck.
 */
@Entity
@IdClass(BidGovCheckPrimaryKey.class)
@Table(name = "bid_gov_check")
public class BidGovCheck implements Serializable {

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
    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    @Size(max = 21)
    @Column(name = "check_unit", length = 21)
    private String checkUnit;

    @Size(max = 500)
    @Column(name = "checker", length = 500)
    private String checker;

    @Size(max = 2000)
    @Column(name = "detel1", length = 2000)
    private String detel1;

    @Size(max = 2000)
    @Column(name = "detel2", length = 2000)
    private String detel2;

    @Size(max = 2000)
    @Column(name = "detel3", length = 2000)
    private String detel3;

    @Size(max = 1)
    @Column(name = "data_status", length = 1)
    private String dataStatus;

    @Column(name = "score", precision = 21, scale = 2)
    private BigDecimal score;

    @Size(max = 2)
    @Column(name = "chkunt", length = 2)
    private String chkunt;

    @Size(max = 2)
    @Column(name = "eng_flag", length = 2)
    private String engFlag;

    @Size(max = 2000)
    @Column(name = "sorea", length = 2000)
    private String sorea;

    @Column(name = "scoreb", precision = 21, scale = 2)
    private BigDecimal scoreb;

    @Column(name = "scorec", precision = 21, scale = 2)
    private BigDecimal scorec;

    @Column(name = "dead_date")
    private LocalDate deadDate;

    @Size(max = 2)
    @Column(name = "rove_flag", length = 2)
    private String roveFlag;

    @Size(max = 60)
    @Column(name = "rove_g_num", length = 60)
    private String roveGNum;

    @Size(max = 60)
    @Column(name = "rove_b_num", length = 60)
    private String roveBNum;

    @Size(max = 1)
    @Column(name = "rove_chk1", length = 1)
    private String roveChk1;

    @Size(max = 1)
    @Column(name = "rove_chk2", length = 1)
    private String roveChk2;

    @Size(max = 1)
    @Column(name = "rove_chk3", length = 1)
    private String roveChk3;

    @Size(max = 1)
    @Column(name = "rove_chk4", length = 1)
    private String roveChk4;

    @Column(name = "scorea", precision = 21, scale = 2)
    private BigDecimal scorea;

    @Size(max = 1)
    @Column(name = "chkflag", length = 1)
    private String chkflag;

    @Size(max = 100)
    @Column(name = "o_chker", length = 100)
    private String oChker;

    @Size(max = 1)
    @Column(name = "rove_chk5", length = 1)
    private String roveChk5;

    @Size(max = 1)
    @Column(name = "rove_chk6", length = 1)
    private String roveChk6;

    @Size(max = 100)
    @Column(name = "l_chker", length = 100)
    private String lChker;

    @Size(max = 1000)
    @Column(name = "un_chker", length = 1000)
    private String unChker;

    @Size(max = 1000)
    @Column(name = "co_chker", length = 1000)
    private String coChker;

    @Size(max = 1)
    @Column(name = "test_flag", length = 1)
    private String testFlag;

    @Size(max = 1)
    @Column(name = "rove_chk42", length = 1)
    private String roveChk42;

    @Size(max = 1)
    @Column(name = "rove_chk43", length = 1)
    private String roveChk43;

    @Size(max = 1)
    @Column(name = "rove_chk32", length = 1)
    private String roveChk32;

    @Size(max = 1)
    @Column(name = "rove_chk33", length = 1)
    private String roveChk33;

    @Size(max = 1)
    @Column(name = "rove_chk34", length = 1)
    private String roveChk34;

    @Size(max = 1)
    @Column(name = "rove_chk35", length = 1)
    private String roveChk35;

    @Size(max = 1)
    @Column(name = "rove_chk36", length = 1)
    private String roveChk36;

    @Size(max = 1)
    @Column(name = "rove_chk44", length = 1)
    private String roveChk44;

    @Size(max = 1)
    @Column(name = "rove_chk45", length = 1)
    private String roveChk45;

    @Size(max = 100)
    @Column(name = "chk_lead", length = 100)
    private String chkLead;

    @Size(max = 1)
    @Column(name = "rove_chk1a", length = 1)
    private String roveChk1a;

    @Size(max = 1)
    @Column(name = "rove_chk2a", length = 1)
    private String roveChk2a;

    @Size(max = 1)
    @Column(name = "rove_chk3a", length = 1)
    private String roveChk3a;

    @Size(max = 1)
    @Column(name = "rove_chk32a", length = 1)
    private String roveChk32a;

    @Size(max = 1)
    @Column(name = "rove_chk33a", length = 1)
    private String roveChk33a;

    @Size(max = 1)
    @Column(name = "rove_chk34a", length = 1)
    private String roveChk34a;

    @Size(max = 1)
    @Column(name = "rove_chk35a", length = 1)
    private String roveChk35a;

    @Size(max = 1)
    @Column(name = "rove_chk36a", length = 1)
    private String roveChk36a;

    @Size(max = 1)
    @Column(name = "rove_chk4a", length = 1)
    private String roveChk4a;

    @Size(max = 1)
    @Column(name = "rove_chk42a", length = 1)
    private String roveChk42a;

    @Size(max = 1)
    @Column(name = "rove_chk43a", length = 1)
    private String roveChk43a;

    @Size(max = 1)
    @Column(name = "rove_chk44a", length = 1)
    private String roveChk44a;

    @Size(max = 1)
    @Column(name = "rove_chk45a", length = 1)
    private String roveChk45a;

    @Column(name = "udt2")
    private LocalDate udt2;

    @Size(max = 2000)
    @Column(name = "detel4", length = 2000)
    private String detel4;

    @Size(max = 1)
    @Column(name = "rove_chk12", length = 1)
    private String roveChk12;

    @Size(max = 1)
    @Column(name = "rove_chk12a", length = 1)
    private String roveChk12a;

    @Size(max = 1)
    @Column(name = "rove_chk13", length = 1)
    private String roveChk13;

    @Size(max = 1)
    @Column(name = "rove_chk13a", length = 1)
    private String roveChk13a;

    @Size(max = 1)
    @Column(name = "rove_chk37", length = 1)
    private String roveChk37;

    @Size(max = 1)
    @Column(name = "rove_chk37a", length = 1)
    private String roveChk37a;

    @Size(max = 1)
    @Column(name = "test_flag2", length = 1)
    private String testFlag2;

    @Size(max = 1)
    @Column(name = "test_flag3", length = 1)
    private String testFlag3;

    @Size(max = 1)
    @Column(name = "test_flag4", length = 1)
    private String testFlag4;

    @Size(max = 1)
    @Column(name = "test_flag5", length = 1)
    private String testFlag5;

    @Size(max = 1)
    @Column(name = "test_flag6", length = 1)
    private String testFlag6;

    @Size(max = 1)
    @Column(name = "rove_chk14", length = 1)
    private String roveChk14;

    @Size(max = 1)
    @Column(name = "rove_chk14a", length = 1)
    private String roveChk14a;

    @Column(name = "rove_g_dat")
    private LocalDate roveGDat;

    @Size(max = 1000)
    @Column(name = "test_memo", length = 1000)
    private String testMemo;

    @Size(max = 2000)
    @Column(name = "detel0", length = 2000)
    private String detel0;

    @Size(max = 500)
    @Column(name = "ov7day", length = 500)
    private String ov7day;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 1)
    @Column(name = "rove_chk0", length = 1)
    private String roveChk0;

    @Size(max = 1)
    @Column(name = "can", length = 1)
    private String can;

    @Size(max = 1)
    @Column(name = "rove_chk5a", length = 1)
    private String roveChk5a;

    @Size(max = 2000)
    @Column(name = "detel5", length = 2000)
    private String detel5;

    @Column(name = "fgop1", precision = 21, scale = 2)
    private BigDecimal fgop1;

    @Column(name = "fgop2", precision = 21, scale = 2)
    private BigDecimal fgop2;

    @Column(name = "fgop3", precision = 21, scale = 2)
    private BigDecimal fgop3;

    @Column(name = "fgop4", precision = 21, scale = 2)
    private BigDecimal fgop4;

    @Column(name = "fgop5", precision = 21, scale = 2)
    private BigDecimal fgop5;

    @Size(max = 1)
    @Column(name = "test_flag7", length = 1)
    private String testFlag7;

    @Size(max = 1)
    @Column(name = "test_flag8", length = 1)
    private String testFlag8;

    @Size(max = 1)
    @Column(name = "test_flag9", length = 1)
    private String testFlag9;

    @Size(max = 1)
    @Column(name = "test_flaga", length = 1)
    private String testFlaga;

    @Size(max = 1)
    @Column(name = "test_flagb", length = 1)
    private String testFlagb;

    @Size(max = 1)
    @Column(name = "test_flagc", length = 1)
    private String testFlagc;

    @Size(max = 1)
    @Column(name = "test_flagd", length = 1)
    private String testFlagd;

    @Size(max = 1)
    @Column(name = "test_flage", length = 1)
    private String testFlage;

    @Size(max = 1)
    @Column(name = "test_flagf", length = 1)
    private String testFlagf;

    @Size(max = 1)
    @Column(name = "test_flagg", length = 1)
    private String testFlagg;

    @Size(max = 1)
    @Column(name = "isckp", length = 1)
    private String isckp;

    @Size(max = 1)
    @Column(name = "isckp2", length = 1)
    private String isckp2;

    @Size(max = 1)
    @Column(name = "isckp3", length = 1)
    private String isckp3;

    @Size(max = 1)
    @Column(name = "rove_chk7", length = 1)
    private String roveChk7;

    @Size(max = 1)
    @Column(name = "rove_chk7a", length = 1)
    private String roveChk7a;

    @Size(max = 300)
    @Column(name = "test_co", length = 300)
    private String testCo;

    @Column(name = "test_date")
    private LocalDate testDate;

    @Size(max = 300)
    @Column(name = "test_num", length = 300)
    private String testNum;

    @Size(max = 10)
    @Column(name = "chkunit_old", length = 10)
    private String chkunitOld;

    @Size(max = 1)
    @Column(name = "test_flagh", length = 1)
    private String testFlagh;

    @Size(max = 1)
    @Column(name = "test_flagz", length = 1)
    private String testFlagz;

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

    public BidGovCheckPrimaryKey getId() {
        return new BidGovCheckPrimaryKey(wkut, prjno, checkDate);
    }

    public BidGovCheck id(BidGovCheckPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidGovCheckPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidGovCheck wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidGovCheck prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getCheckDate() {
        return this.checkDate;
    }

    public BidGovCheck checkDate(LocalDate checkDate) {
        this.setCheckDate(checkDate);
        return this;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckUnit() {
        return this.checkUnit;
    }

    public BidGovCheck checkUnit(String checkUnit) {
        this.setCheckUnit(checkUnit);
        return this;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getChecker() {
        return this.checker;
    }

    public BidGovCheck checker(String checker) {
        this.setChecker(checker);
        return this;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getDetel1() {
        return this.detel1;
    }

    public BidGovCheck detel1(String detel1) {
        this.setDetel1(detel1);
        return this;
    }

    public void setDetel1(String detel1) {
        this.detel1 = detel1;
    }

    public String getDetel2() {
        return this.detel2;
    }

    public BidGovCheck detel2(String detel2) {
        this.setDetel2(detel2);
        return this;
    }

    public void setDetel2(String detel2) {
        this.detel2 = detel2;
    }

    public String getDetel3() {
        return this.detel3;
    }

    public BidGovCheck detel3(String detel3) {
        this.setDetel3(detel3);
        return this;
    }

    public void setDetel3(String detel3) {
        this.detel3 = detel3;
    }

    public String getDataStatus() {
        return this.dataStatus;
    }

    public BidGovCheck dataStatus(String dataStatus) {
        this.setDataStatus(dataStatus);
        return this;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public BigDecimal getScore() {
        return this.score;
    }

    public BidGovCheck score(BigDecimal score) {
        this.setScore(score);
        return this;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getChkunt() {
        return this.chkunt;
    }

    public BidGovCheck chkunt(String chkunt) {
        this.setChkunt(chkunt);
        return this;
    }

    public void setChkunt(String chkunt) {
        this.chkunt = chkunt;
    }

    public String getEngFlag() {
        return this.engFlag;
    }

    public BidGovCheck engFlag(String engFlag) {
        this.setEngFlag(engFlag);
        return this;
    }

    public void setEngFlag(String engFlag) {
        this.engFlag = engFlag;
    }

    public String getSorea() {
        return this.sorea;
    }

    public BidGovCheck sorea(String sorea) {
        this.setSorea(sorea);
        return this;
    }

    public void setSorea(String sorea) {
        this.sorea = sorea;
    }

    public BigDecimal getScoreb() {
        return this.scoreb;
    }

    public BidGovCheck scoreb(BigDecimal scoreb) {
        this.setScoreb(scoreb);
        return this;
    }

    public void setScoreb(BigDecimal scoreb) {
        this.scoreb = scoreb;
    }

    public BigDecimal getScorec() {
        return this.scorec;
    }

    public BidGovCheck scorec(BigDecimal scorec) {
        this.setScorec(scorec);
        return this;
    }

    public void setScorec(BigDecimal scorec) {
        this.scorec = scorec;
    }

    public LocalDate getDeadDate() {
        return this.deadDate;
    }

    public BidGovCheck deadDate(LocalDate deadDate) {
        this.setDeadDate(deadDate);
        return this;
    }

    public void setDeadDate(LocalDate deadDate) {
        this.deadDate = deadDate;
    }

    public String getRoveFlag() {
        return this.roveFlag;
    }

    public BidGovCheck roveFlag(String roveFlag) {
        this.setRoveFlag(roveFlag);
        return this;
    }

    public void setRoveFlag(String roveFlag) {
        this.roveFlag = roveFlag;
    }

    public String getRoveGNum() {
        return this.roveGNum;
    }

    public BidGovCheck roveGNum(String roveGNum) {
        this.setRoveGNum(roveGNum);
        return this;
    }

    public void setRoveGNum(String roveGNum) {
        this.roveGNum = roveGNum;
    }

    public String getRoveBNum() {
        return this.roveBNum;
    }

    public BidGovCheck roveBNum(String roveBNum) {
        this.setRoveBNum(roveBNum);
        return this;
    }

    public void setRoveBNum(String roveBNum) {
        this.roveBNum = roveBNum;
    }

    public String getRoveChk1() {
        return this.roveChk1;
    }

    public BidGovCheck roveChk1(String roveChk1) {
        this.setRoveChk1(roveChk1);
        return this;
    }

    public void setRoveChk1(String roveChk1) {
        this.roveChk1 = roveChk1;
    }

    public String getRoveChk2() {
        return this.roveChk2;
    }

    public BidGovCheck roveChk2(String roveChk2) {
        this.setRoveChk2(roveChk2);
        return this;
    }

    public void setRoveChk2(String roveChk2) {
        this.roveChk2 = roveChk2;
    }

    public String getRoveChk3() {
        return this.roveChk3;
    }

    public BidGovCheck roveChk3(String roveChk3) {
        this.setRoveChk3(roveChk3);
        return this;
    }

    public void setRoveChk3(String roveChk3) {
        this.roveChk3 = roveChk3;
    }

    public String getRoveChk4() {
        return this.roveChk4;
    }

    public BidGovCheck roveChk4(String roveChk4) {
        this.setRoveChk4(roveChk4);
        return this;
    }

    public void setRoveChk4(String roveChk4) {
        this.roveChk4 = roveChk4;
    }

    public BigDecimal getScorea() {
        return this.scorea;
    }

    public BidGovCheck scorea(BigDecimal scorea) {
        this.setScorea(scorea);
        return this;
    }

    public void setScorea(BigDecimal scorea) {
        this.scorea = scorea;
    }

    public String getChkflag() {
        return this.chkflag;
    }

    public BidGovCheck chkflag(String chkflag) {
        this.setChkflag(chkflag);
        return this;
    }

    public void setChkflag(String chkflag) {
        this.chkflag = chkflag;
    }

    public String getoChker() {
        return this.oChker;
    }

    public BidGovCheck oChker(String oChker) {
        this.setoChker(oChker);
        return this;
    }

    public void setoChker(String oChker) {
        this.oChker = oChker;
    }

    public String getRoveChk5() {
        return this.roveChk5;
    }

    public BidGovCheck roveChk5(String roveChk5) {
        this.setRoveChk5(roveChk5);
        return this;
    }

    public void setRoveChk5(String roveChk5) {
        this.roveChk5 = roveChk5;
    }

    public String getRoveChk6() {
        return this.roveChk6;
    }

    public BidGovCheck roveChk6(String roveChk6) {
        this.setRoveChk6(roveChk6);
        return this;
    }

    public void setRoveChk6(String roveChk6) {
        this.roveChk6 = roveChk6;
    }

    public String getlChker() {
        return this.lChker;
    }

    public BidGovCheck lChker(String lChker) {
        this.setlChker(lChker);
        return this;
    }

    public void setlChker(String lChker) {
        this.lChker = lChker;
    }

    public String getUnChker() {
        return this.unChker;
    }

    public BidGovCheck unChker(String unChker) {
        this.setUnChker(unChker);
        return this;
    }

    public void setUnChker(String unChker) {
        this.unChker = unChker;
    }

    public String getCoChker() {
        return this.coChker;
    }

    public BidGovCheck coChker(String coChker) {
        this.setCoChker(coChker);
        return this;
    }

    public void setCoChker(String coChker) {
        this.coChker = coChker;
    }

    public String getTestFlag() {
        return this.testFlag;
    }

    public BidGovCheck testFlag(String testFlag) {
        this.setTestFlag(testFlag);
        return this;
    }

    public void setTestFlag(String testFlag) {
        this.testFlag = testFlag;
    }

    public String getRoveChk42() {
        return this.roveChk42;
    }

    public BidGovCheck roveChk42(String roveChk42) {
        this.setRoveChk42(roveChk42);
        return this;
    }

    public void setRoveChk42(String roveChk42) {
        this.roveChk42 = roveChk42;
    }

    public String getRoveChk43() {
        return this.roveChk43;
    }

    public BidGovCheck roveChk43(String roveChk43) {
        this.setRoveChk43(roveChk43);
        return this;
    }

    public void setRoveChk43(String roveChk43) {
        this.roveChk43 = roveChk43;
    }

    public String getRoveChk32() {
        return this.roveChk32;
    }

    public BidGovCheck roveChk32(String roveChk32) {
        this.setRoveChk32(roveChk32);
        return this;
    }

    public void setRoveChk32(String roveChk32) {
        this.roveChk32 = roveChk32;
    }

    public String getRoveChk33() {
        return this.roveChk33;
    }

    public BidGovCheck roveChk33(String roveChk33) {
        this.setRoveChk33(roveChk33);
        return this;
    }

    public void setRoveChk33(String roveChk33) {
        this.roveChk33 = roveChk33;
    }

    public String getRoveChk34() {
        return this.roveChk34;
    }

    public BidGovCheck roveChk34(String roveChk34) {
        this.setRoveChk34(roveChk34);
        return this;
    }

    public void setRoveChk34(String roveChk34) {
        this.roveChk34 = roveChk34;
    }

    public String getRoveChk35() {
        return this.roveChk35;
    }

    public BidGovCheck roveChk35(String roveChk35) {
        this.setRoveChk35(roveChk35);
        return this;
    }

    public void setRoveChk35(String roveChk35) {
        this.roveChk35 = roveChk35;
    }

    public String getRoveChk36() {
        return this.roveChk36;
    }

    public BidGovCheck roveChk36(String roveChk36) {
        this.setRoveChk36(roveChk36);
        return this;
    }

    public void setRoveChk36(String roveChk36) {
        this.roveChk36 = roveChk36;
    }

    public String getRoveChk44() {
        return this.roveChk44;
    }

    public BidGovCheck roveChk44(String roveChk44) {
        this.setRoveChk44(roveChk44);
        return this;
    }

    public void setRoveChk44(String roveChk44) {
        this.roveChk44 = roveChk44;
    }

    public String getRoveChk45() {
        return this.roveChk45;
    }

    public BidGovCheck roveChk45(String roveChk45) {
        this.setRoveChk45(roveChk45);
        return this;
    }

    public void setRoveChk45(String roveChk45) {
        this.roveChk45 = roveChk45;
    }

    public String getChkLead() {
        return this.chkLead;
    }

    public BidGovCheck chkLead(String chkLead) {
        this.setChkLead(chkLead);
        return this;
    }

    public void setChkLead(String chkLead) {
        this.chkLead = chkLead;
    }

    public String getRoveChk1a() {
        return this.roveChk1a;
    }

    public BidGovCheck roveChk1a(String roveChk1a) {
        this.setRoveChk1a(roveChk1a);
        return this;
    }

    public void setRoveChk1a(String roveChk1a) {
        this.roveChk1a = roveChk1a;
    }

    public String getRoveChk2a() {
        return this.roveChk2a;
    }

    public BidGovCheck roveChk2a(String roveChk2a) {
        this.setRoveChk2a(roveChk2a);
        return this;
    }

    public void setRoveChk2a(String roveChk2a) {
        this.roveChk2a = roveChk2a;
    }

    public String getRoveChk3a() {
        return this.roveChk3a;
    }

    public BidGovCheck roveChk3a(String roveChk3a) {
        this.setRoveChk3a(roveChk3a);
        return this;
    }

    public void setRoveChk3a(String roveChk3a) {
        this.roveChk3a = roveChk3a;
    }

    public String getRoveChk32a() {
        return this.roveChk32a;
    }

    public BidGovCheck roveChk32a(String roveChk32a) {
        this.setRoveChk32a(roveChk32a);
        return this;
    }

    public void setRoveChk32a(String roveChk32a) {
        this.roveChk32a = roveChk32a;
    }

    public String getRoveChk33a() {
        return this.roveChk33a;
    }

    public BidGovCheck roveChk33a(String roveChk33a) {
        this.setRoveChk33a(roveChk33a);
        return this;
    }

    public void setRoveChk33a(String roveChk33a) {
        this.roveChk33a = roveChk33a;
    }

    public String getRoveChk34a() {
        return this.roveChk34a;
    }

    public BidGovCheck roveChk34a(String roveChk34a) {
        this.setRoveChk34a(roveChk34a);
        return this;
    }

    public void setRoveChk34a(String roveChk34a) {
        this.roveChk34a = roveChk34a;
    }

    public String getRoveChk35a() {
        return this.roveChk35a;
    }

    public BidGovCheck roveChk35a(String roveChk35a) {
        this.setRoveChk35a(roveChk35a);
        return this;
    }

    public void setRoveChk35a(String roveChk35a) {
        this.roveChk35a = roveChk35a;
    }

    public String getRoveChk36a() {
        return this.roveChk36a;
    }

    public BidGovCheck roveChk36a(String roveChk36a) {
        this.setRoveChk36a(roveChk36a);
        return this;
    }

    public void setRoveChk36a(String roveChk36a) {
        this.roveChk36a = roveChk36a;
    }

    public String getRoveChk4a() {
        return this.roveChk4a;
    }

    public BidGovCheck roveChk4a(String roveChk4a) {
        this.setRoveChk4a(roveChk4a);
        return this;
    }

    public void setRoveChk4a(String roveChk4a) {
        this.roveChk4a = roveChk4a;
    }

    public String getRoveChk42a() {
        return this.roveChk42a;
    }

    public BidGovCheck roveChk42a(String roveChk42a) {
        this.setRoveChk42a(roveChk42a);
        return this;
    }

    public void setRoveChk42a(String roveChk42a) {
        this.roveChk42a = roveChk42a;
    }

    public String getRoveChk43a() {
        return this.roveChk43a;
    }

    public BidGovCheck roveChk43a(String roveChk43a) {
        this.setRoveChk43a(roveChk43a);
        return this;
    }

    public void setRoveChk43a(String roveChk43a) {
        this.roveChk43a = roveChk43a;
    }

    public String getRoveChk44a() {
        return this.roveChk44a;
    }

    public BidGovCheck roveChk44a(String roveChk44a) {
        this.setRoveChk44a(roveChk44a);
        return this;
    }

    public void setRoveChk44a(String roveChk44a) {
        this.roveChk44a = roveChk44a;
    }

    public String getRoveChk45a() {
        return this.roveChk45a;
    }

    public BidGovCheck roveChk45a(String roveChk45a) {
        this.setRoveChk45a(roveChk45a);
        return this;
    }

    public void setRoveChk45a(String roveChk45a) {
        this.roveChk45a = roveChk45a;
    }

    public LocalDate getUdt2() {
        return this.udt2;
    }

    public BidGovCheck udt2(LocalDate udt2) {
        this.setUdt2(udt2);
        return this;
    }

    public void setUdt2(LocalDate udt2) {
        this.udt2 = udt2;
    }

    public String getDetel4() {
        return this.detel4;
    }

    public BidGovCheck detel4(String detel4) {
        this.setDetel4(detel4);
        return this;
    }

    public void setDetel4(String detel4) {
        this.detel4 = detel4;
    }

    public String getRoveChk12() {
        return this.roveChk12;
    }

    public BidGovCheck roveChk12(String roveChk12) {
        this.setRoveChk12(roveChk12);
        return this;
    }

    public void setRoveChk12(String roveChk12) {
        this.roveChk12 = roveChk12;
    }

    public String getRoveChk12a() {
        return this.roveChk12a;
    }

    public BidGovCheck roveChk12a(String roveChk12a) {
        this.setRoveChk12a(roveChk12a);
        return this;
    }

    public void setRoveChk12a(String roveChk12a) {
        this.roveChk12a = roveChk12a;
    }

    public String getRoveChk13() {
        return this.roveChk13;
    }

    public BidGovCheck roveChk13(String roveChk13) {
        this.setRoveChk13(roveChk13);
        return this;
    }

    public void setRoveChk13(String roveChk13) {
        this.roveChk13 = roveChk13;
    }

    public String getRoveChk13a() {
        return this.roveChk13a;
    }

    public BidGovCheck roveChk13a(String roveChk13a) {
        this.setRoveChk13a(roveChk13a);
        return this;
    }

    public void setRoveChk13a(String roveChk13a) {
        this.roveChk13a = roveChk13a;
    }

    public String getRoveChk37() {
        return this.roveChk37;
    }

    public BidGovCheck roveChk37(String roveChk37) {
        this.setRoveChk37(roveChk37);
        return this;
    }

    public void setRoveChk37(String roveChk37) {
        this.roveChk37 = roveChk37;
    }

    public String getRoveChk37a() {
        return this.roveChk37a;
    }

    public BidGovCheck roveChk37a(String roveChk37a) {
        this.setRoveChk37a(roveChk37a);
        return this;
    }

    public void setRoveChk37a(String roveChk37a) {
        this.roveChk37a = roveChk37a;
    }

    public String getTestFlag2() {
        return this.testFlag2;
    }

    public BidGovCheck testFlag2(String testFlag2) {
        this.setTestFlag2(testFlag2);
        return this;
    }

    public void setTestFlag2(String testFlag2) {
        this.testFlag2 = testFlag2;
    }

    public String getTestFlag3() {
        return this.testFlag3;
    }

    public BidGovCheck testFlag3(String testFlag3) {
        this.setTestFlag3(testFlag3);
        return this;
    }

    public void setTestFlag3(String testFlag3) {
        this.testFlag3 = testFlag3;
    }

    public String getTestFlag4() {
        return this.testFlag4;
    }

    public BidGovCheck testFlag4(String testFlag4) {
        this.setTestFlag4(testFlag4);
        return this;
    }

    public void setTestFlag4(String testFlag4) {
        this.testFlag4 = testFlag4;
    }

    public String getTestFlag5() {
        return this.testFlag5;
    }

    public BidGovCheck testFlag5(String testFlag5) {
        this.setTestFlag5(testFlag5);
        return this;
    }

    public void setTestFlag5(String testFlag5) {
        this.testFlag5 = testFlag5;
    }

    public String getTestFlag6() {
        return this.testFlag6;
    }

    public BidGovCheck testFlag6(String testFlag6) {
        this.setTestFlag6(testFlag6);
        return this;
    }

    public void setTestFlag6(String testFlag6) {
        this.testFlag6 = testFlag6;
    }

    public String getRoveChk14() {
        return this.roveChk14;
    }

    public BidGovCheck roveChk14(String roveChk14) {
        this.setRoveChk14(roveChk14);
        return this;
    }

    public void setRoveChk14(String roveChk14) {
        this.roveChk14 = roveChk14;
    }

    public String getRoveChk14a() {
        return this.roveChk14a;
    }

    public BidGovCheck roveChk14a(String roveChk14a) {
        this.setRoveChk14a(roveChk14a);
        return this;
    }

    public void setRoveChk14a(String roveChk14a) {
        this.roveChk14a = roveChk14a;
    }

    public LocalDate getRoveGDat() {
        return this.roveGDat;
    }

    public BidGovCheck roveGDat(LocalDate roveGDat) {
        this.setRoveGDat(roveGDat);
        return this;
    }

    public void setRoveGDat(LocalDate roveGDat) {
        this.roveGDat = roveGDat;
    }

    public String getTestMemo() {
        return this.testMemo;
    }

    public BidGovCheck testMemo(String testMemo) {
        this.setTestMemo(testMemo);
        return this;
    }

    public void setTestMemo(String testMemo) {
        this.testMemo = testMemo;
    }

    public String getDetel0() {
        return this.detel0;
    }

    public BidGovCheck detel0(String detel0) {
        this.setDetel0(detel0);
        return this;
    }

    public void setDetel0(String detel0) {
        this.detel0 = detel0;
    }

    public String getOv7day() {
        return this.ov7day;
    }

    public BidGovCheck ov7day(String ov7day) {
        this.setOv7day(ov7day);
        return this;
    }

    public void setOv7day(String ov7day) {
        this.ov7day = ov7day;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidGovCheck endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRoveChk0() {
        return this.roveChk0;
    }

    public BidGovCheck roveChk0(String roveChk0) {
        this.setRoveChk0(roveChk0);
        return this;
    }

    public void setRoveChk0(String roveChk0) {
        this.roveChk0 = roveChk0;
    }

    public String getCan() {
        return this.can;
    }

    public BidGovCheck can(String can) {
        this.setCan(can);
        return this;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getRoveChk5a() {
        return this.roveChk5a;
    }

    public BidGovCheck roveChk5a(String roveChk5a) {
        this.setRoveChk5a(roveChk5a);
        return this;
    }

    public void setRoveChk5a(String roveChk5a) {
        this.roveChk5a = roveChk5a;
    }

    public String getDetel5() {
        return this.detel5;
    }

    public BidGovCheck detel5(String detel5) {
        this.setDetel5(detel5);
        return this;
    }

    public void setDetel5(String detel5) {
        this.detel5 = detel5;
    }

    public BigDecimal getFgop1() {
        return this.fgop1;
    }

    public BidGovCheck fgop1(BigDecimal fgop1) {
        this.setFgop1(fgop1);
        return this;
    }

    public void setFgop1(BigDecimal fgop1) {
        this.fgop1 = fgop1;
    }

    public BigDecimal getFgop2() {
        return this.fgop2;
    }

    public BidGovCheck fgop2(BigDecimal fgop2) {
        this.setFgop2(fgop2);
        return this;
    }

    public void setFgop2(BigDecimal fgop2) {
        this.fgop2 = fgop2;
    }

    public BigDecimal getFgop3() {
        return this.fgop3;
    }

    public BidGovCheck fgop3(BigDecimal fgop3) {
        this.setFgop3(fgop3);
        return this;
    }

    public void setFgop3(BigDecimal fgop3) {
        this.fgop3 = fgop3;
    }

    public BigDecimal getFgop4() {
        return this.fgop4;
    }

    public BidGovCheck fgop4(BigDecimal fgop4) {
        this.setFgop4(fgop4);
        return this;
    }

    public void setFgop4(BigDecimal fgop4) {
        this.fgop4 = fgop4;
    }

    public BigDecimal getFgop5() {
        return this.fgop5;
    }

    public BidGovCheck fgop5(BigDecimal fgop5) {
        this.setFgop5(fgop5);
        return this;
    }

    public void setFgop5(BigDecimal fgop5) {
        this.fgop5 = fgop5;
    }

    public String getTestFlag7() {
        return this.testFlag7;
    }

    public BidGovCheck testFlag7(String testFlag7) {
        this.setTestFlag7(testFlag7);
        return this;
    }

    public void setTestFlag7(String testFlag7) {
        this.testFlag7 = testFlag7;
    }

    public String getTestFlag8() {
        return this.testFlag8;
    }

    public BidGovCheck testFlag8(String testFlag8) {
        this.setTestFlag8(testFlag8);
        return this;
    }

    public void setTestFlag8(String testFlag8) {
        this.testFlag8 = testFlag8;
    }

    public String getTestFlag9() {
        return this.testFlag9;
    }

    public BidGovCheck testFlag9(String testFlag9) {
        this.setTestFlag9(testFlag9);
        return this;
    }

    public void setTestFlag9(String testFlag9) {
        this.testFlag9 = testFlag9;
    }

    public String getTestFlaga() {
        return this.testFlaga;
    }

    public BidGovCheck testFlaga(String testFlaga) {
        this.setTestFlaga(testFlaga);
        return this;
    }

    public void setTestFlaga(String testFlaga) {
        this.testFlaga = testFlaga;
    }

    public String getTestFlagb() {
        return this.testFlagb;
    }

    public BidGovCheck testFlagb(String testFlagb) {
        this.setTestFlagb(testFlagb);
        return this;
    }

    public void setTestFlagb(String testFlagb) {
        this.testFlagb = testFlagb;
    }

    public String getTestFlagc() {
        return this.testFlagc;
    }

    public BidGovCheck testFlagc(String testFlagc) {
        this.setTestFlagc(testFlagc);
        return this;
    }

    public void setTestFlagc(String testFlagc) {
        this.testFlagc = testFlagc;
    }

    public String getTestFlagd() {
        return this.testFlagd;
    }

    public BidGovCheck testFlagd(String testFlagd) {
        this.setTestFlagd(testFlagd);
        return this;
    }

    public void setTestFlagd(String testFlagd) {
        this.testFlagd = testFlagd;
    }

    public String getTestFlage() {
        return this.testFlage;
    }

    public BidGovCheck testFlage(String testFlage) {
        this.setTestFlage(testFlage);
        return this;
    }

    public void setTestFlage(String testFlage) {
        this.testFlage = testFlage;
    }

    public String getTestFlagf() {
        return this.testFlagf;
    }

    public BidGovCheck testFlagf(String testFlagf) {
        this.setTestFlagf(testFlagf);
        return this;
    }

    public void setTestFlagf(String testFlagf) {
        this.testFlagf = testFlagf;
    }

    public String getTestFlagg() {
        return this.testFlagg;
    }

    public BidGovCheck testFlagg(String testFlagg) {
        this.setTestFlagg(testFlagg);
        return this;
    }

    public void setTestFlagg(String testFlagg) {
        this.testFlagg = testFlagg;
    }

    public String getIsckp() {
        return this.isckp;
    }

    public BidGovCheck isckp(String isckp) {
        this.setIsckp(isckp);
        return this;
    }

    public void setIsckp(String isckp) {
        this.isckp = isckp;
    }

    public String getIsckp2() {
        return this.isckp2;
    }

    public BidGovCheck isckp2(String isckp2) {
        this.setIsckp2(isckp2);
        return this;
    }

    public void setIsckp2(String isckp2) {
        this.isckp2 = isckp2;
    }

    public String getIsckp3() {
        return this.isckp3;
    }

    public BidGovCheck isckp3(String isckp3) {
        this.setIsckp3(isckp3);
        return this;
    }

    public void setIsckp3(String isckp3) {
        this.isckp3 = isckp3;
    }

    public String getRoveChk7() {
        return this.roveChk7;
    }

    public BidGovCheck roveChk7(String roveChk7) {
        this.setRoveChk7(roveChk7);
        return this;
    }

    public void setRoveChk7(String roveChk7) {
        this.roveChk7 = roveChk7;
    }

    public String getRoveChk7a() {
        return this.roveChk7a;
    }

    public BidGovCheck roveChk7a(String roveChk7a) {
        this.setRoveChk7a(roveChk7a);
        return this;
    }

    public void setRoveChk7a(String roveChk7a) {
        this.roveChk7a = roveChk7a;
    }

    public String getTestCo() {
        return this.testCo;
    }

    public BidGovCheck testCo(String testCo) {
        this.setTestCo(testCo);
        return this;
    }

    public void setTestCo(String testCo) {
        this.testCo = testCo;
    }

    public LocalDate getTestDate() {
        return this.testDate;
    }

    public BidGovCheck testDate(LocalDate testDate) {
        this.setTestDate(testDate);
        return this;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestNum() {
        return this.testNum;
    }

    public BidGovCheck testNum(String testNum) {
        this.setTestNum(testNum);
        return this;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getChkunitOld() {
        return this.chkunitOld;
    }

    public BidGovCheck chkunitOld(String chkunitOld) {
        this.setChkunitOld(chkunitOld);
        return this;
    }

    public void setChkunitOld(String chkunitOld) {
        this.chkunitOld = chkunitOld;
    }

    public String getTestFlagh() {
        return this.testFlagh;
    }

    public BidGovCheck testFlagh(String testFlagh) {
        this.setTestFlagh(testFlagh);
        return this;
    }

    public void setTestFlagh(String testFlagh) {
        this.testFlagh = testFlagh;
    }

    public String getTestFlagz() {
        return this.testFlagz;
    }

    public BidGovCheck testFlagz(String testFlagz) {
        this.setTestFlagz(testFlagz);
        return this;
    }

    public void setTestFlagz(String testFlagz) {
        this.testFlagz = testFlagz;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidGovCheck createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidGovCheck createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidGovCheck updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidGovCheck updateUser(String updateUser) {
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
        if (o == null || getClass() != o.getClass()) return false;
        BidGovCheck that = (BidGovCheck) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && checkDate.equals(that.checkDate);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovCheck{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", checkUnit='" + getCheckUnit() + "'" +
            ", checker='" + getChecker() + "'" +
            ", detel1='" + getDetel1() + "'" +
            ", detel2='" + getDetel2() + "'" +
            ", detel3='" + getDetel3() + "'" +
            ", dataStatus='" + getDataStatus() + "'" +
            ", score=" + getScore() +
            ", chkunt='" + getChkunt() + "'" +
            ", engFlag='" + getEngFlag() + "'" +
            ", sorea='" + getSorea() + "'" +
            ", scoreb=" + getScoreb() +
            ", scorec=" + getScorec() +
            ", deadDate='" + getDeadDate() + "'" +
            ", roveFlag='" + getRoveFlag() + "'" +
            ", roveGNum='" + getRoveGNum() + "'" +
            ", roveBNum='" + getRoveBNum() + "'" +
            ", roveChk1='" + getRoveChk1() + "'" +
            ", roveChk2='" + getRoveChk2() + "'" +
            ", roveChk3='" + getRoveChk3() + "'" +
            ", roveChk4='" + getRoveChk4() + "'" +
            ", scorea=" + getScorea() +
            ", chkflag='" + getChkflag() + "'" +
            ", oChker='" + getoChker() + "'" +
            ", roveChk5='" + getRoveChk5() + "'" +
            ", roveChk6='" + getRoveChk6() + "'" +
            ", lChker='" + getlChker() + "'" +
            ", unChker='" + getUnChker() + "'" +
            ", coChker='" + getCoChker() + "'" +
            ", testFlag='" + getTestFlag() + "'" +
            ", roveChk42='" + getRoveChk42() + "'" +
            ", roveChk43='" + getRoveChk43() + "'" +
            ", roveChk32='" + getRoveChk32() + "'" +
            ", roveChk33='" + getRoveChk33() + "'" +
            ", roveChk34='" + getRoveChk34() + "'" +
            ", roveChk35='" + getRoveChk35() + "'" +
            ", roveChk36='" + getRoveChk36() + "'" +
            ", roveChk44='" + getRoveChk44() + "'" +
            ", roveChk45='" + getRoveChk45() + "'" +
            ", chkLead='" + getChkLead() + "'" +
            ", roveChk1a='" + getRoveChk1a() + "'" +
            ", roveChk2a='" + getRoveChk2a() + "'" +
            ", roveChk3a='" + getRoveChk3a() + "'" +
            ", roveChk32a='" + getRoveChk32a() + "'" +
            ", roveChk33a='" + getRoveChk33a() + "'" +
            ", roveChk34a='" + getRoveChk34a() + "'" +
            ", roveChk35a='" + getRoveChk35a() + "'" +
            ", roveChk36a='" + getRoveChk36a() + "'" +
            ", roveChk4a='" + getRoveChk4a() + "'" +
            ", roveChk42a='" + getRoveChk42a() + "'" +
            ", roveChk43a='" + getRoveChk43a() + "'" +
            ", roveChk44a='" + getRoveChk44a() + "'" +
            ", roveChk45a='" + getRoveChk45a() + "'" +
            ", udt2='" + getUdt2() + "'" +
            ", detel4='" + getDetel4() + "'" +
            ", roveChk12='" + getRoveChk12() + "'" +
            ", roveChk12a='" + getRoveChk12a() + "'" +
            ", roveChk13='" + getRoveChk13() + "'" +
            ", roveChk13a='" + getRoveChk13a() + "'" +
            ", roveChk37='" + getRoveChk37() + "'" +
            ", roveChk37a='" + getRoveChk37a() + "'" +
            ", testFlag2='" + getTestFlag2() + "'" +
            ", testFlag3='" + getTestFlag3() + "'" +
            ", testFlag4='" + getTestFlag4() + "'" +
            ", testFlag5='" + getTestFlag5() + "'" +
            ", testFlag6='" + getTestFlag6() + "'" +
            ", roveChk14='" + getRoveChk14() + "'" +
            ", roveChk14a='" + getRoveChk14a() + "'" +
            ", roveGDat='" + getRoveGDat() + "'" +
            ", testMemo='" + getTestMemo() + "'" +
            ", detel0='" + getDetel0() + "'" +
            ", ov7day='" + getOv7day() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", roveChk0='" + getRoveChk0() + "'" +
            ", can='" + getCan() + "'" +
            ", roveChk5a='" + getRoveChk5a() + "'" +
            ", detel5='" + getDetel5() + "'" +
            ", fgop1=" + getFgop1() +
            ", fgop2=" + getFgop2() +
            ", fgop3=" + getFgop3() +
            ", fgop4=" + getFgop4() +
            ", fgop5=" + getFgop5() +
            ", testFlag7='" + getTestFlag7() + "'" +
            ", testFlag8='" + getTestFlag8() + "'" +
            ", testFlag9='" + getTestFlag9() + "'" +
            ", testFlaga='" + getTestFlaga() + "'" +
            ", testFlagb='" + getTestFlagb() + "'" +
            ", testFlagc='" + getTestFlagc() + "'" +
            ", testFlagd='" + getTestFlagd() + "'" +
            ", testFlage='" + getTestFlage() + "'" +
            ", testFlagf='" + getTestFlagf() + "'" +
            ", testFlagg='" + getTestFlagg() + "'" +
            ", isckp='" + getIsckp() + "'" +
            ", isckp2='" + getIsckp2() + "'" +
            ", isckp3='" + getIsckp3() + "'" +
            ", roveChk7='" + getRoveChk7() + "'" +
            ", roveChk7a='" + getRoveChk7a() + "'" +
            ", testCo='" + getTestCo() + "'" +
            ", testDate='" + getTestDate() + "'" +
            ", testNum='" + getTestNum() + "'" +
            ", chkunitOld='" + getChkunitOld() + "'" +
            ", testFlagh='" + getTestFlagh() + "'" +
            ", testFlagz='" + getTestFlagz() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
