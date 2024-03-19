package gov.pcc.pwc.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.GeoQuestion} entity.
 */
public class GeoQuestionDTO implements Serializable {

    private Long id;

    private String geoQuestionId;

    private String roleId;

    private String question;

    private String reply;

    private Instant transferTime;

    private String transferFrom;

    private String isDel="N";

    private Instant delDate;

    private String delAccId;

    private String createUser;

    private Instant createTime;

    private String updateUser;

    private Instant updateTime;

    private String status = "0";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoQuestionId() {
        return geoQuestionId;
    }

    public void setGeoQuestionId(String geoQuestionId) {
        this.geoQuestionId = geoQuestionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Instant getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Instant transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(String transferFrom) {
        this.transferFrom = transferFrom;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Instant getDelDate() {
        return delDate;
    }

    public void setDelDate(Instant delDate) {
        this.delDate = delDate;
    }

    public String getDelAccId() {
        return delAccId;
    }

    public void setDelAccId(String delAccId) {
        this.delAccId = delAccId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoQuestionDTO)) {
            return false;
        }

        GeoQuestionDTO geoQuestionDTO = (GeoQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, geoQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GeoQuestionDTO{" +
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
