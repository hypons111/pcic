package gov.pcc.pwc.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BidProjectUnitMapperTest {

    private BidProjectUnitMapper bidProjectUnitMapper;

    @BeforeEach
    public void setUp() {
        bidProjectUnitMapper = new BidProjectUnitMapperImpl();
    }
}
