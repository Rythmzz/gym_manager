package com.doan.configs;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.doan.interceptor.CheckAdmin;
import com.doan.interceptor.CheckAllSite;
import com.doan.interceptor.CheckSession;
import com.doan.interceptor.Menu;
import com.doan.interceptor.Menu1;
import com.doan.interceptor.Logging;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

 
  // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
      registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
      registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
      registry.addResourceHandler("/include/**").addResourceLocations("/js/").setCachePeriod(31556926);
  }

  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
  }

  //
  public void addInterceptors(InterceptorRegistry registry) {
   
      // LogInterceptor áp dụng cho mọi URL.
     
	  registry.addInterceptor(new CheckAllSite()).addPathPatterns("/**");
	  registry.addInterceptor(new Logging()).addPathPatterns("/**");
      registry.addInterceptor(new Menu()).addPathPatterns("/*");
      
      registry.addInterceptor(new CheckSession()).addPathPatterns("/manager101/*");//
      registry.addInterceptor(new Menu1()).addPathPatterns("/manager101/*");
//      registry.addInterceptor(new CheckAdmin()).addPathPatterns("/manager101/*");
//      //các controler này thì nhân viên không vào dc
//      registry.addInterceptor(new CheckAdmin()).excludePathPatterns("/manager101/home");
//      registry.addInterceptor(new CheckAdmin()).excludePathPatterns("/manager101/thongke");
      
//      // Đường dẫn login cũ, không còn sử dụng nữa.
//      // Sử dụng OldURLInterceptor để điều hướng tới một URL mới.
//      registry.addInterceptor(new OldLoginInterceptor())//
//              .addPathPatterns("/admin/oldLogin");
//
// 
//      // Interceptor này áp dụng cho các URL có dạng /admin/*
//      // Loại đi trường hợp /admin/oldLogin
   
//              .excludePathPatterns("/admin/oldLogin");
  }

}