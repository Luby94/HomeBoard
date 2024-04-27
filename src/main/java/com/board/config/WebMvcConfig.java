package com.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.board.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 중요 : 폴더 위치 : WebMvcConfigurer.java
	// -> 반드시 main() 함수가 있는 클래스(Board5.Application.java) 의 패키지(com.board) 아래에 .config 를 생성해서 저장(com.board.config)
	// 용도 : Spring legacy project 는 설정을 .xml 에 저장
	// -> Spring Boot Project 는 설정을 .java(= WebMvcConfigurer.java) 에 저장
	
	// 각종 설정 정보를 저장하는 곳
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {		// registry : InterceptorRegistry 의 파라미터
		
		// WebMvcConfigurer.super.addInterceptors(registry);
		
		System.out.println("okokok");
		registry.addInterceptor(loginCheckInterceptor)
		        .addPathPatterns("/**")		// http://localhost:9090/  →  모든 경로
		        .addPathPatterns("/**/*")	// 모든 경로의 하위 경로
		        .excludePathPatterns("/log*", "/css/**", "/img/**", "/js/**", "/");		// 예외 경로 : log 로 시작하는(ex. login) 파일은 반복에서 제외
		
	}
	
	
	
}
