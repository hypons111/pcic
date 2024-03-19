package gov.pcc.pwc.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.domain.PwbUsualfunction} entity.
 */
public class PwbUsualfunctionDTO implements Serializable {

//    @NotNull
    private Long id;

//    @NotNull
    @Size(max = 20)
    private String userId;

//    @NotNull
    @Size(max = 20)
    private String functionId;

//    @NotNull
    @Size(max = 20)
    private String createUser;

//    @NotNull
    private Instant createTime;

    private List<PwcSharedDTO> usualFunctions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
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

    public List<PwcSharedDTO> getUsualFunctions() {
        return usualFunctions;
    }

    public void setUsualFunctions(List<PwcSharedDTO> usualFunctions) {
        this.usualFunctions = usualFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PwbUsualfunctionDTO)) {
            return false;
        }

        PwbUsualfunctionDTO pwbUsualfunctionDTO = (PwbUsualfunctionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pwbUsualfunctionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "PwbUsualfunctionDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", functionId='" + functionId + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", usualFunctions=" + usualFunctions +
                '}';
    }
}
