package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProInnovativeProduct.
 */
@Entity
@Table(name = "pro_innovative_product")
public class ProInnovativeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 10)
    @Id
    @Column(name = "pro_innovative_product_id", length = 10, nullable = false)
    private String proInnovativeProductId;

    @Size(max = 1000)
    @Column(name = "accepted_organization", length = 1000)
    private String acceptedOrganization;

    @Size(max = 20)
    @Column(name = "user_id", length = 20)
    private String userId;

    @Size(max = 2)
    @Column(name = "innovate_type", length = 2)
    private String innovateType;

    @Column(name = "innovate_function")
    private String innovateFunction;

    @Column(name = "innovate_quality")
    private String innovateQuality;

    @Column(name = "innovate_prove")
    private String innovateProve;

    @Size(max = 200)
    @Column(name = "innovate_case_name", length = 200)
    private String innovateCaseName;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @Size(max = 50)
    @Column(name = "innovate_name", length = 50)
    private String innovateName;

    @Size(max = 1)
    @Column(name = "patent", length = 1)
    private String patent;

    @Column(name = "patent_start_date")
    private Instant patentStartDate;

    @Column(name = "patent_end_date")
    private Instant patentEndDate;

    @Size(max = 100)
    @Column(name = "construct_cost", length = 100)
    private String constructCost;

    @Size(max = 100)
    @Column(name = "maintain_cost", length = 100)
    private String maintainCost;

    @Column(name = "communicate_date")
    private Instant communicateDate;

    @Size(max = 100)
    @Column(name = "communicate_doc_number", length = 100)
    private String communicateDocNumber;

    @Size(max = 200)
    @Column(name = "innovate_case_vendor", length = 200)
    private String innovateCaseVendor;

    @Size(max = 200)
    @Column(name = "innovate_case_scale", length = 200)
    private String innovateCaseScale;

    @Size(max = 20)
    @Column(name = "update_user", length = 20)
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Size(max = 20)
    @Column(name = "create_user", length = 20)
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getProInnovativeProductId() {
        return this.proInnovativeProductId;
    }

    public ProInnovativeProduct proInnovativeProductId(String proInnovativeProductId) {
        this.setProInnovativeProductId(proInnovativeProductId);
        return this;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }


    public ProInnovativeProduct userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAcceptedOrganization() {
        return this.acceptedOrganization;
    }

    public ProInnovativeProduct acceptedOrganization(String acceptedOrganization) {
        this.setAcceptedOrganization(acceptedOrganization);
        return this;
    }

    public void setAcceptedOrganization(String acceptedOrganization) {
        this.acceptedOrganization = acceptedOrganization;
    }

    public String getInnovateType() {
        return this.innovateType;
    }

    public ProInnovativeProduct innovateType(String innovateType) {
        this.setInnovateType(innovateType);
        return this;
    }

    public void setInnovateType(String innovateType) {
        this.innovateType = innovateType;
    }

    public String getInnovateFunction() {
        return this.innovateFunction;
    }

    public ProInnovativeProduct innovateFunction(String innovateFunction) {
        this.setInnovateFunction(innovateFunction);
        return this;
    }

    public void setInnovateFunction(String innovateFunction) {
        this.innovateFunction = innovateFunction;
    }

    public String getInnovateQuality() {
        return this.innovateQuality;
    }

    public ProInnovativeProduct innovateQuality(String innovateQuality) {
        this.setInnovateQuality(innovateQuality);
        return this;
    }

    public void setInnovateQuality(String innovateQuality) {
        this.innovateQuality = innovateQuality;
    }

    public String getInnovateProve() {
        return this.innovateProve;
    }

    public ProInnovativeProduct innovateProve(String innovateProve) {
        this.setInnovateProve(innovateProve);
        return this;
    }

    public void setInnovateProve(String innovateProve) {
        this.innovateProve = innovateProve;
    }

    public String getInnovateCaseName() {
        return this.innovateCaseName;
    }

    public ProInnovativeProduct innovateCaseName(String innovateCaseName) {
        this.setInnovateCaseName(innovateCaseName);
        return this;
    }

    public void setInnovateCaseName(String innovateCaseName) {
        this.innovateCaseName = innovateCaseName;
    }

    public String getStatus() {
        return this.status;
    }

    public ProInnovativeProduct status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInnovateName() {
        return this.innovateName;
    }

    public ProInnovativeProduct innovateName(String innovateName) {
        this.setInnovateName(innovateName);
        return this;
    }

    public void setInnovateName(String innovateName) {
        this.innovateName = innovateName;
    }

    public String getPatent() {
        return this.patent;
    }

    public ProInnovativeProduct patent(String patent) {
        this.setPatent(patent);
        return this;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public Instant getPatentStartDate() {
        return this.patentStartDate;
    }

    public ProInnovativeProduct patentStartDate(Instant patentStartDate) {
        this.setPatentStartDate(patentStartDate);
        return this;
    }

    public void setPatentStartDate(Instant patentStartDate) {
        this.patentStartDate = patentStartDate;
    }

    public Instant getPatentEndDate() {
        return this.patentEndDate;
    }

    public ProInnovativeProduct patentEndDate(Instant patentEndDate) {
        this.setPatentEndDate(patentEndDate);
        return this;
    }

    public void setPatentEndDate(Instant patentEndDate) {
        this.patentEndDate = patentEndDate;
    }

    public String getConstructCost() {
        return this.constructCost;
    }

    public ProInnovativeProduct constructCost(String constructCost) {
        this.setConstructCost(constructCost);
        return this;
    }

    public void setConstructCost(String constructCost) {
        this.constructCost = constructCost;
    }

    public String getMaintainCost() {
        return this.maintainCost;
    }

    public ProInnovativeProduct maintainCost(String maintainCost) {
        this.setMaintainCost(maintainCost);
        return this;
    }

    public void setMaintainCost(String maintainCost) {
        this.maintainCost = maintainCost;
    }

    public Instant getCommunicateDate() {
        return this.communicateDate;
    }

    public ProInnovativeProduct communicateDate(Instant communicateDate) {
        this.setCommunicateDate(communicateDate);
        return this;
    }

    public void setCommunicateDate(Instant communicateDate) {
        this.communicateDate = communicateDate;
    }

    public String getCommunicateDocNumber() {
        return this.communicateDocNumber;
    }

    public ProInnovativeProduct communicateDocNumber(String communicateDocNumber) {
        this.setCommunicateDocNumber(communicateDocNumber);
        return this;
    }

    public void setCommunicateDocNumber(String communicateDocNumber) {
        this.communicateDocNumber = communicateDocNumber;
    }

    public String getInnovateCaseVendor() {
        return this.innovateCaseVendor;
    }

    public ProInnovativeProduct innovateCaseVendor(String innovateCaseVendor) {
        this.setInnovateCaseVendor(innovateCaseVendor);
        return this;
    }

    public void setInnovateCaseVendor(String innovateCaseVendor) {
        this.innovateCaseVendor = innovateCaseVendor;
    }

    public String getInnovateCaseScale() {
        return this.innovateCaseScale;
    }

    public ProInnovativeProduct innovateCaseScale(String innovateCaseScale) {
        this.setInnovateCaseScale(innovateCaseScale);
        return this;
    }

    public void setInnovateCaseScale(String innovateCaseScale) {
        this.innovateCaseScale = innovateCaseScale;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public ProInnovativeProduct updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public ProInnovativeProduct updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public ProInnovativeProduct createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public ProInnovativeProduct createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProInnovativeProduct)) {
            return false;
        }
        return proInnovativeProductId != null && proInnovativeProductId.equals(((ProInnovativeProduct) o).proInnovativeProductId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "ProInnovativeProduct{" +
                "proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", acceptedOrganization='" + acceptedOrganization + '\'' +
                ", userId='" + userId + '\'' +
                ", innovateType='" + innovateType + '\'' +
                ", innovateFunction='" + innovateFunction + '\'' +
                ", innovateQuality='" + innovateQuality + '\'' +
                ", innovateProve='" + innovateProve + '\'' +
                ", innovateCaseName='" + innovateCaseName + '\'' +
                ", status='" + status + '\'' +
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
                '}';
    }
}
