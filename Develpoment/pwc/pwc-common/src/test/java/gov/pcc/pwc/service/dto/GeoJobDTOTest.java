package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoJobDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoJobDTO.class);
        GeoJobDTO geoJobDTO1 = new GeoJobDTO();
        geoJobDTO1.setId(1L);
        GeoJobDTO geoJobDTO2 = new GeoJobDTO();
        assertThat(geoJobDTO1).isNotEqualTo(geoJobDTO2);
        geoJobDTO2.setId(geoJobDTO1.getId());
        assertThat(geoJobDTO1).isEqualTo(geoJobDTO2);
        geoJobDTO2.setId(2L);
        assertThat(geoJobDTO1).isNotEqualTo(geoJobDTO2);
        geoJobDTO1.setId(null);
        assertThat(geoJobDTO1).isNotEqualTo(geoJobDTO2);
    }
}
