package com.jiang.demo.websokect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Jiang
 * @date 2018/11/22
 * @time 10:58
 */
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //浏览器支持webSocket
        webSocketHandlerRegistry.addHandler(webSocketHandler(),"/message");
        //使用socket js 模拟
        webSocketHandlerRegistry.addHandler(webSocketHandler(),"/js/message").withSockJS();
    }
    @Bean
    public WebSocketHandler webSocketHandler(){
        return new MessageWebSocketHandler();
    }
}
