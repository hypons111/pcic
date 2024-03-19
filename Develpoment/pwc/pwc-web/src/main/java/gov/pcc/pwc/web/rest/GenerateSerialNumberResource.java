package gov.pcc.pwc.web.rest;

import gov.pcc.pwc.service.genSerialNumber.SerialNumGenerator;
import gov.pcc.pwc.service.genSerialNumber.enums.*;
import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
public class GenerateSerialNumberResource {

    private final Logger log = LoggerFactory.getLogger(GenerateSerialNumberResource.class);

    private final SerialNumGenerator serialNumGenerator;

    public GenerateSerialNumberResource(SerialNumGenerator serialNumGenerator) {
        this.serialNumGenerator = serialNumGenerator;
    }

    @GetMapping("/serialNumTest/ENG")
    public  List<String> getSerialNumTestENG() throws TypeVariableNotMatchException {
        List<String> engNumberArr = new ArrayList<>();

        for (EngGenNumberVariableEnum engEnum : EngGenNumberVariableEnum.values()) {
            //System.out.println("engEnum: "+engEnum.getVariable());
            String str = serialNumGenerator
                .setModule(ModuleEnum.ENG)
                .setVariable(new SerialNumVariableParam(engEnum.getVariable()))
                .getGeneratedNumber();

            engNumberArr.add(engEnum.getVariable()+"："+str);
        }

        return engNumberArr;
    }

    @GetMapping("/serialNumTest/All")
    public  String getSerialNumTestAll() throws TypeVariableNotMatchException {
            String str = serialNumGenerator
                .setModule(ModuleEnum.All)
                .setVariable(new SerialNumVariableParam(AllGenNumberVariableEnum.API_CALL_NO.getVariable()))
                .getGeneratedNumber();
        return str;
    }

    @GetMapping("/serialNumTest/FA1")
    public  List<String> getSerialNumTestFA1() throws TypeVariableNotMatchException {
        List<String> engNumberArr = new ArrayList<>();

        for (Fa1GenNumberVariableEnum fa1Enum : Fa1GenNumberVariableEnum.values()) {
            //System.out.println("engEnum: "+engEnum.getVariable());
            String str = serialNumGenerator
                .setModule(ModuleEnum.FA1)
                .setVariable(new SerialNumVariableParam(fa1Enum.getVariable()))
                .getGeneratedNumber();

            engNumberArr.add(fa1Enum.getVariable()+"："+str);
        }

        return engNumberArr;
    }

    @GetMapping("/serialNumTest/FA2")
    public  String getSerialNumTestFA2() throws TypeVariableNotMatchException {
        String str = serialNumGenerator
            .setModule(ModuleEnum.FA2)
            .setVariable(new SerialNumVariableParam(Fa2GenNumberVariableEnum.IDLE_PLNBAS.getVariable()))
            .getGeneratedNumber();
        return Fa2GenNumberVariableEnum.IDLE_PLNBAS.getVariable()+":"+str;
    }


    @GetMapping("/serialNumTest/INS")
    public  List<String> getSerialNumTestINS() throws TypeVariableNotMatchException {
        List<String> engNumberArr = new ArrayList<>();

        for (InsGenNumberVariableEnum insEnum : InsGenNumberVariableEnum.values()) {
            //System.out.println("engEnum: "+engEnum.getVariable());
            String str = serialNumGenerator
                .setModule(ModuleEnum.INS)
                .setVariable(new SerialNumVariableParam(insEnum.getVariable()))
                .getGeneratedNumber();

            engNumberArr.add(insEnum.getVariable()+"："+str);
        }

        return engNumberArr;
    }

    @GetMapping("/serialNumTest/PRO")
    public  String getSerialNumTestPRO() throws TypeVariableNotMatchException {
        String str = serialNumGenerator
            .setModule(ModuleEnum.PRO)
            .setVariable(new SerialNumVariableParam(ProGenNumberVariableEnum.INNOVATIVE_PRODUCT.getVariable()))
            .getGeneratedNumber();
        return ProGenNumberVariableEnum.INNOVATIVE_PRODUCT.getVariable() + ":"+str;
    }
    @GetMapping("/serialNumTest/QUA")
    public  String getSerialNumTestQUA() throws TypeVariableNotMatchException {
        String str = serialNumGenerator
            .setModule(ModuleEnum.QUA)
            .setVariable(new SerialNumVariableParam(QuaGenNumberVariableEnum.QTRAIN_QTYCLASS.getVariable()))
            .getGeneratedNumber();
        return QuaGenNumberVariableEnum.QTRAIN_QTYCLASS.getVariable() + ":"+str;
    }

    @GetMapping("/serialNumTest/REC")
    public  List<String> getSerialNumTestREC() throws TypeVariableNotMatchException {
        List<String> recNumberArr = new ArrayList<>();

        for (RecGenNumberVariableEnum recEnum : RecGenNumberVariableEnum.values()) {
            //System.out.println("engEnum: "+engEnum.getVariable());
            String str = serialNumGenerator
                .setModule(ModuleEnum.REC)
                .setVariable(new SerialNumVariableParam(recEnum.getVariable()))
                .getGeneratedNumber();

            recNumberArr.add(recEnum.getVariable()+"："+str);
        }

        return recNumberArr;
    }

    @GetMapping("/serialNumTest/REV")
    public  String getSerialNumTestREV() throws TypeVariableNotMatchException {
        String str = serialNumGenerator
            .setModule(ModuleEnum.REV)
            .setVariable(new SerialNumVariableParam(RevGenNumberVariableEnum.CODE.getVariable(),new Date(),"2A0"))
            .getGeneratedNumber();
        return RevGenNumberVariableEnum.CODE.getVariable() + ":"+str;
    }
}
