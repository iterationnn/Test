package com.kgc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgc.entity.User;
import com.kgc.service.UserService;
import com.kgc.util.Common;

@Controller
@RequestMapping("/sys")
public class SystemContorller {

	Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/doLogin")
	public String doLogin(String userCode, String userPassword, 
			HttpSession session, HttpServletRequest request) {
		if(null==userCode && null==userPassword) {
			throw new RuntimeException("用户名为空异常");
		}
		logger.debug("userCode => " + userCode);
		logger.debug("userPassword => " + userPassword);
		//校验密码
		User param = new User();
		param.setUserCode(userCode);
		param.setUserPassword(userPassword);
		List<User> users = userService.getUserByParam(param);
		//用户未找到
		if(users.size()==0) {
			//回到登录页
			request.setAttribute("error", "用户名或密码错误!");
			return "login";
		}else if(users.size()==1) {
			//把登录的用户set到会话中
			session.setAttribute(Common.USER_SESSION,users.get(0) );
			return "frame";
		}else {
			throw new RuntimeException("用户账号重复异常");
		}
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpSession session) {
		//移除会话里的用户对象
		session.removeAttribute(Common.USER_SESSION);
		//请求转发:forward
		//重定向到登录控制器(页面跳转)
		return "redirect:login";
	}
	
	//=============================================================================
	//当前controller发生运行时异常的处理
	@ExceptionHandler(value={RuntimeException.class})
	public String handlerException(RuntimeException e,HttpServletRequest req){
		req.setAttribute("e", e);
		//视图名称
		return "error";
	}
	
}
