package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


public class ComStatusSearchDTO implements Serializable {

    /* from table pro_innovative_product */
    @Size(max = 50)
    private String innovateName;

    /* from table pro_org_suggestion */
    @NotNull
    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 1)
    private String status;

    @Size(max = 1)
    private String writeCondition;

    /* from table adm_company */
    @Size(max = 60)
    private String name;

    /* from table adm_agent */
    @Size(max = 60)
    private String orgName;

    public String getProInnovativeProductId() { return proInnovativeProductId;}
    public void setProInnovativeProductId(String proInnovativeProductId) { this.proInnovativeProductId = proInnovativeProductId;}

    public String getInnovateName() { return innovateName;}
    public void setInnovateName(String innovateName) { this.innovateName = innovateName;}

    public String getStatus() { return status;}
    public void setStatus(String Status) { this.status = Status;}

    public String getWriteCondition() { return writeCondition;}
    public void setWriteCondition(String writeCondition) { this.writeCondition = writeCondition;}

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public String getOrgName() { return orgName;}
    public void setOrgName(String orgName) { this.orgName = orgName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComStatusSearchDTO)) {
            return false;
        }

        ComStatusSearchDTO comStatusSearchDTO = (ComStatusSearchDTO) o;
        if (this.proInnovativeProductId == null) {
            return false;
        }
        return Objects.equals(this.proInnovativeProductId, comStatusSearchDTO.proInnovativeProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInnovativeProductId);
    }

    @Override
    public String toString() {
        return "ComStatusSearchDTO{" +
                "proInnovativeProductId='" + getProInnovativeProductId() + "'" +
                ", innovateName='" + getInnovateName() + "'" +
                ", status='" + getStatus() + "'" +
                ", writeCondition='" + getWriteCondition() + "'" +
                ", name='" + getName() + "'" +
                ", orgName='" + getOrgName() + "'" +
                "}";
    }
}
