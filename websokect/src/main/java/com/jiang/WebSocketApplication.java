package com.jiang;

import com.jiang.websokect.config.MessageWebSocketHandler;
import com.jiang.websokect.config.WebSocketSever;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Jiang
 * @date 2018/11/22
 * @time 10:55
 */
@SpringBootApplication
@EnableWebSocket
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class,args);
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WebSocketSever webSocketSever(){
        return  new WebSocketSever();
    }
    @Bean
    public WebSocketHandler messageWebSocketHandler(){
        return new PerConnectionWebSocketHandler(MessageWebSocketHandler.class);
    }
}
