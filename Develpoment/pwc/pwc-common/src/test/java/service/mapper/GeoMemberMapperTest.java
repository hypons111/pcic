package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeoMemberMapperTest {

    private GeoMemberMapper geoMemberMapper;

    @BeforeEach
    public void setUp() {
        geoMemberMapper = new GeoMemberMapperImpl();
    }
}
