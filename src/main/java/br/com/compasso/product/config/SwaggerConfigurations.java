package br.com.compasso.product.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
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
public class SwaggerConfigurations {
	@Bean
	public Docket api() {
		Predicate<RequestHandler> basePackage = RequestHandlerSelectors.basePackage("br.com.compasso.product");
		Predicate<String> apiUrls = PathSelectors.ant("/products/**");
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(basePackage).paths(apiUrls).build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api de Catálogo de produtos com MySQL")
				.description("Api de um catálogo de produtos com Java e Spring Boot.")
				.contact(new Contact("Juciélen Souza", "https://github.com/Jucielen", "jucielen.souza@compasso.com.br"))
				.build();
				
	}
}