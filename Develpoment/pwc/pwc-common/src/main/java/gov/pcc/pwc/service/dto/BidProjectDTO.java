package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProject} entity.
 */
public class BidProjectDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 180)
    private String name;

    @Size(max = 2)
    private String isAdd92;

    @Size(max = 1)
    private String pntCtrl;

    @Size(max = 2)
    private String typeCode;

    @Size(max = 1)
    private String isUnion;

    @Size(max = 4)
    private String placeCode;

    @Size(max = 100)
    private String place;

    @Size(max = 21)
    private String bosCode;

    @Size(max = 21)
    private String depCode;

    @Size(max = 1)
    private String isBg;

    @Size(max = 21)
    private String plnCode;

    @Size(max = 20)
    private String plnNo;

    @Size(max = 30)
    private String plnUnicode;

    @Size(max = 10)
    private String bos2Code;

    private BigDecimal bos2Rate;

    @Size(max = 21)
    private String bos2bCode;

    private BigDecimal bos2bRate;

    @Size(max = 21)
    private String bos2cCode;

    private BigDecimal bos2cRate;

    @Size(max = 21)
    private String bos2dCode;

    private BigDecimal bos2dRate;

    @Size(max = 40)
    private String lnkCode;

    @Size(max = 180)
    private String ccnt2;

    @Size(max = 80)
    private String man;

    @Size(max = 30)
    private String tel;

    @Size(max = 100)
    private String eMail;

    private BigDecimal wrate;

    @Size(max = 2000)
    private String contain;

    @Size(max = 1000)
    private String pfunc;

    @Size(max = 1000)
    private String benf;

    @Size(max = 1000)
    private String pstand;

    @Size(max = 1000)
    private String pqty;

    @Size(max = 2)
    private String untnd;

    @Size(max = 1)
    private String isSpcw;

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

    private BigDecimal spcw01Amt;

    private Integer spcw01Nm;

    private BigDecimal spcw02Amt;

    private Integer spcw02Nm;

    private BigDecimal spcw03Amt;

    private Integer spcw03Nm;

    private BigDecimal spcw04Amt;

    private Integer spcw04Nm;

    private BigDecimal spcw05Amt;

    private Integer spcw05Nm;

    private BigDecimal spcw06Amt;

    private Integer spcw06Nm;

    private BigDecimal spcw07Amt;

    private Integer spcw07Nm;

    private BigDecimal spcw08Amt;

    private Integer spcw08Nm;

    private BigDecimal spcw09Amt;

    private Integer spcw09Nm;

    private BigDecimal spcw10Amt;

    private Integer spcw10Nm;

    private BigDecimal spcw11Amt;

    private Integer spcw11Nm;

    private BigDecimal spcw12Amt;

    private Integer spcw12Nm;

    private Integer job01Type;

    private Integer job02Type;

    private Integer job03Type;

    private Integer job04Type;

    private Integer job05Type;

    private Integer job06Type;

    private Integer job07Type;

    private Integer job08Type;

    private Integer job09Type;

    private Integer job10Type;

    private Integer job11Type;

    private Integer job12Type;

    private Integer job13Type;

    private Integer job14Type;

    @Size(max = 1)
    private String semset;

    @Size(max = 1)
    private String isRcb;

    private BigDecimal sorc1;

    private BigDecimal sorc1b;

    private BigDecimal sorc1c;

    private BigDecimal sorc6;

    private BigDecimal sorc3e;

    private BigDecimal sorc1d;

    private BigDecimal sorc2;

    private BigDecimal sorc3;

    private BigDecimal sorc3a;

    private BigDecimal sorc4;

    private BigDecimal sorc5;

    private BigDecimal sorc3b;

    private BigDecimal sorc3c;

    private BigDecimal sorc3d;

    private BigDecimal sorc3b2;

    private BigDecimal sorc3b3;

    private BigDecimal sorc3b4;

    private BigDecimal sorc3b5;

    private BigDecimal sorc3c1;

    private BigDecimal sorc3d1;

    private BigDecimal sorc3d3;

    private BigDecimal sorc3d4;

    private BigDecimal sorc7;

    private BigDecimal sorc7Use;

    private BigDecimal sorc8;

    private BigDecimal sorc9;

    private BigDecimal sorc7Use2;

    private BigDecimal sorc7Use3;

    @Size(max = 1)
    private String openYn;

    @Size(max = 1)
    private String isOpen;

    @Size(max = 1)
    private String dOpen;

    @Size(max = 1)
    private String wKind;

    private Integer wDays;

    @Size(max = 200)
    private String wRemk;

    @Size(max = 26)
    private String bCode;

    private BigDecimal totAmt;

    private BigDecimal bdgt1;

    private BigDecimal igtct;

    private BigDecimal othct;

    private BigDecimal rsvct;

    private BigDecimal poll;

    @Size(max = 50)
    private String pollNo;

    private BigDecimal othMon;

    @Size(max = 2000)
    private String gyResn;

    @Size(max = 1)
    private String outSource;

    @Size(max = 21)
    private String drut;

    private BigDecimal drct;

    @Size(max = 300)
    private String dsrm;

    @Size(max = 300)
    private String scrm;

    @Size(max = 1)
    private String kickOff;

    @Size(max = 1)
    private String scrr;

    private Integer scnum;

    @Size(max = 21)
    private String pcmut;

    private Integer ccnum;

    private LocalDate issStartDate;

    private LocalDate issEndDate;

    private BigDecimal issCost;

    @Size(max = 100)
    private String issNo;

    @Size(max = 2000)
    private String issRmk;

    private LocalDate pdsDate;

    private LocalDate dsDate;

    private LocalDate adsDate;

    private LocalDate getlDate;

    private LocalDate agetlDate;

    @Size(max = 1)
    private String isSpay;

    @Size(max = 2)
    private String ipe;

    @Size(max = 10)
    private String ipeY;

    @Size(max = 10)
    private String ipeN;

    @Size(max = 1)
    private String isNlic;

    @Size(max = 1)
    private String isDlic;

    @Size(max = 40)
    private String licNo;

    private LocalDate licDate;

    private LocalDate alicDate;

    private LocalDate opDate;

    private LocalDate aopDate;

    private LocalDate pckDate;

    private LocalDate ackDate;

    private LocalDate pscDate;

    private LocalDate ascDate;

    private LocalDate pctDate;

    private LocalDate actDate;

    @Size(max = 1)
    private String ctmeth;

    @Size(max = 2)
    private String actmth;

    @Size(max = 1)
    private String specPurch;

    @Size(max = 1)
    private String isIntl;

    @Size(max = 1)
    private String tctmth;

    private BigDecimal bdgt2;

    private BigDecimal bdgt3;

    @Size(max = 2)
    private String mctmth;

    @Size(max = 2)
    private String ccnt3;

    @Size(max = 2)
    private String isPer;

    private BigDecimal ctSum;

    private BigDecimal ctSumF;

    private BigDecimal ctSumU;

    @Size(max = 2000)
    private String ctmo;

    private BigDecimal ctSumD;

    @Size(max = 80)
    private String cntid;

    private BigDecimal pays;

    private BigDecimal pmsSumT;

    @Size(max = 2)
    private String pmsType;

    @Size(max = 1)
    private String paymth;

    @Size(max = 1)
    private String foreignYn;

    private LocalDate csDate;

    private LocalDate asDate;

    private LocalDate asuDate;

    private LocalDate ceDate;

    private LocalDate ceDateF;

    private LocalDate aeDate;

    private BigDecimal fcost2;

    private LocalDate fcsDate;

    private LocalDate afcsDate;

    private BigDecimal fcost;

    @Size(max = 1)
    private String isCkp;

    @Size(max = 1)
    private String isCkp2;

    @Size(max = 1)
    private String isCkp3;

    private BigDecimal ckpMoney;

    @Size(max = 100)
    private String ckpUnit;

    private LocalDate ckpDate;

    private BigDecimal ckpCnt;

    @Size(max = 100)
    private String ckpNum;

    @Size(max = 100)
    private String ckaUnit;

    private LocalDate ckaDate;

    private BigDecimal ckaCnt;

    @Size(max = 100)
    private String ckaNum;

    @Size(max = 100)
    private String ckdUnit;

    private LocalDate ckdDate;

    private BigDecimal ckdCnt;

    @Size(max = 100)
    private String ckdNum;

    @Size(max = 1000)
    private String workMemo;

    @Size(max = 20)
    private String hotLine;

    @Size(max = 20)
    private String lnkCode2;

    @Size(max = 40)
    private String lnkCode3;

    @Size(max = 40)
    private String lnkNo2;

    @Size(max = 180)
    private String ccnt1;

    @Size(max = 4)
    private String typeCode2;

    @Size(max = 60)
    private String deptName;

    @Size(max = 5)
    private String aopCnt;

    @Size(max = 20)
    private String uniqueKey;

    @Size(max = 1)
    private String isBidstr;

    @Size(max = 1)
    private String isEngineer;

    @Size(max = 180)
    private String name0;

    private BigDecimal ctSum0;

    private BigDecimal bdgt10;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 7)
    private String batch;

    private Integer nocntDay;

    @Size(max = 1)
    private String status;

    private LocalDate aokDate;

    private BigDecimal fcost3;

    private Integer finWays;

    private Integer aworkDay;

    private Integer fastDay;

    private Integer overDay;

    private Integer overDayY;

    private BigDecimal overFee;

    private BigDecimal otherFee;

    private BigDecimal punshFee;

    @Size(max = 20)
    private String tm2x;

    @Size(max = 20)
    private String tm2y;

    @Size(max = 20)
    private String tm2x2;

    @Size(max = 20)
    private String tm2y2;

    @Size(max = 20)
    private String tm2x3;

    @Size(max = 20)
    private String tm2y3;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String dsts;

    private BigDecimal confOut;

    @Size(max = 60)
    private String uIp;

    @Size(max = 1)
    private String can;

    @Size(max = 2000)
    private String srut;

    private BigDecimal anoct;

    @Size(max = 7)
    private String cepdNo;

    @Size(max = 2)
    private String subNo;

    @Size(max = 60)
    private String scnt;

    @Size(max = 2000)
    private String dsdm;

    @Size(max = 1000)
    private String preson;

    private LocalDate psignDate;

    private LocalDate asignDate;

    private LocalDate trDate;

    private BigDecimal confIn;

    @Size(max = 50)
    private String uKey;

    @Size(max = 200)
    private String tpcoth;

    private BigDecimal pcmct;

    @Size(max = 21)
    private String wkutOld;

    @Size(max = 40)
    private String pccMemo;

    private BigDecimal bgbSumt;

    private LocalDate bgbDate;

    @Size(max = 4000)
    private String mainItem;

    @Size(max = 2000)
    private String mDesc;

    @Size(max = 1000)
    private String mRmk;

    private BigDecimal pmrSumt;

    private LocalDate pmrDate;

    @Size(max = 2)
    private String pmrResm;

    private List<BidProjectUnitDTO> dsuts;

    private List<BidProjectUnitDTO> scuts;

    private List<BidProjectUnitDTO> ccuts;

    public BidProjectPrimaryKey getId() {
        return new BidProjectPrimaryKey(this.wkut, this.prjno);
    }

    public void setId(BidProjectPrimaryKey id) {

        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAdd92() {
        return isAdd92;
    }

    public void setIsAdd92(String isAdd92) {
        this.isAdd92 = isAdd92;
    }

    public String getPntCtrl() {
        return pntCtrl;
    }

    public void setPntCtrl(String pntCtrl) {
        this.pntCtrl = pntCtrl;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getIsUnion() {
        return isUnion;
    }

    public void setIsUnion(String isUnion) {
        this.isUnion = isUnion;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBosCode() {
        return bosCode;
    }

    public void setBosCode(String bosCode) {
        this.bosCode = bosCode;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getIsBg() {
        return isBg;
    }

    public void setIsBg(String isBg) {
        this.isBg = isBg;
    }

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnNo() {
        return plnNo;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getPlnUnicode() {
        return plnUnicode;
    }

    public void setPlnUnicode(String plnUnicode) {
        this.plnUnicode = plnUnicode;
    }

    public String getBos2Code() {
        return bos2Code;
    }

    public void setBos2Code(String bos2Code) {
        this.bos2Code = bos2Code;
    }

    public BigDecimal getBos2Rate() {
        return bos2Rate;
    }

    public void setBos2Rate(BigDecimal bos2Rate) {
        this.bos2Rate = bos2Rate;
    }

    public String getBos2bCode() {
        return bos2bCode;
    }

    public void setBos2bCode(String bos2bCode) {
        this.bos2bCode = bos2bCode;
    }

    public BigDecimal getBos2bRate() {
        return bos2bRate;
    }

    public void setBos2bRate(BigDecimal bos2bRate) {
        this.bos2bRate = bos2bRate;
    }

    public String getBos2cCode() {
        return bos2cCode;
    }

    public void setBos2cCode(String bos2cCode) {
        this.bos2cCode = bos2cCode;
    }

    public BigDecimal getBos2cRate() {
        return bos2cRate;
    }

    public void setBos2cRate(BigDecimal bos2cRate) {
        this.bos2cRate = bos2cRate;
    }

    public String getBos2dCode() {
        return bos2dCode;
    }

    public void setBos2dCode(String bos2dCode) {
        this.bos2dCode = bos2dCode;
    }

    public BigDecimal getBos2dRate() {
        return bos2dRate;
    }

    public void setBos2dRate(BigDecimal bos2dRate) {
        this.bos2dRate = bos2dRate;
    }

    public String getLnkCode() {
        return lnkCode;
    }

    public void setLnkCode(String lnkCode) {
        this.lnkCode = lnkCode;
    }

    public String getCcnt2() {
        return ccnt2;
    }

    public void setCcnt2(String ccnt2) {
        this.ccnt2 = ccnt2;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getWrate() {
        return wrate;
    }

    public void setWrate(BigDecimal wrate) {
        this.wrate = wrate;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getPfunc() {
        return pfunc;
    }

    public void setPfunc(String pfunc) {
        this.pfunc = pfunc;
    }

    public String getBenf() {
        return benf;
    }

    public void setBenf(String benf) {
        this.benf = benf;
    }

    public String getPstand() {
        return pstand;
    }

    public void setPstand(String pstand) {
        this.pstand = pstand;
    }

    public String getPqty() {
        return pqty;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public String getUntnd() {
        return untnd;
    }

    public void setUntnd(String untnd) {
        this.untnd = untnd;
    }

    public String getIsSpcw() {
        return isSpcw;
    }

    public void setIsSpcw(String isSpcw) {
        this.isSpcw = isSpcw;
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

    public BigDecimal getSpcw01Amt() {
        return spcw01Amt;
    }

    public void setSpcw01Amt(BigDecimal spcw01Amt) {
        this.spcw01Amt = spcw01Amt;
    }

    public Integer getSpcw01Nm() {
        return spcw01Nm;
    }

    public void setSpcw01Nm(Integer spcw01Nm) {
        this.spcw01Nm = spcw01Nm;
    }

    public BigDecimal getSpcw02Amt() {
        return spcw02Amt;
    }

    public void setSpcw02Amt(BigDecimal spcw02Amt) {
        this.spcw02Amt = spcw02Amt;
    }

    public Integer getSpcw02Nm() {
        return spcw02Nm;
    }

    public void setSpcw02Nm(Integer spcw02Nm) {
        this.spcw02Nm = spcw02Nm;
    }

    public BigDecimal getSpcw03Amt() {
        return spcw03Amt;
    }

    public void setSpcw03Amt(BigDecimal spcw03Amt) {
        this.spcw03Amt = spcw03Amt;
    }

    public Integer getSpcw03Nm() {
        return spcw03Nm;
    }

    public void setSpcw03Nm(Integer spcw03Nm) {
        this.spcw03Nm = spcw03Nm;
    }

    public BigDecimal getSpcw04Amt() {
        return spcw04Amt;
    }

    public void setSpcw04Amt(BigDecimal spcw04Amt) {
        this.spcw04Amt = spcw04Amt;
    }

    public Integer getSpcw04Nm() {
        return spcw04Nm;
    }

    public void setSpcw04Nm(Integer spcw04Nm) {
        this.spcw04Nm = spcw04Nm;
    }

    public BigDecimal getSpcw05Amt() {
        return spcw05Amt;
    }

    public void setSpcw05Amt(BigDecimal spcw05Amt) {
        this.spcw05Amt = spcw05Amt;
    }

    public Integer getSpcw05Nm() {
        return spcw05Nm;
    }

    public void setSpcw05Nm(Integer spcw05Nm) {
        this.spcw05Nm = spcw05Nm;
    }

    public BigDecimal getSpcw06Amt() {
        return spcw06Amt;
    }

    public void setSpcw06Amt(BigDecimal spcw06Amt) {
        this.spcw06Amt = spcw06Amt;
    }

    public Integer getSpcw06Nm() {
        return spcw06Nm;
    }

    public void setSpcw06Nm(Integer spcw06Nm) {
        this.spcw06Nm = spcw06Nm;
    }

    public BigDecimal getSpcw07Amt() {
        return spcw07Amt;
    }

    public void setSpcw07Amt(BigDecimal spcw07Amt) {
        this.spcw07Amt = spcw07Amt;
    }

    public Integer getSpcw07Nm() {
        return spcw07Nm;
    }

    public void setSpcw07Nm(Integer spcw07Nm) {
        this.spcw07Nm = spcw07Nm;
    }

    public BigDecimal getSpcw08Amt() {
        return spcw08Amt;
    }

    public void setSpcw08Amt(BigDecimal spcw08Amt) {
        this.spcw08Amt = spcw08Amt;
    }

    public Integer getSpcw08Nm() {
        return spcw08Nm;
    }

    public void setSpcw08Nm(Integer spcw08Nm) {
        this.spcw08Nm = spcw08Nm;
    }

    public BigDecimal getSpcw09Amt() {
        return spcw09Amt;
    }

    public void setSpcw09Amt(BigDecimal spcw09Amt) {
        this.spcw09Amt = spcw09Amt;
    }

    public Integer getSpcw09Nm() {
        return spcw09Nm;
    }

    public void setSpcw09Nm(Integer spcw09Nm) {
        this.spcw09Nm = spcw09Nm;
    }

    public BigDecimal getSpcw10Amt() {
        return spcw10Amt;
    }

    public void setSpcw10Amt(BigDecimal spcw10Amt) {
        this.spcw10Amt = spcw10Amt;
    }

    public Integer getSpcw10Nm() {
        return spcw10Nm;
    }

    public void setSpcw10Nm(Integer spcw10Nm) {
        this.spcw10Nm = spcw10Nm;
    }

    public BigDecimal getSpcw11Amt() {
        return spcw11Amt;
    }

    public void setSpcw11Amt(BigDecimal spcw11Amt) {
        this.spcw11Amt = spcw11Amt;
    }

    public Integer getSpcw11Nm() {
        return spcw11Nm;
    }

    public void setSpcw11Nm(Integer spcw11Nm) {
        this.spcw11Nm = spcw11Nm;
    }

    public BigDecimal getSpcw12Amt() {
        return spcw12Amt;
    }

    public void setSpcw12Amt(BigDecimal spcw12Amt) {
        this.spcw12Amt = spcw12Amt;
    }

    public Integer getSpcw12Nm() {
        return spcw12Nm;
    }

    public void setSpcw12Nm(Integer spcw12Nm) {
        this.spcw12Nm = spcw12Nm;
    }

    public Integer getJob01Type() {
        return job01Type;
    }

    public void setJob01Type(Integer job01Type) {
        this.job01Type = job01Type;
    }

    public Integer getJob02Type() {
        return job02Type;
    }

    public void setJob02Type(Integer job02Type) {
        this.job02Type = job02Type;
    }

    public Integer getJob03Type() {
        return job03Type;
    }

    public void setJob03Type(Integer job03Type) {
        this.job03Type = job03Type;
    }

    public Integer getJob04Type() {
        return job04Type;
    }

    public void setJob04Type(Integer job04Type) {
        this.job04Type = job04Type;
    }

    public Integer getJob05Type() {
        return job05Type;
    }

    public void setJob05Type(Integer job05Type) {
        this.job05Type = job05Type;
    }

    public Integer getJob06Type() {
        return job06Type;
    }

    public void setJob06Type(Integer job06Type) {
        this.job06Type = job06Type;
    }

    public Integer getJob07Type() {
        return job07Type;
    }

    public void setJob07Type(Integer job07Type) {
        this.job07Type = job07Type;
    }

    public Integer getJob08Type() {
        return job08Type;
    }

    public void setJob08Type(Integer job08Type) {
        this.job08Type = job08Type;
    }

    public Integer getJob09Type() {
        return job09Type;
    }

    public void setJob09Type(Integer job09Type) {
        this.job09Type = job09Type;
    }

    public Integer getJob10Type() {
        return job10Type;
    }

    public void setJob10Type(Integer job10Type) {
        this.job10Type = job10Type;
    }

    public Integer getJob11Type() {
        return job11Type;
    }

    public void setJob11Type(Integer job11Type) {
        this.job11Type = job11Type;
    }

    public Integer getJob12Type() {
        return job12Type;
    }

    public void setJob12Type(Integer job12Type) {
        this.job12Type = job12Type;
    }

    public Integer getJob13Type() {
        return job13Type;
    }

    public void setJob13Type(Integer job13Type) {
        this.job13Type = job13Type;
    }

    public Integer getJob14Type() {
        return job14Type;
    }

    public void setJob14Type(Integer job14Type) {
        this.job14Type = job14Type;
    }

    public String getSemset() {
        return semset;
    }

    public void setSemset(String semset) {
        this.semset = semset;
    }

    public String getIsRcb() {
        return isRcb;
    }

    public void setIsRcb(String isRcb) {
        this.isRcb = isRcb;
    }

    public BigDecimal getSorc1() {
        return sorc1;
    }

    public void setSorc1(BigDecimal sorc1) {
        this.sorc1 = sorc1;
    }

    public BigDecimal getSorc1b() {
        return sorc1b;
    }

    public void setSorc1b(BigDecimal sorc1b) {
        this.sorc1b = sorc1b;
    }

    public BigDecimal getSorc1c() {
        return sorc1c;
    }

    public void setSorc1c(BigDecimal sorc1c) {
        this.sorc1c = sorc1c;
    }

    public BigDecimal getSorc6() {
        return sorc6;
    }

    public void setSorc6(BigDecimal sorc6) {
        this.sorc6 = sorc6;
    }

    public BigDecimal getSorc3e() {
        return sorc3e;
    }

    public void setSorc3e(BigDecimal sorc3e) {
        this.sorc3e = sorc3e;
    }

    public BigDecimal getSorc1d() {
        return sorc1d;
    }

    public void setSorc1d(BigDecimal sorc1d) {
        this.sorc1d = sorc1d;
    }

    public BigDecimal getSorc2() {
        return sorc2;
    }

    public void setSorc2(BigDecimal sorc2) {
        this.sorc2 = sorc2;
    }

    public BigDecimal getSorc3() {
        return sorc3;
    }

    public void setSorc3(BigDecimal sorc3) {
        this.sorc3 = sorc3;
    }

    public BigDecimal getSorc3a() {
        return sorc3a;
    }

    public void setSorc3a(BigDecimal sorc3a) {
        this.sorc3a = sorc3a;
    }

    public BigDecimal getSorc4() {
        return sorc4;
    }

    public void setSorc4(BigDecimal sorc4) {
        this.sorc4 = sorc4;
    }

    public BigDecimal getSorc5() {
        return sorc5;
    }

    public void setSorc5(BigDecimal sorc5) {
        this.sorc5 = sorc5;
    }

    public BigDecimal getSorc3b() {
        return sorc3b;
    }

    public void setSorc3b(BigDecimal sorc3b) {
        this.sorc3b = sorc3b;
    }

    public BigDecimal getSorc3c() {
        return sorc3c;
    }

    public void setSorc3c(BigDecimal sorc3c) {
        this.sorc3c = sorc3c;
    }

    public BigDecimal getSorc3d() {
        return sorc3d;
    }

    public void setSorc3d(BigDecimal sorc3d) {
        this.sorc3d = sorc3d;
    }

    public BigDecimal getSorc3b2() {
        return sorc3b2;
    }

    public void setSorc3b2(BigDecimal sorc3b2) {
        this.sorc3b2 = sorc3b2;
    }

    public BigDecimal getSorc3b3() {
        return sorc3b3;
    }

    public void setSorc3b3(BigDecimal sorc3b3) {
        this.sorc3b3 = sorc3b3;
    }

    public BigDecimal getSorc3b4() {
        return sorc3b4;
    }

    public void setSorc3b4(BigDecimal sorc3b4) {
        this.sorc3b4 = sorc3b4;
    }

    public BigDecimal getSorc3b5() {
        return sorc3b5;
    }

    public void setSorc3b5(BigDecimal sorc3b5) {
        this.sorc3b5 = sorc3b5;
    }

    public BigDecimal getSorc3c1() {
        return sorc3c1;
    }

    public void setSorc3c1(BigDecimal sorc3c1) {
        this.sorc3c1 = sorc3c1;
    }

    public BigDecimal getSorc3d1() {
        return sorc3d1;
    }

    public void setSorc3d1(BigDecimal sorc3d1) {
        this.sorc3d1 = sorc3d1;
    }

    public BigDecimal getSorc3d3() {
        return sorc3d3;
    }

    public void setSorc3d3(BigDecimal sorc3d3) {
        this.sorc3d3 = sorc3d3;
    }

    public BigDecimal getSorc3d4() {
        return sorc3d4;
    }

    public void setSorc3d4(BigDecimal sorc3d4) {
        this.sorc3d4 = sorc3d4;
    }

    public BigDecimal getSorc7() {
        return sorc7;
    }

    public void setSorc7(BigDecimal sorc7) {
        this.sorc7 = sorc7;
    }

    public BigDecimal getSorc7Use() {
        return sorc7Use;
    }

    public void setSorc7Use(BigDecimal sorc7Use) {
        this.sorc7Use = sorc7Use;
    }

    public BigDecimal getSorc8() {
        return sorc8;
    }

    public void setSorc8(BigDecimal sorc8) {
        this.sorc8 = sorc8;
    }

    public BigDecimal getSorc9() {
        return sorc9;
    }

    public void setSorc9(BigDecimal sorc9) {
        this.sorc9 = sorc9;
    }

    public BigDecimal getSorc7Use2() {
        return sorc7Use2;
    }

    public void setSorc7Use2(BigDecimal sorc7Use2) {
        this.sorc7Use2 = sorc7Use2;
    }

    public BigDecimal getSorc7Use3() {
        return sorc7Use3;
    }

    public void setSorc7Use3(BigDecimal sorc7Use3) {
        this.sorc7Use3 = sorc7Use3;
    }

    public String getOpenYn() {
        return openYn;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getdOpen() {
        return dOpen;
    }

    public void setdOpen(String dOpen) {
        this.dOpen = dOpen;
    }

    public String getwKind() {
        return wKind;
    }

    public void setwKind(String wKind) {
        this.wKind = wKind;
    }

    public Integer getwDays() {
        return wDays;
    }

    public void setwDays(Integer wDays) {
        this.wDays = wDays;
    }

    public String getwRemk() {
        return wRemk;
    }

    public void setwRemk(String wRemk) {
        this.wRemk = wRemk;
    }

    public String getbCode() {
        return bCode;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    public BigDecimal getBdgt1() {
        return bdgt1;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public BigDecimal getIgtct() {
        return igtct;
    }

    public void setIgtct(BigDecimal igtct) {
        this.igtct = igtct;
    }

    public BigDecimal getOthct() {
        return othct;
    }

    public void setOthct(BigDecimal othct) {
        this.othct = othct;
    }

    public BigDecimal getRsvct() {
        return rsvct;
    }

    public void setRsvct(BigDecimal rsvct) {
        this.rsvct = rsvct;
    }

    public BigDecimal getPoll() {
        return poll;
    }

    public void setPoll(BigDecimal poll) {
        this.poll = poll;
    }

    public String getPollNo() {
        return pollNo;
    }

    public void setPollNo(String pollNo) {
        this.pollNo = pollNo;
    }

    public BigDecimal getOthMon() {
        return othMon;
    }

    public void setOthMon(BigDecimal othMon) {
        this.othMon = othMon;
    }

    public String getGyResn() {
        return gyResn;
    }

    public void setGyResn(String gyResn) {
        this.gyResn = gyResn;
    }

    public String getOutSource() {
        return outSource;
    }

    public void setOutSource(String outSource) {
        this.outSource = outSource;
    }

    public String getDrut() {
        return drut;
    }

    public void setDrut(String drut) {
        this.drut = drut;
    }

    public BigDecimal getDrct() {
        return drct;
    }

    public void setDrct(BigDecimal drct) {
        this.drct = drct;
    }

    public String getDsrm() {
        return dsrm;
    }

    public void setDsrm(String dsrm) {
        this.dsrm = dsrm;
    }

    public String getScrm() {
        return scrm;
    }

    public void setScrm(String scrm) {
        this.scrm = scrm;
    }

    public String getKickOff() {
        return kickOff;
    }

    public void setKickOff(String kickOff) {
        this.kickOff = kickOff;
    }

    public String getScrr() {
        return scrr;
    }

    public void setScrr(String scrr) {
        this.scrr = scrr;
    }

    public Integer getScnum() {
        return scnum;
    }

    public void setScnum(Integer scnum) {
        this.scnum = scnum;
    }

    public String getPcmut() {
        return pcmut;
    }

    public void setPcmut(String pcmut) {
        this.pcmut = pcmut;
    }

    public Integer getCcnum() {
        return ccnum;
    }

    public void setCcnum(Integer ccnum) {
        this.ccnum = ccnum;
    }

    public LocalDate getIssStartDate() {
        return issStartDate;
    }

    public void setIssStartDate(LocalDate issStartDate) {
        this.issStartDate = issStartDate;
    }

    public LocalDate getIssEndDate() {
        return issEndDate;
    }

    public void setIssEndDate(LocalDate issEndDate) {
        this.issEndDate = issEndDate;
    }

    public BigDecimal getIssCost() {
        return issCost;
    }

    public void setIssCost(BigDecimal issCost) {
        this.issCost = issCost;
    }

    public String getIssNo() {
        return issNo;
    }

    public void setIssNo(String issNo) {
        this.issNo = issNo;
    }

    public String getIssRmk() {
        return issRmk;
    }

    public void setIssRmk(String issRmk) {
        this.issRmk = issRmk;
    }

    public LocalDate getPdsDate() {
        return pdsDate;
    }

    public void setPdsDate(LocalDate pdsDate) {
        this.pdsDate = pdsDate;
    }

    public LocalDate getDsDate() {
        return dsDate;
    }

    public void setDsDate(LocalDate dsDate) {
        this.dsDate = dsDate;
    }

    public LocalDate getAdsDate() {
        return adsDate;
    }

    public void setAdsDate(LocalDate adsDate) {
        this.adsDate = adsDate;
    }

    public LocalDate getGetlDate() {
        return getlDate;
    }

    public void setGetlDate(LocalDate getlDate) {
        this.getlDate = getlDate;
    }

    public LocalDate getAgetlDate() {
        return agetlDate;
    }

    public void setAgetlDate(LocalDate agetlDate) {
        this.agetlDate = agetlDate;
    }

    public String getIsSpay() {
        return isSpay;
    }

    public void setIsSpay(String isSpay) {
        this.isSpay = isSpay;
    }

    public String getIpe() {
        return ipe;
    }

    public void setIpe(String ipe) {
        this.ipe = ipe;
    }

    public String getIpeY() {
        return ipeY;
    }

    public void setIpeY(String ipeY) {
        this.ipeY = ipeY;
    }

    public String getIpeN() {
        return ipeN;
    }

    public void setIpeN(String ipeN) {
        this.ipeN = ipeN;
    }

    public String getIsNlic() {
        return isNlic;
    }

    public void setIsNlic(String isNlic) {
        this.isNlic = isNlic;
    }

    public String getIsDlic() {
        return isDlic;
    }

    public void setIsDlic(String isDlic) {
        this.isDlic = isDlic;
    }

    public String getLicNo() {
        return licNo;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    public LocalDate getLicDate() {
        return licDate;
    }

    public void setLicDate(LocalDate licDate) {
        this.licDate = licDate;
    }

    public LocalDate getAlicDate() {
        return alicDate;
    }

    public void setAlicDate(LocalDate alicDate) {
        this.alicDate = alicDate;
    }

    public LocalDate getOpDate() {
        return opDate;
    }

    public void setOpDate(LocalDate opDate) {
        this.opDate = opDate;
    }

    public LocalDate getAopDate() {
        return aopDate;
    }

    public void setAopDate(LocalDate aopDate) {
        this.aopDate = aopDate;
    }

    public LocalDate getPckDate() {
        return pckDate;
    }

    public void setPckDate(LocalDate pckDate) {
        this.pckDate = pckDate;
    }

    public LocalDate getAckDate() {
        return ackDate;
    }

    public void setAckDate(LocalDate ackDate) {
        this.ackDate = ackDate;
    }

    public LocalDate getPscDate() {
        return pscDate;
    }

    public void setPscDate(LocalDate pscDate) {
        this.pscDate = pscDate;
    }

    public LocalDate getAscDate() {
        return ascDate;
    }

    public void setAscDate(LocalDate ascDate) {
        this.ascDate = ascDate;
    }

    public LocalDate getPctDate() {
        return pctDate;
    }

    public void setPctDate(LocalDate pctDate) {
        this.pctDate = pctDate;
    }

    public LocalDate getActDate() {
        return actDate;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

    public String getCtmeth() {
        return ctmeth;
    }

    public void setCtmeth(String ctmeth) {
        this.ctmeth = ctmeth;
    }

    public String getActmth() {
        return actmth;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public String getSpecPurch() {
        return specPurch;
    }

    public void setSpecPurch(String specPurch) {
        this.specPurch = specPurch;
    }

    public String getIsIntl() {
        return isIntl;
    }

    public void setIsIntl(String isIntl) {
        this.isIntl = isIntl;
    }

    public String getTctmth() {
        return tctmth;
    }

    public void setTctmth(String tctmth) {
        this.tctmth = tctmth;
    }

    public BigDecimal getBdgt2() {
        return bdgt2;
    }

    public void setBdgt2(BigDecimal bdgt2) {
        this.bdgt2 = bdgt2;
    }

    public BigDecimal getBdgt3() {
        return bdgt3;
    }

    public void setBdgt3(BigDecimal bdgt3) {
        this.bdgt3 = bdgt3;
    }

    public String getMctmth() {
        return mctmth;
    }

    public void setMctmth(String mctmth) {
        this.mctmth = mctmth;
    }

    public String getCcnt3() {
        return ccnt3;
    }

    public void setCcnt3(String ccnt3) {
        this.ccnt3 = ccnt3;
    }

    public String getIsPer() {
        return isPer;
    }

    public void setIsPer(String isPer) {
        this.isPer = isPer;
    }

    public BigDecimal getCtSum() {
        return ctSum;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public BigDecimal getCtSumF() {
        return ctSumF;
    }

    public void setCtSumF(BigDecimal ctSumF) {
        this.ctSumF = ctSumF;
    }

    public BigDecimal getCtSumU() {
        return ctSumU;
    }

    public void setCtSumU(BigDecimal ctSumU) {
        this.ctSumU = ctSumU;
    }

    public String getCtmo() {
        return ctmo;
    }

    public void setCtmo(String ctmo) {
        this.ctmo = ctmo;
    }

    public BigDecimal getCtSumD() {
        return ctSumD;
    }

    public void setCtSumD(BigDecimal ctSumD) {
        this.ctSumD = ctSumD;
    }

    public String getCntid() {
        return cntid;
    }

    public void setCntid(String cntid) {
        this.cntid = cntid;
    }

    public BigDecimal getPays() {
        return pays;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public BigDecimal getPmsSumT() {
        return pmsSumT;
    }

    public void setPmsSumT(BigDecimal pmsSumT) {
        this.pmsSumT = pmsSumT;
    }

    public String getPmsType() {
        return pmsType;
    }

    public void setPmsType(String pmsType) {
        this.pmsType = pmsType;
    }

    public String getPaymth() {
        return paymth;
    }

    public void setPaymth(String paymth) {
        this.paymth = paymth;
    }

    public String getForeignYn() {
        return foreignYn;
    }

    public void setForeignYn(String foreignYn) {
        this.foreignYn = foreignYn;
    }

    public LocalDate getCsDate() {
        return csDate;
    }

    public void setCsDate(LocalDate csDate) {
        this.csDate = csDate;
    }

    public LocalDate getAsDate() {
        return asDate;
    }

    public void setAsDate(LocalDate asDate) {
        this.asDate = asDate;
    }

    public LocalDate getAsuDate() {
        return asuDate;
    }

    public void setAsuDate(LocalDate asuDate) {
        this.asuDate = asuDate;
    }

    public LocalDate getCeDate() {
        return ceDate;
    }

    public void setCeDate(LocalDate ceDate) {
        this.ceDate = ceDate;
    }

    public LocalDate getCeDateF() {
        return ceDateF;
    }

    public void setCeDateF(LocalDate ceDateF) {
        this.ceDateF = ceDateF;
    }

    public LocalDate getAeDate() {
        return aeDate;
    }

    public void setAeDate(LocalDate aeDate) {
        this.aeDate = aeDate;
    }

    public BigDecimal getFcost2() {
        return fcost2;
    }

    public void setFcost2(BigDecimal fcost2) {
        this.fcost2 = fcost2;
    }

    public LocalDate getFcsDate() {
        return fcsDate;
    }

    public void setFcsDate(LocalDate fcsDate) {
        this.fcsDate = fcsDate;
    }

    public LocalDate getAfcsDate() {
        return afcsDate;
    }

    public void setAfcsDate(LocalDate afcsDate) {
        this.afcsDate = afcsDate;
    }

    public BigDecimal getFcost() {
        return fcost;
    }

    public void setFcost(BigDecimal fcost) {
        this.fcost = fcost;
    }

    public String getIsCkp() {
        return isCkp;
    }

    public void setIsCkp(String isCkp) {
        this.isCkp = isCkp;
    }

    public String getIsCkp2() {
        return isCkp2;
    }

    public void setIsCkp2(String isCkp2) {
        this.isCkp2 = isCkp2;
    }

    public String getIsCkp3() {
        return isCkp3;
    }

    public void setIsCkp3(String isCkp3) {
        this.isCkp3 = isCkp3;
    }

    public BigDecimal getCkpMoney() {
        return ckpMoney;
    }

    public void setCkpMoney(BigDecimal ckpMoney) {
        this.ckpMoney = ckpMoney;
    }

    public String getCkpUnit() {
        return ckpUnit;
    }

    public void setCkpUnit(String ckpUnit) {
        this.ckpUnit = ckpUnit;
    }

    public LocalDate getCkpDate() {
        return ckpDate;
    }

    public void setCkpDate(LocalDate ckpDate) {
        this.ckpDate = ckpDate;
    }

    public BigDecimal getCkpCnt() {
        return ckpCnt;
    }

    public void setCkpCnt(BigDecimal ckpCnt) {
        this.ckpCnt = ckpCnt;
    }

    public String getCkpNum() {
        return ckpNum;
    }

    public void setCkpNum(String ckpNum) {
        this.ckpNum = ckpNum;
    }

    public String getCkaUnit() {
        return ckaUnit;
    }

    public void setCkaUnit(String ckaUnit) {
        this.ckaUnit = ckaUnit;
    }

    public LocalDate getCkaDate() {
        return ckaDate;
    }

    public void setCkaDate(LocalDate ckaDate) {
        this.ckaDate = ckaDate;
    }

    public BigDecimal getCkaCnt() {
        return ckaCnt;
    }

    public void setCkaCnt(BigDecimal ckaCnt) {
        this.ckaCnt = ckaCnt;
    }

    public String getCkaNum() {
        return ckaNum;
    }

    public void setCkaNum(String ckaNum) {
        this.ckaNum = ckaNum;
    }

    public String getCkdUnit() {
        return ckdUnit;
    }

    public void setCkdUnit(String ckdUnit) {
        this.ckdUnit = ckdUnit;
    }

    public LocalDate getCkdDate() {
        return ckdDate;
    }

    public void setCkdDate(LocalDate ckdDate) {
        this.ckdDate = ckdDate;
    }

    public BigDecimal getCkdCnt() {
        return ckdCnt;
    }

    public void setCkdCnt(BigDecimal ckdCnt) {
        this.ckdCnt = ckdCnt;
    }

    public String getCkdNum() {
        return ckdNum;
    }

    public void setCkdNum(String ckdNum) {
        this.ckdNum = ckdNum;
    }

    public String getWorkMemo() {
        return workMemo;
    }

    public void setWorkMemo(String workMemo) {
        this.workMemo = workMemo;
    }

    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public String getLnkCode2() {
        return lnkCode2;
    }

    public void setLnkCode2(String lnkCode2) {
        this.lnkCode2 = lnkCode2;
    }

    public String getLnkCode3() {
        return lnkCode3;
    }

    public void setLnkCode3(String lnkCode3) {
        this.lnkCode3 = lnkCode3;
    }

    public String getLnkNo2() {
        return lnkNo2;
    }

    public void setLnkNo2(String lnkNo2) {
        this.lnkNo2 = lnkNo2;
    }

    public String getCcnt1() {
        return ccnt1;
    }

    public void setCcnt1(String ccnt1) {
        this.ccnt1 = ccnt1;
    }

    public String getTypeCode2() {
        return typeCode2;
    }

    public void setTypeCode2(String typeCode2) {
        this.typeCode2 = typeCode2;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAopCnt() {
        return aopCnt;
    }

    public void setAopCnt(String aopCnt) {
        this.aopCnt = aopCnt;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getIsBidstr() {
        return isBidstr;
    }

    public void setIsBidstr(String isBidstr) {
        this.isBidstr = isBidstr;
    }

    public String getIsEngineer() {
        return isEngineer;
    }

    public void setIsEngineer(String isEngineer) {
        this.isEngineer = isEngineer;
    }

    public String getName0() {
        return name0;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public BigDecimal getCtSum0() {
        return ctSum0;
    }

    public void setCtSum0(BigDecimal ctSum0) {
        this.ctSum0 = ctSum0;
    }

    public BigDecimal getBdgt10() {
        return bdgt10;
    }

    public void setBdgt10(BigDecimal bdgt10) {
        this.bdgt10 = bdgt10;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getNocntDay() {
        return nocntDay;
    }

    public void setNocntDay(Integer nocntDay) {
        this.nocntDay = nocntDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAokDate() {
        return aokDate;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
    }

    public BigDecimal getFcost3() {
        return fcost3;
    }

    public void setFcost3(BigDecimal fcost3) {
        this.fcost3 = fcost3;
    }

    public Integer getFinWays() {
        return finWays;
    }

    public void setFinWays(Integer finWays) {
        this.finWays = finWays;
    }

    public Integer getAworkDay() {
        return aworkDay;
    }

    public void setAworkDay(Integer aworkDay) {
        this.aworkDay = aworkDay;
    }

    public Integer getFastDay() {
        return fastDay;
    }

    public void setFastDay(Integer fastDay) {
        this.fastDay = fastDay;
    }

    public Integer getOverDay() {
        return overDay;
    }

    public void setOverDay(Integer overDay) {
        this.overDay = overDay;
    }

    public Integer getOverDayY() {
        return overDayY;
    }

    public void setOverDayY(Integer overDayY) {
        this.overDayY = overDayY;
    }

    public BigDecimal getOverFee() {
        return overFee;
    }

    public void setOverFee(BigDecimal overFee) {
        this.overFee = overFee;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getPunshFee() {
        return punshFee;
    }

    public void setPunshFee(BigDecimal punshFee) {
        this.punshFee = punshFee;
    }

    public String getTm2x() {
        return tm2x;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return tm2y;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getTm2x2() {
        return tm2x2;
    }

    public void setTm2x2(String tm2x2) {
        this.tm2x2 = tm2x2;
    }

    public String getTm2y2() {
        return tm2y2;
    }

    public void setTm2y2(String tm2y2) {
        this.tm2y2 = tm2y2;
    }

    public String getTm2x3() {
        return tm2x3;
    }

    public void setTm2x3(String tm2x3) {
        this.tm2x3 = tm2x3;
    }

    public String getTm2y3() {
        return tm2y3;
    }

    public void setTm2y3(String tm2y3) {
        this.tm2y3 = tm2y3;
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

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return confOut;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getSrut() {
        return srut;
    }

    public void setSrut(String srut) {
        this.srut = srut;
    }

    public BigDecimal getAnoct() {
        return anoct;
    }

    public void setAnoct(BigDecimal anoct) {
        this.anoct = anoct;
    }

    public String getCepdNo() {
        return cepdNo;
    }

    public void setCepdNo(String cepdNo) {
        this.cepdNo = cepdNo;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getScnt() {
        return scnt;
    }

    public void setScnt(String scnt) {
        this.scnt = scnt;
    }

    public String getDsdm() {
        return dsdm;
    }

    public void setDsdm(String dsdm) {
        this.dsdm = dsdm;
    }

    public String getPreson() {
        return preson;
    }

    public void setPreson(String preson) {
        this.preson = preson;
    }

    public LocalDate getPsignDate() {
        return psignDate;
    }

    public void setPsignDate(LocalDate psignDate) {
        this.psignDate = psignDate;
    }

    public LocalDate getAsignDate() {
        return asignDate;
    }

    public void setAsignDate(LocalDate asignDate) {
        this.asignDate = asignDate;
    }

    public LocalDate getTrDate() {
        return trDate;
    }

    public void setTrDate(LocalDate trDate) {
        this.trDate = trDate;
    }

    public BigDecimal getConfIn() {
        return confIn;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    public String getTpcoth() {
        return tpcoth;
    }

    public void setTpcoth(String tpcoth) {
        this.tpcoth = tpcoth;
    }

    public BigDecimal getPcmct() {
        return pcmct;
    }

    public void setPcmct(BigDecimal pcmct) {
        this.pcmct = pcmct;
    }

    public String getWkutOld() {
        return wkutOld;
    }

    public void setWkutOld(String wkutOld) {
        this.wkutOld = wkutOld;
    }

    public String getPccMemo() {
        return pccMemo;
    }

    public void setPccMemo(String pccMemo) {
        this.pccMemo = pccMemo;
    }

    public BigDecimal getBgbSumt() {
        return bgbSumt;
    }

    public void setBgbSumt(BigDecimal bgbSumt) {
        this.bgbSumt = bgbSumt;
    }

    public LocalDate getBgbDate() {
        return bgbDate;
    }

    public void setBgbDate(LocalDate bgbDate) {
        this.bgbDate = bgbDate;
    }

    public String getMainItem() {
        return mainItem;
    }

    public void setMainItem(String mainItem) {
        this.mainItem = mainItem;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmRmk() {
        return mRmk;
    }

    public void setmRmk(String mRmk) {
        this.mRmk = mRmk;
    }

    public BigDecimal getPmrSumt() {
        return pmrSumt;
    }

    public void setPmrSumt(BigDecimal pmrSumt) {
        this.pmrSumt = pmrSumt;
    }

    public LocalDate getPmrDate() {
        return pmrDate;
    }

    public void setPmrDate(LocalDate pmrDate) {
        this.pmrDate = pmrDate;
    }

    public String getPmrResm() {
        return pmrResm;
    }

    public void setPmrResm(String pmrResm) {
        this.pmrResm = pmrResm;
    }

    public List<BidProjectUnitDTO> getDsuts() {
        return dsuts;
    }

    public void setDsuts(List<BidProjectUnitDTO> dsuts) {
        this.dsuts = dsuts;
    }

    public List<BidProjectUnitDTO> getScuts() {
        return scuts;
    }

    public void setScuts(List<BidProjectUnitDTO> scuts) {
        this.scuts = scuts;
    }

    public List<BidProjectUnitDTO> getCcuts() {
        return ccuts;
    }

    public void setCcuts(List<BidProjectUnitDTO> ccuts) {
        this.ccuts = ccuts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectDTO)) return false;
        BidProjectDTO that = (BidProjectDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectDTO{" +
                "id=" + getId() +
                ", wkut='" + getWkut() + "'" +
                ", prjno='" + getPrjno() + "'" +
                ", name='" + getName() + "'" +
                ", isAdd92='" + getIsAdd92() + "'" +
                ", pntCtrl='" + getPntCtrl() + "'" +
                ", typeCode='" + getTypeCode() + "'" +
                ", isUnion='" + getIsUnion() + "'" +
                ", placeCode='" + getPlaceCode() + "'" +
                ", place='" + getPlace() + "'" +
                ", bosCode='" + getBosCode() + "'" +
                ", depCode='" + getDepCode() + "'" +
                ", isBg='" + getIsBg() + "'" +
                ", plnCode='" + getPlnCode() + "'" +
                ", plnNo='" + getPlnNo() + "'" +
                ", plnUnicode='" + getPlnUnicode() + "'" +
                ", bos2Code='" + getBos2Code() + "'" +
                ", bos2Rate=" + getBos2Rate() +
                ", bos2bCode='" + getBos2bCode() + "'" +
                ", bos2bRate=" + getBos2bRate() +
                ", bos2cCode='" + getBos2cCode() + "'" +
                ", bos2cRate=" + getBos2cRate() +
                ", bos2dCode='" + getBos2dCode() + "'" +
                ", bos2dRate=" + getBos2dRate() +
                ", lnkCode='" + getLnkCode() + "'" +
                ", ccnt2='" + getCcnt2() + "'" +
                ", man='" + getMan() + "'" +
                ", tel='" + getTel() + "'" +
                ", eMail='" + geteMail() + "'" +
                ", wrate=" + getWrate() +
                ", contain='" + getContain() + "'" +
                ", pfunc='" + getPfunc() + "'" +
                ", benf='" + getBenf() + "'" +
                ", pstand='" + getPstand() + "'" +
                ", pqty='" + getPqty() + "'" +
                ", untnd='" + getUntnd() + "'" +
                ", isSpcw='" + getIsSpcw() + "'" +
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
                ", spcw01Amt=" + getSpcw01Amt() +
                ", spcw01Nm=" + getSpcw01Nm() +
                ", spcw02Amt=" + getSpcw02Amt() +
                ", spcw02Nm=" + getSpcw02Nm() +
                ", spcw03Amt=" + getSpcw03Amt() +
                ", spcw03Nm=" + getSpcw03Nm() +
                ", spcw04Amt=" + getSpcw04Amt() +
                ", spcw04Nm=" + getSpcw04Nm() +
                ", spcw05Amt=" + getSpcw05Amt() +
                ", spcw05Nm=" + getSpcw05Nm() +
                ", spcw06Amt=" + getSpcw06Amt() +
                ", spcw06Nm=" + getSpcw06Nm() +
                ", spcw07Amt=" + getSpcw07Amt() +
                ", spcw07Nm=" + getSpcw07Nm() +
                ", spcw08Amt=" + getSpcw08Amt() +
                ", spcw08Nm=" + getSpcw08Nm() +
                ", spcw09Amt=" + getSpcw09Amt() +
                ", spcw09Nm=" + getSpcw09Nm() +
                ", spcw10Amt=" + getSpcw10Amt() +
                ", spcw10Nm=" + getSpcw10Nm() +
                ", spcw11Amt=" + getSpcw11Amt() +
                ", spcw11Nm=" + getSpcw11Nm() +
                ", spcw12Amt=" + getSpcw12Amt() +
                ", spcw12Nm=" + getSpcw12Nm() +
                ", job01Type=" + getJob01Type() +
                ", job02Type=" + getJob02Type() +
                ", job03Type=" + getJob03Type() +
                ", job04Type=" + getJob04Type() +
                ", job05Type=" + getJob05Type() +
                ", job06Type=" + getJob06Type() +
                ", job07Type=" + getJob07Type() +
                ", job08Type=" + getJob08Type() +
                ", job09Type=" + getJob09Type() +
                ", job10Type=" + getJob10Type() +
                ", job11Type=" + getJob11Type() +
                ", job12Type=" + getJob12Type() +
                ", job13Type=" + getJob13Type() +
                ", job14Type=" + getJob14Type() +
                ", semset='" + getSemset() + "'" +
                ", isRcb='" + getIsRcb() + "'" +
                ", sorc1=" + getSorc1() +
                ", sorc1b=" + getSorc1b() +
                ", sorc1c=" + getSorc1c() +
                ", sorc6=" + getSorc6() +
                ", sorc3e=" + getSorc3e() +
                ", sorc1d=" + getSorc1d() +
                ", sorc2=" + getSorc2() +
                ", sorc3=" + getSorc3() +
                ", sorc3a=" + getSorc3a() +
                ", sorc4=" + getSorc4() +
                ", sorc5=" + getSorc5() +
                ", sorc3b=" + getSorc3b() +
                ", sorc3c=" + getSorc3c() +
                ", sorc3d=" + getSorc3d() +
                ", sorc3b2=" + getSorc3b2() +
                ", sorc3b3=" + getSorc3b3() +
                ", sorc3b4=" + getSorc3b4() +
                ", sorc3b5=" + getSorc3b5() +
                ", sorc3c1=" + getSorc3c1() +
                ", sorc3d1=" + getSorc3d1() +
                ", sorc3d3=" + getSorc3d3() +
                ", sorc3d4=" + getSorc3d4() +
                ", sorc7=" + getSorc7() +
                ", sorc7Use=" + getSorc7Use() +
                ", sorc8=" + getSorc8() +
                ", sorc9=" + getSorc9() +
                ", sorc7Use2=" + getSorc7Use2() +
                ", sorc7Use3=" + getSorc7Use3() +
                ", openYn='" + getOpenYn() + "'" +
                ", isOpen='" + getIsOpen() + "'" +
                ", dOpen='" + getdOpen() + "'" +
                ", wKind='" + getwKind() + "'" +
                ", wDays=" + getwDays() +
                ", wRemk='" + getwRemk() + "'" +
                ", bCode='" + getbCode() + "'" +
                ", totAmt=" + getTotAmt() +
                ", bdgt1=" + getBdgt1() +
                ", igtct=" + getIgtct() +
                ", othct=" + getOthct() +
                ", rsvct=" + getRsvct() +
                ", poll=" + getPoll() +
                ", pollNo='" + getPollNo() + "'" +
                ", othMon=" + getOthMon() +
                ", gyResn='" + getGyResn() + "'" +
                ", outSource='" + getOutSource() + "'" +
                ", drut='" + getDrut() + "'" +
                ", drct=" + getDrct() +
                ", dsrm='" + getDsrm() + "'" +
                ", scrm='" + getScrm() + "'" +
                ", kickOff='" + getKickOff() + "'" +
                ", scrr='" + getScrr() + "'" +
                ", scnum=" + getScnum() +
                ", pcmut='" + getPcmut() + "'" +
                ", ccnum=" + getCcnum() +
                ", issStartDate='" + getIssStartDate() + "'" +
                ", issEndDate='" + getIssEndDate() + "'" +
                ", issCost=" + getIssCost() +
                ", issNo='" + getIssNo() + "'" +
                ", issRmk='" + getIssRmk() + "'" +
                ", pdsDate='" + getPdsDate() + "'" +
                ", dsDate='" + getDsDate() + "'" +
                ", adsDate='" + getAdsDate() + "'" +
                ", getlDate='" + getGetlDate() + "'" +
                ", agetlDate='" + getAgetlDate() + "'" +
                ", isSpay='" + getIsSpay() + "'" +
                ", ipe='" + getIpe() + "'" +
                ", ipeY='" + getIpeY() + "'" +
                ", ipeN='" + getIpeN() + "'" +
                ", isNlic='" + getIsNlic() + "'" +
                ", isDlic='" + getIsDlic() + "'" +
                ", licNo='" + getLicNo() + "'" +
                ", licDate='" + getLicDate() + "'" +
                ", alicDate='" + getAlicDate() + "'" +
                ", opDate='" + getOpDate() + "'" +
                ", aopDate='" + getAopDate() + "'" +
                ", pckDate='" + getPckDate() + "'" +
                ", ackDate='" + getAckDate() + "'" +
                ", pscDate='" + getPscDate() + "'" +
                ", ascDate='" + getAscDate() + "'" +
                ", pctDate='" + getPctDate() + "'" +
                ", actDate='" + getActDate() + "'" +
                ", ctmeth='" + getCtmeth() + "'" +
                ", actmth='" + getActmth() + "'" +
                ", specPurch='" + getSpecPurch() + "'" +
                ", isIntl='" + getIsIntl() + "'" +
                ", tctmth='" + getTctmth() + "'" +
                ", bdgt2=" + getBdgt2() +
                ", bdgt3=" + getBdgt3() +
                ", mctmth='" + getMctmth() + "'" +
                ", ccnt3='" + getCcnt3() + "'" +
                ", isPer='" + getIsPer() + "'" +
                ", ctSum=" + getCtSum() +
                ", ctSumF=" + getCtSumF() +
                ", ctSumU=" + getCtSumU() +
                ", ctmo='" + getCtmo() + "'" +
                ", ctSumD=" + getCtSumD() +
                ", cntid='" + getCntid() + "'" +
                ", pays=" + getPays() +
                ", pmsSumT=" + getPmsSumT() +
                ", pmsType='" + getPmsType() + "'" +
                ", paymth='" + getPaymth() + "'" +
                ", foreignYn='" + getForeignYn() + "'" +
                ", csDate='" + getCsDate() + "'" +
                ", asDate='" + getAsDate() + "'" +
                ", asuDate='" + getAsuDate() + "'" +
                ", ceDate='" + getCeDate() + "'" +
                ", ceDateF='" + getCeDateF() + "'" +
                ", aeDate='" + getAeDate() + "'" +
                ", fcost2=" + getFcost2() +
                ", fcsDate='" + getFcsDate() + "'" +
                ", afcsDate='" + getAfcsDate() + "'" +
                ", fcost=" + getFcost() +
                ", isCkp='" + getIsCkp() + "'" +
                ", isCkp2='" + getIsCkp2() + "'" +
                ", isCkp3='" + getIsCkp3() + "'" +
                ", ckpMoney=" + getCkpMoney() +
                ", ckpUnit='" + getCkpUnit() + "'" +
                ", ckpDate='" + getCkpDate() + "'" +
                ", ckpCnt=" + getCkpCnt() +
                ", ckpNum='" + getCkpNum() + "'" +
                ", ckaUnit='" + getCkaUnit() + "'" +
                ", ckaDate='" + getCkaDate() + "'" +
                ", ckaCnt=" + getCkaCnt() +
                ", ckaNum='" + getCkaNum() + "'" +
                ", ckdUnit='" + getCkdUnit() + "'" +
                ", ckdDate='" + getCkdDate() + "'" +
                ", ckdCnt=" + getCkdCnt() +
                ", ckdNum='" + getCkdNum() + "'" +
                ", workMemo='" + getWorkMemo() + "'" +
                ", hotLine='" + getHotLine() + "'" +
                ", lnkCode2='" + getLnkCode2() + "'" +
                ", lnkCode3='" + getLnkCode3() + "'" +
                ", lnkNo2='" + getLnkNo2() + "'" +
                ", ccnt1='" + getCcnt1() + "'" +
                ", typeCode2='" + getTypeCode2() + "'" +
                ", deptName='" + getDeptName() + "'" +
                ", aopCnt='" + getAopCnt() + "'" +
                ", uniqueKey='" + getUniqueKey() + "'" +
                ", isBidstr='" + getIsBidstr() + "'" +
                ", isEngineer='" + getIsEngineer() + "'" +
                ", name0='" + getName0() + "'" +
                ", ctSum0=" + getCtSum0() +
                ", bdgt10=" + getBdgt10() +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", batch='" + getBatch() + "'" +
                ", nocntDay=" + getNocntDay() +
                ", status='" + getStatus() + "'" +
                ", aokDate='" + getAokDate() + "'" +
                ", fcost3=" + getFcost3() +
                ", finWays=" + getFinWays() +
                ", aworkDay=" + getAworkDay() +
                ", fastDay=" + getFastDay() +
                ", overDay=" + getOverDay() +
                ", overDayY=" + getOverDayY() +
                ", overFee=" + getOverFee() +
                ", otherFee=" + getOtherFee() +
                ", punshFee=" + getPunshFee() +
                ", tm2x='" + getTm2x() + "'" +
                ", tm2y='" + getTm2y() + "'" +
                ", tm2x2='" + getTm2x2() + "'" +
                ", tm2y2='" + getTm2y2() + "'" +
                ", tm2x3='" + getTm2x3() + "'" +
                ", tm2y3='" + getTm2y3() + "'" +
                ", createDate='" + getCreateDate() + "'" +
                ", createUser='" + getCreateUser() + "'" +
                ", updateDate='" + getUpdateDate() + "'" +
                ", updateUser='" + getUpdateUser() + "'" +
                ", dsts='" + getDsts() + "'" +
                ", confOut=" + getConfOut() +
                ", uIp='" + getuIp() + "'" +
                ", can='" + getCan() + "'" +
                ", srut='" + getSrut() + "'" +
                ", anoct=" + getAnoct() +
                ", cepdNo='" + getCepdNo() + "'" +
                ", subNo='" + getSubNo() + "'" +
                ", scnt='" + getScnt() + "'" +
                ", dsdm='" + getDsdm() + "'" +
                ", preson='" + getPreson() + "'" +
                ", psignDate='" + getPsignDate() + "'" +
                ", asignDate='" + getAsignDate() + "'" +
                ", trDate='" + getTrDate() + "'" +
                ", confIn=" + getConfIn() +
                ", uKey='" + getuKey() + "'" +
                ", tpcoth='" + getTpcoth() + "'" +
                ", pcmct=" + getPcmct() +
                ", wkutOld='" + getWkutOld() + "'" +
                ", pccMemo='" + getPccMemo() + "'" +
                ", bgbSumt=" + getBgbSumt() +
                ", bgbDate='" + getBgbDate() + "'" +
                ", mainItem='" + getMainItem() + "'" +
                ", mDesc='" + getmDesc() + "'" +
                ", mRmk='" + getmRmk() + "'" +
                ", pmrSumt=" + getPmrSumt() +
                ", pmrDate='" + getPmrDate() + "'" +
                ", pmrResm='" + getPmrResm() + "'" +
                "}";
    }
}
