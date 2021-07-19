package com.beerhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.beerhouse"))
				.paths(PathSelectors.regex("/beers.*"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Sensedia Challenge Barbara Martins")
	            .description("The purpose of this project is to develop an API Rest application using Spring Boot and data persistence.")
	            .version("1.0")
	            .contact(new Contact("Barbara", "https://github.com/BahMartins" , "bahmartins2@gmail.com"))
	            .build();
	}
	

	
}
