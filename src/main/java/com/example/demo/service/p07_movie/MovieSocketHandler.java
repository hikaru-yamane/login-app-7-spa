package com.example.demo.service.p07_movie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieSocketHandler extends TextWebSocketHandler {
	
	private static MovieSessionManager sessionManager = new MovieSessionManager();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws UnsupportedEncodingException {
		String room = getRoom(session);
		MovieSocketHandler.sessionManager.addSession(room, session);
		log.info("接続 room:" + room);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws UnsupportedEncodingException {
		String room = getRoom(session);
		MovieSocketHandler.sessionManager.removeSession(room, session);
		log.info("切断 room:" + room);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String room = getRoom(session);
		for (WebSocketSession ses : MovieSocketHandler.sessionManager.getSessions(room)) {
			if (ses != session) {
				ses.sendMessage(message);
        	}
		}
		log.info("送信 room:" + room + ", message:" + message.getPayload());
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		log.info("エラー:" + exception.getMessage());
	}
	
	private String getRoom(WebSocketSession session) throws UnsupportedEncodingException {
		String uri = session.getUri().toString();
		String encodedRoom = uri.replaceFirst(".*/", "");
		String room = URLDecoder.decode(encodedRoom, "UTF-8");
		return room;
	}
}
