package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleApplyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleApply.class);
        PwbRoleApply pwbRoleApply1 = new PwbRoleApply();
        pwbRoleApply1.setId(1L);
        PwbRoleApply pwbRoleApply2 = new PwbRoleApply();
        pwbRoleApply2.setId(pwbRoleApply1.getId());
        assertThat(pwbRoleApply1).isEqualTo(pwbRoleApply2);
        pwbRoleApply2.setId(2L);
        assertThat(pwbRoleApply1).isNotEqualTo(pwbRoleApply2);
        pwbRoleApply1.setId(null);
        assertThat(pwbRoleApply1).isNotEqualTo(pwbRoleApply2);
    }
}
