package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdmContactMapperTest {

    private AdmContactMapper admContactMapper;

    @BeforeEach
    public void setUp() {
        admContactMapper = new AdmContactMapperImpl();
    }
}
