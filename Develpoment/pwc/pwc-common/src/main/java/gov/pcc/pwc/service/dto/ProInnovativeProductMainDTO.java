package gov.pcc.pwc.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import gov.pcc.pwc.common.enums.CommunicateType;
import gov.pcc.pwc.domain.AdmFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProInnovativeProductMainDTO implements Serializable {

    @NotNull
    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 1000)
    private String acceptedOrganization;

    private Instant createTime;

    private Instant updateTime;

    private String updateUser;

    private String createUser;

    @Size(max = 50)
    private String innovateName ;

    @Size(max = 60)
    private String orgName ;

    private Instant communicateDate ;

    @Size(max = 100)
    private String communicateDocNumber ;

    @Size(max = 2)
    private String innovateType ;

    private String innovateFunction ;

    private String innovateQuality ;

    private String innovateProve ;

    @Size(max = 1)
    private String patent ;

    private Instant patentStartDate;

    private Instant patentEndDate ;

    @Size(max = 100)
    private String constructCost ;

    @Size(max = 100)
    private String maintainCost ;

    @Size(max = 200)
    private String innovateCaseName ;

    @Size(max = 200)
    private String innovateCaseVendor ;

    @Size(max = 200)
    private String innovateCaseScale ;

    @Size(max = 1)
    private String statusIP  ;

    private Instant time;

    @Size(max = 1)
    private String type;

    @Size(max = 500)
    private String addr ;

    @Size(max = 2000)
    private String outline ;

    @Size(max = 2000)
    private String memo ;

    @Size(max = 2000)
    private String other ;

    private String commReason ;

    @Size(max = 20)
    private String userId;

    @Size(max = 60)
    private String name  ;

    @Size(max = 10)
    private String compIdno  ;

    @Size(max = 10)
    private String addressCity  ;

    @Size(max = 10)
    private String addressTown  ;

    @Size(max = 10)
    private String addressVillage  ;

    @Size(max = 10)
    private String addressLin  ;

    @Size(max = 10)
    private String addressRoad  ;

    @Size(max = 50)
    private String chName  ;

    @Size(max = 60)
    private String title   ;

    @Size(max = 20)
    private String workTel   ;

    private CommunicateType communicateType;

    @Size(max = 1)
    private String statusInnovativeProduct;

    @Size(max = 1)
    private String statusOrgSuggestion;

    private String writeCondition;

    private AdmFile proAdmFile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWriteCondition() {
        return writeCondition;
    }

    public void setWriteCondition(String writeCondition) {
        this.writeCondition = writeCondition;
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

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getInnovateName() {
        return innovateName;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getInnovateCaseName() {
        return innovateCaseName;
    }

    public void setInnovateCaseName(String innovateCaseName) {
        this.innovateCaseName = innovateCaseName;
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

    public String getStatusIP() {
        return statusIP;
    }

    public void setStatusIP(String statusIP) {
        this.statusIP = statusIP;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getCommReason() {
        return commReason;
    }

    public void setCommReason(String commReason) {
        this.commReason = commReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getAddressVillage() {
        return addressVillage;
    }

    public void setAddressVillage(String addressVillage) {
        this.addressVillage = addressVillage;
    }

    public String getAddressLin() {
        return addressLin;
    }

    public void setAddressLin(String addressLin) {
        this.addressLin = addressLin;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public CommunicateType getCommunicateType() {
        return communicateType;
    }

    public void setCommunicateType(CommunicateType communicateType) {
        this.communicateType = communicateType;
    }

    public AdmFile getProAdmFile() {
        return proAdmFile;
    }

    public void setProAdmFile(AdmFile proAdmFile) {
        this.proAdmFile = proAdmFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProInnovativeProductMainDTO that = (ProInnovativeProductMainDTO) o;
        return Objects.equals(proInnovativeProductId, that.proInnovativeProductId) && Objects.equals(acceptedOrganization, that.acceptedOrganization) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(updateUser, that.updateUser) && Objects.equals(createUser, that.createUser) && Objects.equals(innovateName, that.innovateName) && Objects.equals(orgName, that.orgName) && Objects.equals(communicateDate, that.communicateDate) && Objects.equals(communicateDocNumber, that.communicateDocNumber) && Objects.equals(innovateType, that.innovateType) && Objects.equals(innovateFunction, that.innovateFunction) && Objects.equals(innovateQuality, that.innovateQuality) && Objects.equals(innovateProve, that.innovateProve) && Objects.equals(patent, that.patent) && Objects.equals(patentStartDate, that.patentStartDate) && Objects.equals(patentEndDate, that.patentEndDate) && Objects.equals(constructCost, that.constructCost) && Objects.equals(maintainCost, that.maintainCost) && Objects.equals(innovateCaseName, that.innovateCaseName) && Objects.equals(innovateCaseVendor, that.innovateCaseVendor) && Objects.equals(innovateCaseScale, that.innovateCaseScale) && Objects.equals(statusIP, that.statusIP) && Objects.equals(time, that.time) && Objects.equals(type, that.type) && Objects.equals(addr, that.addr) && Objects.equals(outline, that.outline) && Objects.equals(memo, that.memo) && Objects.equals(other, that.other) && Objects.equals(commReason, that.commReason) && Objects.equals(name, that.name) && Objects.equals(compIdno, that.compIdno) && Objects.equals(addressCity, that.addressCity) && Objects.equals(addressTown, that.addressTown) && Objects.equals(addressVillage, that.addressVillage) && Objects.equals(addressLin, that.addressLin) && Objects.equals(addressRoad, that.addressRoad) && Objects.equals(chName, that.chName) && Objects.equals(title, that.title) && Objects.equals(workTel, that.workTel) && communicateType == that.communicateType && Objects.equals(statusInnovativeProduct, that.statusInnovativeProduct) && Objects.equals(statusOrgSuggestion, that.statusOrgSuggestion) && Objects.equals(writeCondition, that.writeCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proInnovativeProductId, acceptedOrganization, createTime, updateTime, updateUser, createUser, innovateName, orgName, communicateDate, communicateDocNumber, innovateType, innovateFunction, innovateQuality, innovateProve, patent, patentStartDate, patentEndDate, constructCost, maintainCost, innovateCaseName, innovateCaseVendor, innovateCaseScale, statusIP, time, type, addr, outline, memo, other, commReason, name, compIdno, addressCity, addressTown, addressVillage, addressLin, addressRoad, chName, title, workTel, communicateType, statusInnovativeProduct, statusOrgSuggestion, writeCondition);
    }

    @Override
    public String toString() {
        return "ProInnovativeProductMainDTO{" +
                "proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", acceptedOrganization='" + acceptedOrganization + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", createUser='" + createUser + '\'' +
                ", innovateName='" + innovateName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", communicateDate=" + communicateDate +
                ", communicateDocNumber='" + communicateDocNumber + '\'' +
                ", innovateType='" + innovateType + '\'' +
                ", innovateFunction='" + innovateFunction + '\'' +
                ", innovateQuality='" + innovateQuality + '\'' +
                ", innovateProve='" + innovateProve + '\'' +
                ", patent='" + patent + '\'' +
                ", patentStartDate=" + patentStartDate +
                ", patentEndDate=" + patentEndDate +
                ", constructCost='" + constructCost + '\'' +
                ", maintainCost='" + maintainCost + '\'' +
                ", innovateCaseName='" + innovateCaseName + '\'' +
                ", innovateCaseVendor='" + innovateCaseVendor + '\'' +
                ", innovateCaseScale='" + innovateCaseScale + '\'' +
                ", statusIP='" + statusIP + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", addr='" + addr + '\'' +
                ", outline='" + outline + '\'' +
                ", memo='" + memo + '\'' +
                ", other='" + other + '\'' +
                ", commReason='" + commReason + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", compIdno='" + compIdno + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressTown='" + addressTown + '\'' +
                ", addressVillage='" + addressVillage + '\'' +
                ", addressLin='" + addressLin + '\'' +
                ", addressRoad='" + addressRoad + '\'' +
                ", chName='" + chName + '\'' +
                ", title='" + title + '\'' +
                ", workTel='" + workTel + '\'' +
                ", communicateType=" + communicateType +
                ", statusInnovativeProduct='" + statusInnovativeProduct + '\'' +
                ", statusOrgSuggestion='" + statusOrgSuggestion + '\'' +
                ", writeCondition='" + writeCondition + '\'' +
                ", proAdmFile=" + proAdmFile +
                '}';
    }
}
