package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjMonthPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjMonth} entity.
 */
public class BidPrjMonthDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 20)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String yr;

    @NotNull
    @Size(max = 2)
    private String month;

    @NotNull
    @Size(max = 3)
    private String syr;

    private BigDecimal ypexp;

    private BigDecimal rate;

    private BigDecimal pexp;

    private BigDecimal ptrate;

    @Size(max = 2000)
    private String item;

    private BigDecimal yaexp;

    private BigDecimal arat;

    private BigDecimal aexp;

    private BigDecimal atrate;

    @Size(max = 2000)
    private String remark;

    private BigDecimal sumt;

    @Size(max = 2)
    private String stus;

    @Size(max = 1)
    private String mdsts;

    @Size(max = 7)
    private String sumdat;

    private BigDecimal nopayamt;

    @Size(max = 2)
    private String nopayresn;

    private BigDecimal resumt;

    private LocalDate trdt;

    @Size(max = 2000)
    private String nopaydetel;

    private BigDecimal meetCnt;

    private BigDecimal meterChk;

    private BigDecimal meterChkF;

    private BigDecimal workChk;

    private BigDecimal workChkF;

    @Size(max = 2)
    private String replMeth;

    private BigDecimal teckind3;

    private BigDecimal enviCnt;

    private BigDecimal enviFee;

    private BigDecimal wsafeCnt;

    private BigDecimal wsafeFee;

    private BigDecimal sorc1;

    private BigDecimal sorc1b;

    private BigDecimal sorc1c;

    private BigDecimal sorc1d;

    private BigDecimal sorc2;

    private BigDecimal sorc3;

    private BigDecimal sorc4;

    private BigDecimal sorc5;

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

    @Size(max = 1)
    private String equp01;

    @Size(max = 1)
    private String equp02;

    @Size(max = 1)
    private String equp03;

    @Size(max = 1)
    private String equp04;

    @Size(max = 1)
    private String equp05;

    @Size(max = 1)
    private String equp06;

    @Size(max = 1)
    private String equp07;

    @Size(max = 1)
    private String equp08;

    @Size(max = 1)
    private String equp09;

    @Size(max = 1)
    private String equp10;

    @Size(max = 1)
    private String equp11;

    private BigDecimal cosend;

    private BigDecimal aTrate;

    private Integer wdayeds;

    private BigDecimal sorc3b;

    private BigDecimal sorc3c;

    private BigDecimal sorc3d;

    private BigDecimal sorc3e;

    private BigDecimal sorc3a;

    private BigDecimal sorc6;

    private BigDecimal sorc3b2;

    private BigDecimal sorc3b3;

    private BigDecimal sorc3b4;

    private BigDecimal sorc3b5;

    private BigDecimal sorc3c1;

    private BigDecimal sorc3d1;

    private BigDecimal sorc3d3;

    private BigDecimal sorc3d4;

    private BigDecimal teckind4;

    private BigDecimal sorc7;

    private BigDecimal sorc7use;

    private BigDecimal sorc7oth;

    private BigDecimal sorc7tac;

    private BigDecimal sorc7self;

    private BigDecimal sorc8;

    @Size(max = 1)
    private String payArr;

    private BigDecimal sorc7use2;

    private BigDecimal sorc7use3;

    private BigDecimal sfrWkr;

    private BigDecimal sorc9;

    @Size(max = 1)
    private String dsts;

    private BigDecimal confOut;

    private BigDecimal confOut2;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    @Size(max = 7)
    private String udt2;


    public BidPrjMonthPrimaryKey getId(){
        return new BidPrjMonthPrimaryKey(wkut,prjno, yr, month);
    }

    public void setId(BidPrjMonthPrimaryKey id){
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.yr = id.getSyr();
        this.month = id.getMonth();    }


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

    public String getYr() {
        return yr;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public BigDecimal getYpexp() {
        return ypexp;
    }

    public void setYpexp(BigDecimal ypexp) {
        this.ypexp = ypexp;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getPexp() {
        return pexp;
    }

    public void setPexp(BigDecimal pexp) {
        this.pexp = pexp;
    }

    public BigDecimal getPtrate() {
        return ptrate;
    }

    public void setPtrate(BigDecimal ptrate) {
        this.ptrate = ptrate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getYaexp() {
        return yaexp;
    }

    public void setYaexp(BigDecimal yaexp) {
        this.yaexp = yaexp;
    }

    public BigDecimal getArat() {
        return arat;
    }

    public void setArat(BigDecimal arat) {
        this.arat = arat;
    }

    public BigDecimal getAexp() {
        return aexp;
    }

    public void setAexp(BigDecimal aexp) {
        this.aexp = aexp;
    }

    public BigDecimal getAtrate() {
        return atrate;
    }

    public void setAtrate(BigDecimal atrate) {
        this.atrate = atrate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSumt() {
        return sumt;
    }

    public void setSumt(BigDecimal sumt) {
        this.sumt = sumt;
    }

    public String getStus() {
        return stus;
    }

    public void setStus(String stus) {
        this.stus = stus;
    }

    public String getMdsts() {
        return mdsts;
    }

    public void setMdsts(String mdsts) {
        this.mdsts = mdsts;
    }

    public String getSumdat() {
        return sumdat;
    }

    public void setSumdat(String sumdat) {
        this.sumdat = sumdat;
    }

    public BigDecimal getNopayamt() {
        return nopayamt;
    }

    public void setNopayamt(BigDecimal nopayamt) {
        this.nopayamt = nopayamt;
    }

    public String getNopayresn() {
        return nopayresn;
    }

    public void setNopayresn(String nopayresn) {
        this.nopayresn = nopayresn;
    }

    public BigDecimal getResumt() {
        return resumt;
    }

    public void setResumt(BigDecimal resumt) {
        this.resumt = resumt;
    }

    public LocalDate getTrdt() {
        return trdt;
    }

    public void setTrdt(LocalDate trdt) {
        this.trdt = trdt;
    }

    public String getNopaydetel() {
        return nopaydetel;
    }

    public void setNopaydetel(String nopaydetel) {
        this.nopaydetel = nopaydetel;
    }

    public BigDecimal getMeetCnt() {
        return meetCnt;
    }

    public void setMeetCnt(BigDecimal meetCnt) {
        this.meetCnt = meetCnt;
    }

    public BigDecimal getMeterChk() {
        return meterChk;
    }

    public void setMeterChk(BigDecimal meterChk) {
        this.meterChk = meterChk;
    }

    public BigDecimal getMeterChkF() {
        return meterChkF;
    }

    public void setMeterChkF(BigDecimal meterChkF) {
        this.meterChkF = meterChkF;
    }

    public BigDecimal getWorkChk() {
        return workChk;
    }

    public void setWorkChk(BigDecimal workChk) {
        this.workChk = workChk;
    }

    public BigDecimal getWorkChkF() {
        return workChkF;
    }

    public void setWorkChkF(BigDecimal workChkF) {
        this.workChkF = workChkF;
    }

    public String getReplMeth() {
        return replMeth;
    }

    public void setReplMeth(String replMeth) {
        this.replMeth = replMeth;
    }

    public BigDecimal getTeckind3() {
        return teckind3;
    }

    public void setTeckind3(BigDecimal teckind3) {
        this.teckind3 = teckind3;
    }

    public BigDecimal getEnviCnt() {
        return enviCnt;
    }

    public void setEnviCnt(BigDecimal enviCnt) {
        this.enviCnt = enviCnt;
    }

    public BigDecimal getEnviFee() {
        return enviFee;
    }

    public void setEnviFee(BigDecimal enviFee) {
        this.enviFee = enviFee;
    }

    public BigDecimal getWsafeCnt() {
        return wsafeCnt;
    }

    public void setWsafeCnt(BigDecimal wsafeCnt) {
        this.wsafeCnt = wsafeCnt;
    }

    public BigDecimal getWsafeFee() {
        return wsafeFee;
    }

    public void setWsafeFee(BigDecimal wsafeFee) {
        this.wsafeFee = wsafeFee;
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

    public String getEqup01() {
        return equp01;
    }

    public void setEqup01(String equp01) {
        this.equp01 = equp01;
    }

    public String getEqup02() {
        return equp02;
    }

    public void setEqup02(String equp02) {
        this.equp02 = equp02;
    }

    public String getEqup03() {
        return equp03;
    }

    public void setEqup03(String equp03) {
        this.equp03 = equp03;
    }

    public String getEqup04() {
        return equp04;
    }

    public void setEqup04(String equp04) {
        this.equp04 = equp04;
    }

    public String getEqup05() {
        return equp05;
    }

    public void setEqup05(String equp05) {
        this.equp05 = equp05;
    }

    public String getEqup06() {
        return equp06;
    }

    public void setEqup06(String equp06) {
        this.equp06 = equp06;
    }

    public String getEqup07() {
        return equp07;
    }

    public void setEqup07(String equp07) {
        this.equp07 = equp07;
    }

    public String getEqup08() {
        return equp08;
    }

    public void setEqup08(String equp08) {
        this.equp08 = equp08;
    }

    public String getEqup09() {
        return equp09;
    }

    public void setEqup09(String equp09) {
        this.equp09 = equp09;
    }

    public String getEqup10() {
        return equp10;
    }

    public void setEqup10(String equp10) {
        this.equp10 = equp10;
    }

    public String getEqup11() {
        return equp11;
    }

    public void setEqup11(String equp11) {
        this.equp11 = equp11;
    }

    public BigDecimal getCosend() {
        return cosend;
    }

    public void setCosend(BigDecimal cosend) {
        this.cosend = cosend;
    }

    public BigDecimal getaTrate() {
        return aTrate;
    }

    public void setaTrate(BigDecimal aTrate) {
        this.aTrate = aTrate;
    }

    public Integer getWdayeds() {
        return wdayeds;
    }

    public void setWdayeds(Integer wdayeds) {
        this.wdayeds = wdayeds;
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

    public BigDecimal getSorc3e() {
        return sorc3e;
    }

    public void setSorc3e(BigDecimal sorc3e) {
        this.sorc3e = sorc3e;
    }

    public BigDecimal getSorc3a() {
        return sorc3a;
    }

    public void setSorc3a(BigDecimal sorc3a) {
        this.sorc3a = sorc3a;
    }

    public BigDecimal getSorc6() {
        return sorc6;
    }

    public void setSorc6(BigDecimal sorc6) {
        this.sorc6 = sorc6;
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

    public BigDecimal getTeckind4() {
        return teckind4;
    }

    public void setTeckind4(BigDecimal teckind4) {
        this.teckind4 = teckind4;
    }

    public BigDecimal getSorc7() {
        return sorc7;
    }

    public void setSorc7(BigDecimal sorc7) {
        this.sorc7 = sorc7;
    }

    public BigDecimal getSorc7use() {
        return sorc7use;
    }

    public void setSorc7use(BigDecimal sorc7use) {
        this.sorc7use = sorc7use;
    }

    public BigDecimal getSorc7oth() {
        return sorc7oth;
    }

    public void setSorc7oth(BigDecimal sorc7oth) {
        this.sorc7oth = sorc7oth;
    }

    public BigDecimal getSorc7tac() {
        return sorc7tac;
    }

    public void setSorc7tac(BigDecimal sorc7tac) {
        this.sorc7tac = sorc7tac;
    }

    public BigDecimal getSorc7self() {
        return sorc7self;
    }

    public void setSorc7self(BigDecimal sorc7self) {
        this.sorc7self = sorc7self;
    }

    public BigDecimal getSorc8() {
        return sorc8;
    }

    public void setSorc8(BigDecimal sorc8) {
        this.sorc8 = sorc8;
    }

    public String getPayArr() {
        return payArr;
    }

    public void setPayArr(String payArr) {
        this.payArr = payArr;
    }

    public BigDecimal getSorc7use2() {
        return sorc7use2;
    }

    public void setSorc7use2(BigDecimal sorc7use2) {
        this.sorc7use2 = sorc7use2;
    }

    public BigDecimal getSorc7use3() {
        return sorc7use3;
    }

    public void setSorc7use3(BigDecimal sorc7use3) {
        this.sorc7use3 = sorc7use3;
    }

    public BigDecimal getSfrWkr() {
        return sfrWkr;
    }

    public void setSfrWkr(BigDecimal sfrWkr) {
        this.sfrWkr = sfrWkr;
    }

    public BigDecimal getSorc9() {
        return sorc9;
    }

    public void setSorc9(BigDecimal sorc9) {
        this.sorc9 = sorc9;
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

    public BigDecimal getConfOut2() {
        return confOut2;
    }

    public void setConfOut2(BigDecimal confOut2) {
        this.confOut2 = confOut2;
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

    public String getUdt2() {
        return udt2;
    }

    public void setUdt2(String udt2) {
        this.udt2 = udt2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjMonthDTO)) return false;
        BidPrjMonthDTO that = (BidPrjMonthDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(month, that.month) && Objects.equals(syr, that.syr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, month, syr);
    }

    public String getIdString(){
        return this.wkut+"-"+ this.prjno+"-"+this.syr +"-"+ this.month;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjMonthDTO{" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", yr='" + getYr() + "'" +
            ", month='" + getMonth() + "'" +
            ", syr='" + getSyr() + "'" +
            ", item='" + getItem() + "'" +
            ", ptrate=" + getPtrate() +
            ", pexp=" + getPexp() +
            ", rate=" + getRate() +
            ", ypexp=" + getYpexp() +
            ", yaexp=" + getYaexp() +
            ", arat=" + getArat() +
            ", aexp=" + getAexp() +
            ", atrate=" + getAtrate() +
            ", remark='" + getRemark() + "'" +
            ", sumt=" + getSumt() +
            ", stus='" + getStus() + "'" +
            ", mdsts='" + getMdsts() + "'" +
            ", sumdat='" + getSumdat() + "'" +
            ", nopayamt=" + getNopayamt() +
            ", nopayresn='" + getNopayresn() + "'" +
            ", resumt=" + getResumt() +
            ", trdt='" + getTrdt() + "'" +
            ", nopaydetel='" + getNopaydetel() + "'" +
            ", meetCnt=" + getMeetCnt() +
            ", meterChk=" + getMeterChk() +
            ", meterChkF=" + getMeterChkF() +
            ", workChk=" + getWorkChk() +
            ", workChkF=" + getWorkChkF() +
            ", replMeth='" + getReplMeth() + "'" +
            ", teckind3=" + getTeckind3() +
            ", enviCnt=" + getEnviCnt() +
            ", enviFee=" + getEnviFee() +
            ", wsafeCnt=" + getWsafeCnt() +
            ", wsafeFee=" + getWsafeFee() +
            ", sorc1=" + getSorc1() +
            ", sorc1b=" + getSorc1b() +
            ", sorc1c=" + getSorc1c() +
            ", sorc1d=" + getSorc1d() +
            ", sorc2=" + getSorc2() +
            ", sorc3=" + getSorc3() +
            ", sorc4=" + getSorc4() +
            ", sorc5=" + getSorc5() +
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
            ", equp01='" + getEqup01() + "'" +
            ", equp02='" + getEqup02() + "'" +
            ", equp03='" + getEqup03() + "'" +
            ", equp04='" + getEqup04() + "'" +
            ", equp05='" + getEqup05() + "'" +
            ", equp06='" + getEqup06() + "'" +
            ", equp07='" + getEqup07() + "'" +
            ", equp08='" + getEqup08() + "'" +
            ", equp09='" + getEqup09() + "'" +
            ", equp10='" + getEqup10() + "'" +
            ", equp11='" + getEqup11() + "'" +
            ", cosend=" + getCosend() +
            ", atrate=" + getAtrate() +
            ", wdayeds=" + getWdayeds() +
            ", sorc3b=" + getSorc3b() +
            ", sorc3c=" + getSorc3c() +
            ", sorc3d=" + getSorc3d() +
            ", sorc3e=" + getSorc3e() +
            ", sorc3a=" + getSorc3a() +
            ", sorc6=" + getSorc6() +
            ", sorc3b2=" + getSorc3b2() +
            ", sorc3b3=" + getSorc3b3() +
            ", sorc3b4=" + getSorc3b4() +
            ", sorc3b5=" + getSorc3b5() +
            ", sorc3c1=" + getSorc3c1() +
            ", sorc3d1=" + getSorc3d1() +
            ", sorc3d3=" + getSorc3d3() +
            ", sorc3d4=" + getSorc3d4() +
            ", teckind4=" + getTeckind4() +
            ", sorc7=" + getSorc7() +
            ", sorc7use=" + getSorc7use() +
            ", sorc7oth=" + getSorc7oth() +
            ", sorc7tac=" + getSorc7tac() +
            ", sorc7self=" + getSorc7self() +
            ", sorc8=" + getSorc8() +
            ", payArr='" + getPayArr() + "'" +
            ", sorc7use2=" + getSorc7use2() +
            ", sorc7use3=" + getSorc7use3() +
            ", sfrWkr=" + getSfrWkr() +
            ", sorc9=" + getSorc9() +
            ", dsts='" + getDsts() + "'" +
            ", confOut=" + getConfOut() +
            ", confOut2=" + getConfOut2() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            ", udt2='" + getUdt2() + "'" +
            "}";
    }
}
