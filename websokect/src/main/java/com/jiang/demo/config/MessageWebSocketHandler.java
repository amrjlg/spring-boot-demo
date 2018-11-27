package com.jiang.demo.config;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.nio.ByteBuffer;

/**
 * @author Jiang
 * @date 2018/11/22
 * @time 11:16
 */
public class MessageWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer messageBuffer = message.getPayload();
        System.out.println(messageBuffer);
    }
}
