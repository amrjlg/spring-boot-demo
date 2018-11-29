package com.jiang.demo.websokect.config;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Jiang
 * @date 2018/11/22
 * @time 11:47
 */
@ServerEndpoint("/message")
public class WebSocketSever {

    @OnOpen
    public void onOpen(){
        System.out.println("open");
    }
    @OnClose
    public void close(){
        System.out.println("close");
    }
    @OnMessage
    public void message(String message){
        System.out.println("message:"+message);
    }
    @OnError
    public void error(Session session, Throwable error){
        System.out.println(session);
        error.printStackTrace();
    }
}
