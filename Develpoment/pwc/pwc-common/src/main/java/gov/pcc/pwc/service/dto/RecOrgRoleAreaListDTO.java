package gov.pcc.pwc.service.dto;

import java.util.Objects;

public class RecOrgRoleAreaListDTO {

    private String areaName;

    private String orgId;

    private String orgName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof RecOrgRoleAreaListDTO)){
            return false;
        }
        RecOrgRoleAreaListDTO recOrgRoleAreaListDTO = (RecOrgRoleAreaListDTO) o;
        if(this.orgId == null){
            return false;
        }
        return Objects.equals(this.orgId,recOrgRoleAreaListDTO.orgId);
    }

    @Override
    public String toString() {
        return "RecOrgRoleAreaListDTO{" +
                "areaName='" + getAreaName() + "'" +
                "orgId='" + getOrgId() + "'" +
                ", orgName='" + getOrgName() + "'" +
                '}';
    }
}
