package gov.pcc.pwc.service.dto;

import java.util.List;
import java.util.Objects;

public class RecSelectOptionsDTO {

    private String parents;

    private List<RecSelectOptionsChildDTO> child;

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public List<RecSelectOptionsChildDTO> getChild() {
        return child;
    }

    public void setChild(List<RecSelectOptionsChildDTO> child) {
        this.child = child;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof RecSelectOptionsDTO)){
            return false;
        }
        RecSelectOptionsDTO recSelectOptionsDTO = (RecSelectOptionsDTO) o;
        if(this.parents == null){
            return false;
        }
        return Objects.equals(this.parents,recSelectOptionsDTO.parents);
    }

    @Override
    public String toString() {
        return "RecSelectOptionsDTO{" +
                "parents='" + getParents() + '\'' +
                ", child='" + getChild().toString() + '\'' +
                '}';
    }
}
