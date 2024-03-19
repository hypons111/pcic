package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import gov.pcc.pwc.common.enums.CommunicateType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProInnovativeProductProUserDTO implements Serializable {
    @NotNull
    @Size(max = 10)
    private String proInnovativeProductId;

    private int id;

    @Size(max = 1000)
    private String acceptedOrganization;

    @Size(max = 60)
    private String orgName;

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

    @Size(max = 1)
    private String statusInnovativeProduct;

    @Size(max = 1)
    private String statusOrgSuggestion;

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

    private Instant time;

    private String writeCondition;

    private CommunicateType communicateType;

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriteCondition() {
        return writeCondition;
    }

    public void setWriteCondition(String writeCondition) {
        this.writeCondition = writeCondition;
    }

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAcceptedOrganization() {
        return acceptedOrganization;
    }

    public void setAcceptedOrganization(String acceptedOrganization) {
        this.acceptedOrganization = acceptedOrganization;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusInnovativeProduct() {
        return statusInnovativeProduct;
    }

    public void setStatusInnovativeProduct(String statusInnovativeProduct) {
        this.statusInnovativeProduct = statusInnovativeProduct;
    }

    public String getStatusOrgSuggestion() {
        return statusOrgSuggestion;
    }

    public void setStatusOrgSuggestion(String statusOrgSuggestion) {
        this.statusOrgSuggestion = statusOrgSuggestion;
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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public CommunicateType getCommunicateType() {
        return communicateType;
    }

    public void setCommunicateType(CommunicateType communicateType) {
        this.communicateType = communicateType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProInnovativeProductProUserDTO that = (ProInnovativeProductProUserDTO) o;
        return id == that.id && Objects.equals(proInnovativeProductId, that.proInnovativeProductId) && Objects.equals(acceptedOrganization, that.acceptedOrganization) && Objects.equals(orgName, that.orgName) && Objects.equals(userId, that.userId) && Objects.equals(innovateType, that.innovateType) && Objects.equals(innovateFunction, that.innovateFunction) && Objects.equals(innovateQuality, that.innovateQuality) && Objects.equals(innovateProve, that.innovateProve) && Objects.equals(innovateCaseName, that.innovateCaseName) && Objects.equals(status, that.status) && Objects.equals(statusInnovativeProduct, that.statusInnovativeProduct) && Objects.equals(statusOrgSuggestion, that.statusOrgSuggestion) && Objects.equals(innovateName, that.innovateName) && Objects.equals(patent, that.patent) && Objects.equals(patentStartDate, that.patentStartDate) && Objects.equals(patentEndDate, that.patentEndDate) && Objects.equals(constructCost, that.constructCost) && Objects.equals(maintainCost, that.maintainCost) && Objects.equals(communicateDate, that.communicateDate) && Objects.equals(communicateDocNumber, that.communicateDocNumber) && Objects.equals(innovateCaseVendor, that.innovateCaseVendor) && Objects.equals(innovateCaseScale, that.innovateCaseScale) && Objects.equals(updateUser, that.updateUser) && Objects.equals(updateTime, that.updateTime) && Objects.equals(createUser, that.createUser) && Objects.equals(createTime, that.createTime) && Objects.equals(time, that.time) && Objects.equals(writeCondition, that.writeCondition) && communicateType == that.communicateType && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proInnovativeProductId, id, acceptedOrganization, orgName, userId, innovateType, innovateFunction, innovateQuality, innovateProve, innovateCaseName, status, statusInnovativeProduct, statusOrgSuggestion, innovateName, patent, patentStartDate, patentEndDate, constructCost, maintainCost, communicateDate, communicateDocNumber, innovateCaseVendor, innovateCaseScale, updateUser, updateTime, createUser, createTime, time, writeCondition, communicateType, companyName);
    }

    @Override
    public String toString() {
        return "ProInnovativeProductProUserDTO{" +
                "proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", id=" + id +
                ", acceptedOrganization='" + acceptedOrganization + '\'' +
                ", orgName='" + orgName + '\'' +
                ", userId='" + userId + '\'' +
                ", innovateType='" + innovateType + '\'' +
                ", innovateFunction='" + innovateFunction + '\'' +
                ", innovateQuality='" + innovateQuality + '\'' +
                ", innovateProve='" + innovateProve + '\'' +
                ", innovateCaseName='" + innovateCaseName + '\'' +
                ", status='" + status + '\'' +
                ", statusInnovativeProduct='" + statusInnovativeProduct + '\'' +
                ", statusOrgSuggestion='" + statusOrgSuggestion + '\'' +
                ", innovateName='" + innovateName + '\'' +
                ", patent='" + patent + '\'' +
                ", patentStartDate=" + patentStartDate +
                ", patentEndDate=" + patentEndDate +
                ", constructCost='" + constructCost + '\'' +
                ", maintainCost='" + maintainCost + '\'' +
                ", communicateDate=" + communicateDate +
                ", communicateDocNumber='" + communicateDocNumber + '\'' +
                ", innovateCaseVendor='" + innovateCaseVendor + '\'' +
                ", innovateCaseScale='" + innovateCaseScale + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", time=" + time +
                ", writeCondition='" + writeCondition + '\'' +
                ", communicateType=" + communicateType +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
