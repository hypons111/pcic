package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoCompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoCompany.class);
        GeoCompany geoCompany1 = new GeoCompany();
        geoCompany1.setGeoCompanyId("id1");
        GeoCompany geoCompany2 = new GeoCompany();
        geoCompany2.setGeoCompanyId(geoCompany1.getGeoCompanyId());
        assertThat(geoCompany1).isEqualTo(geoCompany2);
        geoCompany2.setGeoCompanyId("id2");
        assertThat(geoCompany1).isNotEqualTo(geoCompany2);
        geoCompany1.setGeoCompanyId(null);
        assertThat(geoCompany1).isNotEqualTo(geoCompany2);
    }
}
