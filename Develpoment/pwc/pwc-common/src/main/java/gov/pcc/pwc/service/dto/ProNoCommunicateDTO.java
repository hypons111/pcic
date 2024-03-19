package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProNoCommunicateDTO implements Serializable {

    @Size(max = 1)
    private String noCommunicateType;

    public String getNoCommunicateType() {
        return noCommunicateType;
    }

    public void setNoCommunicateType(String noCommunicateType) {
        this.noCommunicateType = noCommunicateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProNoCommunicateDTO that = (ProNoCommunicateDTO) o;
        return Objects.equals(noCommunicateType, that.noCommunicateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCommunicateType);
    }

    @Override
    public String toString() {
        return "ProNoCommunicateDTO{" +
                "noCommunicateType='" + noCommunicateType + '\'' +
                '}';
    }
}
