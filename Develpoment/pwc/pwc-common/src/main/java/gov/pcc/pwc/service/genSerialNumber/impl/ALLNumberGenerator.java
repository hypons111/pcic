package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.AllGenNumberVariableEnum;
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
public class ALLNumberGenerator extends AbstractNumberGenerator{
    private final String allPrefix = "ALL-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private AllGenNumberVariableEnum variable;
    private Date codeDate;
    private SequenceNameEnum sequenceEnum;


    public ALLNumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }

    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("API_CALL_NO"):
                this.variable = AllGenNumberVariableEnum.API_CALL_NO;
                this.sequenceEnum = SequenceNameEnum.ALL_API_CALL_NO;
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
                this.allPrefix,
                this.variable.getVariable(),
                this.codeDate,
                serialNumWithPad
        );

        return returnInsSerial;
    }
}
