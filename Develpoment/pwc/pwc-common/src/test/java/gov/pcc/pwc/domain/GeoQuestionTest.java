package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoQuestion.class);
        GeoQuestion geoQuestion1 = new GeoQuestion();
        geoQuestion1.setId(1L);
        GeoQuestion geoQuestion2 = new GeoQuestion();
        geoQuestion2.setId(geoQuestion1.getId());
        assertThat(geoQuestion1).isEqualTo(geoQuestion2);
        geoQuestion2.setId(2L);
        assertThat(geoQuestion1).isNotEqualTo(geoQuestion2);
        geoQuestion1.setId(null);
        assertThat(geoQuestion1).isNotEqualTo(geoQuestion2);
    }
}
