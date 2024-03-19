package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationDTO.class);
        PwcInformationDTO pwcInformationDTO1 = new PwcInformationDTO();
        pwcInformationDTO1.setId(1L);
        PwcInformationDTO pwcInformationDTO2 = new PwcInformationDTO();
        assertThat(pwcInformationDTO1).isNotEqualTo(pwcInformationDTO2);
        pwcInformationDTO2.setId(pwcInformationDTO1.getId());
        assertThat(pwcInformationDTO1).isEqualTo(pwcInformationDTO2);
        pwcInformationDTO2.setId(2L);
        assertThat(pwcInformationDTO1).isNotEqualTo(pwcInformationDTO2);
        pwcInformationDTO1.setId(null);
        assertThat(pwcInformationDTO1).isNotEqualTo(pwcInformationDTO2);
    }
}
