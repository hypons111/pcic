package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidProjectPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidProject} entity.
 */
public class BidProjectBaseDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    @Size(max = 180)
    private String name;

    @Size(max = 180)
    private String name0;

    @Size(max = 1)
    private String dOpen;

    private String wkutName;

    @Size(max = 80)
    private String man;

    @Size(max = 30)
    private String tel;

    @Size(max = 21)
    private String plnCode;

    private String plnName;

    @Size(max = 20)
    private String plnNo;

    @Size(max = 30)
    private String plnUnicode;

    @Size(max = 2)
    private String subNo;

    @Size(max = 2)
    private String isAdd92;

    private String isAdd92Name;

    @Size(max = 21)
    private String bosCode;

    private String bosName;

    @Size(max = 21)
    private String depCode;

    private String depName;

    @Size(max = 10)
    private String bos2Code;

    private String bos2Name;

    private BigDecimal bos2Rate;

    @Size(max = 21)
    private String bos2bCode;

    private String bos2bName;

    private BigDecimal bos2bRate;

    @Size(max = 21)
    private String bos2cCode;

    private String bos2cName;

    private BigDecimal bos2cRate;

    @Size(max = 21)
    private String bos2dCode;

    private String bos2dName;

    private BigDecimal bos2dRate;

    @Size(max = 40)
    private String lnkCode;

    @Size(max = 60)
    private String deptName;

    @Size(max = 40)
    private String lnkCode3;

    @Size(max = 1)
    private String isUnion;

    @Size(max = 1)
    private String specPurch;

    @Size(max = 1)
    private String isIntl;

    @Size(max = 2)
    private String typeCode;

    private String typeName;

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

    @Size(max = 2000)
    private String contain;

    @Size(max = 1000)
    private String benf;

    @Size(max = 1)
    private String wKind;

    private String wKindName;

    private Integer wDays;

    @Size(max = 200)
    private String wRemk;

    @Size(max = 4)
    private String placeCode;

    private String placeName;

    @Size(max = 100)
    private String place;

    @Size(max = 20)
    private String tm2x;

    @Size(max = 20)
    private String tm2y;

    private BigDecimal totAmt;

    private BigDecimal bdgt1;

    private BigDecimal igtct;

    private BigDecimal othct;

    private BigDecimal rsvct;

    private BigDecimal poll;

    @Size(max = 50)
    private String pollNo;

    @Size(max = 21)
    private String drut;

    private String drutName;

    private BigDecimal drct;

    @Size(max = 21)
    private String pcmut;

    private String pcmutName;

    private LocalDate pdsDate;

    private LocalDate dsDate;

    private LocalDate adsDate;

    private LocalDate opDate;

    private LocalDate aopDate;

    @Size(max = 5)
    private String aopCnt;

    private LocalDate pctDate;

    private LocalDate actDate;

    @Size(max = 1)
    private String ctmeth;

    private String ctmethName;

    @Size(max = 2)
    private String actmth;

    private String actmthName;

    @Size(max = 2)
    private String mctmth;

    private String mctmthName;

    @Size(max = 2)
    private String ccnt3;

    @Size(max = 1)
    private String tctmth;

    private String tctmthName;

    private BigDecimal ctSum;

    private BigDecimal ctSum0;

    private BigDecimal ctSumF;

    private BigDecimal pays;

    @Size(max = 1)
    private String paymth;

    @Size(max = 2000)
    private String ctmo;

    private LocalDate csDate;

    private LocalDate asuDate;

    private LocalDate asDate;

    private LocalDate issStartDate;

    private LocalDate issEndDate;

    private BigDecimal issCost;

    @Size(max = 100)
    private String issNo;

    @Size(max = 2000)
    private String issRmk;

    private LocalDate ceDate;

    private LocalDate ceDateF;

    private LocalDate aeDate;

    @Size(max = 2000)
    private String gyResn;

    private LocalDate aokDate;

    private BigDecimal bgbSumt;

    private LocalDate bgbDate;

    private LocalDate fcsDate;

    private LocalDate afcsDate;

    private BigDecimal fcost2;

    private BigDecimal fcost;

    @Size(max = 100)
    private String ckpUnit;

    private String ckpUnitName;

    private LocalDate ckpDate;

    @Size(max = 100)
    private String ckpNum;

    @Size(max = 100)
    private String ckaUnit;

    private String ckaUnitName;

    private LocalDate ckaDate;

    @Size(max = 100)
    private String ckaNum;

    @Size(max = 100)
    private String ckdUnit;

    private String ckdUnitName;

    private LocalDate ckdDate;

    @Size(max = 100)
    private String ckdNum;

    @Size(max = 1)
    private String isCkp;

    @Size(max = 1)
    private String isCkp2;

    @Size(max = 1)
    private String isCkp3;

    @Size(max = 1)
    private String semset;

    private List<BidProjectUnitDTO> dsuts;

    private List<BidProjectUnitDTO> scuts;

    private List<BidProjectUnitDTO> ccuts;

    private List<BidPrjGdfkDTO> bidPrjGdfkDTOs;

    private List<BidPrjSubcoDTO> bidPrjSubcoDTOs;

    private List<BidPrjFalsDTO> bidPrjFalsDTOs;

    private List<BidPrjModifyDTO> bidPrjModifyDTOs;

    private List<BidPrjStopwkDTO> bidPrjStopwkDTOs;

    private List<BidPrjPeDTO> bidPrjPeDTOs;

    private List<BidPrjQcDTO> bidPrjQcDTOs;

    private List<BidPrjWkmengDTO> bidPrjWkmengDTOs;

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

    public String getName0() {
        return name0;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public String getdOpen() {
        return dOpen;
    }

    public void setdOpen(String dOpen) {
        this.dOpen = dOpen;
    }

    public String getWkutName() {
        return wkutName;
    }

    public void setWkutName(String wkutName) {
        this.wkutName = wkutName;
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

    public String getPlnCode() {
        return plnCode;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnName() {
        return plnName;
    }

    public void setPlnName(String plnName) {
        this.plnName = plnName;
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

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getIsAdd92() {
        return isAdd92;
    }

    public void setIsAdd92(String isAdd92) {
        this.isAdd92 = isAdd92;
    }

    public String getIsAdd92Name() {
        return isAdd92Name;
    }

    public void setIsAdd92Name(String isAdd92Name) {
        this.isAdd92Name = isAdd92Name;
    }

    public String getBosCode() {
        return bosCode;
    }

    public void setBosCode(String bosCode) {
        this.bosCode = bosCode;
    }

    public String getBosName() {
        return bosName;
    }

    public void setBosName(String bosName) {
        this.bosName = bosName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getBos2Code() {
        return bos2Code;
    }

    public void setBos2Code(String bos2Code) {
        this.bos2Code = bos2Code;
    }

    public String getBos2Name() {
        return bos2Name;
    }

    public void setBos2Name(String bos2Name) {
        this.bos2Name = bos2Name;
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

    public String getBos2bName() {
        return bos2bName;
    }

    public void setBos2bName(String bos2bName) {
        this.bos2bName = bos2bName;
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

    public String getBos2cName() {
        return bos2cName;
    }

    public void setBos2cName(String bos2cName) {
        this.bos2cName = bos2cName;
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

    public String getBos2dName() {
        return bos2dName;
    }

    public void setBos2dName(String bos2dName) {
        this.bos2dName = bos2dName;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLnkCode3() {
        return lnkCode3;
    }

    public void setLnkCode3(String lnkCode3) {
        this.lnkCode3 = lnkCode3;
    }

    public String getIsUnion() {
        return isUnion;
    }

    public void setIsUnion(String isUnion) {
        this.isUnion = isUnion;
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getBenf() {
        return benf;
    }

    public void setBenf(String benf) {
        this.benf = benf;
    }

    public String getwKind() {
        return wKind;
    }

    public void setwKind(String wKind) {
        this.wKind = wKind;
    }

    public String getwKindName() {
        return wKindName;
    }

    public void setwKindName(String wKindName) {
        this.wKindName = wKindName;
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

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getDrut() {
        return drut;
    }

    public void setDrut(String drut) {
        this.drut = drut;
    }

    public String getDrutName() {
        return drutName;
    }

    public void setDrutName(String drutName) {
        this.drutName = drutName;
    }

    public BigDecimal getDrct() {
        return drct;
    }

    public void setDrct(BigDecimal drct) {
        this.drct = drct;
    }

    public String getPcmut() {
        return pcmut;
    }

    public void setPcmut(String pcmut) {
        this.pcmut = pcmut;
    }

    public String getPcmutName() {
        return pcmutName;
    }

    public void setPcmutName(String pcmutName) {
        this.pcmutName = pcmutName;
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

    public String getAopCnt() {
        return aopCnt;
    }

    public void setAopCnt(String aopCnt) {
        this.aopCnt = aopCnt;
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

    public String getCtmethName() {
        return ctmethName;
    }

    public void setCtmethName(String ctmethName) {
        this.ctmethName = ctmethName;
    }

    public String getActmth() {
        return actmth;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public String getActmthName() {
        return actmthName;
    }

    public void setActmthName(String actmthName) {
        this.actmthName = actmthName;
    }

    public String getMctmth() {
        return mctmth;
    }

    public void setMctmth(String mctmth) {
        this.mctmth = mctmth;
    }

    public String getMctmthName() {
        return mctmthName;
    }

    public void setMctmthName(String mctmthName) {
        this.mctmthName = mctmthName;
    }

    public String getCcnt3() {
        return ccnt3;
    }

    public void setCcnt3(String ccnt3) {
        this.ccnt3 = ccnt3;
    }

    public String getTctmth() {
        return tctmth;
    }

    public void setTctmth(String tctmth) {
        this.tctmth = tctmth;
    }

    public String getTctmthName() {
        return tctmthName;
    }

    public void setTctmthName(String tctmthName) {
        this.tctmthName = tctmthName;
    }

    public BigDecimal getCtSum() {
        return ctSum;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public BigDecimal getCtSum0() {
        return ctSum0;
    }

    public void setCtSum0(BigDecimal ctSum0) {
        this.ctSum0 = ctSum0;
    }

    public BigDecimal getCtSumF() {
        return ctSumF;
    }

    public void setCtSumF(BigDecimal ctSumF) {
        this.ctSumF = ctSumF;
    }

    public BigDecimal getPays() {
        return pays;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public String getPaymth() {
        return paymth;
    }

    public void setPaymth(String paymth) {
        this.paymth = paymth;
    }

    public String getCtmo() {
        return ctmo;
    }

    public void setCtmo(String ctmo) {
        this.ctmo = ctmo;
    }

    public LocalDate getCsDate() {
        return csDate;
    }

    public void setCsDate(LocalDate csDate) {
        this.csDate = csDate;
    }

    public LocalDate getAsuDate() {
        return asuDate;
    }

    public void setAsuDate(LocalDate asuDate) {
        this.asuDate = asuDate;
    }

    public LocalDate getAsDate() {
        return asDate;
    }

    public void setAsDate(LocalDate asDate) {
        this.asDate = asDate;
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

    public String getGyResn() {
        return gyResn;
    }

    public void setGyResn(String gyResn) {
        this.gyResn = gyResn;
    }

    public LocalDate getAokDate() {
        return aokDate;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
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

    public BigDecimal getFcost2() {
        return fcost2;
    }

    public void setFcost2(BigDecimal fcost2) {
        this.fcost2 = fcost2;
    }

    public BigDecimal getFcost() {
        return fcost;
    }

    public void setFcost(BigDecimal fcost) {
        this.fcost = fcost;
    }

    public String getCkpUnit() {
        return ckpUnit;
    }

    public void setCkpUnit(String ckpUnit) {
        this.ckpUnit = ckpUnit;
    }

    public String getCkpUnitName() {
        return ckpUnitName;
    }

    public void setCkpUnitName(String ckpUnitName) {
        this.ckpUnitName = ckpUnitName;
    }

    public LocalDate getCkpDate() {
        return ckpDate;
    }

    public void setCkpDate(LocalDate ckpDate) {
        this.ckpDate = ckpDate;
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

    public String getCkaUnitName() {
        return ckaUnitName;
    }

    public void setCkaUnitName(String ckaUnitName) {
        this.ckaUnitName = ckaUnitName;
    }

    public LocalDate getCkaDate() {
        return ckaDate;
    }

    public void setCkaDate(LocalDate ckaDate) {
        this.ckaDate = ckaDate;
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

    public String getCkdUnitName() {
        return ckdUnitName;
    }

    public void setCkdUnitName(String ckdUnitName) {
        this.ckdUnitName = ckdUnitName;
    }

    public LocalDate getCkdDate() {
        return ckdDate;
    }

    public void setCkdDate(LocalDate ckdDate) {
        this.ckdDate = ckdDate;
    }

    public String getCkdNum() {
        return ckdNum;
    }

    public void setCkdNum(String ckdNum) {
        this.ckdNum = ckdNum;
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

    public String getSemset() {
        return semset;
    }

    public void setSemset(String semset) {
        this.semset = semset;
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

    public List<BidPrjGdfkDTO> getBidPrjGdfkDTOs() {
        return bidPrjGdfkDTOs;
    }

    public void setBidPrjGdfkDTOs(List<BidPrjGdfkDTO> bidPrjGdfkDTOs) {
        this.bidPrjGdfkDTOs = bidPrjGdfkDTOs;
    }

    public List<BidPrjSubcoDTO> getBidPrjSubcoDTOs() {
        return bidPrjSubcoDTOs;
    }

    public void setBidPrjSubcoDTOs(List<BidPrjSubcoDTO> bidPrjSubcoDTOs) {
        this.bidPrjSubcoDTOs = bidPrjSubcoDTOs;
    }

    public List<BidPrjFalsDTO> getBidPrjFalsDTOs() {
        return bidPrjFalsDTOs;
    }

    public void setBidPrjFalsDTOs(List<BidPrjFalsDTO> bidPrjFalsDTOs) {
        this.bidPrjFalsDTOs = bidPrjFalsDTOs;
    }

    public List<BidPrjModifyDTO> getBidPrjModifyDTOs() {
        return bidPrjModifyDTOs;
    }

    public void setBidPrjModifyDTOs(List<BidPrjModifyDTO> bidPrjModifyDTOs) {
        this.bidPrjModifyDTOs = bidPrjModifyDTOs;
    }

    public List<BidPrjStopwkDTO> getBidPrjStopwkDTOs() {
        return bidPrjStopwkDTOs;
    }

    public void setBidPrjStopwkDTOs(List<BidPrjStopwkDTO> bidPrjStopwkDTOs) {
        this.bidPrjStopwkDTOs = bidPrjStopwkDTOs;
    }

    public List<BidPrjPeDTO> getBidPrjPeDTOs() {
        return bidPrjPeDTOs;
    }

    public void setBidPrjPeDTOs(List<BidPrjPeDTO> bidPrjPeDTOs) {
        this.bidPrjPeDTOs = bidPrjPeDTOs;
    }

    public List<BidPrjQcDTO> getBidPrjQcDTOs() {
        return bidPrjQcDTOs;
    }

    public void setBidPrjQcDTOs(List<BidPrjQcDTO> bidPrjQcDTOs) {
        this.bidPrjQcDTOs = bidPrjQcDTOs;
    }

    public List<BidPrjWkmengDTO> getBidPrjWkmengDTOs() {
        return bidPrjWkmengDTOs;
    }

    public void setBidPrjWkmengDTOs(List<BidPrjWkmengDTO> bidPrjWkmengDTOs) {
        this.bidPrjWkmengDTOs = bidPrjWkmengDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProjectBaseDTO)) return false;
        BidProjectBaseDTO that = (BidProjectBaseDTO) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProjectBaseDTO{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", name='" + name + '\'' +
                ", name0='" + name0 + '\'' +
                ", dOpen='" + dOpen + '\'' +
                ", wkutName='" + wkutName + '\'' +
                ", man='" + man + '\'' +
                ", tel='" + tel + '\'' +
                ", plnCode='" + plnCode + '\'' +
                ", plnName='" + plnName + '\'' +
                ", plnNo='" + plnNo + '\'' +
                ", plnUnicode='" + plnUnicode + '\'' +
                ", subNo='" + subNo + '\'' +
                ", isAdd92='" + isAdd92 + '\'' +
                ", isAdd92Name='" + isAdd92Name + '\'' +
                ", bosCode='" + bosCode + '\'' +
                ", bosName='" + bosName + '\'' +
                ", depCode='" + depCode + '\'' +
                ", depName='" + depName + '\'' +
                ", bos2Code='" + bos2Code + '\'' +
                ", bos2Name='" + bos2Name + '\'' +
                ", bos2Rate=" + bos2Rate +
                ", bos2bCode='" + bos2bCode + '\'' +
                ", bos2bName='" + bos2bName + '\'' +
                ", bos2bRate=" + bos2bRate +
                ", bos2cCode='" + bos2cCode + '\'' +
                ", bos2cName='" + bos2cName + '\'' +
                ", bos2cRate=" + bos2cRate +
                ", bos2dCode='" + bos2dCode + '\'' +
                ", bos2dName='" + bos2dName + '\'' +
                ", bos2dRate=" + bos2dRate +
                ", lnkCode='" + lnkCode + '\'' +
                ", deptName='" + deptName + '\'' +
                ", lnkCode3='" + lnkCode3 + '\'' +
                ", isUnion='" + isUnion + '\'' +
                ", specPurch='" + specPurch + '\'' +
                ", isIntl='" + isIntl + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", untnd='" + untnd + '\'' +
                ", isSpcw='" + isSpcw + '\'' +
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
                ", contain='" + contain + '\'' +
                ", benf='" + benf + '\'' +
                ", wKind='" + wKind + '\'' +
                ", wKindName='" + wKindName + '\'' +
                ", wDays=" + wDays +
                ", wRemk='" + wRemk + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", placeName='" + placeName + '\'' +
                ", place='" + place + '\'' +
                ", tm2x='" + tm2x + '\'' +
                ", tm2y='" + tm2y + '\'' +
                ", totAmt=" + totAmt +
                ", bdgt1=" + bdgt1 +
                ", igtct=" + igtct +
                ", othct=" + othct +
                ", rsvct=" + rsvct +
                ", poll=" + poll +
                ", pollNo='" + pollNo + '\'' +
                ", drut='" + drut + '\'' +
                ", drutName='" + drutName + '\'' +
                ", drct=" + drct +
                ", pcmut='" + pcmut + '\'' +
                ", pcmutName='" + pcmutName + '\'' +
                ", pdsDate=" + pdsDate +
                ", dsDate=" + dsDate +
                ", adsDate=" + adsDate +
                ", opDate=" + opDate +
                ", aopDate=" + aopDate +
                ", aopCnt='" + aopCnt + '\'' +
                ", pctDate=" + pctDate +
                ", actDate=" + actDate +
                ", ctmeth='" + ctmeth + '\'' +
                ", ctmethName='" + ctmethName + '\'' +
                ", actmth='" + actmth + '\'' +
                ", actmthName='" + actmthName + '\'' +
                ", mctmth='" + mctmth + '\'' +
                ", mctmthName='" + mctmthName + '\'' +
                ", ccnt3='" + ccnt3 + '\'' +
                ", tctmth='" + tctmth + '\'' +
                ", tctmthName='" + tctmthName + '\'' +
                ", ctSum=" + ctSum +
                ", ctSum0=" + ctSum0 +
                ", ctSumF=" + ctSumF +
                ", pays=" + pays +
                ", paymth='" + paymth + '\'' +
                ", ctmo='" + ctmo + '\'' +
                ", csDate=" + csDate +
                ", asuDate=" + asuDate +
                ", asDate=" + asDate +
                ", issStartDate=" + issStartDate +
                ", issEndDate=" + issEndDate +
                ", issCost=" + issCost +
                ", issNo='" + issNo + '\'' +
                ", issRmk='" + issRmk + '\'' +
                ", ceDate=" + ceDate +
                ", ceDateF=" + ceDateF +
                ", aeDate=" + aeDate +
                ", gyResn='" + gyResn + '\'' +
                ", aokDate=" + aokDate +
                ", bgbSumt=" + bgbSumt +
                ", bgbDate=" + bgbDate +
                ", fcsDate=" + fcsDate +
                ", afcsDate=" + afcsDate +
                ", fcost2=" + fcost2 +
                ", fcost=" + fcost +
                ", ckpUnit='" + ckpUnit + '\'' +
                ", ckpUnitName='" + ckpUnitName + '\'' +
                ", ckpDate=" + ckpDate +
                ", ckpNum='" + ckpNum + '\'' +
                ", ckaUnit='" + ckaUnit + '\'' +
                ", ckaUnitName='" + ckaUnitName + '\'' +
                ", ckaDate=" + ckaDate +
                ", ckaNum='" + ckaNum + '\'' +
                ", ckdUnit='" + ckdUnit + '\'' +
                ", ckdUnitName='" + ckdUnitName + '\'' +
                ", ckdDate=" + ckdDate +
                ", ckdNum='" + ckdNum + '\'' +
                ", isCkp='" + isCkp + '\'' +
                ", isCkp2='" + isCkp2 + '\'' +
                ", isCkp3='" + isCkp3 + '\'' +
                ", semset='" + semset + '\'' +
                '}';
    }
}
