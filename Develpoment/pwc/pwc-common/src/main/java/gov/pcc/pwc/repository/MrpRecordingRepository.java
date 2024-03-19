package gov.pcc.pwc.repository;

import gov.pcc.pwc.domain.MrpRecording;
import gov.pcc.pwc.repository.custom.MrpRecordingRepositoryCustom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MrpRecording entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MrpRecordingRepository extends JpaRepository<MrpRecording, Long>, MrpRecordingRepositoryCustom, JpaSpecificationExecutor<MrpRecording> {}
