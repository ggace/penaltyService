package com.min.kim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.min.kim.interceptor.ConnectingInterceptor;
import com.min.kim.interceptor.LoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	
	@Autowired
	LoginInterceptor loginInterceptor;
	@Autowired
	ConnectingInterceptor connectingInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/admin/**")
		.addPathPatterns("/client/detail")
		.addPathPatterns("/client/main")
		;
		
		registry.addInterceptor(connectingInterceptor)
		.addPathPatterns("/admin/**")
		.addPathPatterns("/client/chart")
		.addPathPatterns("/client/error")
		.addPathPatterns("/client/notfound")
		;
	}
}
