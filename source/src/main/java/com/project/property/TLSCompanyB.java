package com.project.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TLSCompanyB {

	public static String INSTANCE;

	public static String RESOURCE_FILE;

	public static String PROTOCOL;

	public static String PASSWORD;

	@Autowired
	public void loadTlsProperties(@Value("${tls.company-b.instance}") String instance,
			@Value("${tls.company-b.resource}") String resource,
			@Value("${tls.company-b.protocol}") String protocol,
			@Value("${tls.company-b.password}") String password) {
		INSTANCE = instance;
		RESOURCE_FILE = resource;
		PROTOCOL = protocol;
		PASSWORD = password;
	}
}
