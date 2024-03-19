package gov.pcc.pwc.service.dto;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdmRoleDTO {

    @NotNull
    @Size(max = 20)
    private String roleId;

    @Size(max = 50)
    private String roleName;

    @Size(max = 500)
    private String roleDescript;

    @Size(max = 10)
    private String businessCategory;

    @Size(max = 50)
    private String updateUser;

    private Instant updateTime;

    private String roleFunctionList;

    private List<AdmFunctionDTO> admFunctionDTOs;

    private int rowNum;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescript() {
        return roleDescript;
    }

    public void setRoleDescript(String roleDescript) {
        this.roleDescript = roleDescript;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
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

    public String getRoleFunctionList() {
        return roleFunctionList;
    }

    public void setRoleFunctionList(String roleFunctionList) {
        this.roleFunctionList = roleFunctionList;
    }

    public List<AdmFunctionDTO> getAdmFunctionDTOs() {
        return admFunctionDTOs;
    }

    public void setAdmFunctionDTOs(List<AdmFunctionDTO> admFunctionDTOs) {
        this.admFunctionDTOs = admFunctionDTOs;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdmRoleDTO)) {
            return false;
        }

        AdmRoleDTO admAccountContactDTO = (AdmRoleDTO) o;
        if (this.roleId == null) {
            return false;
        }
        return Objects.equals(this.roleId, admAccountContactDTO.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleId);
    }

    @Override
    public String toString() {
        return "AdmRoleDTO{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDescript='" + roleDescript + '\'' +
                ", businessCategory='" + businessCategory + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", roleFunctionList='" + roleFunctionList + '\'' +
                ", admFunctionDTOs=" + admFunctionDTOs +
                ", rowNum=" + rowNum +
                '}';
    }
}
