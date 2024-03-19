package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecUserProjectCodeMapperTest {

    private RecUserProjectCodeMapper recUserProjectCodeMapper;

    @BeforeEach
    public void setUp() {
        recUserProjectCodeMapper = new RecUserProjectCodeMapperImpl();
    }
}
