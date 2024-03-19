package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidUnit10} entity.
 */
public class BidUnit10DTO implements Serializable {

    @Size(max = 21)
    private String code;

    @Size(max = 60)
    private String name;

    @Size(max = 60)
    private String addr;

    @Size(max = 20)
    private String tel;

    @Size(max = 20)
    private String fax;

    @Size(max = 4)
    private String cdty;

    @Size(max = 1)
    private String type;

    @Size(max = 1)
    private String dsts;

    @Size(max = 100)
    private String newcode;

    @Size(max = 20)
    private String name0;

    @Size(max = 8)
    private String can01;

    @Size(max = 20)
    private String hotLine;

    private BigDecimal capital;

    @Size(max = 7)
    private String rejsd;

    @Size(max = 7)
    private String rejed;

    private Instant createDate;

    @Size(max = 20)
    private String createUser;

    private Instant updateDate;

    @Size(max = 20)
    private String updateUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCdty() {
        return cdty;
    }

    public void setCdty(String cdty) {
        this.cdty = cdty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDsts() {
        return dsts;
    }

    public void setDsts(String dsts) {
        this.dsts = dsts;
    }

    public String getNewcode() {
        return newcode;
    }

    public void setNewcode(String newcode) {
        this.newcode = newcode;
    }

    public String getName0() {
        return name0;
    }

    public void setName0(String name0) {
        this.name0 = name0;
    }

    public String getCan01() {
        return can01;
    }

    public void setCan01(String can01) {
        this.can01 = can01;
    }

    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getRejsd() {
        return rejsd;
    }

    public void setRejsd(String rejsd) {
        this.rejsd = rejsd;
    }

    public String getRejed() {
        return rejed;
    }

    public void setRejed(String rejed) {
        this.rejed = rejed;
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
        if (!(o instanceof BidUnit10DTO)) {
            return false;
        }

        BidUnit10DTO bidUnit10DTO = (BidUnit10DTO) o;
        if (this.code == null) {
            return false;
        }
        return Objects.equals(this.code, bidUnit10DTO.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidUnit10DTO{" +
            "code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", cdty='" + getCdty() + "'" +
            ", type='" + getType() + "'" +
            ", dsts='" + getDsts() + "'" +
            ", newcode='" + getNewcode() + "'" +
            ", name0='" + getName0() + "'" +
            ", can01='" + getCan01() + "'" +
            ", hotLine='" + getHotLine() + "'" +
            ", capital=" + getCapital() +
            ", rejsd='" + getRejsd() + "'" +
            ", rejed='" + getRejed() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
