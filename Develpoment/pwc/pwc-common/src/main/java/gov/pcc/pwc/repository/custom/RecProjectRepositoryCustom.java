package gov.pcc.pwc.repository.custom;

import gov.pcc.pwc.service.criteria.RecProjectCriteria;
import gov.pcc.pwc.service.criteria.RecProjectQueryCriteria;
import gov.pcc.pwc.service.dto.RecProjectChartDTO;
import gov.pcc.pwc.service.dto.RecProjectDTO;
import gov.pcc.pwc.service.dto.RecProjectQueryDTO;
import gov.pcc.pwc.service.dto.RecSelectOptionsDTO;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.dto.*;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RecProjectRepositoryCustom {

    Page<RecProjectQueryDTO> findAllByCriteria (RecProjectQueryCriteria criteria);

    Page<RecProject0303DTO> findAllByCriteria (RecProjectCriteria criteria);

    List<RecSelectOptionsDTO> findAllOrgByQuery ();

    List<RecSelectOptionsDTO> findAllTyphoonByQuery();

    List<RecSelectOptionsDTO> findAllProjectCode();

    void exportOdsByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException;

    void exportKmlByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException;

    Map<String,List<RecProjectChartDTO>> findTyphoonCountAndAmount(RecProjectQueryCriteria criteria);

    Map<String,List<RecProjectChartDTO>> findProjectCodeCountAndAmount(RecProjectQueryCriteria criteria);

    Map<String,List<RecProjectChartDTO>> findOrgCountAndAmount(RecProjectQueryCriteria criteria);

    Map<String,List<RecProjectChartDTO>> findOrgCountAndAmountByTyphoon(RecProjectQueryCriteria criteria);

    Map<String,List<RecProjectChartDTO>> findAllTyphoonCountAndAmount();

    Map<String,List<RecProjectChartDTO>> findAllTyphoonCountAndAmountByYear();

    RecProjectDTO packPkAndLogColumn (RecProjectDTO recProjectDTO) throws TypeVariableNotMatchException;

    Map<String,List<RecProjectMultiChartDTO>> findOrgCountAndAmountByYear(RecProjectQueryCriteria criteria);

    Map<String,List<RecProjectChartDTO>> findTyphoonCountAndAmountByIndigenous(RecProjectQueryCriteria criteria);

    Page<RecProjectQueryDTO> findAllExpiredByCriteria (RecProjectQueryCriteria criteria, boolean isFinish);

    void exportOdsByCriteriaAndExpired(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName, boolean isFinish) throws IOException;

    Page<RecProjectQueryDTO> findAllResearchByCriteria (RecProjectQueryCriteria criteria);

    void exportOdsByCriteriaAndResearch(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException;

    Page<RecProjectQueryDTO> findAllProjectByCriteria (RecProjectQueryCriteria criteria);

    void exportProjectKmlByCriteria(RecProjectQueryCriteria criteria, String downloadFilePath, String fileName) throws IOException;

    List<RecProjectOptionsDTO> findProjectTyphoon(String orgId);

    List<RecProjectOptionsDTO> findProjectCode(String typhoon, String orgId);

    List<RecProjectOptionsDTO> findProjectOrgRole(String typhoonNo, String orgId);

    List<RecProjectOptionsDTO> findProjectCity(String typhoonNo, String orgId);

    List<RecProjectOptionsDTO> findProjectStatus(String typhoonNo, String orgId);

    List<RecProjectOptionsDTO> findProjectConstItem();

    List<RecProjectOptionsDTO> findProjectConstModel(String constItemName);

}
