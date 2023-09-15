package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
public class Swagger2 {
    /*http://localhost:8087/swagger-ui.html*/
    /*http://localhost:8087/doc.html*/

    //配置swagger2 核心配置
    @Bean
    public Docket createRestApi(){
        //指定api类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                //定义api文档汇总信息
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.imooc.controller"))
                .paths(PathSelectors.any()).build()
                ;
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("电商平台")
                .contact(new Contact("zhoulei","www.baidu.com","562624628@qq.com"))
                .description("测试代码用文档")
                .version("1.0.0")
                .termsOfServiceUrl("www.baidu.com").build();
    }
}
