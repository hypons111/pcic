package gov.pcc.pwc.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GeoQuestion.
 */
@Entity
@Table(name = "geo_question")
public class GeoQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "geo_question_id")
    private String geoQuestionId;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "question")
    private String question;

    @Column(name = "reply")
    private String reply;

    @Column(name = "transfer_time")
    private Instant transferTime;

    @Column(name = "transfer_from")
    private String transferFrom;


    @Column(name = "is_del")
    private String isDel;

    @Column(name = "del_date")
    private Instant delDate;

    @Column(name = "del_acc_id")
    private String delAccId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GeoQuestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoQuestionId() {
        return this.geoQuestionId;
    }

    public GeoQuestion geoQuestionId(String geoQuestionId) {
        this.setGeoQuestionId(geoQuestionId);
        return this;
    }

    public void setGeoQuestionId(String geoQuestionId) {
        this.geoQuestionId = geoQuestionId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public GeoQuestion roleId(String roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getQuestion() {
        return this.question;
    }

    public GeoQuestion question(String question) {
        this.setQuestion(question);
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return this.reply;
    }

    public GeoQuestion reply(String reply) {
        this.setReply(reply);
        return this;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Instant getTransferTime() {
        return this.transferTime;
    }

    public GeoQuestion transferTime(Instant transferTime) {
        this.setTransferTime(transferTime);
        return this;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferFrom() {
        return this.transferFrom;
    }

    public GeoQuestion transferFrom(String transferFrom) {
        this.setTransferFrom(transferFrom);
        return this;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getIsDel() {
        return this.isDel;
    }

    public GeoQuestion isDel(String isDel) {
        this.setIsDel(isDel);
        return this;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Instant getDelDate() {
        return this.delDate;
    }

    public GeoQuestion delDate(Instant delDate) {
        this.setDelDate(delDate);
        return this;
    }

    public void setDelDate(Instant delDate) {
        this.delDate = delDate;
    }

    public String getDelAccId() {
        return this.delAccId;
    }

    public GeoQuestion delAccId(String delAccId) {
        this.setDelAccId(delAccId);
        return this;
    }

    public void setDelAccId(String delAccId) {
        this.delAccId = delAccId;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public GeoQuestion createUser(String createUser) {
        this.setCreateUser(createUser);
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public GeoQuestion createTime(Instant createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public GeoQuestion updateUser(String updateUser) {
        this.setUpdateUser(updateUser);
        return this;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Instant getUpdateTime() {
        return this.updateTime;
    }

    public GeoQuestion updateTime(Instant updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public GeoQuestion status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoQuestion)) {
            return false;
        }
        return id != null && id.equals(((GeoQuestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoQuestion{" +
            "id=" + getId() +
            ", geoQuestionId=" + getGeoQuestionId() +
            ", roleId='" + getRoleId() + "'" +
            ", question='" + getQuestion() + "'" +
            ", reply='" + getReply() + "'" +
            ", transferTime='" + getTransferTime() + "'" +
            ", transferFrom='" + getTransferFrom() + "'" +
            ", isDel='" + getIsDel() + "'" +
            ", delDate='" + getDelDate() + "'" +
            ", delAccId='" + getDelAccId() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateUser='" + getUpdateUser() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
