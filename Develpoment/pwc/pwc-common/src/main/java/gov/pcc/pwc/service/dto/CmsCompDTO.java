package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.CmsComp} entity.
 */
public class CmsCompDTO implements Serializable {

    @NotNull
    @Size(max = 50)
    private String id;

    @NotNull
    @Size(max = 1)
    private String type;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 1)
    private String useStatus;

    @Size(max = 50)
    private String chairman;

    @Size(max = 50)
    private String secretaryGeneral;

    @Size(max = 50)
    private String tel1;

    @Size(max = 50)
    private String tel2;

    @Size(max = 50)
    private String chairmanTel;

    @Size(max = 50)
    private String fax;

    @Size(max = 100)
    private String address;

    @Size(max = 50)
    private String mail;

    @Size(max = 100)
    private String subjectList;

    @Size(max = 4000)
    private String remark;

    private Instant createDate;

    private Instant modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getSecretaryGeneral() {
        return secretaryGeneral;
    }

    public void setSecretaryGeneral(String secretaryGeneral) {
        this.secretaryGeneral = secretaryGeneral;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getChairmanTel() {
        return chairmanTel;
    }

    public void setChairmanTel(String chairmanTel) {
        this.chairmanTel = chairmanTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmsCompDTO)) {
            return false;
        }

        CmsCompDTO cmsCompDTO = (CmsCompDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cmsCompDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CmsCompDTO{" +
            "id='" + getId() + "'" +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", useStatus='" + getUseStatus() + "'" +
            ", chairman='" + getChairman() + "'" +
            ", secretaryGeneral='" + getSecretaryGeneral() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", chairmanTel='" + getChairmanTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", mail='" + getMail() + "'" +
            ", subjectList='" + getSubjectList() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
