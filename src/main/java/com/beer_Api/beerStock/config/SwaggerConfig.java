package com.beer_Api.beerStock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.*;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String BASE_PACKAGE = "com.beer_Api.beerStock.controller";
	private static final String API_TITLE = "Beer Stock API";
	private static final String API_DESCRIPTION = "Rest API for beer stock management";
	private static final String CONTACT_NAME = "Marcos Pontes";
	private static final String CONTACT_GITHUB = "https://github.com/MarcosPontes136";
	private static final String CONTACT_EMAIL = "marcos.ponts@hotmail.com";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(buildApiInfo());
	}
	
	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITLE)
				.description(API_DESCRIPTION)
				.version("1.0.0")
				.contact(new Contact(CONTACT_NAME, API_DESCRIPTION, CONTACT_EMAIL))
				.build();
	}

}
