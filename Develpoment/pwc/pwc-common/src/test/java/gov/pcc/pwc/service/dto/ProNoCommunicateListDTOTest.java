package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProNoCommunicateListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNoCommunicateListDTO.class);
        ProNoCommunicateListDTO proNoCommunicateListDTO1 = new ProNoCommunicateListDTO();
        proNoCommunicateListDTO1.setProNoCommunicateListId(1L);
        ProNoCommunicateListDTO proNoCommunicateListDTO2 = new ProNoCommunicateListDTO();
        assertThat(proNoCommunicateListDTO1).isNotEqualTo(proNoCommunicateListDTO2);
        proNoCommunicateListDTO2.setProNoCommunicateListId(proNoCommunicateListDTO1.getProNoCommunicateListId());
        assertThat(proNoCommunicateListDTO1).isEqualTo(proNoCommunicateListDTO2);
        proNoCommunicateListDTO2.setProNoCommunicateListId(2L);
        assertThat(proNoCommunicateListDTO1).isNotEqualTo(proNoCommunicateListDTO2);
        proNoCommunicateListDTO1.setProNoCommunicateListId(null);
        assertThat(proNoCommunicateListDTO1).isNotEqualTo(proNoCommunicateListDTO2);
    }
}
