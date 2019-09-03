package com.jiang.websokect.config;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
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
        try {
            session.sendMessage(new TextMessage("message from :"+messageBuffer.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
