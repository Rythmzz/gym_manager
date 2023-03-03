package com.doan.interceptor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class CheckAllSite implements HandlerInterceptor {
	public boolean preHandle(
			  HttpServletRequest request,
			  HttpServletResponse response, 
			  Object handler) throws Exception {
				System.out.println("ta da den day0"+response.getStatus());
				return true;
				
			}
	public void postHandle(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, 
			  ModelAndView modelAndView) throws Exception {
		System.out.println("ta da den day1"+response.getStatus());
		
		Enumeration<String> parameterNames = request.getParameterNames();
		 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i]+"\n";
              //Declaring reference of File class
        		File file = null;
        //Declaring reference of FileOutputStream class
        		//FileOutputStream fileOutStream = null;
        		Pattern patternXSS = Pattern.compile("(?=.*<[a-z])(?=.*(>|<|alert|print|onload|fetch|\\/|src|\\+|\\\"|\\'))(?=.*>)");
        		Pattern patternXSS1 = Pattern.compile("(?=.*(\\\"|\\'))(?=.*(alert|print|onload|fetch|documentsrc))");
        		Pattern patternSQLI = Pattern.compile("(?=.*(select|union|\\;?insert|substr|if|or|and|left|right|mid|spleep|BENCHMARK))(?=.*(from|\\(|\\,|-|\\\"|\\'|set|1|\\=|select|value|md5|rand|\\>))");
        		Pattern patternSQLI1 = Pattern.compile("(?=.*(\\\"|\\'))(?=.*(or|and|\\-\\-|\\#|\\%|union|select|substr|\\(|spleep|\\=|from|value|\\|\\||\\&))");
        		Pattern patternSQLI2=Pattern.compile("alert|<|>");
                Matcher matcherXSS = patternXSS.matcher(paramValue);
                Matcher matcherXSS1 = patternXSS1.matcher(paramValue);
                Matcher matcherSQLI = patternSQLI.matcher(paramValue);
                Matcher matcherSQLI1 = patternSQLI1.matcher(paramValue);
                Matcher matcherSQLI2 = patternSQLI2.matcher(paramValue);
        		if(matcherXSS.find() || matcherXSS1.find()) {
        			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        			   LocalDateTime now = LocalDateTime.now();
        			   
        			String logg="- [Time]: "+dtf.format(now) + " #[Attacker's IP]: "+request.getRemoteAddr()+" #[Detected]: XSS attack!! # [Endpoint]: " + request.getRequestURL().toString()+ " # [Payload]: "+paramValue;
        			file = new File("E:/splunk_log/webvul.log");
        			 FileWriter fw = new FileWriter(file, true);
        			    BufferedWriter bw = new BufferedWriter(fw);
        			    bw.write(logg);
        			    //bw.newLine();
        			    bw.close();
                //System.out.println("Log value ne: "+paramValue);
        		}
        		if( (matcherSQLI.find() || matcherSQLI1.find()) && !matcherSQLI2.find()) {
        			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        			   LocalDateTime now = LocalDateTime.now();
        			   
        			String logg="- [Time]: "+dtf.format(now) + " #[Attacker's IP]: "+request.getRemoteAddr()+" #[Detected]: SQLI attack!! # [Endpoint]: " + request.getRequestURL().toString()+ " # [Payload]: "+paramValue;
        			file = new File("E:/splunk_log/webvul.log");
        			 FileWriter fw = new FileWriter(file, true);
        			    BufferedWriter bw = new BufferedWriter(fw);
        			    bw.write(logg);
        			    //bw.newLine();
        			    bw.close();
                //System.out.println("Log value ne: "+paramValue);
        		}
            }
 
        }
		if(response.getStatus()==500 ||response.getStatus()==400 || response.getStatus()==404 || response.getStatus()==403|| response.getStatus()==405 ) {
			   try {
				response.sendRedirect("home");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }  
		
			}
	public void afterCompletion(
			  HttpServletRequest request, 
			  HttpServletResponse response,
			  Object handler, Exception ex) {
		System.out.println("ta da den day2"+response.getStatus());
			   if(response.getStatus()==500 ||response.getStatus()==400 || response.getStatus()==404 || response.getStatus()==403|| response.getStatus()==405 ) {
				   try {
					response.sendRedirect("home");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			}
}
