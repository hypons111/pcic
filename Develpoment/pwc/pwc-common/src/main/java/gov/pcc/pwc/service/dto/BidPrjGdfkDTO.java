package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjGdfkPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjGdfk} entity.
 */
public class BidPrjGdfkDTO implements Serializable {

//    private Long id;

    @Size(max = 21)
    private String wkut;

    @Size(max = 40)
    private String prjno;

    @Size(max = 1)
    private String useType;

    private LocalDate startDate;

    @Size(max = 100)
    private String prmNo;

    @Size(max = 10)
    private String nccUnit;

    private String nccUnitName;

    private BigDecimal lctSum;

    @Size(max = 2000)
    private String remark;

    @NotNull
    private Instant createDate;

    @NotNull
    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    private LocalDate nceDate;

    private BigDecimal confOut;

    @Size(max = 60)
    private String uIp;

    public BidPrjGdfkPrimaryKey getId() {
        return new BidPrjGdfkPrimaryKey(
                this.wkut,
                this.prjno,
                this.useType,
                this.startDate
        );
    }

    public void setId(BidPrjGdfkPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.useType = id.getUseType();
        this.startDate = id.getStartDate();
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

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPrmNo() {
        return prmNo;
    }

    public void setPrmNo(String prmNo) {
        this.prmNo = prmNo;
    }

    public String getNccUnit() {
        return nccUnit;
    }

    public void setNccUnit(String nccUnit) {
        this.nccUnit = nccUnit;
    }

    public String getNccUnitName() {
        return nccUnitName;
    }

    public void setNccUnitName(String nccUnitName) {
        this.nccUnitName = nccUnitName;
    }

    public BigDecimal getLctSum() {
        return lctSum;
    }

    public void setLctSum(BigDecimal lctSum) {
        this.lctSum = lctSum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public LocalDate getNceDate() {
        return nceDate;
    }

    public void setNceDate(LocalDate nceDate) {
        this.nceDate = nceDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BidPrjGdfkDTO)) {
            return false;
        }

        BidPrjGdfkDTO that = (BidPrjGdfkDTO) o;
        return (
                Objects.equals(wkut, that.wkut) &&
                        Objects.equals(prjno, that.prjno)
                        && Objects.equals(useType, that.useType)
                        && Objects.equals(startDate, that.startDate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, useType, startDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjGdfkDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", useType='" + getUseType() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", prmNo='" + getPrmNo() + "'" +
            ", nccUnit='" + getNccUnit() + "'" +
            ", lctSum=" + getLctSum() +
            ", remark='" + getRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", nceDate='" + getNceDate() + "'" +
            ", confOut=" + getConfOut() +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
