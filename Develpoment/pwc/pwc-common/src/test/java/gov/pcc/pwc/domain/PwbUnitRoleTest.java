package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUnitRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUnitRole.class);
        PwbUnitRole pwbUnitRole1 = new PwbUnitRole();
        pwbUnitRole1.setId(1L);
        PwbUnitRole pwbUnitRole2 = new PwbUnitRole();
        pwbUnitRole2.setId(pwbUnitRole1.getId());
        assertThat(pwbUnitRole1).isEqualTo(pwbUnitRole2);
        pwbUnitRole2.setId(2L);
        assertThat(pwbUnitRole1).isNotEqualTo(pwbUnitRole2);
        pwbUnitRole1.setId(null);
        assertThat(pwbUnitRole1).isNotEqualTo(pwbUnitRole2);
    }
}
