package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoJobTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoJob.class);
        GeoJob geoJob1 = new GeoJob();
        geoJob1.setId(1L);
        GeoJob geoJob2 = new GeoJob();
        geoJob2.setId(geoJob1.getId());
        assertThat(geoJob1).isEqualTo(geoJob2);
        geoJob2.setId(2L);
        assertThat(geoJob1).isNotEqualTo(geoJob2);
        geoJob1.setId(null);
        assertThat(geoJob1).isNotEqualTo(geoJob2);
    }
}
