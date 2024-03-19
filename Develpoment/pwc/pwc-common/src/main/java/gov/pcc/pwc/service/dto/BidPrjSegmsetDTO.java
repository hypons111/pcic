package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.BidPrjSegmsetPrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidPrjSegmset} entity.
 */
public class BidPrjSegmsetDTO implements Serializable {
//
//    private Long id;

    @NotNull
    @Size(max = 21)
    private String wkut;

    @NotNull
    @Size(max = 40)
    private String prjno;

    @NotNull
    private LocalDate agreeDate;

    @Size(max = 100)
    private String agreeNum;

    @Size(max = 1)
    private String agreeReson;

    private BigDecimal agreeValue;

    private LocalDate tearDownDate;

    private BigDecimal totalValue;

    @Size(max = 2000)
    private String valueRemark;

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

    public BidPrjSegmsetPrimaryKey getId() {
        return new BidPrjSegmsetPrimaryKey(
                this.wkut,
                this.prjno,
                this.agreeDate
        );
    }

    public void setId(BidPrjSegmsetPrimaryKey id) {
        this.wkut = id.getWkut();
        this.prjno = id.getPrjno();
        this.agreeDate = id.getAgreeDate();
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

    public LocalDate getAgreeDate() {
        return agreeDate;
    }

    public void setAgreeDate(LocalDate agreeDate) {
        this.agreeDate = agreeDate;
    }

    public String getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(String agreeNum) {
        this.agreeNum = agreeNum;
    }

    public String getAgreeReson() {
        return agreeReson;
    }

    public void setAgreeReson(String agreeReson) {
        this.agreeReson = agreeReson;
    }

    public BigDecimal getAgreeValue() {
        return agreeValue;
    }

    public void setAgreeValue(BigDecimal agreeValue) {
        this.agreeValue = agreeValue;
    }

    public LocalDate getTearDownDate() {
        return tearDownDate;
    }

    public void setTearDownDate(LocalDate tearDownDate) {
        this.tearDownDate = tearDownDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getValueRemark() {
        return valueRemark;
    }

    public void setValueRemark(String valueRemark) {
        this.valueRemark = valueRemark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BidPrjSegmsetDTO)) {return false;}
        BidPrjSegmsetDTO that = (BidPrjSegmsetDTO) o;
        return (
                Objects.equals(wkut, that.wkut) &&
                        Objects.equals(prjno, that.prjno) &&
                        Objects.equals(agreeDate, that.agreeDate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(wkut, prjno, agreeDate);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidPrjSegmsetDTO{" +
            "id=" + getId() +
            ", wkut='" + getWkut() + "'" +
            ", prjno='" + getPrjno() + "'" +
            ", agreeDate='" + getAgreeDate() + "'" +
            ", agreeNum='" + getAgreeNum() + "'" +
            ", agreeReson='" + getAgreeReson() + "'" +
            ", agreeValue=" + getAgreeValue() +
            ", tearDownDate='" + getTearDownDate() + "'" +
            ", totalValue=" + getTotalValue() +
            ", valueRemark='" + getValueRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", uIp='" + getuIp() + "'" +
            "}";
    }
}
