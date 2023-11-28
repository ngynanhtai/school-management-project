package com.project.utils;

import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.util.MultiValueMap;
import com.project.constant.Constant;

public class HttpEntityUtil {

	public static <T> HttpEntity<T> createHttpEntity(T requestBody, MultiValueMap<String, String> additionalHeaders) {
		HttpHeaders headers = createHeaders(additionalHeaders);
		return new HttpEntity<>(requestBody, headers);
	}

	public static <T> HttpEntity<T> createHttpEntity(T requestBody, MediaType mediaType, MultiValueMap<String, String> additionalHeaders) {
		HttpHeaders headers = createHeaders(additionalHeaders);
		headers.setContentType(mediaType);
		return new HttpEntity<>(requestBody, headers);
	}

	private static HttpHeaders createHeaders(MultiValueMap<String, String> additionalHeaders) {
		HttpHeaders headers = new HttpHeaders();
		Locale locale = LocaleContextHolder.getLocale();
		headers.add("Accept-Language", locale.toLanguageTag().equals(Constant.EN) ? Constant.EN : Constant.VI);
		if (!ObjectUtils.isEmpty(additionalHeaders)) {
			headers.addAll(additionalHeaders);
		}
		return headers;
	}
}
