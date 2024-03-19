package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcDownloadModuleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcDownloadModuleDTO.class);
        PwcDownloadModuleDTO pwcDownloadModuleDTO1 = new PwcDownloadModuleDTO();
        pwcDownloadModuleDTO1.setProDownloadModuleId(1L);
        PwcDownloadModuleDTO pwcDownloadModuleDTO2 = new PwcDownloadModuleDTO();
        assertThat(pwcDownloadModuleDTO1).isNotEqualTo(pwcDownloadModuleDTO2);
        pwcDownloadModuleDTO2.setProDownloadModuleId(pwcDownloadModuleDTO1.getProDownloadModuleId());
        assertThat(pwcDownloadModuleDTO1).isEqualTo(pwcDownloadModuleDTO2);
        pwcDownloadModuleDTO2.setProDownloadModuleId(2L);
        assertThat(pwcDownloadModuleDTO1).isNotEqualTo(pwcDownloadModuleDTO2);
        pwcDownloadModuleDTO1.setProDownloadModuleId(null);
        assertThat(pwcDownloadModuleDTO1).isNotEqualTo(pwcDownloadModuleDTO2);
    }
}
