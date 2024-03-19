package gov.pcc.pwc.service.genSerialNumber;

import gov.pcc.pwc.service.genSerialNumber.errors.TypeVariableNotMatchException;
import gov.pcc.pwc.service.genSerialNumber.parameter.SerialNumVariableParam;

public abstract class AbstractNumberGenerator implements GenerateNumberInterFace{
    public abstract AbstractNumberGenerator setVariable(SerialNumVariableParam variable) throws TypeVariableNotMatchException;
}
