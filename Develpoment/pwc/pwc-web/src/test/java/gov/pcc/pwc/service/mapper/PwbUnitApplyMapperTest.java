package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbUnitApplyMapperTest {

    private PwbUnitApplyMapper pwbUnitApplyMapper;

    @BeforeEach
    public void setUp() {
        pwbUnitApplyMapper = new PwbUnitApplyMapperImpl();
    }
}
