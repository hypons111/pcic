package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdmPadHisMapperTest {

    private AdmPadHisMapper admPadHisMapper;

    @BeforeEach
    public void setUp() {
        admPadHisMapper = new AdmPadHisMapperImpl();
    }
}
