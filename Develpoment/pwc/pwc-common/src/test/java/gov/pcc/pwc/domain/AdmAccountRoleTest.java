package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmAccountRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmAccountRole.class);
        AdmAccountRole admAccountRole1 = new AdmAccountRole();
        admAccountRole1.setId(1L);
        AdmAccountRole admAccountRole2 = new AdmAccountRole();
        admAccountRole2.setId(admAccountRole1.getId());
        assertThat(admAccountRole1).isEqualTo(admAccountRole2);
        admAccountRole2.setId(2L);
        assertThat(admAccountRole1).isNotEqualTo(admAccountRole2);
        admAccountRole1.setId(null);
        assertThat(admAccountRole1).isNotEqualTo(admAccountRole2);
    }
}
