package com.leantech.pruebaleantech.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.leantech.pruebaleantech.commons.LeanTechConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(LeanTechConstants.COMMON_STRING_CONTROLLER_PACKAGE_BASE))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(LeanTechConstants.COMMON_STRING_SWAGGER_TITLE,
				LeanTechConstants.COMMON_STRING_SWAGGER_DESCRIPTION, LeanTechConstants.COMMON_STRING_VERSION,
				LeanTechConstants.COMMON_STRING_LEAN_TECH_WEBSITE,
				new Contact(LeanTechConstants.COMMON_STRING_MY_NAME, LeanTechConstants.COMMON_STRING_MY_LINKEDIN,
						LeanTechConstants.COMMON_STRING_MY_EMAIL),
				LeanTechConstants.COMMON_STRING_LICENSE, LeanTechConstants.COMMON_STRING_EMPTY,
				Collections.emptyList());
	}
}
