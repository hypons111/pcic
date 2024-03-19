package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A CmsComp.
 */
@Entity
@Table(name = "cms_comp")
public class CmsComp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 1)
    @Column(name = "type", length = 1, nullable = false)
    private String type;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(name = "usestatus", length = 1, nullable = false)
    private String useStatus;

    @Size(max = 50)
    @Column(name = "chairman", length = 50)
    private String chairman;

    @Size(max = 50)
    @Column(name = "secretarygeneral", length = 50)
    private String secretaryGeneral;

    @Size(max = 50)
    @Column(name = "tel1", length = 50)
    private String tel1;

    @Size(max = 50)
    @Column(name = "tel2", length = 50)
    private String tel2;

    @Size(max = 50)
    @Column(name = "chairmantel", length = 50)
    private String chairmanTel;

    @Size(max = 50)
    @Column(name = "fax", length = 50)
    private String fax;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

    @Size(max = 50)
    @Column(name = "mail", length = 50)
    private String mail;

    @Size(max = 100)
    @Column(name = "subjectlist", length = 100)
    private String subjectList;

    @Size(max = 4000)
    @Column(name = "remark", length = 4000)
    private String remark;

    @Column(name = "createdate")
    private Instant createDate;

    @Column(name = "modifydate")
    private Instant modifyDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CmsComp id(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public CmsComp type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public CmsComp name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseStatus() {
        return this.useStatus;
    }

    public CmsComp useStatus(String useStatus) {
        this.useStatus = useStatus;
        return this;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getChairman() {
        return this.chairman;
    }

    public CmsComp chairman(String chairman) {
        this.chairman = chairman;
        return this;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getSecretaryGeneral() {
        return this.secretaryGeneral;
    }

    public CmsComp secretaryGeneral(String secretaryGeneral) {
        this.secretaryGeneral = secretaryGeneral;
        return this;
    }

    public void setSecretaryGeneral(String secretaryGeneral) {
        this.secretaryGeneral = secretaryGeneral;
    }

    public String getTel1() {
        return this.tel1;
    }

    public CmsComp tel1(String tel1) {
        this.tel1 = tel1;
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return this.tel2;
    }

    public CmsComp tel2(String tel2) {
        this.tel2 = tel2;
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getChairmanTel() {
        return this.chairmanTel;
    }

    public CmsComp chairmanTel(String chairmanTel) {
        this.chairmanTel = chairmanTel;
        return this;
    }

    public void setChairmanTel(String chairmanTel) {
        this.chairmanTel = chairmanTel;
    }

    public String getFax() {
        return this.fax;
    }

    public CmsComp fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return this.address;
    }

    public CmsComp address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return this.mail;
    }

    public CmsComp mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubjectList() {
        return this.subjectList;
    }

    public CmsComp subjectList(String subjectList) {
        this.subjectList = subjectList;
        return this;
    }

    public void setSubjectList(String subjectList) {
        this.subjectList = subjectList;
    }

    public String getRemark() {
        return this.remark;
    }

    public CmsComp remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public CmsComp createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getModifyDate() {
        return this.modifyDate;
    }

    public CmsComp modifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(Instant modifyDate) {
        this.modifyDate = modifyDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmsComp)) {
            return false;
        }
        return id != null && id.equals(((CmsComp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CmsComp{" +
            "id=" + getId() +
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
