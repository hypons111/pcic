package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidKpifin.
 */
@Entity
@Table(name = "bid_kpifin")
@IdClass(BidKpifinPrimaryKey.class)
public class BidKpifin implements Serializable {

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
    @Column(name = "idate", nullable = false)
    private LocalDate idate;

    @Id
    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Column(name = "conf_date")
    private LocalDate confDate;

    @Size(max = 50)
    @Column(name = "conf_number", length = 50)
    private String confNumber;

    @Column(name = "ka02", precision = 21, scale = 2)
    private BigDecimal ka02;

    @Column(name = "kb01", precision = 21, scale = 2)
    private BigDecimal kb01;

    @Column(name = "kb02", precision = 21, scale = 2)
    private BigDecimal kb02;

    @Column(name = "kc01", precision = 21, scale = 2)
    private BigDecimal kc01;

    @Column(name = "kc02", precision = 21, scale = 2)
    private BigDecimal kc02;

    @Column(name = "kd01", precision = 21, scale = 2)
    private BigDecimal kd01;

    @Column(name = "kd02", precision = 21, scale = 2)
    private BigDecimal kd02;

    @Column(name = "kd03", precision = 21, scale = 2)
    private BigDecimal kd03;

    @Column(name = "kd04", precision = 21, scale = 2)
    private BigDecimal kd04;

    @Column(name = "kd05", precision = 21, scale = 2)
    private BigDecimal kd05;

    @Column(name = "kd06", precision = 21, scale = 2)
    private BigDecimal kd06;

    @Column(name = "kd07", precision = 21, scale = 2)
    private BigDecimal kd07;

    @Column(name = "kd08", precision = 21, scale = 2)
    private BigDecimal kd08;

    @Column(name = "kd09", precision = 21, scale = 2)
    private BigDecimal kd09;

    @Column(name = "ke01", precision = 21, scale = 2)
    private BigDecimal ke01;

    @Column(name = "ke02", precision = 21, scale = 2)
    private BigDecimal ke02;

    @Column(name = "ke03", precision = 21, scale = 2)
    private BigDecimal ke03;

    @Column(name = "ke04", precision = 21, scale = 2)
    private BigDecimal ke04;

    @Column(name = "kf01", precision = 21, scale = 2)
    private BigDecimal kf01;

    @Column(name = "kf02", precision = 21, scale = 2)
    private BigDecimal kf02;

    @Column(name = "kscore", precision = 21, scale = 2)
    private BigDecimal kscore;

    @Size(max = 2)
    @Column(name = "qa02", length = 2)
    private String qa02;

    @Size(max = 2)
    @Column(name = "qc01", length = 2)
    private String qc01;

    @Size(max = 200)
    @Column(name = "qc03", length = 200)
    private String qc03;

    @Size(max = 5)
    @Column(name = "qd03a", length = 5)
    private String qd03a;

    @Size(max = 5)
    @Column(name = "qd03b", length = 5)
    private String qd03b;

    @Size(max = 5)
    @Column(name = "qd03c", length = 5)
    private String qd03c;

    @Size(max = 5)
    @Column(name = "qd03d", length = 5)
    private String qd03d;

    @Size(max = 5)
    @Column(name = "qd06", length = 5)
    private String qd06;

    @Size(max = 5)
    @Column(name = "qd07", length = 5)
    private String qd07;

    @Size(max = 5)
    @Column(name = "qd08", length = 5)
    private String qd08;

    @Size(max = 200)
    @Column(name = "qd09", length = 200)
    private String qd09;

    @Size(max = 200)
    @Column(name = "qe01a", length = 200)
    private String qe01a;

    @Size(max = 10)
    @Column(name = "qe01b", length = 10)
    private String qe01b;

    @Size(max = 7)
    @Column(name = "qe021s", length = 7)
    private String qe021s;

    @Size(max = 7)
    @Column(name = "qe021e", length = 7)
    private String qe021e;

    @Size(max = 3)
    @Column(name = "qe021y", length = 3)
    private String qe021y;

    @Size(max = 7)
    @Column(name = "qe022s", length = 7)
    private String qe022s;

    @Size(max = 7)
    @Column(name = "qe022e", length = 7)
    private String qe022e;

    @Size(max = 3)
    @Column(name = "qe022y", length = 3)
    private String qe022y;

    @Size(max = 7)
    @Column(name = "qe023s", length = 7)
    private String qe023s;

    @Size(max = 7)
    @Column(name = "qe023e", length = 7)
    private String qe023e;

    @Size(max = 3)
    @Column(name = "qe023y", length = 3)
    private String qe023y;

    @Size(max = 5)
    @Column(name = "qe03a", length = 5)
    private String qe03a;

    @Size(max = 3)
    @Column(name = "qe03b", length = 3)
    private String qe03b;

    @Size(max = 200)
    @Column(name = "qe04", length = 200)
    private String qe04;

    @Size(max = 7)
    @Column(name = "qe041s", length = 7)
    private String qe041s;

    @Size(max = 7)
    @Column(name = "qe041e", length = 7)
    private String qe041e;

    @Size(max = 3)
    @Column(name = "qe041y", length = 3)
    private String qe041y;

    @Size(max = 7)
    @Column(name = "qe042s", length = 7)
    private String qe042s;

    @Size(max = 7)
    @Column(name = "qe042e", length = 7)
    private String qe042e;

    @Size(max = 3)
    @Column(name = "qe042y", length = 3)
    private String qe042y;

    @Size(max = 7)
    @Column(name = "qe043s", length = 7)
    private String qe043s;

    @Size(max = 7)
    @Column(name = "qe043e", length = 7)
    private String qe043e;

    @Size(max = 3)
    @Column(name = "qe043y", length = 3)
    private String qe043y;

    @Size(max = 200)
    @Column(name = "qf01", length = 200)
    private String qf01;

    @Size(max = 7)
    @Column(name = "qf011s", length = 7)
    private String qf011s;

    @Size(max = 7)
    @Column(name = "qf011e", length = 7)
    private String qf011e;

    @Size(max = 3)
    @Column(name = "qf011y", length = 3)
    private String qf011y;

    @Size(max = 7)
    @Column(name = "qf012s", length = 7)
    private String qf012s;

    @Size(max = 7)
    @Column(name = "qf012e", length = 7)
    private String qf012e;

    @Size(max = 3)
    @Column(name = "qf012y", length = 3)
    private String qf012y;

    @Size(max = 7)
    @Column(name = "qf013s", length = 7)
    private String qf013s;

    @Size(max = 7)
    @Column(name = "qf013e", length = 7)
    private String qf013e;

    @Size(max = 3)
    @Column(name = "qf013y", length = 3)
    private String qf013y;

    @Size(max = 5)
    @Column(name = "qf02", length = 5)
    private String qf02;

    @Size(max = 5)
    @Column(name = "qf02a", length = 5)
    private String qf02a;

    @Size(max = 5)
    @Column(name = "qf02b", length = 5)
    private String qf02b;

    @Size(max = 5)
    @Column(name = "qf02c", length = 5)
    private String qf02c;

    @Column(name = "qf02d", precision = 21, scale = 2)
    private BigDecimal qf02d;

    @Size(max = 2000)
    @Column(name = "item", length = 2000)
    private String item;

    @Size(max = 20)
    @Column(name = "senter", length = 20)
    private String senter;

    @Size(max = 30)
    @Column(name = "sentel", length = 30)
    private String sentel;

    @Size(max = 20)
    @Column(name = "admser", length = 20)
    private String admser;

    @Size(max = 1)
    @Column(name = "dsts", length = 1)
    private String dsts;

    @Column(name = "recnt")
    private Integer recnt;

    @Size(max = 2000)
    @Column(name = "rermk", length = 2000)
    private String rermk;

    @Size(max = 5)
    @Column(name = "qd03at", length = 5)
    private String qd03at;

    @Size(max = 1)
    @Column(name = "publ", length = 1)
    private String publ;

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

    @Size(max = 20)
    @Column(name = "column1", length = 20)
    private String column1;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public BidKpifinPrimaryKey getId() {
        return new BidKpifinPrimaryKey(this.wkut,this.prjno,this.idate,this.seq);
    }

    public BidKpifin id(BidKpifinPrimaryKey id) {
        this.setId(id);
        return this;
    }

    public void setId(BidKpifinPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.idate = id.getIdate();
        this.seq = id.getSeq();
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidKpifin wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidKpifin prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public LocalDate getIdate() {
        return this.idate;
    }

    public BidKpifin idate(LocalDate idate) {
        this.setIdate(idate);
        return this;
    }

    public void setIdate(LocalDate idate) {
        this.idate = idate;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public BidKpifin seq(Integer seq) {
        this.setSeq(seq);
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getFlag() {
        return this.flag;
    }

    public BidKpifin flag(String flag) {
        this.setFlag(flag);
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LocalDate getConfDate() {
        return this.confDate;
    }

    public BidKpifin confDate(LocalDate confDate) {
        this.setConfDate(confDate);
        return this;
    }

    public void setConfDate(LocalDate confDate) {
        this.confDate = confDate;
    }

    public String getConfNumber() {
        return this.confNumber;
    }

    public BidKpifin confNumber(String confNumber) {
        this.setConfNumber(confNumber);
        return this;
    }

    public void setConfNumber(String confNumber) {
        this.confNumber = confNumber;
    }

    public BigDecimal getKa02() {
        return this.ka02;
    }

    public BidKpifin ka02(BigDecimal ka02) {
        this.setKa02(ka02);
        return this;
    }

    public void setKa02(BigDecimal ka02) {
        this.ka02 = ka02;
    }

    public BigDecimal getKb01() {
        return this.kb01;
    }

    public BidKpifin kb01(BigDecimal kb01) {
        this.setKb01(kb01);
        return this;
    }

    public void setKb01(BigDecimal kb01) {
        this.kb01 = kb01;
    }

    public BigDecimal getKb02() {
        return this.kb02;
    }

    public BidKpifin kb02(BigDecimal kb02) {
        this.setKb02(kb02);
        return this;
    }

    public void setKb02(BigDecimal kb02) {
        this.kb02 = kb02;
    }

    public BigDecimal getKc01() {
        return this.kc01;
    }

    public BidKpifin kc01(BigDecimal kc01) {
        this.setKc01(kc01);
        return this;
    }

    public void setKc01(BigDecimal kc01) {
        this.kc01 = kc01;
    }

    public BigDecimal getKc02() {
        return this.kc02;
    }

    public BidKpifin kc02(BigDecimal kc02) {
        this.setKc02(kc02);
        return this;
    }

    public void setKc02(BigDecimal kc02) {
        this.kc02 = kc02;
    }

    public BigDecimal getKd01() {
        return this.kd01;
    }

    public BidKpifin kd01(BigDecimal kd01) {
        this.setKd01(kd01);
        return this;
    }

    public void setKd01(BigDecimal kd01) {
        this.kd01 = kd01;
    }

    public BigDecimal getKd02() {
        return this.kd02;
    }

    public BidKpifin kd02(BigDecimal kd02) {
        this.setKd02(kd02);
        return this;
    }

    public void setKd02(BigDecimal kd02) {
        this.kd02 = kd02;
    }

    public BigDecimal getKd03() {
        return this.kd03;
    }

    public BidKpifin kd03(BigDecimal kd03) {
        this.setKd03(kd03);
        return this;
    }

    public void setKd03(BigDecimal kd03) {
        this.kd03 = kd03;
    }

    public BigDecimal getKd04() {
        return this.kd04;
    }

    public BidKpifin kd04(BigDecimal kd04) {
        this.setKd04(kd04);
        return this;
    }

    public void setKd04(BigDecimal kd04) {
        this.kd04 = kd04;
    }

    public BigDecimal getKd05() {
        return this.kd05;
    }

    public BidKpifin kd05(BigDecimal kd05) {
        this.setKd05(kd05);
        return this;
    }

    public void setKd05(BigDecimal kd05) {
        this.kd05 = kd05;
    }

    public BigDecimal getKd06() {
        return this.kd06;
    }

    public BidKpifin kd06(BigDecimal kd06) {
        this.setKd06(kd06);
        return this;
    }

    public void setKd06(BigDecimal kd06) {
        this.kd06 = kd06;
    }

    public BigDecimal getKd07() {
        return this.kd07;
    }

    public BidKpifin kd07(BigDecimal kd07) {
        this.setKd07(kd07);
        return this;
    }

    public void setKd07(BigDecimal kd07) {
        this.kd07 = kd07;
    }

    public BigDecimal getKd08() {
        return this.kd08;
    }

    public BidKpifin kd08(BigDecimal kd08) {
        this.setKd08(kd08);
        return this;
    }

    public void setKd08(BigDecimal kd08) {
        this.kd08 = kd08;
    }

    public BigDecimal getKd09() {
        return this.kd09;
    }

    public BidKpifin kd09(BigDecimal kd09) {
        this.setKd09(kd09);
        return this;
    }

    public void setKd09(BigDecimal kd09) {
        this.kd09 = kd09;
    }

    public BigDecimal getKe01() {
        return this.ke01;
    }

    public BidKpifin ke01(BigDecimal ke01) {
        this.setKe01(ke01);
        return this;
    }

    public void setKe01(BigDecimal ke01) {
        this.ke01 = ke01;
    }

    public BigDecimal getKe02() {
        return this.ke02;
    }

    public BidKpifin ke02(BigDecimal ke02) {
        this.setKe02(ke02);
        return this;
    }

    public void setKe02(BigDecimal ke02) {
        this.ke02 = ke02;
    }

    public BigDecimal getKe03() {
        return this.ke03;
    }

    public BidKpifin ke03(BigDecimal ke03) {
        this.setKe03(ke03);
        return this;
    }

    public void setKe03(BigDecimal ke03) {
        this.ke03 = ke03;
    }

    public BigDecimal getKe04() {
        return this.ke04;
    }

    public BidKpifin ke04(BigDecimal ke04) {
        this.setKe04(ke04);
        return this;
    }

    public void setKe04(BigDecimal ke04) {
        this.ke04 = ke04;
    }

    public BigDecimal getKf01() {
        return this.kf01;
    }

    public BidKpifin kf01(BigDecimal kf01) {
        this.setKf01(kf01);
        return this;
    }

    public void setKf01(BigDecimal kf01) {
        this.kf01 = kf01;
    }

    public BigDecimal getKf02() {
        return this.kf02;
    }

    public BidKpifin kf02(BigDecimal kf02) {
        this.setKf02(kf02);
        return this;
    }

    public void setKf02(BigDecimal kf02) {
        this.kf02 = kf02;
    }

    public BigDecimal getKscore() {
        return this.kscore;
    }

    public BidKpifin kscore(BigDecimal kscore) {
        this.setKscore(kscore);
        return this;
    }

    public void setKscore(BigDecimal kscore) {
        this.kscore = kscore;
    }

    public String getQa02() {
        return this.qa02;
    }

    public BidKpifin qa02(String qa02) {
        this.setQa02(qa02);
        return this;
    }

    public void setQa02(String qa02) {
        this.qa02 = qa02;
    }

    public String getQc01() {
        return this.qc01;
    }

    public BidKpifin qc01(String qc01) {
        this.setQc01(qc01);
        return this;
    }

    public void setQc01(String qc01) {
        this.qc01 = qc01;
    }

    public String getQc03() {
        return this.qc03;
    }

    public BidKpifin qc03(String qc03) {
        this.setQc03(qc03);
        return this;
    }

    public void setQc03(String qc03) {
        this.qc03 = qc03;
    }

    public String getQd03a() {
        return this.qd03a;
    }

    public BidKpifin qd03a(String qd03a) {
        this.setQd03a(qd03a);
        return this;
    }

    public void setQd03a(String qd03a) {
        this.qd03a = qd03a;
    }

    public String getQd03b() {
        return this.qd03b;
    }

    public BidKpifin qd03b(String qd03b) {
        this.setQd03b(qd03b);
        return this;
    }

    public void setQd03b(String qd03b) {
        this.qd03b = qd03b;
    }

    public String getQd03c() {
        return this.qd03c;
    }

    public BidKpifin qd03c(String qd03c) {
        this.setQd03c(qd03c);
        return this;
    }

    public void setQd03c(String qd03c) {
        this.qd03c = qd03c;
    }

    public String getQd03d() {
        return this.qd03d;
    }

    public BidKpifin qd03d(String qd03d) {
        this.setQd03d(qd03d);
        return this;
    }

    public void setQd03d(String qd03d) {
        this.qd03d = qd03d;
    }

    public String getQd06() {
        return this.qd06;
    }

    public BidKpifin qd06(String qd06) {
        this.setQd06(qd06);
        return this;
    }

    public void setQd06(String qd06) {
        this.qd06 = qd06;
    }

    public String getQd07() {
        return this.qd07;
    }

    public BidKpifin qd07(String qd07) {
        this.setQd07(qd07);
        return this;
    }

    public void setQd07(String qd07) {
        this.qd07 = qd07;
    }

    public String getQd08() {
        return this.qd08;
    }

    public BidKpifin qd08(String qd08) {
        this.setQd08(qd08);
        return this;
    }

    public void setQd08(String qd08) {
        this.qd08 = qd08;
    }

    public String getQd09() {
        return this.qd09;
    }

    public BidKpifin qd09(String qd09) {
        this.setQd09(qd09);
        return this;
    }

    public void setQd09(String qd09) {
        this.qd09 = qd09;
    }

    public String getQe01a() {
        return this.qe01a;
    }

    public BidKpifin qe01a(String qe01a) {
        this.setQe01a(qe01a);
        return this;
    }

    public void setQe01a(String qe01a) {
        this.qe01a = qe01a;
    }

    public String getQe01b() {
        return this.qe01b;
    }

    public BidKpifin qe01b(String qe01b) {
        this.setQe01b(qe01b);
        return this;
    }

    public void setQe01b(String qe01b) {
        this.qe01b = qe01b;
    }

    public String getQe021s() {
        return this.qe021s;
    }

    public BidKpifin qe021s(String qe021s) {
        this.setQe021s(qe021s);
        return this;
    }

    public void setQe021s(String qe021s) {
        this.qe021s = qe021s;
    }

    public String getQe021e() {
        return this.qe021e;
    }

    public BidKpifin qe021e(String qe021e) {
        this.setQe021e(qe021e);
        return this;
    }

    public void setQe021e(String qe021e) {
        this.qe021e = qe021e;
    }

    public String getQe021y() {
        return this.qe021y;
    }

    public BidKpifin qe021y(String qe021y) {
        this.setQe021y(qe021y);
        return this;
    }

    public void setQe021y(String qe021y) {
        this.qe021y = qe021y;
    }

    public String getQe022s() {
        return this.qe022s;
    }

    public BidKpifin qe022s(String qe022s) {
        this.setQe022s(qe022s);
        return this;
    }

    public void setQe022s(String qe022s) {
        this.qe022s = qe022s;
    }

    public String getQe022e() {
        return this.qe022e;
    }

    public BidKpifin qe022e(String qe022e) {
        this.setQe022e(qe022e);
        return this;
    }

    public void setQe022e(String qe022e) {
        this.qe022e = qe022e;
    }

    public String getQe022y() {
        return this.qe022y;
    }

    public BidKpifin qe022y(String qe022y) {
        this.setQe022y(qe022y);
        return this;
    }

    public void setQe022y(String qe022y) {
        this.qe022y = qe022y;
    }

    public String getQe023s() {
        return this.qe023s;
    }

    public BidKpifin qe023s(String qe023s) {
        this.setQe023s(qe023s);
        return this;
    }

    public void setQe023s(String qe023s) {
        this.qe023s = qe023s;
    }

    public String getQe023e() {
        return this.qe023e;
    }

    public BidKpifin qe023e(String qe023e) {
        this.setQe023e(qe023e);
        return this;
    }

    public void setQe023e(String qe023e) {
        this.qe023e = qe023e;
    }

    public String getQe023y() {
        return this.qe023y;
    }

    public BidKpifin qe023y(String qe023y) {
        this.setQe023y(qe023y);
        return this;
    }

    public void setQe023y(String qe023y) {
        this.qe023y = qe023y;
    }

    public String getQe03a() {
        return this.qe03a;
    }

    public BidKpifin qe03a(String qe03a) {
        this.setQe03a(qe03a);
        return this;
    }

    public void setQe03a(String qe03a) {
        this.qe03a = qe03a;
    }

    public String getQe03b() {
        return this.qe03b;
    }

    public BidKpifin qe03b(String qe03b) {
        this.setQe03b(qe03b);
        return this;
    }

    public void setQe03b(String qe03b) {
        this.qe03b = qe03b;
    }

    public String getQe04() {
        return this.qe04;
    }

    public BidKpifin qe04(String qe04) {
        this.setQe04(qe04);
        return this;
    }

    public void setQe04(String qe04) {
        this.qe04 = qe04;
    }

    public String getQe041s() {
        return this.qe041s;
    }

    public BidKpifin qe041s(String qe041s) {
        this.setQe041s(qe041s);
        return this;
    }

    public void setQe041s(String qe041s) {
        this.qe041s = qe041s;
    }

    public String getQe041e() {
        return this.qe041e;
    }

    public BidKpifin qe041e(String qe041e) {
        this.setQe041e(qe041e);
        return this;
    }

    public void setQe041e(String qe041e) {
        this.qe041e = qe041e;
    }

    public String getQe041y() {
        return this.qe041y;
    }

    public BidKpifin qe041y(String qe041y) {
        this.setQe041y(qe041y);
        return this;
    }

    public void setQe041y(String qe041y) {
        this.qe041y = qe041y;
    }

    public String getQe042s() {
        return this.qe042s;
    }

    public BidKpifin qe042s(String qe042s) {
        this.setQe042s(qe042s);
        return this;
    }

    public void setQe042s(String qe042s) {
        this.qe042s = qe042s;
    }

    public String getQe042e() {
        return this.qe042e;
    }

    public BidKpifin qe042e(String qe042e) {
        this.setQe042e(qe042e);
        return this;
    }

    public void setQe042e(String qe042e) {
        this.qe042e = qe042e;
    }

    public String getQe042y() {
        return this.qe042y;
    }

    public BidKpifin qe042y(String qe042y) {
        this.setQe042y(qe042y);
        return this;
    }

    public void setQe042y(String qe042y) {
        this.qe042y = qe042y;
    }

    public String getQe043s() {
        return this.qe043s;
    }

    public BidKpifin qe043s(String qe043s) {
        this.setQe043s(qe043s);
        return this;
    }

    public void setQe043s(String qe043s) {
        this.qe043s = qe043s;
    }

    public String getQe043e() {
        return this.qe043e;
    }

    public BidKpifin qe043e(String qe043e) {
        this.setQe043e(qe043e);
        return this;
    }

    public void setQe043e(String qe043e) {
        this.qe043e = qe043e;
    }

    public String getQe043y() {
        return this.qe043y;
    }

    public BidKpifin qe043y(String qe043y) {
        this.setQe043y(qe043y);
        return this;
    }

    public void setQe043y(String qe043y) {
        this.qe043y = qe043y;
    }

    public String getQf01() {
        return this.qf01;
    }

    public BidKpifin qf01(String qf01) {
        this.setQf01(qf01);
        return this;
    }

    public void setQf01(String qf01) {
        this.qf01 = qf01;
    }

    public String getQf011s() {
        return this.qf011s;
    }

    public BidKpifin qf011s(String qf011s) {
        this.setQf011s(qf011s);
        return this;
    }

    public void setQf011s(String qf011s) {
        this.qf011s = qf011s;
    }

    public String getQf011e() {
        return this.qf011e;
    }

    public BidKpifin qf011e(String qf011e) {
        this.setQf011e(qf011e);
        return this;
    }

    public void setQf011e(String qf011e) {
        this.qf011e = qf011e;
    }

    public String getQf011y() {
        return this.qf011y;
    }

    public BidKpifin qf011y(String qf011y) {
        this.setQf011y(qf011y);
        return this;
    }

    public void setQf011y(String qf011y) {
        this.qf011y = qf011y;
    }

    public String getQf012s() {
        return this.qf012s;
    }

    public BidKpifin qf012s(String qf012s) {
        this.setQf012s(qf012s);
        return this;
    }

    public void setQf012s(String qf012s) {
        this.qf012s = qf012s;
    }

    public String getQf012e() {
        return this.qf012e;
    }

    public BidKpifin qf012e(String qf012e) {
        this.setQf012e(qf012e);
        return this;
    }

    public void setQf012e(String qf012e) {
        this.qf012e = qf012e;
    }

    public String getQf012y() {
        return this.qf012y;
    }

    public BidKpifin qf012y(String qf012y) {
        this.setQf012y(qf012y);
        return this;
    }

    public void setQf012y(String qf012y) {
        this.qf012y = qf012y;
    }

    public String getQf013s() {
        return this.qf013s;
    }

    public BidKpifin qf013s(String qf013s) {
        this.setQf013s(qf013s);
        return this;
    }

    public void setQf013s(String qf013s) {
        this.qf013s = qf013s;
    }

    public String getQf013e() {
        return this.qf013e;
    }

    public BidKpifin qf013e(String qf013e) {
        this.setQf013e(qf013e);
        return this;
    }

    public void setQf013e(String qf013e) {
        this.qf013e = qf013e;
    }

    public String getQf013y() {
        return this.qf013y;
    }

    public BidKpifin qf013y(String qf013y) {
        this.setQf013y(qf013y);
        return this;
    }

    public void setQf013y(String qf013y) {
        this.qf013y = qf013y;
    }

    public String getQf02() {
        return this.qf02;
    }

    public BidKpifin qf02(String qf02) {
        this.setQf02(qf02);
        return this;
    }

    public void setQf02(String qf02) {
        this.qf02 = qf02;
    }

    public String getQf02a() {
        return this.qf02a;
    }

    public BidKpifin qf02a(String qf02a) {
        this.setQf02a(qf02a);
        return this;
    }

    public void setQf02a(String qf02a) {
        this.qf02a = qf02a;
    }

    public String getQf02b() {
        return this.qf02b;
    }

    public BidKpifin qf02b(String qf02b) {
        this.setQf02b(qf02b);
        return this;
    }

    public void setQf02b(String qf02b) {
        this.qf02b = qf02b;
    }

    public String getQf02c() {
        return this.qf02c;
    }

    public BidKpifin qf02c(String qf02c) {
        this.setQf02c(qf02c);
        return this;
    }

    public void setQf02c(String qf02c) {
        this.qf02c = qf02c;
    }

    public BigDecimal getQf02d() {
        return this.qf02d;
    }

    public BidKpifin qf02d(BigDecimal qf02d) {
        this.setQf02d(qf02d);
        return this;
    }

    public void setQf02d(BigDecimal qf02d) {
        this.qf02d = qf02d;
    }

    public String getItem() {
        return this.item;
    }

    public BidKpifin item(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSenter() {
        return this.senter;
    }

    public BidKpifin senter(String senter) {
        this.setSenter(senter);
        return this;
    }

    public void setSenter(String senter) {
        this.senter = senter;
    }

    public String getSentel() {
        return this.sentel;
    }

    public BidKpifin sentel(String sentel) {
        this.setSentel(sentel);
        return this;
    }

    public void setSentel(String sentel) {
        this.sentel = sentel;
    }

    public String getAdmser() {
        return this.admser;
    }

    public BidKpifin admser(String admser) {
        this.setAdmser(admser);
        return this;
    }

    public void setAdmser(String admser) {
        this.admser = admser;
    }

    public String getDsts() {
        return this.dsts;
    }

    public BidKpifin dsts(String dsts) {
        this.setDsts(dsts);
        return this;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public Integer getRecnt() {
        return this.recnt;
    }

    public BidKpifin recnt(Integer recnt) {
        this.setRecnt(recnt);
        return this;
    }

    public void setRecnt(Integer recnt) {
        this.recnt = recnt;
    }

    public String getRermk() {
        return this.rermk;
    }

    public BidKpifin rermk(String rermk) {
        this.setRermk(rermk);
        return this;
    }

    public void setRermk(String rermk) {
        this.rermk = rermk;
    }

    public String getQd03at() {
        return this.qd03at;
    }

    public BidKpifin qd03at(String qd03at) {
        this.setQd03at(qd03at);
        return this;
    }

    public void setQd03at(String qd03at) {
        this.qd03at = qd03at;
    }

    public String getPubl() {
        return this.publ;
    }

    public BidKpifin publ(String publ) {
        this.setPubl(publ);
        return this;
    }

    public void setPubl(String publ) {
        this.publ = publ;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidKpifin createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidKpifin createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidKpifin updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidKpifin updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getColumn1() {
        return this.column1;
    }

    public BidKpifin column1(String column1) {
        this.setColumn1(column1);
        return this;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidKpifin)) return false;
        BidKpifin bidKpifin = (BidKpifin) o;
        return wkut.equals(bidKpifin.wkut) && prjno.equals(bidKpifin.prjno) && idate.equals(bidKpifin.idate) && seq.equals(bidKpifin.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate, seq);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidKpifin{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", idate='" + getIdate() + "'" +
            ", seq=" + getSeq() +
            ", flag='" + getFlag() + "'" +
            ", confDate='" + getConfDate() + "'" +
            ", confNumber='" + getConfNumber() + "'" +
            ", ka02=" + getKa02() +
            ", kb01=" + getKb01() +
            ", kb02=" + getKb02() +
            ", kc01=" + getKc01() +
            ", kc02=" + getKc02() +
            ", kd01=" + getKd01() +
            ", kd02=" + getKd02() +
            ", kd03=" + getKd03() +
            ", kd04=" + getKd04() +
            ", kd05=" + getKd05() +
            ", kd06=" + getKd06() +
            ", kd07=" + getKd07() +
            ", kd08=" + getKd08() +
            ", kd09=" + getKd09() +
            ", ke01=" + getKe01() +
            ", ke02=" + getKe02() +
            ", ke03=" + getKe03() +
            ", ke04=" + getKe04() +
            ", kf01=" + getKf01() +
            ", kf02=" + getKf02() +
            ", kscore=" + getKscore() +
            ", qa02='" + getQa02() + "'" +
            ", qc01='" + getQc01() + "'" +
            ", qc03='" + getQc03() + "'" +
            ", qd03a='" + getQd03a() + "'" +
            ", qd03b='" + getQd03b() + "'" +
            ", qd03c='" + getQd03c() + "'" +
            ", qd03d='" + getQd03d() + "'" +
            ", qd06='" + getQd06() + "'" +
            ", qd07='" + getQd07() + "'" +
            ", qd08='" + getQd08() + "'" +
            ", qd09='" + getQd09() + "'" +
            ", qe01a='" + getQe01a() + "'" +
            ", qe01b='" + getQe01b() + "'" +
            ", qe021s='" + getQe021s() + "'" +
            ", qe021e='" + getQe021e() + "'" +
            ", qe021y='" + getQe021y() + "'" +
            ", qe022s='" + getQe022s() + "'" +
            ", qe022e='" + getQe022e() + "'" +
            ", qe022y='" + getQe022y() + "'" +
            ", qe023s='" + getQe023s() + "'" +
            ", qe023e='" + getQe023e() + "'" +
            ", qe023y='" + getQe023y() + "'" +
            ", qe03a='" + getQe03a() + "'" +
            ", qe03b='" + getQe03b() + "'" +
            ", qe04='" + getQe04() + "'" +
            ", qe041s='" + getQe041s() + "'" +
            ", qe041e='" + getQe041e() + "'" +
            ", qe041y='" + getQe041y() + "'" +
            ", qe042s='" + getQe042s() + "'" +
            ", qe042e='" + getQe042e() + "'" +
            ", qe042y='" + getQe042y() + "'" +
            ", qe043s='" + getQe043s() + "'" +
            ", qe043e='" + getQe043e() + "'" +
            ", qe043y='" + getQe043y() + "'" +
            ", qf01='" + getQf01() + "'" +
            ", qf011s='" + getQf011s() + "'" +
            ", qf011e='" + getQf011e() + "'" +
            ", qf011y='" + getQf011y() + "'" +
            ", qf012s='" + getQf012s() + "'" +
            ", qf012e='" + getQf012e() + "'" +
            ", qf012y='" + getQf012y() + "'" +
            ", qf013s='" + getQf013s() + "'" +
            ", qf013e='" + getQf013e() + "'" +
            ", qf013y='" + getQf013y() + "'" +
            ", qf02='" + getQf02() + "'" +
            ", qf02a='" + getQf02a() + "'" +
            ", qf02b='" + getQf02b() + "'" +
            ", qf02c='" + getQf02c() + "'" +
            ", qf02d=" + getQf02d() +
            ", item='" + getItem() + "'" +
            ", senter='" + getSenter() + "'" +
            ", sentel='" + getSentel() + "'" +
            ", admser='" + getAdmser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", recnt=" + getRecnt() +
            ", rermk='" + getRermk() + "'" +
            ", qd03at='" + getQd03at() + "'" +
            ", publ='" + getPubl() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", column1='" + getColumn1() + "'" +
            "}";
    }
}
