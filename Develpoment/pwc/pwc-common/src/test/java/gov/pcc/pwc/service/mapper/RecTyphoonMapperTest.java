package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecTyphoonMapperTest {

    private RecTyphoonMapper recTyphoonMapper;

    @BeforeEach
    public void setUp() {
        recTyphoonMapper = new RecTyphoonMapperImpl();
    }
}
