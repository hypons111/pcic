package gov.pcc.pwc.service;

import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.domain.PwcInformationAppendix;
import gov.pcc.pwc.repository.PwcInformationAppendixRepository;
import gov.pcc.pwc.service.dto.PwcInformationAppendixDTO;
import gov.pcc.pwc.service.dto.PwcInformationDTO;
import gov.pcc.pwc.service.mapper.PwcInformationAppendixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PwcInformationAppendix}.
 */
@Service
@Transactional
public class PwcInformationAppendixService {

    private final Logger log = LoggerFactory.getLogger(PwcInformationAppendixService.class);

    private final PwcInformationAppendixRepository pwcInformationAppendixRepository;

    private final PwcInformationAppendixMapper pwcInformationAppendixMapper;

    public PwcInformationAppendixService(
            PwcInformationAppendixRepository pwcInformationAppendixRepository,
            PwcInformationAppendixMapper pwcInformationAppendixMapper
    ) {
        this.pwcInformationAppendixRepository = pwcInformationAppendixRepository;
        this.pwcInformationAppendixMapper = pwcInformationAppendixMapper;
    }

    /**
     * Save a pwcInformationAppendix.
     *
     * @param pwcInformationAppendixDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcInformationAppendixDTO save(PwcInformationAppendixDTO pwcInformationAppendixDTO) {
        log.debug("Request to save PwcInformationAppendix : {}", pwcInformationAppendixDTO);
        PwcInformationAppendix pwcInformationAppendix = pwcInformationAppendixMapper.toEntity(pwcInformationAppendixDTO);
        pwcInformationAppendix = pwcInformationAppendixRepository.save(pwcInformationAppendix);
        return pwcInformationAppendixMapper.toDto(pwcInformationAppendix);
    }

    /**
     * Partially update a pwcInformationAppendix.
     *
     * @param pwcInformationAppendixDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcInformationAppendixDTO> partialUpdate(PwcInformationAppendixDTO pwcInformationAppendixDTO) {
        log.debug("Request to partially update PwcInformationAppendix : {}", pwcInformationAppendixDTO);

        return pwcInformationAppendixRepository
                .findById(pwcInformationAppendixDTO.getProInformationAppendixId())
                .map(
                        existingPwcInformationAppendix -> {
                            pwcInformationAppendixMapper.partialUpdate(existingPwcInformationAppendix, pwcInformationAppendixDTO);
                            return existingPwcInformationAppendix;
                        }
                )
                .map(pwcInformationAppendixRepository::save)
                .map(pwcInformationAppendixMapper::toDto);
    }

    /**
     * Get all the pwcInformationAppendices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcInformationAppendixDTO> findAll() {
        log.debug("Request to get all PwcInformationAppendices");
        return pwcInformationAppendixRepository
                .findAll()
                .stream()
                .map(pwcInformationAppendixMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcInformationAppendix by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcInformationAppendixDTO> findOne(Long id) {
        log.debug("Request to get PwcInformationAppendix : {}", id);
        return pwcInformationAppendixRepository.findById(id).map(pwcInformationAppendixMapper::toDto);
    }

    /**
     * Delete the pwcInformationAppendix by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcInformationAppendix : {}", id);
        pwcInformationAppendixRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PwcInformationAppendixDTO> findLinkByCriteria(PwcInformationDTO criteria) {
        return pwcInformationAppendixRepository.findByLink(criteria);
    }

    @Transactional(readOnly = true)
    public List<PwcInformationAppendixDTO> findAttachmentByCriteria(PwcInformationDTO criteria) {
        return pwcInformationAppendixRepository.findByAttachment(criteria);
    }

    @Transactional(readOnly = true)
    public DownloadableResource downFileByInfo(String fileCode) throws IOException {
        // 查询附件是否存在
//        list<pwcinformationappendixdto> sysattachment = pwcinformationappendixrepository.findbyattachment(criteria);
//        if (sysattachment.size() < 0) {
//            return null;
//        }
        String fileName = "Template_Upload.csv"; //取值模式確認參數後改為 fileCode
        String filePath = "C:\\Users\\Y220102\\Desktop\\downloadFile\\";
        File file = new File(filePath + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        final ByteArrayResource fileResource = new ByteArrayResource(Files.readAllBytes(path));
        return new DownloadableResource(fileResource, fileName);
    }

    @Transactional(readOnly = true)
    public List<PwcInformationAppendixDTO> findByInfoId(String Id) {
        return pwcInformationAppendixRepository.findByInfoId(Id);
    }
}
