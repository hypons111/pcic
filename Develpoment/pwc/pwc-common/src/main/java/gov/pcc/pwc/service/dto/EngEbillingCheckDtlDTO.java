package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEbillingCheckDtl} entity.
 */
public class EngEbillingCheckDtlDTO implements Serializable {

    @NotNull
    @Size(max = 8)
    private String engEbillingCheckId;

    @NotNull
    private BigDecimal itemNo;

    @NotNull
    @Size(max = 1)
    private String payType;

    @NotNull
    private Instant payDate;

    @NotNull
    @Size(max = 20)
    private String payAccount;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Size(max = 1)
    private String sourceType;

    @Size(max = 400)
    private String sourceRowData;

    @NotNull
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    private String updateUser;

    @NotNull
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    private String createUser;

    @Size(max = 100)
    private String transferFrom;

    private Instant transferTime;

    public String getEngEbillingCheckId() {
        return engEbillingCheckId;
    }

    public void setEngEbillingCheckId(String engEbillingCheckId) {
        this.engEbillingCheckId = engEbillingCheckId;
    }

    public BigDecimal getItemNo() {
        return itemNo;
    }

    public void setItemNo(BigDecimal itemNo) {
        this.itemNo = itemNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Instant getPayDate() {
        return payDate;
    }

    public void setPayDate(Instant payDate) {
        this.payDate = payDate;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceRowData() {
        return sourceRowData;
    }

    public void setSourceRowData(String sourceRowData) {
        this.sourceRowData = sourceRowData;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEbillingCheckDtlDTO)) {
            return false;
        }

        EngEbillingCheckDtlDTO engEbillingCheckDtlDTO = (EngEbillingCheckDtlDTO) o;
        if (this.engEbillingCheckId == null) {
            return false;
        }
        return Objects.equals(this.engEbillingCheckId, engEbillingCheckDtlDTO.engEbillingCheckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engEbillingCheckId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEbillingCheckDtlDTO{" +
            "engEbillingCheckId='" + getEngEbillingCheckId() + "'" +
            ", itemNo=" + getItemNo() +
            ", payType='" + getPayType() + "'" +
            ", payDate='" + getPayDate() + "'" +
            ", payAccount='" + getPayAccount() + "'" +
            ", amount=" + getAmount() +
            ", sourceType='" + getSourceType() + "'" +
            ", sourceRowData='" + getSourceRowData() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
