package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.EngCompContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface EngCompContactRepository extends JpaRepository<EngCompContact, String> {

    public EngCompContact getByEngCompId(String engCompId);
}
