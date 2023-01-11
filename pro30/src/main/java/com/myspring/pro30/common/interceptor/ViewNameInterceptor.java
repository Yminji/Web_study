package com.myspring.pro30.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ViewNameInterceptor extends HandlerInterceptorAdapter{
	//컨트롤러 실행 전 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	//컨트롤러 실행 후 DispatcherServlet이 뷰로 보내기 전에 호출
	@Override
	public void postHandle(HttpServletRequest rquest, HttpServletResponse response,
			Object handler, ModelAndView mav) throws Exception{
	}
	//뷰까지 수행하고 나서 호출
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception{
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath = request.getContextPath();
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		if(uri == null || uri.trim().equals(""))
			uri = request.getRequestURI();
		
		int begin = 0;
		if(!(contextPath == null)||("".equals(contextPath)))
			begin = contextPath.length();
		
		int end;
		if(uri.indexOf(";") != -1)
			end = uri.indexOf(";");
		else if(uri.indexOf("?") != -1)
			end = uri.indexOf("?");
		else
			end = uri.length();
		
		String fileName = uri.substring(begin, end);
		if(fileName.indexOf(".") != -1)
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		if(fileName.lastIndexOf("/") != -1)
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		
		return fileName;
		
	}
}
