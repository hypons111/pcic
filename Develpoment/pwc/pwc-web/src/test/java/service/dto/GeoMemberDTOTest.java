package gov.pcc.pwc.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GeoMemberDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoMemberDTO.class);
        GeoMemberDTO geoMemberDTO1 = new GeoMemberDTO();
        geoMemberDTO1.setGeoMemberId(String.valueOf(1L));
        GeoMemberDTO geoMemberDTO2 = new GeoMemberDTO();
        assertThat(geoMemberDTO1).isNotEqualTo(geoMemberDTO2);
        geoMemberDTO2.setGeoMemberId(geoMemberDTO1.getGeoMemberId());
        assertThat(geoMemberDTO1).isEqualTo(geoMemberDTO2);
        geoMemberDTO2.setGeoMemberId(String.valueOf(2L));
        assertThat(geoMemberDTO1).isNotEqualTo(geoMemberDTO2);
        geoMemberDTO1.setGeoMemberId(null);
        assertThat(geoMemberDTO1).isNotEqualTo(geoMemberDTO2);
    }
}
