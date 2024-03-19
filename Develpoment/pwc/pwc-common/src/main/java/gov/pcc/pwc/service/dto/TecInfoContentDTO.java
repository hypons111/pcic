package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TecInfoContentDTO {


    private String pageTitle;

    @NotNull
    @Size(max = 4000)
    @Schema(description = "type: NVARCHAR(MAX)", required = true)
    private String content;

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TecInfoContentDTO{" +
                "pageTitle='" + pageTitle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
