package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.entity.p01_login.User;
import com.example.demo.service.p01_login.UserDetailsImpl;
import com.example.demo.service.p01_login.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationSuccessHandlerEx implements AuthenticationSuccessHandler {
	
	@Autowired
	UserDetailsServiceImpl service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (response.isCommitted()) {
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
		String json = mapper.writeValueAsString(user);
		json = json.replaceFirst(",\"password\":\".*?\"", "");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.setStatus(HttpStatus.OK.value());
		clearAuthenticationAttributes(request);
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
