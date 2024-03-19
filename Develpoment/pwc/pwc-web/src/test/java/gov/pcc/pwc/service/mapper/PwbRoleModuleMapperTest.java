package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbRoleModuleMapperTest {

    private PwbRoleModuleMapper pwbRoleModuleMapper;

    @BeforeEach
    public void setUp() {
        pwbRoleModuleMapper = new PwbRoleModuleMapperImpl();
    }
}
