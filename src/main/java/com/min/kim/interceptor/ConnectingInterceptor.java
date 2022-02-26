package com.min.kim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ConnectingInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getHeader("REFERER") == null) {
			request.setAttribute("isDirectAccess", true);
		}
		else {
			request.setAttribute("isDirectAccess", false);
		}
		
		System.out.println(request.getRequestURL().toString());
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
