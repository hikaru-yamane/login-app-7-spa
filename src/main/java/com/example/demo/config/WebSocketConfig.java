package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.demo.service.p05_forum.ForumSocketHandler;
import com.example.demo.service.p07_movie.MovieSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private ForumSocketHandler forumSocketHandler;
	@Autowired
	private MovieSocketHandler movieSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(forumSocketHandler, "/forum/{room}").setAllowedOrigins("*");
		registry.addHandler(movieSocketHandler, "/movie/{room}").setAllowedOrigins("*");
	}

}
