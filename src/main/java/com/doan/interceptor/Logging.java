package com.doan.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Logging implements HandlerInterceptor {
	
	public boolean preHandle(
			  HttpServletRequest req,
			  HttpServletResponse response, 
			  Object handler) throws Exception {
		System.out.println("ta da den day1eeeeeeeeeeeeeeee"+response.getStatus());
		 Enumeration<String> parameterNames = req.getParameterNames();
		 
	        while (parameterNames.hasMoreElements()) {
	 
	            String paramName = parameterNames.nextElement();
	            
	 
	            String[] paramValues = req.getParameterValues(paramName);
	            for (int i = 0; i < paramValues.length; i++) {
	                String paramValue = paramValues[i];
	                System.out.println("Log value ne: "+paramValue);
	            }
	 
	        }
		 	return true;
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
