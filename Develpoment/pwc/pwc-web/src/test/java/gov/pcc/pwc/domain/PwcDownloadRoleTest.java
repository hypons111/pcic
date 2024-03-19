package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadRoleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadRole.class);
        PwcDownloadRole pwcDownloadRole1 = new PwcDownloadRole();
        pwcDownloadRole1.setProDownloadRoleId(1L);
        PwcDownloadRole pwcDownloadRole2 = new PwcDownloadRole();
        pwcDownloadRole2.setProDownloadRoleId(pwcDownloadRole1.getProDownloadRoleId());
        assertThat(pwcDownloadRole1).isEqualTo(pwcDownloadRole2);
        pwcDownloadRole2.setProDownloadRoleId(2L);
        assertThat(pwcDownloadRole1).isNotEqualTo(pwcDownloadRole2);
        pwcDownloadRole1.setProDownloadRoleId(null);
        assertThat(pwcDownloadRole1).isNotEqualTo(pwcDownloadRole2);
    }
}
