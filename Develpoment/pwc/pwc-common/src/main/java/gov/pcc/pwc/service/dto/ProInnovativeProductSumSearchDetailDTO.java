package gov.pcc.pwc.service.dto;

import gov.pcc.pwc.domain.AdmFile;

import java.io.Serializable;
import java.util.List;

public class ProInnovativeProductSumSearchDetailDTO implements Serializable {

    private ProInnovativeProductMainDTO proInnovativeProductMainDTO;
    private List<ProEnginListDTO> proEnginListDTOs;
    private List<ProRecommendListDTO> proRecommendListDTOs;
    private List<ProCommunicatedDTO> proCommunicatedDTOs;
    private List<ProNoCommunicateDTO> proNoCommunicateDTOs;
    private List<String> enginTypes;
    private ProApplyCompanyDTO proApplyCompanyDTO;
    private ProRecommendListDTO proRecommendListDTO;
    private ProOrgSuggestionDTO proOrgSuggestionDTO;
    private List<String> noCommunicateTypes;
    //        private byte[] picFile;
    private List<AdmFile> appendixFiles;
    private List<String> personNames;

    public ProInnovativeProductSumSearchDetailDTO() {
    }

    public ProInnovativeProductSumSearchDetailDTO(ProInnovativeProductMainDTO proInnovativeProductMainDTO, List<ProEnginListDTO> proEnginListDTOs, List<ProRecommendListDTO> proRecommendListDTOs, List<ProCommunicatedDTO> proCommunicatedDTOs, List<ProNoCommunicateDTO> proNoCommunicateDTOs, List<String> enginTypes, ProApplyCompanyDTO proApplyCompanyDTO ,List<AdmFile> appendixFiles,ProRecommendListDTO proRecommendListDTO, ProOrgSuggestionDTO proOrgSuggestionDTO, List<String> noCommunicateTypes, List<String> personNames) {
        this.proInnovativeProductMainDTO = proInnovativeProductMainDTO;
        this.proEnginListDTOs = proEnginListDTOs;
        this.proRecommendListDTOs = proRecommendListDTOs;
        this.proCommunicatedDTOs = proCommunicatedDTOs;
        this.proNoCommunicateDTOs = proNoCommunicateDTOs;
        this.enginTypes = enginTypes;
        this.proApplyCompanyDTO = proApplyCompanyDTO;
        this.appendixFiles = appendixFiles;
        this.proRecommendListDTO = proRecommendListDTO;
        this.proOrgSuggestionDTO = proOrgSuggestionDTO;
        this.noCommunicateTypes = noCommunicateTypes;
        this.personNames = personNames;
    }

    public ProInnovativeProductSumSearchDetailDTO(ProInnovativeProductMainDTO proInnovativeProductMainDTO, List<ProEnginListDTO> proEnginListDTOs, List<ProRecommendListDTO> proRecommendListDTOs, List<ProCommunicatedDTO> proCommunicatedDTOs, List<ProNoCommunicateDTO> proNoCommunicateDTOs) {
        this.proInnovativeProductMainDTO = proInnovativeProductMainDTO;
        this.proEnginListDTOs = proEnginListDTOs;
        this.proRecommendListDTOs = proRecommendListDTOs;
        this.proCommunicatedDTOs = proCommunicatedDTOs;
        this.proNoCommunicateDTOs = proNoCommunicateDTOs;
    }

    public List<AdmFile> getAppendixFiles() {
        return appendixFiles;
    }

    public void setAppendixFiles(List<AdmFile> appendixFiles) {
        this.appendixFiles = appendixFiles;
    }

    public ProInnovativeProductMainDTO getProInnovativeProductMainDTO() {
        return proInnovativeProductMainDTO;
    }

    public void setProInnovativeProductMainDTO(ProInnovativeProductMainDTO proInnovativeProductMainDTO) {
        this.proInnovativeProductMainDTO = proInnovativeProductMainDTO;
    }

    public List<ProEnginListDTO> getProEnginListDTOs() {
        return proEnginListDTOs;
    }

    public void setProEnginListDTOs(List<ProEnginListDTO> proEnginListDTOs) {
        this.proEnginListDTOs = proEnginListDTOs;
    }

    public List<ProRecommendListDTO> getProRecommendListDTOs() {
        return proRecommendListDTOs;
    }

    public void setProRecommendListDTOs(List<ProRecommendListDTO> proRecommendListDTOs) {
        this.proRecommendListDTOs = proRecommendListDTOs;
    }

    public List<ProCommunicatedDTO> getProCommunicatedDTOs() {
        return proCommunicatedDTOs;
    }

    public void setProCommunicatedDTOs(List<ProCommunicatedDTO> proCommunicatedDTOs) {
        this.proCommunicatedDTOs = proCommunicatedDTOs;
    }

    public List<ProNoCommunicateDTO> getProNoCommunicateDTOs() {
        return proNoCommunicateDTOs;
    }

    public void setProNoCommunicateDTOs(List<ProNoCommunicateDTO> proNoCommunicateDTOs) {
        this.proNoCommunicateDTOs = proNoCommunicateDTOs;
    }

    public List<String> getEnginTypes() {
        return enginTypes;
    }

    public void setEnginTypes(List<String> enginTypes) {
        this.enginTypes = enginTypes;
    }

    public ProApplyCompanyDTO getProApplyCompanyDTO() {
        return proApplyCompanyDTO;
    }

    public void setProApplyCompanyDTO(ProApplyCompanyDTO proApplyCompanyDTO) {
        this.proApplyCompanyDTO = proApplyCompanyDTO;
    }

    public ProRecommendListDTO getProRecommendListDTO() {
        return proRecommendListDTO;
    }

    public void setProRecommendListDTO(ProRecommendListDTO proRecommendListDTO) {
        this.proRecommendListDTO = proRecommendListDTO;
    }

    public ProOrgSuggestionDTO getProOrgSuggestionDTO() {
        return proOrgSuggestionDTO;
    }

    public void setProOrgSuggestionDTO(ProOrgSuggestionDTO proOrgSuggestionDTO) {
        this.proOrgSuggestionDTO = proOrgSuggestionDTO;
    }

    public List<String> getNoCommunicateTypes() {
        return noCommunicateTypes;
    }

    public void setNoCommunicateTypes(List<String> noCommunicateTypes) {
        this.noCommunicateTypes = noCommunicateTypes;
    }

    public List<String> getPersonNames() {
        return personNames;
    }

    public void setPersonNames(List<String> personNames) {
        this.personNames = personNames;
    }
}
