package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationAppendixDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationAppendixDTO.class);
        PwcInformationAppendixDTO pwcInformationAppendixDTO1 = new PwcInformationAppendixDTO();
        pwcInformationAppendixDTO1.setProInformationAppendixId(1L);
        PwcInformationAppendixDTO pwcInformationAppendixDTO2 = new PwcInformationAppendixDTO();
        assertThat(pwcInformationAppendixDTO1).isNotEqualTo(pwcInformationAppendixDTO2);
        pwcInformationAppendixDTO2.setProInformationAppendixId(pwcInformationAppendixDTO1.getProInformationAppendixId());
        assertThat(pwcInformationAppendixDTO1).isEqualTo(pwcInformationAppendixDTO2);
        pwcInformationAppendixDTO2.setProInformationAppendixId(2L);
        assertThat(pwcInformationAppendixDTO1).isNotEqualTo(pwcInformationAppendixDTO2);
        pwcInformationAppendixDTO1.setProInformationAppendixId(null);
        assertThat(pwcInformationAppendixDTO1).isNotEqualTo(pwcInformationAppendixDTO2);
    }
}
