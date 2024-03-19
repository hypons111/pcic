package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProEnginListMapperTest {

    private ProEnginListMapper proEnginListMapper;

    @BeforeEach
    public void setUp() {
        proEnginListMapper = new ProEnginListMapperImpl();
    }
}
