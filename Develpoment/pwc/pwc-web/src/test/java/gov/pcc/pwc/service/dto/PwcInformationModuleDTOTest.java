package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwcInformationModuleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwcInformationModuleDTO.class);
        PwcInformationModuleDTO pwcInformationModuleDTO1 = new PwcInformationModuleDTO();
        pwcInformationModuleDTO1.setProInformationModuleId(1L);
        PwcInformationModuleDTO pwcInformationModuleDTO2 = new PwcInformationModuleDTO();
        assertThat(pwcInformationModuleDTO1).isNotEqualTo(pwcInformationModuleDTO2);
        pwcInformationModuleDTO2.setProInformationModuleId(pwcInformationModuleDTO1.getProInformationModuleId());
        assertThat(pwcInformationModuleDTO1).isEqualTo(pwcInformationModuleDTO2);
        pwcInformationModuleDTO2.setProInformationModuleId(2L);
        assertThat(pwcInformationModuleDTO1).isNotEqualTo(pwcInformationModuleDTO2);
        pwcInformationModuleDTO1.setProInformationModuleId(null);
        assertThat(pwcInformationModuleDTO1).isNotEqualTo(pwcInformationModuleDTO2);
    }
}
