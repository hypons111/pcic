package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.AdmFile;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.ProRecommendList} entity.
 */
public class ProRecommendListDTO implements Serializable {

    private Boolean checked;

    @NotNull
    private Long proRecommendListId;

    @Size(max = 10)
    private String proInnovativeProductId;

    @Size(max = 2)
    private String recommendType;

    @Size(max = 50)
    private String recommendName;

    @Size(max = 50)
    private String recommendTel;

    @Size(max = 20)
    private String updateUser;

    private Instant updateTime;

    @Size(max = 20)
    private String createUser;

    private Instant createTime;

    private AdmFile proAdmFile;

    public Long getProRecommendListId() {
        return proRecommendListId;
    }

    public void setProRecommendListId(Long proRecommendListId) {
        this.proRecommendListId = proRecommendListId;
    }

    public String getProInnovativeProductId() {
        return proInnovativeProductId;
    }

    public void setProInnovativeProductId(String proInnovativeProductId) {
        this.proInnovativeProductId = proInnovativeProductId;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getRecommendTel() {
        return recommendTel;
    }

    public void setRecommendTel(String recommendTel) {
        this.recommendTel = recommendTel;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public AdmFile getProAdmFile() {
        return proAdmFile;
    }

    public void setProAdmFile(AdmFile proAdmFile) {
        this.proAdmFile = proAdmFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProRecommendListDTO)) {
            return false;
        }

        ProRecommendListDTO proRecommendListDTO = (ProRecommendListDTO) o;
        if (this.proRecommendListId == null) {
            return false;
        }
        return Objects.equals(this.proRecommendListId, proRecommendListDTO.proRecommendListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.proRecommendListId);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "ProRecommendListDTO{" +
                "checked=" + checked +
                ", proRecommendListId=" + proRecommendListId +
                ", proInnovativeProductId='" + proInnovativeProductId + '\'' +
                ", recommendType='" + recommendType + '\'' +
                ", recommendName='" + recommendName + '\'' +
                ", recommendTel='" + recommendTel + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", proAdmFile=" + proAdmFile +
                '}';
    }
}
