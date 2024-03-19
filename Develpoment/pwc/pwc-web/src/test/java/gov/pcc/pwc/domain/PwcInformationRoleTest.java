package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationRole.class);
        PwcInformationRole pwcInformationRole1 = new PwcInformationRole();
        pwcInformationRole1.setProInformationRoleId(1L);
        PwcInformationRole pwcInformationRole2 = new PwcInformationRole();
        pwcInformationRole2.setProInformationRoleId(pwcInformationRole1.getProInformationRoleId());
        assertThat(pwcInformationRole1).isEqualTo(pwcInformationRole2);
        pwcInformationRole2.setProInformationRoleId(2L);
        assertThat(pwcInformationRole1).isNotEqualTo(pwcInformationRole2);
        pwcInformationRole1.setProInformationRoleId(null);
        assertThat(pwcInformationRole1).isNotEqualTo(pwcInformationRole2);
    }
}
