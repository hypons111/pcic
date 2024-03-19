package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPrjMonth.
 */
@Entity
@IdClass(BidPrjMonthPrimaryKey.class)
@Table(name = "bid_prj_month")
public class BidPrjMonth implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "wkut", length = 21, nullable = false)
    private String wkut;

    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "prjno", length = 20, nullable = false)
    private String prjno;

    @NotNull
    @Size(max = 3)
    @Column(name = "yr", length = 3, nullable = false)
    private String yr;

    @Id
    @NotNull
    @Size(max = 2)
    @Column(name = "month", length = 2, nullable = false)
    private String month;

    @Id
    @NotNull
    @Size(max = 3)
    @Column(name = "syr", length = 3, nullable = false)
    private String syr;

    @Column(name = "ypexp", precision = 15, scale = 3)
    private BigDecimal ypexp;

    @Column(name = "rate", precision = 15, scale = 3)
    private BigDecimal rate;

    @Column(name = "pexp", precision = 15, scale = 3)
    private BigDecimal pexp;

    @Column(name = "ptrate", precision = 15, scale = 3)
    private BigDecimal ptrate;

    @Size(max = 2000)
    @Column(name = "item", length = 2000)
    private String item;

    @Column(name = "yaexp", precision = 15, scale = 3)
    private BigDecimal yaexp;

    @Column(name = "arat", precision = 15, scale = 3)
    private BigDecimal arat;

    @Column(name = "aexp", precision = 15, scale = 3)
    private BigDecimal aexp;

    @Column(name = "atrate", precision = 15, scale = 3)
    private BigDecimal atrate;

    @Size(max = 2000)
    @Column(name = "remark", length = 2000)
    private String remark;

    @Column(name = "sumt", precision = 15, scale = 3)
    private BigDecimal sumt;

    @Size(max = 2)
    @Column(name = "stus", length = 2)
    private String stus;

    @Size(max = 1)
    @Column(name = "mdsts", length = 1)
    private String mdsts;

    @Size(max = 7)
    @Column(name = "sumdat", length = 7)
    private String sumdat;

    @Column(name = "nopayamt", precision = 15, scale = 3)
    private BigDecimal nopayamt;

    @Size(max = 2)
    @Column(name = "nopayresn", length = 2)
    private String nopayresn;

    @Column(name = "resumt", precision = 15, scale = 3)
    private BigDecimal resumt;

    @Column(name = "trdt")
    private LocalDate trdt;

    @Size(max = 2000)
    @Column(name = "nopaydetel", length = 2000)
    private String nopaydetel;

    @Column(name = "meet_cnt", precision = 15, scale = 3)
    private BigDecimal meetCnt;

    @Column(name = "meter_chk", precision = 15, scale = 3)
    private BigDecimal meterChk;

    @Column(name = "meter_chk_f", precision = 15, scale = 3)
    private BigDecimal meterChkF;

    @Column(name = "work_chk", precision = 15, scale = 3)
    private BigDecimal workChk;

    @Column(name = "work_chk_f", precision = 15, scale = 3)
    private BigDecimal workChkF;

    @Size(max = 2)
    @Column(name = "repl_meth", length = 2)
    private String replMeth;

    @Column(name = "teckind3", precision = 15, scale = 3)
    private BigDecimal teckind3;

    @Column(name = "envi_cnt", precision = 15, scale = 3)
    private BigDecimal enviCnt;

    @Column(name = "envi_fee", precision = 15, scale = 3)
    private BigDecimal enviFee;

    @Column(name = "wsafe_cnt", precision = 15, scale = 3)
    private BigDecimal wsafeCnt;

    @Column(name = "wsafe_fee", precision = 15, scale = 3)
    private BigDecimal wsafeFee;

    @Column(name = "sorc1", precision = 15, scale = 3)
    private BigDecimal sorc1;

    @Column(name = "sorc1b", precision = 15, scale = 3)
    private BigDecimal sorc1b;

    @Column(name = "sorc1c", precision = 15, scale = 3)
    private BigDecimal sorc1c;

    @Column(name = "sorc1d", precision = 15, scale = 3)
    private BigDecimal sorc1d;

    @Column(name = "sorc2", precision = 15, scale = 3)
    private BigDecimal sorc2;

    @Column(name = "sorc3", precision = 15, scale = 3)
    private BigDecimal sorc3;

    @Column(name = "sorc4", precision = 15, scale = 3)
    private BigDecimal sorc4;

    @Column(name = "sorc5", precision = 15, scale = 3)
    private BigDecimal sorc5;

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

    @Size(max = 1)
    @Column(name = "equp01", length = 1)
    private String equp01;

    @Size(max = 1)
    @Column(name = "equp02", length = 1)
    private String equp02;

    @Size(max = 1)
    @Column(name = "equp03", length = 1)
    private String equp03;

    @Size(max = 1)
    @Column(name = "equp04", length = 1)
    private String equp04;

    @Size(max = 1)
    @Column(name = "equp05", length = 1)
    private String equp05;

    @Size(max = 1)
    @Column(name = "equp06", length = 1)
    private String equp06;

    @Size(max = 1)
    @Column(name = "equp07", length = 1)
    private String equp07;

    @Size(max = 1)
    @Column(name = "equp08", length = 1)
    private String equp08;

    @Size(max = 1)
    @Column(name = "equp09", length = 1)
    private String equp09;

    @Size(max = 1)
    @Column(name = "equp10", length = 1)
    private String equp10;

    @Size(max = 1)
    @Column(name = "equp11", length = 1)
    private String equp11;

    @Column(name = "cosend", precision = 15, scale = 3)
    private BigDecimal cosend;

    @Column(name = "a_trate", precision = 15, scale = 3)
    private BigDecimal aTrate;

    @Column(name = "wdayeds")
    private Integer wdayeds;

    @Column(name = "sorc3b", precision = 15, scale = 3)
    private BigDecimal sorc3b;

    @Column(name = "sorc3c", precision = 15, scale = 3)
    private BigDecimal sorc3c;

    @Column(name = "sorc3d", precision = 15, scale = 3)
    private BigDecimal sorc3d;

    @Column(name = "sorc3e", precision = 15, scale = 3)
    private BigDecimal sorc3e;

    @Column(name = "sorc3a", precision = 15, scale = 3)
    private BigDecimal sorc3a;

    @Column(name = "sorc6", precision = 15, scale = 3)
    private BigDecimal sorc6;

    @Column(name = "sorc3b2", precision = 15, scale = 3)
    private BigDecimal sorc3b2;

    @Column(name = "sorc3b3", precision = 15, scale = 3)
    private BigDecimal sorc3b3;

    @Column(name = "sorc3b4", precision = 15, scale = 3)
    private BigDecimal sorc3b4;

    @Column(name = "sorc3b5", precision = 15, scale = 3)
    private BigDecimal sorc3b5;

    @Column(name = "sorc3c1", precision = 15, scale = 3)
    private BigDecimal sorc3c1;

    @Column(name = "sorc3d1", precision = 15, scale = 3)
    private BigDecimal sorc3d1;

    @Column(name = "sorc3d3", precision = 15, scale = 3)
    private BigDecimal sorc3d3;

    @Column(name = "sorc3d4", precision = 15, scale = 3)
    private BigDecimal sorc3d4;

    @Column(name = "teckind4", precision = 15, scale = 3)
    private BigDecimal teckind4;

    @Column(name = "sorc7", precision = 15, scale = 3)
    private BigDecimal sorc7;

    @Column(name = "sorc7use", precision = 15, scale = 3)
    private BigDecimal sorc7use;

    @Column(name = "sorc7oth", precision = 15, scale = 3)
    private BigDecimal sorc7oth;

    @Column(name = "sorc7tac", precision = 15, scale = 3)
    private BigDecimal sorc7tac;

    @Column(name = "sorc7self", precision = 15, scale = 3)
    private BigDecimal sorc7self;

    @Column(name = "sorc8", precision = 15, scale = 3)
    private BigDecimal sorc8;

    @Size(max = 1)
    @Column(name = "pay_arr", length = 1)
    private String payArr;

    @Column(name = "sorc7use2", precision = 15, scale = 3)
    private BigDecimal sorc7use2;

    @Column(name = "sorc7use3", precision = 15, scale = 3)
    private BigDecimal sorc7use3;

    @Column(name = "sfr_wkr", precision = 15, scale = 3)
    private BigDecimal sfrWkr;

    @Column(name = "sorc9", precision = 15, scale = 3)
    private BigDecimal sorc9;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Column(name = "conf_out", precision = 15, scale = 3)
    private BigDecimal confOut;

    @Column(name = "conf_out2", precision = 15, scale = 3)
    private BigDecimal confOut2;

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

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 7)
    @Column(name = "udt2", length = 7)
    private String udt2;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidPrjMonthPrimaryKey getId(){
        return new BidPrjMonthPrimaryKey(wkut,prjno, yr, month);
    }

    public void setId(BidPrjMonthPrimaryKey id){
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.yr = id.getSyr();
        this.month = id.getMonth();    }

    public String getWkut() {
        return this.wkut;
    }

    public BidPrjMonth wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidPrjMonth prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getYr() {
        return this.yr;
    }

    public BidPrjMonth year(String year) {
        this.setYr(year);
        return this;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getMonth() {
        return this.month;
    }

    public BidPrjMonth month(String month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSyr() {
        return this.syr;
    }

    public BidPrjMonth syr(String syr) {
        this.setSyr(syr);
        return this;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public BigDecimal getYpexp() {
        return this.ypexp;
    }

    public BidPrjMonth ypexp(BigDecimal ypexp) {
        this.setYpexp(ypexp);
        return this;
    }

    public void setYpexp(BigDecimal ypexp) {
        this.ypexp = ypexp;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public BidPrjMonth rate(BigDecimal rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getPexp() {
        return this.pexp;
    }

    public BidPrjMonth pexp(BigDecimal pexp) {
        this.setPexp(pexp);
        return this;
    }

    public void setPexp(BigDecimal pexp) {
        this.pexp = pexp;
    }

    public BigDecimal getPtrate() {
        return this.ptrate;
    }

    public BidPrjMonth ptrate(BigDecimal ptrate) {
        this.setPtrate(ptrate);
        return this;
    }

    public void setPtrate(BigDecimal ptrate) {
        this.ptrate = ptrate;
    }

    public String getItem() {
        return this.item;
    }

    public BidPrjMonth item(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getYaexp() {
        return this.yaexp;
    }

    public BidPrjMonth yaexp(BigDecimal yaexp) {
        this.setYaexp(yaexp);
        return this;
    }

    public void setYaexp(BigDecimal yaexp) {
        this.yaexp = yaexp;
    }

    public BigDecimal getArat() {
        return this.arat;
    }

    public BidPrjMonth arat(BigDecimal arat) {
        this.setArat(arat);
        return this;
    }

    public void setArat(BigDecimal arat) {
        this.arat = arat;
    }

    public BigDecimal getAexp() {
        return this.aexp;
    }

    public BidPrjMonth aexp(BigDecimal aexp) {
        this.setAexp(aexp);
        return this;
    }

    public void setAexp(BigDecimal aexp) {
        this.aexp = aexp;
    }

    public BigDecimal getAtrate() {
        return this.atrate;
    }

    public BidPrjMonth atrate(BigDecimal atrate) {
        this.setAtrate(atrate);
        return this;
    }

    public void setAtrate(BigDecimal atrate) {
        this.atrate = atrate;
    }

    public String getRemark() {
        return this.remark;
    }

    public BidPrjMonth remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSumt() {
        return this.sumt;
    }

    public BidPrjMonth sumt(BigDecimal sumt) {
        this.setSumt(sumt);
        return this;
    }

    public void setSumt(BigDecimal sumt) {
        this.sumt = sumt;
    }

    public String getStus() {
        return this.stus;
    }

    public BidPrjMonth stus(String stus) {
        this.setStus(stus);
        return this;
    }

    public void setStus(String stus) {
        this.stus = stus;
    }

    public String getMdsts() {
        return this.mdsts;
    }

    public BidPrjMonth mdsts(String mdsts) {
        this.setMdsts(mdsts);
        return this;
    }

    public void setMdsts(String mdsts) {
        this.mdsts = mdsts;
    }

    public String getSumdat() {
        return this.sumdat;
    }

    public BidPrjMonth sumdat(String sumdat) {
        this.setSumdat(sumdat);
        return this;
    }

    public void setSumdat(String sumdat) {
        this.sumdat = sumdat;
    }

    public BigDecimal getNopayamt() {
        return this.nopayamt;
    }

    public BidPrjMonth nopayamt(BigDecimal nopayamt) {
        this.setNopayamt(nopayamt);
        return this;
    }

    public void setNopayamt(BigDecimal nopayamt) {
        this.nopayamt = nopayamt;
    }

    public String getNopayresn() {
        return this.nopayresn;
    }

    public BidPrjMonth nopayresn(String nopayresn) {
        this.setNopayresn(nopayresn);
        return this;
    }

    public void setNopayresn(String nopayresn) {
        this.nopayresn = nopayresn;
    }

    public BigDecimal getResumt() {
        return this.resumt;
    }

    public BidPrjMonth resumt(BigDecimal resumt) {
        this.setResumt(resumt);
        return this;
    }

    public void setResumt(BigDecimal resumt) {
        this.resumt = resumt;
    }

    public LocalDate getTrdt() {
        return this.trdt;
    }

    public BidPrjMonth trdt(LocalDate trdt) {
        this.setTrdt(trdt);
        return this;
    }

    public void setTrdt(LocalDate trdt) {
        this.trdt = trdt;
    }

    public String getNopaydetel() {
        return this.nopaydetel;
    }

    public BidPrjMonth nopaydetel(String nopaydetel) {
        this.setNopaydetel(nopaydetel);
        return this;
    }

    public void setNopaydetel(String nopaydetel) {
        this.nopaydetel = nopaydetel;
    }

    public BigDecimal getMeetCnt() {
        return this.meetCnt;
    }

    public BidPrjMonth meetCnt(BigDecimal meetCnt) {
        this.setMeetCnt(meetCnt);
        return this;
    }

    public void setMeetCnt(BigDecimal meetCnt) {
        this.meetCnt = meetCnt;
    }

    public BigDecimal getMeterChk() {
        return this.meterChk;
    }

    public BidPrjMonth meterChk(BigDecimal meterChk) {
        this.setMeterChk(meterChk);
        return this;
    }

    public void setMeterChk(BigDecimal meterChk) {
        this.meterChk = meterChk;
    }

    public BigDecimal getMeterChkF() {
        return this.meterChkF;
    }

    public BidPrjMonth meterChkF(BigDecimal meterChkF) {
        this.setMeterChkF(meterChkF);
        return this;
    }

    public void setMeterChkF(BigDecimal meterChkF) {
        this.meterChkF = meterChkF;
    }

    public BigDecimal getWorkChk() {
        return this.workChk;
    }

    public BidPrjMonth workChk(BigDecimal workChk) {
        this.setWorkChk(workChk);
        return this;
    }

    public void setWorkChk(BigDecimal workChk) {
        this.workChk = workChk;
    }

    public BigDecimal getWorkChkF() {
        return this.workChkF;
    }

    public BidPrjMonth workChkF(BigDecimal workChkF) {
        this.setWorkChkF(workChkF);
        return this;
    }

    public void setWorkChkF(BigDecimal workChkF) {
        this.workChkF = workChkF;
    }

    public String getReplMeth() {
        return this.replMeth;
    }

    public BidPrjMonth replMeth(String replMeth) {
        this.setReplMeth(replMeth);
        return this;
    }

    public void setReplMeth(String replMeth) {
        this.replMeth = replMeth;
    }

    public BigDecimal getTeckind3() {
        return this.teckind3;
    }

    public BidPrjMonth teckind3(BigDecimal teckind3) {
        this.setTeckind3(teckind3);
        return this;
    }

    public void setTeckind3(BigDecimal teckind3) {
        this.teckind3 = teckind3;
    }

    public BigDecimal getEnviCnt() {
        return this.enviCnt;
    }

    public BidPrjMonth enviCnt(BigDecimal enviCnt) {
        this.setEnviCnt(enviCnt);
        return this;
    }

    public void setEnviCnt(BigDecimal enviCnt) {
        this.enviCnt = enviCnt;
    }

    public BigDecimal getEnviFee() {
        return this.enviFee;
    }

    public BidPrjMonth enviFee(BigDecimal enviFee) {
        this.setEnviFee(enviFee);
        return this;
    }

    public void setEnviFee(BigDecimal enviFee) {
        this.enviFee = enviFee;
    }

    public BigDecimal getWsafeCnt() {
        return this.wsafeCnt;
    }

    public BidPrjMonth wsafeCnt(BigDecimal wsafeCnt) {
        this.setWsafeCnt(wsafeCnt);
        return this;
    }

    public void setWsafeCnt(BigDecimal wsafeCnt) {
        this.wsafeCnt = wsafeCnt;
    }

    public BigDecimal getWsafeFee() {
        return this.wsafeFee;
    }

    public BidPrjMonth wsafeFee(BigDecimal wsafeFee) {
        this.setWsafeFee(wsafeFee);
        return this;
    }

    public void setWsafeFee(BigDecimal wsafeFee) {
        this.wsafeFee = wsafeFee;
    }

    public BigDecimal getSorc1() {
        return this.sorc1;
    }

    public BidPrjMonth sorc1(BigDecimal sorc1) {
        this.setSorc1(sorc1);
        return this;
    }

    public void setSorc1(BigDecimal sorc1) {
        this.sorc1 = sorc1;
    }

    public BigDecimal getSorc1b() {
        return this.sorc1b;
    }

    public BidPrjMonth sorc1b(BigDecimal sorc1b) {
        this.setSorc1b(sorc1b);
        return this;
    }

    public void setSorc1b(BigDecimal sorc1b) {
        this.sorc1b = sorc1b;
    }

    public BigDecimal getSorc1c() {
        return this.sorc1c;
    }

    public BidPrjMonth sorc1c(BigDecimal sorc1c) {
        this.setSorc1c(sorc1c);
        return this;
    }

    public void setSorc1c(BigDecimal sorc1c) {
        this.sorc1c = sorc1c;
    }

    public BigDecimal getSorc1d() {
        return this.sorc1d;
    }

    public BidPrjMonth sorc1d(BigDecimal sorc1d) {
        this.setSorc1d(sorc1d);
        return this;
    }

    public void setSorc1d(BigDecimal sorc1d) {
        this.sorc1d = sorc1d;
    }

    public BigDecimal getSorc2() {
        return this.sorc2;
    }

    public BidPrjMonth sorc2(BigDecimal sorc2) {
        this.setSorc2(sorc2);
        return this;
    }

    public void setSorc2(BigDecimal sorc2) {
        this.sorc2 = sorc2;
    }

    public BigDecimal getSorc3() {
        return this.sorc3;
    }

    public BidPrjMonth sorc3(BigDecimal sorc3) {
        this.setSorc3(sorc3);
        return this;
    }

    public void setSorc3(BigDecimal sorc3) {
        this.sorc3 = sorc3;
    }

    public BigDecimal getSorc4() {
        return this.sorc4;
    }

    public BidPrjMonth sorc4(BigDecimal sorc4) {
        this.setSorc4(sorc4);
        return this;
    }

    public void setSorc4(BigDecimal sorc4) {
        this.sorc4 = sorc4;
    }

    public BigDecimal getSorc5() {
        return this.sorc5;
    }

    public BidPrjMonth sorc5(BigDecimal sorc5) {
        this.setSorc5(sorc5);
        return this;
    }

    public void setSorc5(BigDecimal sorc5) {
        this.sorc5 = sorc5;
    }

    public String getSpcw01() {
        return this.spcw01;
    }

    public BidPrjMonth spcw01(String spcw01) {
        this.setSpcw01(spcw01);
        return this;
    }

    public void setSpcw01(String spcw01) {
        this.spcw01 = spcw01;
    }

    public String getSpcw02() {
        return this.spcw02;
    }

    public BidPrjMonth spcw02(String spcw02) {
        this.setSpcw02(spcw02);
        return this;
    }

    public void setSpcw02(String spcw02) {
        this.spcw02 = spcw02;
    }

    public String getSpcw03() {
        return this.spcw03;
    }

    public BidPrjMonth spcw03(String spcw03) {
        this.setSpcw03(spcw03);
        return this;
    }

    public void setSpcw03(String spcw03) {
        this.spcw03 = spcw03;
    }

    public String getSpcw04() {
        return this.spcw04;
    }

    public BidPrjMonth spcw04(String spcw04) {
        this.setSpcw04(spcw04);
        return this;
    }

    public void setSpcw04(String spcw04) {
        this.spcw04 = spcw04;
    }

    public String getSpcw05() {
        return this.spcw05;
    }

    public BidPrjMonth spcw05(String spcw05) {
        this.setSpcw05(spcw05);
        return this;
    }

    public void setSpcw05(String spcw05) {
        this.spcw05 = spcw05;
    }

    public String getSpcw06() {
        return this.spcw06;
    }

    public BidPrjMonth spcw06(String spcw06) {
        this.setSpcw06(spcw06);
        return this;
    }

    public void setSpcw06(String spcw06) {
        this.spcw06 = spcw06;
    }

    public String getSpcw07() {
        return this.spcw07;
    }

    public BidPrjMonth spcw07(String spcw07) {
        this.setSpcw07(spcw07);
        return this;
    }

    public void setSpcw07(String spcw07) {
        this.spcw07 = spcw07;
    }

    public String getSpcw08() {
        return this.spcw08;
    }

    public BidPrjMonth spcw08(String spcw08) {
        this.setSpcw08(spcw08);
        return this;
    }

    public void setSpcw08(String spcw08) {
        this.spcw08 = spcw08;
    }

    public String getSpcw09() {
        return this.spcw09;
    }

    public BidPrjMonth spcw09(String spcw09) {
        this.setSpcw09(spcw09);
        return this;
    }

    public void setSpcw09(String spcw09) {
        this.spcw09 = spcw09;
    }

    public String getSpcw10() {
        return this.spcw10;
    }

    public BidPrjMonth spcw10(String spcw10) {
        this.setSpcw10(spcw10);
        return this;
    }

    public void setSpcw10(String spcw10) {
        this.spcw10 = spcw10;
    }

    public String getSpcw11() {
        return this.spcw11;
    }

    public BidPrjMonth spcw11(String spcw11) {
        this.setSpcw11(spcw11);
        return this;
    }

    public void setSpcw11(String spcw11) {
        this.spcw11 = spcw11;
    }

    public String getSpcw12() {
        return this.spcw12;
    }

    public BidPrjMonth spcw12(String spcw12) {
        this.setSpcw12(spcw12);
        return this;
    }

    public void setSpcw12(String spcw12) {
        this.spcw12 = spcw12;
    }

    public String getEqup01() {
        return this.equp01;
    }

    public BidPrjMonth equp01(String equp01) {
        this.setEqup01(equp01);
        return this;
    }

    public void setEqup01(String equp01) {
        this.equp01 = equp01;
    }

    public String getEqup02() {
        return this.equp02;
    }

    public BidPrjMonth equp02(String equp02) {
        this.setEqup02(equp02);
        return this;
    }

    public void setEqup02(String equp02) {
        this.equp02 = equp02;
    }

    public String getEqup03() {
        return this.equp03;
    }

    public BidPrjMonth equp03(String equp03) {
        this.setEqup03(equp03);
        return this;
    }

    public void setEqup03(String equp03) {
        this.equp03 = equp03;
    }

    public String getEqup04() {
        return this.equp04;
    }

    public BidPrjMonth equp04(String equp04) {
        this.setEqup04(equp04);
        return this;
    }

    public void setEqup04(String equp04) {
        this.equp04 = equp04;
    }

    public String getEqup05() {
        return this.equp05;
    }

    public BidPrjMonth equp05(String equp05) {
        this.setEqup05(equp05);
        return this;
    }

    public void setEqup05(String equp05) {
        this.equp05 = equp05;
    }

    public String getEqup06() {
        return this.equp06;
    }

    public BidPrjMonth equp06(String equp06) {
        this.setEqup06(equp06);
        return this;
    }

    public void setEqup06(String equp06) {
        this.equp06 = equp06;
    }

    public String getEqup07() {
        return this.equp07;
    }

    public BidPrjMonth equp07(String equp07) {
        this.setEqup07(equp07);
        return this;
    }

    public void setEqup07(String equp07) {
        this.equp07 = equp07;
    }

    public String getEqup08() {
        return this.equp08;
    }

    public BidPrjMonth equp08(String equp08) {
        this.setEqup08(equp08);
        return this;
    }

    public void setEqup08(String equp08) {
        this.equp08 = equp08;
    }

    public String getEqup09() {
        return this.equp09;
    }

    public BidPrjMonth equp09(String equp09) {
        this.setEqup09(equp09);
        return this;
    }

    public void setEqup09(String equp09) {
        this.equp09 = equp09;
    }

    public String getEqup10() {
        return this.equp10;
    }

    public BidPrjMonth equp10(String equp10) {
        this.setEqup10(equp10);
        return this;
    }

    public void setEqup10(String equp10) {
        this.equp10 = equp10;
    }

    public String getEqup11() {
        return this.equp11;
    }

    public BidPrjMonth equp11(String equp11) {
        this.setEqup11(equp11);
        return this;
    }

    public void setEqup11(String equp11) {
        this.equp11 = equp11;
    }

    public BigDecimal getCosend() {
        return this.cosend;
    }

    public BidPrjMonth cosend(BigDecimal cosend) {
        this.setCosend(cosend);
        return this;
    }

    public void setCosend(BigDecimal cosend) {
        this.cosend = cosend;
    }

    public BigDecimal getaTrate() {
        return this.aTrate;
    }

    public BidPrjMonth aTrate(BigDecimal aTrate) {
        this.setaTrate(aTrate);
        return this;
    }

    public void setaTrate(BigDecimal aTrate) {
        this.aTrate = aTrate;
    }

    public Integer getWdayeds() {
        return this.wdayeds;
    }

    public BidPrjMonth wdayeds(Integer wdayeds) {
        this.setWdayeds(wdayeds);
        return this;
    }

    public void setWdayeds(Integer wdayeds) {
        this.wdayeds = wdayeds;
    }

    public BigDecimal getSorc3b() {
        return this.sorc3b;
    }

    public BidPrjMonth sorc3b(BigDecimal sorc3b) {
        this.setSorc3b(sorc3b);
        return this;
    }

    public void setSorc3b(BigDecimal sorc3b) {
        this.sorc3b = sorc3b;
    }

    public BigDecimal getSorc3c() {
        return this.sorc3c;
    }

    public BidPrjMonth sorc3c(BigDecimal sorc3c) {
        this.setSorc3c(sorc3c);
        return this;
    }

    public void setSorc3c(BigDecimal sorc3c) {
        this.sorc3c = sorc3c;
    }

    public BigDecimal getSorc3d() {
        return this.sorc3d;
    }

    public BidPrjMonth sorc3d(BigDecimal sorc3d) {
        this.setSorc3d(sorc3d);
        return this;
    }

    public void setSorc3d(BigDecimal sorc3d) {
        this.sorc3d = sorc3d;
    }

    public BigDecimal getSorc3e() {
        return this.sorc3e;
    }

    public BidPrjMonth sorc3e(BigDecimal sorc3e) {
        this.setSorc3e(sorc3e);
        return this;
    }

    public void setSorc3e(BigDecimal sorc3e) {
        this.sorc3e = sorc3e;
    }

    public BigDecimal getSorc3a() {
        return this.sorc3a;
    }

    public BidPrjMonth sorc3a(BigDecimal sorc3a) {
        this.setSorc3a(sorc3a);
        return this;
    }

    public void setSorc3a(BigDecimal sorc3a) {
        this.sorc3a = sorc3a;
    }

    public BigDecimal getSorc6() {
        return this.sorc6;
    }

    public BidPrjMonth sorc6(BigDecimal sorc6) {
        this.setSorc6(sorc6);
        return this;
    }

    public void setSorc6(BigDecimal sorc6) {
        this.sorc6 = sorc6;
    }

    public BigDecimal getSorc3b2() {
        return this.sorc3b2;
    }

    public BidPrjMonth sorc3b2(BigDecimal sorc3b2) {
        this.setSorc3b2(sorc3b2);
        return this;
    }

    public void setSorc3b2(BigDecimal sorc3b2) {
        this.sorc3b2 = sorc3b2;
    }

    public BigDecimal getSorc3b3() {
        return this.sorc3b3;
    }

    public BidPrjMonth sorc3b3(BigDecimal sorc3b3) {
        this.setSorc3b3(sorc3b3);
        return this;
    }

    public void setSorc3b3(BigDecimal sorc3b3) {
        this.sorc3b3 = sorc3b3;
    }

    public BigDecimal getSorc3b4() {
        return this.sorc3b4;
    }

    public BidPrjMonth sorc3b4(BigDecimal sorc3b4) {
        this.setSorc3b4(sorc3b4);
        return this;
    }

    public void setSorc3b4(BigDecimal sorc3b4) {
        this.sorc3b4 = sorc3b4;
    }

    public BigDecimal getSorc3b5() {
        return this.sorc3b5;
    }

    public BidPrjMonth sorc3b5(BigDecimal sorc3b5) {
        this.setSorc3b5(sorc3b5);
        return this;
    }

    public void setSorc3b5(BigDecimal sorc3b5) {
        this.sorc3b5 = sorc3b5;
    }

    public BigDecimal getSorc3c1() {
        return this.sorc3c1;
    }

    public BidPrjMonth sorc3c1(BigDecimal sorc3c1) {
        this.setSorc3c1(sorc3c1);
        return this;
    }

    public void setSorc3c1(BigDecimal sorc3c1) {
        this.sorc3c1 = sorc3c1;
    }

    public BigDecimal getSorc3d1() {
        return this.sorc3d1;
    }

    public BidPrjMonth sorc3d1(BigDecimal sorc3d1) {
        this.setSorc3d1(sorc3d1);
        return this;
    }

    public void setSorc3d1(BigDecimal sorc3d1) {
        this.sorc3d1 = sorc3d1;
    }

    public BigDecimal getSorc3d3() {
        return this.sorc3d3;
    }

    public BidPrjMonth sorc3d3(BigDecimal sorc3d3) {
        this.setSorc3d3(sorc3d3);
        return this;
    }

    public void setSorc3d3(BigDecimal sorc3d3) {
        this.sorc3d3 = sorc3d3;
    }

    public BigDecimal getSorc3d4() {
        return this.sorc3d4;
    }

    public BidPrjMonth sorc3d4(BigDecimal sorc3d4) {
        this.setSorc3d4(sorc3d4);
        return this;
    }

    public void setSorc3d4(BigDecimal sorc3d4) {
        this.sorc3d4 = sorc3d4;
    }

    public BigDecimal getTeckind4() {
        return this.teckind4;
    }

    public BidPrjMonth teckind4(BigDecimal teckind4) {
        this.setTeckind4(teckind4);
        return this;
    }

    public void setTeckind4(BigDecimal teckind4) {
        this.teckind4 = teckind4;
    }

    public BigDecimal getSorc7() {
        return this.sorc7;
    }

    public BidPrjMonth sorc7(BigDecimal sorc7) {
        this.setSorc7(sorc7);
        return this;
    }

    public void setSorc7(BigDecimal sorc7) {
        this.sorc7 = sorc7;
    }

    public BigDecimal getSorc7use() {
        return this.sorc7use;
    }

    public BidPrjMonth sorc7use(BigDecimal sorc7use) {
        this.setSorc7use(sorc7use);
        return this;
    }

    public void setSorc7use(BigDecimal sorc7use) {
        this.sorc7use = sorc7use;
    }

    public BigDecimal getSorc7oth() {
        return this.sorc7oth;
    }

    public BidPrjMonth sorc7oth(BigDecimal sorc7oth) {
        this.setSorc7oth(sorc7oth);
        return this;
    }

    public void setSorc7oth(BigDecimal sorc7oth) {
        this.sorc7oth = sorc7oth;
    }

    public BigDecimal getSorc7tac() {
        return this.sorc7tac;
    }

    public BidPrjMonth sorc7tac(BigDecimal sorc7tac) {
        this.setSorc7tac(sorc7tac);
        return this;
    }

    public void setSorc7tac(BigDecimal sorc7tac) {
        this.sorc7tac = sorc7tac;
    }

    public BigDecimal getSorc7self() {
        return this.sorc7self;
    }

    public BidPrjMonth sorc7self(BigDecimal sorc7self) {
        this.setSorc7self(sorc7self);
        return this;
    }

    public void setSorc7self(BigDecimal sorc7self) {
        this.sorc7self = sorc7self;
    }

    public BigDecimal getSorc8() {
        return this.sorc8;
    }

    public BidPrjMonth sorc8(BigDecimal sorc8) {
        this.setSorc8(sorc8);
        return this;
    }

    public void setSorc8(BigDecimal sorc8) {
        this.sorc8 = sorc8;
    }

    public String getPayArr() {
        return this.payArr;
    }

    public BidPrjMonth payArr(String payArr) {
        this.setPayArr(payArr);
        return this;
    }

    public void setPayArr(String payArr) {
        this.payArr = payArr;
    }

    public BigDecimal getSorc7use2() {
        return this.sorc7use2;
    }

    public BidPrjMonth sorc7use2(BigDecimal sorc7use2) {
        this.setSorc7use2(sorc7use2);
        return this;
    }

    public void setSorc7use2(BigDecimal sorc7use2) {
        this.sorc7use2 = sorc7use2;
    }

    public BigDecimal getSorc7use3() {
        return this.sorc7use3;
    }

    public BidPrjMonth sorc7use3(BigDecimal sorc7use3) {
        this.setSorc7use3(sorc7use3);
        return this;
    }

    public void setSorc7use3(BigDecimal sorc7use3) {
        this.sorc7use3 = sorc7use3;
    }

    public BigDecimal getSfrWkr() {
        return this.sfrWkr;
    }

    public BidPrjMonth sfrWkr(BigDecimal sfrWkr) {
        this.setSfrWkr(sfrWkr);
        return this;
    }

    public void setSfrWkr(BigDecimal sfrWkr) {
        this.sfrWkr = sfrWkr;
    }

    public BigDecimal getSorc9() {
        return this.sorc9;
    }

    public BidPrjMonth sorc9(BigDecimal sorc9) {
        this.setSorc9(sorc9);
        return this;
    }

    public void setSorc9(BigDecimal sorc9) {
        this.sorc9 = sorc9;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPrjMonth dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPrjMonth confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public BigDecimal getConfOut2() {
        return this.confOut2;
    }

    public BidPrjMonth confOut2(BigDecimal confOut2) {
        this.setConfOut2(confOut2);
        return this;
    }

    public void setConfOut2(BigDecimal confOut2) {
        this.confOut2 = confOut2;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPrjMonth createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPrjMonth createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPrjMonth updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPrjMonth updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPrjMonth uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getUdt2() {
        return this.udt2;
    }

    public BidPrjMonth udt2(String udt2) {
        this.setUdt2(udt2);
        return this;
    }

    public void setUdt2(String udt2) {
        this.udt2 = udt2;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidPrjMonth)) return false;
        BidPrjMonth that = (BidPrjMonth) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(month, that.month) && Objects.equals(syr, that.syr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, month, syr);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjMonth{" +
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
            ", aTrate=" + getAtrate() +
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
