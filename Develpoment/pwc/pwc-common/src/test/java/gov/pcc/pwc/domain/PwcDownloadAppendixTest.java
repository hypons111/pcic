package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadAppendixTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadAppendix.class);
        PwcDownloadAppendix pwcDownloadAppendix1 = new PwcDownloadAppendix();
        pwcDownloadAppendix1.setProDownloadAppendixId(1L);
        PwcDownloadAppendix pwcDownloadAppendix2 = new PwcDownloadAppendix();
        pwcDownloadAppendix2.setProDownloadAppendixId(pwcDownloadAppendix1.getProDownloadAppendixId());
        assertThat(pwcDownloadAppendix1).isEqualTo(pwcDownloadAppendix2);
        pwcDownloadAppendix2.setProDownloadAppendixId(2L);
        assertThat(pwcDownloadAppendix1).isNotEqualTo(pwcDownloadAppendix2);
        pwcDownloadAppendix1.setProDownloadAppendixId(null);
        assertThat(pwcDownloadAppendix1).isNotEqualTo(pwcDownloadAppendix2);
    }
}
