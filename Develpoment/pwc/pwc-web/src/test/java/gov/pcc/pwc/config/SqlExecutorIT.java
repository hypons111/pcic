package gov.pcc.pwc.config;

import static org.assertj.core.api.Assertions.assertThat;

import gov.pcc.pwc.IntegrationTest;
import gov.pcc.pwc.db.Query;
import gov.pcc.pwc.db.SqlExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Integration tests for the SqlExecutor configuration.
 */
@IntegrationTest
class SqlExecutorIT {

    @Autowired
    private SqlExecutor sqlExecutor;

    @Test
    void sqlExecutorShouldBeRegistered() throws Exception {
        Query query = Query.of("SELECT 1");

        assertThat(sqlExecutor.queryForList(query, Integer.class)).contains(1);
    }
}
