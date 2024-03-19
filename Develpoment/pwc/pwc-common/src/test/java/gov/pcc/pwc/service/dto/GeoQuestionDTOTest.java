package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoQuestionDTO.class);
        GeoQuestionDTO geoQuestionDTO1 = new GeoQuestionDTO();
        geoQuestionDTO1.setId(1L);
        GeoQuestionDTO geoQuestionDTO2 = new GeoQuestionDTO();
        assertThat(geoQuestionDTO1).isNotEqualTo(geoQuestionDTO2);
        geoQuestionDTO2.setId(geoQuestionDTO1.getId());
        assertThat(geoQuestionDTO1).isEqualTo(geoQuestionDTO2);
        geoQuestionDTO2.setId(2L);
        assertThat(geoQuestionDTO1).isNotEqualTo(geoQuestionDTO2);
        geoQuestionDTO1.setId(null);
        assertThat(geoQuestionDTO1).isNotEqualTo(geoQuestionDTO2);
    }
}
