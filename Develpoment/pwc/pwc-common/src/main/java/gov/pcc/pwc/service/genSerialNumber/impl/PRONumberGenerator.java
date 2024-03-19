package gov.pcc.pwc.service.genSerialNumber.impl;

import gov.pcc.pwc.repository.impl.SequencesRepositoryImpl;
import gov.pcc.pwc.service.genSerialNumber.AbstractNumberGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.ProGenNumberVariableEnum;
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
public class PRONumberGenerator extends AbstractNumberGenerator{
    private final String proPrefix = "PRO-";

    private final GenSerialNumUtil genSerialNumUtil;
    private final SequencesRepositoryImpl sequencesRepositoryImpl;

    private ProGenNumberVariableEnum variable;
    private Date codeDate;
    private SequenceNameEnum sequenceEnum;


    public PRONumberGenerator(GenSerialNumUtil genSerialNumUtil, SequencesRepositoryImpl sequencesRepositoryImpl) {
        this.genSerialNumUtil = genSerialNumUtil;
        this.sequencesRepositoryImpl = sequencesRepositoryImpl;
    }

    @Override
    public AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException {
        switch (variable.getTypeVariable()){
            case ("INNOVATIVE_PRODUCT"):
                this.variable = ProGenNumberVariableEnum.INNOVATIVE_PRODUCT;
                this.sequenceEnum = SequenceNameEnum.PRO_INNOVATIVE_PRODUCT;
                break;
            default:
                throw new TypeVariableNotMatchException();
        }

        this.codeDate = variable.getDateVariable();

        return this;
    }

    @Override
    public String getGeneratedNumber() {
        StringBuilder proSerialNumBuilder = new StringBuilder();
        //利用codeDate取得 西元年+月
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.codeDate);
        Integer year =  calendar.get(Calendar.YEAR);
        Integer month =  calendar.get(Calendar.MONTH)+1;

        proSerialNumBuilder.append(year.toString());

        //月填滿兩碼
        String monthWithPad = StringUtils.leftPad(month.toString(),2,'0');
        proSerialNumBuilder.append(monthWithPad);

        //利用sequece name來拿到sequence number
        //固定3碼,如果之後有變動再改
        String serialNum = (sequencesRepositoryImpl.getNextSequenceVal(this.sequenceEnum.getSeqName())).toString();
        String serialNumWithPad = StringUtils.leftPad(serialNum,3,'0');

        proSerialNumBuilder.append(serialNumWithPad);
        String returnInsSerial = proSerialNumBuilder.toString();
        //存進資料庫
        genSerialNumUtil.saveAndUpdateGenNumTableWithYear(
                this.proPrefix,
                this.variable.getVariable(),
                this.codeDate,
                serialNumWithPad
        );

        return returnInsSerial;
    }
}
