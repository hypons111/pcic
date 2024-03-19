package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjRescisPrimaryKey;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjRescis} entity.
 */
public class BidPrjRescisDTO implements Serializable {

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate stopDate;

    @Size(max = 1)
    private String contractType;

    @Size(max = 100)
    private String prmNo;

    @Size(max = 2)
    private String stopReson;

    @Size(max = 1)
    private String blong;

    @Size(max = 2000)
    private String stopRemark;

    @Size(max = 2000)
    private String contractItem;

    @Size(max = 1)
    private String isRedo;

    private LocalDate redoDate;

    @Size(max = 40)
    private String redoPrjno;

    @Size(max = 2000)
    private String noRedoReson;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public BidPrjRescisPrimaryKey getId() {
        return new BidPrjRescisPrimaryKey(this.wkut,this.prjno,this.stopDate);
    }

    public void setId(BidPrjRescisPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.stopDate = id.getStopDate();
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

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPrmNo() {
        return prmNo;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getStopReson() {
        return stopReson;
    }

    public void setStopReson(String stopReson) {
        this.stopReson = stopReson;
    }

    public String getBlong() {
        return blong;
    }

    public void setBlong(String blong) {
        this.blong = blong;
    }

    public String getStopRemark() {
        return stopRemark;
    }

    public void setStopRemark(String stopRemark) {
        this.stopRemark = stopRemark;
    }

    public String getContractItem() {
        return contractItem;
    }

    public void setContractItem(String contractItem) {
        this.contractItem = contractItem;
    }

    public String getIsRedo() {
        return isRedo;
    }

    public void setIsRedo(String isRedo) {
        this.isRedo = isRedo;
    }

    public LocalDate getRedoDate() {
        return redoDate;
    }

    public void setRedoDate(LocalDate redoDate) {
        this.redoDate = redoDate;
    }

    public String getRedoPrjno() {
        return redoPrjno;
    }

    public void setRedoPrjno(String redoPrjno) {
        this.redoPrjno = redoPrjno;
    }

    public String getNoRedoReson() {
        return noRedoReson;
    }

    public void setNoRedoReson(String noRedoReson) {
        this.noRedoReson = noRedoReson;
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
        if (!(o instanceof BidPrjRescisDTO)) return false;
        BidPrjRescisDTO that = (BidPrjRescisDTO) o;
        return Objects.equals(wkut, that.wkut) && Objects.equals(prjno, that.prjno) && Objects.equals(stopDate, that.stopDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, stopDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjRescisDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", stopDate='" + getStopDate() + "'" +
            ", contractType='" + getContractType() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", stopReson='" + getStopReson() + "'" +
            ", blong='" + getBlong() + "'" +
            ", stopRemark='" + getStopRemark() + "'" +
            ", contractItem='" + getContractItem() + "'" +
            ", isRedo='" + getIsRedo() + "'" +
            ", redoDate='" + getRedoDate() + "'" +
            ", redoPrjno='" + getRedoPrjno() + "'" +
            ", noRedoReson='" + getNoRedoReson() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }

    public String getIdString(){
        return this.wkut + "-" + this.prjno + "-" + this.stopDate.toString();
    }
}
