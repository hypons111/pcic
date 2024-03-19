package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwbRoleApproveMapperTest {

    private PwbRoleApproveMapper pwbRoleApproveMapper;

    @BeforeEach
    public void setUp() {
        pwbRoleApproveMapper = new PwbRoleApproveMapperImpl();
    }
}
