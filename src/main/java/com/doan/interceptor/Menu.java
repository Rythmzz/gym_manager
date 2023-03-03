package com.doan.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class Menu implements HandlerInterceptor {
	@Autowired
	
	public boolean preHandle(
	  HttpServletRequest request,
	  HttpServletResponse response, 
	  Object handler) throws Exception {
		List<String> menus=new ArrayList<String>();
		menus.add("home");
		menus.add("contact");
		menus.add("about");
		
		menus.add("blog");
		menus.add("login");
		System.out.println("aaaaaaaa");
		 request.setAttribute("menus", menus);
		 return true;
		
//		System.out.println("ok");
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		if (username.equals("admin") && password.equals("admin")) return true;
//		return false;
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
