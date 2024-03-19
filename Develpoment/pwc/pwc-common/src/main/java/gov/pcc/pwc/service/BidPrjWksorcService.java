package gov.pcc.pwc.service;

import gov.pcc.pwc.domain.AdmMailSend;
import gov.pcc.pwc.domain.BidPrjWksorc;
import gov.pcc.pwc.domain.BidPrjWksorcPrimaryKey;
import gov.pcc.pwc.repository.AdmMailSendRepository;
import gov.pcc.pwc.repository.BidPrjWksorcRepository;
import gov.pcc.pwc.service.criteria.BidPrjEnviCriteria;
import gov.pcc.pwc.service.criteria.BidPrjWksorcCriteria;
import gov.pcc.pwc.service.dto.AdmAccountContactDTO;
import gov.pcc.pwc.service.dto.BidCcutDTO;
import gov.pcc.pwc.service.dto.BidPrjWksorcDTO;
import gov.pcc.pwc.service.dto.BidUnit10DTO;
import gov.pcc.pwc.service.mapper.BidPrjWksorcMapper;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BidPrjWksorc}.
 */
@Service
@Transactional
public class BidPrjWksorcService {

    private final Logger log = LoggerFactory.getLogger(BidPrjWksorcService.class);

    private final BidPrjWksorcRepository bidPrjWksorcRepository;

    private final BidPrjWksorcMapper bidPrjWksorcMapper;
    private final AdmAccountService admAccountService;
    private final AdmMailSendRepository admMailSendRepository;

    public BidPrjWksorcService(BidPrjWksorcRepository bidPrjWksorcRepository, BidPrjWksorcMapper bidPrjWksorcMapper, AdmAccountService admAccountService, AdmMailSendRepository admMailSendRepository) {
        this.bidPrjWksorcRepository = bidPrjWksorcRepository;
        this.bidPrjWksorcMapper = bidPrjWksorcMapper;
        this.admAccountService = admAccountService;
        this.admMailSendRepository = admMailSendRepository;
    }

    private String admMailSendContent = "" ;

    /**
     * Save a bidPrjWksorc.
     *
     * @param bidPrjWksorcDTO the entity to save.
     * @return the persisted entity.
     */
    public BidPrjWksorcDTO save(BidPrjWksorcDTO bidPrjWksorcDTO) {
        log.debug("Request to save BidPrjWksorc : {}", bidPrjWksorcDTO);
        BidPrjWksorc bidPrjWksorc = bidPrjWksorcMapper.toEntity(bidPrjWksorcDTO);
        bidPrjWksorc = bidPrjWksorcRepository.save(bidPrjWksorc);
        return bidPrjWksorcMapper.toDto(bidPrjWksorc);
    }

    /**
     * Partially update a bidPrjWksorc.
     *
     * @param bidPrjWksorcDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BidPrjWksorcDTO> partialUpdate(BidPrjWksorcDTO bidPrjWksorcDTO) {
        log.debug("Request to partially update BidPrjWksorc : {}", bidPrjWksorcDTO);

        return bidPrjWksorcRepository
            .findById(bidPrjWksorcDTO.getId())
            .map(existingBidPrjWksorc -> {
                bidPrjWksorcMapper.partialUpdate(existingBidPrjWksorc, bidPrjWksorcDTO);

                return existingBidPrjWksorc;
            })
            .map(bidPrjWksorcRepository::save)
            .map(bidPrjWksorcMapper::toDto);
    }

    /**
     * Get all the bidPrjWksorcs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BidPrjWksorcDTO> findAll() {
        log.debug("Request to get all BidPrjWksorcs");
        return bidPrjWksorcRepository.findAll().stream().map(bidPrjWksorcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one bidPrjWksorc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BidPrjWksorcDTO> findOne(BidPrjWksorcPrimaryKey id) {
        log.debug("Request to get BidPrjWksorc : {}", id);
        return bidPrjWksorcRepository.findById(id).map(bidPrjWksorcMapper::toDto);
    }

    /**
     * Delete the bidPrjWksorc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(BidPrjWksorcPrimaryKey id) {
        log.debug("Request to delete BidPrjWksorc : {}", id);
        bidPrjWksorcRepository.deleteById(id);
    }




    /**
     * Find the list of BidPrjSegmsetDTO by criteria.
     *
     * @param criteria the criteria.
     * @return the list of BidPrjSegmsetDTO.
     */
    @Transactional(readOnly = true)
    public Page<BidPrjWksorcDTO> findByCriteria(BidPrjWksorcCriteria criteria) {
        if (criteria.getSortBy() != null && criteria.getSortBy().length == 0) {
            criteria.setSortBy(new String[] { "syr" });
        }
        final Page<BidPrjWksorc> bidPrjWksorcs = bidPrjWksorcRepository.findAll(criteria, criteria.toPageable());
        return bidPrjWksorcs.map(bidPrjWksorcMapper::toDto);
    }


    public List<BidPrjWksorcDTO> findByWkutAndPrjno(String wkut, String prjno){
        return bidPrjWksorcRepository.findByWkutAndPrjno(wkut, prjno).stream().map(bidPrjWksorcMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

//    @Transactional(readOnly = true)
//    public Optional<BidPrjWksorcDTO> findByWkutAndPrjno(String wkut, String ptjno) {
//        log.debug("Request to get BidUnit10DTO, docType : {}, docId : {}", wkut, prjno);
//        return bidPrjWksorcRepository.findByWkutAndPrjno(wkut, ptjno).map(bidPrjWksorcMapper::toDto);
//    }



    //郵件發送主檔 MailSend
    public void saveEmail(BidPrjWksorcDTO bidPrjWksorcDTO) {
        AdmAccountContactDTO admAccountContactDTO = new AdmAccountContactDTO();
        admAccountContactDTO.setUserId(bidPrjWksorcDTO.getWkut());
        admAccountContactDTO.setEmail("yisyuan9324@gmail.com");
        admAccountContactDTO.setUpdateTime(Instant.now());
        admAccountContactDTO.setUpdateUser("SYS");
        BeanUtils.copyProperties(admAccountContactDTO, bidPrjWksorcDTO);
        String content = formatEmail(bidPrjWksorcDTO);
        String subject = "公共工程勞工需求通知";
        AdmMailSend admMailSend = admAccountService.saveMailSend(admAccountContactDTO, "PWC0301", content, subject);
        admMailSend = admMailSendRepository.save(admMailSend);
//        emailSeeting(admMailSend);
    }

    private void emailSeeting(AdmMailSend admMailSend) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("chole10388@gmail.com");
        mailSender.setPassword("chole720401");
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");

        //sendMail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getJavaMailProperties().getProperty("from"));
        message.setTo(admMailSend.getReceiver());
        message.setSubject(admMailSend.getSubject());
        message.setText(admMailSend.getContent());
        mailSender.send(message);

    }


    public String formatEmail(BidPrjWksorcDTO bidPrjWksorcDTO) {
        admMailSendContent =  " 主旨：公共工程勞工需求通知;\n"+
                        "敬啟者：\n"+
                "短缺下列勞工：\n"+
                "惠請　貴中心儘速協助媒合。\n"+
                " 本工程承攬廠商:工地聯絡人：\n"+
                "主辦機關聯絡人：　聯絡電話：\n"+
                "主辦機關聯絡人：　聯絡電話：\n" +
                "'※以上訊息係由公共工程標案管理系統自動發送，請勿回傳，若需瞭解詳細職缺請與上述聯絡人聯繫。';";

        return admMailSendContent;



    }
}
