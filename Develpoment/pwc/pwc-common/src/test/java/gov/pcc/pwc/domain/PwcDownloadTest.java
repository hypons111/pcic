package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownload.class);
        PwcDownload pwcDownload1 = new PwcDownload();
        pwcDownload1.setId(1L);
        PwcDownload pwcDownload2 = new PwcDownload();
        pwcDownload2.setId(pwcDownload1.getId());
        assertThat(pwcDownload1).isEqualTo(pwcDownload2);
        pwcDownload2.setId(2L);
        assertThat(pwcDownload1).isNotEqualTo(pwcDownload2);
        pwcDownload1.setId(null);
        assertThat(pwcDownload1).isNotEqualTo(pwcDownload2);
    }
}
