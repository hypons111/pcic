package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.EctCheck} entity.
 */
public class EctSysParamDTO implements Serializable {

    private String serviceId;

    private String serviceToken;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    @Override
    public String toString() {
        return "EctSysParamDTO{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceToken='" + serviceToken + '\'' +
                '}';
    }
}
