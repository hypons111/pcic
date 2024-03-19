package gov.pcc.pwc.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class UserManagementDTO {

    /* ADM_ACCOUNT */
    @Size(max = 20)
    private String userId;

    @Size(max = 1)
    private String status;

    /* ADM_CONTACT */
    @Size(max = 50)
    private String chName;

    /* ADM_ACCOUNT_ROLE */
    @Size(max = 20)
    private String roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserManagementDTO that = (UserManagementDTO) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserManagementDTO{" +
                "userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", chName='" + chName + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
