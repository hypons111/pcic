package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PwcDownloadAppendixMapperTest {

    private PwcDownloadAppendixMapper pwcDownloadAppendixMapper;

    @BeforeEach
    public void setUp() {
        pwcDownloadAppendixMapper = new PwcDownloadAppendixMapperImpl();
    }
}
