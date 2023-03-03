package com.doan.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckSession implements HandlerInterceptor {
	public boolean preHandle(
			  HttpServletRequest request,
			  HttpServletResponse response, 
			  Object handler) throws Exception {
//				TaiKhoanService taiKhoanService= new TaiKhoanService();
//				List<TaiKhoan> taiKhoans = taiKhoanService.listAll();
				try {
					if(request.getSession().getAttribute("username").equals(null)) {
						response.sendRedirect("/HelloSpringMVC/home");
					}else return true;
				}catch(Exception e) {
					response.sendRedirect("/HelloSpringMVC/home");
					
				}
				return false;
				
			}
	public void postHandle(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, 
			  ModelAndView modelAndView) throws Exception {
			    		  
			}
	public void afterCompletion(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, Exception ex) {
			    // your code
			}
}
