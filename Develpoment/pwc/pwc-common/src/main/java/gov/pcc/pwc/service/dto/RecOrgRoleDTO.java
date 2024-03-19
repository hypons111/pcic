package gov.pcc.pwc.service.dto;

import java.util.Objects;

public class RecOrgRoleDTO {

    private String orgId;

    private String orgName;

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

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof RecOrgRoleDTO)){
            return false;
        }
        RecOrgRoleDTO recOrgRoleDTO = (RecOrgRoleDTO) o;
        if(this.orgId == null){
            return false;
        }
        return Objects.equals(this.orgId, recOrgRoleDTO.orgId);
    }

    @Override
    public String toString() {
        return "RecOrgRoleDTO{" +
                "orgId='" + getOrgId() + "'" +
                ", orgName='" + getOrgName() + "'" +
                '}';
    }
}
