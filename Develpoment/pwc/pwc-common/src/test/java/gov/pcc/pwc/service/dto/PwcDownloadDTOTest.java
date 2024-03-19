package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadDTO.class);
        PwcDownloadDTO pwcDownloadDTO1 = new PwcDownloadDTO();
        pwcDownloadDTO1.setId(1L);
        PwcDownloadDTO pwcDownloadDTO2 = new PwcDownloadDTO();
        assertThat(pwcDownloadDTO1).isNotEqualTo(pwcDownloadDTO2);
        pwcDownloadDTO2.setId(pwcDownloadDTO1.getId());
        assertThat(pwcDownloadDTO1).isEqualTo(pwcDownloadDTO2);
        pwcDownloadDTO2.setId(2L);
        assertThat(pwcDownloadDTO1).isNotEqualTo(pwcDownloadDTO2);
        pwcDownloadDTO1.setId(null);
        assertThat(pwcDownloadDTO1).isNotEqualTo(pwcDownloadDTO2);
    }
}
