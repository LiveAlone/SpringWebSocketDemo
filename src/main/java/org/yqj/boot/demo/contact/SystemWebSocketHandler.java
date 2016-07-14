package org.yqj.boot.demo.contact;

import com.google.common.collect.Lists;
import org.springframework.cglib.core.Constants;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yaoqijun.
 * Date:2016-07-14
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
public class SystemWebSocketHandler implements WebSocketHandler{

    private static final ArrayList<WebSocketSession> users = Lists.newArrayList();


    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("------------------------------------------------------");
        System.out.println("**********  connection to web socket success  session");
        users.add(session);
        System.out.println("session info is : " + session.getAttributes().toString());
        session.sendMessage(new TextMessage("hello fuck"));
        System.out.println("------------------------------------------------------");

    }

    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        System.out.println("-----------------------------------------------------");
        System.out.println("receive a message from client "+message.toString());
        System.out.println("-----------------------------------------------------");
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("websocket connection closed......");
        users.remove(session);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("**************** sessions is to closed after all ");
        users.remove(session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
