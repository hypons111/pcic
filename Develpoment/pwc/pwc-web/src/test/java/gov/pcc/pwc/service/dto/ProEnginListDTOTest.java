package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProEnginListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProEnginListDTO.class);
        ProEnginListDTO proEnginListDTO1 = new ProEnginListDTO();
        proEnginListDTO1.setProEnginListId(1L);
        ProEnginListDTO proEnginListDTO2 = new ProEnginListDTO();
        assertThat(proEnginListDTO1).isNotEqualTo(proEnginListDTO2);
        proEnginListDTO2.setProEnginListId(proEnginListDTO1.getProEnginListId());
        assertThat(proEnginListDTO1).isEqualTo(proEnginListDTO2);
        proEnginListDTO2.setProEnginListId(2L);
        assertThat(proEnginListDTO1).isNotEqualTo(proEnginListDTO2);
        proEnginListDTO1.setProEnginListId(null);
        assertThat(proEnginListDTO1).isNotEqualTo(proEnginListDTO2);
    }
}
