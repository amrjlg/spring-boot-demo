package com.jiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * @author Jiang
 * @date 2018/11/29
 * @time 13:42
 */
@SpringBootApplication
public class SessionApp {
    public static void main(String[] args) {
        SpringApplication.run(SessionApp.class, args);
    }

    /**
     * tomcat error page config
     * URI 字符配置
     */
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return (TomcatServletWebServerFactory container) -> {
            //uri 字符包含 {}|
            container.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
            container.addErrorPages(error404Page, error500Page);
        };
    }
}
