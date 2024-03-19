package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbRoleModuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbRoleModule.class);
        PwbRoleModule pwbRoleModule1 = new PwbRoleModule();
        pwbRoleModule1.setId(1L);
        PwbRoleModule pwbRoleModule2 = new PwbRoleModule();
        pwbRoleModule2.setId(pwbRoleModule1.getId());
        assertThat(pwbRoleModule1).isEqualTo(pwbRoleModule2);
        pwbRoleModule2.setId(2L);
        assertThat(pwbRoleModule1).isNotEqualTo(pwbRoleModule2);
        pwbRoleModule1.setId(null);
        assertThat(pwbRoleModule1).isNotEqualTo(pwbRoleModule2);
    }
}
