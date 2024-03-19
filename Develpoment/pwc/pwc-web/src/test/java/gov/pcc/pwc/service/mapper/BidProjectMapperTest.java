package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidProjectMapperTest {

    private BidProjectMapper bidProjectMapper;

    @BeforeEach
    public void setUp() {
        bidProjectMapper = new BidProjectMapperImpl();
    }
}
