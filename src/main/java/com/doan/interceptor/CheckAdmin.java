package com.doan.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.doan.entity.TaiKhoan;
import com.doan.service.TaiKhoanService;

public class CheckAdmin implements HandlerInterceptor {
	
	public boolean preHandle(
			  HttpServletRequest request,
			  HttpServletResponse response, 
			  Object handler) throws Exception {
//				TaiKhoanService taiKhoanService= new TaiKhoanService();
//				List<TaiKhoan> taiKhoans = taiKhoanService.listAll();
//				try {
//					if(request.getSession().getAttribute("maQuyen").equals("1")) {
//						response.sendRedirect("/HelloSpringMVC/home");
//					}else return true;
//				}catch(Exception e) {
//					response.sendRedirect("/HelloSpringMVC/home");
//					
//				}
//				return false;
		 return true;
				
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
