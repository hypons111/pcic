package gov.pcc.pwc.service.dto;

import java.util.Objects;

public class RecAdmRoleDTO {

    private String roleId;

    private String roleName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecAdmRoleDTO)) {
            return false;
        }
        RecAdmRoleDTO recAdmRoleDTO = (RecAdmRoleDTO) o;
        if(this.roleId == null){
            return false;
        }
        return Objects.equals(this.roleId, recAdmRoleDTO.roleId);
    }

    @Override
    public String toString() {
        return "RecAdmRoleDTO{" +
                "roleId='" + getRoleId() + "'" +
                ", roleName='" + getRoleName() + "'" +
                '}';
    }
}
