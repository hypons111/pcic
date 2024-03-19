package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmPadHisDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmPadHisDTO.class);
        AdmPadHisDTO admPadHisDTO1 = new AdmPadHisDTO();
        admPadHisDTO1.setId(1L);
        AdmPadHisDTO admPadHisDTO2 = new AdmPadHisDTO();
        assertThat(admPadHisDTO1).isNotEqualTo(admPadHisDTO2);
        admPadHisDTO2.setId(admPadHisDTO1.getId());
        assertThat(admPadHisDTO1).isEqualTo(admPadHisDTO2);
        admPadHisDTO2.setId(2L);
        assertThat(admPadHisDTO1).isNotEqualTo(admPadHisDTO2);
        admPadHisDTO1.setId(null);
        assertThat(admPadHisDTO1).isNotEqualTo(admPadHisDTO2);
    }
}
