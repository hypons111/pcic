package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProCommunicatedDTO implements Serializable {


    private Instant updateTimePos ;

    @Size(max = 20)
    private String personName ;

    public Instant getupdateTimePos() {
        return updateTimePos;
    }

    public void setupdateTimePos(Instant updateTimePos) {
        this.updateTimePos = updateTimePos;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProCommunicatedDTO that = (ProCommunicatedDTO) o;
        return Objects.equals(updateTimePos, that.updateTimePos) && Objects.equals(personName, that.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updateTimePos, personName);
    }

    @Override
    public String toString() {
        return "ProCommunicatedDTO{" +
                "updateTimePos=" + updateTimePos +
                ", personName='" + personName + '\'' +
                '}';
    }
}
