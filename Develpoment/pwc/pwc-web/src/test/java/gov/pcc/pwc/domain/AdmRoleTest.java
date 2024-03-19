package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmRole.class);
        AdmRole admRole1 = new AdmRole();
        admRole1.setRoleId("id1");
        AdmRole admRole2 = new AdmRole();
        admRole2.setRoleId(admRole1.getRoleId());
        assertThat(admRole1).isEqualTo(admRole2);
        admRole2.setRoleId("id2");
        assertThat(admRole1).isNotEqualTo(admRole2);
        admRole1.setRoleId(null);
        assertThat(admRole1).isNotEqualTo(admRole2);
    }
}
