package com.project.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TLSCompanyA {

	public static String INSTANCE;

	public static String RESOURCE_FILE;

	public static String PROTOCOL;

	public static String PASSWORD;

	@Autowired
	public void loadTlsProperties(@Value("${tls.company-a.instance}") String instance,
			@Value("${tls.company-a.resource}") String resource,
			@Value("${tls.company-a.protocol}") String protocol, 
			@Value("${tls.company-a.password}") String password) {
		INSTANCE = instance;
		RESOURCE_FILE = resource;
		PROTOCOL = protocol;
		PASSWORD = password;
	}
}
