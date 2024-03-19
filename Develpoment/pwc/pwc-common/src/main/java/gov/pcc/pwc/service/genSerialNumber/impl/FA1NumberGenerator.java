package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.Fa1GenNumberVariableEnum;
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
public class FA1NumberGenerator extends AbstractNumberGenerator{
    private final String insPrefix = "INS-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private Fa1GenNumberVariableEnum variable;
    private Date codeDate;
    private SequenceNameEnum sequenceEnum;


    public FA1NumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }

    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("IDLE_UTILITY"):
                this.variable = Fa1GenNumberVariableEnum.IDLE_UTILITY;
                this.sequenceEnum = SequenceNameEnum.FA1_IDLE_UTILITY;
                break;
            case ("IDLE_OVERSEE_TEMP"):
                this.variable = Fa1GenNumberVariableEnum.IDLE_OVERSEE_TEMP;
                this.sequenceEnum = SequenceNameEnum.FA1_IDLE_OVERSEE_TEMP;
                break;
            case ("IDLE_OVERSEE"):
                this.variable = Fa1GenNumberVariableEnum.IDLE_OVERSEE;
                this.sequenceEnum = SequenceNameEnum.FA1_IDLE_OVERSEE;
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
        //固定8碼,如果之後有變動再改
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
}
