package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EngEbillingCheck} entity.
 */
public class EngEbillingCheckDTO implements Serializable {

    @NotNull
    @Size(max = 8)
    private String engEbillingCheckId;

    @NotNull
    @Size(max = 1)
    private String docType;

    @NotNull
    @Size(max = 11)
    private String docId;

    @NotNull
    @Size(max = 1)
    private String isClose;

    @NotNull
    @Size(max = 5)
    private String code1;

    @NotNull
    @Size(max = 10)
    private String code2;

    @NotNull
    @Size(max = 1)
    private String code3;

    @NotNull
    private BigDecimal price;

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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        if (!(o instanceof EngEbillingCheckDTO)) {
            return false;
        }

        EngEbillingCheckDTO engEbillingCheckDTO = (EngEbillingCheckDTO) o;
        if (this.engEbillingCheckId == null) {
            return false;
        }
        return Objects.equals(this.engEbillingCheckId, engEbillingCheckDTO.engEbillingCheckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.engEbillingCheckId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EngEbillingCheckDTO{" +
            "engEbillingCheckId='" + getEngEbillingCheckId() + "'" +
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
