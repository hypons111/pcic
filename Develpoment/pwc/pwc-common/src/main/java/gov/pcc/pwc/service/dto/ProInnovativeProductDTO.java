package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProInnovativeProduct} entity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProInnovativeProductDTO implements Serializable {

    @NotNull
    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 1000)
    private String acceptedOrganization;

    @Size(max = 20)
    private String userId;

    @Size(max = 2)
    private String innovateType;

    private String innovateFunction;

    private String innovateQuality;

    private String innovateProve;

    @Size(max = 200)
    private String innovateCaseName;

    @Size(max = 1)
    private String status;

    //PRO_INNOVATIVE_PRODUCT的status
    @Size(max = 1)
    private String statusIp;

    //PRO_ORG_SUGGESTION的status
    @Size(max = 1)
    private String statusOs;

    @Size(max = 50)
    private String innovateName;

    @Size(max = 1)
    private String patent;

    private Instant patentStartDate;

    private Instant patentEndDate;

    @Size(max = 100)
    private String constructCost;

    @Size(max = 100)
    private String maintainCost;

    private Instant communicateDate;

    @Size(max = 100)
    private String communicateDocNumber;

    @Size(max = 200)
    private String innovateCaseVendor;

    @Size(max = 200)
    private String innovateCaseScale;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    //PRO_ORG_SUGGESTION的TIME
    private Instant time;



    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getAcceptedOrganization() {
        return acceptedOrganization;
    }

    public void setAcceptedOrganization(String acceptedOrganization) {
        this.acceptedOrganization = acceptedOrganization;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getStatusIp() {
        return statusIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusIp(String statusIp) {
        this.statusIp = statusIp;
    }

    public String getStatusOs() {
        return statusOs;
    }

    public void setStatusOs(String statusOs) {
        this.statusOs = statusOs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInnovateType() {
        return innovateType;
    }

    public void setInnovateType(String innovateType) {
        this.innovateType = innovateType;
    }

    public String getInnovateFunction() {
        return innovateFunction;
    }

    public void setInnovateFunction(String innovateFunction) {
        this.innovateFunction = innovateFunction;
    }

    public String getInnovateQuality() {
        return innovateQuality;
    }

    public void setInnovateQuality(String innovateQuality) {
        this.innovateQuality = innovateQuality;
    }

    public String getInnovateProve() {
        return innovateProve;
    }

    public void setInnovateProve(String innovateProve) {
        this.innovateProve = innovateProve;
    }

    public String getInnovateCaseName() {
        return innovateCaseName;
    }

    public void setInnovateCaseName(String innovateCaseName) {
        this.innovateCaseName = innovateCaseName;
    }


    public String getInnovateName() {
        return innovateName;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public Instant getPatentStartDate() {
        return patentStartDate;
    }

    public void setPatentStartDate(Instant patentStartDate) {
        this.patentStartDate = patentStartDate;
    }

    public Instant getPatentEndDate() {
        return patentEndDate;
    }

    public void setPatentEndDate(Instant patentEndDate) {
        this.patentEndDate = patentEndDate;
    }

    public String getConstructCost() {
        return constructCost;
    }

    public void setConstructCost(String constructCost) {
        this.constructCost = constructCost;
    }

    public String getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(String maintainCost) {
        this.maintainCost = maintainCost;
    }

    public Instant getCommunicateDate() {
        return communicateDate;
    }

    public void setCommunicateDate(Instant communicateDate) {
        this.communicateDate = communicateDate;
    }

    public String getCommunicateDocNumber() {
        return communicateDocNumber;
    }

    public void setCommunicateDocNumber(String communicateDocNumber) {
        this.communicateDocNumber = communicateDocNumber;
    }

    public String getInnovateCaseVendor() {
        return innovateCaseVendor;
    }

    public void setInnovateCaseVendor(String innovateCaseVendor) {
        this.innovateCaseVendor = innovateCaseVendor;
    }

    public String getInnovateCaseScale() {
        return innovateCaseScale;
    }

    public void setInnovateCaseScale(String innovateCaseScale) {
        this.innovateCaseScale = innovateCaseScale;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProInnovativeProductDTO)) {
            return false;
        }

        ProInnovativeProductDTO proInnovativeProductDTO = (ProInnovativeProductDTO) o;
        if (this.proInnovativeProductId == null) {
            return false;
        }
        return Objects.equals(this.proInnovativeProductId, proInnovativeProductDTO.proInnovativeProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proInnovativeProductId);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "ProInnovativeProductDTO{" +
            "proInnovativeProductId='" + getProInnovativeProductId() + "'" +
            ", acceptedOrganization='" + getAcceptedOrganization() + "'" +
            ", userId='" + getUserId() + "'" +
            ", innovateType='" + getInnovateType() + "'" +
            ", innovateFunction='" + getInnovateFunction() + "'" +
            ", innovateQuality='" + getInnovateQuality() + "'" +
            ", innovateProve='" + getInnovateProve() + "'" +
            ", innovateCaseName='" + getInnovateCaseName() + "'" +
            ", status='" + getStatus() + "'" +
            ", innovateName='" + getInnovateName() + "'" +
            ", patent='" + getPatent() + "'" +
            ", patentStartDate='" + getPatentStartDate() + "'" +
            ", patentEndDate='" + getPatentEndDate() + "'" +
            ", constructCost='" + getConstructCost() + "'" +
            ", maintainCost='" + getMaintainCost() + "'" +
            ", communicateDate='" + getCommunicateDate() + "'" +
            ", communicateDocNumber='" + getCommunicateDocNumber() + "'" +
            ", innovateCaseVendor='" + getInnovateCaseVendor() + "'" +
            ", innovateCaseScale='" + getInnovateCaseScale() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
