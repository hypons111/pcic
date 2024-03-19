package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleApproveDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleApproveDTO.class);
        PwbRoleApproveDTO pwbRoleApproveDTO1 = new PwbRoleApproveDTO();
        pwbRoleApproveDTO1.setId(1L);
        PwbRoleApproveDTO pwbRoleApproveDTO2 = new PwbRoleApproveDTO();
        assertThat(pwbRoleApproveDTO1).isNotEqualTo(pwbRoleApproveDTO2);
        pwbRoleApproveDTO2.setId(pwbRoleApproveDTO1.getId());
        assertThat(pwbRoleApproveDTO1).isEqualTo(pwbRoleApproveDTO2);
        pwbRoleApproveDTO2.setId(2L);
        assertThat(pwbRoleApproveDTO1).isNotEqualTo(pwbRoleApproveDTO2);
        pwbRoleApproveDTO1.setId(null);
        assertThat(pwbRoleApproveDTO1).isNotEqualTo(pwbRoleApproveDTO2);
    }
}
