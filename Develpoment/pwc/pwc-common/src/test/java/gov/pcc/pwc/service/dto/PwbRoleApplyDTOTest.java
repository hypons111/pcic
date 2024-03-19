package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleApplyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleApplyDTO.class);
        PwbRoleApplyDTO pwbRoleApplyDTO1 = new PwbRoleApplyDTO();
        pwbRoleApplyDTO1.setId(1L);
        PwbRoleApplyDTO pwbRoleApplyDTO2 = new PwbRoleApplyDTO();
        assertThat(pwbRoleApplyDTO1).isNotEqualTo(pwbRoleApplyDTO2);
        pwbRoleApplyDTO2.setId(pwbRoleApplyDTO1.getId());
        assertThat(pwbRoleApplyDTO1).isEqualTo(pwbRoleApplyDTO2);
        pwbRoleApplyDTO2.setId(2L);
        assertThat(pwbRoleApplyDTO1).isNotEqualTo(pwbRoleApplyDTO2);
        pwbRoleApplyDTO1.setId(null);
        assertThat(pwbRoleApplyDTO1).isNotEqualTo(pwbRoleApplyDTO2);
    }
}
