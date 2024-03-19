package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoCompanyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoCompanyDTO.class);
        GeoCompanyDTO geoCompanyDTO1 = new GeoCompanyDTO();
        geoCompanyDTO1.setGeoCompanyId("id1");
        GeoCompanyDTO geoCompanyDTO2 = new GeoCompanyDTO();
        assertThat(geoCompanyDTO1).isNotEqualTo(geoCompanyDTO2);
        geoCompanyDTO2.setGeoCompanyId(geoCompanyDTO1.getGeoCompanyId());
        assertThat(geoCompanyDTO1).isEqualTo(geoCompanyDTO2);
        geoCompanyDTO2.setGeoCompanyId("id2");
        assertThat(geoCompanyDTO1).isNotEqualTo(geoCompanyDTO2);
        geoCompanyDTO1.setGeoCompanyId(null);
        assertThat(geoCompanyDTO1).isNotEqualTo(geoCompanyDTO2);
    }
}
