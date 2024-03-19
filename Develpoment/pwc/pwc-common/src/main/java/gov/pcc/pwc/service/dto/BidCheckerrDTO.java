package gov.pcc.pwc.service.dto;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.BidCheckerr} entity.
 */
public class BidCheckerrDTO implements Serializable {

    @Id
    @NotNull
    @Size(max = 20)
    private String code;

    @Size(max = 1200)
    private String name;

    @Size(max = 1)
    private String typ;

    private Integer ord;

    @Size(max = 1)
    private String flag;

    @NotNull
    private Instant createDate;

    @NotNull
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

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
        if (!(o instanceof BidCheckerrDTO)) {
            return false;
        }

        BidCheckerrDTO bidCheckerrDTO = (BidCheckerrDTO) o;
        if (this.code == null) {
            return false;
        }
        return Objects.equals(this.code, bidCheckerrDTO.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BidCheckerrDTO{" +
            "code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", typ='" + getTyp() + "'" +
            ", ord=" + getOrd() +
            ", flag='" + getFlag() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            "}";
    }
}
