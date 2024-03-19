package gov.pcc.pwc.service;

import gov.pcc.pwc.common.web.io.DownloadableResource;
import gov.pcc.pwc.domain.PwcDownloadAppendix;
import gov.pcc.pwc.repository.PwcDownloadAppendixRepository;
import gov.pcc.pwc.service.dto.PwcDownloadAppendixDTO;
import gov.pcc.pwc.service.dto.PwcDownloadDTO;
import gov.pcc.pwc.service.mapper.PwcDownloadAppendixMapper;
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
 * Service Implementation for managing {@link PwcDownloadAppendix}.
 */
@Service
@Transactional
public class PwcDownloadAppendixService {

    private final Logger log = LoggerFactory.getLogger(PwcDownloadAppendixService.class);

    private final PwcDownloadAppendixRepository pwcDownloadAppendixRepository;

    private final PwcDownloadAppendixMapper pwcDownloadAppendixMapper;

    public PwcDownloadAppendixService(
        PwcDownloadAppendixRepository pwcDownloadAppendixRepository,
        PwcDownloadAppendixMapper pwcDownloadAppendixMapper
    ) {
        this.pwcDownloadAppendixRepository = pwcDownloadAppendixRepository;
        this.pwcDownloadAppendixMapper = pwcDownloadAppendixMapper;
    }

    /**
     * Save a pwcDownloadAppendix.
     *
     * @param pwcDownloadAppendixDTO the entity to save.
     * @return the persisted entity.
     */
    public PwcDownloadAppendixDTO save(PwcDownloadAppendixDTO pwcDownloadAppendixDTO) {
        log.debug("Request to save PwcDownloadAppendix : {}", pwcDownloadAppendixDTO);
        PwcDownloadAppendix pwcDownloadAppendix = pwcDownloadAppendixMapper.toEntity(pwcDownloadAppendixDTO);
        pwcDownloadAppendix = pwcDownloadAppendixRepository.save(pwcDownloadAppendix);
        return pwcDownloadAppendixMapper.toDto(pwcDownloadAppendix);
    }

    /**
     * Partially update a pwcDownloadAppendix.
     *
     * @param pwcDownloadAppendixDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PwcDownloadAppendixDTO> partialUpdate(PwcDownloadAppendixDTO pwcDownloadAppendixDTO) {
        log.debug("Request to partially update PwcDownloadAppendix : {}", pwcDownloadAppendixDTO);

        return pwcDownloadAppendixRepository
            .findById(pwcDownloadAppendixDTO.getProDownloadAppendixId())
            .map(
                existingPwcDownloadAppendix -> {
                    pwcDownloadAppendixMapper.partialUpdate(existingPwcDownloadAppendix, pwcDownloadAppendixDTO);
                    return existingPwcDownloadAppendix;
                }
            )
            .map(pwcDownloadAppendixRepository::save)
            .map(pwcDownloadAppendixMapper::toDto);
    }

    /**
     * Get all the pwcDownloadAppendices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PwcDownloadAppendixDTO> findAll() {
        log.debug("Request to get all PwcDownloadAppendices");
        return pwcDownloadAppendixRepository
            .findAll()
            .stream()
            .map(pwcDownloadAppendixMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one pwcDownloadAppendix by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PwcDownloadAppendixDTO> findOne(Long id) {
        log.debug("Request to get PwcDownloadAppendix : {}", id);
        return pwcDownloadAppendixRepository.findById(id).map(pwcDownloadAppendixMapper::toDto);
    }

    /**
     * Delete the pwcDownloadAppendix by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PwcDownloadAppendix : {}", id);
        pwcDownloadAppendixRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<PwcDownloadAppendixDTO> findLinkByCriteria(PwcDownloadDTO criteria) {
        return pwcDownloadAppendixRepository.findByLink(criteria);
    }

    @Transactional(readOnly = true)
    public List<PwcDownloadAppendixDTO> findAttachmentByCriteria(PwcDownloadDTO criteria) {
        return pwcDownloadAppendixRepository.findByAttachment(criteria);
    }

    @Transactional(readOnly = true)
    public DownloadableResource downFileByDown(String fileCode) throws IOException {
        // 查询附件是否存在
//        list<pwcinformationappendixdto> sysattachment = pwcinformationappendixrepository.findbyattachment(criteria);
//        if (sysattachment.size() < 0) {
//            return null;
//        }
        String fileName = "Template_Upload.csv";
        String filePath = "C:\\Users\\Y220102\\Desktop\\downloadFile\\";
        File file = new File(filePath + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        final ByteArrayResource fileResource = new ByteArrayResource(Files.readAllBytes(path));
        return new DownloadableResource(fileResource, fileName);
    }

    @Transactional(readOnly = true)
    public List<PwcDownloadAppendixDTO> findByInfoId(String Id) {
        return pwcDownloadAppendixRepository.findByInfoId(Id);
    }
}
