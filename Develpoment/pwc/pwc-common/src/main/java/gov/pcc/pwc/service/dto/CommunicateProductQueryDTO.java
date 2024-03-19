package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProInnovativeProduct} entity.
 */
public class CommunicateProductQueryDTO implements Serializable {

    @NotNull
    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 50)
    private String innovateName;

    @Size(max = 1)
    private String writeCondition;

    @Size(max = 60)
    private String name;

    @Size(max = 1)
    private String status;

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getInnovateName() {return innovateName;}

    public void setInnovateName(String innovateName) {this.innovateName = innovateName;}

    public String getWriteCondition() {return writeCondition;}

    public void setWriteCondition(String writeCondition) {this.writeCondition = writeCondition;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicateProductQueryDTO)) {
            return false;
        }

        CommunicateProductQueryDTO proInnovativeProductDTO = (CommunicateProductQueryDTO) o;
        if (this.proInnovativeProductId == null) {
            return false;
        }
        return Objects.equals(this.proInnovativeProductId, proInnovativeProductDTO.proInnovativeProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInnovativeProductId);
    }

    @Override
    public String toString() {
        return "CommunicateProductQueryDTO{" +
                "proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", innovateName='" + innovateName + '\'' +
                ", writeCondition='" + writeCondition + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
