package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidPlnbas.
 */
@Entity
@IdClass(BidPlnbasPrimaryKey.class)
@Table(name = "bid_plnbas")
public class BidPlnbas implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @NotNull
    @Size(max = 21)
    @Column(name = "plncode", length = 21, nullable = false)
    private String plncode;

    @Id
    @NotNull
    @Size(max = 20)
    @Column(name = "plnno", length = 20, nullable = false)
    private String plnno;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 2)
    @Column(name = "typcode", length = 2)
    private String typcode;

    @Size(max = 1)
    @Column(name = "mnthly", length = 1)
    private String mnthly;

    @Size(max = 10)
    @Column(name = "man", length = 10)
    private String man;

    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;

    @Size(max = 10)
    @Column(name = "boss", length = 10)
    private String boss;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @Size(max = 21)
    @Column(name = "boscode", length = 21)
    private String boscode;

    @Size(max = 21)
    @Column(name = "sbcod_1", length = 21)
    private String sbcod1;

    @Size(max = 21)
    @Column(name = "sbcod_2", length = 21)
    private String sbcod2;

    @Size(max = 21)
    @Column(name = "sbcod_3", length = 21)
    private String sbcod3;

    @Size(max = 5)
    @Column(name = "bdate", length = 5)
    private String bdate;

    @Size(max = 5)
    @Column(name = "edate", length = 5)
    private String edate;

    @Column(name = "totamt", precision = 21, scale = 2)
    private BigDecimal totamt;

    @Column(name = "dexp", precision = 21, scale = 2)
    private BigDecimal dexp;

    @Column(name = "lexp", precision = 21, scale = 2)
    private BigDecimal lexp;

    @Column(name = "eexp", precision = 21, scale = 2)
    private BigDecimal eexp;

    @Column(name = "cexp", precision = 21, scale = 2)
    private BigDecimal cexp;

    @Column(name = "jexp", precision = 21, scale = 2)
    private BigDecimal jexp;

    @Column(name = "oexp", precision = 21, scale = 2)
    private BigDecimal oexp;

    @Column(name = "cnslt", precision = 21, scale = 2)
    private BigDecimal cnslt;

    @Size(max = 30)
    @Column(name = "place", length = 30)
    private String place;

    @Size(max = 4000)
    @Column(name = "idea", length = 4000)
    private String idea;

    @Size(max = 4000)
    @Column(name = "goal", length = 4000)
    private String goal;

    @Size(max = 4000)
    @Column(name = "prpse", length = 4000)
    private String prpse;

    @Size(max = 2000)
    @Column(name = "point", length = 2000)
    private String point;

    @Size(max = 2000)
    @Column(name = "organ", length = 2000)
    private String organ;

    @Size(max = 2000)
    @Column(name = "budget", length = 2000)
    private String budget;

    @Size(max = 2000)
    @Column(name = "cntrl", length = 2000)
    private String cntrl;

    @Size(max = 2000)
    @Column(name = "dyrgt", length = 2000)
    private String dyrgt;

    @Size(max = 2000)
    @Column(name = "other", length = 2000)
    private String other;

    @Size(max = 21)
    @Column(name = "gblcode", length = 21)
    private String gblcode;

    @Size(max = 20)
    @Column(name = "gblno", length = 20)
    private String gblno;

    @Size(max = 3)
    @Column(name = "othcod", length = 3)
    private String othcod;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Size(max = 10)
    @Column(name = "cent_spon", length = 10)
    private String centSpon;

    @Size(max = 10)
    @Column(name = "cepd_no", length = 10)
    private String cepdNo;

    @Size(max = 60)
    @Column(name = "e_mail", length = 60)
    private String eMail;

    @Column(name = "conf_out", precision = 21, scale = 2)
    private BigDecimal confOut;

    @Column(name = "conf_in", precision = 21, scale = 2)
    private BigDecimal confIn;

    @Size(max = 1)
    @Column(name = "pln_type", length = 1)
    private String plnType;

    @Column(name = "prj_totq", precision = 21, scale = 2)
    private BigDecimal prjTotq;

    @Size(max = 1)
    @Column(name = "pln_type_2", length = 1)
    private String plnType2;

    @Column(name = "prj_totq_1", precision = 21, scale = 2)
    private BigDecimal prjTotq1;

    @Column(name = "prj_totq_2", precision = 21, scale = 2)
    private BigDecimal prjTotq2;

    @Column(name = "prj_totq_3", precision = 21, scale = 2)
    private BigDecimal prjTotq3;

    @Size(max = 2)
    @Column(name = "work_type", length = 2)
    private String workType;

    @Size(max = 1)
    @Column(name = "hm_90", length = 1)
    private String hm90;

    @Size(max = 4)
    @Column(name = "pcode", length = 4)
    private String pcode;

    @Size(max = 4)
    @Column(name = "pcode_2", length = 4)
    private String pcode2;

    @Size(max = 4)
    @Column(name = "pcode_3", length = 4)
    private String pcode3;

    @Size(max = 4)
    @Column(name = "pcode_4", length = 4)
    private String pcode4;

    @Size(max = 4)
    @Column(name = "pcode_5", length = 4)
    private String pcode5;

    @Size(max = 4)
    @Column(name = "pcode_6", length = 4)
    private String pcode6;

    @Size(max = 4)
    @Column(name = "pcode_7", length = 4)
    private String pcode7;

    @Size(max = 4)
    @Column(name = "pcode_1", length = 4)
    private String pcode1;

    @Size(max = 1)
    @Column(name = "hm_91", length = 1)
    private String hm91;

    @Size(max = 60)
    @Column(name = "u_ip", length = 60)
    private String uIp;

    @Size(max = 15)
    @Column(name = "sixyrpln", length = 15)
    private String sixyrpln;

    @Size(max = 1)
    @Column(name = "plnkind_1", length = 1)
    private String plnkind1;

    @Size(max = 1)
    @Column(name = "plnkind_2", length = 1)
    private String plnkind2;

    @Size(max = 1)
    @Column(name = "plnkind_3", length = 1)
    private String plnkind3;

    @Size(max = 1)
    @Column(name = "plnkind_4", length = 1)
    private String plnkind4;

    @Size(max = 1)
    @Column(name = "erc_921", length = 1)
    private String erc921;

    @Size(max = 1)
    @Column(name = "hm_92", length = 1)
    private String hm92;

    @Size(max = 30)
    @Column(name = "school", length = 30)
    private String school;

    @Size(max = 1)
    @Column(name = "can", length = 1)
    private String can;

    @Size(max = 1)
    @Column(name = "p_01", length = 1)
    private String p01;

    @Size(max = 1)
    @Column(name = "p_02", length = 1)
    private String p02;

    @Size(max = 1)
    @Column(name = "p_03", length = 1)
    private String p03;

    @Size(max = 1)
    @Column(name = "p_04", length = 1)
    private String p04;

    @Size(max = 1)
    @Column(name = "p_05", length = 1)
    private String p05;

    @Size(max = 1)
    @Column(name = "p_06", length = 1)
    private String p06;

    @Size(max = 1)
    @Column(name = "p_07", length = 1)
    private String p07;

    @Size(max = 1)
    @Column(name = "p_08", length = 1)
    private String p08;

    @Size(max = 1)
    @Column(name = "p_09", length = 1)
    private String p09;

    @Size(max = 1)
    @Column(name = "p_10", length = 1)
    private String p10;

    @Size(max = 1)
    @Column(name = "p_11", length = 1)
    private String p11;

    @Size(max = 1)
    @Column(name = "p_12", length = 1)
    private String p12;

    @Size(max = 1)
    @Column(name = "p_13", length = 1)
    private String p13;

    @Size(max = 1)
    @Column(name = "p_14", length = 1)
    private String p14;

    @Size(max = 1)
    @Column(name = "p_15", length = 1)
    private String p15;

    @Size(max = 1)
    @Column(name = "p_16", length = 1)
    private String p16;

    @Size(max = 1)
    @Column(name = "p_17", length = 1)
    private String p17;

    @Size(max = 1)
    @Column(name = "p_18", length = 1)
    private String p18;

    @Size(max = 1)
    @Column(name = "p_19", length = 1)
    private String p19;

    @Size(max = 1)
    @Column(name = "p_20", length = 1)
    private String p20;

    @Size(max = 1)
    @Column(name = "p_21", length = 1)
    private String p21;

    @Size(max = 1)
    @Column(name = "p_22", length = 1)
    private String p22;

    @Size(max = 1)
    @Column(name = "p_23", length = 1)
    private String p23;

    @Size(max = 1)
    @Column(name = "p_24", length = 1)
    private String p24;

    @Size(max = 1)
    @Column(name = "p_25", length = 1)
    private String p25;

    @Size(max = 1)
    @Column(name = "sgn", length = 1)
    private String sgn;

    @Column(name = "totamt_2", precision = 21, scale = 2)
    private BigDecimal totamt2;

    @Size(max = 1)
    @Column(name = "hm_93", length = 1)
    private String hm93;

    @Column(name = "ototamt", precision = 21, scale = 2)
    private BigDecimal ototamt;

    @Column(name = "obdate")
    private LocalDate obdate;

    @Column(name = "oedate")
    private LocalDate oedate;

    @Size(max = 1)
    @Column(name = "plan_92", length = 1)
    private String plan92;

    @Size(max = 1)
    @Column(name = "hm_94", length = 1)
    private String hm94;

    @Size(max = 12)
    @Column(name = "new_10", length = 12)
    private String new10;

    @Size(max = 1)
    @Column(name = "hm_95", length = 1)
    private String hm95;

    @Size(max = 1)
    @Column(name = "hm_96", length = 1)
    private String hm96;

    @Size(max = 10)
    @Column(name = "rdec_no", length = 10)
    private String rdecNo;

    @Size(max = 1)
    @Column(name = "hm_97", length = 1)
    private String hm97;

    @Size(max = 1)
    @Column(name = "hm_98", length = 1)
    private String hm98;

    @Size(max = 1)
    @Column(name = "hm_99", length = 1)
    private String hm99;

    @Size(max = 1)
    @Column(name = "hm_100", length = 1)
    private String hm100;

    @Size(max = 1)
    @Column(name = "kd_97", length = 1)
    private String kd97;

    @Size(max = 1)
    @Column(name = "plnkind_5", length = 1)
    private String plnkind5;

    @Size(max = 1)
    @Column(name = "plnkind_6", length = 1)
    private String plnkind6;

    @Size(max = 1)
    @Column(name = "d_open", length = 1)
    private String dOpen;

    @Size(max = 2)
    @Column(name = "typcode_2", length = 2)
    private String typcode2;

    @Size(max = 200)
    @Column(name = "urls", length = 200)
    private String urls;

    @Size(max = 1)
    @Column(name = "plnkind_598", length = 1)
    private String plnkind598;

    @Size(max = 1)
    @Column(name = "hm_101", length = 1)
    private String hm101;

    @Size(max = 1)
    @Column(name = "temp", length = 1)
    private String temp;

    @Size(max = 1)
    @Column(name = "hm_102", length = 1)
    private String hm102;

    @Size(max = 1)
    @Column(name = "hm_103", length = 1)
    private String hm103;

    @Size(max = 1)
    @Column(name = "hm_104", length = 1)
    private String hm104;

    @Size(max = 1)
    @Column(name = "hm_105", length = 1)
    private String hm105;

    @Size(max = 1)
    @Column(name = "hm_106", length = 1)
    private String hm106;

    @Size(max = 1)
    @Column(name = "plnkind_7", length = 1)
    private String plnkind7;

    @Size(max = 1)
    @Column(name = "plnkind_8", length = 1)
    private String plnkind8;

    @Size(max = 1)
    @Column(name = "plnkind_9", length = 1)
    private String plnkind9;

    @Size(max = 1)
    @Column(name = "plnkinda", length = 1)
    private String plnkinda;

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

    public BidPlnbasPrimaryKey getId() {
        return new BidPlnbasPrimaryKey(plncode, plnno);
    }

    public BidPlnbas id(BidPlnbasPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidPlnbasPrimaryKey id) {
        this.plncode = id.getPlncode();
        this.plnno = id.getPlnno();
    }

    public String getPlncode() {
        return this.plncode;
    }

    public BidPlnbas plncode(String plncode) {
        this.setPlncode(plncode);
        return this;
    }

    public void setPlncode(String plncode) {
        this.plncode = plncode;
    }

    public String getPlnno() {
        return this.plnno;
    }

    public BidPlnbas plnno(String plnno) {
        this.setPlnno(plnno);
        return this;
    }

    public void setPlnno(String plnno) {
        this.plnno = plnno;
    }

    public String getName() {
        return this.name;
    }

    public BidPlnbas name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypcode() {
        return this.typcode;
    }

    public BidPlnbas typcode(String typcode) {
        this.setTypcode(typcode);
        return this;
    }

    public void setTypcode(String typcode) {
        this.typcode = typcode;
    }

    public String getMnthly() {
        return this.mnthly;
    }

    public BidPlnbas mnthly(String mnthly) {
        this.setMnthly(mnthly);
        return this;
    }

    public void setMnthly(String mnthly) {
        this.mnthly = mnthly;
    }

    public String getMan() {
        return this.man;
    }

    public BidPlnbas man(String man) {
        this.setMan(man);
        return this;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return this.tel;
    }

    public BidPlnbas tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBoss() {
        return this.boss;
    }

    public BidPlnbas boss(String boss) {
        this.setBoss(boss);
        return this;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getFax() {
        return this.fax;
    }

    public BidPlnbas fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBoscode() {
        return this.boscode;
    }

    public BidPlnbas boscode(String boscode) {
        this.setBoscode(boscode);
        return this;
    }

    public void setBoscode(String boscode) {
        this.boscode = boscode;
    }

    public String getSbcod1() {
        return this.sbcod1;
    }

    public BidPlnbas sbcod1(String sbcod1) {
        this.setSbcod1(sbcod1);
        return this;
    }

    public void setSbcod1(String sbcod1) {
        this.sbcod1 = sbcod1;
    }

    public String getSbcod2() {
        return this.sbcod2;
    }

    public BidPlnbas sbcod2(String sbcod2) {
        this.setSbcod2(sbcod2);
        return this;
    }

    public void setSbcod2(String sbcod2) {
        this.sbcod2 = sbcod2;
    }

    public String getSbcod3() {
        return this.sbcod3;
    }

    public BidPlnbas sbcod3(String sbcod3) {
        this.setSbcod3(sbcod3);
        return this;
    }

    public void setSbcod3(String sbcod3) {
        this.sbcod3 = sbcod3;
    }

    public String getBdate() {
        return this.bdate;
    }

    public BidPlnbas bdate(String bdate) {
        this.setBdate(bdate);
        return this;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getEdate() {
        return this.edate;
    }

    public BidPlnbas edate(String edate) {
        this.setEdate(edate);
        return this;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public BigDecimal getTotamt() {
        return this.totamt;
    }

    public BidPlnbas totamt(BigDecimal totamt) {
        this.setTotamt(totamt);
        return this;
    }

    public void setTotamt(BigDecimal totamt) {
        this.totamt = totamt;
    }

    public BigDecimal getDexp() {
        return this.dexp;
    }

    public BidPlnbas dexp(BigDecimal dexp) {
        this.setDexp(dexp);
        return this;
    }

    public void setDexp(BigDecimal dexp) {
        this.dexp = dexp;
    }

    public BigDecimal getLexp() {
        return this.lexp;
    }

    public BidPlnbas lexp(BigDecimal lexp) {
        this.setLexp(lexp);
        return this;
    }

    public void setLexp(BigDecimal lexp) {
        this.lexp = lexp;
    }

    public BigDecimal getEexp() {
        return this.eexp;
    }

    public BidPlnbas eexp(BigDecimal eexp) {
        this.setEexp(eexp);
        return this;
    }

    public void setEexp(BigDecimal eexp) {
        this.eexp = eexp;
    }

    public BigDecimal getCexp() {
        return this.cexp;
    }

    public BidPlnbas cexp(BigDecimal cexp) {
        this.setCexp(cexp);
        return this;
    }

    public void setCexp(BigDecimal cexp) {
        this.cexp = cexp;
    }

    public BigDecimal getJexp() {
        return this.jexp;
    }

    public BidPlnbas jexp(BigDecimal jexp) {
        this.setJexp(jexp);
        return this;
    }

    public void setJexp(BigDecimal jexp) {
        this.jexp = jexp;
    }

    public BigDecimal getOexp() {
        return this.oexp;
    }

    public BidPlnbas oexp(BigDecimal oexp) {
        this.setOexp(oexp);
        return this;
    }

    public void setOexp(BigDecimal oexp) {
        this.oexp = oexp;
    }

    public BigDecimal getCnslt() {
        return this.cnslt;
    }

    public BidPlnbas cnslt(BigDecimal cnslt) {
        this.setCnslt(cnslt);
        return this;
    }

    public void setCnslt(BigDecimal cnslt) {
        this.cnslt = cnslt;
    }

    public String getPlace() {
        return this.place;
    }

    public BidPlnbas place(String place) {
        this.setPlace(place);
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIdea() {
        return this.idea;
    }

    public BidPlnbas idea(String idea) {
        this.setIdea(idea);
        return this;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getGoal() {
        return this.goal;
    }

    public BidPlnbas goal(String goal) {
        this.setGoal(goal);
        return this;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPrpse() {
        return this.prpse;
    }

    public BidPlnbas prpse(String prpse) {
        this.setPrpse(prpse);
        return this;
    }

    public void setPrpse(String prpse) {
        this.prpse = prpse;
    }

    public String getPoint() {
        return this.point;
    }

    public BidPlnbas point(String point) {
        this.setPoint(point);
        return this;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getOrgan() {
        return this.organ;
    }

    public BidPlnbas organ(String organ) {
        this.setOrgan(organ);
        return this;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getBudget() {
        return this.budget;
    }

    public BidPlnbas budget(String budget) {
        this.setBudget(budget);
        return this;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCntrl() {
        return this.cntrl;
    }

    public BidPlnbas cntrl(String cntrl) {
        this.setCntrl(cntrl);
        return this;
    }

    public void setCntrl(String cntrl) {
        this.cntrl = cntrl;
    }

    public String getDyrgt() {
        return this.dyrgt;
    }

    public BidPlnbas dyrgt(String dyrgt) {
        this.setDyrgt(dyrgt);
        return this;
    }

    public void setDyrgt(String dyrgt) {
        this.dyrgt = dyrgt;
    }

    public String getOther() {
        return this.other;
    }

    public BidPlnbas other(String other) {
        this.setOther(other);
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getGblcode() {
        return this.gblcode;
    }

    public BidPlnbas gblcode(String gblcode) {
        this.setGblcode(gblcode);
        return this;
    }

    public void setGblcode(String gblcode) {
        this.gblcode = gblcode;
    }

    public String getGblno() {
        return this.gblno;
    }

    public BidPlnbas gblno(String gblno) {
        this.setGblno(gblno);
        return this;
    }

    public void setGblno(String gblno) {
        this.gblno = gblno;
    }

    public String getOthcod() {
        return this.othcod;
    }

    public BidPlnbas othcod(String othcod) {
        this.setOthcod(othcod);
        return this;
    }

    public void setOthcod(String othcod) {
        this.othcod = othcod;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidPlnbas dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getCentSpon() {
        return this.centSpon;
    }

    public BidPlnbas centSpon(String centSpon) {
        this.setCentSpon(centSpon);
        return this;
    }

    public void setCentSpon(String centSpon) {
        this.centSpon = centSpon;
    }

    public String getCepdNo() {
        return this.cepdNo;
    }

    public BidPlnbas cepdNo(String cepdNo) {
        this.setCepdNo(cepdNo);
        return this;
    }

    public void setCepdNo(String cepdNo) {
        this.cepdNo = cepdNo;
    }

    public String geteMail() {
        return this.eMail;
    }

    public BidPlnbas eMail(String eMail) {
        this.seteMail(eMail);
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getConfOut() {
        return this.confOut;
    }

    public BidPlnbas confOut(BigDecimal confOut) {
        this.setConfOut(confOut);
        return this;
    }

    public void setConfOut(BigDecimal confOut) {
        this.confOut = confOut;
    }

    public BigDecimal getConfIn() {
        return this.confIn;
    }

    public BidPlnbas confIn(BigDecimal confIn) {
        this.setConfIn(confIn);
        return this;
    }

    public void setConfIn(BigDecimal confIn) {
        this.confIn = confIn;
    }

    public String getPlnType() {
        return this.plnType;
    }

    public BidPlnbas plnType(String plnType) {
        this.setPlnType(plnType);
        return this;
    }

    public void setPlnType(String plnType) {
        this.plnType = plnType;
    }

    public BigDecimal getPrjTotq() {
        return this.prjTotq;
    }

    public BidPlnbas prjTotq(BigDecimal prjTotq) {
        this.setPrjTotq(prjTotq);
        return this;
    }

    public void setPrjTotq(BigDecimal prjTotq) {
        this.prjTotq = prjTotq;
    }

    public String getPlnType2() {
        return this.plnType2;
    }

    public BidPlnbas plnType2(String plnType2) {
        this.setPlnType2(plnType2);
        return this;
    }

    public void setPlnType2(String plnType2) {
        this.plnType2 = plnType2;
    }

    public BigDecimal getPrjTotq1() {
        return this.prjTotq1;
    }

    public BidPlnbas prjTotq1(BigDecimal prjTotq1) {
        this.setPrjTotq1(prjTotq1);
        return this;
    }

    public void setPrjTotq1(BigDecimal prjTotq1) {
        this.prjTotq1 = prjTotq1;
    }

    public BigDecimal getPrjTotq2() {
        return this.prjTotq2;
    }

    public BidPlnbas prjTotq2(BigDecimal prjTotq2) {
        this.setPrjTotq2(prjTotq2);
        return this;
    }

    public void setPrjTotq2(BigDecimal prjTotq2) {
        this.prjTotq2 = prjTotq2;
    }

    public BigDecimal getPrjTotq3() {
        return this.prjTotq3;
    }

    public BidPlnbas prjTotq3(BigDecimal prjTotq3) {
        this.setPrjTotq3(prjTotq3);
        return this;
    }

    public void setPrjTotq3(BigDecimal prjTotq3) {
        this.prjTotq3 = prjTotq3;
    }

    public String getWorkType() {
        return this.workType;
    }

    public BidPlnbas workType(String workType) {
        this.setWorkType(workType);
        return this;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getHm90() {
        return this.hm90;
    }

    public BidPlnbas hm90(String hm90) {
        this.setHm90(hm90);
        return this;
    }

    public void setHm90(String hm90) {
        this.hm90 = hm90;
    }

    public String getPcode() {
        return this.pcode;
    }

    public BidPlnbas pcode(String pcode) {
        this.setPcode(pcode);
        return this;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPcode2() {
        return this.pcode2;
    }

    public BidPlnbas pcode2(String pcode2) {
        this.setPcode2(pcode2);
        return this;
    }

    public void setPcode2(String pcode2) {
        this.pcode2 = pcode2;
    }

    public String getPcode3() {
        return this.pcode3;
    }

    public BidPlnbas pcode3(String pcode3) {
        this.setPcode3(pcode3);
        return this;
    }

    public void setPcode3(String pcode3) {
        this.pcode3 = pcode3;
    }

    public String getPcode4() {
        return this.pcode4;
    }

    public BidPlnbas pcode4(String pcode4) {
        this.setPcode4(pcode4);
        return this;
    }

    public void setPcode4(String pcode4) {
        this.pcode4 = pcode4;
    }

    public String getPcode5() {
        return this.pcode5;
    }

    public BidPlnbas pcode5(String pcode5) {
        this.setPcode5(pcode5);
        return this;
    }

    public void setPcode5(String pcode5) {
        this.pcode5 = pcode5;
    }

    public String getPcode6() {
        return this.pcode6;
    }

    public BidPlnbas pcode6(String pcode6) {
        this.setPcode6(pcode6);
        return this;
    }

    public void setPcode6(String pcode6) {
        this.pcode6 = pcode6;
    }

    public String getPcode7() {
        return this.pcode7;
    }

    public BidPlnbas pcode7(String pcode7) {
        this.setPcode7(pcode7);
        return this;
    }

    public void setPcode7(String pcode7) {
        this.pcode7 = pcode7;
    }

    public String getPcode1() {
        return this.pcode1;
    }

    public BidPlnbas pcode1(String pcode1) {
        this.setPcode1(pcode1);
        return this;
    }

    public void setPcode1(String pcode1) {
        this.pcode1 = pcode1;
    }

    public String getHm91() {
        return this.hm91;
    }

    public BidPlnbas hm91(String hm91) {
        this.setHm91(hm91);
        return this;
    }

    public void setHm91(String hm91) {
        this.hm91 = hm91;
    }

    public String getuIp() {
        return this.uIp;
    }

    public BidPlnbas uIp(String uIp) {
        this.setuIp(uIp);
        return this;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getSixyrpln() {
        return this.sixyrpln;
    }

    public BidPlnbas sixyrpln(String sixyrpln) {
        this.setSixyrpln(sixyrpln);
        return this;
    }

    public void setSixyrpln(String sixyrpln) {
        this.sixyrpln = sixyrpln;
    }

    public String getPlnkind1() {
        return this.plnkind1;
    }

    public BidPlnbas plnkind1(String plnkind1) {
        this.setPlnkind1(plnkind1);
        return this;
    }

    public void setPlnkind1(String plnkind1) {
        this.plnkind1 = plnkind1;
    }

    public String getPlnkind2() {
        return this.plnkind2;
    }

    public BidPlnbas plnkind2(String plnkind2) {
        this.setPlnkind2(plnkind2);
        return this;
    }

    public void setPlnkind2(String plnkind2) {
        this.plnkind2 = plnkind2;
    }

    public String getPlnkind3() {
        return this.plnkind3;
    }

    public BidPlnbas plnkind3(String plnkind3) {
        this.setPlnkind3(plnkind3);
        return this;
    }

    public void setPlnkind3(String plnkind3) {
        this.plnkind3 = plnkind3;
    }

    public String getPlnkind4() {
        return this.plnkind4;
    }

    public BidPlnbas plnkind4(String plnkind4) {
        this.setPlnkind4(plnkind4);
        return this;
    }

    public void setPlnkind4(String plnkind4) {
        this.plnkind4 = plnkind4;
    }

    public String getErc921() {
        return this.erc921;
    }

    public BidPlnbas erc921(String erc921) {
        this.setErc921(erc921);
        return this;
    }

    public void setErc921(String erc921) {
        this.erc921 = erc921;
    }

    public String getHm92() {
        return this.hm92;
    }

    public BidPlnbas hm92(String hm92) {
        this.setHm92(hm92);
        return this;
    }

    public void setHm92(String hm92) {
        this.hm92 = hm92;
    }

    public String getSchool() {
        return this.school;
    }

    public BidPlnbas school(String school) {
        this.setSchool(school);
        return this;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCan() {
        return this.can;
    }

    public BidPlnbas can(String can) {
        this.setCan(can);
        return this;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getp01() {
        return this.p01;
    }

    public BidPlnbas p01(String p01) {
        this.setp01(p01);
        return this;
    }

    public void setp01(String p01) {
        this.p01 = p01;
    }

    public String getp02() {
        return this.p02;
    }

    public BidPlnbas p02(String p02) {
        this.setp02(p02);
        return this;
    }

    public void setp02(String p02) {
        this.p02 = p02;
    }

    public String getp03() {
        return this.p03;
    }

    public BidPlnbas p03(String p03) {
        this.setp03(p03);
        return this;
    }

    public void setp03(String p03) {
        this.p03 = p03;
    }

    public String getp04() {
        return this.p04;
    }

    public BidPlnbas p04(String p04) {
        this.setp04(p04);
        return this;
    }

    public void setp04(String p04) {
        this.p04 = p04;
    }

    public String getp05() {
        return this.p05;
    }

    public BidPlnbas p05(String p05) {
        this.setp05(p05);
        return this;
    }

    public void setp05(String p05) {
        this.p05 = p05;
    }

    public String getp06() {
        return this.p06;
    }

    public BidPlnbas p06(String p06) {
        this.setp06(p06);
        return this;
    }

    public void setp06(String p06) {
        this.p06 = p06;
    }

    public String getp07() {
        return this.p07;
    }

    public BidPlnbas p07(String p07) {
        this.setp07(p07);
        return this;
    }

    public void setp07(String p07) {
        this.p07 = p07;
    }

    public String getp08() {
        return this.p08;
    }

    public BidPlnbas p08(String p08) {
        this.setp08(p08);
        return this;
    }

    public void setp08(String p08) {
        this.p08 = p08;
    }

    public String getp09() {
        return this.p09;
    }

    public BidPlnbas p09(String p09) {
        this.setp09(p09);
        return this;
    }

    public void setp09(String p09) {
        this.p09 = p09;
    }

    public String getp10() {
        return this.p10;
    }

    public BidPlnbas p10(String p10) {
        this.setp10(p10);
        return this;
    }

    public void setp10(String p10) {
        this.p10 = p10;
    }

    public String getp11() {
        return this.p11;
    }

    public BidPlnbas p11(String p11) {
        this.setp11(p11);
        return this;
    }

    public void setp11(String p11) {
        this.p11 = p11;
    }

    public String getp12() {
        return this.p12;
    }

    public BidPlnbas p12(String p12) {
        this.setp12(p12);
        return this;
    }

    public void setp12(String p12) {
        this.p12 = p12;
    }

    public String getp13() {
        return this.p13;
    }

    public BidPlnbas p13(String p13) {
        this.setp13(p13);
        return this;
    }

    public void setp13(String p13) {
        this.p13 = p13;
    }

    public String getp14() {
        return this.p14;
    }

    public BidPlnbas p14(String p14) {
        this.setp14(p14);
        return this;
    }

    public void setp14(String p14) {
        this.p14 = p14;
    }

    public String getp15() {
        return this.p15;
    }

    public BidPlnbas p15(String p15) {
        this.setp15(p15);
        return this;
    }

    public void setp15(String p15) {
        this.p15 = p15;
    }

    public String getp16() {
        return this.p16;
    }

    public BidPlnbas p16(String p16) {
        this.setp16(p16);
        return this;
    }

    public void setp16(String p16) {
        this.p16 = p16;
    }

    public String getp17() {
        return this.p17;
    }

    public BidPlnbas p17(String p17) {
        this.setp17(p17);
        return this;
    }

    public void setp17(String p17) {
        this.p17 = p17;
    }

    public String getp18() {
        return this.p18;
    }

    public BidPlnbas p18(String p18) {
        this.setp18(p18);
        return this;
    }

    public void setp18(String p18) {
        this.p18 = p18;
    }

    public String getp19() {
        return this.p19;
    }

    public BidPlnbas p19(String p19) {
        this.setp19(p19);
        return this;
    }

    public void setp19(String p19) {
        this.p19 = p19;
    }

    public String getp20() {
        return this.p20;
    }

    public BidPlnbas p20(String p20) {
        this.setp20(p20);
        return this;
    }

    public void setp20(String p20) {
        this.p20 = p20;
    }

    public String getp21() {
        return this.p21;
    }

    public BidPlnbas p21(String p21) {
        this.setp21(p21);
        return this;
    }

    public void setp21(String p21) {
        this.p21 = p21;
    }

    public String getp22() {
        return this.p22;
    }

    public BidPlnbas p22(String p22) {
        this.setp22(p22);
        return this;
    }

    public void setp22(String p22) {
        this.p22 = p22;
    }

    public String getp23() {
        return this.p23;
    }

    public BidPlnbas p23(String p23) {
        this.setp23(p23);
        return this;
    }

    public void setp23(String p23) {
        this.p23 = p23;
    }

    public String getp24() {
        return this.p24;
    }

    public BidPlnbas p24(String p24) {
        this.setp24(p24);
        return this;
    }

    public void setp24(String p24) {
        this.p24 = p24;
    }

    public String getp25() {
        return this.p25;
    }

    public BidPlnbas p25(String p25) {
        this.setp25(p25);
        return this;
    }

    public void setp25(String p25) {
        this.p25 = p25;
    }

    public String getSgn() {
        return this.sgn;
    }

    public BidPlnbas sgn(String sgn) {
        this.setSgn(sgn);
        return this;
    }

    public void setSgn(String sgn) {
        this.sgn = sgn;
    }

    public BigDecimal getTotamt2() {
        return this.totamt2;
    }

    public BidPlnbas totamt2(BigDecimal totamt2) {
        this.setTotamt2(totamt2);
        return this;
    }

    public void setTotamt2(BigDecimal totamt2) {
        this.totamt2 = totamt2;
    }

    public String getHm93() {
        return this.hm93;
    }

    public BidPlnbas hm93(String hm93) {
        this.setHm93(hm93);
        return this;
    }

    public void setHm93(String hm93) {
        this.hm93 = hm93;
    }

    public BigDecimal getOtotamt() {
        return this.ototamt;
    }

    public BidPlnbas ototamt(BigDecimal ototamt) {
        this.setOtotamt(ototamt);
        return this;
    }

    public void setOtotamt(BigDecimal ototamt) {
        this.ototamt = ototamt;
    }

    public LocalDate getObdate() {
        return this.obdate;
    }

    public BidPlnbas obdate(LocalDate obdate) {
        this.setObdate(obdate);
        return this;
    }

    public void setObdate(LocalDate obdate) {
        this.obdate = obdate;
    }

    public LocalDate getOedate() {
        return this.oedate;
    }

    public BidPlnbas oedate(LocalDate oedate) {
        this.setOedate(oedate);
        return this;
    }

    public void setOedate(LocalDate oedate) {
        this.oedate = oedate;
    }

    public String getPlan92() {
        return this.plan92;
    }

    public BidPlnbas plan92(String plan92) {
        this.setPlan92(plan92);
        return this;
    }

    public void setPlan92(String plan92) {
        this.plan92 = plan92;
    }

    public String getHm94() {
        return this.hm94;
    }

    public BidPlnbas hm94(String hm94) {
        this.setHm94(hm94);
        return this;
    }

    public void setHm94(String hm94) {
        this.hm94 = hm94;
    }

    public String getNew10() {
        return this.new10;
    }

    public BidPlnbas new10(String new10) {
        this.setNew10(new10);
        return this;
    }

    public void setNew10(String new10) {
        this.new10 = new10;
    }

    public String getHm95() {
        return this.hm95;
    }

    public BidPlnbas hm95(String hm95) {
        this.setHm95(hm95);
        return this;
    }

    public void setHm95(String hm95) {
        this.hm95 = hm95;
    }

    public String getHm96() {
        return this.hm96;
    }

    public BidPlnbas hm96(String hm96) {
        this.setHm96(hm96);
        return this;
    }

    public void setHm96(String hm96) {
        this.hm96 = hm96;
    }

    public String getRdecNo() {
        return this.rdecNo;
    }

    public BidPlnbas rdecNo(String rdecNo) {
        this.setRdecNo(rdecNo);
        return this;
    }

    public void setRdecNo(String rdecNo) {
        this.rdecNo = rdecNo;
    }

    public String getHm97() {
        return this.hm97;
    }

    public BidPlnbas hm97(String hm97) {
        this.setHm97(hm97);
        return this;
    }

    public void setHm97(String hm97) {
        this.hm97 = hm97;
    }

    public String getHm98() {
        return this.hm98;
    }

    public BidPlnbas hm98(String hm98) {
        this.setHm98(hm98);
        return this;
    }

    public void setHm98(String hm98) {
        this.hm98 = hm98;
    }

    public String getHm99() {
        return this.hm99;
    }

    public BidPlnbas hm99(String hm99) {
        this.setHm99(hm99);
        return this;
    }

    public void setHm99(String hm99) {
        this.hm99 = hm99;
    }

    public String getHm100() {
        return this.hm100;
    }

    public BidPlnbas hm100(String hm100) {
        this.setHm100(hm100);
        return this;
    }

    public void setHm100(String hm100) {
        this.hm100 = hm100;
    }

    public String getKd97() {
        return this.kd97;
    }

    public BidPlnbas kd97(String kd97) {
        this.setKd97(kd97);
        return this;
    }

    public void setKd97(String kd97) {
        this.kd97 = kd97;
    }

    public String getPlnkind5() {
        return this.plnkind5;
    }

    public BidPlnbas plnkind5(String plnkind5) {
        this.setPlnkind5(plnkind5);
        return this;
    }

    public void setPlnkind5(String plnkind5) {
        this.plnkind5 = plnkind5;
    }

    public String getPlnkind6() {
        return this.plnkind6;
    }

    public BidPlnbas plnkind6(String plnkind6) {
        this.setPlnkind6(plnkind6);
        return this;
    }

    public void setPlnkind6(String plnkind6) {
        this.plnkind6 = plnkind6;
    }

    public String getdOpen() {
        return this.dOpen;
    }

    public BidPlnbas dOpen(String dOpen) {
        this.setdOpen(dOpen);
        return this;
    }

    public void setdOpen(String dOpen) {
        this.dOpen = dOpen;
    }

    public String getTypcode2() {
        return this.typcode2;
    }

    public BidPlnbas typcode2(String typcode2) {
        this.setTypcode2(typcode2);
        return this;
    }

    public void setTypcode2(String typcode2) {
        this.typcode2 = typcode2;
    }

    public String getUrls() {
        return this.urls;
    }

    public BidPlnbas urls(String urls) {
        this.setUrls(urls);
        return this;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getPlnkind598() {
        return this.plnkind598;
    }

    public BidPlnbas plnkind598(String plnkind598) {
        this.setPlnkind598(plnkind598);
        return this;
    }

    public void setPlnkind598(String plnkind598) {
        this.plnkind598 = plnkind598;
    }

    public String getHm101() {
        return this.hm101;
    }

    public BidPlnbas hm101(String hm101) {
        this.setHm101(hm101);
        return this;
    }

    public void setHm101(String hm101) {
        this.hm101 = hm101;
    }

    public String getTemp() {
        return this.temp;
    }

    public BidPlnbas temp(String temp) {
        this.setTemp(temp);
        return this;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHm102() {
        return this.hm102;
    }

    public BidPlnbas hm102(String hm102) {
        this.setHm102(hm102);
        return this;
    }

    public void setHm102(String hm102) {
        this.hm102 = hm102;
    }

    public String getHm103() {
        return this.hm103;
    }

    public BidPlnbas hm103(String hm103) {
        this.setHm103(hm103);
        return this;
    }

    public void setHm103(String hm103) {
        this.hm103 = hm103;
    }

    public String getHm104() {
        return this.hm104;
    }

    public BidPlnbas hm104(String hm104) {
        this.setHm104(hm104);
        return this;
    }

    public void setHm104(String hm104) {
        this.hm104 = hm104;
    }

    public String getHm105() {
        return this.hm105;
    }

    public BidPlnbas hm105(String hm105) {
        this.setHm105(hm105);
        return this;
    }

    public void setHm105(String hm105) {
        this.hm105 = hm105;
    }

    public String getHm106() {
        return this.hm106;
    }

    public BidPlnbas hm106(String hm106) {
        this.setHm106(hm106);
        return this;
    }

    public void setHm106(String hm106) {
        this.hm106 = hm106;
    }

    public String getPlnkind7() {
        return this.plnkind7;
    }

    public BidPlnbas plnkind7(String plnkind7) {
        this.setPlnkind7(plnkind7);
        return this;
    }

    public void setPlnkind7(String plnkind7) {
        this.plnkind7 = plnkind7;
    }

    public String getPlnkind8() {
        return this.plnkind8;
    }

    public BidPlnbas plnkind8(String plnkind8) {
        this.setPlnkind8(plnkind8);
        return this;
    }

    public void setPlnkind8(String plnkind8) {
        this.plnkind8 = plnkind8;
    }

    public String getPlnkind9() {
        return this.plnkind9;
    }

    public BidPlnbas plnkind9(String plnkind9) {
        this.setPlnkind9(plnkind9);
        return this;
    }

    public void setPlnkind9(String plnkind9) {
        this.plnkind9 = plnkind9;
    }

    public String getPlnkinda() {
        return this.plnkinda;
    }

    public BidPlnbas plnkinda(String plnkinda) {
        this.setPlnkinda(plnkinda);
        return this;
    }

    public void setPlnkinda(String plnkinda) {
        this.plnkinda = plnkinda;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidPlnbas createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidPlnbas createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidPlnbas updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidPlnbas updateUser(String updateUser) {
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
        if (!(o instanceof BidPlnbas)) return false;
        BidPlnbas that = (BidPlnbas) o;
        return plncode.equals(that.plncode) && plnno.equals(that.plnno);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPlnbas{" +
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
