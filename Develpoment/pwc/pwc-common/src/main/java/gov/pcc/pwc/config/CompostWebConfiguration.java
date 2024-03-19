package gov.pcc.pwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.pcc.pwc.common.web.io.DownloadableResourceMessageConverter;


@Configuration
public class CompostWebConfiguration {

    @Bean
    public DownloadableResourceMessageConverter downloadableResourceMessageConverter() {
        return new DownloadableResourceMessageConverter();
    }

}
