package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidMdlbasPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidMdlbas} entity.
 */
public class BidMdlbasDTO implements Serializable {

//    private Long id;

    @Size(max = 21)
    private String wkut;

    @Size(max = 20)
    private String disNo;

    @Size(max = 300)
    private String disName;

    @Size(max = 200)
    private String disPlce;

    @Size(max = 2)
    private String workType;

    private BigDecimal estBudg;

    @Size(max = 2000)
    private String workItem;

    private BigDecimal veriBudg;

    private BigDecimal veri2Budg;

    @Size(max = 2000)
    private String veri2Item;

    private BigDecimal veri3Budg;

    @Size(max = 2000)
    private String veri3Item;

    @Size(max = 200)
    private String tm2x;

    @Size(max = 200)
    private String tm2y;

    @Size(max = 200)
    private String notyBudg;

    private BigDecimal notyBudg0;

    private BigDecimal veri3Budg0;

    @Size(max = 1000)
    private String disRmk;

    private LocalDate sActdt;

    private LocalDate sActdt0;

    private LocalDate sAedat;

    private LocalDate sAedat0;

    @Size(max = 1000)
    private String chgRmk;

    @Size(max = 1)
    private String delResn;

    @Size(max = 1)
    private String delResn2;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidMdlbasPrimaryKey getId() {
        return new BidMdlbasPrimaryKey(this.wkut,this.disNo);
    }

    public void setId(BidMdlbasPrimaryKey id) {
        this.wkut = id.getWkut();
        this.disNo = id.getDisNo();
    }

    public String getWkut() {
        return wkut;
    }

    public void setWkut(String wkut) {
        this.wkut = wkut;
    }

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisPlce() {
        return disPlce;
    }

    public void setDisPlce(String disPlce) {
        this.disPlce = disPlce;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public BigDecimal getEstBudg() {
        return estBudg;
    }

    public void setEstBudg(BigDecimal estBudg) {
        this.estBudg = estBudg;
    }

    public String getWorkItem() {
        return workItem;
    }

    public void setWorkItem(String workItem) {
        this.workItem = workItem;
    }

    public BigDecimal getVeriBudg() {
        return veriBudg;
    }

    public void setVeriBudg(BigDecimal veriBudg) {
        this.veriBudg = veriBudg;
    }

    public BigDecimal getVeri2Budg() {
        return veri2Budg;
    }

    public void setVeri2Budg(BigDecimal veri2Budg) {
        this.veri2Budg = veri2Budg;
    }

    public String getVeri2Item() {
        return veri2Item;
    }

    public void setVeri2Item(String veri2Item) {
        this.veri2Item = veri2Item;
    }

    public BigDecimal getVeri3Budg() {
        return veri3Budg;
    }

    public void setVeri3Budg(BigDecimal veri3Budg) {
        this.veri3Budg = veri3Budg;
    }

    public String getVeri3Item() {
        return veri3Item;
    }

    public void setVeri3Item(String veri3Item) {
        this.veri3Item = veri3Item;
    }

    public String getTm2x() {
        return tm2x;
    }

    public void setTm2x(String tm2x) {
        this.tm2x = tm2x;
    }

    public String getTm2y() {
        return tm2y;
    }

    public void setTm2y(String tm2y) {
        this.tm2y = tm2y;
    }

    public String getNotyBudg() {
        return notyBudg;
    }

    public void setNotyBudg(String notyBudg) {
        this.notyBudg = notyBudg;
    }

    public BigDecimal getNotyBudg0() {
        return notyBudg0;
    }

    public void setNotyBudg0(BigDecimal notyBudg0) {
        this.notyBudg0 = notyBudg0;
    }

    public BigDecimal getVeri3Budg0() {
        return veri3Budg0;
    }

    public void setVeri3Budg0(BigDecimal veri3Budg0) {
        this.veri3Budg0 = veri3Budg0;
    }

    public String getDisRmk() {
        return disRmk;
    }

    public void setDisRmk(String disRmk) {
        this.disRmk = disRmk;
    }

    public LocalDate getsActdt() {
        return sActdt;
    }

    public void setsActdt(LocalDate sActdt) {
        this.sActdt = sActdt;
    }

    public LocalDate getsActdt0() {
        return sActdt0;
    }

    public void setsActdt0(LocalDate sActdt0) {
        this.sActdt0 = sActdt0;
    }

    public LocalDate getsAedat() {
        return sAedat;
    }

    public void setsAedat(LocalDate sAedat) {
        this.sAedat = sAedat;
    }

    public LocalDate getsAedat0() {
        return sAedat0;
    }

    public void setsAedat0(LocalDate sAedat0) {
        this.sAedat0 = sAedat0;
    }

    public String getChgRmk() {
        return chgRmk;
    }

    public void setChgRmk(String chgRmk) {
        this.chgRmk = chgRmk;
    }

    public String getDelResn() {
        return delResn;
    }

    public void setDelResn(String delResn) {
        this.delResn = delResn;
    }

    public String getDelResn2() {
        return delResn2;
    }

    public void setDelResn2(String delResn2) {
        this.delResn2 = delResn2;
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidMdlbasDTO)) {
            return false;
        }

        BidMdlbasDTO that = (BidMdlbasDTO) o;
        return  wkut.equals(that.wkut) && disNo.equals(that.disNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.wkut,this.disNo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidMdlbasDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", disNo='" + getDisNo() + "'" +
            ", disName='" + getDisName() + "'" +
            ", disPlce='" + getDisPlce() + "'" +
            ", workType='" + getWorkType() + "'" +
            ", estBudg=" + getEstBudg() +
            ", workItem='" + getWorkItem() + "'" +
            ", veriBudg=" + getVeriBudg() +
            ", veri2Budg=" + getVeri2Budg() +
            ", veri2Item='" + getVeri2Item() + "'" +
            ", veri3Budg=" + getVeri3Budg() +
            ", veri3Item='" + getVeri3Item() + "'" +
            ", tm2x='" + getTm2x() + "'" +
            ", tm2y='" + getTm2y() + "'" +
            ", notyBudg='" + getNotyBudg() + "'" +
            ", notyBudg0=" + getNotyBudg0() +
            ", veri3Budg0=" + getVeri3Budg0() +
            ", disRmk='" + getDisRmk() + "'" +
            ", sActdt='" + getsActdt() + "'" +
            ", sActdt0='" + getsActdt0() + "'" +
            ", sAedat='" + getsAedat() + "'" +
            ", sAedat0='" + getsAedat0() + "'" +
            ", chgRmk='" + getChgRmk() + "'" +
            ", delResn='" + getDelResn() + "'" +
            ", delResn2='" + getDelResn2() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
