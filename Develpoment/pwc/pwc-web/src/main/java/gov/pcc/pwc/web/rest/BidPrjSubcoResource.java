package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.MessageCodes;
import gov.pcc.pwc.common.errors.BadRequestAlertException;
import gov.pcc.pwc.domain.BidPrjScequip;
import gov.pcc.pwc.domain.BidPrjSubco;
import gov.pcc.pwc.domain.BidPrjSubcoPrimaryKey;
import gov.pcc.pwc.repository.BidPrjSubcoRepository;
import gov.pcc.pwc.service.BidPrjScequipService;
import gov.pcc.pwc.service.BidPrjSubcoService;
import gov.pcc.pwc.service.criteria.BidPrjSubcoCriteria;
import org.springframework.data.domain.Page;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import gov.pcc.pwc.service.BidProjectUnitService;
import gov.pcc.pwc.service.BidUnit10Service;
import gov.pcc.pwc.service.dto.BidPrjScequipDTO;
import gov.pcc.pwc.service.dto.BidPrjSubcoDTO;
import gov.pcc.pwc.service.dto.BidProjectDTO;
import gov.pcc.pwc.utils.PwcHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link gov.pcc.pwc.domain.BidPrjSubco}.
 */
@RestController
@RequestMapping("/api")
public class BidPrjSubcoResource {

    private final Logger log = LoggerFactory.getLogger(BidPrjSubcoResource.class);

    private static final String ENTITY_NAME = "bidPrjSubco";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BidPrjSubcoService bidPrjSubcoService;

    private final BidPrjSubcoRepository bidPrjSubcoRepository;

    private final BidProjectUnitService bidProjectUnitService;

    private final BidUnit10Service bidUnit10Service;

    private final BidPrjScequipService bidPrjScequipService;

    private final BidPrjScequipService sidPrjScequipService;

    public BidPrjSubcoResource(BidPrjSubcoService bidPrjSubcoService, BidPrjSubcoRepository bidPrjSubcoRepository, BidProjectUnitService bidProjectUnitService, BidUnit10Service bidUnit10Service, BidPrjScequipService bidPrjScequipService, BidPrjScequipService sidPrjScequipService) {
        this.bidPrjSubcoService = bidPrjSubcoService;
        this.bidPrjSubcoRepository = bidPrjSubcoRepository;
        this.bidProjectUnitService = bidProjectUnitService;
        this.bidUnit10Service = bidUnit10Service;
        this.bidPrjScequipService = bidPrjScequipService;
        this.sidPrjScequipService = sidPrjScequipService;
    }

//    /**
//     * {@code PUT  /bid-prj-subco/:id} : Updates an existing bidPrjSubco.
//     *
//     * @param bidPrjSubcoDTO the bidPrjSubcoDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSubcoDTO,
//     * or with status {@code 400 (Bad Request)} if the bidPrjSubcoDTO is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the bidPrjSubcoDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/bid-prj-subco/{id}")
//    public ResponseEntity<BidPrjSubcoDTO> updateBidPrjSubco(@Valid @RequestBody BidPrjSubcoDTO bidPrjSubcoDTO
//    ) throws URISyntaxException {
//        log.debug("REST request to update BidPrjSubco : {}, {}", bidPrjSubcoDTO);//BidPrjSubcoPrimaryKey
//        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(bidPrjSubcoDTO.getWkut(), bidPrjSubcoDTO.getPrjno(), bidPrjSubcoDTO.getSubcoNo());
//
//        if (bidPrjSubcoDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(bidPrjSubcoPrimaryKey, bidPrjSubcoDTO.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        if (!bidPrjSubcoRepository.existsById(bidPrjSubcoPrimaryKey)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }
//
//        BidPrjSubcoDTO result = bidPrjSubcoService.save(bidPrjSubcoDTO);
//        return ResponseEntity
//            .ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSubcoDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * {@code PATCH  /bid-prj-subco/:id} : Partial updates given fields of an existing bidPrjSubco, field will ignore if it is null
     *
     * @param bidPrjSubcoDTO the bidPrjSubcoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSubcoDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjSubcoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bidPrjSubcoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjSubcoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bid-prj-subco/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<BidPrjSubcoDTO> partialUpdateBidPrjSubco(
        @NotNull @RequestBody BidPrjSubcoDTO bidPrjSubcoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BidPrjSubco partially : {}, {}", bidPrjSubcoDTO);
        BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey = new BidPrjSubcoPrimaryKey(bidPrjSubcoDTO.getWkut(), bidPrjSubcoDTO.getPrjno(), bidPrjSubcoDTO.getSubcoNo());

        if (bidPrjSubcoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSubcoPrimaryKey, bidPrjSubcoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSubcoRepository.existsById(bidPrjSubcoPrimaryKey)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BidPrjSubcoDTO> result = bidPrjSubcoService.partialUpdate(bidPrjSubcoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSubcoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bid-prj-subco} : get all the bidPrjSubcos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bidPrjSubcos in body.
     */
    @GetMapping("/bid-prj-subco")
    public List<BidPrjSubcoDTO> getAllBidPrjSubcos() {
        log.debug("REST request to get all BidPrjSubcos");
        return bidPrjSubcoService.findAll();
    }

    /**
     * {@code GET  /bid-prj-subco/:id} : get the "id" bidPrjSubco.
     *
     * @param id the id of the bidPrjSubcoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bidPrjSubcoDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-subco/findOne")
    public ResponseEntity<BidPrjSubcoDTO> getBidPrjSubco(@RequestBody BidPrjSubcoPrimaryKey id) {
        log.debug("REST request to get BidPrjSubco : {}", id);
        Optional<BidPrjSubcoDTO> bidPrjSubcoDTO = bidPrjSubcoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bidPrjSubcoDTO);
    }

    /**
     * {@code DELETE  /bid-prj-subco/:id} : delete the "id" bidPrjSubco.
     *
     * @param id the id of the bidPrjSubcoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bid-prj-subco/{id}")
    public ResponseEntity<Void> deleteBidPrjSubco(@RequestBody BidPrjSubcoPrimaryKey id) {
        log.debug("REST request to delete BidPrjSubco : {}", id);
        bidPrjSubcoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * get bidPrjSubcos by wkut and prjno
     **/
    @PostMapping("/bid-prj-subco/getListByCretiria")
    public List<BidPrjSubcoDTO> getBidPrjSubcosList(@RequestBody BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("REST request to get RecTyphoon : {}", bidPrjSubcoPrimaryKey);



        List<String> eCodeList = new ArrayList<>();

        List<String> spcwList = new ArrayList<>();

        int bidPrjSubcoCount = bidPrjSubcoService.findBidPrjSubcoCountList(bidPrjSubcoPrimaryKey);

        List<BidPrjSubcoDTO> prjAndBpu = bidPrjSubcoService.bidProjectAndProjectUnitList(bidPrjSubcoPrimaryKey);

        int subcoNo = 1;

        if (bidPrjSubcoCount <= 0) {

            BidPrjSubco upData = new BidPrjSubco();

            List<BidProjectDTO> project = bidPrjSubcoService.gatAllProject(bidPrjSubcoPrimaryKey);

            System.out.println("project%%%%%%%%%%%%%%%%%%%%%%%" + project);

            for (int i = 0; i < prjAndBpu.size(); i++) {

                upData.setWkut(prjAndBpu.get(i).getWkut());
                upData.setPrjno(prjAndBpu.get(i).getPrjno());
                upData.setSubcoNo("M0" + (subcoNo + i));
                upData.setSubCcut(prjAndBpu.get(i).getSubCcut());
                upData.setSubBdgt1(prjAndBpu.get(i).getSubBdgt1().doubleValue());
                if (prjAndBpu.size() == 1) {
                    upData.setSubcoMemo("主要工項詳如工程概要");
                }

                project.forEach(data -> {
                    upData.setSpcw01(data.getSpcw01());
                    upData.setSpcw02(data.getSpcw02());
                    upData.setSpcw03(data.getSpcw03());
                    upData.setSpcw04(data.getSpcw04());
                    upData.setSpcw05(data.getSpcw05());
                    upData.setSpcw06(data.getSpcw06());
                    upData.setSpcw07(data.getSpcw07());
                    upData.setSpcw08(data.getSpcw08());
                    upData.setSpcw09(data.getSpcw09());
                    upData.setSpcw10(data.getSpcw10());
                    upData.setSpcw11(data.getSpcw11());
                    upData.setSpcw12(data.getSpcw12());
                });


                LocalDate localDate = LocalDate.now();

                Instant localDateInstant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

                upData.setCreateDate(localDateInstant);
                //FIXME:先寫死 之後再調整
                upData.setCreateUser("SYS");
            }

            bidPrjSubcoRepository.save(upData);

        } else if (bidPrjSubcoCount > 0) {

            List<BidPrjSubcoDTO> prjSubcosList = bidPrjSubcoService.findPrjSubcosList(bidPrjSubcoPrimaryKey);

            System.out.println("prjSubcosList???????" + prjSubcosList);
            BidPrjSubco upData = new BidPrjSubco();
            for (int i = 0; i < prjSubcosList.size(); i++) {

                List<String> metList = new ArrayList<>();

                upData.setWkut(prjSubcosList.get(i).getWkut());
                upData.setPrjno(prjSubcosList.get(i).getPrjno());
                upData.setSubcoNo("M0" + (subcoNo + i));
                upData.setSubCcut(prjSubcosList.get(i).getSubCcut());
                upData.setSubBdgt1(prjSubcosList.get(i).getSubBdgt1().doubleValue());


                if (prjSubcosList.get(i).getSpcw01() != null) {
                    if (prjSubcosList.get(i).getSpcw01().equals("Y")) {
                        spcwList.add("鋼構工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw02() != null) {
                    if (prjSubcosList.get(i).getSpcw02().equals("Y")) {
                        spcwList.add("擋土支撐及土方工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw03() != null) {
                    if (prjSubcosList.get(i).getSpcw03().equals("Y")) {
                        spcwList.add("基礎工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw04() != null) {
                    if (prjSubcosList.get(i).getSpcw04().equals("Y")) {
                        spcwList.add("施工塔架吊裝及模板工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw05() != null) {
                    if (prjSubcosList.get(i).getSpcw05().equals("Y")) {
                        spcwList.add("預拌混凝土工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw06() != null) {
                    if (prjSubcosList.get(i).getSpcw06().equals("Y")) {
                        spcwList.add("營建鑽探工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw07() != null) {
                    if (prjSubcosList.get(i).getSpcw07().equals("Y")) {
                        spcwList.add("地下管線工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw08() != null) {
                    if (prjSubcosList.get(i).getSpcw08().equals("Y")) {
                        spcwList.add("帷幕牆工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw09() != null) {
                    if (prjSubcosList.get(i).getSpcw09().equals("Y")) {
                        spcwList.add("庭園、景觀工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw10() != null) {
                    if (prjSubcosList.get(i).getSpcw10().equals("Y")) {
                        spcwList.add("環境保護工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw11() != null) {
                    if (prjSubcosList.get(i).getSpcw11().equals("Y")) {
                        spcwList.add("防水工程");
                    }
                }

                if (prjSubcosList.get(i).getSpcw12() != null) {
                    if (prjSubcosList.get(i).getSpcw12().equals("Y")) {
                        spcwList.add("其他經中央主管機關會同主管機關增訂或變更，並公告之項目");
                    }
                }

                if (prjSubcosList.get(i).getMet01v() != null) {
                    upData.setMet01v(prjSubcosList.get(i).getMet01v().doubleValue());
                    upData.setMet01(prjSubcosList.get(i).getMet01());
                    if (prjSubcosList.get(i).getMet01v().intValue() > 0 || prjSubcosList.get(i).getMet01().equals("Y")) {
                        metList.add("預拌混凝土供應量" + prjSubcosList.get(i).getMet01v() + "立方米");

                    }
                }
                if (prjSubcosList.get(i).getMet01bv() != null) {
                    upData.setMet01bv(prjSubcosList.get(i).getMet01bv().doubleValue());
                    upData.setMet01b(prjSubcosList.get(i).getMet01b());
                    if (prjSubcosList.get(i).getMet01bv().intValue() > 0 || prjSubcosList.get(i).getMet01b().equals("Y")) {

                        metList.add("機拌混凝土供應量" + prjSubcosList.get(i).getMet01bv() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet01cv() != null) {
                    upData.setMet01cv(prjSubcosList.get(i).getMet01cv().doubleValue());
                    upData.setMet01c(prjSubcosList.get(i).getMet01c());
                    if (prjSubcosList.get(i).getMet01cv().intValue() > 0 || prjSubcosList.get(i).getMet01c().equals("Y")) {
                        metList.add("瀝青混凝土供應量" + prjSubcosList.get(i).getMet01cv() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet05v() != null) {
                    upData.setMet05v(prjSubcosList.get(i).getMet05v().doubleValue());
                    upData.setMet05(prjSubcosList.get(i).getMet05());
                    if (prjSubcosList.get(i).getMet05v().intValue() > 0 || prjSubcosList.get(i).getMet05().equals("Y")) {
                        metList.add("低密度再生透水混凝土供應量" + prjSubcosList.get(i).getMet05v() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet02fv() != null) {
                    upData.setMet02fv(prjSubcosList.get(i).getMet02fv().doubleValue());
                    upData.setMet02f(prjSubcosList.get(i).getMet02f());
                    if (prjSubcosList.get(i).getMet02fv().intValue() > 0 || prjSubcosList.get(i).getMet02f().equals("Y")) {
                        metList.add("clsm應量" + prjSubcosList.get(i).getMet02fv() + "立方米");
                    }
                }
                if (prjSubcosList.get(i).getMet01dv() != null) {
                    upData.setMet01dv(prjSubcosList.get(i).getMet01dv().doubleValue());
                    upData.setMet01d(prjSubcosList.get(i).getMet01d());
                    if (prjSubcosList.get(i).getMet01dv().intValue() > 0 || prjSubcosList.get(i).getMet01d().equals("Y")) {
                        metList.add("水泥砂漿供應量" + prjSubcosList.get(i).getMet01dv() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet02v() != null) {
                    upData.setMet02v(prjSubcosList.get(i).getMet02v().doubleValue());
                    upData.setMet02(prjSubcosList.get(i).getMet02());
                    if (prjSubcosList.get(i).getMet02v().intValue() > 0 || prjSubcosList.get(i).getMet02().equals("Y")) {
                        metList.add("砂供應量" + prjSubcosList.get(i).getMet02v() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet02bv() != null) {
                    upData.setMet02bv(prjSubcosList.get(i).getMet02bv().doubleValue());
                    upData.setMet02b(prjSubcosList.get(i).getMet02b());
                    if (prjSubcosList.get(i).getMet02bv().intValue() > 0 || prjSubcosList.get(i).getMet02b().equals("Y")) {
                        metList.add("級配供應量" + prjSubcosList.get(i).getMet02bv() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet04v() != null) {
                    upData.setMet04v(prjSubcosList.get(i).getMet04v().doubleValue());
                    upData.setMet04(prjSubcosList.get(i).getMet04());
                    if (prjSubcosList.get(i).getMet04v().intValue() > 0 || prjSubcosList.get(i).getMet04().equals("Y")) {
                        metList.add("土石方供應量" + prjSubcosList.get(i).getMet04v() + "立方米");
                    }
                }

                if (prjSubcosList.get(i).getMet03v() != null) {
                    upData.setMet03v(prjSubcosList.get(i).getMet03v().doubleValue());
                    upData.setMet03(prjSubcosList.get(i).getMet03());
                    if (prjSubcosList.get(i).getMet03v().intValue() > 0 || prjSubcosList.get(i).getMet03().equals("Y")) {
                        metList.add("鋼筋供應量" + prjSubcosList.get(i).getMet03v() + "噸");
                    }
                }

                if (prjSubcosList.get(i).getMet03bv() != null) {
                    upData.setMet03bv(prjSubcosList.get(i).getMet03bv().doubleValue());
                    upData.setMet03b(prjSubcosList.get(i).getMet03b());
                    if (prjSubcosList.get(i).getMet03bv().intValue() > 0 || prjSubcosList.get(i).getMet03b().equals("Y")) {
                        metList.add("鋼板型鋼供應量" + prjSubcosList.get(i).getMet03bv() + "噸");
                    }
                }

                if (prjSubcosList.get(i).getMet02cv() != null) {
                    upData.setMet02cv(prjSubcosList.get(i).getMet02cv().doubleValue());
                    upData.setMet02c(prjSubcosList.get(i).getMet02c());
                    if (prjSubcosList.get(i).getMet02cv().intValue() > 0 || prjSubcosList.get(i).getMet02c().equals("Y")) {
                        metList.add("焚化爐底渣供應量" + prjSubcosList.get(i).getMet02cv() + "噸");
                    }
                }

                if (prjSubcosList.get(i).getMet02dv() != null) {
                    upData.setMet02dv(prjSubcosList.get(i).getMet02dv().doubleValue());
                    upData.setMet02d(prjSubcosList.get(i).getMet02d());
                    if (prjSubcosList.get(i).getMet02dv().intValue() > 0 || prjSubcosList.get(i).getMet02d().equals("Y")) {
                        metList.add("轉爐石爐碴供應量" + prjSubcosList.get(i).getMet02dv() + "噸");
                    }
                }

                if (prjSubcosList.get(i).getMet02ev() != null) {
                    upData.setMet02ev(prjSubcosList.get(i).getMet02ev().doubleValue());
                    upData.setMet02e(prjSubcosList.get(i).getMet02e());
                    if (prjSubcosList.get(i).getMet02ev().intValue() > 0 || prjSubcosList.get(i).getMet02e().equals("Y")) {
                        metList.add("電弧爐氧化碴供應量" + prjSubcosList.get(i).getMet02ev() + "噸");
                    }
                }


                String metData = "";

                if (metList.size() > 0) {
                    for (int e = 0; e < metList.size(); e++) {
                        metData += metList.get(e) + "\n";
                    }

                    upData.setSubcoMemo(metData);

                } else {
                    upData.setSubcoMemo("主要工項詳如工程概要");
                }

                LocalDate localDate = LocalDate.now();

                Instant localDateInstant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

                upData.setCreateDate(localDateInstant);
                //FIXME:先寫死 之後再調整
                upData.setCreateUser("SYS");


            }

            bidPrjSubcoRepository.save(upData);
        }


        String spcwData = "";

        if (spcwList.size() > 0) {
            for (int e = 0; e < spcwList.size(); e++) {
                spcwData += spcwList.get(e) + "\n";
            }
        }

        String finalSpcwData = spcwData;


        List<BidPrjScequip> prjAndScequip = sidPrjScequipService.findByWkutAndPrjnoAndSubCcut(prjAndBpu.get(0).getWkut(), prjAndBpu.get(0).getPrjno(), prjAndBpu.get(0).getSubCcut());

        prjAndScequip.forEach(data -> {
            eCodeList.add(data.geteCode());
        });

        List<BidPrjSubcoDTO> prjSubcosList = bidPrjSubcoService.findPrjSubcosList(bidPrjSubcoPrimaryKey);

        String eCodeData = "";

        if (eCodeList.size() > 0) {
            for (int e = 0; e < prjAndScequip.size(); e++) {
                eCodeData += eCodeList.get(e) + "\n";
            }

        }

        String finalECodeData = eCodeData;

        prjSubcosList.forEach(data -> {
            data.seteCode(finalECodeData);
            data.setSpcw(finalSpcwData);
        });


        log.debug("REST request to get hhh : {}", prjSubcosList);

        return prjSubcosList;
    }

    /**
     * {@code PUT  /bid-prj-subco} : Updates an existing bidPrjSubco.
     *
     * @param bidPrjSubcoDTO the bidPrjModifyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bidPrjSubcoDTO,
     * or with status {@code 400 (Bad Request)} if the bidPrjModifyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bidPrjModifyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bid-prj-subco")
    public ResponseEntity<BidPrjSubcoDTO> updateBidPrjSubco(
        @Valid @RequestBody BidPrjSubcoDTO bidPrjSubcoDTO, Authentication authentication
    ) throws URISyntaxException {
        BidPrjSubcoPrimaryKey bidPrjSubcoPK = new BidPrjSubcoPrimaryKey(bidPrjSubcoDTO.getWkut(), bidPrjSubcoDTO.getPrjno(), bidPrjSubcoDTO.getSubcoNo());
        log.debug("REST request to update BidPrjSubco : {}", bidPrjSubcoDTO);
        if (bidPrjSubcoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(bidPrjSubcoPK, bidPrjSubcoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bidPrjSubcoRepository.existsById(bidPrjSubcoPK)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }


        if (bidPrjSubcoDTO.getScequips().get(0).geteCode() != null) {

            bidPrjScequipService.deleteScequips(bidPrjSubcoDTO.getWkut(), bidPrjSubcoDTO.getPrjno(), bidPrjSubcoDTO.getSubCcut());

            bidPrjSubcoDTO.getScequips().forEach(data -> {

                data.setWkut(bidPrjSubcoDTO.getWkut());
                data.setPrjno(bidPrjSubcoDTO.getPrjno());
                data.setSubCcut(bidPrjSubcoDTO.getSubCcut());
                data.setCreateDate(Instant.now());
                data.setCreateUser(authentication.getName());
                data.setUpdateDate(Instant.now());
                data.setUpdateUser(authentication.getName());

            });

            bidPrjSubcoDTO.getScequips().forEach(data -> {
                bidPrjScequipService.save(data);
            });
        }

        System.out.println("bidPrjSubcoDTO.getSpcw01():::::"+bidPrjSubcoDTO.getSpcw01());

//        List<String> validResult = bidPrjSubcoService.validData(bidPrjSubcoPK);
//
//        if(validResult.size()>0){
//            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
//        }



        bidPrjSubcoDTO.setUpdateDate(Instant.now());
        bidPrjSubcoDTO.setUpdateUser(authentication.getName());

        BidPrjSubcoDTO result = bidPrjSubcoService.save(bidPrjSubcoDTO);

        return ResponseEntity
            .ok()
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bidPrjSubcoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code Post  /bid-prj-subco/getOne} : get the "id" bidPrjSubco.
     *
     * @param bidPrjSubcoPrimaryKey the id of the BidPrjSubcoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the BidPrjSubcoDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/bid-prj-subco/getOne")
    public ResponseEntity<BidPrjSubcoDTO> getBidPrjModify(@RequestBody BidPrjSubcoPrimaryKey bidPrjSubcoPrimaryKey) {
        log.debug("REST request to get BidPrjModify : {}", bidPrjSubcoPrimaryKey);
        Optional<BidPrjSubcoDTO> bidPrjSubcoDTO = bidPrjSubcoService.findOne(bidPrjSubcoPrimaryKey);
        return ResponseUtil.wrapOrNotFound(bidPrjSubcoDTO);
    }

    @PostMapping("/bid-prj-subco/getProjectSubcoPage")
    public Page<BidPrjSubcoDTO> getProjectAllModify(@RequestBody BidPrjSubcoCriteria bidPrjSubcoCriteria) {
        log.debug("REST request to get BidPrjModify : {}", bidPrjSubcoCriteria);

        return bidPrjSubcoService.findProjectAllSubco(bidPrjSubcoCriteria);
    }

    /**
     * {@code POST  /bid-prj-subco} : Create a new bidPrjSubco.
     *
     * @param bidPrjSubcoDTO the bidPrjSubcoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bidPrjModifyDTO, or with status {@code 400 (Bad Request)} if the bidPrjSubco has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bid-prj-subco")
    public ResponseEntity<BidPrjSubcoDTO> createBidPrjSubco(@Valid @RequestBody BidPrjSubcoDTO bidPrjSubcoDTO, Authentication authentication)
        throws URISyntaxException {

        System.out.println("前端傳來的bidPrjSubcoDTO:" + bidPrjSubcoDTO);

        BidPrjSubcoDTO upData = new BidPrjSubcoDTO();
        BidPrjScequipDTO upDataScequip = new BidPrjScequipDTO();


        upData.setWkut(bidPrjSubcoDTO.getWkut());
        upData.setPrjno(bidPrjSubcoDTO.getPrjno());
        upData.setSubcoNo("M0" + bidPrjSubcoDTO.getSubcoNo());
        upData.setSubCcut(bidPrjSubcoDTO.getSubCcut());
        upData.setSubBdgt1(bidPrjSubcoDTO.getSubBdgt1());
        upData.setSpcw01(bidPrjSubcoDTO.getSpcw01());
        upData.setSpcw02(bidPrjSubcoDTO.getSpcw02());
        upData.setSpcw03(bidPrjSubcoDTO.getSpcw03());
        upData.setSpcw04(bidPrjSubcoDTO.getSpcw04());
        upData.setSpcw05(bidPrjSubcoDTO.getSpcw05());
        upData.setSpcw06(bidPrjSubcoDTO.getSpcw06());
        upData.setSpcw07(bidPrjSubcoDTO.getSpcw07());
        upData.setSpcw08(bidPrjSubcoDTO.getSpcw08());
        upData.setSpcw09(bidPrjSubcoDTO.getSpcw09());
        upData.setSpcw10(bidPrjSubcoDTO.getSpcw10());
        upData.setSpcw11(bidPrjSubcoDTO.getSpcw11());
        upData.setSpcw12(bidPrjSubcoDTO.getSpcw12());


        if(bidPrjSubcoDTO.getScequips().get(0).geteCode() != null){
            bidPrjSubcoDTO.getScequips().forEach(data -> {

                data.setWkut(bidPrjSubcoDTO.getWkut());
                data.setPrjno(bidPrjSubcoDTO.getPrjno());
                data.setSubCcut(bidPrjSubcoDTO.getSubCcut());
                data.setCreateDate(Instant.now());
                data.setCreateUser(authentication.getName());
                data.setUpdateDate(Instant.now());
                data.setUpdateUser(authentication.getName());

            });

            bidPrjSubcoDTO.getScequips().forEach(data -> {
                bidPrjScequipService.save(data);
            });
        }


//        List<String> validResult = bidPrjSubcoService.validData(bidPrjSubcoDTO);
//
//        if(validResult.size()>0){
//            throw new BidValidationFailException("bidValidError","資料檢核錯誤" ,validResult.toArray(new String[0]));
//        }

        upData.setCreateDate(Instant.now());
        upData.setCreateUser(authentication.getName());
        upData.setUpdateDate(Instant.now());
        upData.setUpdateUser(authentication.getName());

        BidPrjSubcoDTO result = bidPrjSubcoService.save(upData);
        return ResponseEntity
            .created(new URI("/api/bid-prj-modifies/" + result.getWkut() + "/" + result.getPrjno() + "/" + result.getSubcoNo().toString()))
            .headers(PwcHeadUtil.createAlert(MessageCodes.PWC_COMMON_0001_S()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getSubcoNo().toString()))
            .body(result);
    }
}
