package gov.pcc.pwc.repository.impl;

import gov.pcc.pwc.repository.custom.SequencesRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.MessageFormat;

/**
 * 用sequence的名字去取sequence的號碼
 * 需要自己在DB寫sequence
 */
@Service
public class SequencesRepositoryImpl implements SequencesRepository {
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;


    public SequencesRepositoryImpl(EntityManager em, JdbcTemplate jdbcTemplate) {
        this.em = em;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getNextSequenceVal(String seq) {
        return method(seq);

    }
    @Transactional
    public void resetSequence(String sequenceName, Integer restartNum,Integer maxValue){
        String formatSql = MessageFormat.format("ALTER SEQUENCE {0} RESTART WITH {1} INCREMENT BY 1 MINVALUE 1 MAXVALUE {2} NO CYCLE NO CACHE",
                sequenceName,restartNum,maxValue.toString());
        System.out.println("sql:"+formatSql);
        Query result = em.createNativeQuery(formatSql);
        result.executeUpdate();
    }

    private Integer method(String seq){
        String sql = MessageFormat.format("SELECT NEXT VALUE FOR {0}",seq);
        System.out.println("sql"+sql);
        Query q = em.createNativeQuery(sql);
        Integer result = (Integer) q.getSingleResult();

        return result;
    }



}
