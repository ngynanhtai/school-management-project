package com.project.configuration;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.project.constant.Constant;

@Component
public class Translator {
	private static ResourceBundleMessageSource messageSource;
	public Translator(ResourceBundleMessageSource messageSource) {
		Translator.messageSource = messageSource;
	}
	
	public static String tolocale(String msgCode, Object... objects) {
		if(StringUtils.isBlank(msgCode)) {
			return Constant.EMPTY;
		}
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(msgCode, objects, 
				locale.toLanguageTag()
				.equals(Constant.EN)?locale : new Locale(Constant.VI));
	}
}
