package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidGovCheckPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidGovCheck} entity.
 */
public class BidGovCheckDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate checkDate;

    @Size(max = 21)
    private String checkUnit;

    @Size(max = 500)
    private String checker;

    @Size(max = 2000)
    private String detel1;

    @Size(max = 2000)
    private String detel2;

    @Size(max = 2000)
    private String detel3;

    @Size(max = 1)
    private String dataStatus;

    private BigDecimal score;

    @Size(max = 2)
    private String chkunt;

    @Size(max = 2)
    private String engFlag;

    @Size(max = 2000)
    private String sorea;

    private BigDecimal scoreb;

    private BigDecimal scorec;

    private LocalDate deadDate;

    @Size(max = 2)
    private String roveFlag;

    @Size(max = 60)
    private String roveGNum;

    @Size(max = 60)
    private String roveBNum;

    @Size(max = 1)
    private String roveChk1;

    @Size(max = 1)
    private String roveChk2;

    @Size(max = 1)
    private String roveChk3;

    @Size(max = 1)
    private String roveChk4;

    private BigDecimal scorea;

    @Size(max = 1)
    private String chkflag;

    @Size(max = 100)
    private String oChker;

    @Size(max = 1)
    private String roveChk5;

    @Size(max = 1)
    private String roveChk6;

    @Size(max = 100)
    private String lChker;

    @Size(max = 1000)
    private String unChker;

    @Size(max = 1000)
    private String coChker;

    @Size(max = 1)
    private String testFlag;

    @Size(max = 1)
    private String roveChk42;

    @Size(max = 1)
    private String roveChk43;

    @Size(max = 1)
    private String roveChk32;

    @Size(max = 1)
    private String roveChk33;

    @Size(max = 1)
    private String roveChk34;

    @Size(max = 1)
    private String roveChk35;

    @Size(max = 1)
    private String roveChk36;

    @Size(max = 1)
    private String roveChk44;

    @Size(max = 1)
    private String roveChk45;

    @Size(max = 100)
    private String chkLead;

    @Size(max = 1)
    private String roveChk1a;

    @Size(max = 1)
    private String roveChk2a;

    @Size(max = 1)
    private String roveChk3a;

    @Size(max = 1)
    private String roveChk32a;

    @Size(max = 1)
    private String roveChk33a;

    @Size(max = 1)
    private String roveChk34a;

    @Size(max = 1)
    private String roveChk35a;

    @Size(max = 1)
    private String roveChk36a;

    @Size(max = 1)
    private String roveChk4a;

    @Size(max = 1)
    private String roveChk42a;

    @Size(max = 1)
    private String roveChk43a;

    @Size(max = 1)
    private String roveChk44a;

    @Size(max = 1)
    private String roveChk45a;

    private LocalDate udt2;

    @Size(max = 2000)
    private String detel4;

    @Size(max = 1)
    private String roveChk12;

    @Size(max = 1)
    private String roveChk12a;

    @Size(max = 1)
    private String roveChk13;

    @Size(max = 1)
    private String roveChk13a;

    @Size(max = 1)
    private String roveChk37;

    @Size(max = 1)
    private String roveChk37a;

    @Size(max = 1)
    private String testFlag2;

    @Size(max = 1)
    private String testFlag3;

    @Size(max = 1)
    private String testFlag4;

    @Size(max = 1)
    private String testFlag5;

    @Size(max = 1)
    private String testFlag6;

    @Size(max = 1)
    private String roveChk14;

    @Size(max = 1)
    private String roveChk14a;

    private LocalDate roveGDat;

    @Size(max = 1000)
    private String testMemo;

    @Size(max = 2000)
    private String detel0;

    @Size(max = 500)
    private String ov7day;

    private LocalDate endDate;

    @Size(max = 1)
    private String roveChk0;

    @Size(max = 1)
    private String can;

    @Size(max = 1)
    private String roveChk5a;

    @Size(max = 2000)
    private String detel5;

    private BigDecimal fgop1;

    private BigDecimal fgop2;

    private BigDecimal fgop3;

    private BigDecimal fgop4;

    private BigDecimal fgop5;

    @Size(max = 1)
    private String testFlag7;

    @Size(max = 1)
    private String testFlag8;

    @Size(max = 1)
    private String testFlag9;

    @Size(max = 1)
    private String testFlaga;

    @Size(max = 1)
    private String testFlagb;

    @Size(max = 1)
    private String testFlagc;

    @Size(max = 1)
    private String testFlagd;

    @Size(max = 1)
    private String testFlage;

    @Size(max = 1)
    private String testFlagf;

    @Size(max = 1)
    private String testFlagg;

    @Size(max = 1)
    private String isckp;

    @Size(max = 1)
    private String isckp2;

    @Size(max = 1)
    private String isckp3;

    @Size(max = 1)
    private String roveChk7;

    @Size(max = 1)
    private String roveChk7a;

    @Size(max = 300)
    private String testCo;

    private LocalDate testDate;

    @Size(max = 300)
    private String testNum;

    @Size(max = 10)
    private String chkunitOld;

    @Size(max = 1)
    private String testFlagh;

    @Size(max = 1)
    private String testFlagz;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    private int rowNum;

    private String name;

    private String checkString;

    private String roveString;

    public BidGovCheckPrimaryKey getId() {
        return new BidGovCheckPrimaryKey(wkut, prjno, checkDate);
    }

    public void setId(BidGovCheckPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.checkDate = id.getCheckDate();
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getDetel1() {
        return detel1;
    }

    public void setDetel1(String detel1) {
        this.detel1 = detel1;
    }

    public String getDetel2() {
        return detel2;
    }

    public void setDetel2(String detel2) {
        this.detel2 = detel2;
    }

    public String getDetel3() {
        return detel3;
    }

    public void setDetel3(String detel3) {
        this.detel3 = detel3;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getChkunt() {
        return chkunt;
    }

    public void setChkunt(String chkunt) {
        this.chkunt = chkunt;
    }

    public String getEngFlag() {
        return engFlag;
    }

    public void setEngFlag(String engFlag) {
        this.engFlag = engFlag;
    }

    public String getSorea() {
        return sorea;
    }

    public void setSorea(String sorea) {
        this.sorea = sorea;
    }

    public BigDecimal getScoreb() {
        return scoreb;
    }

    public void setScoreb(BigDecimal scoreb) {
        this.scoreb = scoreb;
    }

    public BigDecimal getScorec() {
        return scorec;
    }

    public void setScorec(BigDecimal scorec) {
        this.scorec = scorec;
    }

    public LocalDate getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(LocalDate deadDate) {
        this.deadDate = deadDate;
    }

    public String getRoveFlag() {
        return roveFlag;
    }

    public void setRoveFlag(String roveFlag) {
        this.roveFlag = roveFlag;
    }

    public String getRoveGNum() {
        return roveGNum;
    }

    public void setRoveGNum(String roveGNum) {
        this.roveGNum = roveGNum;
    }

    public String getRoveBNum() {
        return roveBNum;
    }

    public void setRoveBNum(String roveBNum) {
        this.roveBNum = roveBNum;
    }

    public String getRoveChk1() {
        return roveChk1;
    }

    public void setRoveChk1(String roveChk1) {
        this.roveChk1 = roveChk1;
    }

    public String getRoveChk2() {
        return roveChk2;
    }

    public void setRoveChk2(String roveChk2) {
        this.roveChk2 = roveChk2;
    }

    public String getRoveChk3() {
        return roveChk3;
    }

    public void setRoveChk3(String roveChk3) {
        this.roveChk3 = roveChk3;
    }

    public String getRoveChk4() {
        return roveChk4;
    }

    public void setRoveChk4(String roveChk4) {
        this.roveChk4 = roveChk4;
    }

    public BigDecimal getScorea() {
        return scorea;
    }

    public void setScorea(BigDecimal scorea) {
        this.scorea = scorea;
    }

    public String getChkflag() {
        return chkflag;
    }

    public void setChkflag(String chkflag) {
        this.chkflag = chkflag;
    }

    public String getoChker() {
        return oChker;
    }

    public void setoChker(String oChker) {
        this.oChker = oChker;
    }

    public String getRoveChk5() {
        return roveChk5;
    }

    public void setRoveChk5(String roveChk5) {
        this.roveChk5 = roveChk5;
    }

    public String getRoveChk6() {
        return roveChk6;
    }

    public void setRoveChk6(String roveChk6) {
        this.roveChk6 = roveChk6;
    }

    public String getlChker() {
        return lChker;
    }

    public void setlChker(String lChker) {
        this.lChker = lChker;
    }

    public String getUnChker() {
        return unChker;
    }

    public void setUnChker(String unChker) {
        this.unChker = unChker;
    }

    public String getCoChker() {
        return coChker;
    }

    public void setCoChker(String coChker) {
        this.coChker = coChker;
    }

    public String getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(String testFlag) {
        this.testFlag = testFlag;
    }

    public String getRoveChk42() {
        return roveChk42;
    }

    public void setRoveChk42(String roveChk42) {
        this.roveChk42 = roveChk42;
    }

    public String getRoveChk43() {
        return roveChk43;
    }

    public void setRoveChk43(String roveChk43) {
        this.roveChk43 = roveChk43;
    }

    public String getRoveChk32() {
        return roveChk32;
    }

    public void setRoveChk32(String roveChk32) {
        this.roveChk32 = roveChk32;
    }

    public String getRoveChk33() {
        return roveChk33;
    }

    public void setRoveChk33(String roveChk33) {
        this.roveChk33 = roveChk33;
    }

    public String getRoveChk34() {
        return roveChk34;
    }

    public void setRoveChk34(String roveChk34) {
        this.roveChk34 = roveChk34;
    }

    public String getRoveChk35() {
        return roveChk35;
    }

    public void setRoveChk35(String roveChk35) {
        this.roveChk35 = roveChk35;
    }

    public String getRoveChk36() {
        return roveChk36;
    }

    public void setRoveChk36(String roveChk36) {
        this.roveChk36 = roveChk36;
    }

    public String getRoveChk44() {
        return roveChk44;
    }

    public void setRoveChk44(String roveChk44) {
        this.roveChk44 = roveChk44;
    }

    public String getRoveChk45() {
        return roveChk45;
    }

    public void setRoveChk45(String roveChk45) {
        this.roveChk45 = roveChk45;
    }

    public String getChkLead() {
        return chkLead;
    }

    public void setChkLead(String chkLead) {
        this.chkLead = chkLead;
    }

    public String getRoveChk1a() {
        return roveChk1a;
    }

    public void setRoveChk1a(String roveChk1a) {
        this.roveChk1a = roveChk1a;
    }

    public String getRoveChk2a() {
        return roveChk2a;
    }

    public void setRoveChk2a(String roveChk2a) {
        this.roveChk2a = roveChk2a;
    }

    public String getRoveChk3a() {
        return roveChk3a;
    }

    public void setRoveChk3a(String roveChk3a) {
        this.roveChk3a = roveChk3a;
    }

    public String getRoveChk32a() {
        return roveChk32a;
    }

    public void setRoveChk32a(String roveChk32a) {
        this.roveChk32a = roveChk32a;
    }

    public String getRoveChk33a() {
        return roveChk33a;
    }

    public void setRoveChk33a(String roveChk33a) {
        this.roveChk33a = roveChk33a;
    }

    public String getRoveChk34a() {
        return roveChk34a;
    }

    public void setRoveChk34a(String roveChk34a) {
        this.roveChk34a = roveChk34a;
    }

    public String getRoveChk35a() {
        return roveChk35a;
    }

    public void setRoveChk35a(String roveChk35a) {
        this.roveChk35a = roveChk35a;
    }

    public String getRoveChk36a() {
        return roveChk36a;
    }

    public void setRoveChk36a(String roveChk36a) {
        this.roveChk36a = roveChk36a;
    }

    public String getRoveChk4a() {
        return roveChk4a;
    }

    public void setRoveChk4a(String roveChk4a) {
        this.roveChk4a = roveChk4a;
    }

    public String getRoveChk42a() {
        return roveChk42a;
    }

    public void setRoveChk42a(String roveChk42a) {
        this.roveChk42a = roveChk42a;
    }

    public String getRoveChk43a() {
        return roveChk43a;
    }

    public void setRoveChk43a(String roveChk43a) {
        this.roveChk43a = roveChk43a;
    }

    public String getRoveChk44a() {
        return roveChk44a;
    }

    public void setRoveChk44a(String roveChk44a) {
        this.roveChk44a = roveChk44a;
    }

    public String getRoveChk45a() {
        return roveChk45a;
    }

    public void setRoveChk45a(String roveChk45a) {
        this.roveChk45a = roveChk45a;
    }

    public LocalDate getUdt2() {
        return udt2;
    }

    public void setUdt2(LocalDate udt2) {
        this.udt2 = udt2;
    }

    public String getDetel4() {
        return detel4;
    }

    public void setDetel4(String detel4) {
        this.detel4 = detel4;
    }

    public String getRoveChk12() {
        return roveChk12;
    }

    public void setRoveChk12(String roveChk12) {
        this.roveChk12 = roveChk12;
    }

    public String getRoveChk12a() {
        return roveChk12a;
    }

    public void setRoveChk12a(String roveChk12a) {
        this.roveChk12a = roveChk12a;
    }

    public String getRoveChk13() {
        return roveChk13;
    }

    public void setRoveChk13(String roveChk13) {
        this.roveChk13 = roveChk13;
    }

    public String getRoveChk13a() {
        return roveChk13a;
    }

    public void setRoveChk13a(String roveChk13a) {
        this.roveChk13a = roveChk13a;
    }

    public String getRoveChk37() {
        return roveChk37;
    }

    public void setRoveChk37(String roveChk37) {
        this.roveChk37 = roveChk37;
    }

    public String getRoveChk37a() {
        return roveChk37a;
    }

    public void setRoveChk37a(String roveChk37a) {
        this.roveChk37a = roveChk37a;
    }

    public String getTestFlag2() {
        return testFlag2;
    }

    public void setTestFlag2(String testFlag2) {
        this.testFlag2 = testFlag2;
    }

    public String getTestFlag3() {
        return testFlag3;
    }

    public void setTestFlag3(String testFlag3) {
        this.testFlag3 = testFlag3;
    }

    public String getTestFlag4() {
        return testFlag4;
    }

    public void setTestFlag4(String testFlag4) {
        this.testFlag4 = testFlag4;
    }

    public String getTestFlag5() {
        return testFlag5;
    }

    public void setTestFlag5(String testFlag5) {
        this.testFlag5 = testFlag5;
    }

    public String getTestFlag6() {
        return testFlag6;
    }

    public void setTestFlag6(String testFlag6) {
        this.testFlag6 = testFlag6;
    }

    public String getRoveChk14() {
        return roveChk14;
    }

    public void setRoveChk14(String roveChk14) {
        this.roveChk14 = roveChk14;
    }

    public String getRoveChk14a() {
        return roveChk14a;
    }

    public void setRoveChk14a(String roveChk14a) {
        this.roveChk14a = roveChk14a;
    }

    public LocalDate getRoveGDat() {
        return roveGDat;
    }

    public void setRoveGDat(LocalDate roveGDat) {
        this.roveGDat = roveGDat;
    }

    public String getTestMemo() {
        return testMemo;
    }

    public void setTestMemo(String testMemo) {
        this.testMemo = testMemo;
    }

    public String getDetel0() {
        return detel0;
    }

    public void setDetel0(String detel0) {
        this.detel0 = detel0;
    }

    public String getOv7day() {
        return ov7day;
    }

    public void setOv7day(String ov7day) {
        this.ov7day = ov7day;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRoveChk0() {
        return roveChk0;
    }

    public void setRoveChk0(String roveChk0) {
        this.roveChk0 = roveChk0;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getRoveChk5a() {
        return roveChk5a;
    }

    public void setRoveChk5a(String roveChk5a) {
        this.roveChk5a = roveChk5a;
    }

    public String getDetel5() {
        return detel5;
    }

    public void setDetel5(String detel5) {
        this.detel5 = detel5;
    }

    public BigDecimal getFgop1() {
        return fgop1;
    }

    public void setFgop1(BigDecimal fgop1) {
        this.fgop1 = fgop1;
    }

    public BigDecimal getFgop2() {
        return fgop2;
    }

    public void setFgop2(BigDecimal fgop2) {
        this.fgop2 = fgop2;
    }

    public BigDecimal getFgop3() {
        return fgop3;
    }

    public void setFgop3(BigDecimal fgop3) {
        this.fgop3 = fgop3;
    }

    public BigDecimal getFgop4() {
        return fgop4;
    }

    public void setFgop4(BigDecimal fgop4) {
        this.fgop4 = fgop4;
    }

    public BigDecimal getFgop5() {
        return fgop5;
    }

    public void setFgop5(BigDecimal fgop5) {
        this.fgop5 = fgop5;
    }

    public String getTestFlag7() {
        return testFlag7;
    }

    public void setTestFlag7(String testFlag7) {
        this.testFlag7 = testFlag7;
    }

    public String getTestFlag8() {
        return testFlag8;
    }

    public void setTestFlag8(String testFlag8) {
        this.testFlag8 = testFlag8;
    }

    public String getTestFlag9() {
        return testFlag9;
    }

    public void setTestFlag9(String testFlag9) {
        this.testFlag9 = testFlag9;
    }

    public String getTestFlaga() {
        return testFlaga;
    }

    public void setTestFlaga(String testFlaga) {
        this.testFlaga = testFlaga;
    }

    public String getTestFlagb() {
        return testFlagb;
    }

    public void setTestFlagb(String testFlagb) {
        this.testFlagb = testFlagb;
    }

    public String getTestFlagc() {
        return testFlagc;
    }

    public void setTestFlagc(String testFlagc) {
        this.testFlagc = testFlagc;
    }

    public String getTestFlagd() {
        return testFlagd;
    }

    public void setTestFlagd(String testFlagd) {
        this.testFlagd = testFlagd;
    }

    public String getTestFlage() {
        return testFlage;
    }

    public void setTestFlage(String testFlage) {
        this.testFlage = testFlage;
    }

    public String getTestFlagf() {
        return testFlagf;
    }

    public void setTestFlagf(String testFlagf) {
        this.testFlagf = testFlagf;
    }

    public String getTestFlagg() {
        return testFlagg;
    }

    public void setTestFlagg(String testFlagg) {
        this.testFlagg = testFlagg;
    }

    public String getIsckp() {
        return isckp;
    }

    public void setIsckp(String isckp) {
        this.isckp = isckp;
    }

    public String getIsckp2() {
        return isckp2;
    }

    public void setIsckp2(String isckp2) {
        this.isckp2 = isckp2;
    }

    public String getIsckp3() {
        return isckp3;
    }

    public void setIsckp3(String isckp3) {
        this.isckp3 = isckp3;
    }

    public String getRoveChk7() {
        return roveChk7;
    }

    public void setRoveChk7(String roveChk7) {
        this.roveChk7 = roveChk7;
    }

    public String getRoveChk7a() {
        return roveChk7a;
    }

    public void setRoveChk7a(String roveChk7a) {
        this.roveChk7a = roveChk7a;
    }

    public String getTestCo() {
        return testCo;
    }

    public void setTestCo(String testCo) {
        this.testCo = testCo;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestNum() {
        return testNum;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getChkunitOld() {
        return chkunitOld;
    }

    public void setChkunitOld(String chkunitOld) {
        this.chkunitOld = chkunitOld;
    }

    public String getTestFlagh() {
        return testFlagh;
    }

    public void setTestFlagh(String testFlagh) {
        this.testFlagh = testFlagh;
    }

    public String getTestFlagz() {
        return testFlagz;
    }

    public void setTestFlagz(String testFlagz) {
        this.testFlagz = testFlagz;
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

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckString() {
        return checkString;
    }

    public void setCheckString(String checkString) {
        this.checkString = checkString;
    }

    public String getRoveString() {
        return roveString;
    }

    public void setRoveString(String roveString) {
        this.roveString = roveString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidGovCheckDTO that = (BidGovCheckDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(checkDate, that.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, checkDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidGovCheckDTO{" +
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
