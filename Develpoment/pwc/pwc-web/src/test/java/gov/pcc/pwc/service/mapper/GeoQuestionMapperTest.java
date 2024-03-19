package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeoQuestionMapperTest {

    private GeoQuestionMapper geoQuestionMapper;

    @BeforeEach
    public void setUp() {
        geoQuestionMapper = new GeoQuestionMapperImpl();
    }
}
