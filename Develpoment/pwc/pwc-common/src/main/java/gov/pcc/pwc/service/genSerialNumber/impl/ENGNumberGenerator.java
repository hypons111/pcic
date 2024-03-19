package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.common.enums.eng.EngDocNoTypeEnum;
import gov.pcc.pwc.domain.AdmSysCode;
import gov.pcc.pwc.repository.AdmSysCodeRepository;
import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.EngDocNoService;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.EngGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.SequenceNameEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.genSerialNumber.util.GenSerialNumUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ENGNumberGenerator extends AbstractNumberGenerator {
    private final String engPrefix = "ENG-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;
    private final AdmSysCodeRepository admSysCodeRepository;
    private final EngDocNoService engDocNoService;

    public ENGNumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl, AdmSysCodeRepository admSysCodeRepository, EngDocNoService engDocNoService) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
        this.admSysCodeRepository = admSysCodeRepository;
        this.engDocNoService = engDocNoService;
    }

    /**技師有三種給號的模式
     * engYYYY_MM_S4 西元年 + 月份 + 流水號4
     * engYYYY_S6 西元年 + 流水號6
     * engDocNo_S6 "字號開頭" + 流水號6 + "號"
     * engSerail8 流水號8
     *
     */
    enum SerailType{
        engYYYY_MM_S4, engYYYY_S6, engDocNo_S6, engSerail8
    }

    private EngGenNumberVariableEnum variable;
    private Date codeDate;
    private SerailType type;
    private SequenceNameEnum sequenceEnum;
    private String admSysCode_dataKey;


    //根據傳進來的變數
    //來決定對應的給號模式
    //以及他的sequence
    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("ENGR_CERTIFATE_ID"):
                this.variable = EngGenNumberVariableEnum.ENGR_CERTIFATE_ID;
                this.type = SerailType.engYYYY_MM_S4;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_CERTIFICATE_ID_SEQ;
                break;
            case ("ENGR_SUBJECTPOINT_ID"):
                this.variable = EngGenNumberVariableEnum.ENGR_SUBJECTPOINT_ID;
                this.type = SerailType.engYYYY_MM_S4;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_SUBJECTPOINT_ID_SEQ;
                break;
            case ("COMP_ID"):
                this.variable = EngGenNumberVariableEnum.COMP;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_COMP_SEQ;
                break;
            case ("CST_LICENSE"):
                this.variable = EngGenNumberVariableEnum.CST_LICENSE;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_COMP_SEQ;
                break;
            case ("ENGR_CERTIFATE_NO"):
                this.variable = EngGenNumberVariableEnum.ENGR_CERTIFATE_NO;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_CERTIFICATE_NO_SEQ;
                break;
            case ("ENGR_LICENSE"):
                this.variable = EngGenNumberVariableEnum.ENGR_LICENSE;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_LICENCE_DOC_NO_SEQ;
                break;
            case ("COURSE_ID"):
                this.variable = EngGenNumberVariableEnum.COURSE;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_COURSE_SEQ;
                break;
            case ("TEACHER_ID"):
                this.variable = EngGenNumberVariableEnum.TEACHER;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_TEACHER_SEQ;
                break;
            case ("REVIEW_LOG"):
                this.variable = EngGenNumberVariableEnum.REVIEW_LOG;
                this.type = SerailType.engYYYY_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_REVIEW_LOG_SEQ;
                break;
            case ("ENGR_CERTIFICATE_DOC_NO"):
                this.variable = EngGenNumberVariableEnum.ENGR_CERTIFICATE_DOC_NO;
                this.type = SerailType.engDocNo_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_CERTIFICATE_DOC_NO_SEQ;
                this.admSysCode_dataKey = EngDocNoTypeEnum.ENGR_CERTIFICATE.getCode();
                break;
            case ("ENGR_LICENCE_DOC_NO"):
                this.variable = EngGenNumberVariableEnum.ENGR_LICENCE_DOC_NO;
                this.type = SerailType.engDocNo_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_ENGR_LICENCE_DOC_NO_SEQ;
                this.admSysCode_dataKey = EngDocNoTypeEnum.ENGR_LICENSE.getCode();
                break;
            case ("CST_LICENCE_DOC_NO"):
                this.variable = EngGenNumberVariableEnum.CST_LICENCE_DOC_NO;
                this.type = SerailType.engDocNo_S6;
                this.sequenceEnum = SequenceNameEnum.ENG_CST_LICENCE_DOC_NO_SEQ;
                this.admSysCode_dataKey = EngDocNoTypeEnum.CST_LICENSE.getCode();
                break;
            case ("MEMBER_ID"):
                this.variable = EngGenNumberVariableEnum.MEMBER;
                this.type = SerailType.engSerail8;
                this.sequenceEnum = SequenceNameEnum.ENG_MEMBER_SEQ;
                break;
            case ("EBILLING_CHECK"):
                this.variable = EngGenNumberVariableEnum.EBILLING_CHECK;
                this.type = SerailType.engSerail8;
                this.sequenceEnum = SequenceNameEnum.ENG_EBILLING_CHECK_SEQ;
                break;
            default:
                throw new TypeVariableNotMatchException();
        }
        this.codeDate = variable.getDateVariable();

        return this;
    }

    //用sequence name從sequence取號，並加上pad,ex: 名字是ENG_EBILLING_CHECK_SEQ(電子對帳)，取出來的比如說245,
    //然後因為要滿足8碼的關係,size設定為8，加入'0'的padding char，會變成00000245
    private String serailNumWithPad(String sequenceName,int size) {
        String serialNum = (sequencesRepositoryImpl.getNextSequenceVal(sequenceName)).toString();
        return StringUtils.leftPad(serialNum,size,'0');
    }

    //利用給號模式和sequence name產生序號
    @Override
    public String getGeneratedNumber() {
        StringBuilder engSerialNumBuilder = new StringBuilder();
        String dateText;
        String postNumber = "";
        switch (this.type){
            case engYYYY_MM_S4:
                //西元年+月份
                dateText = new SimpleDateFormat("yyyyMM").format(this.codeDate);
                engSerialNumBuilder.append(dateText);
                //後四碼為sequence 產生的serial num
                postNumber = serailNumWithPad(this.sequenceEnum.getSeqName(),4);
                engSerialNumBuilder.append(postNumber);
                break;
            case engYYYY_S6:
                //西元年
                dateText = new SimpleDateFormat("yyyy").format(this.codeDate);
                engSerialNumBuilder.append(dateText);
                //後六碼為sequence 產生的serial num
                postNumber = serailNumWithPad(this.sequenceEnum.getSeqName(),6);
                engSerialNumBuilder.append(postNumber);
                break;
            case engDocNo_S6:
                String docNo = engDocNoService.getDocNo(this.admSysCode_dataKey);
                if(docNo != null) {
                    postNumber = docNo;
                }else {
                    postNumber = serailNumWithPad(this.sequenceEnum.getSeqName(),6);
                }
                Optional<AdmSysCode> admSysCodeOptional = admSysCodeRepository.findByModuleTypeAndDataTypeAndDataKey("ENG", "DOC_NO", this.admSysCode_dataKey);
                admSysCodeOptional.ifPresent( admSysCode -> engSerialNumBuilder.append(admSysCode.getValue()) );
                engSerialNumBuilder.append("第");
                engSerialNumBuilder.append(postNumber);
                engSerialNumBuilder.append("號");
                break;
            case engSerail8:
                postNumber = serailNumWithPad(this.sequenceEnum.getSeqName(),8);
                engSerialNumBuilder.append(postNumber);
                break;
            default:
                break;
        }
        //存進資料庫
        genSerialNumUtil.saveAndUpdateGenNumTableWithYear(
                this.engPrefix,
                this.variable.getVariable(),
                this.codeDate,
                postNumber
        );

        return engSerialNumBuilder.toString();
    }




}
