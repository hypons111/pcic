package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.domain.AdmMailSend;

import java.util.List;

public interface AdmMailSendRepositoryCustom {
    List<AdmMailSend> findAllByCriteria(AdmMailSend criteria);
}
