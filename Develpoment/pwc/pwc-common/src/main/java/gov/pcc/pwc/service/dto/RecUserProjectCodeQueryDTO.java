package gov.pcc.pwc.service.dto;

import java.util.Objects;

public class RecUserProjectCodeQueryDTO {

    private String userId;

    private String chName;

    private String orgId;

    private String orgName;

    private String workTel;

    private String email;

    private String roleId;

    private  String roleName;

    private String status;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
        if (!(o instanceof RecUserProjectCodeQueryDTO )) {
            return false;
        }
        RecUserProjectCodeQueryDTO recUserProjectCodeQueryDTO = (RecUserProjectCodeQueryDTO) o;
        if(this.userId == null){
            return false;
        }

        return Objects.equals(this.userId,recUserProjectCodeQueryDTO.userId);
    }


    @Override
    public String toString() {
        return "RecUserProjectCodeQueryDTO{" +
                "userId='" + getUserId() + "'" +
                ", chName='" + getChName() + "'" +
                ", orgName='" + getOrgName() + "'" +
                ", orgId='" + getOrgId() + "'" +
                ", workTel='" + getWorkTel() + "'" +
                ", email='" + getEmail() + "'" +
                ", roleId='" + getRoleId() + "'" +
                ", roleName='" + getRoleName() + "'" +
                ", status='" + getStatus() + "'" +
                '}';
    }
}
