package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A BidOversee.
 */
@Entity
@Table(name = "bid_oversee")
public class BidOversee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 11)
    @Column(name = "seq_no", length = 11, nullable = false)
    private String seqNo;

    @Column(name = "seq_date")
    private LocalDate seqDate;

    @Size(max = 2)
    @Column(name = "seq_flag", length = 2)
    private String seqFlag;

    @Size(max = 200)
    @Column(name = "usrname", length = 200)
    private String usrname;

    @Size(max = 120)
    @Column(name = "usraddr", length = 120)
    private String usraddr;

    @Size(max = 30)
    @Column(name = "usrtel", length = 30)
    private String usrtel;

    @Size(max = 30)
    @Column(name = "usrfax", length = 30)
    private String usrfax;

    @Size(max = 100)
    @Column(name = "usrmail", length = 100)
    private String usrmail;

    @Size(max = 1)
    @Column(name = "usrflag01", length = 1)
    private String usrflag01;

    @Size(max = 1)
    @Column(name = "usrflag02", length = 1)
    private String usrflag02;

    @Size(max = 1)
    @Column(name = "usrflag03", length = 1)
    private String usrflag03;

    @Size(max = 1)
    @Column(name = "usrflag04", length = 1)
    private String usrflag04;

    @Size(max = 1)
    @Column(name = "usrflag05", length = 1)
    private String usrflag05;

    @Size(max = 1)
    @Column(name = "usrflag06", length = 1)
    private String usrflag06;

    @Size(max = 1)
    @Column(name = "usrflag07", length = 1)
    private String usrflag07;

    @Size(max = 150)
    @Column(name = "usrmaster", length = 150)
    private String usrmaster;

    @Size(max = 2000)
    @Column(name = "usrmemo", length = 2000)
    private String usrmemo;

    @Size(max = 60)
    @Column(name = "engcode", length = 60)
    private String engcode;

    @Size(max = 2)
    @Column(name = "engflag", length = 2)
    private String engflag;

    @Size(max = 3)
    @Column(name = "engzon", length = 3)
    private String engzon;

    @Size(max = 120)
    @Column(name = "engaddr", length = 120)
    private String engaddr;

    @Size(max = 21)
    @Column(name = "comcode", length = 21)
    private String comcode;

    @Size(max = 100)
    @Column(name = "commail", length = 100)
    private String commail;

    @Size(max = 30)
    @Column(name = "comtel", length = 30)
    private String comtel;

    @Size(max = 80)
    @Column(name = "comman", length = 80)
    private String comman;

    @Size(max = 20)
    @Column(name = "comusr", length = 20)
    private String comusr;

    @Size(max = 180)
    @Column(name = "engname", length = 180)
    private String engname;

    @Column(name = "deal_date")
    private LocalDate dealDate;

    @Size(max = 1)
    @Column(name = "end_close", length = 1)
    private String endClose;

    @Column(name = "end_date_p")
    private LocalDate endDateP;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 50)
    @Column(name = "end_no", length = 50)
    private String endNo;

    @Column(name = "view_num")
    private Integer viewNum;

    @Size(max = 1)
    @Column(name = "projflag", length = 1)
    private String projflag;

    @Size(max = 2000)
    @Column(name = "delay_memo", length = 2000)
    private String delayMemo;

    @Size(max = 500)
    @Column(name = "end_memo", length = 500)
    private String endMemo;

    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "eend_date")
    private LocalDate eendDate;

    @Size(max = 21)
    @Column(name = "wkut", length = 21)
    private String wkut;

    @Size(max = 40)
    @Column(name = "prjno", length = 40)
    private String prjno;

    @Size(max = 1)
    @Column(name = "usrflag08", length = 1)
    private String usrflag08;

    @Size(max = 2)
    @Column(name = "se", length = 2)
    private String se;

    @Column(name = "iengpx", precision = 21, scale = 2)
    private BigDecimal iengpx;

    @Column(name = "iengpy", precision = 21, scale = 2)
    private BigDecimal iengpy;

    @Size(max = 21)
    @Column(name = "ipeo_what", length = 21)
    private String ipeoWhat;

    @Size(max = 40)
    @Column(name = "ipeo_prjno", length = 40)
    private String ipeoPrjno;

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

    public String getSeqNo() {
        return this.seqNo;
    }

    public BidOversee seqNo(String seqNo) {
        this.setSeqNo(seqNo);
        return this;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public LocalDate getSeqDate() {
        return this.seqDate;
    }

    public BidOversee seqDate(LocalDate seqDate) {
        this.setSeqDate(seqDate);
        return this;
    }

    public void setSeqDate(LocalDate seqDate) {
        this.seqDate = seqDate;
    }

    public String getSeqFlag() {
        return this.seqFlag;
    }

    public BidOversee seqFlag(String seqFlag) {
        this.setSeqFlag(seqFlag);
        return this;
    }

    public void setSeqFlag(String seqFlag) {
        this.seqFlag = seqFlag;
    }

    public String getUsrname() {
        return this.usrname;
    }

    public BidOversee usrname(String usrname) {
        this.setUsrname(usrname);
        return this;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsraddr() {
        return this.usraddr;
    }

    public BidOversee usraddr(String usraddr) {
        this.setUsraddr(usraddr);
        return this;
    }

    public void setUsraddr(String usraddr) {
        this.usraddr = usraddr;
    }

    public String getUsrtel() {
        return this.usrtel;
    }

    public BidOversee usrtel(String usrtel) {
        this.setUsrtel(usrtel);
        return this;
    }

    public void setUsrtel(String usrtel) {
        this.usrtel = usrtel;
    }

    public String getUsrfax() {
        return this.usrfax;
    }

    public BidOversee usrfax(String usrfax) {
        this.setUsrfax(usrfax);
        return this;
    }

    public void setUsrfax(String usrfax) {
        this.usrfax = usrfax;
    }

    public String getUsrmail() {
        return this.usrmail;
    }

    public BidOversee usrmail(String usrmail) {
        this.setUsrmail(usrmail);
        return this;
    }

    public void setUsrmail(String usrmail) {
        this.usrmail = usrmail;
    }

    public String getUsrflag01() {
        return this.usrflag01;
    }

    public BidOversee usrflag01(String usrflag01) {
        this.setUsrflag01(usrflag01);
        return this;
    }

    public void setUsrflag01(String usrflag01) {
        this.usrflag01 = usrflag01;
    }

    public String getUsrflag02() {
        return this.usrflag02;
    }

    public BidOversee usrflag02(String usrflag02) {
        this.setUsrflag02(usrflag02);
        return this;
    }

    public void setUsrflag02(String usrflag02) {
        this.usrflag02 = usrflag02;
    }

    public String getUsrflag03() {
        return this.usrflag03;
    }

    public BidOversee usrflag03(String usrflag03) {
        this.setUsrflag03(usrflag03);
        return this;
    }

    public void setUsrflag03(String usrflag03) {
        this.usrflag03 = usrflag03;
    }

    public String getUsrflag04() {
        return this.usrflag04;
    }

    public BidOversee usrflag04(String usrflag04) {
        this.setUsrflag04(usrflag04);
        return this;
    }

    public void setUsrflag04(String usrflag04) {
        this.usrflag04 = usrflag04;
    }

    public String getUsrflag05() {
        return this.usrflag05;
    }

    public BidOversee usrflag05(String usrflag05) {
        this.setUsrflag05(usrflag05);
        return this;
    }

    public void setUsrflag05(String usrflag05) {
        this.usrflag05 = usrflag05;
    }

    public String getUsrflag06() {
        return this.usrflag06;
    }

    public BidOversee usrflag06(String usrflag06) {
        this.setUsrflag06(usrflag06);
        return this;
    }

    public void setUsrflag06(String usrflag06) {
        this.usrflag06 = usrflag06;
    }

    public String getUsrflag07() {
        return this.usrflag07;
    }

    public BidOversee usrflag07(String usrflag07) {
        this.setUsrflag07(usrflag07);
        return this;
    }

    public void setUsrflag07(String usrflag07) {
        this.usrflag07 = usrflag07;
    }

    public String getUsrmaster() {
        return this.usrmaster;
    }

    public BidOversee usrmaster(String usrmaster) {
        this.setUsrmaster(usrmaster);
        return this;
    }

    public void setUsrmaster(String usrmaster) {
        this.usrmaster = usrmaster;
    }

    public String getUsrmemo() {
        return this.usrmemo;
    }

    public BidOversee usrmemo(String usrmemo) {
        this.setUsrmemo(usrmemo);
        return this;
    }

    public void setUsrmemo(String usrmemo) {
        this.usrmemo = usrmemo;
    }

    public String getEngcode() {
        return this.engcode;
    }

    public BidOversee engcode(String engcode) {
        this.setEngcode(engcode);
        return this;
    }

    public void setEngcode(String engcode) {
        this.engcode = engcode;
    }

    public String getEngflag() {
        return this.engflag;
    }

    public BidOversee engflag(String engflag) {
        this.setEngflag(engflag);
        return this;
    }

    public void setEngflag(String engflag) {
        this.engflag = engflag;
    }

    public String getEngzon() {
        return this.engzon;
    }

    public BidOversee engzon(String engzon) {
        this.setEngzon(engzon);
        return this;
    }

    public void setEngzon(String engzon) {
        this.engzon = engzon;
    }

    public String getEngaddr() {
        return this.engaddr;
    }

    public BidOversee engaddr(String engaddr) {
        this.setEngaddr(engaddr);
        return this;
    }

    public void setEngaddr(String engaddr) {
        this.engaddr = engaddr;
    }

    public String getComcode() {
        return this.comcode;
    }

    public BidOversee comcode(String comcode) {
        this.setComcode(comcode);
        return this;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    public String getCommail() {
        return this.commail;
    }

    public BidOversee commail(String commail) {
        this.setCommail(commail);
        return this;
    }

    public void setCommail(String commail) {
        this.commail = commail;
    }

    public String getComtel() {
        return this.comtel;
    }

    public BidOversee comtel(String comtel) {
        this.setComtel(comtel);
        return this;
    }

    public void setComtel(String comtel) {
        this.comtel = comtel;
    }

    public String getComman() {
        return this.comman;
    }

    public BidOversee comman(String comman) {
        this.setComman(comman);
        return this;
    }

    public void setComman(String comman) {
        this.comman = comman;
    }

    public String getComusr() {
        return this.comusr;
    }

    public BidOversee comusr(String comusr) {
        this.setComusr(comusr);
        return this;
    }

    public void setComusr(String comusr) {
        this.comusr = comusr;
    }

    public String getEngname() {
        return this.engname;
    }

    public BidOversee engname(String engname) {
        this.setEngname(engname);
        return this;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public LocalDate getDealDate() {
        return this.dealDate;
    }

    public BidOversee dealDate(LocalDate dealDate) {
        this.setDealDate(dealDate);
        return this;
    }

    public void setDealDate(LocalDate dealDate) {
        this.dealDate = dealDate;
    }

    public String getEndClose() {
        return this.endClose;
    }

    public BidOversee endClose(String endClose) {
        this.setEndClose(endClose);
        return this;
    }

    public void setEndClose(String endClose) {
        this.endClose = endClose;
    }

    public LocalDate getEndDateP() {
        return this.endDateP;
    }

    public BidOversee endDateP(LocalDate endDateP) {
        this.setEndDateP(endDateP);
        return this;
    }

    public void setEndDateP(LocalDate endDateP) {
        this.endDateP = endDateP;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public BidOversee endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEndNo() {
        return this.endNo;
    }

    public BidOversee endNo(String endNo) {
        this.setEndNo(endNo);
        return this;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public Integer getViewNum() {
        return this.viewNum;
    }

    public BidOversee viewNum(Integer viewNum) {
        this.setViewNum(viewNum);
        return this;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getProjflag() {
        return this.projflag;
    }

    public BidOversee projflag(String projflag) {
        this.setProjflag(projflag);
        return this;
    }

    public void setProjflag(String projflag) {
        this.projflag = projflag;
    }

    public String getDelayMemo() {
        return this.delayMemo;
    }

    public BidOversee delayMemo(String delayMemo) {
        this.setDelayMemo(delayMemo);
        return this;
    }

    public void setDelayMemo(String delayMemo) {
        this.delayMemo = delayMemo;
    }

    public String getEndMemo() {
        return this.endMemo;
    }

    public BidOversee endMemo(String endMemo) {
        this.setEndMemo(endMemo);
        return this;
    }

    public void setEndMemo(String endMemo) {
        this.endMemo = endMemo;
    }

    public LocalDate getSendDate() {
        return this.sendDate;
    }

    public BidOversee sendDate(LocalDate sendDate) {
        this.setSendDate(sendDate);
        return this;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getEendDate() {
        return this.eendDate;
    }

    public BidOversee eendDate(LocalDate eendDate) {
        this.setEendDate(eendDate);
        return this;
    }

    public void setEendDate(LocalDate eendDate) {
        this.eendDate = eendDate;
    }

    public String getWkut() {
        return this.wkut;
    }

    public BidOversee wkut(String wkut) {
        this.setWkut(wkut);
        return this;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getPrjno() {
        return this.prjno;
    }

    public BidOversee prjno(String prjno) {
        this.setPrjno(prjno);
        return this;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    public String getUsrflag08() {
        return this.usrflag08;
    }

    public BidOversee usrflag08(String usrflag08) {
        this.setUsrflag08(usrflag08);
        return this;
    }

    public void setUsrflag08(String usrflag08) {
        this.usrflag08 = usrflag08;
    }

    public String getSe() {
        return this.se;
    }

    public BidOversee se(String se) {
        this.setSe(se);
        return this;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public BigDecimal getIengpx() {
        return this.iengpx;
    }

    public BidOversee iengpx(BigDecimal iengpx) {
        this.setIengpx(iengpx);
        return this;
    }

    public void setIengpx(BigDecimal iengpx) {
        this.iengpx = iengpx;
    }

    public BigDecimal getIengpy() {
        return this.iengpy;
    }

    public BidOversee iengpy(BigDecimal iengpy) {
        this.setIengpy(iengpy);
        return this;
    }

    public void setIengpy(BigDecimal iengpy) {
        this.iengpy = iengpy;
    }

    public String getIpeoWhat() {
        return this.ipeoWhat;
    }

    public BidOversee ipeoWhat(String ipeoWhat) {
        this.setIpeoWhat(ipeoWhat);
        return this;
    }

    public void setIpeoWhat(String ipeoWhat) {
        this.ipeoWhat = ipeoWhat;
    }

    public String getIpeoPrjno() {
        return this.ipeoPrjno;
    }

    public BidOversee ipeoPrjno(String ipeoPrjno) {
        this.setIpeoPrjno(ipeoPrjno);
        return this;
    }

    public void setIpeoPrjno(String ipeoPrjno) {
        this.ipeoPrjno = ipeoPrjno;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public BidOversee createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public BidOversee createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public BidOversee updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public BidOversee updateUser(String updateUser) {
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
        if (!(o instanceof BidOversee)) return false;
        BidOversee that = (BidOversee) o;
        return Objects.equals(seqNo, that.seqNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidOversee{"+
            ", seqNo='" + getSeqNo() + "'" +
            ", seqDate='" + getSeqDate() + "'" +
            ", seqFlag='" + getSeqFlag() + "'" +
            ", usrname='" + getUsrname() + "'" +
            ", usraddr='" + getUsraddr() + "'" +
            ", usrtel='" + getUsrtel() + "'" +
            ", usrfax='" + getUsrfax() + "'" +
            ", usrmail='" + getUsrmail() + "'" +
            ", usrflag01='" + getUsrflag01() + "'" +
            ", usrflag02='" + getUsrflag02() + "'" +
            ", usrflag03='" + getUsrflag03() + "'" +
            ", usrflag04='" + getUsrflag04() + "'" +
            ", usrflag05='" + getUsrflag05() + "'" +
            ", usrflag06='" + getUsrflag06() + "'" +
            ", usrflag07='" + getUsrflag07() + "'" +
            ", usrmaster='" + getUsrmaster() + "'" +
            ", usrmemo='" + getUsrmemo() + "'" +
            ", engcode='" + getEngcode() + "'" +
            ", engflag='" + getEngflag() + "'" +
            ", engzon='" + getEngzon() + "'" +
            ", engaddr='" + getEngaddr() + "'" +
            ", comcode='" + getComcode() + "'" +
            ", commail='" + getCommail() + "'" +
            ", comtel='" + getComtel() + "'" +
            ", comman='" + getComman() + "'" +
            ", comusr='" + getComusr() + "'" +
            ", engname='" + getEngname() + "'" +
            ", dealDate='" + getDealDate() + "'" +
            ", endClose='" + getEndClose() + "'" +
            ", endDateP='" + getEndDateP() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", endNo='" + getEndNo() + "'" +
            ", viewNum=" + getViewNum() +
            ", projflag='" + getProjflag() + "'" +
            ", delayMemo='" + getDelayMemo() + "'" +
            ", endMemo='" + getEndMemo() + "'" +
            ", sendDate='" + getSendDate() + "'" +
            ", eendDate='" + getEendDate() + "'" +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", usrflag08='" + getUsrflag08() + "'" +
            ", se='" + getSe() + "'" +
            ", iengpx=" + getIengpx() +
            ", iengpy=" + getIengpy() +
            ", ipeoWhat='" + getIpeoWhat() + "'" +
            ", ipeoPrjno='" + getIpeoPrjno() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
