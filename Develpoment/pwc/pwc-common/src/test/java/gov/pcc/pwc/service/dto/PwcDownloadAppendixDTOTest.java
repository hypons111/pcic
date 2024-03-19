package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadAppendixDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadAppendixDTO.class);
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO1 = new PwcDownloadAppendixDTO();
        pwcDownloadAppendixDTO1.setProDownloadAppendixId(1L);
        PwcDownloadAppendixDTO pwcDownloadAppendixDTO2 = new PwcDownloadAppendixDTO();
        assertThat(pwcDownloadAppendixDTO1).isNotEqualTo(pwcDownloadAppendixDTO2);
        pwcDownloadAppendixDTO2.setProDownloadAppendixId(pwcDownloadAppendixDTO1.getProDownloadAppendixId());
        assertThat(pwcDownloadAppendixDTO1).isEqualTo(pwcDownloadAppendixDTO2);
        pwcDownloadAppendixDTO2.setProDownloadAppendixId(2L);
        assertThat(pwcDownloadAppendixDTO1).isNotEqualTo(pwcDownloadAppendixDTO2);
        pwcDownloadAppendixDTO1.setProDownloadAppendixId(null);
        assertThat(pwcDownloadAppendixDTO1).isNotEqualTo(pwcDownloadAppendixDTO2);
    }
}
