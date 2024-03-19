package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.RecGenNumberVariableEnum;
import gov.pcc.pwc.service.genSerialNumber.enums.SequenceNameEnum;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import gov.pcc.pwc.service.genSerialNumber.util.GenSerialNumUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RECNumberGenerator extends AbstractNumberGenerator {
    private final String recPrefix = "REC-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private RecGenNumberVariableEnum variable;
    private Date codeDate;


    public RECNumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }


    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {

        switch (variable.getTypeVariable()){
            case ("Typ"):
                this.variable = RecGenNumberVariableEnum.TYP;
                break;
            case ("Act"):
                this.variable = RecGenNumberVariableEnum.ACT;
                break;
            case ("Prj"):
                this.variable = RecGenNumberVariableEnum.PRJ;
                break;
            case ("Are"):
                this.variable = RecGenNumberVariableEnum.Are;
                break;
            case ("Pic"):
                this.variable = RecGenNumberVariableEnum.Pic;
                break;
            case ("New"):
                this.variable = RecGenNumberVariableEnum.New;
                break;
            case ("Wor"):
                this.variable = RecGenNumberVariableEnum.WOR;
                break;
            case ("Ana"):
                this.variable = RecGenNumberVariableEnum.ANA;
                break;
            case ("Fil"):
                this.variable = RecGenNumberVariableEnum.FIL;
                break;
            default:
                throw new TypeVariableNotMatchException();
        }
        this.codeDate = new Date();

        return this;
    }

    public RECNumberGenerator setRecVariable(RecGenNumberVariableEnum variable) {
        this.variable = variable;
        this.codeDate = new Date();
        return this;
    }

    @Override
    public String getGeneratedNumber() {
        StringBuilder recNumberBuilder = new StringBuilder();

        recNumberBuilder.append(this.variable.getVariable());

        //西元年尾2+時分秒毫秒
        String dateText = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(codeDate);
        dateText = dateText.substring(2,17);


        //毫秒以下四位的寫法
        // DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(7).toFormatter();
        // String behindSecond = formatter.format(Instant.now()).toString().split(":")[2].substring(3,10);
        // dateText = dateText.substring(2);

        //取後四位
//        String behindSecond = genNumberRepository.getNextValRec().toString();
        String behindSecond = sequencesRepositoryImpl.getNextSequenceVal(SequenceNameEnum.REC_SEQ.getSeqName()).toString();
        behindSecond= StringUtils.leftPad(behindSecond, 4, '0');

        recNumberBuilder.append(dateText);
        recNumberBuilder.append(behindSecond);

        //把serial num存進資料表
        genSerialNumUtil.saveAndUpdateGenNumTableWithYear(this.recPrefix,this.variable.getVariable(),this.codeDate,behindSecond);

        return recNumberBuilder.toString();
    }


    /**
     *
     * @param serialNumberBySequence 透過上面的方法，從sequence產生的serial num
     */
    //搬到genSerialNumUtil去了
//    private void saveAndUpdateGenNumTable(String serialNumberBySequence){
//        String codeType = this.recPrefix + this.variable.getVariable();
//        //處理年
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(this.codeDate);
//        Integer year =  calendar.get(Calendar.YEAR);
//        Integer mingouYear =  year-1911;
//
//
//        // String codeYear = this.codeDate
//
//        Optional<AdmNumber> genNumOp = admNumberRepository.findOneByCodeTypeAndCodeYear(codeType, mingouYear.toString());
//        if(genNumOp.isPresent()){
//            AdmNumber genNum = genNumOp.get();
//            genNum.setNumber(serialNumberBySequence);
//            admNumberRepository.save(genNum);
//        }else{
//            AdmNumber newGenNum = new AdmNumber();
//            newGenNum.setCodeType(codeType);
//            newGenNum.setNumber(serialNumberBySequence);
//            newGenNum.setCodeYear(mingouYear.toString());
//            newGenNum.setCreateTime(Instant.now());
//            newGenNum.setCreateUser("SYS");
//            admNumberRepository.save(newGenNum);
//        }
//    }


}
