package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TecContentMapperTest {

    private TecContentMapper tecContentMapper;

    @BeforeEach
    public void setUp() {
        tecContentMapper = new TecContentMapperImpl();
    }
}
