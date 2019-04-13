package com.kgc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kgc.entity.User;
import com.kgc.util.Common;

/**
 * 拦截未登录时访问web资源或控制器的请求
 * 继承Spingmvc的HandlerInterceptorAdapter
 * @author admin
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 重写拦截Handle的核心方法,返回true表示放行,false拦截
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute(Common.USER_SESSION);
		
		if(null == user){
			response.sendRedirect(request.getContextPath()+"/sys/login");
			return false;
		}
		return true;
	}
}
