package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEbillingCheckDtl.
 */
@Entity
@Table(name = "eng_ebilling_check_detail")
public class EngEbillingCheckDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 8)
    @Id
    @Column(name = "eng_ebilling_check_id", length = 8, nullable = false)
    private String engEbillingCheckId;

    @NotNull
    @Column(name = "item_no", precision = 21, scale = 2, nullable = false)
    private BigDecimal itemNo;

    @NotNull
    @Size(max = 1)
    @Column(name = "pay_type", length = 1, nullable = false)
    private String payType;

    @NotNull
    @Column(name = "pay_date", nullable = false)
    private Instant payDate;

    @NotNull
    @Size(max = 20)
    @Column(name = "pay_account", length = 20, nullable = false)
    private String payAccount;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Size(max = 1)
    @Column(name = "source_type", length = 1, nullable = false)
    private String sourceType;

    @Size(max = 400)
    @Column(name = "source_row_data", length = 400)
    private String sourceRowData;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "update_user", length = 20, nullable = false)
    private String updateUser;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    @NotNull
    @Size(max = 20)
    @Column(name = "create_user", length = 20, nullable = false)
    private String createUser;

    @Size(max = 100)
    @Column(name = "transfer_from", length = 100)
    private String transferFrom;

    @Column(name = "transfer_time")
    private Instant transferTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getEngEbillingCheckId() {
        return this.engEbillingCheckId;
    }

    public EngEbillingCheckDtl engEbillingCheckId(String engEbillingCheckId) {
        this.setEngEbillingCheckId(engEbillingCheckId);
        return this;
    }

    public void setEngEbillingCheckId(String engEbillingCheckId) {
        this.engEbillingCheckId = engEbillingCheckId;
    }

    public BigDecimal getItemNo() {
        return this.itemNo;
    }

    public EngEbillingCheckDtl itemNo(BigDecimal itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(BigDecimal itemNo) {
        this.itemNo = itemNo;
    }

    public String getPayType() {
        return this.payType;
    }

    public EngEbillingCheckDtl payType(String payType) {
        this.setPayType(payType);
        return this;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Instant getPayDate() {
        return this.payDate;
    }

    public EngEbillingCheckDtl payDate(Instant payDate) {
        this.setPayDate(payDate);
        return this;
    }

    public void setPayDate(Instant payDate) {
        this.payDate = payDate;
    }

    public String getPayAccount() {
        return this.payAccount;
    }

    public EngEbillingCheckDtl payAccount(String payAccount) {
        this.setPayAccount(payAccount);
        return this;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public EngEbillingCheckDtl amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public EngEbillingCheckDtl sourceType(String sourceType) {
        this.setSourceType(sourceType);
        return this;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceRowData() {
        return this.sourceRowData;
    }

    public EngEbillingCheckDtl sourceRowData(String sourceRowData) {
        this.setSourceRowData(sourceRowData);
        return this;
    }

    public void setSourceRowData(String sourceRowData) {
        this.sourceRowData = sourceRowData;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEbillingCheckDtl updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEbillingCheckDtl updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEbillingCheckDtl createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEbillingCheckDtl createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEbillingCheckDtl transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEbillingCheckDtl transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngEbillingCheckDtl)) {
            return false;
        }
        return engEbillingCheckId != null && engEbillingCheckId.equals(((EngEbillingCheckDtl) o).engEbillingCheckId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEbillingCheckDtl{" +
            "engEbillingCheckId=" + getEngEbillingCheckId() +
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
