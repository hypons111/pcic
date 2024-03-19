package gov.pcc.pwc.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@EnableConfigurationProperties(TestLoginConfigurationProperties.class)
public class TestLoginConfiguration {}
