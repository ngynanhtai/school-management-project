package com.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.project.constant.Constant;

@Configuration
public class LocaleConfig {

	@Bean
    ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename(Constant.I18N_MESSAGES);
        rs.setDefaultEncoding(Constant.UTF8);
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}
