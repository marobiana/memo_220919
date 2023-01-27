package com.memo.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component // 스프링빈
public class PermissionInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws IOException {
		
		// 요청 url을 가져온다.
		String uri = request.getRequestURI();  //     /post/post_list_view
		logger.info("[####### preHandle: uri:{}", uri);
		
		// 세션이 있는지 확인 => 있으면 로그인
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 비로그인 && /post로 온 경우 => 로그인 페이지로 리다이렉트  return false
		if (userId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/sign_in_view");
			return false; // 컨트롤러 수행 X
		}
		
		// 로그인 && /user로 온 경우 => 글 목록 페이지로 리다이렉트 return false
		if (userId != null && uri.startsWith("/user")) {
			response.sendRedirect("/post/post_list_view");
			return false; // 컨트롤러 수행 X
		}
		
		return true; // 컨트롤러 수행 O
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler, ModelAndView mav) {
		
		logger.info("[$$$$$$$$ postHandle]");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, Object handler, Exception ex) {
		
		logger.info("[@@@@@@@ afterCompletion]");
	}
}










