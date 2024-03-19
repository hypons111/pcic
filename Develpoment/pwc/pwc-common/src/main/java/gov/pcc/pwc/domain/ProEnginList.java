package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProEnginList.
 */
@Entity
@Table(name = "pro_engin_list")
public class ProEnginList implements Serializable {

//    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pro_engin_list_id", nullable = false)
    private Long proEnginListId;

    @Size(max = 10)
    @Column(name = "pro_innovative_product_id", length = 10)
    private String proInnovativeProductId;

    @Size(max = 2)
    @Column(name = "engin_type", length = 2)
    private String enginType;

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

    public Long getProEnginListId() {
        return this.proEnginListId;
    }

    public ProEnginList proEnginListId(Long proEnginListId) {
        this.setProEnginListId(proEnginListId);
        return this;
    }

    public void setProEnginListId(Long proEnginListId) {
        this.proEnginListId = proEnginListId;
    }

    public String getProInnovativeProductId() {
        return this.proInnovativeProductId;
    }

    public ProEnginList proInnovativeProductId(String proInnovativeProductId) {
        this.setProInnovativeProductId(proInnovativeProductId);
        return this;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getEnginType() {
        return this.enginType;
    }

    public ProEnginList enginType(String enginType) {
        this.setEnginType(enginType);
        return this;
    }

    public void setEnginType(String enginType) {
        this.enginType = enginType;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public ProEnginList updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public ProEnginList updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public ProEnginList createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public ProEnginList createTime(Instant createTime) {
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
        if (!(o instanceof ProEnginList)) {
            return false;
        }
        return proEnginListId != null && proEnginListId.equals(((ProEnginList) o).proEnginListId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProEnginList{" +
            "proEnginListId=" + getProEnginListId() +
            ", proInnovativeProductId='" + getProInnovativeProductId() + "'" +
            ", enginType='" + getEnginType() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
