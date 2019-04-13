package com.kgc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

//控制器:Controller
//依据BeanName 映射Controller
public class FristController extends AbstractController{
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
		System.out.println("hello,SpringMVC!");
		//返回视图
		return new ModelAndView("index");
	}
	
}