package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcInformationRoleMapperTest {

    private PwcInformationRoleMapper pwcInformationRoleMapper;

    @BeforeEach
    public void setUp() {
        pwcInformationRoleMapper = new PwcInformationRoleMapperImpl();
    }
}
