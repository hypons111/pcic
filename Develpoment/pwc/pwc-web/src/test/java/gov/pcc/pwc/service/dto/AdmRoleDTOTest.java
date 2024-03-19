package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmRoleDTO.class);
        AdmRoleDTO admRoleDTO1 = new AdmRoleDTO();
        admRoleDTO1.setRoleId("id1");
        AdmRoleDTO admRoleDTO2 = new AdmRoleDTO();
        assertThat(admRoleDTO1).isNotEqualTo(admRoleDTO2);
        admRoleDTO2.setRoleId(admRoleDTO1.getRoleId());
        assertThat(admRoleDTO1).isEqualTo(admRoleDTO2);
        admRoleDTO2.setRoleId("id2");
        assertThat(admRoleDTO1).isNotEqualTo(admRoleDTO2);
        admRoleDTO1.setRoleId(null);
        assertThat(admRoleDTO1).isNotEqualTo(admRoleDTO2);
    }
}
