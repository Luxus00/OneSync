package com.vnpt.eoffice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceConfiguration.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        long maxAgeInSeconds = 3600;
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(maxAgeInSeconds);
        logger.info("Cross-Origin Resource Sharing passed");
    }


}
