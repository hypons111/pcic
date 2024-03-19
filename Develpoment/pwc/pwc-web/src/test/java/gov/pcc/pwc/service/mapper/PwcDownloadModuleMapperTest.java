package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcDownloadModuleMapperTest {

    private PwcDownloadModuleMapper pwcDownloadModuleMapper;

    @BeforeEach
    public void setUp() {
        pwcDownloadModuleMapper = new PwcDownloadModuleMapperImpl();
    }
}
