package com.mobileapplication.exposys.translate.api.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;



@Configuration
@EnableSwagger2WebMvc
@EnableWebMvc
@SuppressWarnings("deprecation")
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	
	public SwaggerConfig() {
		super();
	}
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mobileapplication.exposys.translate.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/MATE-INF/resources/webjars/");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Translation-Service").description("Translation Api").version("1.0").build();
	}

}
