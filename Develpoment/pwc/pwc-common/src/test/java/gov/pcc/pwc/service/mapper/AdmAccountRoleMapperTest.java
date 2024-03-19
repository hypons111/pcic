package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdmAccountRoleMapperTest {

    private AdmAccountRoleMapper admAccountRoleMapper;

    @BeforeEach
    public void setUp() {
        admAccountRoleMapper = new AdmAccountRoleMapperImpl();
    }
}
