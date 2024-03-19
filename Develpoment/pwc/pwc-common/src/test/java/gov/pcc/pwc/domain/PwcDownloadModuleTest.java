package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadModuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadModule.class);
        PwcDownloadModule pwcDownloadModule1 = new PwcDownloadModule();
        pwcDownloadModule1.setProDownloadModuleId(1L);
        PwcDownloadModule pwcDownloadModule2 = new PwcDownloadModule();
        pwcDownloadModule2.setProDownloadModuleId(pwcDownloadModule1.getProDownloadModuleId());
        assertThat(pwcDownloadModule1).isEqualTo(pwcDownloadModule2);
        pwcDownloadModule2.setProDownloadModuleId(2L);
        assertThat(pwcDownloadModule1).isNotEqualTo(pwcDownloadModule2);
        pwcDownloadModule1.setProDownloadModuleId(null);
        assertThat(pwcDownloadModule1).isNotEqualTo(pwcDownloadModule2);
    }
}
