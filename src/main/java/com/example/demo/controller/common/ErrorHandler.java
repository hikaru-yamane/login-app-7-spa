package com.example.demo.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.view.InternalResourceView;

@Controller
@RequestMapping("/error")
public class ErrorHandler implements ErrorController {
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest req) {
		ServletWebRequest swr = new ServletWebRequest(req);
		DefaultErrorAttributes dea = new DefaultErrorAttributes();
		ErrorAttributeOptions eao = ErrorAttributeOptions.of(
			ErrorAttributeOptions.Include.BINDING_ERRORS,
			ErrorAttributeOptions.Include.EXCEPTION,
			ErrorAttributeOptions.Include.MESSAGE,
			ErrorAttributeOptions.Include.STACK_TRACE);
		return dea.getErrorAttributes(swr, eao);
	}
	
	private HttpStatus getHttpStatus(HttpServletRequest req) {
		Object statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (statusCode != null) {
			String code = statusCode.toString();
			switch (code) {
				case "401":
					status = HttpStatus.UNAUTHORIZED;
					break;
				case "403":
					status = HttpStatus.FORBIDDEN;
					break;
				case "404":
					status = HttpStatus.NOT_FOUND;
					break;
			}
		}
		return status;
	}
	
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public InternalResourceView returnErrorHtml(HttpServletRequest req) {
		HttpStatus status = getHttpStatus(req);
		int code = status.value();
		if (code == 404) {
			return new InternalResourceView("/index.html");
		}
		return new InternalResourceView("/index.html");
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> returnErrorJson(HttpServletRequest req) {
		Map<String, Object> attr = getErrorAttributes(req);
		HttpStatus status = getHttpStatus(req);
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("status", status);
		body.put("timestamp", attr.get("timestamp"));
		body.put("error", attr.get("error"));
		body.put("exception", attr.get("exception"));
		body.put("message", attr.get("message"));
		body.put("errors", attr.get("errors"));
		body.put("trace", attr.get("trace"));
		body.put("path", attr.get("path"));
		return new ResponseEntity<>(body, status);
	}
	
	@PostMapping("/login")
	public String  login() {
		return "redirect:/login";
	}
	
}
