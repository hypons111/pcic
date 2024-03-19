package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProRecommendListDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRecommendListDTO.class);
        ProRecommendListDTO proRecommendListDTO1 = new ProRecommendListDTO();
        proRecommendListDTO1.setProRecommendListId(1L);
        ProRecommendListDTO proRecommendListDTO2 = new ProRecommendListDTO();
        assertThat(proRecommendListDTO1).isNotEqualTo(proRecommendListDTO2);
        proRecommendListDTO2.setProRecommendListId(proRecommendListDTO1.getProRecommendListId());
        assertThat(proRecommendListDTO1).isEqualTo(proRecommendListDTO2);
        proRecommendListDTO2.setProRecommendListId(2L);
        assertThat(proRecommendListDTO1).isNotEqualTo(proRecommendListDTO2);
        proRecommendListDTO1.setProRecommendListId(null);
        assertThat(proRecommendListDTO1).isNotEqualTo(proRecommendListDTO2);
    }
}
