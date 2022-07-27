package com.example.demo.aspect;

import java.util.Map;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@Around("execution(* com.example.demo.controller..*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		String sessionId = request.getSession().getId();
		String servletPath = request.getServletPath();
		String method = request.getMethod();
		String body = ((Supplier<String>) () -> {
			String params = "";
			for (Map.Entry<String, String[]> paramMap : request.getParameterMap().entrySet()) {
				String key = paramMap.getKey();
				String[] values = paramMap.getValue();
				String value = values.length == 1 ? values[0] : ("[" + String.join(",", values) + "]");
				String param = key + "=" + value;
				params = params + "&" + param;
			}
			params = "".equals(params) ? "" : params.substring(1);
			return params;
		}).get();
		
		log.info(sessionId + " " + servletPath + " " + method + " " + body);
		
		Object result = joinPoint.proceed();
		
		String statusCode = String.valueOf(response.getStatus());
		
		log.info(sessionId + " " + statusCode);
		
		return result;
	}

}
