package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProRecommendListMapperTest {

    private ProRecommendListMapper proRecommendListMapper;

    @BeforeEach
    public void setUp() {
        proRecommendListMapper = new ProRecommendListMapperImpl();
    }
}
