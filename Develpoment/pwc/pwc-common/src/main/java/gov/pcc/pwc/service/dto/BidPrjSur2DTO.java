package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjSur2PrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjSur2} entity.
 */
public class BidPrjSur2DTO implements Serializable {

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 20)
    private String disNo;

    private BigDecimal amnta;

    private BigDecimal amntb;

    private BigDecimal amnt;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    @Size(max = 1)
    private String dsts;

    private BigDecimal confOut;

    @Size(max = 60)
    private String uIp;

    @Size(max=300)
    private String disName;

    private BigDecimal veri3Budg;



    public BidPrjSur2PrimaryKey getId() {
        return new BidPrjSur2PrimaryKey(this.wkut,this.prjno,this.disNo);
    }

    public void setId(BidPrjSur2PrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.disNo = id.getDisNo();
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

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public BigDecimal getAmnta() {
        return amnta;
    }

    public void setAmnta(BigDecimal amnta) {
        this.amnta = amnta;
    }

    public BigDecimal getAmntb() {
        return amntb;
    }

    public void setAmntb(BigDecimal amntb) {
        this.amntb = amntb;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
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

    public String getuIp() {
        return uIp;
    }

    public void setuIp(String uIp) {
        this.uIp = uIp;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public BigDecimal getVeri3Budg() {
        return veri3Budg;
    }

    public void setVeri3Budg(BigDecimal veri3Budg) {
        this.veri3Budg = veri3Budg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjSur2DTO)) {
            return false;
        }

        BidPrjSur2DTO that = (BidPrjSur2DTO) o;

        return wkut.equals(that.wkut) && prjno.equals(that.prjno) && disNo.equals(that.disNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut,prjno,disNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSur2DTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", disNo='" + getDisNo() + "'" +
            ", amnta=" + getAmnta() +
            ", amntb=" + getAmntb() +
            ", amnt=" + getAmnt() +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            ", disName='" + getDisName() + "'" +
            ", veri3Budg='" + getVeri3Budg() + "'" +
            "}";
    }
}
