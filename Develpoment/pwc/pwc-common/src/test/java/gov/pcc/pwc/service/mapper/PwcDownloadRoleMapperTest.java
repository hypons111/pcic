package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcDownloadRoleMapperTest {

    private PwcDownloadRoleMapper pwcDownloadRoleMapper;

    @BeforeEach
    public void setUp() {
        pwcDownloadRoleMapper = new PwcDownloadRoleMapperImpl();
    }
}
