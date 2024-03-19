package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleApproveTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleApprove.class);
        PwbRoleApprove pwbRoleApprove1 = new PwbRoleApprove();
        pwbRoleApprove1.setId(1L);
        PwbRoleApprove pwbRoleApprove2 = new PwbRoleApprove();
        pwbRoleApprove2.setId(pwbRoleApprove1.getId());
        assertThat(pwbRoleApprove1).isEqualTo(pwbRoleApprove2);
        pwbRoleApprove2.setId(2L);
        assertThat(pwbRoleApprove1).isNotEqualTo(pwbRoleApprove2);
        pwbRoleApprove1.setId(null);
        assertThat(pwbRoleApprove1).isNotEqualTo(pwbRoleApprove2);
    }
}
