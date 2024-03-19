package gov.pcc.pwc.service.dto;

public class ProAdmAgentOrgNameDTO {

    private final String orgId;

    private final String orgName;

    public ProAdmAgentOrgNameDTO(String orgId, String orgName) {
        this.orgId = orgId;
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }
}
