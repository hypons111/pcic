package gov.pcc.pwc.utils;

import org.springframework.http.HttpHeaders;

import gov.pcc.pwc.common.MessageCode;
import tech.jhipster.web.util.HeaderUtil;

public final class PwcHeadUtil {

    private static final String APPLICATION_NAME = "PWC";

    private PwcHeadUtil() {}

    public static HttpHeaders createAlert(MessageCode messageCode) {
        return HeaderUtil.createAlert(APPLICATION_NAME, messageCode.getLevel().name(), messageCode.getMessage());
    }

}
