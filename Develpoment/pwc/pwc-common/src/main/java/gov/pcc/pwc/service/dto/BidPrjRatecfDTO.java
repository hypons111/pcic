package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjRatecfPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjRatecf} entity.
 */
public class BidPrjRatecfDTO implements Serializable {

//    private Long id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 20)
    private String prjno;

    @NotNull
    @Size(max = 3)
    private String syr;

    @NotNull
    @Size(max = 2)
    private String month;

    @NotNull
    @Size(max = 20)
    private String chkunit;

    @NotNull
    @Size(max = 3)
    private String mft;

    private BigDecimal ptrate;

    private BigDecimal ptrate1;

    private BigDecimal atrate;

    private BigDecimal atrate1;

    @Size(max = 2000)
    private String mfas;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 60)
    private String uIp;

    @Size(max = 20)
    private String mftno;

    private String name;

    private String orgName;

    public BidPrjRatecfPrimaryKey getId() {
        return new BidPrjRatecfPrimaryKey(
                this.wkut,
                this.prjno,
                this.syr,
                this.month,
                this.chkunit,
                this.mft
        );
    }

    public void setId(BidPrjRatecfPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.syr = id.getSyr();
        this.month = id.getMonth();
        this.chkunit = id.getChkunit();
        this.mft = id.getMft();
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

    public String getSyr() {
        return syr;
    }

    public void setSyr(String syr) {
        this.syr = syr;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getChkunit() {
        return chkunit;
    }

    public void setChkunit(String chkunit) {
        this.chkunit = chkunit;
    }

    public String getMft() {
        return mft;
    }

    public void setMft(String mft) {
        this.mft = mft;
    }

    public BigDecimal getPtrate() {
        return ptrate;
    }

    public void setPtrate(BigDecimal ptrate) {
        this.ptrate = ptrate;
    }

    public BigDecimal getPtrate1() {
        return ptrate1;
    }

    public void setPtrate1(BigDecimal ptrate1) {
        this.ptrate1 = ptrate1;
    }

    public BigDecimal getAtrate() {
        return atrate;
    }

    public void setAtrate(BigDecimal atrate) {
        this.atrate = atrate;
    }

    public BigDecimal getAtrate1() {
        return atrate1;
    }

    public void setAtrate1(BigDecimal atrate1) {
        this.atrate1 = atrate1;
    }

    public String getMfas() {
        return mfas;
    }

    public void setMfas(String mfas) {
        this.mfas = mfas;
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

    public String getMftno() {
        return mftno;
    }

    public void setMftno(String mftno) {
        this.mftno = mftno;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getOrgName() {return orgName;}

    public void setOrgName(String orgName) {this.orgName = orgName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjRatecfDTO)) {return false;}
        BidPrjRatecfDTO that = (BidPrjRatecfDTO) o;
        return wkut.equals(that.wkut)
                && prjno.equals(that.prjno)
                && syr.equals(that.syr)
                && month.equals(that.month)
                && chkunit.equals(that.chkunit)
                && mft.equals(that.mft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, syr, month, chkunit, mft);
    }

    @Override
    public String toString() {
        return "BidPrjRatecfDTO{" +
                "wkut='" + wkut + '\'' +
                ", prjno='" + prjno + '\'' +
                ", syr='" + syr + '\'' +
                ", month='" + month + '\'' +
                ", chkunit='" + chkunit + '\'' +
                ", mft='" + mft + '\'' +
                ", ptrate=" + ptrate +
                ", ptrate1=" + ptrate1 +
                ", atrate=" + atrate +
                ", atrate1=" + atrate1 +
                ", mfas='" + mfas + '\'' +
                ", createDate=" + createDate +
                ", createUser='" + createUser + '\'' +
                ", updateDate=" + updateDate +
                ", updateUser='" + updateUser + '\'' +
                ", uIp='" + uIp + '\'' +
                ", mftno='" + mftno + '\'' +
                ", name='" + name + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
