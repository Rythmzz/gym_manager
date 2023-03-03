package com.doan.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class Menu1 implements HandlerInterceptor {
	@Autowired
	
	public boolean preHandle(
			  HttpServletRequest request,
			  HttpServletResponse response, 
			  Object handler) throws Exception {
				List<String> menu1s=new ArrayList<String>();
				menu1s.add("home");
				menu1s.add("statistics");
				menu1s.add("information");
				request.setAttribute("menu1s", menu1s);
				return true;
				
//				System.out.println("ok");
//				String username=request.getParameter("username");
//				String password=request.getParameter("password");
//				if (username.equals("admin") && password.equals("admin")) return true;
//				return false;
			}
			public void postHandle(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, 
			  ModelAndView modelAndView) throws Exception {
			    // your code
			}
			public void afterCompletion(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, Exception ex) {
			    // your code
			}
}
