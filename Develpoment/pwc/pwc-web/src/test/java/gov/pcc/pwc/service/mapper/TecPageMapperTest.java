package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TecPageMapperTest {

    private TecPageMapper tecPageMapper;

    @BeforeEach
    public void setUp() {
        tecPageMapper = new TecPageMapperImpl();
    }
}
