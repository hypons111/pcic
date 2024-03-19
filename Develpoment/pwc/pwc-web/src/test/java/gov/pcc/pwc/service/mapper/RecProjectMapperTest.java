package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecProjectMapperTest {

    private RecProjectMapper recProjectMapper;

    @BeforeEach
    public void setUp() {
        recProjectMapper = new RecProjectMapperImpl();
    }
}
