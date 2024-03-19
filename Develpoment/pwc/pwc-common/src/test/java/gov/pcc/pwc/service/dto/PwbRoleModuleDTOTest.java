package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleModuleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleModuleDTO.class);
        PwbRoleModuleDTO pwbRoleModuleDTO1 = new PwbRoleModuleDTO();
        pwbRoleModuleDTO1.setId(1L);
        PwbRoleModuleDTO pwbRoleModuleDTO2 = new PwbRoleModuleDTO();
        assertThat(pwbRoleModuleDTO1).isNotEqualTo(pwbRoleModuleDTO2);
        pwbRoleModuleDTO2.setId(pwbRoleModuleDTO1.getId());
        assertThat(pwbRoleModuleDTO1).isEqualTo(pwbRoleModuleDTO2);
        pwbRoleModuleDTO2.setId(2L);
        assertThat(pwbRoleModuleDTO1).isNotEqualTo(pwbRoleModuleDTO2);
        pwbRoleModuleDTO1.setId(null);
        assertThat(pwbRoleModuleDTO1).isNotEqualTo(pwbRoleModuleDTO2);
    }
}
