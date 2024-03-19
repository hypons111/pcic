package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ProRecommendList.
 */
@Entity
@Table(name = "pro_recommend_list")
public class ProRecommendList implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "pro_recommend_list_id", nullable = false)
    private Long proRecommendListId;

    @Size(max = 10)
    @Column(name = "pro_innovative_product_id", length = 10)
    private String proInnovativeProductId;

    @Size(max = 2)
    @Column(name = "recommend_type", length = 2)
    private String recommendType;

    @Size(max = 50)
    @Column(name = "recommend_name", length = 50)
    private String recommendName;

    @Size(max = 50)
    @Column(name = "recommend_tel", length = 50)
    private String recommendTel;

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

    public Long getProRecommendListId() {
        return this.proRecommendListId;
    }

    public ProRecommendList proRecommendListId(Long proRecommendListId) {
        this.setProRecommendListId(proRecommendListId);
        return this;
    }

    public void setProRecommendListId(Long proRecommendListId) {
        this.proRecommendListId = proRecommendListId;
    }

    public String getProInnovativeProductId() {
        return this.proInnovativeProductId;
    }

    public ProRecommendList proInnovativeProductId(String proInnovativeProductId) {
        this.setProInnovativeProductId(proInnovativeProductId);
        return this;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getRecommendType() {
        return this.recommendType;
    }

    public ProRecommendList recommendType(String recommendType) {
        this.setRecommendType(recommendType);
        return this;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getRecommendName() {
        return this.recommendName;
    }

    public ProRecommendList recommendName(String recommendName) {
        this.setRecommendName(recommendName);
        return this;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getRecommendTel() {
        return this.recommendTel;
    }

    public ProRecommendList recommendTel(String recommendTel) {
        this.setRecommendTel(recommendTel);
        return this;
    }

    public void setRecommendTel(String recommendTel) {
        this.recommendTel = recommendTel;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public ProRecommendList updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public ProRecommendList updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public ProRecommendList createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public ProRecommendList createTime(Instant createTime) {
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
        if (!(o instanceof ProRecommendList)) {
            return false;
        }
        return proRecommendListId != null && proRecommendListId.equals(((ProRecommendList) o).proRecommendListId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProRecommendList{" +
            "proRecommendListId=" + getProRecommendListId() +
            ", proInnovativeProductId='" + getProInnovativeProductId() + "'" +
            ", recommendType='" + getRecommendType() + "'" +
            ", recommendName='" + getRecommendName() + "'" +
            ", recommendTel='" + getRecommendTel() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
