package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmAccountRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmAccountRoleDTO.class);
        AdmAccountRoleDTO admAccountRoleDTO1 = new AdmAccountRoleDTO();
        admAccountRoleDTO1.setId(1L);
        AdmAccountRoleDTO admAccountRoleDTO2 = new AdmAccountRoleDTO();
        assertThat(admAccountRoleDTO1).isNotEqualTo(admAccountRoleDTO2);
        admAccountRoleDTO2.setId(admAccountRoleDTO1.getId());
        assertThat(admAccountRoleDTO1).isEqualTo(admAccountRoleDTO2);
        admAccountRoleDTO2.setId(2L);
        assertThat(admAccountRoleDTO1).isNotEqualTo(admAccountRoleDTO2);
        admAccountRoleDTO1.setId(null);
        assertThat(admAccountRoleDTO1).isNotEqualTo(admAccountRoleDTO2);
    }
}
