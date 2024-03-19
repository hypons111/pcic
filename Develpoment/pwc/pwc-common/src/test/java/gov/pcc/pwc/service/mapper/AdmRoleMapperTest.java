package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdmRoleMapperTest {

    private AdmRoleMapper admRoleMapper;

    @BeforeEach
    public void setUp() {
        admRoleMapper = new AdmRoleMapperImpl();
    }
}
