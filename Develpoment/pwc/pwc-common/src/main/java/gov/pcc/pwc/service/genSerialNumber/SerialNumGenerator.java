package gov.pcc.pwc.service.genSerialNumber;

import gov.pcc.pwc.service.genSerialNumber.enums.ModuleEnum;
import gov.pcc.pwc.service.genSerialNumber.impl.*;
import org.springframework.stereotype.Service;

@Service
public class SerialNumGenerator{

    private final ALLNumberGenerator allNumberGenerator;
    private final ENGNumberGenerator engNumberGenerator;
    private final FA1NumberGenerator fa1NumberGenerator;
    private final FA2NumberGenerator fa2NumberGenerator;
    private final INSNumberGenerator insNumberGenerator;
    private final PRONumberGenerator proNumberGenerator;
    private final QUANumberGenerator quaNumberGenerator;
    private final RECNumberGenerator recNumberGenerator;
    private final REVNumberGenerator revNumberGenerator;


    public SerialNumGenerator(RECNumberGenerator recNumberGenerator, ENGNumberGenerator engNumberGenerator, ALLNumberGenerator allNumberGenerator, FA1NumberGenerator fa1NumberGenerator, FA2NumberGenerator fa2NumberGenerator, INSNumberGenerator insNumberGenerator, PRONumberGenerator proNumberGenerator, QUANumberGenerator quaNumberGenerator, REVNumberGenerator revNumberGenerator) {
        this.allNumberGenerator = allNumberGenerator;
        this.engNumberGenerator = engNumberGenerator;
        this.fa1NumberGenerator = fa1NumberGenerator;
        this.fa2NumberGenerator = fa2NumberGenerator;
        this.insNumberGenerator = insNumberGenerator;
        this.proNumberGenerator = proNumberGenerator;
        this.quaNumberGenerator = quaNumberGenerator;
        this.recNumberGenerator = recNumberGenerator;
        this.revNumberGenerator = revNumberGenerator;
    }



    public AbstractNumberGenerator setModule(ModuleEnum moduleEnum){
        AbstractNumberGenerator returnModule = null;

        switch (moduleEnum){
            case All:
                returnModule =  allNumberGenerator;
                break;
            case ENG:
                returnModule =  engNumberGenerator;
                break;
            case FA1:
                returnModule =  fa1NumberGenerator;
                break;
            case FA2:
                returnModule =  fa2NumberGenerator;
                break;
            case INS:
                returnModule =  insNumberGenerator;
                break;
            case PRO:
                returnModule =  proNumberGenerator;
                break;
            case QUA:
                returnModule =  quaNumberGenerator;
                break;
            case REC:
                returnModule =  recNumberGenerator;
                break;
            case REV:
                returnModule =  revNumberGenerator;
                break;
        }

        return returnModule;
    }

}
