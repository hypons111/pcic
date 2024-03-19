package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidKpifin;
import gov.pcc.pwc.domain.BidKpifinPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidKpifin} entity.
 */
public class BidKpifinDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate idate;

    private Integer seq;

    @Size(max = 1)
    private String flag;

    private LocalDate confDate;

    @Size(max = 50)
    private String confNumber;

    private BigDecimal ka02;

    private BigDecimal kb01;

    private BigDecimal kb02;

    private BigDecimal kc01;

    private BigDecimal kc02;

    private BigDecimal kd01;

    private BigDecimal kd02;

    private BigDecimal kd03;

    private BigDecimal kd04;

    private BigDecimal kd05;

    private BigDecimal kd06;

    private BigDecimal kd07;

    private BigDecimal kd08;

    private BigDecimal kd09;

    private BigDecimal ke01;

    private BigDecimal ke02;

    private BigDecimal ke03;

    private BigDecimal ke04;

    private BigDecimal kf01;

    private BigDecimal kf02;

    private BigDecimal kscore;

    @Size(max = 2)
    private String qa02;

    @Size(max = 2)
    private String qc01;

    @Size(max = 200)
    private String qc03;

    @Size(max = 5)
    private String qd03a;

    @Size(max = 5)
    private String qd03b;

    @Size(max = 5)
    private String qd03c;

    @Size(max = 5)
    private String qd03d;

    @Size(max = 5)
    private String qd06;

    @Size(max = 5)
    private String qd07;

    @Size(max = 5)
    private String qd08;

    @Size(max = 200)
    private String qd09;

    @Size(max = 200)
    private String qe01a;

    @Size(max = 10)
    private String qe01b;

    @Size(max = 7)
    private String qe021s;

    @Size(max = 7)
    private String qe021e;

    @Size(max = 3)
    private String qe021y;

    @Size(max = 7)
    private String qe022s;

    @Size(max = 7)
    private String qe022e;

    @Size(max = 3)
    private String qe022y;

    @Size(max = 7)
    private String qe023s;

    @Size(max = 7)
    private String qe023e;

    @Size(max = 3)
    private String qe023y;

    @Size(max = 5)
    private String qe03a;

    @Size(max = 3)
    private String qe03b;

    @Size(max = 200)
    private String qe04;

    @Size(max = 7)
    private String qe041s;

    @Size(max = 7)
    private String qe041e;

    @Size(max = 3)
    private String qe041y;

    @Size(max = 7)
    private String qe042s;

    @Size(max = 7)
    private String qe042e;

    @Size(max = 3)
    private String qe042y;

    @Size(max = 7)
    private String qe043s;

    @Size(max = 7)
    private String qe043e;

    @Size(max = 3)
    private String qe043y;

    @Size(max = 200)
    private String qf01;

    @Size(max = 7)
    private String qf011s;

    @Size(max = 7)
    private String qf011e;

    @Size(max = 3)
    private String qf011y;

    @Size(max = 7)
    private String qf012s;

    @Size(max = 7)
    private String qf012e;

    @Size(max = 3)
    private String qf012y;

    @Size(max = 7)
    private String qf013s;

    @Size(max = 7)
    private String qf013e;

    @Size(max = 3)
    private String qf013y;

    @Size(max = 5)
    private String qf02;

    @Size(max = 5)
    private String qf02a;

    @Size(max = 5)
    private String qf02b;

    @Size(max = 5)
    private String qf02c;

    private BigDecimal qf02d;

    @Size(max = 2000)
    private String item;

    @Size(max = 20)
    private String senter;

    @Size(max = 30)
    private String sentel;

    @Size(max = 20)
    private String admser;

    @Size(max = 1)
    private String dsts;

    private Integer recnt;

    @Size(max = 2000)
    private String rermk;

    @Size(max = 5)
    private String qd03at;

    @Size(max = 1)
    private String publ;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 20)
    private String column1;

    public BidKpifinPrimaryKey getId() {
        return new BidKpifinPrimaryKey(this.wkut, this.prjno, this.idate, this.seq);
    }

    public void setId(BidKpifinPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.idate = id.getIdate();
        this.seq = id.getSeq();
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

    public LocalDate getIdate() {
        return idate;
    }

    public void setIdate(LocalDate idate) {
        this.idate = idate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LocalDate getConfDate() {
        return confDate;
    }

    public void setConfDate(LocalDate confDate) {
        this.confDate = confDate;
    }

    public String getConfNumber() {
        return confNumber;
    }

    public void setConfNumber(String confNumber) {
        this.confNumber = confNumber;
    }

    public BigDecimal getKa02() {
        return ka02;
    }

    public void setKa02(BigDecimal ka02) {
        this.ka02 = ka02;
    }

    public BigDecimal getKb01() {
        return kb01;
    }

    public void setKb01(BigDecimal kb01) {
        this.kb01 = kb01;
    }

    public BigDecimal getKb02() {
        return kb02;
    }

    public void setKb02(BigDecimal kb02) {
        this.kb02 = kb02;
    }

    public BigDecimal getKc01() {
        return kc01;
    }

    public void setKc01(BigDecimal kc01) {
        this.kc01 = kc01;
    }

    public BigDecimal getKc02() {
        return kc02;
    }

    public void setKc02(BigDecimal kc02) {
        this.kc02 = kc02;
    }

    public BigDecimal getKd01() {
        return kd01;
    }

    public void setKd01(BigDecimal kd01) {
        this.kd01 = kd01;
    }

    public BigDecimal getKd02() {
        return kd02;
    }

    public void setKd02(BigDecimal kd02) {
        this.kd02 = kd02;
    }

    public BigDecimal getKd03() {
        return kd03;
    }

    public void setKd03(BigDecimal kd03) {
        this.kd03 = kd03;
    }

    public BigDecimal getKd04() {
        return kd04;
    }

    public void setKd04(BigDecimal kd04) {
        this.kd04 = kd04;
    }

    public BigDecimal getKd05() {
        return kd05;
    }

    public void setKd05(BigDecimal kd05) {
        this.kd05 = kd05;
    }

    public BigDecimal getKd06() {
        return kd06;
    }

    public void setKd06(BigDecimal kd06) {
        this.kd06 = kd06;
    }

    public BigDecimal getKd07() {
        return kd07;
    }

    public void setKd07(BigDecimal kd07) {
        this.kd07 = kd07;
    }

    public BigDecimal getKd08() {
        return kd08;
    }

    public void setKd08(BigDecimal kd08) {
        this.kd08 = kd08;
    }

    public BigDecimal getKd09() {
        return kd09;
    }

    public void setKd09(BigDecimal kd09) {
        this.kd09 = kd09;
    }

    public BigDecimal getKe01() {
        return ke01;
    }

    public void setKe01(BigDecimal ke01) {
        this.ke01 = ke01;
    }

    public BigDecimal getKe02() {
        return ke02;
    }

    public void setKe02(BigDecimal ke02) {
        this.ke02 = ke02;
    }

    public BigDecimal getKe03() {
        return ke03;
    }

    public void setKe03(BigDecimal ke03) {
        this.ke03 = ke03;
    }

    public BigDecimal getKe04() {
        return ke04;
    }

    public void setKe04(BigDecimal ke04) {
        this.ke04 = ke04;
    }

    public BigDecimal getKf01() {
        return kf01;
    }

    public void setKf01(BigDecimal kf01) {
        this.kf01 = kf01;
    }

    public BigDecimal getKf02() {
        return kf02;
    }

    public void setKf02(BigDecimal kf02) {
        this.kf02 = kf02;
    }

    public BigDecimal getKscore() {
        return kscore;
    }

    public void setKscore(BigDecimal kscore) {
        this.kscore = kscore;
    }

    public String getQa02() {
        return qa02;
    }

    public void setQa02(String qa02) {
        this.qa02 = qa02;
    }

    public String getQc01() {
        return qc01;
    }

    public void setQc01(String qc01) {
        this.qc01 = qc01;
    }

    public String getQc03() {
        return qc03;
    }

    public void setQc03(String qc03) {
        this.qc03 = qc03;
    }

    public String getQd03a() {
        return qd03a;
    }

    public void setQd03a(String qd03a) {
        this.qd03a = qd03a;
    }

    public String getQd03b() {
        return qd03b;
    }

    public void setQd03b(String qd03b) {
        this.qd03b = qd03b;
    }

    public String getQd03c() {
        return qd03c;
    }

    public void setQd03c(String qd03c) {
        this.qd03c = qd03c;
    }

    public String getQd03d() {
        return qd03d;
    }

    public void setQd03d(String qd03d) {
        this.qd03d = qd03d;
    }

    public String getQd06() {
        return qd06;
    }

    public void setQd06(String qd06) {
        this.qd06 = qd06;
    }

    public String getQd07() {
        return qd07;
    }

    public void setQd07(String qd07) {
        this.qd07 = qd07;
    }

    public String getQd08() {
        return qd08;
    }

    public void setQd08(String qd08) {
        this.qd08 = qd08;
    }

    public String getQd09() {
        return qd09;
    }

    public void setQd09(String qd09) {
        this.qd09 = qd09;
    }

    public String getQe01a() {
        return qe01a;
    }

    public void setQe01a(String qe01a) {
        this.qe01a = qe01a;
    }

    public String getQe01b() {
        return qe01b;
    }

    public void setQe01b(String qe01b) {
        this.qe01b = qe01b;
    }

    public String getQe021s() {
        return qe021s;
    }

    public void setQe021s(String qe021s) {
        this.qe021s = qe021s;
    }

    public String getQe021e() {
        return qe021e;
    }

    public void setQe021e(String qe021e) {
        this.qe021e = qe021e;
    }

    public String getQe021y() {
        return qe021y;
    }

    public void setQe021y(String qe021y) {
        this.qe021y = qe021y;
    }

    public String getQe022s() {
        return qe022s;
    }

    public void setQe022s(String qe022s) {
        this.qe022s = qe022s;
    }

    public String getQe022e() {
        return qe022e;
    }

    public void setQe022e(String qe022e) {
        this.qe022e = qe022e;
    }

    public String getQe022y() {
        return qe022y;
    }

    public void setQe022y(String qe022y) {
        this.qe022y = qe022y;
    }

    public String getQe023s() {
        return qe023s;
    }

    public void setQe023s(String qe023s) {
        this.qe023s = qe023s;
    }

    public String getQe023e() {
        return qe023e;
    }

    public void setQe023e(String qe023e) {
        this.qe023e = qe023e;
    }

    public String getQe023y() {
        return qe023y;
    }

    public void setQe023y(String qe023y) {
        this.qe023y = qe023y;
    }

    public String getQe03a() {
        return qe03a;
    }

    public void setQe03a(String qe03a) {
        this.qe03a = qe03a;
    }

    public String getQe03b() {
        return qe03b;
    }

    public void setQe03b(String qe03b) {
        this.qe03b = qe03b;
    }

    public String getQe04() {
        return qe04;
    }

    public void setQe04(String qe04) {
        this.qe04 = qe04;
    }

    public String getQe041s() {
        return qe041s;
    }

    public void setQe041s(String qe041s) {
        this.qe041s = qe041s;
    }

    public String getQe041e() {
        return qe041e;
    }

    public void setQe041e(String qe041e) {
        this.qe041e = qe041e;
    }

    public String getQe041y() {
        return qe041y;
    }

    public void setQe041y(String qe041y) {
        this.qe041y = qe041y;
    }

    public String getQe042s() {
        return qe042s;
    }

    public void setQe042s(String qe042s) {
        this.qe042s = qe042s;
    }

    public String getQe042e() {
        return qe042e;
    }

    public void setQe042e(String qe042e) {
        this.qe042e = qe042e;
    }

    public String getQe042y() {
        return qe042y;
    }

    public void setQe042y(String qe042y) {
        this.qe042y = qe042y;
    }

    public String getQe043s() {
        return qe043s;
    }

    public void setQe043s(String qe043s) {
        this.qe043s = qe043s;
    }

    public String getQe043e() {
        return qe043e;
    }

    public void setQe043e(String qe043e) {
        this.qe043e = qe043e;
    }

    public String getQe043y() {
        return qe043y;
    }

    public void setQe043y(String qe043y) {
        this.qe043y = qe043y;
    }

    public String getQf01() {
        return qf01;
    }

    public void setQf01(String qf01) {
        this.qf01 = qf01;
    }

    public String getQf011s() {
        return qf011s;
    }

    public void setQf011s(String qf011s) {
        this.qf011s = qf011s;
    }

    public String getQf011e() {
        return qf011e;
    }

    public void setQf011e(String qf011e) {
        this.qf011e = qf011e;
    }

    public String getQf011y() {
        return qf011y;
    }

    public void setQf011y(String qf011y) {
        this.qf011y = qf011y;
    }

    public String getQf012s() {
        return qf012s;
    }

    public void setQf012s(String qf012s) {
        this.qf012s = qf012s;
    }

    public String getQf012e() {
        return qf012e;
    }

    public void setQf012e(String qf012e) {
        this.qf012e = qf012e;
    }

    public String getQf012y() {
        return qf012y;
    }

    public void setQf012y(String qf012y) {
        this.qf012y = qf012y;
    }

    public String getQf013s() {
        return qf013s;
    }

    public void setQf013s(String qf013s) {
        this.qf013s = qf013s;
    }

    public String getQf013e() {
        return qf013e;
    }

    public void setQf013e(String qf013e) {
        this.qf013e = qf013e;
    }

    public String getQf013y() {
        return qf013y;
    }

    public void setQf013y(String qf013y) {
        this.qf013y = qf013y;
    }

    public String getQf02() {
        return qf02;
    }

    public void setQf02(String qf02) {
        this.qf02 = qf02;
    }

    public String getQf02a() {
        return qf02a;
    }

    public void setQf02a(String qf02a) {
        this.qf02a = qf02a;
    }

    public String getQf02b() {
        return qf02b;
    }

    public void setQf02b(String qf02b) {
        this.qf02b = qf02b;
    }

    public String getQf02c() {
        return qf02c;
    }

    public void setQf02c(String qf02c) {
        this.qf02c = qf02c;
    }

    public BigDecimal getQf02d() {
        return qf02d;
    }

    public void setQf02d(BigDecimal qf02d) {
        this.qf02d = qf02d;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSenter() {
        return senter;
    }

    public void setSenter(String senter) {
        this.senter = senter;
    }

    public String getSentel() {
        return sentel;
    }

    public void setSentel(String sentel) {
        this.sentel = sentel;
    }

    public String getAdmser() {
        return admser;
    }

    public void setAdmser(String admser) {
        this.admser = admser;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public Integer getRecnt() {
        return recnt;
    }

    public void setRecnt(Integer recnt) {
        this.recnt = recnt;
    }

    public String getRermk() {
        return rermk;
    }

    public void setRermk(String rermk) {
        this.rermk = rermk;
    }

    public String getQd03at() {
        return qd03at;
    }

    public void setQd03at(String qd03at) {
        this.qd03at = qd03at;
    }

    public String getPubl() {
        return publ;
    }

    public void setPubl(String publ) {
        this.publ = publ;
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

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidKpifin)) return false;
        BidKpifinDTO bidKpifinDTO = (BidKpifinDTO) o;
        return wkut.equals(bidKpifinDTO.wkut) && prjno.equals(bidKpifinDTO.prjno) && idate.equals(bidKpifinDTO.idate) && seq.equals(bidKpifinDTO.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, idate, seq);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidKpifinDTO{" +
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

    public String getIdString() {
        return getWkut() + "-" + getPrjno() + "-" + getIdate().toString() + "-" + getSeq().toString();

    }
}
