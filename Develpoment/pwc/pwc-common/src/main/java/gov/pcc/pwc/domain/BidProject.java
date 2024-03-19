package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidProject.
 */
@Entity
@IdClass(BidProjectPrimaryKey.class)
@Table(name = "bid_project")
public class BidProject implements Serializable {

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

    @NotNull
    @Size(max = 180)
    @Column(name = "name", length = 180, nullable = false)
    private String name;

    @Size(max = 2)
    @Column(name = "is_add92", length = 2)
    private String isAdd92;

    @Size(max = 1)
    @Column(name = "pnt_ctrl", length = 1)
    private String pntCtrl;

    @Size(max = 2)
    @Column(name = "type_code", length = 2)
    private String typeCode;

    @Size(max = 1)
    @Column(name = "is_union", length = 1)
    private String isUnion;

    @Size(max = 4)
    @Column(name = "place_code", length = 4)
    private String placeCode;

    @Size(max = 100)
    @Column(name = "place", length = 100)
    private String place;

    @Size(max = 21)
    @Column(name = "bos_code", length = 21)
    private String bosCode;

    @Size(max = 21)
    @Column(name = "dep_code", length = 21)
    private String depCode;

    @Size(max = 1)
    @Column(name = "is_bg", length = 1)
    private String isBg;

    @Size(max = 21)
    @Column(name = "pln_code", length = 21)
    private String plnCode;

    @Size(max = 20)
    @Column(name = "pln_no", length = 20)
    private String plnNo;

    @Size(max = 30)
    @Column(name = "pln_unicode", length = 30)
    private String plnUnicode;

    @Size(max = 10)
    @Column(name = "bos2_code", length = 10)
    private String bos2Code;

    @Column(name = "bos2_rate", precision = 21, scale = 2)
    private BigDecimal bos2Rate;

    @Size(max = 21)
    @Column(name = "bos2b_code", length = 21)
    private String bos2bCode;

    @Column(name = "bos2b_rate", precision = 21, scale = 2)
    private BigDecimal bos2bRate;

    @Size(max = 21)
    @Column(name = "bos2c_code", length = 21)
    private String bos2cCode;

    @Column(name = "bos2c_rate", precision = 21, scale = 2)
    private BigDecimal bos2cRate;

    @Size(max = 21)
    @Column(name = "bos2d_code", length = 21)
    private String bos2dCode;

    @Column(name = "bos2d_rate", precision = 21, scale = 2)
    private BigDecimal bos2dRate;

    @Size(max = 40)
    @Column(name = "lnk_code", length = 40)
    private String lnkCode;

    @Size(max = 180)
    @Column(name = "ccnt2", length = 180)
    private String ccnt2;

    @Size(max = 80)
    @Column(name = "man", length = 80)
    private String man;

    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    @Size(max = 100)
    @Column(name = "e_mail", length = 100)
    private String eMail;

    @Column(name = "wrate", precision = 21, scale = 2)
    private BigDecimal wrate;

    @Size(max = 2000)
    @Column(name = "contain", length = 2000)
    private String contain;

    @Size(max = 1000)
    @Column(name = "pfunc", length = 1000)
    private String pfunc;

    @Size(max = 1000)
    @Column(name = "benf", length = 1000)
    private String benf;

    @Size(max = 1000)
    @Column(name = "pstand", length = 1000)
    private String pstand;

    @Size(max = 1000)
    @Column(name = "pqty", length = 1000)
    private String pqty;

    @Size(max = 2)
    @Column(name = "untnd", length = 2)
    private String untnd;

    @Size(max = 1)
    @Column(name = "is_spcw", length = 1)
    private String isSpcw;

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

    @Column(name = "spcw01_amt", precision = 21, scale = 2)
    private BigDecimal spcw01Amt;

    @Column(name = "spcw01_nm")
    private Integer spcw01Nm;

    @Column(name = "spcw02_amt", precision = 21, scale = 2)
    private BigDecimal spcw02Amt;

    @Column(name = "spcw02_nm")
    private Integer spcw02Nm;

    @Column(name = "spcw03_amt", precision = 21, scale = 2)
    private BigDecimal spcw03Amt;

    @Column(name = "spcw03_nm")
    private Integer spcw03Nm;

    @Column(name = "spcw04_amt", precision = 21, scale = 2)
    private BigDecimal spcw04Amt;

    @Column(name = "spcw04_nm")
    private Integer spcw04Nm;

    @Column(name = "spcw05_amt", precision = 21, scale = 2)
    private BigDecimal spcw05Amt;

    @Column(name = "spcw05_nm")
    private Integer spcw05Nm;

    @Column(name = "spcw06_amt", precision = 21, scale = 2)
    private BigDecimal spcw06Amt;

    @Column(name = "spcw06_nm")
    private Integer spcw06Nm;

    @Column(name = "spcw07_amt", precision = 21, scale = 2)
    private BigDecimal spcw07Amt;

    @Column(name = "spcw07_nm")
    private Integer spcw07Nm;

    @Column(name = "spcw08_amt", precision = 21, scale = 2)
    private BigDecimal spcw08Amt;

    @Column(name = "spcw08_nm")
    private Integer spcw08Nm;

    @Column(name = "spcw09_amt", precision = 21, scale = 2)
    private BigDecimal spcw09Amt;

    @Column(name = "spcw09_nm")
    private Integer spcw09Nm;

    @Column(name = "spcw10_amt", precision = 21, scale = 2)
    private BigDecimal spcw10Amt;

    @Column(name = "spcw10_nm")
    private Integer spcw10Nm;

    @Column(name = "spcw11_amt", precision = 21, scale = 2)
    private BigDecimal spcw11Amt;

    @Column(name = "spcw11_nm")
    private Integer spcw11Nm;

    @Column(name = "spcw12_amt", precision = 21, scale = 2)
    private BigDecimal spcw12Amt;

    @Column(name = "spcw12_nm")
    private Integer spcw12Nm;

    @Column(name = "job01_type")
    private Integer job01Type;

    @Column(name = "job02_type")
    private Integer job02Type;

    @Column(name = "job03_type")
    private Integer job03Type;

    @Column(name = "job04_type")
    private Integer job04Type;

    @Column(name = "job05_type")
    private Integer job05Type;

    @Column(name = "job06_type")
    private Integer job06Type;

    @Column(name = "job07_type")
    private Integer job07Type;

    @Column(name = "job08_type")
    private Integer job08Type;

    @Column(name = "job09_type")
    private Integer job09Type;

    @Column(name = "job10_type")
    private Integer job10Type;

    @Column(name = "job11_type")
    private Integer job11Type;

    @Column(name = "job12_type")
    private Integer job12Type;

    @Column(name = "job13_type")
    private Integer job13Type;

    @Column(name = "job14_type")
    private Integer job14Type;

    @Size(max = 1)
    @Column(name = "semset", length = 1)
    private String semset;

    @Size(max = 1)
    @Column(name = "is_rcb", length = 1)
    private String isRcb;

    @Column(name = "sorc1", precision = 21, scale = 2)
    private BigDecimal sorc1;

    @Column(name = "sorc_1b", precision = 21, scale = 2)
    private BigDecimal sorc1b;

    @Column(name = "sorc_1c", precision = 21, scale = 2)
    private BigDecimal sorc1c;

    @Column(name = "sorc6", precision = 21, scale = 2)
    private BigDecimal sorc6;

    @Column(name = "sorc_3e", precision = 21, scale = 2)
    private BigDecimal sorc3e;

    @Column(name = "sorc_1d", precision = 21, scale = 2)
    private BigDecimal sorc1d;

    @Column(name = "sorc2", precision = 21, scale = 2)
    private BigDecimal sorc2;

    @Column(name = "sorc3", precision = 21, scale = 2)
    private BigDecimal sorc3;

    @Column(name = "sorc_3a", precision = 21, scale = 2)
    private BigDecimal sorc3a;

    @Column(name = "sorc4", precision = 21, scale = 2)
    private BigDecimal sorc4;

    @Column(name = "sorc5", precision = 21, scale = 2)
    private BigDecimal sorc5;

    @Column(name = "sorc_3b", precision = 21, scale = 2)
    private BigDecimal sorc3b;

    @Column(name = "sorc_3c", precision = 21, scale = 2)
    private BigDecimal sorc3c;

    @Column(name = "sorc_3d", precision = 21, scale = 2)
    private BigDecimal sorc3d;

    @Column(name = "sorc_3b2", precision = 21, scale = 2)
    private BigDecimal sorc3b2;

    @Column(name = "sorc_3b3", precision = 21, scale = 2)
    private BigDecimal sorc3b3;

    @Column(name = "sorc_3b4", precision = 21, scale = 2)
    private BigDecimal sorc3b4;

    @Column(name = "sorc_3b5", precision = 21, scale = 2)
    private BigDecimal sorc3b5;

    @Column(name = "sorc_3c1", precision = 21, scale = 2)
    private BigDecimal sorc3c1;

    @Column(name = "sorc_3d1", precision = 21, scale = 2)
    private BigDecimal sorc3d1;

    @Column(name = "sorc_3d3", precision = 21, scale = 2)
    private BigDecimal sorc3d3;

    @Column(name = "sorc_3d4", precision = 21, scale = 2)
    private BigDecimal sorc3d4;

    @Column(name = "sorc7", precision = 21, scale = 2)
    private BigDecimal sorc7;

    @Column(name = "sorc7_use", precision = 21, scale = 2)
    private BigDecimal sorc7Use;

    @Column(name = "sorc8", precision = 21, scale = 2)
    private BigDecimal sorc8;

    @Column(name = "sorc9", precision = 21, scale = 2)
    private BigDecimal sorc9;

    @Column(name = "sorc7_use2", precision = 21, scale = 2)
    private BigDecimal sorc7Use2;

    @Column(name = "sorc7_use3", precision = 21, scale = 2)
    private BigDecimal sorc7Use3;

    @Size(max = 1)
    @Column(name = "open_yn", length = 1)
    private String openYn;

    @Size(max = 1)
    @Column(name = "is_open", length = 1)
    private String isOpen;

    @Size(max = 1)
    @Column(name = "d_open", length = 1)
    private String dOpen;

    @Size(max = 1)
    @Column(name = "w_kind", length = 1)
    private String wKind;

    @Column(name = "w_days")
    private Integer wDays;

    @Size(max = 200)
    @Column(name = "w_remk", length = 200)
    private String wRemk;

    @Size(max = 26)
    @Column(name = "b_code", length = 26)
    private String bCode;

    @Column(name = "tot_amt", precision = 21, scale = 2)
    private BigDecimal totAmt;

    @Column(name = "bdgt1", precision = 21, scale = 2)
    private BigDecimal bdgt1;

    @Column(name = "igtct", precision = 21, scale = 2)
    private BigDecimal igtct;

    @Column(name = "othct", precision = 21, scale = 2)
    private BigDecimal othct;

    @Column(name = "rsvct", precision = 21, scale = 2)
    private BigDecimal rsvct;

    @Column(name = "poll", precision = 21, scale = 2)
    private BigDecimal poll;

    @Size(max = 50)
    @Column(name = "poll_no", length = 50)
    private String pollNo;

    @Column(name = "oth_mon", precision = 21, scale = 2)
    private BigDecimal othMon;

    @Size(max = 2000)
    @Column(name = "gy_resn", length = 2000)
    private String gyResn;

    @Size(max = 1)
    @Column(name = "out_source", length = 1)
    private String outSource;

    @Size(max = 21)
    @Column(name = "drut", length = 21)
    private String drut;

    @Column(name = "drct", precision = 21, scale = 2)
    private BigDecimal drct;

    @Size(max = 300)
    @Column(name = "dsrm", length = 300)
    private String dsrm;

    @Size(max = 300)
    @Column(name = "scrm", length = 300)
    private String scrm;

    @Size(max = 1)
    @Column(name = "kick_off", length = 1)
    private String kickOff;

    @Size(max = 1)
    @Column(name = "scrr", length = 1)
    private String scrr;

    @Column(name = "scnum")
    private Integer scnum;

    @Size(max = 21)
    @Column(name = "pcmut", length = 21)
    private String pcmut;

    @Column(name = "ccnum")
    private Integer ccnum;

    @Column(name = "iss_start_date")
    private LocalDate issStartDate;

    @Column(name = "iss_end_date")
    private LocalDate issEndDate;

    @Column(name = "iss_cost", precision = 21, scale = 2)
    private BigDecimal issCost;

    @Size(max = 100)
    @Column(name = "iss_no", length = 100)
    private String issNo;

    @Size(max = 2000)
    @Column(name = "iss_rmk", length = 2000)
    private String issRmk;

    @Column(name = "pds_date")
    private LocalDate pdsDate;

    @Column(name = "ds_date")
    private LocalDate dsDate;

    @Column(name = "ads_date")
    private LocalDate adsDate;

    @Column(name = "getl_date")
    private LocalDate getlDate;

    @Column(name = "agetl_date")
    private LocalDate agetlDate;

    @Size(max = 1)
    @Column(name = "is_spay", length = 1)
    private String isSpay;

    @Size(max = 2)
    @Column(name = "ipe", length = 2)
    private String ipe;

    @Size(max = 10)
    @Column(name = "ipe_y", length = 10)
    private String ipeY;

    @Size(max = 10)
    @Column(name = "ipe_n", length = 10)
    private String ipeN;

    @Size(max = 1)
    @Column(name = "is_nlic", length = 1)
    private String isNlic;

    @Size(max = 1)
    @Column(name = "is_dlic", length = 1)
    private String isDlic;

    @Size(max = 40)
    @Column(name = "lic_no", length = 40)
    private String licNo;

    @Column(name = "lic_date")
    private LocalDate licDate;

    @Column(name = "alic_date")
    private LocalDate alicDate;

    @Column(name = "op_date")
    private LocalDate opDate;

    @Column(name = "aop_date")
    private LocalDate aopDate;

    @Column(name = "pck_date")
    private LocalDate pckDate;

    @Column(name = "ack_date")
    private LocalDate ackDate;

    @Column(name = "psc_date")
    private LocalDate pscDate;

    @Column(name = "asc_date")
    private LocalDate ascDate;

    @Column(name = "pct_date")
    private LocalDate pctDate;

    @Column(name = "act_date")
    private LocalDate actDate;

    @Size(max = 1)
    @Column(name = "ctmeth", length = 1)
    private String ctmeth;

    @Size(max = 2)
    @Column(name = "actmth", length = 2)
    private String actmth;

    @Size(max = 1)
    @Column(name = "spec_purch", length = 1)
    private String specPurch;

    @Size(max = 1)
    @Column(name = "is_intl", length = 1)
    private String isIntl;

    @Size(max = 1)
    @Column(name = "tctmth", length = 1)
    private String tctmth;

    @Column(name = "bdgt2", precision = 21, scale = 2)
    private BigDecimal bdgt2;

    @Column(name = "bdgt3", precision = 21, scale = 2)
    private BigDecimal bdgt3;

    @Size(max = 2)
    @Column(name = "mctmth", length = 2)
    private String mctmth;

    @Size(max = 2)
    @Column(name = "ccnt3", length = 2)
    private String ccnt3;

    @Size(max = 2)
    @Column(name = "is_per", length = 2)
    private String isPer;

    @Column(name = "ct_sum", precision = 21, scale = 2)
    private BigDecimal ctSum;

    @Column(name = "ct_sum_f", precision = 21, scale = 2)
    private BigDecimal ctSumF;

    @Column(name = "ct_sum_u", precision = 21, scale = 2)
    private BigDecimal ctSumU;

    @Size(max = 2000)
    @Column(name = "ctmo", length = 2000)
    private String ctmo;

    @Column(name = "ct_sum_d", precision = 21, scale = 2)
    private BigDecimal ctSumD;

    @Size(max = 80)
    @Column(name = "cntid", length = 80)
    private String cntid;

    @Column(name = "pays", precision = 21, scale = 2)
    private BigDecimal pays;

    @Column(name = "pms_sum_t", precision = 21, scale = 2)
    private BigDecimal pmsSumT;

    @Size(max = 2)
    @Column(name = "pms_type", length = 2)
    private String pmsType;

    @Size(max = 1)
    @Column(name = "paymth", length = 1)
    private String paymth;

    @Size(max = 1)
    @Column(name = "foreign_yn", length = 1)
    private String foreignYn;

    @Column(name = "cs_date")
    private LocalDate csDate;

    @Column(name = "as_date")
    private LocalDate asDate;

    @Column(name = "asu_date")
    private LocalDate asuDate;

    @Column(name = "ce_date")
    private LocalDate ceDate;

    @Column(name = "ce_date_f")
    private LocalDate ceDateF;

    @Column(name = "ae_date")
    private LocalDate aeDate;

    @Column(name = "fcost2", precision = 21, scale = 2)
    private BigDecimal fcost2;

    @Column(name = "fcs_date")
    private LocalDate fcsDate;

    @Column(name = "afcs_date")
    private LocalDate afcsDate;

    @Column(name = "fcost", precision = 21, scale = 2)
    private BigDecimal fcost;

    @Size(max = 1)
    @Column(name = "is_ckp", length = 1)
    private String isCkp;

    @Size(max = 1)
    @Column(name = "is_ckp2", length = 1)
    private String isCkp2;

    @Size(max = 1)
    @Column(name = "is_ckp3", length = 1)
    private String isCkp3;

    @Column(name = "ckp_money", precision = 21, scale = 2)
    private BigDecimal ckpMoney;

    @Size(max = 100)
    @Column(name = "ckp_unit", length = 100)
    private String ckpUnit;

    @Column(name = "ckp_date")
    private LocalDate ckpDate;

    @Column(name = "ckp_cnt", precision = 21, scale = 2)
    private BigDecimal ckpCnt;

    @Size(max = 100)
    @Column(name = "ckp_num", length = 100)
    private String ckpNum;

    @Size(max = 100)
    @Column(name = "cka_unit", length = 100)
    private String ckaUnit;

    @Column(name = "cka_date")
    private LocalDate ckaDate;

    @Column(name = "cka_cnt", precision = 21, scale = 2)
    private BigDecimal ckaCnt;

    @Size(max = 100)
    @Column(name = "cka_num", length = 100)
    private String ckaNum;

    @Size(max = 100)
    @Column(name = "ckd_unit", length = 100)
    private String ckdUnit;

    @Column(name = "ckd_date")
    private LocalDate ckdDate;

    @Column(name = "ckd_cnt", precision = 21, scale = 2)
    private BigDecimal ckdCnt;

    @Size(max = 100)
    @Column(name = "ckd_num", length = 100)
    private String ckdNum;

    @Size(max = 1000)
    @Column(name = "work_memo", length = 1000)
    private String workMemo;

    @Size(max = 20)
    @Column(name = "hot_line", length = 20)
    private String hotLine;

    @Size(max = 20)
    @Column(name = "lnk_code2", length = 20)
    private String lnkCode2;

    @Size(max = 40)
    @Column(name = "lnk_code3", length = 40)
    private String lnkCode3;

    @Size(max = 40)
    @Column(name = "lnk_no2", length = 40)
    private String lnkNo2;

    @Size(max = 180)
    @Column(name = "ccnt1", length = 180)
    private String ccnt1;

    @Size(max = 4)
    @Column(name = "type_code2", length = 4)
    private String typeCode2;

    @Size(max = 60)
    @Column(name = "dept_name", length = 60)
    private String deptName;

    @Size(max = 5)
    @Column(name = "aop_cnt", length = 5)
    private String aopCnt;

    @Size(max = 20)
    @Column(name = "unique_key", length = 20)
    private String uniqueKey;

    @Size(max = 1)
    @Column(name = "is_bidstr", length = 1)
    private String isBidstr;

    @Size(max = 1)
    @Column(name = "is_engineer", length = 1)
    private String isEngineer;

    @Size(max = 180)
    @Column(name = "name0", length = 180)
    private String name0;

    @Column(name = "ct_sum0", precision = 21, scale = 2)
    private BigDecimal ctSum0;

    @Column(name = "bdgt1_0", precision = 21, scale = 2)
    private BigDecimal bdgt1_0;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 7)
    @Column(name = "batch", length = 7)
    private String batch;

    @Column(name = "nocnt_day")
    private Integer nocntDay;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "aok_date")
    private LocalDate aokDate;

    @Column(name = "fcost3", precision = 21, scale = 2)
    private BigDecimal fcost3;

    @Column(name = "fin_ways")
    private Integer finWays;

    @Column(name = "awork_day")
    private Integer aworkDay;

    @Column(name = "fast_day")
    private Integer fastDay;

    @Column(name = "over_day")
    private Integer overDay;

    @Column(name = "over_day_y")
    private Integer overDayY;

    @Column(name = "over_fee", precision = 21, scale = 2)
    private BigDecimal overFee;

    @Column(name = "other_fee", precision = 21, scale = 2)
    private BigDecimal otherFee;

    @Column(name = "punsh_fee", precision = 21, scale = 2)
    private BigDecimal punshFee;

    @Size(max = 20)
    @Column(name = "tm2x", length = 20)
    private String tm2x;

    @Size(max = 20)
    @Column(name = "tm2y", length = 20)
    private String tm2y;

    @Size(max = 20)
    @Column(name = "tm2x2", length = 20)
    private String tm2x2;

    @Size(max = 20)
    @Column(name = "tm2y2", length = 20)
    private String tm2y2;

    @Size(max = 20)
    @Column(name = "tm2x3", length = 20)
    private String tm2x3;

    @Size(max = 20)
    @Column(name = "tm2y3", length = 20)
    private String tm2y3;

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

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 1)
    @Column(name = "can", length = 1)
    private String can;

    @Size(max = 2000)
    @Column(name = "srut", length = 2000)
    private String srut;

    @Column(name = "anoct", precision = 21, scale = 2)
    private BigDecimal anoct;

    @Size(max = 7)
    @Column(name = "cepd_no", length = 7)
    private String cepdNo;

    @Size(max = 2)
    @Column(name = "sub_no", length = 2)
    private String subNo;

    @Size(max = 60)
    @Column(name = "scnt", length = 60)
    private String scnt;

    @Size(max = 2000)
    @Column(name = "dsdm", length = 2000)
    private String dsdm;

    @Size(max = 1000)
    @Column(name = "preson", length = 1000)
    private String preson;

    @Column(name = "psign_date")
    private LocalDate psignDate;

    @Column(name = "asign_date")
    private LocalDate asignDate;

    @Column(name = "tr_date")
    private LocalDate trDate;

    @Column(name = "conf_in", precision = 21, scale = 2)
    private BigDecimal confIn;

    @Size(max = 50)
    @Column(name = "u_key", length = 50)
    private String uKey;

    @Size(max = 200)
    @Column(name = "tpcoth", length = 200)
    private String tpcoth;

    @Column(name = "pcmct", precision = 21, scale = 2)
    private BigDecimal pcmct;

    @Size(max = 21)
    @Column(name = "wkut_old", length = 21)
    private String wkutOld;

    @Size(max = 40)
    @Column(name = "pcc_memo", length = 40)
    private String pccMemo;

    @Column(name = "bgb_sumt", precision = 21, scale = 2)
    private BigDecimal bgbSumt;

    @Column(name = "bgb_date")
    private LocalDate bgbDate;

    @Size(max = 4000)
    @Column(name = "main_item", length = 4000)
    private String mainItem;

    @Size(max = 2000)
    @Column(name = "m_desc", length = 2000)
    private String mDesc;

    @Size(max = 1000)
    @Column(name = "m_rmk", length = 1000)
    private String mRmk;

    @Column(name = "pmr_sumt", precision = 21, scale = 2)
    private BigDecimal pmrSumt;

    @Column(name = "pmr_date")
    private LocalDate pmrDate;

    @Size(max = 2)
    @Column(name = "pmr_resm", length = 2)
    private String pmrResm;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidProjectPrimaryKey getId() {
        return new BidProjectPrimaryKey(wkut, prjno);
    }

    public BidProject id(BidProjectPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidProjectPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidProject wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidProject prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getName() {
        return this.name;
    }

    public BidProject name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAdd92() {
        return this.isAdd92;
    }

    public BidProject isAdd92(String isAdd92) {
        this.setIsAdd92(isAdd92);
        return this;
    }

    public void setIsAdd92(String isAdd92) {
        this.isAdd92 = isAdd92;
    }

    public String getPntCtrl() {
        return this.pntCtrl;
    }

    public BidProject pntCtrl(String pntCtrl) {
        this.setPntCtrl(pntCtrl);
        return this;
    }

    public void setPntCtrl(String pntCtrl) {
        this.pntCtrl = pntCtrl;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public BidProject typeCode(String typeCode) {
        this.setTypeCode(typeCode);
        return this;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getIsUnion() {
        return this.isUnion;
    }

    public BidProject isUnion(String isUnion) {
        this.setIsUnion(isUnion);
        return this;
    }

    public void setIsUnion(String isUnion) {
        this.isUnion = isUnion;
    }

    public String getPlaceCode() {
        return this.placeCode;
    }

    public BidProject placeCode(String placeCode) {
        this.setPlaceCode(placeCode);
        return this;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlace() {
        return this.place;
    }

    public BidProject place(String place) {
        this.setPlace(place);
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBosCode() {
        return this.bosCode;
    }

    public BidProject bosCode(String bosCode) {
        this.setBosCode(bosCode);
        return this;
    }

    public void setBosCode(String bosCode) {
        this.bosCode = bosCode;
    }

    public String getDepCode() {
        return this.depCode;
    }

    public BidProject depCode(String depCode) {
        this.setDepCode(depCode);
        return this;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getIsBg() {
        return this.isBg;
    }

    public BidProject isBg(String isBg) {
        this.setIsBg(isBg);
        return this;
    }

    public void setIsBg(String isBg) {
        this.isBg = isBg;
    }

    public String getPlnCode() {
        return this.plnCode;
    }

    public BidProject plnCode(String plnCode) {
        this.setPlnCode(plnCode);
        return this;
    }

    public void setPlnCode(String plnCode) {
        this.plnCode = plnCode;
    }

    public String getPlnNo() {
        return this.plnNo;
    }

    public BidProject plnNo(String plnNo) {
        this.setPlnNo(plnNo);
        return this;
    }

    public void setPlnNo(String plnNo) {
        this.plnNo = plnNo;
    }

    public String getPlnUnicode() {
        return this.plnUnicode;
    }

    public BidProject plnUnicode(String plnUnicode) {
        this.setPlnUnicode(plnUnicode);
        return this;
    }

    public void setPlnUnicode(String plnUnicode) {
        this.plnUnicode = plnUnicode;
    }

    public String getBos2Code() {
        return this.bos2Code;
    }

    public BidProject bos2Code(String bos2Code) {
        this.setBos2Code(bos2Code);
        return this;
    }

    public void setBos2Code(String bos2Code) {
        this.bos2Code = bos2Code;
    }

    public BigDecimal getBos2Rate() {
        return this.bos2Rate;
    }

    public BidProject bos2Rate(BigDecimal bos2Rate) {
        this.setBos2Rate(bos2Rate);
        return this;
    }

    public void setBos2Rate(BigDecimal bos2Rate) {
        this.bos2Rate = bos2Rate;
    }

    public String getBos2bCode() {
        return this.bos2bCode;
    }

    public BidProject bos2bCode(String bos2bCode) {
        this.setBos2bCode(bos2bCode);
        return this;
    }

    public void setBos2bCode(String bos2bCode) {
        this.bos2bCode = bos2bCode;
    }

    public BigDecimal getBos2bRate() {
        return this.bos2bRate;
    }

    public BidProject bos2bRate(BigDecimal bos2bRate) {
        this.setBos2bRate(bos2bRate);
        return this;
    }

    public void setBos2bRate(BigDecimal bos2bRate) {
        this.bos2bRate = bos2bRate;
    }

    public String getBos2cCode() {
        return this.bos2cCode;
    }

    public BidProject bos2cCode(String bos2cCode) {
        this.setBos2cCode(bos2cCode);
        return this;
    }

    public void setBos2cCode(String bos2cCode) {
        this.bos2cCode = bos2cCode;
    }

    public BigDecimal getBos2cRate() {
        return this.bos2cRate;
    }

    public BidProject bos2cRate(BigDecimal bos2cRate) {
        this.setBos2cRate(bos2cRate);
        return this;
    }

    public void setBos2cRate(BigDecimal bos2cRate) {
        this.bos2cRate = bos2cRate;
    }

    public String getBos2dCode() {
        return this.bos2dCode;
    }

    public BidProject bos2dCode(String bos2dCode) {
        this.setBos2dCode(bos2dCode);
        return this;
    }

    public void setBos2dCode(String bos2dCode) {
        this.bos2dCode = bos2dCode;
    }

    public BigDecimal getBos2dRate() {
        return this.bos2dRate;
    }

    public BidProject bos2dRate(BigDecimal bos2dRate) {
        this.setBos2dRate(bos2dRate);
        return this;
    }

    public void setBos2dRate(BigDecimal bos2dRate) {
        this.bos2dRate = bos2dRate;
    }

    public String getLnkCode() {
        return this.lnkCode;
    }

    public BidProject lnkCode(String lnkCode) {
        this.setLnkCode(lnkCode);
        return this;
    }

    public void setLnkCode(String lnkCode) {
        this.lnkCode = lnkCode;
    }

    public String getCcnt2() {
        return this.ccnt2;
    }

    public BidProject ccnt2(String ccnt2) {
        this.setCcnt2(ccnt2);
        return this;
    }

    public void setCcnt2(String ccnt2) {
        this.ccnt2 = ccnt2;
    }

    public String getMan() {
        return this.man;
    }

    public BidProject man(String man) {
        this.setMan(man);
        return this;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return this.tel;
    }

    public BidProject tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String geteMail() {
        return this.eMail;
    }

    public BidProject eMail(String eMail) {
        this.seteMail(eMail);
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getWrate() {
        return this.wrate;
    }

    public BidProject wrate(BigDecimal wrate) {
        this.setWrate(wrate);
        return this;
    }

    public void setWrate(BigDecimal wrate) {
        this.wrate = wrate;
    }

    public String getContain() {
        return this.contain;
    }

    public BidProject contain(String contain) {
        this.setContain(contain);
        return this;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getPfunc() {
        return this.pfunc;
    }

    public BidProject pfunc(String pfunc) {
        this.setPfunc(pfunc);
        return this;
    }

    public void setPfunc(String pfunc) {
        this.pfunc = pfunc;
    }

    public String getBenf() {
        return this.benf;
    }

    public BidProject benf(String benf) {
        this.setBenf(benf);
        return this;
    }

    public void setBenf(String benf) {
        this.benf = benf;
    }

    public String getPstand() {
        return this.pstand;
    }

    public BidProject pstand(String pstand) {
        this.setPstand(pstand);
        return this;
    }

    public void setPstand(String pstand) {
        this.pstand = pstand;
    }

    public String getPqty() {
        return this.pqty;
    }

    public BidProject pqty(String pqty) {
        this.setPqty(pqty);
        return this;
    }

    public void setPqty(String pqty) {
        this.pqty = pqty;
    }

    public String getUntnd() {
        return this.untnd;
    }

    public BidProject untnd(String untnd) {
        this.setUntnd(untnd);
        return this;
    }

    public void setUntnd(String untnd) {
        this.untnd = untnd;
    }

    public String getIsSpcw() {
        return this.isSpcw;
    }

    public BidProject isSpcw(String isSpcw) {
        this.setIsSpcw(isSpcw);
        return this;
    }

    public void setIsSpcw(String isSpcw) {
        this.isSpcw = isSpcw;
    }

    public String getSpcw01() {
        return this.spcw01;
    }

    public BidProject spcw01(String spcw01) {
        this.setSpcw01(spcw01);
        return this;
    }

    public void setSpcw01(String spcw01) {
        this.spcw01 = spcw01;
    }

    public String getSpcw02() {
        return this.spcw02;
    }

    public BidProject spcw02(String spcw02) {
        this.setSpcw02(spcw02);
        return this;
    }

    public void setSpcw02(String spcw02) {
        this.spcw02 = spcw02;
    }

    public String getSpcw03() {
        return this.spcw03;
    }

    public BidProject spcw03(String spcw03) {
        this.setSpcw03(spcw03);
        return this;
    }

    public void setSpcw03(String spcw03) {
        this.spcw03 = spcw03;
    }

    public String getSpcw04() {
        return this.spcw04;
    }

    public BidProject spcw04(String spcw04) {
        this.setSpcw04(spcw04);
        return this;
    }

    public void setSpcw04(String spcw04) {
        this.spcw04 = spcw04;
    }

    public String getSpcw05() {
        return this.spcw05;
    }

    public BidProject spcw05(String spcw05) {
        this.setSpcw05(spcw05);
        return this;
    }

    public void setSpcw05(String spcw05) {
        this.spcw05 = spcw05;
    }

    public String getSpcw06() {
        return this.spcw06;
    }

    public BidProject spcw06(String spcw06) {
        this.setSpcw06(spcw06);
        return this;
    }

    public void setSpcw06(String spcw06) {
        this.spcw06 = spcw06;
    }

    public String getSpcw07() {
        return this.spcw07;
    }

    public BidProject spcw07(String spcw07) {
        this.setSpcw07(spcw07);
        return this;
    }

    public void setSpcw07(String spcw07) {
        this.spcw07 = spcw07;
    }

    public String getSpcw08() {
        return this.spcw08;
    }

    public BidProject spcw08(String spcw08) {
        this.setSpcw08(spcw08);
        return this;
    }

    public void setSpcw08(String spcw08) {
        this.spcw08 = spcw08;
    }

    public String getSpcw09() {
        return this.spcw09;
    }

    public BidProject spcw09(String spcw09) {
        this.setSpcw09(spcw09);
        return this;
    }

    public void setSpcw09(String spcw09) {
        this.spcw09 = spcw09;
    }

    public String getSpcw10() {
        return this.spcw10;
    }

    public BidProject spcw10(String spcw10) {
        this.setSpcw10(spcw10);
        return this;
    }

    public void setSpcw10(String spcw10) {
        this.spcw10 = spcw10;
    }

    public String getSpcw11() {
        return this.spcw11;
    }

    public BidProject spcw11(String spcw11) {
        this.setSpcw11(spcw11);
        return this;
    }

    public void setSpcw11(String spcw11) {
        this.spcw11 = spcw11;
    }

    public String getSpcw12() {
        return this.spcw12;
    }

    public BidProject spcw12(String spcw12) {
        this.setSpcw12(spcw12);
        return this;
    }

    public void setSpcw12(String spcw12) {
        this.spcw12 = spcw12;
    }

    public BigDecimal getSpcw01Amt() {
        return this.spcw01Amt;
    }

    public BidProject spcw01Amt(BigDecimal spcw01Amt) {
        this.setSpcw01Amt(spcw01Amt);
        return this;
    }

    public void setSpcw01Amt(BigDecimal spcw01Amt) {
        this.spcw01Amt = spcw01Amt;
    }

    public Integer getSpcw01Nm() {
        return this.spcw01Nm;
    }

    public BidProject spcw01Nm(Integer spcw01Nm) {
        this.setSpcw01Nm(spcw01Nm);
        return this;
    }

    public void setSpcw01Nm(Integer spcw01Nm) {
        this.spcw01Nm = spcw01Nm;
    }

    public BigDecimal getSpcw02Amt() {
        return this.spcw02Amt;
    }

    public BidProject spcw02Amt(BigDecimal spcw02Amt) {
        this.setSpcw02Amt(spcw02Amt);
        return this;
    }

    public void setSpcw02Amt(BigDecimal spcw02Amt) {
        this.spcw02Amt = spcw02Amt;
    }

    public Integer getSpcw02Nm() {
        return this.spcw02Nm;
    }

    public BidProject spcw02Nm(Integer spcw02Nm) {
        this.setSpcw02Nm(spcw02Nm);
        return this;
    }

    public void setSpcw02Nm(Integer spcw02Nm) {
        this.spcw02Nm = spcw02Nm;
    }

    public BigDecimal getSpcw03Amt() {
        return this.spcw03Amt;
    }

    public BidProject spcw03Amt(BigDecimal spcw03Amt) {
        this.setSpcw03Amt(spcw03Amt);
        return this;
    }

    public void setSpcw03Amt(BigDecimal spcw03Amt) {
        this.spcw03Amt = spcw03Amt;
    }

    public Integer getSpcw03Nm() {
        return this.spcw03Nm;
    }

    public BidProject spcw03Nm(Integer spcw03Nm) {
        this.setSpcw03Nm(spcw03Nm);
        return this;
    }

    public void setSpcw03Nm(Integer spcw03Nm) {
        this.spcw03Nm = spcw03Nm;
    }

    public BigDecimal getSpcw04Amt() {
        return this.spcw04Amt;
    }

    public BidProject spcw04Amt(BigDecimal spcw04Amt) {
        this.setSpcw04Amt(spcw04Amt);
        return this;
    }

    public void setSpcw04Amt(BigDecimal spcw04Amt) {
        this.spcw04Amt = spcw04Amt;
    }

    public Integer getSpcw04Nm() {
        return this.spcw04Nm;
    }

    public BidProject spcw04Nm(Integer spcw04Nm) {
        this.setSpcw04Nm(spcw04Nm);
        return this;
    }

    public void setSpcw04Nm(Integer spcw04Nm) {
        this.spcw04Nm = spcw04Nm;
    }

    public BigDecimal getSpcw05Amt() {
        return this.spcw05Amt;
    }

    public BidProject spcw05Amt(BigDecimal spcw05Amt) {
        this.setSpcw05Amt(spcw05Amt);
        return this;
    }

    public void setSpcw05Amt(BigDecimal spcw05Amt) {
        this.spcw05Amt = spcw05Amt;
    }

    public Integer getSpcw05Nm() {
        return this.spcw05Nm;
    }

    public BidProject spcw05Nm(Integer spcw05Nm) {
        this.setSpcw05Nm(spcw05Nm);
        return this;
    }

    public void setSpcw05Nm(Integer spcw05Nm) {
        this.spcw05Nm = spcw05Nm;
    }

    public BigDecimal getSpcw06Amt() {
        return this.spcw06Amt;
    }

    public BidProject spcw06Amt(BigDecimal spcw06Amt) {
        this.setSpcw06Amt(spcw06Amt);
        return this;
    }

    public void setSpcw06Amt(BigDecimal spcw06Amt) {
        this.spcw06Amt = spcw06Amt;
    }

    public Integer getSpcw06Nm() {
        return this.spcw06Nm;
    }

    public BidProject spcw06Nm(Integer spcw06Nm) {
        this.setSpcw06Nm(spcw06Nm);
        return this;
    }

    public void setSpcw06Nm(Integer spcw06Nm) {
        this.spcw06Nm = spcw06Nm;
    }

    public BigDecimal getSpcw07Amt() {
        return this.spcw07Amt;
    }

    public BidProject spcw07Amt(BigDecimal spcw07Amt) {
        this.setSpcw07Amt(spcw07Amt);
        return this;
    }

    public void setSpcw07Amt(BigDecimal spcw07Amt) {
        this.spcw07Amt = spcw07Amt;
    }

    public Integer getSpcw07Nm() {
        return this.spcw07Nm;
    }

    public BidProject spcw07Nm(Integer spcw07Nm) {
        this.setSpcw07Nm(spcw07Nm);
        return this;
    }

    public void setSpcw07Nm(Integer spcw07Nm) {
        this.spcw07Nm = spcw07Nm;
    }

    public BigDecimal getSpcw08Amt() {
        return this.spcw08Amt;
    }

    public BidProject spcw08Amt(BigDecimal spcw08Amt) {
        this.setSpcw08Amt(spcw08Amt);
        return this;
    }

    public void setSpcw08Amt(BigDecimal spcw08Amt) {
        this.spcw08Amt = spcw08Amt;
    }

    public Integer getSpcw08Nm() {
        return this.spcw08Nm;
    }

    public BidProject spcw08Nm(Integer spcw08Nm) {
        this.setSpcw08Nm(spcw08Nm);
        return this;
    }

    public void setSpcw08Nm(Integer spcw08Nm) {
        this.spcw08Nm = spcw08Nm;
    }

    public BigDecimal getSpcw09Amt() {
        return this.spcw09Amt;
    }

    public BidProject spcw09Amt(BigDecimal spcw09Amt) {
        this.setSpcw09Amt(spcw09Amt);
        return this;
    }

    public void setSpcw09Amt(BigDecimal spcw09Amt) {
        this.spcw09Amt = spcw09Amt;
    }

    public Integer getSpcw09Nm() {
        return this.spcw09Nm;
    }

    public BidProject spcw09Nm(Integer spcw09Nm) {
        this.setSpcw09Nm(spcw09Nm);
        return this;
    }

    public void setSpcw09Nm(Integer spcw09Nm) {
        this.spcw09Nm = spcw09Nm;
    }

    public BigDecimal getSpcw10Amt() {
        return this.spcw10Amt;
    }

    public BidProject spcw10Amt(BigDecimal spcw10Amt) {
        this.setSpcw10Amt(spcw10Amt);
        return this;
    }

    public void setSpcw10Amt(BigDecimal spcw10Amt) {
        this.spcw10Amt = spcw10Amt;
    }

    public Integer getSpcw10Nm() {
        return this.spcw10Nm;
    }

    public BidProject spcw10Nm(Integer spcw10Nm) {
        this.setSpcw10Nm(spcw10Nm);
        return this;
    }

    public void setSpcw10Nm(Integer spcw10Nm) {
        this.spcw10Nm = spcw10Nm;
    }

    public BigDecimal getSpcw11Amt() {
        return this.spcw11Amt;
    }

    public BidProject spcw11Amt(BigDecimal spcw11Amt) {
        this.setSpcw11Amt(spcw11Amt);
        return this;
    }

    public void setSpcw11Amt(BigDecimal spcw11Amt) {
        this.spcw11Amt = spcw11Amt;
    }

    public Integer getSpcw11Nm() {
        return this.spcw11Nm;
    }

    public BidProject spcw11Nm(Integer spcw11Nm) {
        this.setSpcw11Nm(spcw11Nm);
        return this;
    }

    public void setSpcw11Nm(Integer spcw11Nm) {
        this.spcw11Nm = spcw11Nm;
    }

    public BigDecimal getSpcw12Amt() {
        return this.spcw12Amt;
    }

    public BidProject spcw12Amt(BigDecimal spcw12Amt) {
        this.setSpcw12Amt(spcw12Amt);
        return this;
    }

    public void setSpcw12Amt(BigDecimal spcw12Amt) {
        this.spcw12Amt = spcw12Amt;
    }

    public Integer getSpcw12Nm() {
        return this.spcw12Nm;
    }

    public BidProject spcw12Nm(Integer spcw12Nm) {
        this.setSpcw12Nm(spcw12Nm);
        return this;
    }

    public void setSpcw12Nm(Integer spcw12Nm) {
        this.spcw12Nm = spcw12Nm;
    }

    public Integer getJob01Type() {
        return this.job01Type;
    }

    public BidProject job01Type(Integer job01Type) {
        this.setJob01Type(job01Type);
        return this;
    }

    public void setJob01Type(Integer job01Type) {
        this.job01Type = job01Type;
    }

    public Integer getJob02Type() {
        return this.job02Type;
    }

    public BidProject job02Type(Integer job02Type) {
        this.setJob02Type(job02Type);
        return this;
    }

    public void setJob02Type(Integer job02Type) {
        this.job02Type = job02Type;
    }

    public Integer getJob03Type() {
        return this.job03Type;
    }

    public BidProject job03Type(Integer job03Type) {
        this.setJob03Type(job03Type);
        return this;
    }

    public void setJob03Type(Integer job03Type) {
        this.job03Type = job03Type;
    }

    public Integer getJob04Type() {
        return this.job04Type;
    }

    public BidProject job04Type(Integer job04Type) {
        this.setJob04Type(job04Type);
        return this;
    }

    public void setJob04Type(Integer job04Type) {
        this.job04Type = job04Type;
    }

    public Integer getJob05Type() {
        return this.job05Type;
    }

    public BidProject job05Type(Integer job05Type) {
        this.setJob05Type(job05Type);
        return this;
    }

    public void setJob05Type(Integer job05Type) {
        this.job05Type = job05Type;
    }

    public Integer getJob06Type() {
        return this.job06Type;
    }

    public BidProject job06Type(Integer job06Type) {
        this.setJob06Type(job06Type);
        return this;
    }

    public void setJob06Type(Integer job06Type) {
        this.job06Type = job06Type;
    }

    public Integer getJob07Type() {
        return this.job07Type;
    }

    public BidProject job07Type(Integer job07Type) {
        this.setJob07Type(job07Type);
        return this;
    }

    public void setJob07Type(Integer job07Type) {
        this.job07Type = job07Type;
    }

    public Integer getJob08Type() {
        return this.job08Type;
    }

    public BidProject job08Type(Integer job08Type) {
        this.setJob08Type(job08Type);
        return this;
    }

    public void setJob08Type(Integer job08Type) {
        this.job08Type = job08Type;
    }

    public Integer getJob09Type() {
        return this.job09Type;
    }

    public BidProject job09Type(Integer job09Type) {
        this.setJob09Type(job09Type);
        return this;
    }

    public void setJob09Type(Integer job09Type) {
        this.job09Type = job09Type;
    }

    public Integer getJob10Type() {
        return this.job10Type;
    }

    public BidProject job10Type(Integer job10Type) {
        this.setJob10Type(job10Type);
        return this;
    }

    public void setJob10Type(Integer job10Type) {
        this.job10Type = job10Type;
    }

    public Integer getJob11Type() {
        return this.job11Type;
    }

    public BidProject job11Type(Integer job11Type) {
        this.setJob11Type(job11Type);
        return this;
    }

    public void setJob11Type(Integer job11Type) {
        this.job11Type = job11Type;
    }

    public Integer getJob12Type() {
        return this.job12Type;
    }

    public BidProject job12Type(Integer job12Type) {
        this.setJob12Type(job12Type);
        return this;
    }

    public void setJob12Type(Integer job12Type) {
        this.job12Type = job12Type;
    }

    public Integer getJob13Type() {
        return this.job13Type;
    }

    public BidProject job13Type(Integer job13Type) {
        this.setJob13Type(job13Type);
        return this;
    }

    public void setJob13Type(Integer job13Type) {
        this.job13Type = job13Type;
    }

    public Integer getJob14Type() {
        return this.job14Type;
    }

    public BidProject job14Type(Integer job14Type) {
        this.setJob14Type(job14Type);
        return this;
    }

    public void setJob14Type(Integer job14Type) {
        this.job14Type = job14Type;
    }

    public String getSemset() {
        return this.semset;
    }

    public BidProject semset(String semset) {
        this.setSemset(semset);
        return this;
    }

    public void setSemset(String semset) {
        this.semset = semset;
    }

    public String getIsRcb() {
        return this.isRcb;
    }

    public BidProject isRcb(String isRcb) {
        this.setIsRcb(isRcb);
        return this;
    }

    public void setIsRcb(String isRcb) {
        this.isRcb = isRcb;
    }

    public BigDecimal getSorc1() {
        return this.sorc1;
    }

    public BidProject sorc1(BigDecimal sorc1) {
        this.setSorc1(sorc1);
        return this;
    }

    public void setSorc1(BigDecimal sorc1) {
        this.sorc1 = sorc1;
    }

    public BigDecimal getSorc1b() {
        return this.sorc1b;
    }

    public BidProject sorc1b(BigDecimal sorc1b) {
        this.setSorc1b(sorc1b);
        return this;
    }

    public void setSorc1b(BigDecimal sorc1b) {
        this.sorc1b = sorc1b;
    }

    public BigDecimal getSorc1c() {
        return this.sorc1c;
    }

    public BidProject sorc1c(BigDecimal sorc1c) {
        this.setSorc1c(sorc1c);
        return this;
    }

    public void setSorc1c(BigDecimal sorc1c) {
        this.sorc1c = sorc1c;
    }

    public BigDecimal getSorc6() {
        return this.sorc6;
    }

    public BidProject sorc6(BigDecimal sorc6) {
        this.setSorc6(sorc6);
        return this;
    }

    public void setSorc6(BigDecimal sorc6) {
        this.sorc6 = sorc6;
    }

    public BigDecimal getSorc3e() {
        return this.sorc3e;
    }

    public BidProject sorc3e(BigDecimal sorc3e) {
        this.setSorc3e(sorc3e);
        return this;
    }

    public void setSorc3e(BigDecimal sorc3e) {
        this.sorc3e = sorc3e;
    }

    public BigDecimal getSorc1d() {
        return this.sorc1d;
    }

    public BidProject sorc1d(BigDecimal sorc1d) {
        this.setSorc1d(sorc1d);
        return this;
    }

    public void setSorc1d(BigDecimal sorc1d) {
        this.sorc1d = sorc1d;
    }

    public BigDecimal getSorc2() {
        return this.sorc2;
    }

    public BidProject sorc2(BigDecimal sorc2) {
        this.setSorc2(sorc2);
        return this;
    }

    public void setSorc2(BigDecimal sorc2) {
        this.sorc2 = sorc2;
    }

    public BigDecimal getSorc3() {
        return this.sorc3;
    }

    public BidProject sorc3(BigDecimal sorc3) {
        this.setSorc3(sorc3);
        return this;
    }

    public void setSorc3(BigDecimal sorc3) {
        this.sorc3 = sorc3;
    }

    public BigDecimal getSorc3a() {
        return this.sorc3a;
    }

    public BidProject sorc3a(BigDecimal sorc3a) {
        this.setSorc3a(sorc3a);
        return this;
    }

    public void setSorc3a(BigDecimal sorc3a) {
        this.sorc3a = sorc3a;
    }

    public BigDecimal getSorc4() {
        return this.sorc4;
    }

    public BidProject sorc4(BigDecimal sorc4) {
        this.setSorc4(sorc4);
        return this;
    }

    public void setSorc4(BigDecimal sorc4) {
        this.sorc4 = sorc4;
    }

    public BigDecimal getSorc5() {
        return this.sorc5;
    }

    public BidProject sorc5(BigDecimal sorc5) {
        this.setSorc5(sorc5);
        return this;
    }

    public void setSorc5(BigDecimal sorc5) {
        this.sorc5 = sorc5;
    }

    public BigDecimal getSorc3b() {
        return this.sorc3b;
    }

    public BidProject sorc3b(BigDecimal sorc3b) {
        this.setSorc3b(sorc3b);
        return this;
    }

    public void setSorc3b(BigDecimal sorc3b) {
        this.sorc3b = sorc3b;
    }

    public BigDecimal getSorc3c() {
        return this.sorc3c;
    }

    public BidProject sorc3c(BigDecimal sorc3c) {
        this.setSorc3c(sorc3c);
        return this;
    }

    public void setSorc3c(BigDecimal sorc3c) {
        this.sorc3c = sorc3c;
    }

    public BigDecimal getSorc3d() {
        return this.sorc3d;
    }

    public BidProject sorc3d(BigDecimal sorc3d) {
        this.setSorc3d(sorc3d);
        return this;
    }

    public void setSorc3d(BigDecimal sorc3d) {
        this.sorc3d = sorc3d;
    }

    public BigDecimal getSorc3b2() {
        return this.sorc3b2;
    }

    public BidProject sorc3b2(BigDecimal sorc3b2) {
        this.setSorc3b2(sorc3b2);
        return this;
    }

    public void setSorc3b2(BigDecimal sorc3b2) {
        this.sorc3b2 = sorc3b2;
    }

    public BigDecimal getSorc3b3() {
        return this.sorc3b3;
    }

    public BidProject sorc3b3(BigDecimal sorc3b3) {
        this.setSorc3b3(sorc3b3);
        return this;
    }

    public void setSorc3b3(BigDecimal sorc3b3) {
        this.sorc3b3 = sorc3b3;
    }

    public BigDecimal getSorc3b4() {
        return this.sorc3b4;
    }

    public BidProject sorc3b4(BigDecimal sorc3b4) {
        this.setSorc3b4(sorc3b4);
        return this;
    }

    public void setSorc3b4(BigDecimal sorc3b4) {
        this.sorc3b4 = sorc3b4;
    }

    public BigDecimal getSorc3b5() {
        return this.sorc3b5;
    }

    public BidProject sorc3b5(BigDecimal sorc3b5) {
        this.setSorc3b5(sorc3b5);
        return this;
    }

    public void setSorc3b5(BigDecimal sorc3b5) {
        this.sorc3b5 = sorc3b5;
    }

    public BigDecimal getSorc3c1() {
        return this.sorc3c1;
    }

    public BidProject sorc3c1(BigDecimal sorc3c1) {
        this.setSorc3c1(sorc3c1);
        return this;
    }

    public void setSorc3c1(BigDecimal sorc3c1) {
        this.sorc3c1 = sorc3c1;
    }

    public BigDecimal getSorc3d1() {
        return this.sorc3d1;
    }

    public BidProject sorc3d1(BigDecimal sorc3d1) {
        this.setSorc3d1(sorc3d1);
        return this;
    }

    public void setSorc3d1(BigDecimal sorc3d1) {
        this.sorc3d1 = sorc3d1;
    }

    public BigDecimal getSorc3d3() {
        return this.sorc3d3;
    }

    public BidProject sorc3d3(BigDecimal sorc3d3) {
        this.setSorc3d3(sorc3d3);
        return this;
    }

    public void setSorc3d3(BigDecimal sorc3d3) {
        this.sorc3d3 = sorc3d3;
    }

    public BigDecimal getSorc3d4() {
        return this.sorc3d4;
    }

    public BidProject sorc3d4(BigDecimal sorc3d4) {
        this.setSorc3d4(sorc3d4);
        return this;
    }

    public void setSorc3d4(BigDecimal sorc3d4) {
        this.sorc3d4 = sorc3d4;
    }

    public BigDecimal getSorc7() {
        return this.sorc7;
    }

    public BidProject sorc7(BigDecimal sorc7) {
        this.setSorc7(sorc7);
        return this;
    }

    public void setSorc7(BigDecimal sorc7) {
        this.sorc7 = sorc7;
    }

    public BigDecimal getSorc7Use() {
        return this.sorc7Use;
    }

    public BidProject sorc7Use(BigDecimal sorc7Use) {
        this.setSorc7Use(sorc7Use);
        return this;
    }

    public void setSorc7Use(BigDecimal sorc7Use) {
        this.sorc7Use = sorc7Use;
    }

    public BigDecimal getSorc8() {
        return this.sorc8;
    }

    public BidProject sorc8(BigDecimal sorc8) {
        this.setSorc8(sorc8);
        return this;
    }

    public void setSorc8(BigDecimal sorc8) {
        this.sorc8 = sorc8;
    }

    public BigDecimal getSorc9() {
        return this.sorc9;
    }

    public BidProject sorc9(BigDecimal sorc9) {
        this.setSorc9(sorc9);
        return this;
    }

    public void setSorc9(BigDecimal sorc9) {
        this.sorc9 = sorc9;
    }

    public BigDecimal getSorc7Use2() {
        return this.sorc7Use2;
    }

    public BidProject sorc7Use2(BigDecimal sorc7Use2) {
        this.setSorc7Use2(sorc7Use2);
        return this;
    }

    public void setSorc7Use2(BigDecimal sorc7Use2) {
        this.sorc7Use2 = sorc7Use2;
    }

    public BigDecimal getSorc7Use3() {
        return this.sorc7Use3;
    }

    public BidProject sorc7Use3(BigDecimal sorc7Use3) {
        this.setSorc7Use3(sorc7Use3);
        return this;
    }

    public void setSorc7Use3(BigDecimal sorc7Use3) {
        this.sorc7Use3 = sorc7Use3;
    }

    public String getOpenYn() {
        return this.openYn;
    }

    public BidProject openYn(String openYn) {
        this.setOpenYn(openYn);
        return this;
    }

    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }

    public String getIsOpen() {
        return this.isOpen;
    }

    public BidProject isOpen(String isOpen) {
        this.setIsOpen(isOpen);
        return this;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getdOpen() {
        return this.dOpen;
    }

    public BidProject dOpen(String dOpen) {
        this.setdOpen(dOpen);
        return this;
    }

    public void setdOpen(String dOpen) {
        this.dOpen = dOpen;
    }

    public String getwKind() {
        return this.wKind;
    }

    public BidProject wKind(String wKind) {
        this.setwKind(wKind);
        return this;
    }

    public void setwKind(String wKind) {
        this.wKind = wKind;
    }

    public Integer getwDays() {
        return this.wDays;
    }

    public BidProject wDays(Integer wDays) {
        this.setwDays(wDays);
        return this;
    }

    public void setwDays(Integer wDays) {
        this.wDays = wDays;
    }

    public String getwRemk() {
        return this.wRemk;
    }

    public BidProject wRemk(String wRemk) {
        this.setwRemk(wRemk);
        return this;
    }

    public void setwRemk(String wRemk) {
        this.wRemk = wRemk;
    }

    public String getbCode() {
        return this.bCode;
    }

    public BidProject bCode(String bCode) {
        this.setbCode(bCode);
        return this;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public BigDecimal getTotAmt() {
        return this.totAmt;
    }

    public BidProject totAmt(BigDecimal totAmt) {
        this.setTotAmt(totAmt);
        return this;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    public BigDecimal getBdgt1() {
        return this.bdgt1;
    }

    public BidProject bdgt1(BigDecimal bdgt1) {
        this.setBdgt1(bdgt1);
        return this;
    }

    public void setBdgt1(BigDecimal bdgt1) {
        this.bdgt1 = bdgt1;
    }

    public BigDecimal getIgtct() {
        return this.igtct;
    }

    public BidProject igtct(BigDecimal igtct) {
        this.setIgtct(igtct);
        return this;
    }

    public void setIgtct(BigDecimal igtct) {
        this.igtct = igtct;
    }

    public BigDecimal getOthct() {
        return this.othct;
    }

    public BidProject othct(BigDecimal othct) {
        this.setOthct(othct);
        return this;
    }

    public void setOthct(BigDecimal othct) {
        this.othct = othct;
    }

    public BigDecimal getRsvct() {
        return this.rsvct;
    }

    public BidProject rsvct(BigDecimal rsvct) {
        this.setRsvct(rsvct);
        return this;
    }

    public void setRsvct(BigDecimal rsvct) {
        this.rsvct = rsvct;
    }

    public BigDecimal getPoll() {
        return this.poll;
    }

    public BidProject poll(BigDecimal poll) {
        this.setPoll(poll);
        return this;
    }

    public void setPoll(BigDecimal poll) {
        this.poll = poll;
    }

    public String getPollNo() {
        return this.pollNo;
    }

    public BidProject pollNo(String pollNo) {
        this.setPollNo(pollNo);
        return this;
    }

    public void setPollNo(String pollNo) {
        this.pollNo = pollNo;
    }

    public BigDecimal getOthMon() {
        return this.othMon;
    }

    public BidProject othMon(BigDecimal othMon) {
        this.setOthMon(othMon);
        return this;
    }

    public void setOthMon(BigDecimal othMon) {
        this.othMon = othMon;
    }

    public String getGyResn() {
        return this.gyResn;
    }

    public BidProject gyResn(String gyResn) {
        this.setGyResn(gyResn);
        return this;
    }

    public void setGyResn(String gyResn) {
        this.gyResn = gyResn;
    }

    public String getOutSource() {
        return outSource;
    }

    public BidProject outSource(String outSource) {
        this.setOutSource(outSource);
        return this;
    }

    public void setOutSource(String outSource) {
        this.outSource = outSource;
    }

    public String getDrut() {
        return this.drut;
    }

    public BidProject drut(String drut) {
        this.setDrut(drut);
        return this;
    }

    public void setDrut(String drut) {
        this.drut = drut;
    }

    public BigDecimal getDrct() {
        return this.drct;
    }

    public BidProject drct(BigDecimal drct) {
        this.setDrct(drct);
        return this;
    }

    public void setDrct(BigDecimal drct) {
        this.drct = drct;
    }

    public String getDsrm() {
        return this.dsrm;
    }

    public BidProject dsrm(String dsrm) {
        this.setDsrm(dsrm);
        return this;
    }

    public void setDsrm(String dsrm) {
        this.dsrm = dsrm;
    }

    public String getScrm() {
        return this.scrm;
    }

    public BidProject scrm(String scrm) {
        this.setScrm(scrm);
        return this;
    }

    public void setScrm(String scrm) {
        this.scrm = scrm;
    }

    public String getKickOff() {
        return this.kickOff;
    }

    public BidProject kickOff(String kickOff) {
        this.setKickOff(kickOff);
        return this;
    }

    public void setKickOff(String kickOff) {
        this.kickOff = kickOff;
    }

    public String getScrr() {
        return this.scrr;
    }

    public BidProject scrr(String scrr) {
        this.setScrr(scrr);
        return this;
    }

    public void setScrr(String scrr) {
        this.scrr = scrr;
    }

    public Integer getScnum() {
        return this.scnum;
    }

    public BidProject scnum(Integer scnum) {
        this.setScnum(scnum);
        return this;
    }

    public void setScnum(Integer scnum) {
        this.scnum = scnum;
    }

    public String getPcmut() {
        return this.pcmut;
    }

    public BidProject pcmut(String pcmut) {
        this.setPcmut(pcmut);
        return this;
    }

    public void setPcmut(String pcmut) {
        this.pcmut = pcmut;
    }

    public Integer getCcnum() {
        return this.ccnum;
    }

    public BidProject ccnum(Integer ccnum) {
        this.setCcnum(ccnum);
        return this;
    }

    public void setCcnum(Integer ccnum) {
        this.ccnum = ccnum;
    }

    public LocalDate getIssStartDate() {
        return this.issStartDate;
    }

    public BidProject issStartDate(LocalDate issStartDate) {
        this.setIssStartDate(issStartDate);
        return this;
    }

    public void setIssStartDate(LocalDate issStartDate) {
        this.issStartDate = issStartDate;
    }

    public LocalDate getIssEndDate() {
        return this.issEndDate;
    }

    public BidProject issEndDate(LocalDate issEndDate) {
        this.setIssEndDate(issEndDate);
        return this;
    }

    public void setIssEndDate(LocalDate issEndDate) {
        this.issEndDate = issEndDate;
    }

    public BigDecimal getIssCost() {
        return this.issCost;
    }

    public BidProject issCost(BigDecimal issCost) {
        this.setIssCost(issCost);
        return this;
    }

    public void setIssCost(BigDecimal issCost) {
        this.issCost = issCost;
    }

    public String getIssNo() {
        return this.issNo;
    }

    public BidProject issNo(String issNo) {
        this.setIssNo(issNo);
        return this;
    }

    public void setIssNo(String issNo) {
        this.issNo = issNo;
    }

    public String getIssRmk() {
        return this.issRmk;
    }

    public BidProject issRmk(String issRmk) {
        this.setIssRmk(issRmk);
        return this;
    }

    public void setIssRmk(String issRmk) {
        this.issRmk = issRmk;
    }

    public LocalDate getPdsDate() {
        return this.pdsDate;
    }

    public BidProject pdsDate(LocalDate pdsDate) {
        this.setPdsDate(pdsDate);
        return this;
    }

    public void setPdsDate(LocalDate pdsDate) {
        this.pdsDate = pdsDate;
    }

    public LocalDate getDsDate() {
        return this.dsDate;
    }

    public BidProject dsDate(LocalDate dsDate) {
        this.setDsDate(dsDate);
        return this;
    }

    public void setDsDate(LocalDate dsDate) {
        this.dsDate = dsDate;
    }

    public LocalDate getAdsDate() {
        return this.adsDate;
    }

    public BidProject adsDate(LocalDate adsDate) {
        this.setAdsDate(adsDate);
        return this;
    }

    public void setAdsDate(LocalDate adsDate) {
        this.adsDate = adsDate;
    }

    public LocalDate getGetlDate() {
        return this.getlDate;
    }

    public BidProject getlDate(LocalDate getlDate) {
        this.setGetlDate(getlDate);
        return this;
    }

    public void setGetlDate(LocalDate getlDate) {
        this.getlDate = getlDate;
    }

    public LocalDate getAgetlDate() {
        return this.agetlDate;
    }

    public BidProject agetlDate(LocalDate agetlDate) {
        this.setAgetlDate(agetlDate);
        return this;
    }

    public void setAgetlDate(LocalDate agetlDate) {
        this.agetlDate = agetlDate;
    }

    public String getIsSpay() {
        return this.isSpay;
    }

    public BidProject isSpay(String isSpay) {
        this.setIsSpay(isSpay);
        return this;
    }

    public void setIsSpay(String isSpay) {
        this.isSpay = isSpay;
    }

    public String getIpe() {
        return this.ipe;
    }

    public BidProject ipe(String ipe) {
        this.setIpe(ipe);
        return this;
    }

    public void setIpe(String ipe) {
        this.ipe = ipe;
    }

    public String getIpeY() {
        return this.ipeY;
    }

    public BidProject ipeY(String ipeY) {
        this.setIpeY(ipeY);
        return this;
    }

    public void setIpeY(String ipeY) {
        this.ipeY = ipeY;
    }

    public String getIpeN() {
        return this.ipeN;
    }

    public BidProject ipeN(String ipeN) {
        this.setIpeN(ipeN);
        return this;
    }

    public void setIpeN(String ipeN) {
        this.ipeN = ipeN;
    }

    public String getIsNlic() {
        return this.isNlic;
    }

    public BidProject isNlic(String isNlic) {
        this.setIsNlic(isNlic);
        return this;
    }

    public void setIsNlic(String isNlic) {
        this.isNlic = isNlic;
    }

    public String getIsDlic() {
        return this.isDlic;
    }

    public BidProject isDlic(String isDlic) {
        this.setIsDlic(isDlic);
        return this;
    }

    public void setIsDlic(String isDlic) {
        this.isDlic = isDlic;
    }

    public String getLicNo() {
        return this.licNo;
    }

    public BidProject licNo(String licNo) {
        this.setLicNo(licNo);
        return this;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    public LocalDate getLicDate() {
        return this.licDate;
    }

    public BidProject licDate(LocalDate licDate) {
        this.setLicDate(licDate);
        return this;
    }

    public void setLicDate(LocalDate licDate) {
        this.licDate = licDate;
    }

    public LocalDate getAlicDate() {
        return this.alicDate;
    }

    public BidProject alicDate(LocalDate alicDate) {
        this.setAlicDate(alicDate);
        return this;
    }

    public void setAlicDate(LocalDate alicDate) {
        this.alicDate = alicDate;
    }

    public LocalDate getOpDate() {
        return this.opDate;
    }

    public BidProject opDate(LocalDate opDate) {
        this.setOpDate(opDate);
        return this;
    }

    public void setOpDate(LocalDate opDate) {
        this.opDate = opDate;
    }

    public LocalDate getAopDate() {
        return this.aopDate;
    }

    public BidProject aopDate(LocalDate aopDate) {
        this.setAopDate(aopDate);
        return this;
    }

    public void setAopDate(LocalDate aopDate) {
        this.aopDate = aopDate;
    }

    public LocalDate getPckDate() {
        return this.pckDate;
    }

    public BidProject pckDate(LocalDate pckDate) {
        this.setPckDate(pckDate);
        return this;
    }

    public void setPckDate(LocalDate pckDate) {
        this.pckDate = pckDate;
    }

    public LocalDate getAckDate() {
        return this.ackDate;
    }

    public BidProject ackDate(LocalDate ackDate) {
        this.setAckDate(ackDate);
        return this;
    }

    public void setAckDate(LocalDate ackDate) {
        this.ackDate = ackDate;
    }

    public LocalDate getPscDate() {
        return this.pscDate;
    }

    public BidProject pscDate(LocalDate pscDate) {
        this.setPscDate(pscDate);
        return this;
    }

    public void setPscDate(LocalDate pscDate) {
        this.pscDate = pscDate;
    }

    public LocalDate getAscDate() {
        return this.ascDate;
    }

    public BidProject ascDate(LocalDate ascDate) {
        this.setAscDate(ascDate);
        return this;
    }

    public void setAscDate(LocalDate ascDate) {
        this.ascDate = ascDate;
    }

    public LocalDate getPctDate() {
        return this.pctDate;
    }

    public BidProject pctDate(LocalDate pctDate) {
        this.setPctDate(pctDate);
        return this;
    }

    public void setPctDate(LocalDate pctDate) {
        this.pctDate = pctDate;
    }

    public LocalDate getActDate() {
        return this.actDate;
    }

    public BidProject actDate(LocalDate actDate) {
        this.setActDate(actDate);
        return this;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

    public String getCtmeth() {
        return this.ctmeth;
    }

    public BidProject ctmeth(String ctmeth) {
        this.setCtmeth(ctmeth);
        return this;
    }

    public void setCtmeth(String ctmeth) {
        this.ctmeth = ctmeth;
    }

    public String getActmth() {
        return this.actmth;
    }

    public BidProject actmth(String actmth) {
        this.setActmth(actmth);
        return this;
    }

    public void setActmth(String actmth) {
        this.actmth = actmth;
    }

    public String getSpecPurch() {
        return this.specPurch;
    }

    public BidProject specPurch(String specPurch) {
        this.setSpecPurch(specPurch);
        return this;
    }

    public void setSpecPurch(String specPurch) {
        this.specPurch = specPurch;
    }

    public String getIsIntl() {
        return this.isIntl;
    }

    public BidProject isIntl(String isIntl) {
        this.setIsIntl(isIntl);
        return this;
    }

    public void setIsIntl(String isIntl) {
        this.isIntl = isIntl;
    }

    public String getTctmth() {
        return this.tctmth;
    }

    public BidProject tctmth(String tctmth) {
        this.setTctmth(tctmth);
        return this;
    }

    public void setTctmth(String tctmth) {
        this.tctmth = tctmth;
    }

    public BigDecimal getBdgt2() {
        return this.bdgt2;
    }

    public BidProject bdgt2(BigDecimal bdgt2) {
        this.setBdgt2(bdgt2);
        return this;
    }

    public void setBdgt2(BigDecimal bdgt2) {
        this.bdgt2 = bdgt2;
    }

    public BigDecimal getBdgt3() {
        return this.bdgt3;
    }

    public BidProject bdgt3(BigDecimal bdgt3) {
        this.setBdgt3(bdgt3);
        return this;
    }

    public void setBdgt3(BigDecimal bdgt3) {
        this.bdgt3 = bdgt3;
    }

    public String getMctmth() {
        return this.mctmth;
    }

    public BidProject mctmth(String mctmth) {
        this.setMctmth(mctmth);
        return this;
    }

    public void setMctmth(String mctmth) {
        this.mctmth = mctmth;
    }

    public String getCcnt3() {
        return this.ccnt3;
    }

    public BidProject ccnt3(String ccnt3) {
        this.setCcnt3(ccnt3);
        return this;
    }

    public void setCcnt3(String ccnt3) {
        this.ccnt3 = ccnt3;
    }

    public String getIsPer() {
        return this.isPer;
    }

    public BidProject isPer(String isPer) {
        this.setIsPer(isPer);
        return this;
    }

    public void setIsPer(String isPer) {
        this.isPer = isPer;
    }

    public BigDecimal getCtSum() {
        return this.ctSum;
    }

    public BidProject ctSum(BigDecimal ctSum) {
        this.setCtSum(ctSum);
        return this;
    }

    public void setCtSum(BigDecimal ctSum) {
        this.ctSum = ctSum;
    }

    public BigDecimal getCtSumF() {
        return this.ctSumF;
    }

    public BidProject ctSumF(BigDecimal ctSumF) {
        this.setCtSumF(ctSumF);
        return this;
    }

    public void setCtSumF(BigDecimal ctSumF) {
        this.ctSumF = ctSumF;
    }

    public BigDecimal getCtSumU() {
        return this.ctSumU;
    }

    public BidProject ctSumU(BigDecimal ctSumU) {
        this.setCtSumU(ctSumU);
        return this;
    }

    public void setCtSumU(BigDecimal ctSumU) {
        this.ctSumU = ctSumU;
    }

    public String getCtmo() {
        return this.ctmo;
    }

    public BidProject ctmo(String ctmo) {
        this.setCtmo(ctmo);
        return this;
    }

    public void setCtmo(String ctmo) {
        this.ctmo = ctmo;
    }

    public BigDecimal getCtSumD() {
        return this.ctSumD;
    }

    public BidProject ctSumD(BigDecimal ctSumD) {
        this.setCtSumD(ctSumD);
        return this;
    }

    public void setCtSumD(BigDecimal ctSumD) {
        this.ctSumD = ctSumD;
    }

    public String getCntid() {
        return this.cntid;
    }

    public BidProject cntid(String cntid) {
        this.setCntid(cntid);
        return this;
    }

    public void setCntid(String cntid) {
        this.cntid = cntid;
    }

    public BigDecimal getPays() {
        return this.pays;
    }

    public BidProject pays(BigDecimal pays) {
        this.setPays(pays);
        return this;
    }

    public void setPays(BigDecimal pays) {
        this.pays = pays;
    }

    public BigDecimal getPmsSumT() {
        return this.pmsSumT;
    }

    public BidProject pmsSumT(BigDecimal pmsSumT) {
        this.setPmsSumT(pmsSumT);
        return this;
    }

    public void setPmsSumT(BigDecimal pmsSumT) {
        this.pmsSumT = pmsSumT;
    }

    public String getPmsType() {
        return this.pmsType;
    }

    public BidProject pmsType(String pmsType) {
        this.setPmsType(pmsType);
        return this;
    }

    public void setPmsType(String pmsType) {
        this.pmsType = pmsType;
    }

    public String getPaymth() {
        return this.paymth;
    }

    public BidProject paymth(String paymth) {
        this.setPaymth(paymth);
        return this;
    }

    public void setPaymth(String paymth) {
        this.paymth = paymth;
    }

    public String getForeignYn() {
        return this.foreignYn;
    }

    public BidProject foreignYn(String foreignYn) {
        this.setForeignYn(foreignYn);
        return this;
    }

    public void setForeignYn(String foreignYn) {
        this.foreignYn = foreignYn;
    }

    public LocalDate getCsDate() {
        return this.csDate;
    }

    public BidProject csDate(LocalDate csDate) {
        this.setCsDate(csDate);
        return this;
    }

    public void setCsDate(LocalDate csDate) {
        this.csDate = csDate;
    }

    public LocalDate getAsDate() {
        return this.asDate;
    }

    public BidProject asDate(LocalDate asDate) {
        this.setAsDate(asDate);
        return this;
    }

    public void setAsDate(LocalDate asDate) {
        this.asDate = asDate;
    }

    public LocalDate getAsuDate() {
        return this.asuDate;
    }

    public BidProject asuDate(LocalDate asuDate) {
        this.setAsuDate(asuDate);
        return this;
    }

    public void setAsuDate(LocalDate asuDate) {
        this.asuDate = asuDate;
    }

    public LocalDate getCeDate() {
        return this.ceDate;
    }

    public BidProject ceDate(LocalDate ceDate) {
        this.setCeDate(ceDate);
        return this;
    }

    public void setCeDate(LocalDate ceDate) {
        this.ceDate = ceDate;
    }

    public LocalDate getCeDateF() {
        return this.ceDateF;
    }

    public BidProject ceDateF(LocalDate ceDateF) {
        this.setCeDateF(ceDateF);
        return this;
    }

    public void setCeDateF(LocalDate ceDateF) {
        this.ceDateF = ceDateF;
    }

    public LocalDate getAeDate() {
        return this.aeDate;
    }

    public BidProject aeDate(LocalDate aeDate) {
        this.setAeDate(aeDate);
        return this;
    }

    public void setAeDate(LocalDate aeDate) {
        this.aeDate = aeDate;
    }

    public BigDecimal getFcost2() {
        return this.fcost2;
    }

    public BidProject fcost2(BigDecimal fcost2) {
        this.setFcost2(fcost2);
        return this;
    }

    public void setFcost2(BigDecimal fcost2) {
        this.fcost2 = fcost2;
    }

    public LocalDate getFcsDate() {
        return this.fcsDate;
    }

    public BidProject fcsDate(LocalDate fcsDate) {
        this.setFcsDate(fcsDate);
        return this;
    }

    public void setFcsDate(LocalDate fcsDate) {
        this.fcsDate = fcsDate;
    }

    public LocalDate getAfcsDate() {
        return this.afcsDate;
    }

    public BidProject afcsDate(LocalDate afcsDate) {
        this.setAfcsDate(afcsDate);
        return this;
    }

    public void setAfcsDate(LocalDate afcsDate) {
        this.afcsDate = afcsDate;
    }

    public BigDecimal getFcost() {
        return this.fcost;
    }

    public BidProject fcost(BigDecimal fcost) {
        this.setFcost(fcost);
        return this;
    }

    public void setFcost(BigDecimal fcost) {
        this.fcost = fcost;
    }

    public String getIsCkp() {
        return this.isCkp;
    }

    public BidProject isCkp(String isCkp) {
        this.setIsCkp(isCkp);
        return this;
    }

    public void setIsCkp(String isCkp) {
        this.isCkp = isCkp;
    }

    public String getIsCkp2() {
        return this.isCkp2;
    }

    public BidProject isCkp2(String isCkp2) {
        this.setIsCkp2(isCkp2);
        return this;
    }

    public void setIsCkp2(String isCkp2) {
        this.isCkp2 = isCkp2;
    }

    public String getIsCkp3() {
        return this.isCkp3;
    }

    public BidProject isCkp3(String isCkp3) {
        this.setIsCkp3(isCkp3);
        return this;
    }

    public void setIsCkp3(String isCkp3) {
        this.isCkp3 = isCkp3;
    }

    public BigDecimal getCkpMoney() {
        return this.ckpMoney;
    }

    public BidProject ckpMoney(BigDecimal ckpMoney) {
        this.setCkpMoney(ckpMoney);
        return this;
    }

    public void setCkpMoney(BigDecimal ckpMoney) {
        this.ckpMoney = ckpMoney;
    }

    public String getCkpUnit() {
        return this.ckpUnit;
    }

    public BidProject ckpUnit(String ckpUnit) {
        this.setCkpUnit(ckpUnit);
        return this;
    }

    public void setCkpUnit(String ckpUnit) {
        this.ckpUnit = ckpUnit;
    }

    public LocalDate getCkpDate() {
        return this.ckpDate;
    }

    public BidProject ckpDate(LocalDate ckpDate) {
        this.setCkpDate(ckpDate);
        return this;
    }

    public void setCkpDate(LocalDate ckpDate) {
        this.ckpDate = ckpDate;
    }

    public BigDecimal getCkpCnt() {
        return this.ckpCnt;
    }

    public BidProject ckpCnt(BigDecimal ckpCnt) {
        this.setCkpCnt(ckpCnt);
        return this;
    }

    public void setCkpCnt(BigDecimal ckpCnt) {
        this.ckpCnt = ckpCnt;
    }

    public String getCkpNum() {
        return this.ckpNum;
    }

    public BidProject ckpNum(String ckpNum) {
        this.setCkpNum(ckpNum);
        return this;
    }

    public void setCkpNum(String ckpNum) {
        this.ckpNum = ckpNum;
    }

    public String getCkaUnit() {
        return this.ckaUnit;
    }

    public BidProject ckaUnit(String ckaUnit) {
        this.setCkaUnit(ckaUnit);
        return this;
    }

    public void setCkaUnit(String ckaUnit) {
        this.ckaUnit = ckaUnit;
    }

    public LocalDate getCkaDate() {
        return this.ckaDate;
    }

    public BidProject ckaDate(LocalDate ckaDate) {
        this.setCkaDate(ckaDate);
        return this;
    }

    public void setCkaDate(LocalDate ckaDate) {
        this.ckaDate = ckaDate;
    }

    public BigDecimal getCkaCnt() {
        return this.ckaCnt;
    }

    public BidProject ckaCnt(BigDecimal ckaCnt) {
        this.setCkaCnt(ckaCnt);
        return this;
    }

    public void setCkaCnt(BigDecimal ckaCnt) {
        this.ckaCnt = ckaCnt;
    }

    public String getCkaNum() {
        return this.ckaNum;
    }

    public BidProject ckaNum(String ckaNum) {
        this.setCkaNum(ckaNum);
        return this;
    }

    public void setCkaNum(String ckaNum) {
        this.ckaNum = ckaNum;
    }

    public String getCkdUnit() {
        return this.ckdUnit;
    }

    public BidProject ckdUnit(String ckdUnit) {
        this.setCkdUnit(ckdUnit);
        return this;
    }

    public void setCkdUnit(String ckdUnit) {
        this.ckdUnit = ckdUnit;
    }

    public LocalDate getCkdDate() {
        return this.ckdDate;
    }

    public BidProject ckdDate(LocalDate ckdDate) {
        this.setCkdDate(ckdDate);
        return this;
    }

    public void setCkdDate(LocalDate ckdDate) {
        this.ckdDate = ckdDate;
    }

    public BigDecimal getCkdCnt() {
        return this.ckdCnt;
    }

    public BidProject ckdCnt(BigDecimal ckdCnt) {
        this.setCkdCnt(ckdCnt);
        return this;
    }

    public void setCkdCnt(BigDecimal ckdCnt) {
        this.ckdCnt = ckdCnt;
    }

    public String getCkdNum() {
        return this.ckdNum;
    }

    public BidProject ckdNum(String ckdNum) {
        this.setCkdNum(ckdNum);
        return this;
    }

    public void setCkdNum(String ckdNum) {
        this.ckdNum = ckdNum;
    }

    public String getWorkMemo() {
        return this.workMemo;
    }

    public BidProject workMemo(String workMemo) {
        this.setWorkMemo(workMemo);
        return this;
    }

    public void setWorkMemo(String workMemo) {
        this.workMemo = workMemo;
    }

    public String getHotLine() {
        return this.hotLine;
    }

    public BidProject hotLine(String hotLine) {
        this.setHotLine(hotLine);
        return this;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public String getLnkCode2() {
        return this.lnkCode2;
    }

    public BidProject lnkCode2(String lnkCode2) {
        this.setLnkCode2(lnkCode2);
        return this;
    }

    public void setLnkCode2(String lnkCode2) {
        this.lnkCode2 = lnkCode2;
    }

    public String getLnkCode3() {
        return this.lnkCode3;
    }

    public BidProject lnkCode3(String lnkCode3) {
        this.setLnkCode3(lnkCode3);
        return this;
    }

    public void setLnkCode3(String lnkCode3) {
        this.lnkCode3 = lnkCode3;
    }

    public String getLnkNo2() {
        return this.lnkNo2;
    }

    public BidProject lnkNo2(String lnkNo2) {
        this.setLnkNo2(lnkNo2);
        return this;
    }

    public void setLnkNo2(String lnkNo2) {
        this.lnkNo2 = lnkNo2;
    }

    public String getCcnt1() {
        return this.ccnt1;
    }

    public BidProject ccnt1(String ccnt1) {
        this.setCcnt1(ccnt1);
        return this;
    }

    public void setCcnt1(String ccnt1) {
        this.ccnt1 = ccnt1;
    }

    public String getTypeCode2() {
        return this.typeCode2;
    }

    public BidProject typeCode2(String typeCode2) {
        this.setTypeCode2(typeCode2);
        return this;
    }

    public void setTypeCode2(String typeCode2) {
        this.typeCode2 = typeCode2;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public BidProject deptName(String deptName) {
        this.setDeptName(deptName);
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAopCnt() {
        return this.aopCnt;
    }

    public BidProject aopCnt(String aopCnt) {
        this.setAopCnt(aopCnt);
        return this;
    }

    public void setAopCnt(String aopCnt) {
        this.aopCnt = aopCnt;
    }

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    public BidProject uniqueKey(String uniqueKey) {
        this.setUniqueKey(uniqueKey);
        return this;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getIsBidstr() {
        return this.isBidstr;
    }

    public BidProject isBidstr(String isBidstr) {
        this.setIsBidstr(isBidstr);
        return this;
    }

    public void setIsBidstr(String isBidstr) {
        this.isBidstr = isBidstr;
    }

    public String getIsEngineer() {
        return this.isEngineer;
    }

    public BidProject isEngineer(String isEngineer) {
        this.setIsEngineer(isEngineer);
        return this;
    }

    public void setIsEngineer(String isEngineer) {
        this.isEngineer = isEngineer;
    }

    public String getName0() {
        return this.name0;
    }

    public BidProject name0(String name0) {
        this.setName0(name0);
        return this;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public BigDecimal getCtSum0() {
        return this.ctSum0;
    }

    public BidProject ctSum0(BigDecimal ctSum0) {
        this.setCtSum0(ctSum0);
        return this;
    }

    public void setCtSum0(BigDecimal ctSum0) {
        this.ctSum0 = ctSum0;
    }

    public BigDecimal getBdgt1_0() {
        return this.bdgt1_0;
    }

    public BidProject bdgt10(BigDecimal bdgt10) {
        this.setBdgt1_0(bdgt10);
        return this;
    }

    public void setBdgt1_0(BigDecimal bdgt1_0) {
        this.bdgt1_0 = bdgt1_0;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public BidProject startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidProject endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getBatch() {
        return this.batch;
    }

    public BidProject batch(String batch) {
        this.setBatch(batch);
        return this;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getNocntDay() {
        return this.nocntDay;
    }

    public BidProject nocntDay(Integer nocntDay) {
        this.setNocntDay(nocntDay);
        return this;
    }

    public void setNocntDay(Integer nocntDay) {
        this.nocntDay = nocntDay;
    }

    public String getStatus() {
        return this.status;
    }

    public BidProject status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAokDate() {
        return this.aokDate;
    }

    public BidProject aokDate(LocalDate aokDate) {
        this.setAokDate(aokDate);
        return this;
    }

    public void setAokDate(LocalDate aokDate) {
        this.aokDate = aokDate;
    }

    public BigDecimal getFcost3() {
        return this.fcost3;
    }

    public BidProject fcost3(BigDecimal fcost3) {
        this.setFcost3(fcost3);
        return this;
    }

    public void setFcost3(BigDecimal fcost3) {
        this.fcost3 = fcost3;
    }

    public Integer getFinWays() {
        return this.finWays;
    }

    public BidProject finWays(Integer finWays) {
        this.setFinWays(finWays);
        return this;
    }

    public void setFinWays(Integer finWays) {
        this.finWays = finWays;
    }

    public Integer getAworkDay() {
        return this.aworkDay;
    }

    public BidProject aworkDay(Integer aworkDay) {
        this.setAworkDay(aworkDay);
        return this;
    }

    public void setAworkDay(Integer aworkDay) {
        this.aworkDay = aworkDay;
    }

    public Integer getFastDay() {
        return this.fastDay;
    }

    public BidProject fastDay(Integer fastDay) {
        this.setFastDay(fastDay);
        return this;
    }

    public void setFastDay(Integer fastDay) {
        this.fastDay = fastDay;
    }

    public Integer getOverDay() {
        return this.overDay;
    }

    public BidProject overDay(Integer overDay) {
        this.setOverDay(overDay);
        return this;
    }

    public void setOverDay(Integer overDay) {
        this.overDay = overDay;
    }

    public Integer getOverDayY() {
        return this.overDayY;
    }

    public BidProject overDayY(Integer overDayY) {
        this.setOverDayY(overDayY);
        return this;
    }

    public void setOverDayY(Integer overDayY) {
        this.overDayY = overDayY;
    }

    public BigDecimal getOverFee() {
        return this.overFee;
    }

    public BidProject overFee(BigDecimal overFee) {
        this.setOverFee(overFee);
        return this;
    }

    public void setOverFee(BigDecimal overFee) {
        this.overFee = overFee;
    }

    public BigDecimal getOtherFee() {
        return this.otherFee;
    }

    public BidProject otherFee(BigDecimal otherFee) {
        this.setOtherFee(otherFee);
        return this;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getPunshFee() {
        return this.punshFee;
    }

    public BidProject punshFee(BigDecimal punshFee) {
        this.setPunshFee(punshFee);
        return this;
    }

    public void setPunshFee(BigDecimal punshFee) {
        this.punshFee = punshFee;
    }

    public String getTm2x() {
        return this.tm2x;
    }

    public BidProject tm2x(String tm2x) {
        this.setTm2x(tm2x);
        return this;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return this.tm2y;
    }

    public BidProject tm2y(String tm2y) {
        this.setTm2y(tm2y);
        return this;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getTm2x2() {
        return this.tm2x2;
    }

    public BidProject tm2x2(String tm2x2) {
        this.setTm2x2(tm2x2);
        return this;
    }

    public void setTm2x2(String tm2x2) {
        this.tm2x2 = tm2x2;
    }

    public String getTm2y2() {
        return this.tm2y2;
    }

    public BidProject tm2y2(String tm2y2) {
        this.setTm2y2(tm2y2);
        return this;
    }

    public void setTm2y2(String tm2y2) {
        this.tm2y2 = tm2y2;
    }

    public String getTm2x3() {
        return this.tm2x3;
    }

    public BidProject tm2x3(String tm2x3) {
        this.setTm2x3(tm2x3);
        return this;
    }

    public void setTm2x3(String tm2x3) {
        this.tm2x3 = tm2x3;
    }

    public String getTm2y3() {
        return this.tm2y3;
    }

    public BidProject tm2y3(String tm2y3) {
        this.setTm2y3(tm2y3);
        return this;
    }

    public void setTm2y3(String tm2y3) {
        this.tm2y3 = tm2y3;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidProject createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidProject createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidProject updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidProject updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidProject dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidProject confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidProject uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getCan() {
        return this.can;
    }

    public BidProject can(String can) {
        this.setCan(can);
        return this;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getSrut() {
        return this.srut;
    }

    public BidProject srut(String srut) {
        this.setSrut(srut);
        return this;
    }

    public void setSrut(String srut) {
        this.srut = srut;
    }

    public BigDecimal getAnoct() {
        return this.anoct;
    }

    public BidProject anoct(BigDecimal anoct) {
        this.setAnoct(anoct);
        return this;
    }

    public void setAnoct(BigDecimal anoct) {
        this.anoct = anoct;
    }

    public String getCepdNo() {
        return this.cepdNo;
    }

    public BidProject cepdNo(String cepdNo) {
        this.setCepdNo(cepdNo);
        return this;
    }

    public void setCepdNo(String cepdNo) {
        this.cepdNo = cepdNo;
    }

    public String getSubNo() {
        return this.subNo;
    }

    public BidProject subNo(String subNo) {
        this.setSubNo(subNo);
        return this;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getScnt() {
        return this.scnt;
    }

    public BidProject scnt(String scnt) {
        this.setScnt(scnt);
        return this;
    }

    public void setScnt(String scnt) {
        this.scnt = scnt;
    }

    public String getDsdm() {
        return this.dsdm;
    }

    public BidProject dsdm(String dsdm) {
        this.setDsdm(dsdm);
        return this;
    }

    public void setDsdm(String dsdm) {
        this.dsdm = dsdm;
    }

    public String getPreson() {
        return this.preson;
    }

    public BidProject preson(String preson) {
        this.setPreson(preson);
        return this;
    }

    public void setPreson(String preson) {
        this.preson = preson;
    }

    public LocalDate getPsignDate() {
        return this.psignDate;
    }

    public BidProject psignDate(LocalDate psignDate) {
        this.setPsignDate(psignDate);
        return this;
    }

    public void setPsignDate(LocalDate psignDate) {
        this.psignDate = psignDate;
    }

    public LocalDate getAsignDate() {
        return this.asignDate;
    }

    public BidProject asignDate(LocalDate asignDate) {
        this.setAsignDate(asignDate);
        return this;
    }

    public void setAsignDate(LocalDate asignDate) {
        this.asignDate = asignDate;
    }

    public LocalDate getTrDate() {
        return this.trDate;
    }

    public BidProject trDate(LocalDate trDate) {
        this.setTrDate(trDate);
        return this;
    }

    public void setTrDate(LocalDate trDate) {
        this.trDate = trDate;
    }

    public BigDecimal getConfIn() {
        return this.confIn;
    }

    public BidProject confIn(BigDecimal confIn) {
        this.setConfIn(confIn);
        return this;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getuKey() {
        return this.uKey;
    }

    public BidProject uKey(String uKey) {
        this.setuKey(uKey);
        return this;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    public String getTpcoth() {
        return this.tpcoth;
    }

    public BidProject tpcoth(String tpcoth) {
        this.setTpcoth(tpcoth);
        return this;
    }

    public void setTpcoth(String tpcoth) {
        this.tpcoth = tpcoth;
    }

    public BigDecimal getPcmct() {
        return this.pcmct;
    }

    public BidProject pcmct(BigDecimal pcmct) {
        this.setPcmct(pcmct);
        return this;
    }

    public void setPcmct(BigDecimal pcmct) {
        this.pcmct = pcmct;
    }

    public String getWkutOld() {
        return this.wkutOld;
    }

    public BidProject wkutOld(String wkutOld) {
        this.setWkutOld(wkutOld);
        return this;
    }

    public void setWkutOld(String wkutOld) {
        this.wkutOld = wkutOld;
    }

    public String getPccMemo() {
        return this.pccMemo;
    }

    public BidProject pccMemo(String pccMemo) {
        this.setPccMemo(pccMemo);
        return this;
    }

    public void setPccMemo(String pccMemo) {
        this.pccMemo = pccMemo;
    }

    public BigDecimal getBgbSumt() {
        return this.bgbSumt;
    }

    public BidProject bgbSumt(BigDecimal bgbSumt) {
        this.setBgbSumt(bgbSumt);
        return this;
    }

    public void setBgbSumt(BigDecimal bgbSumt) {
        this.bgbSumt = bgbSumt;
    }

    public LocalDate getBgbDate() {
        return this.bgbDate;
    }

    public BidProject bgbDate(LocalDate bgbDate) {
        this.setBgbDate(bgbDate);
        return this;
    }

    public void setBgbDate(LocalDate bgbDate) {
        this.bgbDate = bgbDate;
    }

    public String getMainItem() {
        return this.mainItem;
    }

    public BidProject mainItem(String mainItem) {
        this.setMainItem(mainItem);
        return this;
    }

    public void setMainItem(String mainItem) {
        this.mainItem = mainItem;
    }

    public String getmDesc() {
        return this.mDesc;
    }

    public BidProject mDesc(String mDesc) {
        this.setmDesc(mDesc);
        return this;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmRmk() {
        return this.mRmk;
    }

    public BidProject mRmk(String mRmk) {
        this.setmRmk(mRmk);
        return this;
    }

    public void setmRmk(String mRmk) {
        this.mRmk = mRmk;
    }

    public BigDecimal getPmrSumt() {
        return this.pmrSumt;
    }

    public BidProject pmrSumt(BigDecimal pmrSumt) {
        this.setPmrSumt(pmrSumt);
        return this;
    }

    public void setPmrSumt(BigDecimal pmrSumt) {
        this.pmrSumt = pmrSumt;
    }

    public LocalDate getPmrDate() {
        return this.pmrDate;
    }

    public BidProject pmrDate(LocalDate pmrDate) {
        this.setPmrDate(pmrDate);
        return this;
    }

    public void setPmrDate(LocalDate pmrDate) {
        this.pmrDate = pmrDate;
    }

    public String getPmrResm() {
        return this.pmrResm;
    }

    public BidProject pmrResm(String pmrResm) {
        this.setPmrResm(pmrResm);
        return this;
    }

    public void setPmrResm(String pmrResm) {
        this.pmrResm = pmrResm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidProject)) return false;
        BidProject that = (BidProject) o;
        return wkut.equals(that.wkut) && prjno.equals(that.prjno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidProject{" +
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
                ", bdgt10=" + getBdgt1_0() +
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
