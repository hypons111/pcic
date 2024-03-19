package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbUnitRoleMapperTest {

    private PwbUnitRoleMapper pwbUnitRoleMapper;

    @BeforeEach
    public void setUp() {
        pwbUnitRoleMapper = new PwbUnitRoleMapperImpl();
    }
}
