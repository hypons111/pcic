package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link gov.pcc.pwc.service.dto.PwcPengingViewDTO}.
 */
public class PwcPengingViewDTO implements Serializable {

    /**
     * type: BIGINT
     */
//    @NotNull
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String unitId;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String unitName;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String userId;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String chName;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String roleId;

    /**
     * type: VARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: VARCHAR(20)", required = true)
    private String businessType;

    /**
     * type: NVARCHAR(20)
     */
//    @NotNull
    @Size(max = 20)
    @Schema(description = "type: NVARCHAR(20)", required = true)
    private String caseNum;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    @Override
    public String toString() {
        return "PwcPengingViewDTO{" +
                "unitId='" + unitId + '\'' +
                ", unitName='" + unitName + '\'' +
                ", userId='" + userId + '\'' +
                ", chName='" + chName + '\'' +
                ", roleId='" + roleId + '\'' +
                ", businessType='" + businessType + '\'' +
                ", caseNum='" + caseNum + '\'' +
                '}';
    }
}
