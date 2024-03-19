package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.InsGenNumberVariableEnum;

import gov.pcc.pwc.service.genSerialNumber.enums.SequenceNameEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.genSerialNumber.util.GenSerialNumUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class INSNumberGenerator extends AbstractNumberGenerator {
    private final String insPrefix = "INS-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private InsGenNumberVariableEnum variable;
    private Date codeDate;
    private SequenceNameEnum sequenceEnum;


    public INSNumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }

    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("DUCON_OVERSEE"):
                this.variable = InsGenNumberVariableEnum.DUCON_OVERSEE;
                this.sequenceEnum = SequenceNameEnum.INS_DUCON_OVERSEE;
                break;
            case ("DUCON_1999"):
                this.variable = InsGenNumberVariableEnum.DUCON_1999;
                this.sequenceEnum = SequenceNameEnum.INS_DUCON_1999;
                break;
            default:
                throw new TypeVariableNotMatchException();
        }

        this.codeDate = variable.getDateVariable();

        return this;
    }

    @Override
    public String getGeneratedNumber() {
        StringBuilder insSerialNumBuilder = new StringBuilder();
        //利用codeDate取得 民國年
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.codeDate);
        Integer year =  calendar.get(Calendar.YEAR);
        Integer mingouYear =  year-1911;

        insSerialNumBuilder.append(mingouYear.toString());

        //利用sequece name來拿到sequence number
        //督工固定8碼,如果之後有變動再改
        String serialNum = (sequencesRepositoryImpl.getNextSequenceVal(this.sequenceEnum.getSeqName())).toString();
        String serialNumWithPad = StringUtils.leftPad(serialNum,8,'0');

        insSerialNumBuilder.append(serialNumWithPad);
        String returnInsSerial = insSerialNumBuilder.toString();
        //存進資料庫
        genSerialNumUtil.saveAndUpdateGenNumTableWithYear(
                this.insPrefix,
                this.variable.getVariable(),
                this.codeDate,
                serialNumWithPad
        );

        return returnInsSerial;
    }


    //    @Override
//    public String getGeneratedNumber() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("INS-");
//        sb.append(this.variable.getVariable());

        //篩選條件
//        Specification<AdmNumber> criteria = new Specification<AdmNumber>() {
//            @Override
//            public Predicate toPredicate(Root<AdmNumber> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate predicate =criteriaBuilder.and( criteriaBuilder.equal(root.get("codeType"),sb.toString()),
//                                                          criteriaBuilder.equal(root.get("codeYear"),"111"));
//                return predicate;
//            }
//        };
//        Optional<AdmNumber> currentNumOptional =admNumberRepository.findOneByCodeTypeAndCodeYear("INS-"+this.variable.getVariable(),"111");
//
//        String specificNumber =null;
//        String prefix = null;
//        if(currentNumOptional.isPresent()){
//            AdmNumber currentNum = currentNumOptional.get();
//            specificNumber= currentNumOptional.get().getNumber();
//            prefix= currentNumOptional.get().getCodeYear();
//            String newNumber = String.valueOf(Integer.parseInt(specificNumber) + 1);
//            if(newNumber.length()<8){
//                newNumber= StringUtils.leftPad(newNumber, 8, '0');
//            }
//
//            currentNum.setNumber(newNumber);
//
//
//
//            admNumberRepository.save(currentNum);
//        }
//
//
//        return prefix+specificNumber;
//    }
}
