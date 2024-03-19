package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CmsCompMapperTest {

    private CmsCompMapper cmsCompMapper;

    @BeforeEach
    public void setUp() {
        cmsCompMapper = new CmsCompMapperImpl();
    }
}
