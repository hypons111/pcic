package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcDownloadMapperTest {

    private PwcDownloadMapper pwcDownloadMapper;

    @BeforeEach
    public void setUp() {
        pwcDownloadMapper = new PwcDownloadMapperImpl();
    }
}
