package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdmContactDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdmContactDTO.class);
        AdmContactDTO admContactDTO1 = new AdmContactDTO();
        admContactDTO1.setUserId("id1");
        AdmContactDTO admContactDTO2 = new AdmContactDTO();
        assertThat(admContactDTO1).isNotEqualTo(admContactDTO2);
        admContactDTO2.setUserId(admContactDTO1.getUserId());
        assertThat(admContactDTO1).isEqualTo(admContactDTO2);
        admContactDTO2.setUserId("id2");
        assertThat(admContactDTO1).isNotEqualTo(admContactDTO2);
        admContactDTO1.setUserId(null);
        assertThat(admContactDTO1).isNotEqualTo(admContactDTO2);
    }
}
