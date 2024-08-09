package com.mvc.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
// http://localhost:2408/swagger-ui/index.html#/
// 访问swagger-ui网址
@EnableOpenApi
@Configuration  // 注入spring boot
public class SwaggerConfig {
    @Bean   // 要想配置生效必须注入
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("Spring Boot2.7.6中使用Swagger3.0.0构建RESTful APIs")
                .description("我可是描述信息哈~~更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .version("8.0")
                .build();
    }
}

