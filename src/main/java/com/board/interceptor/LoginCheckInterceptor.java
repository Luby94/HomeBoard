package com.board.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	// Alt+Shift+s -> Override/Implement Methods -> preHandle 체크 후 Finish
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. 세션에서 회원정보를 검색
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("login");	// Object 는 모든 클래스의 부모, 부모는 자식에 담을 수 없음, 자식은 부모에 담을 수 있음
		
		// 2. 요청한 주소정보 확인
		String requestUrl = request.getRequestURL().toString();	// .toString() 없으면 type mismatch 에러 (String <-> StringBuffer)
		
		// 3. /login 페이지는 체크에서 제외한다(제외설정)
		// 3-2. 전제 : interface 설정하는 곳(페이지)에서 해당 경로를 제외할 때 if() 필요없다
		if( requestUrl.contains("/login") ) {
			return true;	// 로그인 체크를 중단
		}
		
		// 4. 로그인 되어 있지 않다면 /loginForm 으로 이동
		if( obj == null ) {
			response.sendRedirect("/loginForm");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
		
	}

	
	
}
