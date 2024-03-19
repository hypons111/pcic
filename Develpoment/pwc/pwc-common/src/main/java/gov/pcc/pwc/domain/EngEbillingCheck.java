package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A EngEbillingCheck.
 */
@Entity
@Table(name = "eng_ebilling_check")
public class EngEbillingCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 8)
    @Id
    @Column(name = "eng_ebilling_check_id", length = 8, nullable = false)
    private String engEbillingCheckId;

    @NotNull
    @Size(max = 1)
    @Column(name = "doc_type", length = 1, nullable = false)
    private String docType;

    @NotNull
    @Size(max = 11)
    @Column(name = "doc_id", length = 11, nullable = false)
    private String docId;

    @NotNull
    @Size(max = 1)
    @Column(name = "is_close", length = 1, nullable = false)
    private String isClose;

    @NotNull
    @Size(max = 5)
    @Column(name = "code1", length = 5, nullable = false)
    private String code1;

    @NotNull
    @Size(max = 10)
    @Column(name = "code2", length = 10, nullable = false)
    private String code2;

    @NotNull
    @Size(max = 1)
    @Column(name = "code3", length = 1, nullable = false)
    private String code3;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

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

    public EngEbillingCheck engEbillingCheckId(String engEbillingCheckId) {
        this.setEngEbillingCheckId(engEbillingCheckId);
        return this;
    }

    public void setEngEbillingCheckId(String engEbillingCheckId) {
        this.engEbillingCheckId = engEbillingCheckId;
    }

    public String getDocType() {
        return this.docType;
    }

    public EngEbillingCheck docType(String docType) {
        this.setDocType(docType);
        return this;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocId() {
        return this.docId;
    }

    public EngEbillingCheck docId(String docId) {
        this.setDocId(docId);
        return this;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getIsClose() {
        return this.isClose;
    }

    public EngEbillingCheck isClose(String isClose) {
        this.setIsClose(isClose);
        return this;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public String getCode1() {
        return this.code1;
    }

    public EngEbillingCheck code1(String code1) {
        this.setCode1(code1);
        return this;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return this.code2;
    }

    public EngEbillingCheck code2(String code2) {
        this.setCode2(code2);
        return this;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return this.code3;
    }

    public EngEbillingCheck code3(String code3) {
        this.setCode3(code3);
        return this;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public EngEbillingCheck price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public EngEbillingCheck updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public EngEbillingCheck updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public EngEbillingCheck createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public EngEbillingCheck createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public EngEbillingCheck transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public EngEbillingCheck transferTime(Instant transferTime) {
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
        if (!(o instanceof EngEbillingCheck)) {
            return false;
        }
        return engEbillingCheckId != null && engEbillingCheckId.equals(((EngEbillingCheck) o).engEbillingCheckId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEbillingCheck{" +
            "engEbillingCheckId=" + getEngEbillingCheckId() +
            ", docType='" + getDocType() + "'" +
            ", docId='" + getDocId() + "'" +
            ", isClose='" + getIsClose() + "'" +
            ", code1='" + getCode1() + "'" +
            ", code2='" + getCode2() + "'" +
            ", code3='" + getCode3() + "'" +
            ", price=" + getPrice() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            "}";
    }
}
