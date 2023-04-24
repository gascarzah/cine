
package net.prueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations(
			"classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket simpleDiffServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Pruebas1").apiInfo(
			apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(
				PathSelectors.any()).build().pathMapping("/").forCodeGeneration(true).enableUrlTemplating(
					false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Pruebas").description("Pruebas de la pregunta 1").version("1.0").build();
	}

}
