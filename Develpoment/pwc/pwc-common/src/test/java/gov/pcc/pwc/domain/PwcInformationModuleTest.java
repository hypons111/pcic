package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationModuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationModule.class);
        PwcInformationModule pwcInformationModule1 = new PwcInformationModule();
        pwcInformationModule1.setProInformationModuleId(1L);
        PwcInformationModule pwcInformationModule2 = new PwcInformationModule();
        pwcInformationModule2.setProInformationModuleId(pwcInformationModule1.getProInformationModuleId());
        assertThat(pwcInformationModule1).isEqualTo(pwcInformationModule2);
        pwcInformationModule2.setProInformationModuleId(2L);
        assertThat(pwcInformationModule1).isNotEqualTo(pwcInformationModule2);
        pwcInformationModule1.setProInformationModuleId(null);
        assertThat(pwcInformationModule1).isNotEqualTo(pwcInformationModule2);
    }
}
