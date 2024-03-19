package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CstSubjectMapperTest {

    private CstSubjectMapper cstSubjectMapper;

    @BeforeEach
    public void setUp() {
        cstSubjectMapper = new CstSubjectMapperImpl();
    }
}
