package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProOrgSuggestionMapperTest {

    private ProOrgSuggestionMapper proOrgSuggestionMapper;

    @BeforeEach
    public void setUp() {
        proOrgSuggestionMapper = new ProOrgSuggestionMapperImpl();
    }
}
