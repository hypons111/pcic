package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPlnbasPrimaryKey;
import gov.pcc.pwc.domain.BidProjectFilePrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPlnbas} entity.
 */
public class BidPlnbasDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String plncode;

    @NotNull
    @Size(max = 20)
    private String plnno;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 2)
    private String typcode;

    @Size(max = 1)
    private String mnthly;

    @Size(max = 10)
    private String man;

    @Size(max = 30)
    private String tel;

    @Size(max = 10)
    private String boss;

    @Size(max = 30)
    private String fax;

    @Size(max = 21)
    private String boscode;

    @Size(max = 21)
    private String sbcod1;

    @Size(max = 21)
    private String sbcod2;

    @Size(max = 21)
    private String sbcod3;

    @Size(max = 5)
    private String bdate;

    @Size(max = 5)
    private String edate;

    private BigDecimal totamt;

    private BigDecimal dexp;

    private BigDecimal lexp;

    private BigDecimal eexp;

    private BigDecimal cexp;

    private BigDecimal jexp;

    private BigDecimal oexp;

    private BigDecimal cnslt;

    @Size(max = 30)
    private String place;

    @Size(max = 4000)
    private String idea;

    @Size(max = 4000)
    private String goal;

    @Size(max = 4000)
    private String prpse;

    @Size(max = 2000)
    private String point;

    @Size(max = 2000)
    private String organ;

    @Size(max = 2000)
    private String budget;

    @Size(max = 2000)
    private String cntrl;

    @Size(max = 2000)
    private String dyrgt;

    @Size(max = 2000)
    private String other;

    @Size(max = 21)
    private String gblcode;

    @Size(max = 20)
    private String gblno;

    @Size(max = 3)
    private String othcod;

    @Size(max = 1)
    private String dsts;

    @Size(max = 10)
    private String centSpon;

    @Size(max = 10)
    private String cepdNo;

    @Size(max = 60)
    private String eMail;

    private BigDecimal confOut;

    private BigDecimal confIn;

    @Size(max = 1)
    private String plnType;

    private BigDecimal prjTotq;

    @Size(max = 1)
    private String plnType2;

    private BigDecimal prjTotq1;

    private BigDecimal prjTotq2;

    private BigDecimal prjTotq3;

    @Size(max = 2)
    private String workType;

    @Size(max = 1)
    private String hm90;

    @Size(max = 4)
    private String pcode;

    @Size(max = 4)
    private String pcode2;

    @Size(max = 4)
    private String pcode3;

    @Size(max = 4)
    private String pcode4;

    @Size(max = 4)
    private String pcode5;

    @Size(max = 4)
    private String pcode6;

    @Size(max = 4)
    private String pcode7;

    @Size(max = 4)
    private String pcode1;

    @Size(max = 1)
    private String hm91;

    @Size(max = 60)
    private String uIp;

    @Size(max = 15)
    private String sixyrpln;

    @Size(max = 1)
    private String plnkind1;

    @Size(max = 1)
    private String plnkind2;

    @Size(max = 1)
    private String plnkind3;

    @Size(max = 1)
    private String plnkind4;

    @Size(max = 1)
    private String erc921;

    @Size(max = 1)
    private String hm92;

    @Size(max = 30)
    private String school;

    @Size(max = 1)
    private String can;

    @Size(max = 1)
    private String p01;

    @Size(max = 1)
    private String p02;

    @Size(max = 1)
    private String p03;

    @Size(max = 1)
    private String p04;

    @Size(max = 1)
    private String p05;

    @Size(max = 1)
    private String p06;

    @Size(max = 1)
    private String p07;

    @Size(max = 1)
    private String p08;

    @Size(max = 1)
    private String p09;

    @Size(max = 1)
    private String p10;

    @Size(max = 1)
    private String p11;

    @Size(max = 1)
    private String p12;

    @Size(max = 1)
    private String p13;

    @Size(max = 1)
    private String p14;

    @Size(max = 1)
    private String p15;

    @Size(max = 1)
    private String p16;

    @Size(max = 1)
    private String p17;

    @Size(max = 1)
    private String p18;

    @Size(max = 1)
    private String p19;

    @Size(max = 1)
    private String p20;

    @Size(max = 1)
    private String p21;

    @Size(max = 1)
    private String p22;

    @Size(max = 1)
    private String p23;

    @Size(max = 1)
    private String p24;

    @Size(max = 1)
    private String p25;

    @Size(max = 1)
    private String sgn;

    private BigDecimal totamt2;

    @Size(max = 1)
    private String hm93;

    private BigDecimal ototamt;

    private LocalDate obdate;

    private LocalDate oedate;

    @Size(max = 1)
    private String plan92;

    @Size(max = 1)
    private String hm94;

    @Size(max = 12)
    private String new10;

    @Size(max = 1)
    private String hm95;

    @Size(max = 1)
    private String hm96;

    @Size(max = 10)
    private String rdecNo;

    @Size(max = 1)
    private String hm97;

    @Size(max = 1)
    private String hm98;

    @Size(max = 1)
    private String hm99;

    @Size(max = 1)
    private String hm100;

    @Size(max = 1)
    private String kd97;

    @Size(max = 1)
    private String plnkind5;

    @Size(max = 1)
    private String plnkind6;

    @Size(max = 1)
    private String dOpen;

    @Size(max = 2)
    private String typcode2;

    @Size(max = 200)
    private String urls;

    @Size(max = 1)
    private String plnkind598;

    @Size(max = 1)
    private String hm101;

    @Size(max = 1)
    private String temp;

    @Size(max = 1)
    private String hm102;

    @Size(max = 1)
    private String hm103;

    @Size(max = 1)
    private String hm104;

    @Size(max = 1)
    private String hm105;

    @Size(max = 1)
    private String hm106;

    @Size(max = 1)
    private String plnkind7;

    @Size(max = 1)
    private String plnkind8;

    @Size(max = 1)
    private String plnkind9;

    @Size(max = 1)
    private String plnkinda;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidPlnbasPrimaryKey getId() {
        return new BidPlnbasPrimaryKey(this.plncode, this.plnno);
    }

    public void setId(BidPlnbasPrimaryKey id) {
        this.plncode = id.getPlncode();
        this.plnno = id.getPlnno();
    }

    public String getPlncode() {
        return plncode;
    }

    public void setPlncode(String plncode) {
        this.plncode = plncode;
    }

    public String getPlnno() {
        return plnno;
    }

    public void setPlnno(String plnno) {
        this.plnno = plnno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypcode() {
        return typcode;
    }

    public void setTypcode(String typcode) {
        this.typcode = typcode;
    }

    public String getMnthly() {
        return mnthly;
    }

    public void setMnthly(String mnthly) {
        this.mnthly = mnthly;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBoscode() {
        return boscode;
    }

    public void setBoscode(String boscode) {
        this.boscode = boscode;
    }

    public String getSbcod1() {
        return sbcod1;
    }

    public void setSbcod1(String sbcod1) {
        this.sbcod1 = sbcod1;
    }

    public String getSbcod2() {
        return sbcod2;
    }

    public void setSbcod2(String sbcod2) {
        this.sbcod2 = sbcod2;
    }

    public String getSbcod3() {
        return sbcod3;
    }

    public void setSbcod3(String sbcod3) {
        this.sbcod3 = sbcod3;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public BigDecimal getTotamt() {
        return totamt;
    }

    public void setTotamt(BigDecimal totamt) {
        this.totamt = totamt;
    }

    public BigDecimal getDexp() {
        return dexp;
    }

    public void setDexp(BigDecimal dexp) {
        this.dexp = dexp;
    }

    public BigDecimal getLexp() {
        return lexp;
    }

    public void setLexp(BigDecimal lexp) {
        this.lexp = lexp;
    }

    public BigDecimal getEexp() {
        return eexp;
    }

    public void setEexp(BigDecimal eexp) {
        this.eexp = eexp;
    }

    public BigDecimal getCexp() {
        return cexp;
    }

    public void setCexp(BigDecimal cexp) {
        this.cexp = cexp;
    }

    public BigDecimal getJexp() {
        return jexp;
    }

    public void setJexp(BigDecimal jexp) {
        this.jexp = jexp;
    }

    public BigDecimal getOexp() {
        return oexp;
    }

    public void setOexp(BigDecimal oexp) {
        this.oexp = oexp;
    }

    public BigDecimal getCnslt() {
        return cnslt;
    }

    public void setCnslt(BigDecimal cnslt) {
        this.cnslt = cnslt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPrpse() {
        return prpse;
    }

    public void setPrpse(String prpse) {
        this.prpse = prpse;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCntrl() {
        return cntrl;
    }

    public void setCntrl(String cntrl) {
        this.cntrl = cntrl;
    }

    public String getDyrgt() {
        return dyrgt;
    }

    public void setDyrgt(String dyrgt) {
        this.dyrgt = dyrgt;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getGblcode() {
        return gblcode;
    }

    public void setGblcode(String gblcode) {
        this.gblcode = gblcode;
    }

    public String getGblno() {
        return gblno;
    }

    public void setGblno(String gblno) {
        this.gblno = gblno;
    }

    public String getOthcod() {
        return othcod;
    }

    public void setOthcod(String othcod) {
        this.othcod = othcod;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getCentSpon() {
        return centSpon;
    }

    public void setCentSpon(String centSpon) {
        this.centSpon = centSpon;
    }

    public String getCepdNo() {
        return cepdNo;
    }

    public void setCepdNo(String cepdNo) {
        this.cepdNo = cepdNo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public BigDecimal getConfIn() {
        return confIn;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getPlnType() {
        return plnType;
    }

    public void setPlnType(String plnType) {
        this.plnType = plnType;
    }

    public BigDecimal getPrjTotq() {
        return prjTotq;
    }

    public void setPrjTotq(BigDecimal prjTotq) {
        this.prjTotq = prjTotq;
    }

    public String getPlnType2() {
        return plnType2;
    }

    public void setPlnType2(String plnType2) {
        this.plnType2 = plnType2;
    }

    public BigDecimal getPrjTotq1() {
        return prjTotq1;
    }

    public void setPrjTotq1(BigDecimal prjTotq1) {
        this.prjTotq1 = prjTotq1;
    }

    public BigDecimal getPrjTotq2() {
        return prjTotq2;
    }

    public void setPrjTotq2(BigDecimal prjTotq2) {
        this.prjTotq2 = prjTotq2;
    }

    public BigDecimal getPrjTotq3() {
        return prjTotq3;
    }

    public void setPrjTotq3(BigDecimal prjTotq3) {
        this.prjTotq3 = prjTotq3;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getHm90() {
        return hm90;
    }

    public void setHm90(String hm90) {
        this.hm90 = hm90;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPcode2() {
        return pcode2;
    }

    public void setPcode2(String pcode2) {
        this.pcode2 = pcode2;
    }

    public String getPcode3() {
        return pcode3;
    }

    public void setPcode3(String pcode3) {
        this.pcode3 = pcode3;
    }

    public String getPcode4() {
        return pcode4;
    }

    public void setPcode4(String pcode4) {
        this.pcode4 = pcode4;
    }

    public String getPcode5() {
        return pcode5;
    }

    public void setPcode5(String pcode5) {
        this.pcode5 = pcode5;
    }

    public String getPcode6() {
        return pcode6;
    }

    public void setPcode6(String pcode6) {
        this.pcode6 = pcode6;
    }

    public String getPcode7() {
        return pcode7;
    }

    public void setPcode7(String pcode7) {
        this.pcode7 = pcode7;
    }

    public String getPcode1() {
        return pcode1;
    }

    public void setPcode1(String pcode1) {
        this.pcode1 = pcode1;
    }

    public String getHm91() {
        return hm91;
    }

    public void setHm91(String hm91) {
        this.hm91 = hm91;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getSixyrpln() {
        return sixyrpln;
    }

    public void setSixyrpln(String sixyrpln) {
        this.sixyrpln = sixyrpln;
    }

    public String getPlnkind1() {
        return plnkind1;
    }

    public void setPlnkind1(String plnkind1) {
        this.plnkind1 = plnkind1;
    }

    public String getPlnkind2() {
        return plnkind2;
    }

    public void setPlnkind2(String plnkind2) {
        this.plnkind2 = plnkind2;
    }

    public String getPlnkind3() {
        return plnkind3;
    }

    public void setPlnkind3(String plnkind3) {
        this.plnkind3 = plnkind3;
    }

    public String getPlnkind4() {
        return plnkind4;
    }

    public void setPlnkind4(String plnkind4) {
        this.plnkind4 = plnkind4;
    }

    public String getErc921() {
        return erc921;
    }

    public void setErc921(String erc921) {
        this.erc921 = erc921;
    }

    public String getHm92() {
        return hm92;
    }

    public void setHm92(String hm92) {
        this.hm92 = hm92;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getp01() {
        return p01;
    }

    public void setp01(String p01) {
        this.p01 = p01;
    }

    public String getp02() {
        return p02;
    }

    public void setp02(String p02) {
        this.p02 = p02;
    }

    public String getp03() {
        return p03;
    }

    public void setp03(String p03) {
        this.p03 = p03;
    }

    public String getp04() {
        return p04;
    }

    public void setp04(String p04) {
        this.p04 = p04;
    }

    public String getp05() {
        return p05;
    }

    public void setp05(String p05) {
        this.p05 = p05;
    }

    public String getp06() {
        return p06;
    }

    public void setp06(String p06) {
        this.p06 = p06;
    }

    public String getp07() {
        return p07;
    }

    public void setp07(String p07) {
        this.p07 = p07;
    }

    public String getp08() {
        return p08;
    }

    public void setp08(String p08) {
        this.p08 = p08;
    }

    public String getp09() {
        return p09;
    }

    public void setp09(String p09) {
        this.p09 = p09;
    }

    public String getp10() {
        return p10;
    }

    public void setp10(String p10) {
        this.p10 = p10;
    }

    public String getp11() {
        return p11;
    }

    public void setp11(String p11) {
        this.p11 = p11;
    }

    public String getp12() {
        return p12;
    }

    public void setp12(String p12) {
        this.p12 = p12;
    }

    public String getp13() {
        return p13;
    }

    public void setp13(String p13) {
        this.p13 = p13;
    }

    public String getp14() {
        return p14;
    }

    public void setp14(String p14) {
        this.p14 = p14;
    }

    public String getp15() {
        return p15;
    }

    public void setp15(String p15) {
        this.p15 = p15;
    }

    public String getp16() {
        return p16;
    }

    public void setp16(String p16) {
        this.p16 = p16;
    }

    public String getp17() {
        return p17;
    }

    public void setp17(String p17) {
        this.p17 = p17;
    }

    public String getp18() {
        return p18;
    }

    public void setp18(String p18) {
        this.p18 = p18;
    }

    public String getp19() {
        return p19;
    }

    public void setp19(String p19) {
        this.p19 = p19;
    }

    public String getp20() {
        return p20;
    }

    public void setp20(String p20) {
        this.p20 = p20;
    }

    public String getp21() {
        return p21;
    }

    public void setp21(String p21) {
        this.p21 = p21;
    }

    public String getp22() {
        return p22;
    }

    public void setp22(String p22) {
        this.p22 = p22;
    }

    public String getp23() {
        return p23;
    }

    public void setp23(String p23) {
        this.p23 = p23;
    }

    public String getp24() {
        return p24;
    }

    public void setp24(String p24) {
        this.p24 = p24;
    }

    public String getp25() {
        return p25;
    }

    public void setp25(String p25) {
        this.p25 = p25;
    }

    public String getSgn() {
        return sgn;
    }

    public void setSgn(String sgn) {
        this.sgn = sgn;
    }

    public BigDecimal getTotamt2() {
        return totamt2;
    }

    public void setTotamt2(BigDecimal totamt2) {
        this.totamt2 = totamt2;
    }

    public String getHm93() {
        return hm93;
    }

    public void setHm93(String hm93) {
        this.hm93 = hm93;
    }

    public BigDecimal getOtotamt() {
        return ototamt;
    }

    public void setOtotamt(BigDecimal ototamt) {
        this.ototamt = ototamt;
    }

    public LocalDate getObdate() {
        return obdate;
    }

    public void setObdate(LocalDate obdate) {
        this.obdate = obdate;
    }

    public LocalDate getOedate() {
        return oedate;
    }

    public void setOedate(LocalDate oedate) {
        this.oedate = oedate;
    }

    public String getPlan92() {
        return plan92;
    }

    public void setPlan92(String plan92) {
        this.plan92 = plan92;
    }

    public String getHm94() {
        return hm94;
    }

    public void setHm94(String hm94) {
        this.hm94 = hm94;
    }

    public String getNew10() {
        return new10;
    }

    public void setNew10(String new10) {
        this.new10 = new10;
    }

    public String getHm95() {
        return hm95;
    }

    public void setHm95(String hm95) {
        this.hm95 = hm95;
    }

    public String getHm96() {
        return hm96;
    }

    public void setHm96(String hm96) {
        this.hm96 = hm96;
    }

    public String getRdecNo() {
        return rdecNo;
    }

    public void setRdecNo(String rdecNo) {
        this.rdecNo = rdecNo;
    }

    public String getHm97() {
        return hm97;
    }

    public void setHm97(String hm97) {
        this.hm97 = hm97;
    }

    public String getHm98() {
        return hm98;
    }

    public void setHm98(String hm98) {
        this.hm98 = hm98;
    }

    public String getHm99() {
        return hm99;
    }

    public void setHm99(String hm99) {
        this.hm99 = hm99;
    }

    public String getHm100() {
        return hm100;
    }

    public void setHm100(String hm100) {
        this.hm100 = hm100;
    }

    public String getKd97() {
        return kd97;
    }

    public void setKd97(String kd97) {
        this.kd97 = kd97;
    }

    public String getPlnkind5() {
        return plnkind5;
    }

    public void setPlnkind5(String plnkind5) {
        this.plnkind5 = plnkind5;
    }

    public String getPlnkind6() {
        return plnkind6;
    }

    public void setPlnkind6(String plnkind6) {
        this.plnkind6 = plnkind6;
    }

    public String getdOpen() {
        return dOpen;
    }

    public void setdOpen(String dOpen) {
        this.dOpen = dOpen;
    }

    public String getTypcode2() {
        return typcode2;
    }

    public void setTypcode2(String typcode2) {
        this.typcode2 = typcode2;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getPlnkind598() {
        return plnkind598;
    }

    public void setPlnkind598(String plnkind598) {
        this.plnkind598 = plnkind598;
    }

    public String getHm101() {
        return hm101;
    }

    public void setHm101(String hm101) {
        this.hm101 = hm101;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHm102() {
        return hm102;
    }

    public void setHm102(String hm102) {
        this.hm102 = hm102;
    }

    public String getHm103() {
        return hm103;
    }

    public void setHm103(String hm103) {
        this.hm103 = hm103;
    }

    public String getHm104() {
        return hm104;
    }

    public void setHm104(String hm104) {
        this.hm104 = hm104;
    }

    public String getHm105() {
        return hm105;
    }

    public void setHm105(String hm105) {
        this.hm105 = hm105;
    }

    public String getHm106() {
        return hm106;
    }

    public void setHm106(String hm106) {
        this.hm106 = hm106;
    }

    public String getPlnkind7() {
        return plnkind7;
    }

    public void setPlnkind7(String plnkind7) {
        this.plnkind7 = plnkind7;
    }

    public String getPlnkind8() {
        return plnkind8;
    }

    public void setPlnkind8(String plnkind8) {
        this.plnkind8 = plnkind8;
    }

    public String getPlnkind9() {
        return plnkind9;
    }

    public void setPlnkind9(String plnkind9) {
        this.plnkind9 = plnkind9;
    }

    public String getPlnkinda() {
        return plnkinda;
    }

    public void setPlnkinda(String plnkinda) {
        this.plnkinda = plnkinda;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPlnbasDTO)) return false;
        BidPlnbasDTO that = (BidPlnbasDTO) o;
        return plncode.equals(that.plncode) && plnno.equals(that.plnno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plncode, plnno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPlnbasDTO{" +
            "id=" + getId() +
            ", plncode='" + getPlncode() + "'" +
            ", plnno='" + getPlnno() + "'" +
            ", name='" + getName() + "'" +
            ", typcode='" + getTypcode() + "'" +
            ", mnthly='" + getMnthly() + "'" +
            ", man='" + getMan() + "'" +
            ", tel='" + getTel() + "'" +
            ", boss='" + getBoss() + "'" +
            ", fax='" + getFax() + "'" +
            ", boscode='" + getBoscode() + "'" +
            ", sbcod1='" + getSbcod1() + "'" +
            ", sbcod2='" + getSbcod2() + "'" +
            ", sbcod3='" + getSbcod3() + "'" +
            ", bdate='" + getBdate() + "'" +
            ", edate='" + getEdate() + "'" +
            ", totamt=" + getTotamt() +
            ", dexp=" + getDexp() +
            ", lexp=" + getLexp() +
            ", eexp=" + getEexp() +
            ", cexp=" + getCexp() +
            ", jexp=" + getJexp() +
            ", oexp=" + getOexp() +
            ", cnslt=" + getCnslt() +
            ", place='" + getPlace() + "'" +
            ", idea='" + getIdea() + "'" +
            ", goal='" + getGoal() + "'" +
            ", prpse='" + getPrpse() + "'" +
            ", point='" + getPoint() + "'" +
            ", organ='" + getOrgan() + "'" +
            ", budget='" + getBudget() + "'" +
            ", cntrl='" + getCntrl() + "'" +
            ", dyrgt='" + getDyrgt() + "'" +
            ", other='" + getOther() + "'" +
            ", gblcode='" + getGblcode() + "'" +
            ", gblno='" + getGblno() + "'" +
            ", othcod='" + getOthcod() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", centSpon='" + getCentSpon() + "'" +
            ", cepdNo='" + getCepdNo() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", confOut=" + getConfOut() +
            ", confIn=" + getConfIn() +
            ", plnType='" + getPlnType() + "'" +
            ", prjTotq=" + getPrjTotq() +
            ", plnType2='" + getPlnType2() + "'" +
            ", prjTotq1=" + getPrjTotq1() +
            ", prjTotq2=" + getPrjTotq2() +
            ", prjTotq3=" + getPrjTotq3() +
            ", workType='" + getWorkType() + "'" +
            ", hm90='" + getHm90() + "'" +
            ", pcode='" + getPcode() + "'" +
            ", pcode2='" + getPcode2() + "'" +
            ", pcode3='" + getPcode3() + "'" +
            ", pcode4='" + getPcode4() + "'" +
            ", pcode5='" + getPcode5() + "'" +
            ", pcode6='" + getPcode6() + "'" +
            ", pcode7='" + getPcode7() + "'" +
            ", pcode1='" + getPcode1() + "'" +
            ", hm91='" + getHm91() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", sixyrpln='" + getSixyrpln() + "'" +
            ", plnkind1='" + getPlnkind1() + "'" +
            ", plnkind2='" + getPlnkind2() + "'" +
            ", plnkind3='" + getPlnkind3() + "'" +
            ", plnkind4='" + getPlnkind4() + "'" +
            ", erc921='" + getErc921() + "'" +
            ", hm92='" + getHm92() + "'" +
            ", school='" + getSchool() + "'" +
            ", can='" + getCan() + "'" +
            ", p01='" + getp01() + "'" +
            ", p02='" + getp02() + "'" +
            ", p03='" + getp03() + "'" +
            ", p04='" + getp04() + "'" +
            ", p05='" + getp05() + "'" +
            ", p06='" + getp06() + "'" +
            ", p07='" + getp07() + "'" +
            ", p08='" + getp08() + "'" +
            ", p09='" + getp09() + "'" +
            ", p10='" + getp10() + "'" +
            ", p11='" + getp11() + "'" +
            ", p12='" + getp12() + "'" +
            ", p13='" + getp13() + "'" +
            ", p14='" + getp14() + "'" +
            ", p15='" + getp15() + "'" +
            ", p16='" + getp16() + "'" +
            ", p17='" + getp17() + "'" +
            ", p18='" + getp18() + "'" +
            ", p19='" + getp19() + "'" +
            ", p20='" + getp20() + "'" +
            ", p21='" + getp21() + "'" +
            ", p22='" + getp22() + "'" +
            ", p23='" + getp23() + "'" +
            ", p24='" + getp24() + "'" +
            ", p25='" + getp25() + "'" +
            ", sgn='" + getSgn() + "'" +
            ", totamt2=" + getTotamt2() +
            ", hm93='" + getHm93() + "'" +
            ", ototamt=" + getOtotamt() +
            ", obdate='" + getObdate() + "'" +
            ", oedate='" + getOedate() + "'" +
            ", plan92='" + getPlan92() + "'" +
            ", hm94='" + getHm94() + "'" +
            ", new10='" + getNew10() + "'" +
            ", hm95='" + getHm95() + "'" +
            ", hm96='" + getHm96() + "'" +
            ", rdecNo='" + getRdecNo() + "'" +
            ", hm97='" + getHm97() + "'" +
            ", hm98='" + getHm98() + "'" +
            ", hm99='" + getHm99() + "'" +
            ", hm100='" + getHm100() + "'" +
            ", kd97='" + getKd97() + "'" +
            ", plnkind5='" + getPlnkind5() + "'" +
            ", plnkind6='" + getPlnkind6() + "'" +
            ", dOpen='" + getdOpen() + "'" +
            ", typcode2='" + getTypcode2() + "'" +
            ", urls='" + getUrls() + "'" +
            ", plnkind598='" + getPlnkind598() + "'" +
            ", hm101='" + getHm101() + "'" +
            ", temp='" + getTemp() + "'" +
            ", hm102='" + getHm102() + "'" +
            ", hm103='" + getHm103() + "'" +
            ", hm104='" + getHm104() + "'" +
            ", hm105='" + getHm105() + "'" +
            ", hm106='" + getHm106() + "'" +
            ", plnkind7='" + getPlnkind7() + "'" +
            ", plnkind8='" + getPlnkind8() + "'" +
            ", plnkind9='" + getPlnkind9() + "'" +
            ", plnkinda='" + getPlnkinda() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
