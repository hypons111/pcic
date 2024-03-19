package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationAppendixTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationAppendix.class);
        PwcInformationAppendix pwcInformationAppendix1 = new PwcInformationAppendix();
        pwcInformationAppendix1.setProInformationAppendixId(1L);
        PwcInformationAppendix pwcInformationAppendix2 = new PwcInformationAppendix();
        pwcInformationAppendix2.setProInformationAppendixId(pwcInformationAppendix1.getProInformationAppendixId());
        assertThat(pwcInformationAppendix1).isEqualTo(pwcInformationAppendix2);
        pwcInformationAppendix2.setProInformationAppendixId(2L);
        assertThat(pwcInformationAppendix1).isNotEqualTo(pwcInformationAppendix2);
        pwcInformationAppendix1.setProInformationAppendixId(null);
        assertThat(pwcInformationAppendix1).isNotEqualTo(pwcInformationAppendix2);
    }
}
