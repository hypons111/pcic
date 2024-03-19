package gov.pcc.pwc.domain;

import gov.pcc.pwc.pwb.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdmAgentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmAgent.class);
        AdmAgent admAgent1 = new AdmAgent();
        admAgent1.setOrgId("id1");
        AdmAgent admAgent2 = new AdmAgent();
        admAgent2.setOrgId(admAgent1.getOrgId());
        assertThat(admAgent1).isEqualTo(admAgent2);
        admAgent2.setOrgId("id2");
        assertThat(admAgent1).isNotEqualTo(admAgent2);
        admAgent1.setOrgId(null);
        assertThat(admAgent1).isNotEqualTo(admAgent2);
    }
}
