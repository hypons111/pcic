package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUnitRoleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUnitRoleDTO.class);
        PwbUnitRoleDTO pwbUnitRoleDTO1 = new PwbUnitRoleDTO();
        pwbUnitRoleDTO1.setId(1L);
        PwbUnitRoleDTO pwbUnitRoleDTO2 = new PwbUnitRoleDTO();
        assertThat(pwbUnitRoleDTO1).isNotEqualTo(pwbUnitRoleDTO2);
        pwbUnitRoleDTO2.setId(pwbUnitRoleDTO1.getId());
        assertThat(pwbUnitRoleDTO1).isEqualTo(pwbUnitRoleDTO2);
        pwbUnitRoleDTO2.setId(2L);
        assertThat(pwbUnitRoleDTO1).isNotEqualTo(pwbUnitRoleDTO2);
        pwbUnitRoleDTO1.setId(null);
        assertThat(pwbUnitRoleDTO1).isNotEqualTo(pwbUnitRoleDTO2);
    }
}
