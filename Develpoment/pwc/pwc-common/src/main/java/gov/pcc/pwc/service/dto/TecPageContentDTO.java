package gov.pcc.pwc.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

public class TecPageContentDTO {

    @NotNull
    @Schema(description = "type: BIGINT", required = true)
    private Long pageId;

    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant pageStart;

    @NotNull
    @Schema(description = "type: DATETIME", required = true)
    private Instant pageEnd;

    @Size(max = 200)
    @Schema(description = "type: NVARCHAR(200)")
    private String pageTitle;

    private String contentType;

    private String contentTitle;

    @NotNull
    @Size(max = 4000)
    @Schema(description = "type: NVARCHAR(MAX)", required = true)
    private String content;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Instant getPageStart() {
        return pageStart;
    }

    public void setPageStart(Instant pageStart) {
        this.pageStart = pageStart;
    }

    public Instant getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(Instant pageEnd) {
        this.pageEnd = pageEnd;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TecPageContentDTO{" +
                "pageId=" + pageId +
                ", pageStart=" + pageStart +
                ", pageEnd=" + pageEnd +
                ", pageTitle='" + pageTitle + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentTitle='" + contentTitle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
