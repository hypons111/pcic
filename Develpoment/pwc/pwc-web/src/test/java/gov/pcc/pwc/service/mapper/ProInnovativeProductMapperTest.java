package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProInnovativeProductMapperTest {

    private ProInnovativeProductMapper proInnovativeProductMapper;

    @BeforeEach
    public void setUp() {
        proInnovativeProductMapper = new ProInnovativeProductMapperImpl();
    }
}
