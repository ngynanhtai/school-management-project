package com.project.configuration;

import java.util.Arrays;
import java.util.List;

import com.project.utils.ListUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@Component
public class SwaggerConfig {
	
	private final String JWT = "JWT";
	private final String AUTHOR = "Authorization";
	private final String HEADER = "header";
	private final String GLOBAL = "global";
	private final String DESCRIPTION = "accessEverything";
	
	private ApiKey apiKey() { 
	    return new ApiKey(JWT, AUTHOR, HEADER); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope(GLOBAL, DESCRIPTION); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference(JWT, authorizationScopes)); 
	}

	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "INITIAL REST API",
	      "INITIAL PROJECT",
	      "1.0",
	      "INITIAL Project",
	      new Contact("INITIAL Project", "google.com", "ngynanhtai@gmail.com"),
	      "License of API",
	      "API license URL",
	      ListUtil.emptyList());
	}
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2).select()
	    		.apis(RequestHandlerSelectors.any())
	    		.paths(PathSelectors.any())
	    		.build()
	    		.apiInfo(apiInfo())
	    		.securityContexts(Arrays.asList(securityContext()))
	    		.securitySchemes(Arrays.asList(apiKey()));
	}
}
