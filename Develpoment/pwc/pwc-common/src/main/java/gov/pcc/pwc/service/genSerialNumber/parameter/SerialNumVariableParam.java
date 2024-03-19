package gov.pcc.pwc.service.genSerialNumber.parameter;

import java.util.Date;

public class SerialNumVariableParam {

    private String typeVariable;
    private Date dateVariable = new Date();
    private String otherVariable = "";

    public SerialNumVariableParam(String typeVariable) {
        this.typeVariable = typeVariable;
    }

    public SerialNumVariableParam(String typeVariable, Date dateVariable, String otherVariable) {
        this.typeVariable = typeVariable;
        this.dateVariable = dateVariable;
        this.otherVariable = otherVariable;
    }

    public String getTypeVariable() {
        return typeVariable;
    }

    public void setTypeVariable(String typeVariable) {
        this.typeVariable = typeVariable;
    }

    public Date getDateVariable() {
        return dateVariable;
    }

    public void setDateVariable(Date dateVariable) {
        this.dateVariable = dateVariable;
    }

    public String getOtherVariable() {
        return otherVariable;
    }

    public void setOtherVariable(String otherVariable) {
        this.otherVariable = otherVariable;
    }
}
