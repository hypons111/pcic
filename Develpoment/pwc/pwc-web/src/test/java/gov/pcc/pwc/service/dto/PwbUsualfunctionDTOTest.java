package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PwbUsualfunctionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PwbUsualfunctionDTO.class);
        PwbUsualfunctionDTO pwbUsualfunctionDTO1 = new PwbUsualfunctionDTO();
        pwbUsualfunctionDTO1.setId(1L);
        PwbUsualfunctionDTO pwbUsualfunctionDTO2 = new PwbUsualfunctionDTO();
        assertThat(pwbUsualfunctionDTO1).isNotEqualTo(pwbUsualfunctionDTO2);
        pwbUsualfunctionDTO2.setId(pwbUsualfunctionDTO1.getId());
        assertThat(pwbUsualfunctionDTO1).isEqualTo(pwbUsualfunctionDTO2);
        pwbUsualfunctionDTO2.setId(2L);
        assertThat(pwbUsualfunctionDTO1).isNotEqualTo(pwbUsualfunctionDTO2);
        pwbUsualfunctionDTO1.setId(null);
        assertThat(pwbUsualfunctionDTO1).isNotEqualTo(pwbUsualfunctionDTO2);
    }
}
