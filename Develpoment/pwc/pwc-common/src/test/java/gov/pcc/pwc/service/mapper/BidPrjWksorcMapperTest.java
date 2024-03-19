package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidPrjWksorcMapperTest {

    private BidPrjWksorcMapper bidPrjWksorcMapper;

    @BeforeEach
    public void setUp() {
        bidPrjWksorcMapper = new BidPrjWksorcMapperImpl();
    }
}
