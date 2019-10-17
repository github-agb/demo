package com.hehe.ws;

import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws")
public class ChatServer {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("this is open");
		Set<Session> sessions = session.getOpenSessions();
		System.out.println("open methodsession numbers :" +sessions.size());
		for (Session s : sessions) {
			System.out.println(s);
		}
	}
	
	@OnMessage
	public void onMessage(Session session,String message) {
		System.out.println("来自客户端的消息:" + message);
        //群发消息
		Set<Session> sessions = session.getOpenSessions();
		System.out.println("message method session numbers :" +sessions.size());
        for(Session s: sessions){
            try {
                s.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
		
	}
	
	@OnClose
	public void onClose() {
		System.out.println("this is close");
		
	}
	
	@OnError
	public void onError(Throwable t) {
		System.out.println("this is error");
		
	}
}
