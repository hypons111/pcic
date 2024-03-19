package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbRoleApplyMapperTest {

    private PwbRoleApplyMapper pwbRoleApplyMapper;

    @BeforeEach
    public void setUp() {
        pwbRoleApplyMapper = new PwbRoleApplyMapperImpl();
    }
}
