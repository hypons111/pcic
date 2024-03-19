package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbUsualfunctionMapperTest {

    private PwbUsualfunctionMapper pwbUsualfunctionMapper;

    @BeforeEach
    public void setUp() {
        pwbUsualfunctionMapper = new PwbUsualfunctionMapperImpl();
    }
}
