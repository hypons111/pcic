package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeoJobMapperTest {

    private GeoJobMapper geoJobMapper;

    @BeforeEach
    public void setUp() {
        geoJobMapper = new GeoJobMapperImpl();
    }
}
