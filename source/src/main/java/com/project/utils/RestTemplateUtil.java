package com.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Map;

import com.project.enums.TLSCompanyEnum;
import com.project.enums.MessageCodeEnum;
import com.project.exception.HandleErrorRestTemplate;
import com.project.property.TLSCompanyA;
import com.project.property.TLSCompanyB;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;


public class RestTemplateUtil {

	private static RestTemplate restTemplate;

	public static RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new HandleErrorRestTemplate());
		}
		return restTemplate;
	}

	public static <T> ResponseEntity<T> getForEntity(String url, MultiValueMap<String, String> additionalHeaders, String companyTLS,
													 Integer connectTimeout, Object object, Class<T> responseType, MessageCodeEnum messageError) {
		try {
			RestTemplate restTemplate = getRestTemplate();

			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			if (!StringUtils.isEmpty(companyTLS)) {
				httpRequestFactory = createHttpRequestFactoryTLS(companyTLS);
			}
			if (connectTimeout != null) {
				httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
				httpRequestFactory.setConnectTimeout(connectTimeout);
				httpRequestFactory.setReadTimeout(connectTimeout);
			}
			restTemplate.setRequestFactory(httpRequestFactory);

			return restTemplate.exchange(url, HttpMethod.GET,
					HttpEntityUtil.createHttpEntity(object, additionalHeaders), responseType);
		} catch (Exception e) {
			ExceptionUtil.throwCustomException(messageError);
		}
		return null;
	}

	public static <T> ResponseEntity<T> postForEntity(String url, MultiValueMap<String, String> additionalHeaders, String companyTLS,
													  Integer connectTimeout, Class<T> responseType, MessageCodeEnum messageError, Map<String, ?> uriVariables) {
		try {
			RestTemplate restTemplate = getRestTemplate();

			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			if (!StringUtils.isEmpty(companyTLS)) {
				httpRequestFactory = createHttpRequestFactoryTLS(companyTLS);
			}
			if (connectTimeout != null) {
				httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
				httpRequestFactory.setConnectTimeout(connectTimeout);
				httpRequestFactory.setReadTimeout(connectTimeout);
			}
			restTemplate.setRequestFactory(httpRequestFactory);

			return restTemplate.exchange(url, HttpMethod.POST, HttpEntityUtil.createHttpEntity(null, additionalHeaders),
					responseType, uriVariables);
		} catch (Exception e) {
			ExceptionUtil.throwCustomException(messageError);
		}
		return null;
	}

	public static <T> ResponseEntity<T> getForEntity(String url, MultiValueMap<String, String> additionalHeaders, String companyTLS,
													 Integer connectTimeout, Class<T> responseType, MessageCodeEnum messageError, Map<String, ?> uriVariables) {
		try {
			RestTemplate restTemplate = getRestTemplate();

			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			if (!StringUtils.isEmpty(companyTLS)) {
				httpRequestFactory = createHttpRequestFactoryTLS(companyTLS);
			}
			if (connectTimeout != null) {
				httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
				httpRequestFactory.setConnectTimeout(connectTimeout);
				httpRequestFactory.setReadTimeout(connectTimeout);
			}
			restTemplate.setRequestFactory(httpRequestFactory);

			return restTemplate.exchange(url, HttpMethod.GET, HttpEntityUtil.createHttpEntity(null, additionalHeaders),
					responseType, uriVariables);
		} catch (Exception e) {
			ExceptionUtil.throwCustomException(messageError);
		}
		return null;
	}

	public static <T> ResponseEntity<T> postForEntity(String url, MultiValueMap<String, String> additionalHeaders, String companyTLS,
													  Integer connectTimeout, Object request, Class<T> responseType, MessageCodeEnum messageError) {
		try {
			RestTemplate restTemplate = getRestTemplate();

			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			if (!StringUtils.isEmpty(companyTLS)) {
				httpRequestFactory = createHttpRequestFactoryTLS(companyTLS);
			}
			if (connectTimeout != null) {
				httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
				httpRequestFactory.setConnectTimeout(connectTimeout);
				httpRequestFactory.setReadTimeout(connectTimeout);
			}
			restTemplate.setRequestFactory(httpRequestFactory);

			return restTemplate.exchange(url, HttpMethod.POST,
					HttpEntityUtil.createHttpEntity(request, additionalHeaders), responseType);
		} catch (Exception e) {
			ExceptionUtil.throwCustomException(messageError);
		}
		return null;
	}

	public static <T> ResponseEntity<T> putForEntity(String url, MultiValueMap<String, String> additionalHeaders, String companyTLS,
													 Integer connectTimeout, Object request, Class<T> responseType, MessageCodeEnum messageError) {
		try {
			RestTemplate restTemplate = getRestTemplate();

			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			if (!StringUtils.isEmpty(companyTLS)) {
				httpRequestFactory = createHttpRequestFactoryTLS(companyTLS);
			}
			if (connectTimeout != null) {
				httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
				httpRequestFactory.setConnectTimeout(connectTimeout);
				httpRequestFactory.setReadTimeout(connectTimeout);
			}
			restTemplate.setRequestFactory(httpRequestFactory);

			return restTemplate.exchange(url, HttpMethod.PUT,
					HttpEntityUtil.createHttpEntity(request, additionalHeaders), responseType);
		} catch (Exception e) {
			ExceptionUtil.throwCustomException(messageError);
		}
		return null;
	}

	private static HttpComponentsClientHttpRequestFactory createHttpRequestFactoryTLS(String companyTLS) throws Exception {
		String instance = null;
		String resource = null;
		char[] password = null;
		String protocol = null;

		if (companyTLS.equalsIgnoreCase(TLSCompanyEnum.COMPANY_A.getCode())) {
			instance = TLSCompanyA.INSTANCE;
			resource = TLSCompanyA.RESOURCE_FILE;
			password = TLSCompanyA.PASSWORD.toCharArray();
			protocol = TLSCompanyA.PROTOCOL;
		} else if (companyTLS.equalsIgnoreCase(TLSCompanyEnum.COMPANY_B.getCode())) {
			instance = TLSCompanyB.INSTANCE;
			resource = TLSCompanyB.RESOURCE_FILE;
			password = TLSCompanyB.PASSWORD.toCharArray();
			protocol = TLSCompanyB.PROTOCOL;
		}

		if (instance == null || resource == null || password.length == 0 || protocol == null) {
			return new HttpComponentsClientHttpRequestFactory();
		}

		KeyStore clientStore = KeyStore.getInstance(instance);
		File file = new File(resource);
		char[] passwordChar = password;
		clientStore.load(new FileInputStream(file), passwordChar);
		SSLContext sslContext = SSLContextBuilder.create()
				.setProtocol(protocol)
				.loadKeyMaterial(clientStore, passwordChar)
				.loadTrustMaterial(new TrustSelfSignedStrategy())
				.build();

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslConnectionSocketFactory)
				.build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	private static HttpComponentsClientHttpRequestFactory createHttpRequestFactory(Integer connectTimeout) {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(connectTimeout);
		httpRequestFactory.setConnectTimeout(connectTimeout);
		httpRequestFactory.setReadTimeout(connectTimeout);
		return httpRequestFactory;
	}
}
