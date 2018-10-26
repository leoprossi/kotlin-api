package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Controller
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun swaggerSettings(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.Example.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiKey(): ApiKey {
        return ApiKey("OAuth", "Authorization", "header")
    }

    @GetMapping("/")
    fun redirectSwagger(): String {
        return "redirect:/swagger-ui.html"
    }

}
