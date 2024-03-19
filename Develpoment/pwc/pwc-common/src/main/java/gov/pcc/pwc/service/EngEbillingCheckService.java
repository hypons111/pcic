package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.EngEbillingCheck;
import gov.pcc.pwc.repository.EngEbillingCheckRepository;
import gov.pcc.pwc.service.dto.EngEbillingCheckDTO;
import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.mapper.EngEbillingCheckMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EngEbillingCheck}.
 */
@Service
@Transactional
public class EngEbillingCheckService {

    private final Logger log = LoggerFactory.getLogger(EngEbillingCheckService.class);

    private final EngEbillingCheckRepository engEbillingCheckRepository;

    private final EngEbillingCheckMapper engEbillingCheckMapper;

    private final SerialNumGenerator serialNumGenerator;

    public EngEbillingCheckService(EngEbillingCheckRepository engEbillingCheckRepository, EngEbillingCheckMapper engEbillingCheckMapper, SerialNumGenerator serialNumGenerator) {
        this.engEbillingCheckRepository = engEbillingCheckRepository;
        this.engEbillingCheckMapper = engEbillingCheckMapper;
        this.serialNumGenerator = serialNumGenerator;
    }

    /**
     * Save a engEbillingCheck.
     *
     * @param engEbillingCheckDTO the entity to save.
     * @return the persisted entity.
     */
    public EngEbillingCheckDTO save(EngEbillingCheckDTO engEbillingCheckDTO) throws TypeVariableNotMatchException {
        log.debug("Request to save EngEbillingCheck : {}", engEbillingCheckDTO);

        String engEbillingCheckId = serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(EngGenNumberVariableEnum.EBILLING_CHECK.getVariable()))
                .getGeneratedNumber();
        engEbillingCheckDTO.setEngEbillingCheckId(engEbillingCheckId);
        engEbillingCheckDTO.setIsClose("0");
        engEbillingCheckDTO.setCode1("98685");
        engEbillingCheckDTO.setCode2(engEbillingCheckDTO.getDocType() + "9" + engEbillingCheckId);

        String code3;
        String code1_2 = engEbillingCheckDTO.getCode1() + engEbillingCheckDTO.getCode2();
        if(code1_2.length() == 15 && (code1_2.charAt(5) == '1' || code1_2.charAt(5) == '2' || code1_2.charAt(5) == '3')) {
            int sum = 0;
            for(int i = 0; i < code1_2.length(); i++) {
                switch (i % 3) {
                    case 0:
                        sum += Character.getNumericValue(code1_2.charAt(i)) * 3;
                        break;
                    case 1:
                        sum += Character.getNumericValue(code1_2.charAt(i)) * 7;
                        break;
                    case 2:
                        sum += Character.getNumericValue(code1_2.charAt(i));
                }
            }
            code3 = String.valueOf( ( 10 - (sum % 10) ) % 10 );
        }else {
            code3 = "E";
        }
        engEbillingCheckDTO.setCode3(code3);
        EngEbillingCheck engEbillingCheck = engEbillingCheckMapper.toEntity(engEbillingCheckDTO);
        engEbillingCheck = engEbillingCheckRepository.save(engEbillingCheck);
        return engEbillingCheckMapper.toDto(engEbillingCheck);
    }

    /**
     * Partially update a engEbillingCheck.
     *
     * @param engEbillingCheckDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EngEbillingCheckDTO> partialUpdate(EngEbillingCheckDTO engEbillingCheckDTO) {
        log.debug("Request to partially update EngEbillingCheck : {}", engEbillingCheckDTO);

        return engEbillingCheckRepository
            .findById(engEbillingCheckDTO.getEngEbillingCheckId())
            .map(existingEngEbillingCheck -> {
                engEbillingCheckMapper.partialUpdate(existingEngEbillingCheck, engEbillingCheckDTO);

                return existingEngEbillingCheck;
            })
            .map(engEbillingCheckRepository::save)
            .map(engEbillingCheckMapper::toDto);
    }

    /**
     * Get all the engEbillingChecks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EngEbillingCheckDTO> findAll() {
        log.debug("Request to get all EngEbillingChecks");
        return engEbillingCheckRepository
            .findAll()
            .stream()
            .map(engEbillingCheckMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one engEbillingCheck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EngEbillingCheckDTO> findOne(String id) {
        log.debug("Request to get EngEbillingCheck : {}", id);
        return engEbillingCheckRepository.findById(id).map(engEbillingCheckMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<EngEbillingCheckDTO> findByDocTypeAndDocId(String docType, String docId) {
        log.debug("Request to get EngEbillingCheck, docType : {}, docId : {}", docType, docId);
        return engEbillingCheckRepository.findByDocTypeAndDocId(docType, docId).map(engEbillingCheckMapper::toDto);
    }

    /**
     * Delete the engEbillingCheck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EngEbillingCheck : {}", id);
        engEbillingCheckRepository.deleteById(id);
    }
}
