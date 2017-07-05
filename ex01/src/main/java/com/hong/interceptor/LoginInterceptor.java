package com.hong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hong.domain.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String LOGIN = "login";
	private static final Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		System.out.println("LoginInterceptor Post : " + (UserVO)userVO);
		
		if(userVO != null){
			System.out.println("Interceptor Post");
			session.setAttribute(LOGIN, userVO);
			//response.sendRedirect("/");
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest != null ? (String)dest : "/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		System.out.println("LoginInterceptor Pre");
		
		if(session.getAttribute(LOGIN) != null){
			System.out.println("Clear Login Data");
			session.removeAttribute(LOGIN);
		}
		return true;
	}
	
	
}
