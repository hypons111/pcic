package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidOversee} entity.
 */
public class BidOverseeDTO implements Serializable {

    @NotNull
    @Size(max = 11)
    private String seqNo;

    private LocalDate seqDate;

    @Size(max = 2)
    private String seqFlag;

    @Size(max = 200)
    private String usrname;

    @Size(max = 120)
    private String usraddr;

    @Size(max = 30)
    private String usrtel;

    @Size(max = 30)
    private String usrfax;

    @Size(max = 100)
    private String usrmail;

    @Size(max = 1)
    private String usrflag01;

    @Size(max = 1)
    private String usrflag02;

    @Size(max = 1)
    private String usrflag03;

    @Size(max = 1)
    private String usrflag04;

    @Size(max = 1)
    private String usrflag05;

    @Size(max = 1)
    private String usrflag06;

    @Size(max = 1)
    private String usrflag07;

    @Size(max = 150)
    private String usrmaster;

    @Size(max = 2000)
    private String usrmemo;

    @Size(max = 60)
    private String engcode;

    @Size(max = 2)
    private String engflag;

    @Size(max = 3)
    private String engzon;

    @Size(max = 120)
    private String engaddr;

    @Size(max = 21)
    private String comcode;

    @Size(max = 100)
    private String commail;

    @Size(max = 30)
    private String comtel;

    @Size(max = 80)
    private String comman;

    @Size(max = 20)
    private String comusr;

    @Size(max = 180)
    private String engname;

    private LocalDate dealDate;

    @Size(max = 1)
    private String endClose;

    private LocalDate endDateP;

    private LocalDate endDate;

    @Size(max = 50)
    private String endNo;

    private Integer viewNum;

    @Size(max = 1)
    private String projflag;

    @Size(max = 2000)
    private String delayMemo;

    @Size(max = 500)
    private String endMemo;

    private LocalDate sendDate;

    private LocalDate eendDate;

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 1)
    private String usrflag08;

    @Size(max = 2)
    private String se;

    private BigDecimal iengpx;

    private BigDecimal iengpy;

    @Size(max = 21)
    private String ipeoWhat;

    @Size(max = 40)
    private String ipeoPrjno;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public LocalDate getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(LocalDate seqDate) {
        this.seqDate = seqDate;
    }

    public String getSeqFlag() {
        return seqFlag;
    }

    public void setSeqFlag(String seqFlag) {
        this.seqFlag = seqFlag;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsraddr() {
        return usraddr;
    }

    public void setUsraddr(String usraddr) {
        this.usraddr = usraddr;
    }

    public String getUsrtel() {
        return usrtel;
    }

    public void setUsrtel(String usrtel) {
        this.usrtel = usrtel;
    }

    public String getUsrfax() {
        return usrfax;
    }

    public void setUsrfax(String usrfax) {
        this.usrfax = usrfax;
    }

    public String getUsrmail() {
        return usrmail;
    }

    public void setUsrmail(String usrmail) {
        this.usrmail = usrmail;
    }

    public String getUsrflag01() {
        return usrflag01;
    }

    public void setUsrflag01(String usrflag01) {
        this.usrflag01 = usrflag01;
    }

    public String getUsrflag02() {
        return usrflag02;
    }

    public void setUsrflag02(String usrflag02) {
        this.usrflag02 = usrflag02;
    }

    public String getUsrflag03() {
        return usrflag03;
    }

    public void setUsrflag03(String usrflag03) {
        this.usrflag03 = usrflag03;
    }

    public String getUsrflag04() {
        return usrflag04;
    }

    public void setUsrflag04(String usrflag04) {
        this.usrflag04 = usrflag04;
    }

    public String getUsrflag05() {
        return usrflag05;
    }

    public void setUsrflag05(String usrflag05) {
        this.usrflag05 = usrflag05;
    }

    public String getUsrflag06() {
        return usrflag06;
    }

    public void setUsrflag06(String usrflag06) {
        this.usrflag06 = usrflag06;
    }

    public String getUsrflag07() {
        return usrflag07;
    }

    public void setUsrflag07(String usrflag07) {
        this.usrflag07 = usrflag07;
    }

    public String getUsrmaster() {
        return usrmaster;
    }

    public void setUsrmaster(String usrmaster) {
        this.usrmaster = usrmaster;
    }

    public String getUsrmemo() {
        return usrmemo;
    }

    public void setUsrmemo(String usrmemo) {
        this.usrmemo = usrmemo;
    }

    public String getEngcode() {
        return engcode;
    }

    public void setEngcode(String engcode) {
        this.engcode = engcode;
    }

    public String getEngflag() {
        return engflag;
    }

    public void setEngflag(String engflag) {
        this.engflag = engflag;
    }

    public String getEngzon() {
        return engzon;
    }

    public void setEngzon(String engzon) {
        this.engzon = engzon;
    }

    public String getEngaddr() {
        return engaddr;
    }

    public void setEngaddr(String engaddr) {
        this.engaddr = engaddr;
    }

    public String getComcode() {
        return comcode;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    public String getCommail() {
        return commail;
    }

    public void setCommail(String commail) {
        this.commail = commail;
    }

    public String getComtel() {
        return comtel;
    }

    public void setComtel(String comtel) {
        this.comtel = comtel;
    }

    public String getComman() {
        return comman;
    }

    public void setComman(String comman) {
        this.comman = comman;
    }

    public String getComusr() {
        return comusr;
    }

    public void setComusr(String comusr) {
        this.comusr = comusr;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public LocalDate getDealDate() {
        return dealDate;
    }

    public void setDealDate(LocalDate dealDate) {
        this.dealDate = dealDate;
    }

    public String getEndClose() {
        return endClose;
    }

    public void setEndClose(String endClose) {
        this.endClose = endClose;
    }

    public LocalDate getEndDateP() {
        return endDateP;
    }

    public void setEndDateP(LocalDate endDateP) {
        this.endDateP = endDateP;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEndNo() {
        return endNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getProjflag() {
        return projflag;
    }

    public void setProjflag(String projflag) {
        this.projflag = projflag;
    }

    public String getDelayMemo() {
        return delayMemo;
    }

    public void setDelayMemo(String delayMemo) {
        this.delayMemo = delayMemo;
    }

    public String getEndMemo() {
        return endMemo;
    }

    public void setEndMemo(String endMemo) {
        this.endMemo = endMemo;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getEendDate() {
        return eendDate;
    }

    public void setEendDate(LocalDate eendDate) {
        this.eendDate = eendDate;
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

    public String getUsrflag08() {
        return usrflag08;
    }

    public void setUsrflag08(String usrflag08) {
        this.usrflag08 = usrflag08;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public BigDecimal getIengpx() {
        return iengpx;
    }

    public void setIengpx(BigDecimal iengpx) {
        this.iengpx = iengpx;
    }

    public BigDecimal getIengpy() {
        return iengpy;
    }

    public void setIengpy(BigDecimal iengpy) {
        this.iengpy = iengpy;
    }

    public String getIpeoWhat() {
        return ipeoWhat;
    }

    public void setIpeoWhat(String ipeoWhat) {
        this.ipeoWhat = ipeoWhat;
    }

    public String getIpeoPrjno() {
        return ipeoPrjno;
    }

    public void setIpeoPrjno(String ipeoPrjno) {
        this.ipeoPrjno = ipeoPrjno;
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
        if (!(o instanceof BidOverseeDTO)) return false;
        BidOverseeDTO that = (BidOverseeDTO) o;
        return Objects.equals(seqNo, that.seqNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidOverseeDTO{" +
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
