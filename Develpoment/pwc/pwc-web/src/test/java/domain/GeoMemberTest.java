package gov.pcc.pwc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoMemberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoMember.class);
        GeoMember geoMember1 = new GeoMember();
        geoMember1.setGeoMemberId(String.valueOf(1L));
        GeoMember geoMember2 = new GeoMember();
        geoMember2.setGeoMemberId(geoMember1.getGeoMemberId());
        assertThat(geoMember1).isEqualTo(geoMember2);
        geoMember2.setGeoMemberId(String.valueOf(2L));
        assertThat(geoMember1).isNotEqualTo(geoMember2);
        geoMember1.setGeoMemberId(null);
        assertThat(geoMember1).isNotEqualTo(geoMember2);
    }
}
